package team.gdgocspring.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User {
    @Id
    @Column(length = 255)
    private String googleSubject;
    private String email;
    private String name;

    protected User() {
    }

    public User(String googleSubject) {
        this.googleSubject = googleSubject;
    }

    public void updateProfile(String email, String name) {
        this.email = email;
        this.name = name;
    }

    public String getGoogleSubject() {
        return googleSubject;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }
}
