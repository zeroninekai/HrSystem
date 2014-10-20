package novare.com.hk.controller;

import novare.com.hk.model.Project;
import novare.com.hk.services.AllocationService;
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
    AllocationService allocationService;

    @Autowired
    ProjectService projectService;

    List<Project> projectList;

    @RequestMapping(value = "/home")
    public ModelAndView indexHandler(){
        System.out.println("!!index here!!");
        projectList = projectService.getProjectList();
        projectList = allocationService.defaultAlloc(projectList);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("projectList", projectList);
        return new ModelAndView("index", "map", map);
    }
}
