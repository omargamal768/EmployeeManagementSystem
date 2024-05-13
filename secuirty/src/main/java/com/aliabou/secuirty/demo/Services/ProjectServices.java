package com.aliabou.secuirty.demo.Services;


import com.aliabou.secuirty.demo.Repository.ProjectRepo;
import com.aliabou.secuirty.entities.Project;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectServices {
    @Autowired
    ProjectRepo projectRepo;



    public List<Project> getProjects() {
        return projectRepo.findAll();
    }
    public Project getProjectById(Long id) {
        return projectRepo.findById(id).orElse(null);

    }
    public void deleteProjectById(Long id) {
        projectRepo.deleteById(id);
    }

    public Project saveProject(Project project) {
        // Save the employee using the repository
        return projectRepo.save(project);
    }
    public Project updateProject(Project project) {
        // Check if the employee exists
        if (projectRepo.existsById(project.getProjectId())) {
            // Update the employee using the repository
            return projectRepo.save(project);
        } else {
            // Handle the case where the employee does not exist
            throw new EntityNotFoundException("Project with ID " + project.getProjectId() + " not found");
        }
    }
}
