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

    public List<Project> saveAllProjectsOfUser(List<Project> projects, Long userId){
        List<ProjectUserLink> links = new ArrayList<>();
        List<Project> savedProjects = projectRepository.save(projects);

        for (Project project:savedProjects){
            ProjectUserLink projectUserLink = new ProjectUserLink();
            projectUserLink.setProjectId(project.getId());
            projectUserLink.setUserId(userId);
            links.add(projectUserLink);
        }

        return savedProjects;
    }

    //Есть ли у админа возможность удаления проекта? В ТЗ об этом ничего
    public void deleteProject(Long projectId){
        projectRepository.delete(projectId);
        projectUserLinkRepository.deleteAllByProjectId(projectId);
    }

    public void deleteUsersLinks(Long userId){
        projectUserLinkRepository.deleteAllByUserId(userId);
    }
}
