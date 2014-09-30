package novare.com.hk.repository;

import java.util.Date;
import java.util.List;

import novare.com.hk.model.Project;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ProjectRepository extends JpaRepository<Project, Integer> {
	
	@Query("SELECT p from Project p WHERE LOWER(project_name) LIKE CONCAT('%',:searchParam,'%')"
			+ " OR LOWER(client) LIKE CONCAT('%',:searchParam,'%')")
	public List<Project> searchProject(@Param("searchParam") String searchParam);
	
	@Query("SELECT p FROM Project p WHERE project_name = ?1")
	public List<Project> filterProject(String filterStat);

	@Query("SELECT DISTINCT p FROM Project p"
			+ " JOIN p.allocations AS alloc"
			+ "  WHERE alloc.start_date BETWEEN ?1 AND ?2")
	public List<Project> generateReport(Date dateParam, Date endDateParam);
	
	@Query("SELECT DISTINCT p FROM Project p"
			+ " JOIN p.allocations AS alloc"
			+ "  WHERE alloc.start_date >= ?1")
	public List<Project> generateReport(Date dateParam);
}
