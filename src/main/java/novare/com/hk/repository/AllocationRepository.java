package novare.com.hk.repository;

import java.util.Date;
import java.util.List;

import novare.com.hk.model.Allocation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AllocationRepository extends JpaRepository<Allocation, Integer> {

	@Query("SELECT a FROM Allocation a WHERE project.project_name = ?1")
	public List<Allocation> filterAllocation(String filterStat);
	
	@Query("SELECT a FROM Allocation a WHERE LOWER(project.project_name) LIKE CONCAT('%',:search_param,'%')"
			+ " OR LOWER(employee.fname) LIKE CONCAT('%',:search_param,'%')"
			+ " OR LOWER(employee.lname) LIKE CONCAT('%',:search_param,'%')")
	public List<Allocation> searchAllocation(@Param("search_param") String search_param);
	
	@Query("SELECT a FROM Allocation a"
			+ " JOIN a.employee AS emp"
			+ " JOIN a.project AS proj"
			+ "  WHERE a.start_date BETWEEN ?1 AND ?2")
	public List<Allocation> generateReport(Date dateParam, Date endDateParam);

}
