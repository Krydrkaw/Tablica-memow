package pl.gw.memboard.service;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.gw.memboard.model.*;
import pl.gw.memboard.repository.CommentRepository;
import pl.gw.memboard.repository.MemRepository;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class MemService {

    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    private final MemRepository repository;
    private final CommentRepository commentRepository;

    public MemService(MemRepository repository, CommentRepository commentRepository) {
        this.repository = repository;
        this.commentRepository = commentRepository;
    }


    public List<MemDto> getAllMems() {
        log.info("Pobieranie wszystkich memów");
        return repository.findAll()
                .stream()
                .map(this::mapToMemDto)
                .toList();
    }

    public void saveNewMem(NewMemDto newMemDto) {
        Mem mem = new Mem();
        mem.setCreationDate(LocalDateTime.now());
        mem.setTitle(newMemDto.getTitle());
        mem.setDescription(newMemDto.getDescription());
        mem.setBase64(getBase64(newMemDto));

        repository.save(mem);
        log.info("Dodano nowy mem");
    }

    public void deleteMem(Long id) {
        repository.deleteById(id);
        log.info("Skasowano mem o id {} wraz z komentarzami", id);
    }

    public void addNewComment(Long id, NewCommentDto commentDto) {
        Optional<Mem> maybeMem = repository.findById(id);
        maybeMem.ifPresent(mem -> {
            Comment comment = new Comment();
            comment.setContent(commentDto.getText());
            comment.setCreationDate(LocalDateTime.now());
            comment.setMem(mem);

            commentRepository.save(comment);
            log.info("Dodano nowy komentarz do mema o id {}", mem.getId());
        });
    }

    private MemDto mapToMemDto(Mem mem) {
        MemDto memDto = new MemDto();
        memDto.setId(mem.getId());
        memDto.setTitle(mem.getTitle());
        memDto.setDescription(mem.getDescription());
        memDto.setSrc(toJsSrcBase64(mem.getBase64()));
        memDto.setCreationDate(mem.getCreationDate().format(DATE_TIME_FORMATTER));
        memDto.setComments(mem.getComment().stream().map(Comment::getContent).toList());

        return memDto;
    }

    private String toJsSrcBase64(String base64) {
        return "data:image/png;base64," + base64;
    }


    private String getBase64(NewMemDto memdto) {
        try {
            byte[] bytes = memdto.getFile().getBytes();
            return Base64.getEncoder().encodeToString(bytes);
        } catch (IOException e) {
            log.error("Nie można odczytać pliku!");
        }
        return null;
    }
}
