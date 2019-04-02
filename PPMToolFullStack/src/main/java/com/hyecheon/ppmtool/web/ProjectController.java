package com.hyecheon.ppmtool.web;

import com.hyecheon.ppmtool.domain.Project;
import com.hyecheon.ppmtool.services.MapValidationErrorService;
import com.hyecheon.ppmtool.services.ProjectService;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/project")
@CrossOrigin
public class ProjectController {
    private final ProjectService projectService;
    private final MapValidationErrorService mapValidationErrorService;

    public ProjectController(ProjectService projectService, MapValidationErrorService mapValidationErrorService) {
        this.projectService = projectService;
        this.mapValidationErrorService = mapValidationErrorService;
    }

    //creat
    @PostMapping("")
    public ResponseEntity<?> createNewProject(@Valid @RequestBody Project project, BindingResult result) {
        final Optional<ResponseEntity<?>> optionalResponseEntity = mapValidationErrorService.mapValidationService(result);
        return optionalResponseEntity.orElseGet(() ->
                new ResponseEntity<>(
                        projectService.saveOrUpdateProject(project),
                        HttpStatus.CREATED));
    }

    //read
    @GetMapping("/{projectId}")
    public ResponseEntity<?> getProjectById(@PathVariable String projectId) {
        final Project projectByIdentifier = projectService.findProjectByIdentifier(projectId);
        return new ResponseEntity<>(projectByIdentifier, HttpStatus.OK);
    }

 /*   //update
    @PutMapping("/{projectId}")
    public ResponseEntity<?> updateProject(@PathVariable String projectId, @Valid @RequestBody Project project, BindingResult result) {
        final Optional<ResponseEntity<?>> optionalResponseEntity = mapValidationErrorService.mapValidationService(result);
        project.setProjectIdentifier(projectId);
        return optionalResponseEntity.orElseGet(() -> new ResponseEntity<>(projectService.saveOrUpdateProject(project), HttpStatus.OK));
    }*/

    //delete
    @DeleteMapping("/{projectId}")
    public ResponseEntity<?> deleteProject(@PathVariable String projectId) {
        projectService.deleteProjectByIdentifier(projectId.toUpperCase());
        return new ResponseEntity<>("[projectId : " + projectId + "] 가 삭제되었습니다.", HttpStatus.OK);
    }

    @GetMapping("/all")
    public Iterable<Project> getAllProject() {
        return projectService.findAllProjects();
    }

}
