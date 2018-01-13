package rnd.projects.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rnd.projects.entity.Project;

public interface ProjectRepository extends JpaRepository<Project, Long> {
    Project findByName(String name);
}
