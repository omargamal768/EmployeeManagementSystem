package com.aliabou.secuirty.demo.controller;



import com.aliabou.secuirty.demo.Services.ProjectServices;
import com.aliabou.secuirty.entities.Project;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@SpringBootApplication
@RestController
@RequestMapping(path="api/pro")
public class ProjectController {

    protected final ProjectServices projectServices;

    public ProjectController(ProjectServices projectServices) {
        this.projectServices = projectServices;
    }


    @GetMapping("/all")
	public List<Project> getProjects() {
		return projectServices.getProjects();}


    @GetMapping("/{id}")
    public Project getProjectById(@PathVariable String id) {
        return projectServices.getProjectById(Long.valueOf(id));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProjectById(@PathVariable Long id) {
        projectServices.deleteProjectById(id);
        return new ResponseEntity<>("Project with ID " + id + " deleted successfully", HttpStatus.OK);
    }
    @PostMapping("/new")
    public ResponseEntity<Project> saveProject(@RequestBody Project project) {
        Project savedProject = projectServices.saveProject(project);
        return new ResponseEntity<>(savedProject, HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<Project> updateProject(@RequestBody Project  project) {
        Project updatedProject = projectServices.updateProject(project);
        return new ResponseEntity<>(updatedProject, HttpStatus.OK);
    }

}
