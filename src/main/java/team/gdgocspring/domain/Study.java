package team.gdgocspring.domain;

import jakarta.persistence.*;
import lombok.Builder;
import java.time.LocalDateTime;

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

    @Column(name = "recruitment_status", length = 20)
    private String recruitmentStatus;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    // 스터디 개설자와 User 엔티티 연결
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "leader_id", referencedColumnName = "google_subject")
    private User leader;

    protected Study() {
    }

    // 스터디 등록 시 사용하는 생성자
    @Builder
    public Study(String title, String content, int capacity, User leader) {
        this.title = title;
        this.content = content;
        this.capacity = capacity;
        this.leader = leader;
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

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public User getLeader() {
        return leader;
    }

    // 스터디 정보 수정
    public void update(String title, String content, Integer capacity,
                       String recruitmentStatus) {
        this.title = title;
        this.content = content;
        this.capacity = capacity;
        this.recruitmentStatus = recruitmentStatus;
    }

    @PrePersist
    protected void onCreate() {
        LocalDateTime now = LocalDateTime.now();
        this.createdAt = now;
        this.updatedAt = now;
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}
