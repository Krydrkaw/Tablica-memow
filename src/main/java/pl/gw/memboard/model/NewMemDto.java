package pl.gw.memboard.model;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class NewMemDto {

    private String title;
    private String description;
    private MultipartFile file;

}
