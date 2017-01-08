package service.Project;

import model.Project;

import java.util.List;

/**
 * Created by LulzimG on 30/12/16.
 */
public interface ProjectService {
    List<Project> getAllProjects();

    Project getProject(String projectId);

    String addProject(Project project);

    String updateProject(Project project);

    String deleteProject(String projectId);
}
