package novare.com.hk.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import novare.com.hk.model.Allocation;
import novare.com.hk.model.Employee;
import novare.com.hk.model.Project;
import novare.com.hk.services.AllocationService;
import novare.com.hk.services.EmployeeService;
import novare.com.hk.services.ProjectService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AllocationController {
	@Autowired
	AllocationService allocationService;

	@Autowired
	EmployeeService employeeService;

	@Autowired
	ProjectService projectService;

	List<Project> projectList;
	
	@RequestMapping(value = "/addAllocation", method = RequestMethod.GET)
	public String addAllocation(@ModelAttribute Allocation allocation,
			Model model) {

		System.out.println("Viewing pre-adding page. . .");

		/******************** EMPLOYEES ****************************/
		Set<Map.Entry<String, Integer>> employees;
		List<Employee> employeesList = employeeService.getEmployeeList();
		final Map<String, Integer> employeesMap = new HashMap<String, Integer>();
		if (employeesList != null && !employeesList.isEmpty()) {
			for (Employee eachEmployee : employeesList) {
				if (eachEmployee != null) {
					employeesMap.put(eachEmployee.getFname() + " "
							+ eachEmployee.getLname(), eachEmployee.getId());
				}
			}
		}
		employees = employeesMap.entrySet();
		model.addAttribute("employees", employees);

		/***********************************************************/
		/******************** PROJECTS ****************************/
		Set<Map.Entry<String, Integer>> projects;
		List<Project> projectsList = projectService.getProjectList();
		final Map<String, Integer> projectsMap = new HashMap<String, Integer>();
		if (projectsList != null && !projectsList.isEmpty()) {
			for (Project eachProject : projectsList) {
				if (eachProject != null) {
					projectsMap.put(eachProject.getProject_name(),
							eachProject.getId());
				}
			}
		}
		projects = projectsMap.entrySet();
		model.addAttribute("projects", projects);

		/************************************************/
		return "addAllocation";
	}

	@RequestMapping("/viewAllocationList")
	public ModelAndView getAllocationList(@ModelAttribute Project project, @ModelAttribute Allocation allocation) {
		
		projectList = projectService.getProjectList();

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("names", arrangeDropdownProj() );
		map.put("allocationList", arrangeNames(allocationService.getAllocationList() ));

		return new ModelAndView("viewAllocationList", "map", map);
	}

	@RequestMapping("/editAllocation")
	public ModelAndView editAllocation(@RequestParam String id,
			@ModelAttribute Allocation allocation, Model model) {

		allocation = allocationService.getAllocation(Integer.parseInt(id));
		allocation.setEmployee_name(allocation.getEmployee().getFname() + " " + allocation.getEmployee().getLname());
		allocation.setProject_name(allocation.getProject().getProject_name());
				
			Set<Map.Entry<String, Integer>> projects;
			List<Project> projectsList = projectService.getProjectList();
			final Map<String, Integer> projectsMap = new HashMap<String, Integer>();
			if (projectsList != null && !projectsList.isEmpty()) {
				for (Project eachProject : projectsList) {
					if (eachProject != null) {
						projectsMap.put(eachProject.getProject_name(),eachProject.getId());
					}
				}
			}
			projects = projectsMap.entrySet();
			model.addAttribute("projects", projects);

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("allocation", allocation);
		map.put("names", arrangeDropdownProj());
		map.put("empID", allocation.getEmployee().getId());

		return new ModelAndView("editAllocation", "map", map);
	}

	@RequestMapping("/updateAllocation")
	public String updateAllocation(@ModelAttribute Allocation allocation) {
		try {
			if (allocation.getPercent() > 100 || allocation.getPercent() < 0) {
				throw new Exception("Invalid Percentage");
			} else {
				allocationService.updateData(allocation);
				return "redirect:/viewAllocationList";
			}
		} catch (Exception ex) {
			System.out.println("Invalid input. Error is: " + ex.getMessage());
			return "redirect:/errorPage";
		}
	}

	@RequestMapping("/deleteAllocation")
	public String deleteAllocation(@RequestParam String id) {
		System.out.println("id = " + id);
		allocationService.deleteData(Integer.parseInt(id));
		return "redirect:/viewAllocationList";
	}

	@RequestMapping("/insertAllocation")
	public String insertAllocation(@ModelAttribute Allocation allocation) {
		try {
			if (allocation.getPercent() > 100 || allocation.getPercent() < 0) {
				throw new Exception("Invalid percentage");
			}
			if (allocation != null) {
				allocationService.insertData(allocation);
			}
			return "redirect:/viewAllocationList";

		} catch (Exception ex) {
			System.out.println("Invalid input. Error is: " + ex.getMessage());
			return "redirect:/errorPage";
		}
	}

	@RequestMapping("/filterAlloc")
	public ModelAndView filterAllocationList(@RequestParam String project_name, @ModelAttribute Project project, @ModelAttribute Allocation allocation) {
		
		projectList = projectService.filterProject(project_name);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("names", arrangeDropdownProj() );
		map.put("allocationList", arrangeNames(allocationService.filterAllocation(project_name) ));

		return new ModelAndView("viewAllocationList", "map", map);
	}

	@RequestMapping("/searchAlloc")
	public ModelAndView searchAllocationList(@RequestParam String searchquery,
			@ModelAttribute Project project,@ModelAttribute Allocation allocation) {
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("names", arrangeDropdownProj());
		map.put("allocationList", arrangeNames(allocationService.searchAllocation(searchquery) ));

		return new ModelAndView("viewAllocationList", "map", map);
	}

	@RequestMapping(value = "/reportPDFAlloc", method = RequestMethod.GET)
	public ModelAndView jasperDownloadPdf(@RequestParam Date reportStartDate, @RequestParam Date reportEndDate, ModelAndView mv) {
		System.out.println("------------------Downloading PDF------------------");

/*		if(reportStartDate != null || reportEndDate != null){
			projectList = projectService.getReport(reportStartDate, reportEndDate);
			System.out.println("====\nreport \nservice \nproject \n====\n");
		}	
	
		
		for(Project p : projectList){
			System.out.print(p.getProject_name() + ": ");
			List<Allocation> allocationList = p.getAllocations();
			for(Allocation a : allocationList){
				if(reportEndDate != null){
					if((a.getStart_date().after(reportStartDate) || a.getStart_date().equals(reportStartDate)) && (a.getStart_date().before(reportEndDate) || a.getStart_date().equals(reportEndDate))){
						p.setPlannedHeadCount(p.getPlannedHeadCount()+1);
					    double percentage = (double)a.getPercent()/100;
						p.setTotalAllocation(p.getTotalAllocation()+percentage);
						p.setDailyCost(p.getDailyCost()+a.getEmployee().getCost()*percentage);
						System.out.println(a.getEmployee().getFname());
					}// end inner if
				} //end outer if
				else if(reportStartDate != null){
					if((a.getStart_date().after(reportStartDate) || a.getStart_date().equals(reportStartDate))){
						p.setPlannedHeadCount(p.getPlannedHeadCount()+1);
					    double percentage = (double)a.getPercent()/100;
						p.setTotalAllocation(p.getTotalAllocation()+percentage);
						p.setDailyCost(p.getDailyCost()+a.getEmployee().getCost()*percentage);
						System.out.println(a.getEmployee().getFname());
					}// end inner if
				} //end outer else
				else{
					p.setPlannedHeadCount(p.getPlannedHeadCount()+1);
				    double percentage = (double)a.getPercent()/100;
					p.setTotalAllocation(p.getTotalAllocation()+percentage);
					p.setDailyCost(p.getDailyCost()+a.getEmployee().getCost()*percentage);
					System.out.println(a.getEmployee().getFname());
				}
			} //end for inner for
		} //end outer for
*/
		projectList = projectService.gen(reportStartDate, reportEndDate);
		Map<String, Object> parameterMap = new HashMap<String, Object>();
		JRDataSource jrDataSource = new JRBeanCollectionDataSource(projectList, false);
		parameterMap.put("dataSource", jrDataSource);

		mv = new ModelAndView("pdfReportAlloc", parameterMap);
		return mv;
	}

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(
				dateFormat, true));
	}

	private List<Allocation> arrangeNames(List<Allocation> allocationList){
		/* set up employee name; employee.fname + employee.lname to transient field and project name */
		if(allocationList != null){
			for (Allocation a : allocationList) {
				a.setEmployee_name(a.getEmployee().getFname() + " " + a.getEmployee().getLname());
				a.setProject_name(a.getProject().getProject_name());
			}
		}
		return allocationList;
	}
	
	private List<String> arrangeDropdownProj(){
		/*	for project names under filter dropdown	 */
		List<Project> projectListDbox = projectService.getProjectList();
		List<String> proj_names = new ArrayList<String>();
		List<String> names = new ArrayList<String>();
		for (Project p : projectListDbox) {
			proj_names.add(p.getProject_name());
		}
		Set<String> se = new HashSet<String>(proj_names);
		proj_names.clear();
		proj_names = new ArrayList<String>(se);
		for (Object obj : proj_names) {
			names.add(obj.toString());
		}
		Collections.sort(names);
		
		return names;
	}
}
