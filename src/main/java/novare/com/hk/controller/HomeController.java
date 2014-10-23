package novare.com.hk.controller;

import novare.com.hk.model.Allocation;
import novare.com.hk.model.Employee;
import novare.com.hk.model.Project;
import novare.com.hk.services.AllocationService;
import novare.com.hk.services.EmployeeService;
import novare.com.hk.services.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class HomeController {

    @Autowired
    ProjectService projectService;

    @Autowired
    AllocationService allocationService;

    @Autowired
    EmployeeService employeeService;

    List<Employee> employeeExceededList;
    List<Project> projectList;
    List<Project> projectListAlloc;

    @RequestMapping(value = "/home")
    public ModelAndView indexHandler(){
        System.out.println("!!index here!!");
        projectList = projectService.getProjectList();
        projectList = projectService.indexProjectCost(projectList);

        projectListAlloc = projectService.getProjectList();
        projectListAlloc = projectService.indexProjectAlloc(projectListAlloc);

        employeeExceededList = employeeService.getEmployeeList();
        employeeExceededList = allocationService.viewExceededAllocation(employeeExceededList);

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("projectList", projectList);
        map.put("projectListAlloc", projectListAlloc);
        map.put("employeeExceededList", employeeExceededList);

        return new ModelAndView("index", "map", map);
    }
}
