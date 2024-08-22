package uz.pdp.jpabuddy.project;


import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/project")
public class ProjectController {

    private final ProjectRepository projectRepository;
    private final ProjectMapper projectMapper;

    public ProjectController(ProjectRepository projectRepository, ProjectMapper projectMapper) {
        this.projectRepository = projectRepository;
        this.projectMapper = projectMapper;
    }

    @GetMapping
    public List<ProjectDTO> projectDTOList(){
        List<Project> list = projectRepository.findAll();
        return projectMapper.toDto(list);
    }

    @PostMapping
    public Project create(@RequestBody ProjectCreatDTO dto){
        Project project = projectMapper.fromCreateDTO(dto);
        project.setCode(project.getName().replace(" ","_").toUpperCase());
        return projectRepository.save(project);
    }
}
