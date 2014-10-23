package novare.com.hk.services;

import java.util.Date;
import java.util.List;

import novare.com.hk.model.Allocation;
import novare.com.hk.model.Employee;
import novare.com.hk.model.Project;
import novare.com.hk.repository.AllocationRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("allocationService")
public class AllocationServiceImpl implements AllocationService{

	@Autowired
	private AllocationRepository allocationRepository;

	@Transactional
	public void insertData(Allocation allocation) throws Exception {
            allocationRepository.save(allocation);
    }

	@Transactional
	public List<Allocation> getAllocationList() {
		return allocationRepository.findAll();
	}

	@Transactional
	public void updateData(Allocation allocation) throws Exception {
            allocationRepository.save(allocation);
	}

	@Transactional
	public void deleteData(int id) {
		allocationRepository.delete(id);
	}

	@Transactional
	public Allocation getAllocation(int id) {
		return allocationRepository.findOne(id);
	}

	@Transactional
	public List<Allocation> filterAllocation(String project_name){
		return allocationRepository.filterAllocation(project_name);
	}

	@Transactional
	public List<Allocation> searchAllocation(String searchquery){
		return allocationRepository.searchAllocation(searchquery);
	}

	@Transactional
	public List<Project> defaultAlloc(List<Project> projectList) {
		projectList.clear();
		List<Object[]> rows = allocationRepository.defaultAlloc();
		if(rows != null){
			for (Object[] row: rows) {
			    Project p = new Project();
			    p.setProject_name(row[0].toString());
			    p.setMonth(row[1].toString());
			    p.setYear(row[2].toString());
			    p.setPlannedHeadCount(Long.parseLong(row[3].toString()));
			    p.setTotalAllocation(Double.parseDouble(row[4].toString()));
			    p.setDailyCost(Double.parseDouble(row[5].toString()));
			    projectList.add(p);
			}
		}
		return projectList;
	}

	@Transactional
	public List<Project> filterPdf(List<Project> projectList, String project_name) {
		projectList.clear();
		List<Object[]> rows = allocationRepository.filterPdf(project_name);
		if(rows != null){
			for (Object[] row: rows) {
			    Project p = new Project();
			    p.setProject_name(row[0].toString());
			    p.setMonth(row[1].toString());
			    p.setYear(row[2].toString());
			    p.setPlannedHeadCount(Long.parseLong(row[3].toString()));
			    p.setTotalAllocation(Double.parseDouble(row[4].toString()));
			    p.setDailyCost(Double.parseDouble(row[5].toString()));
			    projectList.add(p);
			}
		}
		return projectList;
	}

	@Transactional
	public List<Project> generatePdf(Date startDateParam, Date endDateParam, List<Project> projectList) {

		List<Object[]> rows;
		if(startDateParam != null && endDateParam != null){ // two dates entered
			rows = allocationRepository.generatePdf(startDateParam, endDateParam);
		}
		else{ // one date entered --starting date--
			rows = allocationRepository.generatePdf(startDateParam);
		}

		if(rows != null){
			projectList.clear();
			for (Object[] row: rows) {
			    Project p = new Project();
			    p.setProject_name(row[0].toString());
			    p.setMonth(row[1].toString());
			    p.setYear(row[2].toString());
			    p.setPlannedHeadCount(Long.parseLong(row[3].toString()));
			    p.setTotalAllocation(Double.parseDouble(row[4].toString()));
			    p.setDailyCost(Double.parseDouble(row[5].toString()));
			    projectList.add(p);
			}
		}
		return projectList;
	}

    @Transactional
    public List<Employee> viewExceededAllocation(List<Employee> employeeExceededList){
        employeeExceededList.clear();
        List<Object[]> rows = allocationRepository.viewExceededAllocation();
        if(rows != null){
            for (Object[] row: rows) {
                Employee e = new Employee();
                e.setFullName(row[0].toString());
                e.setTotalPercentAllocated(Double.parseDouble(row[1].toString()));
                employeeExceededList.add(e);
            }
        }
        return employeeExceededList;
    }
}
