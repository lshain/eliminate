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
		// chartTitleΪͼ�����,dataTypeΪ��ͳ�Ƶ���������,YDateΪY����������

		setStyle();

		timeSeries = new TimeSeries(dataType, Millisecond.class);
		TimeSeriesCollection timeseriescollection = new TimeSeriesCollection(
				timeSeries);
		// timeseriescollection.addSeries(cpu2);��ӵڶ�������

		chart = ChartFactory.createTimeSeriesChart(title, "ʱ��(��)", YData,
				timeseriescollection, true, true, false);

		XYPlot xyplot = chart.getXYPlot();
		xyplot.setOutlinePaint(Color.CYAN);// �����������ı߽�������ɫ
		xyplot.setBackgroundPaint(null); // ����ͼ������ɫ
		xyplot.setDomainGridlinePaint(Color.BLUE); // ���ú�����������ɫ
		xyplot.setDomainGridlinesVisible(true); // ������ʾ����������
		xyplot.setRangeGridlinePaint(Color.BLUE); // ����������������ɫ
		xyplot.setRangeGridlinesVisible(true); // ������ʾ����������
		// �������趨
		ValueAxis valueaxis = xyplot.getDomainAxis();
		// �Զ��������������ݷ�Χ
		valueaxis.setAutoRange(true);
		// ������̶����ݷ�Χ 30s
		valueaxis.setFixedAutoRange(30000D);

		valueaxis = xyplot.getRangeAxis();
	}

	// ͼ�����ݻ�ȡ;��
	/**
	 * Gets the on line num.
	 * 
	 * @return the on line num
	 */
	private int getOnLineNum() {
		return OnlineUser.getMap().size();
	}

	// ���ؽ���
	/**
	 * Gets the ui.
	 * 
	 * @return the ui
	 */
	public JPanel getUI() {
		return new ChartPanel(chart);
	}

	// �߳�
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

	// ������ʽ
	/**
	 * Sets the style.
	 */
	private void setStyle() {
		// ����ʱ��ͼ����
		// ����������ʽ
		StandardChartTheme standardChartTheme = new StandardChartTheme("CN");
		// ���ñ�������
		standardChartTheme.setExtraLargeFont(new Font("����", Font.BOLD, 20));
		// ����ͼ��������
		standardChartTheme.setRegularFont(new Font("����", Font.PLAIN, 15));
		// �������������
		standardChartTheme.setLargeFont(new Font("����", Font.PLAIN, 15));
		// Ӧ��������ʽ
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
