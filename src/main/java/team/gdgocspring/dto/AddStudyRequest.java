package team.gdgocspring.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import team.gdgocspring.domain.Study;
import team.gdgocspring.domain.User;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AddStudyRequest {

    private String title;
    private String content;
    private int capacity;

    public Study toEntity(User leader) {
        return Study.builder()
                .title(title)
                .content(content)
                .capacity(capacity)
                .leader(leader)
                .build();
    }
}