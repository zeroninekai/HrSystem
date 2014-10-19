package novare.com.hk.repository;

import java.util.Date;
import java.util.List;

import novare.com.hk.model.Allocation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AllocationRepository extends JpaRepository<Allocation, Integer> {

	@Query("SELECT a FROM Allocation a WHERE Project.project_name = ?1")
	public List<Allocation> filterAllocation(String filterStat);

	@Query("SELECT a FROM Allocation a WHERE LOWER(Project.project_name) LIKE CONCAT('%',:search_param,'%')"
			+ " OR LOWER(Employee.fname) LIKE CONCAT('%',:search_param,'%')"
			+ " OR LOWER(Employee.lname) LIKE CONCAT('%',:search_param,'%')")
	public List<Allocation> searchAllocation(@Param("search_param") String search_param);

	@Query(value="SELECT p.project_name AS project_name, " +
			"MONTHNAME(alloc.start_date), " +
			"YEAR(alloc.start_date), " +
			"COUNT(alloc.employee.id), " +
			"SUM((alloc.percent/100.0000)), " +
			"SUM((alloc.employee.cost*alloc.percent/100)) "+
			"from Allocation alloc " +
			"INNER JOIN alloc.project as p " +
			"INNER JOIN alloc.employee as e " +
			"GROUP BY YEAR(alloc.start_date), MONTH(alloc.start_date),  p.project_name")
	public List<Object[]> defaultAlloc();

	@Query(value="SELECT p.project_name AS project_name, " +
			"MONTHNAME(alloc.start_date), " +
			"YEAR(alloc.start_date), " +
			"COUNT(alloc.employee.id), " +
			"SUM((alloc.percent/100.0000)), " +
			"SUM((alloc.employee.cost*alloc.percent/100)) "+
			"from Allocation alloc " +
			"INNER JOIN alloc.project as p " +
			"INNER JOIN alloc.employee as e " +
			"WHERE alloc.project.project_name = ?1 " +
			"GROUP BY YEAR(alloc.start_date), MONTH(alloc.start_date),  p.project_name")
	public List<Object[]> filterPdf(String project_name);

	@Query(value="SELECT p.project_name AS project_name, " +
			"MONTHNAME(alloc.start_date), " +
			"YEAR(alloc.start_date), " +
			"COUNT(alloc.employee.id), " +
			"SUM((alloc.percent/100.0000)), " +
			"SUM((alloc.employee.cost*(alloc.percent/100))) "+
			"from Allocation alloc " +
			"INNER JOIN alloc.project as p " +
			"INNER JOIN alloc.employee as e " +
			"WHERE alloc.start_date >= DATE(?1) " +
			"GROUP BY YEAR(alloc.start_date), MONTH(alloc.start_date),  p.project_name")
	public List<Object[]> generatePdf(Date startDateParam);

	@Query(value="SELECT p.project_name AS project_name, " +
			"MONTHNAME(alloc.start_date), " +
			"YEAR(alloc.start_date), " +
			"COUNT(alloc.employee.id), " +
			"SUM((alloc.percent/100.0000)), " +
			"SUM((alloc.employee.cost*alloc.percent/100)) "+
			"from Allocation alloc " +
			"INNER JOIN alloc.project as p " +
			"INNER JOIN alloc.employee as e " +
			"WHERE alloc.start_date BETWEEN DATE(?1) AND DATE(?2) " +
			"GROUP BY YEAR(alloc.start_date), MONTH(alloc.start_date),  p.project_name")
	public List<Object[]> generatePdf(Date startDateParam, Date endDateParam);
}
