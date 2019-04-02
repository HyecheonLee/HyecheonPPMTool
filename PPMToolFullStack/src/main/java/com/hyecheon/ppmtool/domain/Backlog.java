package com.hyecheon.ppmtool.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Entity
public class Backlog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer ptSequence = 0;
    private String projectIdentifier;

    //one to one with project
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "project_id", nullable = false)
    @JsonIgnore
    @ToString.Exclude
    private Project project;

    //one to many project tasks
    @OneToMany(cascade = CascadeType.REFRESH, mappedBy = "backlog", orphanRemoval = true)
    private List<ProjectTask> projectTasks = new ArrayList<>();

    public List<ProjectTask> addProjectTask(ProjectTask projectTask) {
        this.projectTasks.add(projectTask);
        this.ptSequence++;
        return this.projectTasks;
    }

    public List<ProjectTask> removeProjectTask(ProjectTask projectTask) {
        this.projectTasks = this.projectTasks.stream().filter(value -> !value.getId().equals(projectTask.getId())).collect(Collectors.toList());
        return this.projectTasks;
    }
}
