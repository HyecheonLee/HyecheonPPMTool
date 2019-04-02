package com.hyecheon.ppmtool.services;

import com.hyecheon.ppmtool.domain.Backlog;
import com.hyecheon.ppmtool.domain.Project;
import com.hyecheon.ppmtool.domain.ProjectTask;
import com.hyecheon.ppmtool.exceptions.ProjectIdException;
import com.hyecheon.ppmtool.exceptions.ProjectNotFoundException;
import com.hyecheon.ppmtool.repositories.BacklogRepository;
import com.hyecheon.ppmtool.repositories.ProjectTaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProjectTaskService {
    private final BacklogRepository backlogRepository;
    private final ProjectTaskRepository projectTaskRepository;

    public ProjectTaskService(BacklogRepository backlogRepository, ProjectTaskRepository projectTaskRepository) {
        this.backlogRepository = backlogRepository;
        this.projectTaskRepository = projectTaskRepository;
    }

    public ProjectTask addProjectTask(ProjectTask projectTask) {
        final Optional<Backlog> optionalBacklog = backlogRepository.findByProjectIdentifier(projectTask.getProjectIdentifier());
        final Backlog backlog = optionalBacklog.orElseThrow(() -> new ProjectNotFoundException("Project [" + projectTask.getProjectIdentifier() + "] does not exist"));
        if (projectTask.getId() == null) { //create
            backlog.addProjectTask(projectTask);
            projectTask.setProjectSequence(backlog.getProjectIdentifier() + "-" + backlog.getPtSequence());
            projectTask.setBacklog(backlog);
        }
        return projectTaskRepository.save(projectTask);
    }

    public List<ProjectTask> findByProjectIdentifier(String projectIdentifier) {
        return projectTaskRepository.findByProjectIdentifierOrderByPriorityAscUpdateAtDesc(projectIdentifier);
    }

    public ProjectTask findPTByProjectSequence(String projectIdentifier, String ptId) {
        final Optional<Backlog> optionalBacklog = backlogRepository.findByProjectIdentifier(projectIdentifier);
        final Backlog backlog = optionalBacklog.orElseThrow(() -> new ProjectNotFoundException("Project [" + projectIdentifier + "] does not exist"));
        final Optional<ProjectTask> optionalProjectTask = projectTaskRepository.findByProjectSequence(ptId);
        final ProjectTask projectTask = optionalProjectTask.orElseThrow(() -> new ProjectNotFoundException("Project Task [" + projectIdentifier + "] not found"));
        if (projectTask.getProjectIdentifier().equals(projectIdentifier)) {
            return projectTask;
        } else {
            throw new ProjectNotFoundException("Project [" + projectIdentifier + "] does not exist in project [" + projectIdentifier + "]");
        }
    }

    public boolean deletePTByProjectSequence(String projectIdentifier, String ptId) {
        final ProjectTask projectTask = findPTByProjectSequence(projectIdentifier, ptId);
        projectTaskRepository.delete(projectTask);
        return true;
    }
}
