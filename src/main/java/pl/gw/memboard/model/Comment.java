package pl.gw.memboard.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(columnDefinition = "serial")
    private Long id;

    private String content;

    private LocalDateTime creationDate;

    @ManyToOne
    @JoinColumn(name = "mem_id", nullable = false)
    private Mem mem;


}
