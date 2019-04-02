package com.hyecheon.ppmtool.repositories;

import com.hyecheon.ppmtool.domain.Backlog;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface BacklogRepository extends CrudRepository<Backlog, Long> {
    Optional<Backlog> findByProjectIdentifier(String id);
}
