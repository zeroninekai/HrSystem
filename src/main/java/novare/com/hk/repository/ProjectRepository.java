package novare.com.hk.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import novare.com.hk.model.Project;

public interface ProjectRepository extends JpaRepository<Project, Integer> {
	
	@Query(value = "SELECT p from Project p WHERE project_name = ?1 OR client = ?1")
	public List<Project> searchProject(String search_param);
	
	@Query("SELECT p FROM Project p WHERE project_name = ?1")
	public List<Project> filterProject(String filterStat);

}
