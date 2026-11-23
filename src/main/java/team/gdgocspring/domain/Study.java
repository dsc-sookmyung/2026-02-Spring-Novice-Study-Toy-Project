package team.gdgocspring.domain;

import jakarta.persistence.*;
import lombok.Builder;
import java.util.Date;

@Entity
@Table(name = "studies")
public class Study {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Column(columnDefinition = "TEXT")
    private String content;

    private int capacity;

    @Column(name = "recruitment_status")
    private String recruitmentStatus;

    private Date created_at;
    private Date updated_at;

    private String leader_id;

    protected Study() {
    }

    @Builder
    public Study(String title, String content, int capacity,
                 String leader_id) {
        this.title = title;
        this.content = content;
        this.capacity = capacity;
        this.leader_id = leader_id;
        this.recruitmentStatus = "OPEN";
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public int getCapacity() {
        return capacity;
    }

    public String getRecruitmentStatus() {
        return recruitmentStatus;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public Date getUpdated_at() {
        return updated_at;
    }

    public String getLeader_id() {
        return leader_id;
    }

    @PrePersist
    protected void onCreate() {
        this.created_at = new Date();
        this.updated_at = new Date();
    }
}