package novare.com.hk.repository;

import java.util.Date;
import java.util.List;

import novare.com.hk.model.Project;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ProjectRepository extends JpaRepository<Project, Integer> {

	@Query("SELECT p from Project p WHERE LOWER(p.project_name) LIKE CONCAT('%',:searchParam,'%')"
			+ " OR LOWER(p.client) LIKE CONCAT('%',:searchParam,'%')")
	public List<Project> searchProject(@Param("searchParam") String searchParam);

	@Query("SELECT p FROM Project p WHERE p.project_name = ?1")
	public List<Project> filterProject(String filterStat);

	@Query("SELECT DISTINCT p FROM Project p"
			+ " JOIN p.allocations AS alloc"
			+ "  WHERE alloc.start_date BETWEEN ?1 AND ?2")
	public List<Project> generateReport(Date dateParam, Date endDateParam);

	@Query("SELECT DISTINCT p FROM Project p"
			+ " JOIN p.allocations AS alloc"
			+ "  WHERE alloc.start_date >= ?1")
	public List<Project> generateReport(Date dateParam);

    @Query(value="SELECT p.project_name AS project_name, "
            + "MONTHNAME(alloc.start_date), "
            + "YEAR(alloc.start_date), "
            + "COUNT(alloc.employee.id), "
            + "SUM((alloc.percent/100.0000)), "
            + "SUM((alloc.employee.cost*(alloc.percent/100))) "
            + "from Allocation alloc "
            + "INNER JOIN alloc.project as p "
            + "INNER JOIN alloc.employee as e "
            + "WHERE alloc.start_date >= DATE(?1) "
            + "GROUP BY YEAR(alloc.start_date), MONTH(alloc.start_date),  p.project_name")
    public List<Object[]> genReport(Date start_date);

    @Query(value="SELECT p.project_name AS project_name,"
            + "MONTHNAME(alloc.start_date), "
            + "YEAR(alloc.start_date), "
            + "COUNT(alloc.employee.id), "
            + "SUM((alloc.percent/100.0000)), "
            + "SUM((alloc.employee.cost*alloc.percent/100))"
            + "from Allocation alloc "
            + "INNER JOIN alloc.project as p "
            + "INNER JOIN alloc.employee as e "
            + "WHERE alloc.start_date BETWEEN DATE(?1) AND DATE(?2) "
            + "GROUP BY YEAR(alloc.start_date), MONTH(alloc.start_date),  p.project_name")
    public List<Object[]> genReport(Date start_date, Date end_date);

}
