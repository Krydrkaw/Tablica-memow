package pl.gw.memboard.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
public class Mem {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String title;

    private String description;

    @Column(columnDefinition = "text")
    private String base64;

    private LocalDateTime creationDate;

    @OneToMany(mappedBy = "mem", cascade = CascadeType.ALL)
    private List<Comment> comment;


}
