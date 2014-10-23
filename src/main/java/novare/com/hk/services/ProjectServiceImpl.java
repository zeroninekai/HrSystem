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
    public List<Project> indexProjectCost(List<Project> projectList) {
        projectList.clear();
        List<Object[]> rows = projectRepository.viewProjectsCost();
        if(rows != null){
            for(Object[] row: rows){
                Project p = new Project();
                p.setProject_name(row[0].toString());
                p.setDailyCost(Double.parseDouble(row[1].toString()));
                projectList.add(p);
            }
        }
        return projectList;
    }

    @Override
    public List<Project> indexProjectAlloc(List<Project> projectList) {
        projectList.clear();
        List<Object[]> rows = projectRepository.viewProjectsAllocation();
        if(rows != null){
            for(Object[] row: rows){
                Project p = new Project();
                p.setProject_name(row[0].toString());
                p.setTotalAllocation(Double.parseDouble(row[1].toString()));
                projectList.add(p);
            }
        }
        return projectList;
    }

}
