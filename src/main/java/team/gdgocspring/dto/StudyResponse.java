package team.gdgocspring.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import team.gdgocspring.domain.Study;
import team.gdgocspring.domain.User;

@NoArgsConstructor
@Getter
public class StudyResponse {

    private Long studyid;
    private String title;
    private String content;
    private int capacity;
    private String recruitment_status;
    private User leader;

    public StudyResponse(Study study) {
        this.studyid = study.getId();
        this.title = study.getTitle();
        this.content = study.getContent();
        this.capacity = study.getCapacity();
        this.recruitment_status = study.getRecruitmentStatus();
        this.leader = study.getLeader();
    }
}