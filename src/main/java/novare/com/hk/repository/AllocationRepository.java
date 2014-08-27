package novare.com.hk.repository;

import java.util.Date;
import java.util.List;

import novare.com.hk.model.Allocation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AllocationRepository extends JpaRepository<Allocation, Integer> {

	@Query("SELECT a FROM Allocation a WHERE project.project_name = ?1")
	public List<Allocation> filterAllocation(String filterStat);
	
	@Query("SELECT a FROM Allocation a WHERE project.project_name LIKE ?1 OR employee.fname = ?1 OR employee.lname = ?1")
	public List<Allocation> searchAllocation(String search_param);
	
	@Query("SELECT a FROM Allocation a"
			+ " JOIN a.employee AS emp"
			+ " JOIN a.project AS proj"
			+ "  WHERE a.start_date BETWEEN ?1 AND ?2")
	public List<Allocation> generateReport(Date dateParam, Date endDateParam);

}
