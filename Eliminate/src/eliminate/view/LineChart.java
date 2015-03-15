/*
 * 
 */
package eliminate.view;

import java.awt.Font;
import java.awt.FontFormatException;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.time.Day;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.XYDataset;

// TODO: Auto-generated Javadoc
/**
 * The Class LineChart.
 */
public class LineChart {

	/** The frame. */
	private ChartPanel frame;

	/** The font path. */
	private String fontPath = "media/font/font.ttf";

	/** The content. */
	private int[][] content;

	/** The title. */
	private String title;

	/** The property. */
	private String property;

	/** The font. */
	private Font font;

	// data[][4]分别为年，月，日，分数/局数,type=0为每日局数，1为每日平均得分
	/**
	 * Instantiates a new line chart.
	 * 
	 * @param data
	 *            the data
	 * @param type
	 *            the type
	 */
	public LineChart(int[][] data, int type) {
		content = data;
		if (type == 0) {
			title = "每日游戏局数";
			property = "局数";
		} else {
			title = "每日平均得分";
			property = "平均得分";
		}

		XYDataset xydataset = createDataset();
		JFreeChart jfreechart = ChartFactory.createTimeSeriesChart(title, "日期",
				property, xydataset, true, true, true);

		XYPlot xyplot = (XYPlot) jfreechart.getPlot();
		DateAxis dateaxis = (DateAxis) xyplot.getDomainAxis();
		dateaxis.setDateFormatOverride(new SimpleDateFormat("yyyy-MM-dd"));

		frame = new ChartPanel(jfreechart, true);
		dateaxis.setLabelFont(initFont(14f)); // 水平底部标题
		dateaxis.setTickLabelFont(initFont(12f)); // 垂直标题
		ValueAxis rangeAxis = xyplot.getRangeAxis();// 获取柱状

		rangeAxis.setLabelFont(initFont(15f));
		jfreechart.getLegend().setItemFont(initFont(15f));

		jfreechart.getTitle().setFont(initFont(20f));// 设置标题字体

	}

	/**
	 * Creates the dataset.
	 * 
	 * @return the XY dataset
	 */
	private XYDataset createDataset() {
		@SuppressWarnings("deprecation")
		TimeSeries timeseries = new TimeSeries(title, Day.class);
		int length = content.length;
		for (int i = 0; i < length; i++) {
			timeseries.add(
					new Day(content[i][2], content[i][1], content[i][0]),
					content[i][3]);
		}
		TimeSeriesCollection timeseriescollection = new TimeSeriesCollection();
		timeseriescollection.addSeries(timeseries);
		return timeseriescollection;
	}

	/**
	 * Gets the chart panel.
	 * 
	 * @return the chart panel
	 */
	public ChartPanel getChartPanel() {
		return frame;
	}

	/**
	 * Inits the font.
	 * 
	 * @param size
	 *            the size
	 * @return the font
	 */
	private Font initFont(float size) {
		font = null;
		try {
			font = (Font.createFont(Font.TRUETYPE_FONT, new File(fontPath))
					.deriveFont(size));
		} catch (FontFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return font;
	}
}
