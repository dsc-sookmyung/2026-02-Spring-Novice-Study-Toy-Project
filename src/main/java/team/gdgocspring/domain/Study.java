package team.gdgocspring.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "studies")
public class Study {
    @Id
    @Column(name = "study_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer studyId;

    @Column(nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String description;
    private int capacity;

    @Column(name = "is_closed", nullable = false)
    private boolean isClosed;

    @ManyToOne
    @JoinColumn(name = "owner_id", nullable = false)
    private User owner;

    public User getOwner() {
        return owner;
    }
    public Integer getStudyId() {
        return studyId;
    }
    public String getTitle() {
        return title;
    }
    public String getDescription() {
        return description;
    }
    public int getCapacity() {
        return capacity;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public boolean isClosed() {
        return isClosed;
    }

    protected Study() {
    }

    public Study(String title, String description, int capacity, User owner) {
        this.title = title;
        this.description = description;
        this.capacity = capacity;
        this.owner = owner;
    }

    public void update(String title, String description, int capacity, int currentParticipantCount) {
        if (capacity < currentParticipantCount) {
            throw new IllegalArgumentException("모집 정원은 현재 참여 인원(" + currentParticipantCount + "명)보다 적게 설정할 수 없습니다.");
        }
        this.title = title;
        this.description = description;
        this.capacity = capacity;
    }
}
