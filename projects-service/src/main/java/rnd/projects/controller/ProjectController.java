package rnd.projects.controller;

import com.netflix.discovery.converters.Auto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rnd.projects.entity.Project;
import rnd.projects.service.ProjectService;

import java.util.List;

@RestController
public class ProjectController {
    @Autowired
    ProjectService projectService;

    @RequestMapping(value = "/users/{id}/projects", method = RequestMethod.GET)
    private ResponseEntity<List<Project>> getAllProjectsOfUser(@PathVariable("id") Long userId){
        return new ResponseEntity<>(projectService.getAllProjectsOfUser(userId), HttpStatus.OK);
    }

    @RequestMapping(value = "/users/{id}/projects", method = RequestMethod.POST)
    private ResponseEntity<List<Project>> saveAllProjectsOfUser(@PathVariable("id") Long userId, @RequestBody List<Project> projects){
        return new ResponseEntity<>(projectService.saveAllProjectsOfUser(projects,userId),HttpStatus.OK);
    }

    //Если уже есть такой метод в другом модуле, вызовутся ли оба метода по одному этому урлу?
    @RequestMapping(value = "/users/{id}", method = RequestMethod.DELETE)
    private ResponseEntity<Void> deleteAllLinksIfUser(@PathVariable("id") Long userId){
        projectService.deleteUsersLinks(userId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
