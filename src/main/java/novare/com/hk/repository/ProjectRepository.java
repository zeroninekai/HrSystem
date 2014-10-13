package novare.com.hk.repository;

import java.util.Date;
import java.util.List;

import novare.com.hk.model.Allocation;
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
	
	@Query(value="SELECT projects.id, projects.client, projects.end_date, projects.start_date, " +
	"projects.project_name AS 'project_name', " +
	"MONTHNAME(calendar.datefield) AS 'month', " +
	"YEAR(calendar.datefield) AS 'year', " +
	"COUNT(allocation.employee_id) AS 'plannedHeadCount', " +
	"SUM((allocation.percent/100)) AS 'totalAllocation', " +
	"SUM((employees.cost*allocation.percent/100)) AS 'dailyCost' " +

	"FROM employees " + 
	"LEFT JOIN allocation ON allocation.employee_id = employees.id " +
	"INNER JOIN projects ON projects.id = allocation.project_id " +
	"LEFT JOIN calendar on calendar.datefield = DATE(allocation.start_date) " +

	"WHERE calendar.datefield BETWEEN (SELECT MIN(DATE(?1))) AND (SELECT MAX(DATE(?2))) " +
	"GROUP BY YEAR(allocation.start_date), MONTH(allocation.start_date),  projects.project_name", nativeQuery=true)
	public List<Project> gen(Date startDateParam, Date endDateParam);
	
	@Query(value="SELECT projects.id, projects.client, projects.end_date, projects.start_date, " +
	"projects.project_name AS 'project_name', " +
	"MONTHNAME(calendar.datefield) AS 'month', " +
	"YEAR(calendar.datefield) AS 'year', " +
	"COUNT(allocation.employee_id) AS 'plannedHeadCount', " +
	"SUM((allocation.percent/100)) AS 'totalAllocation', " +
	"SUM((employees.cost*allocation.percent/100)) AS 'dailyCost' " +

	"FROM employees " + 
	"LEFT JOIN allocation ON allocation.employee_id = employees.id " +
	"INNER JOIN projects ON projects.id = allocation.project_id " +
	"LEFT JOIN calendar on calendar.datefield = DATE(allocation.start_date) " +

	"WHERE calendar.datefield >= (SELECT MIN(DATE(?1))) " +
	"GROUP BY YEAR(allocation.start_date), MONTH(allocation.start_date),  projects.project_name", nativeQuery=true)
	public List<Project> gen(Date startDateParam);
}
