package controller;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JPanel;

import login.OnlineUser;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.StandardChartTheme;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.time.Millisecond;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;

// TODO: Auto-generated Javadoc
/**
 * The Class TimeSeriesChart.
 */
public class TimeSeriesChart implements Runnable {

	/** The chart. */
	private JFreeChart chart;

	/** The time series. */
	private static TimeSeries timeSeries;


	/** The thread. */
	Thread thread;

	/**
	 * Instantiates a new time series chart.
	 * 
	 * @param title
	 *            the title
	 * @param dataType
	 *            the data type
	 * @param YData
	 *            the y data
	 * @param isOK
	 *            the is ok
	 */
	@SuppressWarnings("deprecation")
	public TimeSeriesChart(String title, String dataType, String YData,
			boolean isOK) {
		// chartTitle为图表标题,dataType为所统计的数据类型,YDate为Y轴数据类型

		setStyle();

		timeSeries = new TimeSeries(dataType, Millisecond.class);
		TimeSeriesCollection timeseriescollection = new TimeSeriesCollection(
				timeSeries);
		// timeseriescollection.addSeries(cpu2);添加第二个折线

		chart = ChartFactory.createTimeSeriesChart(title, "时间(秒)", YData,
				timeseriescollection, true, true, false);

		XYPlot xyplot = chart.getXYPlot();
		xyplot.setOutlinePaint(Color.CYAN);// 设置数据区的边界线条颜色
		xyplot.setBackgroundPaint(null); // 设置图表背景颜色
		xyplot.setDomainGridlinePaint(Color.BLUE); // 设置横向网格线蓝色
		xyplot.setDomainGridlinesVisible(true); // 设置显示横向网格线
		xyplot.setRangeGridlinePaint(Color.BLUE); // 设置纵向网格线蓝色
		xyplot.setRangeGridlinesVisible(true); // 设置显示纵向网格线
		// 纵坐标设定
		ValueAxis valueaxis = xyplot.getDomainAxis();
		// 自动设置数据轴数据范围
		valueaxis.setAutoRange(true);
		// 数据轴固定数据范围 30s
		valueaxis.setFixedAutoRange(30000D);

		valueaxis = xyplot.getRangeAxis();
	}

	// 图表数据获取途径
	/**
	 * Gets the on line num.
	 * 
	 * @return the on line num
	 */
	private int getOnLineNum() {
		return OnlineUser.getMap().size();
	}

	// 返回界面
	/**
	 * Gets the ui.
	 * 
	 * @return the ui
	 */
	public JPanel getUI() {
		return new ChartPanel(chart);
	}

	// 线程
	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (thread != null) {
			try {
				timeSeries.add(new Millisecond(), getOnLineNum());
				Thread.sleep(300);
			} catch (InterruptedException e) {

			}
		}
	}

	// 设置样式
	/**
	 * Sets the style.
	 */
	private void setStyle() {
		// 创建时序图对象
		// 创建主题样式
		StandardChartTheme standardChartTheme = new StandardChartTheme("CN");
		// 设置标题字体
		standardChartTheme.setExtraLargeFont(new Font("隶书", Font.BOLD, 20));
		// 设置图例的字体
		standardChartTheme.setRegularFont(new Font("宋书", Font.PLAIN, 15));
		// 设置轴向的字体
		standardChartTheme.setLargeFont(new Font("宋书", Font.PLAIN, 15));
		// 应用主题样式
		ChartFactory.setChartTheme(standardChartTheme);
	}

	/**
	 * Start.
	 */
	public void start() {
		if (thread == null) {
			thread = new Thread(this);
		}
		thread.start();
	}

	/**
	 * Stop.
	 */
	public void stop() {
		thread = null;
	}

}
