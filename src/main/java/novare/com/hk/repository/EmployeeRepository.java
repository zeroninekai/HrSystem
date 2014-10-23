package novare.com.hk.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import novare.com.hk.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {


	@Query("SELECT e FROM Employee e WHERE status = ?1")
	public List<Employee> filterEmployee(String filterStat);

	public List<Employee> findByFnameContainingOrLnameContaining(String search_param,String search_param1);
}
