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

    @CrossOrigin
    @RequestMapping(value = "/projects", method = RequestMethod.GET)
    private ResponseEntity<List<Project>> getAllProjectsOfUser(@RequestParam("stafferId") Long stafferId){
        return new ResponseEntity<>(projectService.getAllProjectsOfUser(stafferId), HttpStatus.OK);
    }

    @CrossOrigin
    @RequestMapping(value = "/projects", method = RequestMethod.POST)
    private ResponseEntity<Project> saveProjectsOfUser(@RequestParam("stafferId") Long stafferId, @RequestParam("projectName") String projectName){
        return new ResponseEntity<>(projectService.saveProjectOfUser(projectName,stafferId),HttpStatus.OK);
    }

    @CrossOrigin
    @RequestMapping(value = "/projects", method = RequestMethod.DELETE)
    private ResponseEntity<Void> deleteProject(@RequestParam("projectId") Long projectId, @RequestParam("stafferId") Long stafferId){
        projectService.deleteProject(projectId, stafferId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
