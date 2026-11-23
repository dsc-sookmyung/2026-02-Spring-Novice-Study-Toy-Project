package team.gdgocspring.dto;

import lombok.Getter;
import team.gdgocspring.domain.Study;

@Getter
public class StudyListViewResponse {

    private final Long id;
    private final String title;
    private final String description;
    private final Integer capacity;
    private final Boolean close;
    private final Integer currentCount;

    public StudyListViewResponse(Study study) {
        this.id = study.getId();
        this.title = study.getTitle();
        this.description = study.getDescription();
        this.capacity = study.getCapacity();
        this.close = study.isClose();
        this.currentCount = 1;
    }
}
