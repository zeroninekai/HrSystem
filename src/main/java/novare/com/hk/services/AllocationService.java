package novare.com.hk.services;

import java.util.Date;
import java.util.List;

import novare.com.hk.model.Allocation;
import novare.com.hk.model.Employee;
import novare.com.hk.model.Project;

public interface AllocationService {

	public void insertData(Allocation allocation) throws Exception;

	public List<Allocation> getAllocationList();

	public void updateData(Allocation allocation) throws Exception;

	public void deleteData(int id);

	public Allocation getAllocation(int id);

	public List<Allocation> filterAllocation(String project_name);

	public List<Allocation> searchAllocation(String searchquery);

	public List<Project> defaultAlloc(List<Project> projectList);

	public List<Project> filterPdf(List<Project> projectList,String project_name);

	public List<Project> generatePdf(Date startDateParam, Date endDateParam, List<Project> projectList);

    public List<Employee> viewExceededAllocation(List<Employee> employeeExceededList);
}
