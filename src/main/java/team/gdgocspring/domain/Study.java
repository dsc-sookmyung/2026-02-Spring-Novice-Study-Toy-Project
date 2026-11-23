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

    @Column(nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    @Column(nullable = false)
    private Integer capacity;

    @Column(name = "recruitment_status", nullable = false, length = 20)
    private String recruitmentStatus;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "leader_id", nullable = false)
    private User leader;

    protected Study() {
    }

    // 스터디 등록용 생성자
    @Builder
    public Study(String title, String content, Integer capacity, User leader) {
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

    public Integer getCapacity() {
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

    // 스터디 수정
    public void update(String title, String content, Integer capacity,
                       String recruitmentStatus) {
        this.title = title;
        this.content = content;
        this.capacity = capacity;
        this.recruitmentStatus = recruitmentStatus;
        this.updatedAt = LocalDateTime.now();
    }

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }
}