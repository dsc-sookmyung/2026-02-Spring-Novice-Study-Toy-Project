package team.gdgocspring.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import team.gdgocspring.domain.Study;

@NoArgsConstructor
@Getter
public class StudyResponse {

    private Long studyid;
    private String title;
    private String content;
    private int capacity;
    private String recruitment_status;
    private String leader_id;

    public StudyResponse(Study study) {
        this.studyid = study.getId();
        this.title = study.getTitle();
        this.content = study.getContent();
        this.capacity = study.getCapacity();
        this.recruitment_status = study.getRecruitmentStatus();
        this.leader_id = study.getLeader_id();
    }
}