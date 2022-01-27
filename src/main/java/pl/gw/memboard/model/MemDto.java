package pl.gw.memboard.model;

import lombok.Data;

import java.util.List;

@Data
public class MemDto {

    private Long id;
    private String title;
    private String description;
    private String src;
    private String creationDate;
    private List<String> comments;

}
