package team.gdgocspring.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import team.gdgocspring.domain.Study;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AddStudyRequest {

    private String title;
    private String description;
    private Integer capacity;

    public Study toEntity(String leaderId) {
        return Study.builder()
                .leaderId(leaderId)
                .title(title)
                .description(description)
                .capacity(capacity)
                .build();
    }
}
