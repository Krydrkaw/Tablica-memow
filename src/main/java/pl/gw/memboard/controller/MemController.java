package pl.gw.memboard.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import pl.gw.memboard.model.MemDto;
import pl.gw.memboard.model.NewCommentDto;
import pl.gw.memboard.model.NewMemDto;
import pl.gw.memboard.service.MemService;

import java.util.List;


@Controller
public class MemController {

    private final MemService memService;

    public MemController(MemService memService) {
        this.memService = memService;
    }

    @GetMapping("")
    public String home() {
        return "home";
    }


    @GetMapping("/mem")
    public String mems(Model model) {
        List<MemDto> memList = memService.getAllMems();
        model.addAttribute("mems", memList);
        model.addAttribute("newComment", new NewCommentDto());
        return "mem-board";
    }


    @GetMapping("/mem/new")
    public String newMem(Model model) {
        model.addAttribute("mem", new NewMemDto());
        return "new-mem";
    }


    @PostMapping("/mem/new")
    public String addNewMem(NewMemDto mem, BindingResult result) {
        if (result.hasErrors()) {
            return "new-mem";
        }
        memService.saveNewMem(mem);
        return "redirect:/mem";
    }


    @GetMapping("/mem/delete/{id}")
    public String deleteStudent(@PathVariable("id") Long id, Model model) {
        memService.deleteMem(id);
        return "redirect:/mem";
    }

    @PostMapping("/mem/{id}/comment/new")
    public String addNewComment(@PathVariable("id") Long id, NewCommentDto commentDto, BindingResult result){
        if (result.hasErrors()) {
            return "redirect:/mem";
        }
        memService.addNewComment(id, commentDto);
        return "redirect:/mem";
    }

}
