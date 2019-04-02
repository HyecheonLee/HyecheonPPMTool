package com.hyecheon.ppmtool.repositories;

import com.hyecheon.ppmtool.domain.Project;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProjectRepository extends JpaRepository<Project, Long> {
    Optional<Project> findByProjectIdentifier(String projectId);

    void deleteByProjectIdentifier(String projectId);
}
