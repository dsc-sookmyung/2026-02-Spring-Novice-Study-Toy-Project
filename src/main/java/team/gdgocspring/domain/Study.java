package team.gdgocspring.domain;

import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Table(name = "study")
@EntityListeners(AuditingEntityListener.class)
public class Study {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Long id;

    @Column(name = "leader_id", nullable = false)
    private String leaderId;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "capacity", nullable = false)
    private int capacity;

    @Column(name = "close", nullable = false)
    private boolean close;

    @CreatedDate
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    protected Study() {
    }

    public Study(String leaderId, String title, String description, int capacity) {
        this.leaderId = leaderId;
        this.title = title;
        this.description = description;
        this.capacity = capacity;
        this.close = false;
    }

    public Long getId() {
        return id;
    }

    public String getLeaderId() {
        return leaderId;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() { return description; }

    public int getCapacity() { return capacity; }

    public boolean getClose() { return close; }

    public LocalDateTime getCreatedAt() { return createdAt; }

    public LocalDateTime getUpdatedAt() { return updatedAt; }
}
