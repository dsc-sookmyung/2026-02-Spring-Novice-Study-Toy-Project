package team.gdgocspring.dto;

import lombok.Getter;
import team.gdgocspring.domain.Study;

@Getter
public class StudyResponse {

    private final Long id;
    private final String title;
    private final String description;
    private final Integer capacity;
    private final Boolean close;

    public StudyResponse(Study study) {
        this.id = study.getId();
        this.title = study.getTitle();
        this.description = study.getDescription();
        this.capacity = study.getCapacity();
        this.close = study.isClose();
    }

}
