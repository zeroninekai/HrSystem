package novare.com.hk.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import novare.com.hk.model.Project;
import novare.com.hk.repository.ProjectRepository;

@Service("projectService")
public class ProjectServiceImpl implements ProjectService {
	
	@Autowired
	private ProjectRepository projectRepository; 

	@Transactional
	public void insertData(Project project) {
		projectRepository.insertData(project);
		
	}

	@Transactional
	public List<Project> getProjectList() {
		return projectRepository.getProjectList();
	}

	@Transactional
	public void updateData(Project project) {
		projectRepository.updateData(project);;
	}

	@Transactional
	public void deleteData(int id) {
		projectRepository.deleteData(id);
	}

	@Transactional
	public Project getProject(int id) {
		return projectRepository.getProject(id);
	}

	@Transactional
	public List<Project> searchProject(String searchquery){
		return projectRepository.searchProject(searchquery);
	}
	
	@Transactional
	public List<Project> filterProject(String project_name){
		return projectRepository.filterProject(project_name);
	}
}
