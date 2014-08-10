package novare.com.hk.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import novare.com.hk.model.Project;

public interface ProjectRepository extends JpaRepository<Project, Integer> {

}
