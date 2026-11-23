package team.gdgocspring.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import team.gdgocspring.domain.Study;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class AddStudyRequest {

    private String title;
    private String content;
    private int capacity;

    public Study toEntity(String leaderId) {
        return Study.builder()
                .title(title)
                .content(content)
                .capacity(capacity)
                .leader_id(leaderId)
                .build();
    }
}