package novare.com.hk.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

//import java.text.SimpleDateFormat;

@Entity
@Table(name = "projects")
public class Project {

	@Id
	@GeneratedValue
	private int id;
	
	public List<Allocation> getAllocations() {
		return allocations;
	}

	public void setAllocations(List<Allocation> allocations) {
		this.allocations = allocations;
	}

	@OneToMany(mappedBy = "project")
	private List<Allocation> allocations;
	 
	private String client;
	private String project_name;
	
	@Temporal(TemporalType.DATE)
	private Date start_date;
	
	@Temporal(TemporalType.DATE)
	@Column(nullable = true)
	private Date end_date;

	@Transient
	private String searchquery;

	public String getClient() {
		return client;
	}

	public Date getEnd_date() {
		return end_date;

	}

	public int getId() {
		return id;
	}

	public String getProject_name() {
		return project_name;
	}

	public String getSearchquery() {
		return searchquery;
	}

	public Date getStart_date() {
		return start_date;
	}

	public void setClient(String client) {
		this.client = client;
	}

	public void setEnd_date(Date end_date) {
		this.end_date = end_date;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setProject_name(String project_name) {
		this.project_name = project_name;
	}

	public void setSearchquery(String searchquery) {
		this.searchquery = searchquery;
	}

	public void setStart_date(Date start_date) {
		this.start_date = start_date;
	}
}
