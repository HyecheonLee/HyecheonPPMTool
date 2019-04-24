package com.hyecheon.ppmtool.services;

import com.hyecheon.ppmtool.domain.Backlog;
import com.hyecheon.ppmtool.domain.Project;
import com.hyecheon.ppmtool.domain.User;
import com.hyecheon.ppmtool.exceptions.ProjectIdException;
import com.hyecheon.ppmtool.exceptions.ProjectNotFoundException;
import com.hyecheon.ppmtool.repositories.BacklogRepository;
import com.hyecheon.ppmtool.repositories.ProjectRepository;
import com.hyecheon.ppmtool.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Slf4j
@Service
public class ProjectService {
    private final ProjectRepository projectRepository;
    private final BacklogRepository backlogRepository;
    private final UserRepository userRepository;

    public Project saveOrUpdateProject(Project project, String username) {
        if (project.getId() != null) {
            final Optional<Project> optionalProject = projectRepository.findByProjectIdentifier(project.getProjectIdentifier());
            if (optionalProject.isPresent()) {
                final Project existingProject = optionalProject.get();
                if (existingProject.getUser().getUsername().equals(username)) {
                    throw new ProjectNotFoundException("Project not found in your account");
                }
            } else {
                throw new ProjectNotFoundException("Project with Id: " + project.getProjectIdentifier() + " cannot be updated because it doesn't exist");
            }
        }

        try {
            final User user = userRepository.findByUsername(username);
            project.setUser(user);
            project.setProjectLeader(user.getUsername());
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


    public Project findProjectByIdentifier(String projectId, String username) {
        try {
            final Optional<Project> optionalProject = projectRepository.findByProjectIdentifier(projectId.toUpperCase());
            if (optionalProject.isPresent()) {

                final Project project = optionalProject.get();
                if (!project.getProjectLeader().equals(username)) {
                    throw new ProjectNotFoundException("Project not found in your account");
                }
                return project;
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

    public void deleteProjectByIdentifier(String projectId, String username) {
        projectRepository.delete(findProjectByIdentifier(projectId, username));
    }
}
