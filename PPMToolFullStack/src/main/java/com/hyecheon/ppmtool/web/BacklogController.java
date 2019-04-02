package com.hyecheon.ppmtool.web;

import com.hyecheon.ppmtool.domain.ProjectTask;
import com.hyecheon.ppmtool.services.MapValidationErrorService;
import com.hyecheon.ppmtool.services.ProjectTaskService;
import org.jetbrains.annotations.Contract;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/backlog")
@CrossOrigin
public class BacklogController {

    private final ProjectTaskService projectTaskService;
    private final MapValidationErrorService mapValidationErrorService;

    @Contract(pure = true)
    public BacklogController(ProjectTaskService projectTaskService, MapValidationErrorService mapValidationErrorService) {
        this.projectTaskService = projectTaskService;
        this.mapValidationErrorService = mapValidationErrorService;
    }

    @PostMapping("")
    public ResponseEntity<?> addPtToBackLog(@Valid @RequestBody ProjectTask projectTask, BindingResult result) {
        final Optional<ResponseEntity<?>> responseEntity = mapValidationErrorService.mapValidationService(result);
        return responseEntity.orElseGet(() -> new ResponseEntity<>(projectTaskService.addProjectTask(projectTask), HttpStatus.CREATED));
    }

    @GetMapping("/{projectIdentifier}")
    public ResponseEntity<List<ProjectTask>> getProjectBacklog(@PathVariable String projectIdentifier) {
        return new ResponseEntity<>(projectTaskService.findByProjectIdentifier(projectIdentifier), HttpStatus.OK);
    }

    @GetMapping("/{projectIdentifier}/{ptId}")
    public ResponseEntity<?> getProjectTask(@PathVariable String projectIdentifier, @PathVariable String ptId) {
        return new ResponseEntity<>(
                projectTaskService.findPTByProjectSequence(projectIdentifier, ptId),
                HttpStatus.OK);
    }

    @DeleteMapping("/{projectIdentifier}/{ptId}")
    public ResponseEntity<?> deleteProjectTask(@PathVariable String projectIdentifier, @PathVariable String ptId) {
        return new ResponseEntity<>(projectTaskService.deletePTByProjectSequence(projectIdentifier, ptId), HttpStatus.OK);
    }
}
