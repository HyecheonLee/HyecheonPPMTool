package com.hyecheon.ppmtool.repositories;

import com.hyecheon.ppmtool.domain.ProjectTask;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface ProjectTaskRepository extends CrudRepository<ProjectTask, Long> {
    List<ProjectTask> findByProjectIdentifierOrderByPriorityAscUpdateAtDesc(String id);

    Optional<ProjectTask> findByProjectSequence(String sequence);
}
