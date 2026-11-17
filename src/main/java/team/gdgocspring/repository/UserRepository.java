package team.gdgocspring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import team.gdgocspring.domain.User;

public interface UserRepository extends JpaRepository<User, String> {
}
