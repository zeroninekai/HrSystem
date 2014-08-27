package novare.com.hk.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@Entity
public class Allocation {

	@Id
	@GeneratedValue
	private int id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "employee_id")
	private Employee employee;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "project_id")
	private Project project;

	private int percent;

	@Temporal(TemporalType.DATE)
	private Date start_date;

	@Temporal(TemporalType.DATE)
	private Date end_date;

	@Transient
	private String searchquery;

	@Transient
	@Temporal(TemporalType.DATE)
	private Date reportStartDate;

	@Transient
	@Temporal(TemporalType.DATE)
	private Date reportEndDate;

	@Transient
	private double totalAlloc;

	@Transient
	private double dailyCostMonth;

	@Transient
	private String employee_name;

	@Transient
	private String project_name;

	public double getDailyCostMonth() {
		return dailyCostMonth;
	}

	public Employee getEmployee() {
		return employee;
	}

	public String getEmployee_name() {
		return employee_name;
	}

	public Date getEnd_date() {
		return end_date;
	}

	public int getId() {
		return id;
	}

	public int getPercent() {
		return percent;
	}

	public Project getProject() {
		return project;
	}

	public String getProject_name() {
		return project_name;
	}

	public Date getReportEndDate() {
		return reportEndDate;
	}

	public Date getReportStartDate() {
		return reportStartDate;
	}

	public String getSearchquery() {
		return searchquery;
	}

	public Date getStart_date() {
		return start_date;
	}

	public double getTotalAlloc() {
		return totalAlloc;
	}

	public void setDailyCostMonth(double dailyCostMonth) {
		this.dailyCostMonth = dailyCostMonth;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public void setEmployee_name(String employee_name) {
		this.employee_name = employee_name;
	}

	public void setEnd_date(Date end_date) {
		this.end_date = end_date;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setPercent(int percent) {
		this.percent = percent;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public void setProject_name(String project_name) {
		this.project_name = project_name;
	}

	public void setReportEndDate(Date reportEndDate) {
		this.reportEndDate = reportEndDate;
	}

	public void setReportStartDate(Date reportStartDate) {
		this.reportStartDate = reportStartDate;
	}

	public void setSearchquery(String searchquery) {
		this.searchquery = searchquery;
	}

	public void setStart_date(Date start_date) {
		this.start_date = start_date;
	}

	public void setTotalAlloc(double totalAlloc) {
		this.totalAlloc = totalAlloc;
	}
}
