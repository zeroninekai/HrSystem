package novare.com.hk.repository;

import java.util.List;

import novare.com.hk.model.Project;

public interface ProjectRepository {
	
	public void insertData(Project project);

	public List<Project> getProjectList();

	public void updateData(Project project);

	public void deleteData(int id);

	public Project getProject(int id);

	public List<Project> searchProject(String searchquery);

	public List<Project> filterProject(String project_name);
}
