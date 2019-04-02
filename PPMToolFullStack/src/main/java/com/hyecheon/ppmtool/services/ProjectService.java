package com.hyecheon.ppmtool.services;

import com.hyecheon.ppmtool.domain.Backlog;
import com.hyecheon.ppmtool.domain.Project;
import com.hyecheon.ppmtool.exceptions.ProjectIdException;
import com.hyecheon.ppmtool.repositories.BacklogRepository;
import com.hyecheon.ppmtool.repositories.ProjectRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class ProjectService {
    private final ProjectRepository projectRepository;
    private final BacklogRepository backlogRepository;

    public ProjectService(ProjectRepository projectRepository, BacklogRepository backlogRepository) {
        this.projectRepository = projectRepository;
        this.backlogRepository = backlogRepository;
    }

    public Project saveOrUpdateProject(Project project) {
        try {
            project.setProjectIdentifier(project.getProjectIdentifier().toUpperCase());
            //create
            if (project.getId() == null) {
                final Backlog backlog = new Backlog();
                project.setBacklog(backlog);
                backlog.setProject(project);
                backlog.setProjectIdentifier(project.getProjectIdentifier());
            }
            if (project.getId() != null) {
                final Optional<Backlog> optionalBacklog = backlogRepository.findByProjectIdentifier(project.getProjectIdentifier());
                if (optionalBacklog.isPresent()) {
                    final Backlog backlog = optionalBacklog.get();
                    project.setBacklog(backlog);
                    backlog.setProject(project);
                } else {
                    throw new ProjectIdException("[" + project.getProjectIdentifier() + "] : ");
                }
            }
            return projectRepository.save(project);
        } catch (Exception e) {
            throw new ProjectIdException("[" + project.getProjectIdentifier() + "] : " + e.getMessage());
        }
    }


    public Project findProjectByIdentifier(String projectId) {
        try {
            final Optional<Project> optionalProject = projectRepository.findByProjectIdentifier(projectId.toUpperCase());
            if (optionalProject.isPresent()) {
                return optionalProject.get();
            } else {
                throw new ProjectIdException("[" + projectId + "] 를 확인해 주세요");
            }
        } catch (Exception e) {
            throw new ProjectIdException(e.getMessage());
        }
    }

    public Iterable<Project> findAllProjects() {
        try {
            return projectRepository.findAll();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteProjectByIdentifier(String projectId) {
        try {
            final Optional<Project> optionalProject = projectRepository.findByProjectIdentifier(projectId);
            if (optionalProject.isPresent()) {
                projectRepository.delete(optionalProject.get());
            } else {
                throw new ProjectIdException("[" + projectId + "] 를 확인해 주세요");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
