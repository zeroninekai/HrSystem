package novare.com.hk.repository;

import java.util.List;

import novare.com.hk.model.Allocation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AllocationRepository extends JpaRepository<Allocation, Integer> {

	@Query("SELECT a FROM Allocation a WHERE project.project_name = ?1")
	public List<Allocation> filterAllocation(String filterStat);

}
