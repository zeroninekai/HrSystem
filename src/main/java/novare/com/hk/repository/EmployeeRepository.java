package novare.com.hk.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import novare.com.hk.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

}
