package team.gdgocspring.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import team.gdgocspring.domain.Study;

@NoArgsConstructor
@Getter
public class StudyViewResponse {

    private Long id;
    private String title;
    private String description;
    private Integer capacity;
    private Boolean close;
    private Integer currentCount;

    public StudyViewResponse(Study study) {
        this.id = study.getId();
        this.title = study.getTitle();
        this.description = study.getDescription();
        this.capacity = study.getCapacity();
        this.close = study.isClose();
        this.currentCount = 1;
    }
}

