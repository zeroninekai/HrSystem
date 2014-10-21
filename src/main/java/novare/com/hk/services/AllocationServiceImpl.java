package novare.com.hk.services;

import java.util.Date;
import java.util.List;

import novare.com.hk.model.Allocation;
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
        // If inputted allocation percent value is > 100 or < 0
        if (allocation.getPercent() > 100 || allocation.getPercent() < 0) {
            throw new Exception("Invalid percentage");
        }
        // checks if the user has already another allocation
        // if there is, it checks if the value inputted by the user plus the
        // employee's existing value for allocation exceeds 100; which should not be
        else {
            List<Allocation> allocationStorage = allocationRepository.findAll();
            int totalPercent = 0;
            for (Allocation a : allocationStorage) {
                // checks if the employee the user is trying to add has an existing entry in allocations db
                if (a.getEmployee().getId() == allocation.getEmployee().getId()) { //matches or check if allocation's emp ID stored from database is equal to user input allocation emp id which was binded to a dropbox as a name
                    totalPercent += a.getPercent();
                }
            } // end for-loop
            totalPercent += allocation.getPercent();
            if (totalPercent > 100) {
                System.out.println("Cannot add employee if more than allocated 100 percent!!!!");
                throw new Exception("Cannot insert if allocation is more than 100 percent!");
            }
            // if no exceptions, below code will execute
            System.out.println("Saved! :" + totalPercent);
            allocationRepository.save(allocation);
        }
    }

	@Transactional
	public List<Allocation> getAllocationList() {
		return allocationRepository.findAll();
	}

	@Transactional
	public void updateData(Allocation allocation) throws Exception {
        // If inputted allocation percent value is > 100 or < 0
        if (allocation.getPercent() > 100 || allocation.getPercent() < 0) {
            throw new Exception("Invalid percentage");
        }
        // checks if the user has already another allocation
        // if there is, it checks if the value inputted by the user plus the
        // employee's existing value for allocation exceeds 100; which should not be
        else {
            List<Allocation> allocationStorage = allocationRepository.findAll();
            int totalPercent = 0;
            int initialValue = 0;
            for (Allocation a : allocationStorage) {
                // matches or check if allocation's emp ID stored from database is equal to user selected allocation emp id
                // which was binded to employee name; this is to see if employee exists/has an existing allocation
                // if there is an existing allocation for the employee, it sums up all the allocation of employee to see if it is valid or <= 100.
                if (a.getEmployee().getId() == allocation.getEmployee().getId()) {
                    // if the allocation.project id from database matches the allocation.project id the user is currently editing
                    // it should not add the total of the percent he is currently editing because he will update its value
                    if(a.getProject().getId() == allocation.getProject().getId()){
                        initialValue = a.getPercent();
                    } else {
                        System.out.println("Total Percent: " + totalPercent +"+"+ a.getPercent());
                        totalPercent += a.getPercent();
                    }
                } // end outer-if
            } // end for-loop
            totalPercent += allocation.getPercent();
            System.out.println("Total Percent Final: " + (totalPercent));
            if (totalPercent > 100) {
                throw new Exception("Cannot update employee if more than allocated 100 percent!!!!");
            }
            // if no exceptions, below code will execute
            System.out.println("!!----Saved----!!");
            allocationRepository.save(allocation);
        }
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
}
