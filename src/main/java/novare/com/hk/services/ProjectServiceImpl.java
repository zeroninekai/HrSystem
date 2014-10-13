package novare.com.hk.services;

import java.util.Date;
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
		projectRepository.save(project);
	}

	@Transactional
	public List<Project> getProjectList() {
		return projectRepository.findAll();
	}

	@Transactional
	public void updateData(Project project) {
		projectRepository.save(project);
	}

	@Transactional
	public void deleteData(int id) {
		projectRepository.delete(id);
	}

	@Transactional
	public Project getProject(int id) {
		return projectRepository.findOne(id);
	}

	@Transactional
	public List<Project> searchProject(String searchquery){
		return projectRepository.searchProject(searchquery);
	}
	
	@Transactional
	public List<Project> filterProject(String project_name){
		return projectRepository.filterProject(project_name);
	}

	@Transactional
	public List<Project> getReport(Date dateParam, Date endDateParam) {
		// Both start date and end date is not empty
		if(dateParam != null && endDateParam != null){
			return projectRepository.generateReport(dateParam, endDateParam);
		}
		// When only start date has a value and end date is empty/null.
		else if(dateParam != null && endDateParam == null){
			return projectRepository.generateReport(dateParam);
		}
		else{
			return projectRepository.findAll();
		}
	}

	@Transactional
	public List<Project> gen(Date startDateParam, Date endDateParam) {
		if(startDateParam != null && endDateParam != null){
			return projectRepository.gen(startDateParam, endDateParam);
		}
		else if(startDateParam != null && endDateParam == null){
			return projectRepository.gen(startDateParam);
		}
		else{
			return projectRepository.findAll();
		}
	}
}
