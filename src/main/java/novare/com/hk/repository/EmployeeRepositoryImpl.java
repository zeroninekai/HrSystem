package novare.com.hk.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import novare.com.hk.model.Employee;

@Repository("employeeRepository")
public class EmployeeRepositoryImpl implements EmployeeRepository {
	
	@PersistenceContext
	private EntityManager em;

	public void insertData(Employee employee) {
		em.persist(employee);
		em.flush();

		System.out.println("----------repository INSERT emp EXECUTED----------");
	}

	public List<Employee> getEmployeeList() {
		Query query = em.createQuery("SELECT emp FROM Employee emp");
		List employees = query.getResultList();
		
		return employees;
	}

	public void updateData(Employee employeeUpdate) {
		System.out.println("\n\n\n"	+ employeeUpdate.getFname() + " = value na gusto ipalit sa existing FNAME\n");
		System.out.println("\n"	+ employeeUpdate.getLname() + " = value na gusto ipalit sa existing LNAME\n\n");
		
		Employee empContainer = em.find(Employee.class, employeeUpdate.getId()); //find employee where id = ? & set sa container
		
		empContainer.setFname(employeeUpdate.getFname());
		empContainer.setLname(employeeUpdate.getLname());
		empContainer.setCost(employeeUpdate.getCost());
		empContainer.setDate_resigned(employeeUpdate.getDate_resigned());
		empContainer.setDepartment(employeeUpdate.getDepartment());
		empContainer.setPosition(employeeUpdate.getPosition());
		empContainer.setStart_date(employeeUpdate.getStart_date());
		empContainer.setStatus(employeeUpdate.getStatus());
		
		System.out.println("\n\nName result: "+employeeUpdate.getFname() +"\n");
		System.out.println("\nLast result: "+employeeUpdate.getLname() +"\n\n\n\n");
		
		em.merge(empContainer);
	}

	public void deleteData(int id) {
		Employee employee = em.find(Employee.class, id); //find employee where id = ?
		
		if(employee != null){
			em.remove(employee); // delete emp if id found
		}
	}

	public Employee getEmployee(int id) {
		Query query = em.createQuery("SELECT emp FROM Employee emp WHERE emp.id IN (:tempoID)");
		query.setParameter("tempoID", id);

		return (Employee) query.getSingleResult();
	}

	public List<Employee> searchEmployee(String search_param) {
/*		String sql = "SELECT emp FROM EMPLOYEE emp WHERE MATCH(first_name,last_name,"
				+ "department,status,position) AGAINST('"+search_param+"*' IN BOOLEAN MODE)";
		
		Query query = em.createQuery(Restrictions.sqlRestriction(sql));
		
		List employees = query.getResultList();*/
		
/*		String sql = String.format("SELECT * FROM EMPLOYEE WHERE MATCH(first_name,last_name,"
				+ "department,status,position) AGAINST('%s*' IN BOOLEAN MODE)", search_param);
		
		Query query = em.createNativeQuery(sql,Employee.class);
		List employees = query.getResultList();
		 return employees;*/
		
/*		//hibernate-search
		 FullTextEntityManager fullTextEntityManager = org.hibernate.search.jpa.Search.getFullTextEntityManager(em);
		 QueryBuilder qb = fullTextEntityManager.getSearchFactory().buildQueryBuilder().forEntity( Employee.class ).get();
		 org.apache.lucene.search.Query luceneQuery = qb.keyword().onFields("first_name", "last_name",
				 "department", "status", "position").matching(search_param).createQuery();
		
		 Query persistenceQuery  = fullTextEntityManager.createFullTextQuery(luceneQuery, Employee.class);
		 List employees = persistenceQuery.getResultList();*/
		return null;
	}

	public List<Employee> filterEmployee(String filterEmp) {
		// TODO Auto-generated method stub
		return null;
	}

}
