package rnd.projects.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rnd.projects.entity.ProjectUserLink;

import java.util.List;

public interface ProjectUserLinkRepository extends JpaRepository<ProjectUserLink, Long> {

    List<ProjectUserLink> findAllByUserId (Long id);
    void deleteAllByProjectId(Long id);
    void deleteAllByUserId(Long id);
}
