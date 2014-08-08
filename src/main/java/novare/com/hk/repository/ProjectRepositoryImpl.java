package novare.com.hk.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import novare.com.hk.model.Project;

@Repository("projectRepository")
public class ProjectRepositoryImpl implements ProjectRepository {
	
	@PersistenceContext
	private EntityManager em;

	public void insertData(Project project) {
		em.persist(project);
		em.flush();

		System.out.println("----------repository INSERT project EXECUTED----------");
	}

	public List<Project> getProjectList() {
		Query query = em.createQuery("SELECT proj FROM Project proj");
		List projects = query.getResultList();
		
		return projects;
	}

	public void updateData(Project projectUpdate) {
		Project projectContainer = em.find(Project.class, projectUpdate.getId()); //find employee where id = ? & set sa container
		
		projectContainer.setClient(projectUpdate.getClient());
		projectContainer.setEnd_date(projectUpdate.getEnd_date());
		projectContainer.setProject_name(projectUpdate.getProject_name());
		projectContainer.setStart_date(projectUpdate.getStart_date());
	
		em.merge(projectContainer);
	}

	public void deleteData(int id) {
		Project project = em.find(Project.class, id);
		
		if(project != null){
			em.remove(project);
		}
	}

	public Project getProject(int id) {
		Query query = em.createQuery("SELECT proj FROM Project proj WHERE proj.id IN (:tempoID)");
		query.setParameter("tempoID", id);

		return (Project) query.getSingleResult();
	}

	public List<Project> searchProject(String searchquery) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Project> filterProject(String project_name) {
		// TODO Auto-generated method stub
		return null;
	}

}
