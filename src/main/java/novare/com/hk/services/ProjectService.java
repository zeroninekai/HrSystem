package novare.com.hk.services;

import java.util.List;

import novare.com.hk.model.Project;
import org.jfree.chart.JFreeChart;

public interface ProjectService {
	public void insertData(Project project);

	public List<Project> getProjectList();

	public void updateData(Project project);

	public void deleteData(int id);

	public Project getProject(int id);

	public List<Project> searchProject(String searchquery);

	public List<Project> filterProject(String project_name);

    public List<Project> indexProjectCost (List<Project> projectList);

    public List<Project> indexProjectAlloc (List<Project> projectList);

    public JFreeChart indexChart(List<Project> projectList);
    public JFreeChart createChart(List<Project> projectList, List<Project> projectAllocList);
}
