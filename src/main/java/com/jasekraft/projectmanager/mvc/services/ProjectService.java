package com.jasekraft.projectmanager.mvc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.jasekraft.projectmanager.mvc.models.Project;
import com.jasekraft.projectmanager.mvc.repositories.ProjectRepository;

@Service
public class ProjectService {
	private final ProjectRepository projectRepo;
	
	public ProjectService(ProjectRepository projectRepo) {
		this.projectRepo = projectRepo;
	}
    // returns all the projects
    public List<Project> allProjects() {
    	
        return projectRepo.findAll();
    }
    // creates a book
    public Project createProject(Project p) {
        return projectRepo.save(p);
    }

    public Project findProject(Long id) {
        Optional<Project> optionalProject = projectRepo.findById(id);
        if(optionalProject.isPresent()) {
            return optionalProject.get();
        } else {
            return null;
        }
    }

    
    public void deleteBook(long id) {
    	projectRepo.deleteById(id);
    }
    
    public Project updateProject(Project proj) {
    	Optional<Project> optionalProject = projectRepo.findById(proj.getId());
    	if(optionalProject.isPresent()) {
    		projectRepo.save(proj);
    		return proj;
    	}
    	else {
    		return null;
    	}
    }
}