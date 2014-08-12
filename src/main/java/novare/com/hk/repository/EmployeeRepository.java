package novare.com.hk.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import novare.com.hk.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
	
	@Query(value = "SELECT e from Employee e WHERE first_name = ?1 OR last_name = ?1 OR department = ?1 OR status = ?1")
	public List<Employee> searchEmployee(String search_param);
	
	
	@Query("SELECT e FROM Employee e WHERE status = ?1")
	public List<Employee> filterEmployee(String filterStat);
}
