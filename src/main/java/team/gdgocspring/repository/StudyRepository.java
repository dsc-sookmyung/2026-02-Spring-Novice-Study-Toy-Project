package team.gdgocspring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import team.gdgocspring.domain.Study;

public interface StudyRepository extends JpaRepository<Study, Long> {
}
