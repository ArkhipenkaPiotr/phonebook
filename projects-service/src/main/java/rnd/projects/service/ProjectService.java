package rnd.projects.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rnd.projects.entity.Project;
import rnd.projects.entity.ProjectUserLink;
import rnd.projects.repository.ProjectRepository;
import rnd.projects.repository.ProjectUserLinkRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProjectService {
    @Autowired
    ProjectRepository projectRepository;

    @Autowired
    ProjectUserLinkRepository projectUserLinkRepository;

    public List<Project> getAllProjectsOfUser(Long id){
        List<ProjectUserLink> links;
        links = projectUserLinkRepository.findAllByUserId(id);

        List<Project> projects = new ArrayList<>();
        for (ProjectUserLink projectUserLink : links) {
            Project project = projectRepository.findOne(projectUserLink.getProjectId());
            projects.add(project);
        }
        return projects;
    }

    public Project saveProjectOfUser(String projectName, Long userId){
        Project savedProject = projectRepository.findByName(projectName);
        if (savedProject==null){
            Project project = new Project();
            project.setName(projectName);
            savedProject = projectRepository.save(project);
        }
        ProjectUserLink projectUserLink = new ProjectUserLink();
        projectUserLink.setUserId(userId);
        projectUserLink.setProjectId(savedProject.getId());
        projectUserLinkRepository.save(projectUserLink);

        return savedProject;
    }

    //Есть ли у админа возможность удаления проекта? В ТЗ об этом ничего
    public void deleteProject(Long projectId, Long userId){
        List<ProjectUserLink> projectUserLinks = projectUserLinkRepository.findByUserIdAndProjectId(userId, projectId);
        projectUserLinkRepository.delete(projectUserLinks);
    }
}
