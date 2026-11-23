package team.gdgocspring.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class UpdateStudyRequest {
    private String title;
    private String description;
    private Integer capacity;
}
