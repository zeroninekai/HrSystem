package novare.com.hk.services;

import java.util.List;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.block.BlockBorder;
import org.jfree.chart.block.BlockContainer;
import org.jfree.chart.block.BorderArrangement;
import org.jfree.chart.block.EmptyBlock;
import org.jfree.chart.labels.StandardCategoryToolTipGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.DatasetRenderingOrder;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.CategoryItemRenderer;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.chart.title.CompositeTitle;
import org.jfree.chart.title.LegendTitle;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.RectangleEdge;
import org.jfree.ui.RectangleInsets;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import novare.com.hk.model.Project;
import novare.com.hk.repository.ProjectRepository;

@Service("projectService")
public class ProjectServiceImpl implements ProjectService {

	@Autowired
	private ProjectRepository projectRepository;

	@Transactional
	public void insertData(Project project) {
		projectRepository.save(project);
	}

	@Transactional
	public List<Project> getProjectList() {
		return projectRepository.findAll();
	}

	@Transactional
	public void updateData(Project project) {
		projectRepository.save(project);
	}

	@Transactional
	public void deleteData(int id) {
		projectRepository.delete(id);
	}

	@Transactional
	public Project getProject(int id) {
		return projectRepository.findOne(id);
	}

	@Transactional
	public List<Project> searchProject(String searchquery){
		return projectRepository.searchProject(searchquery);
	}

	@Transactional
	public List<Project> filterProject(String project_name){
		return projectRepository.filterProject(project_name);
	}

    @Transactional
    public List<Project> indexProjectCost(List<Project> projectList) {
        projectList.clear();
        List<Object[]> rows = projectRepository.viewProjectsCost();
        if(rows != null){
            for(Object[] row: rows){
                Project p = new Project();
                p.setProject_name(row[0].toString());
                p.setDailyCost(Double.parseDouble(row[1].toString()));
                projectList.add(p);
            }
        }
        return projectList;
    }

    @Override
    public List<Project> indexProjectAlloc(List<Project> projectList) {
        projectList.clear();
        List<Object[]> rows = projectRepository.viewProjectsAllocation();
        if(rows != null){
            for(Object[] row: rows){
                Project p = new Project();
                p.setProject_name(row[0].toString());
                p.setTotalAllocation(Double.parseDouble(row[1].toString()));
                projectList.add(p);
            }
        }
        return projectList;
    }

    @Override
    public JFreeChart indexChart(List<Project> projectList) {
        projectList.clear();
        List<Object[]> rows = projectRepository.viewProjectsCost();
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        if(rows != null){
            for(Object[] row: rows){
                Project p = new Project();
                p.setProject_name(row[0].toString());
                p.setDailyCost(Double.parseDouble(row[1].toString()));
                projectList.add(p);
            }
        }

/*        for(Project p : projectList){
            dataset.addValue(p.getDailyCost(), p.getProject_name(), "Projects");
        }
        *//* test *//*
        CategoryItemRenderer renderer = new BarRenderer();
        CategoryPlot plot = new CategoryPlot();
        plot.setDataset(dataset);
        plot.setRenderer(renderer);
        plot.setDomainAxis(new CategoryAxis("Projects"));
        plot.setRangeAxis(new NumberAxis("Value"));

        plot.setOrientation(PlotOrientation.VERTICAL);
        plot.setRangeGridlinesVisible(true);
        plot.setDomainGridlinesVisible(true);

        JFreeChart chart = ChartFactory.createBarChart(
                "Project Cost with Allocations",
                "",            //x-axis
                "Cost",          //top left side or y-axis left
                dataset,         //data
                PlotOrientation.VERTICAL,//orientation
                true,           //include legend
                true,           //tooltips
                false           //URLs
        );
        return chart;*/
        return null;
    }

    private CategoryDataset createDataSetCostProjects(List<Project> projectList){
        DefaultCategoryDataset datasetForCostProjects = new DefaultCategoryDataset();
        for(Project p : projectList){
            datasetForCostProjects.addValue(p.getDailyCost(), p.getProject_name(), "Projects");
        }
        return datasetForCostProjects;
    }

    private CategoryDataset createDataSetAllocProjects(List<Project> projectAllocList){
        DefaultCategoryDataset datasetForAllocProjects = new DefaultCategoryDataset();
        for(Project p : projectAllocList){
            datasetForAllocProjects.addValue(p.getTotalAllocation(), "Allocations", p.getProject_name());
        }
        return datasetForAllocProjects;
    }

    public JFreeChart createChart(List<Project> projectList, List<Project> projectAllocList){
        JFreeChart jFreeChart = ChartFactory.createBarChart(
                "Overview",
                "Projects",
                "Cost",
                createDataSetCostProjects(projectList),
                PlotOrientation.VERTICAL,
                false,
                true,
                false);
        CategoryPlot categoryplot = (CategoryPlot)jFreeChart.getPlot();
        CategoryDataset datasetForAllocProjects = createDataSetAllocProjects(projectAllocList);

        categoryplot.setDataset(1, datasetForAllocProjects);
        categoryplot.mapDatasetToRangeAxis(1, 1);

        CategoryAxis categoryaxis = categoryplot.getDomainAxis();
        //categoryaxis.setCategoryLabelPositions(CategoryLabelPositions.DOWN_45);

        NumberAxis numberaxis = new NumberAxis("Allocations");
        categoryplot.setRangeAxis(1, numberaxis);

        LineAndShapeRenderer lineandshaperenderer = new LineAndShapeRenderer();
        lineandshaperenderer.setBaseToolTipGenerator(new StandardCategoryToolTipGenerator());
        categoryplot.setRenderer(1, lineandshaperenderer);
        categoryplot.setDatasetRenderingOrder(DatasetRenderingOrder.FORWARD);
        categoryplot.getDomainAxis(0).setVisible(false); // set label of x-axis (Projects)

        LegendTitle legendtitle = new LegendTitle(categoryplot.getRenderer(0));
        legendtitle.setMargin(new RectangleInsets(2D, 2D, 2D, 2D));
        legendtitle.setFrame(new BlockBorder());

        LegendTitle legendtitle1 = new LegendTitle(categoryplot.getRenderer(1));
        legendtitle1.setMargin(new RectangleInsets(2D, 2D, 2D, 2D));
        legendtitle1.setFrame(new BlockBorder());

        BlockContainer blockcontainer = new BlockContainer(new BorderArrangement());
        blockcontainer.add(legendtitle, RectangleEdge.LEFT);
        blockcontainer.add(legendtitle1, RectangleEdge.RIGHT);
        blockcontainer.add(new EmptyBlock(2000D, 0.0D));

        CompositeTitle compositetitle = new CompositeTitle(blockcontainer);
        compositetitle.setPosition(RectangleEdge.BOTTOM);
        jFreeChart.addSubtitle(compositetitle);
        ChartUtilities.applyCurrentTheme(jFreeChart);
        return jFreeChart;
    }
}
