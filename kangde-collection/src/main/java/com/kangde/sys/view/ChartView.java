package com.kangde.sys.view;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Paint;
import java.awt.RenderingHints;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.StandardChartTheme;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.StandardBarPainter;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.GradientPaintTransformType;
import org.jfree.ui.RectangleInsets;
import org.jfree.ui.StandardGradientPaintTransformer;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.AbstractView;

/**
 * 自定义视图
 * @author zhangyj
 *
 */
@Component
public class ChartView extends AbstractView{

	@Override
	protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		CategoryDataset paramCategoryDataset = createDataset(model);
		JFreeChart chart = createChart(paramCategoryDataset);
		
		ChartUtilities.writeChartAsJPEG(response.getOutputStream(), chart, 450, 270);
	}
	
	 private CategoryDataset createDataset(Map<String,Object> map) {
	    DefaultCategoryDataset localDefaultCategoryDataset = new DefaultCategoryDataset();
	    /*localDefaultCategoryDataset.addValue(410.0D, "Network Traffic", "Monday");*/
	    
	    for (Map.Entry<String, Object> entry : map.entrySet()) {
	    	localDefaultCategoryDataset.addValue((Long)entry.getValue(), "", entry.getKey());
		}
	    
	    return localDefaultCategoryDataset;
	  }

	 private JFreeChart createChart(CategoryDataset paramCategoryDataset) {
	    JFreeChart localJFreeChart = ChartFactory.createBarChart(" The receivable In the past three months!", null, "", paramCategoryDataset, PlotOrientation.VERTICAL, false, true, false);
	    TextTitle localTextTitle = localJFreeChart.getTitle();
	    localTextTitle.setBorder(0.0D, 0.0D, 1.0D, 0.0D);
	    localTextTitle.setBackgroundPaint(new GradientPaint(0.0F, 0.0F, Color.gray, 350.0F, 0.0F, Color.white, true));
	    localTextTitle.setExpandToFitSpace(true);
	    localJFreeChart.setBackgroundPaint(new GradientPaint(0.0F, 0.0F, Color.white, 350.0F, 0.0F, Color.white, true));
	    CategoryPlot localCategoryPlot = (CategoryPlot)localJFreeChart.getPlot();
	    localCategoryPlot.setNoDataMessage("无有效数据!");
	    localCategoryPlot.setBackgroundPaint(null);
	    localCategoryPlot.setInsets(new RectangleInsets(10.0D, 5.0D, 5.0D, 5.0D));
	    localCategoryPlot.setOutlinePaint(Color.white);
	    // 设置横虚线可见  
	    localCategoryPlot.setRangeGridlinesVisible(false);  
        // 虚线色彩  
	    localCategoryPlot.setRangeGridlinePaint(Color.gray);
	    localCategoryPlot.setRangeGridlineStroke(new BasicStroke(1.0F));
	    Paint[] arrayOfPaint = createPaint();
	    CustomBarRenderer localCustomBarRenderer = new CustomBarRenderer(arrayOfPaint);
	    localCustomBarRenderer.setBarPainter(new StandardBarPainter());
	    localCustomBarRenderer.setDrawBarOutline(true);
	    localCustomBarRenderer.setGradientPaintTransformer(new StandardGradientPaintTransformer(GradientPaintTransformType.CENTER_HORIZONTAL));
	    
	    // 设置柱子宽度  
	    localCustomBarRenderer.setMaximumBarWidth(0.15);  
        // 设置柱子高度  
	    localCustomBarRenderer.setMinimumBarLength(0.2);  
        // 设置柱子边框颜色  
	    localCustomBarRenderer.setBaseOutlinePaint(Color.BLACK);  
        // 设置柱子边框可见  
	    localCustomBarRenderer.setDrawBarOutline(true);  
        // // 设置柱的颜色  
        //renderer.setSeriesPaint(0, Color.decode("#8BBA00"));  
        // 设置每个地区所包含的平行柱的之间距离  
	    localCustomBarRenderer.setItemMargin(0.5);  
        localJFreeChart.getRenderingHints().put(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_OFF);  
        // 显示每个柱的数值，并修改该数值的字体属性  
        localCustomBarRenderer.setIncludeBaseInRange(true);  
        localCustomBarRenderer.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator());  
        localCustomBarRenderer.setBaseItemLabelsVisible(true);  
	    
	    localCategoryPlot.setRenderer(localCustomBarRenderer);
	    
	    NumberAxis localNumberAxis = (NumberAxis)localCategoryPlot.getRangeAxis();
	    localNumberAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
	    localNumberAxis.setRange(0.0D, 5000.0D);//设置范围
	    localNumberAxis.setTickMarkPaint(Color.black);
	    
	    //创建主题样式  
	    StandardChartTheme standardChartTheme=new StandardChartTheme("CN");  
	    //设置标题字体  
	    standardChartTheme.setExtraLargeFont(new Font("楷书",Font.BOLD,20));  
	    //设置图例的字体  
	    standardChartTheme.setRegularFont(new Font("楷书",Font.PLAIN,15));  
	    //设置轴向的字体  
	    standardChartTheme.setLargeFont(new Font("楷书",Font.PLAIN,15));  
	    //应用主题样式  
	    ChartFactory.setChartTheme(standardChartTheme);
	    
       /* // 设置柱子宽度  
	    localCustomBarRenderer.setMaximumBarWidth(0.15);  
        // 设置柱子高度  
	    localCustomBarRenderer.setMinimumBarLength(0.2);  
        // 设置柱子边框颜色  
	    localCustomBarRenderer.setBaseOutlinePaint(Color.BLACK);  
        // 设置柱子边框可见  
	    localCustomBarRenderer.setDrawBarOutline(true);  
        // // 设置柱的颜色  
        //renderer.setSeriesPaint(0, Color.decode("#8BBA00"));  
        // 设置每个地区所包含的平行柱的之间距离  
	    localCustomBarRenderer.setItemMargin(0.5);  */
	    
        /*localJFreeChart.getRenderingHints().put(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_OFF);  
        // 显示每个柱的数值，并修改该数值的字体属性  
        renderer.setIncludeBaseInRange(true);  
        renderer.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator());  
        renderer.setBaseItemLabelsVisible(true); */ 
	    
        //localCategoryPlot.setRenderer(localCustomBarRenderer);  
	    
        // 设置柱的透明度  
        //localCategoryPlot.setForegroundAlpha(1.0f);  
  
        // 背景色 透明度  
        //localCategoryPlot.setBackgroundAlpha(0.5f);  
	    
	    return localJFreeChart;
	}

	 private static Paint[] createPaint() {
	    Paint[] arrayOfPaint = new Paint[5];
	    arrayOfPaint[0] = new GradientPaint(0.0F, 0.0F, Color.gray, 0.0F, 0.0F, Color.white);
	    arrayOfPaint[1] = new GradientPaint(0.0F, 0.0F, Color.gray, 0.0F, 0.0F, Color.white);
	    arrayOfPaint[2] = new GradientPaint(0.0F, 0.0F, Color.gray, 0.0F, 0.0F, Color.white);
	    arrayOfPaint[3] = new GradientPaint(0.0F, 0.0F, Color.orange, 0.0F, 0.0F, Color.white);
	    arrayOfPaint[4] = new GradientPaint(0.0F, 0.0F, Color.magenta, 0.0F, 0.0F, Color.white);
	    return arrayOfPaint;
	 }
	  
	 class CustomBarRenderer extends BarRenderer {
		private static final long serialVersionUID = -3747618150032111606L;
		private Paint[] colors;
	    public CustomBarRenderer(Paint[] paramArrayOfPaint) {
	      this.colors = paramArrayOfPaint;
	    }

	    public Paint getItemPaint(int paramInt1, int paramInt2) {
	      return this.colors[(paramInt2 % this.colors.length)];
	    }
	  }
	 /* private static CategoryDataset createDataset()
	  {
	    DefaultCategoryDataset localDefaultCategoryDataset = new DefaultCategoryDataset();
	    localDefaultCategoryDataset.addValue(25.0D, "Series 1", "Category 1");
	    localDefaultCategoryDataset.addValue(34.0D, "Series 1", "Category 2");
	    localDefaultCategoryDataset.addValue(19.0D, "Series 2", "Category 1");
	    localDefaultCategoryDataset.addValue(29.0D, "Series 2", "Category 2");
	    localDefaultCategoryDataset.addValue(41.0D, "Series 3", "Category 1");
	    localDefaultCategoryDataset.addValue(33.0D, "Series 3", "Category 2");
	    return localDefaultCategoryDataset;
	  }

	  private static JFreeChart createChart(CategoryDataset paramCategoryDataset)
	  {
	    JFreeChart localJFreeChart = ChartFactory.createBarChart3D("3D Bar Chart Demo", "Category", "Value", paramCategoryDataset, PlotOrientation.VERTICAL, true, true, false);
	    CategoryPlot localCategoryPlot = (CategoryPlot)localJFreeChart.getPlot();
	    CategoryAxis localCategoryAxis = localCategoryPlot.getDomainAxis();
	    localCategoryAxis.setCategoryLabelPositions(CategoryLabelPositions.createUpRotationLabelPositions(0.3926990816987241D));
	    CategoryItemRenderer localCategoryItemRenderer = localCategoryPlot.getRenderer();
	    localCategoryItemRenderer.setBaseItemLabelsVisible(true);
	    BarRenderer localBarRenderer = (BarRenderer)localCategoryItemRenderer;
	    localBarRenderer.setItemMargin(0.2D);
	    return localJFreeChart;
	  }*/
}
