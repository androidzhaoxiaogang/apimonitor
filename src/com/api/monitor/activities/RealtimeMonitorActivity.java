package com.api.monitor.activities;

import java.util.HashMap;

import org.achartengine.ChartFactory;
import org.achartengine.GraphicalView;
import org.achartengine.chart.PointStyle;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.model.XYSeries;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.graphics.Paint.Align;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.ViewGroup.LayoutParams;
import android.widget.RelativeLayout;

import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.Menu;
import com.api.monitor.R;
import com.api.monitor.pojo.UserInfo;
import com.api.monitor.utils.Constants;

import fast.rocket.Rocket;
import fast.rocket.config.JsonCallback;
import fast.rocket.error.RocketError;

public class RealtimeMonitorActivity extends SherlockActivity {
	private final static int MSG_UPDATE = 0x01;
	private final static int ITEM_COUNT = 18;
	private final static long PERIOD = 4 << 10;
	
	private static final String TAG = "RealtimeMonitorActivity";

	private int[] mXvalue = new int[100];
	private double[] mYvalue = new double[100];
	private int mAddX = 0;
	private double mAddY = 0.000;

	private boolean isFirstTime = false;
	private boolean isPagePaused = false;
	private String mTitle;
	private XYMultipleSeriesRenderer mRenderer;
	private XYMultipleSeriesDataset mDataset;
	private GraphicalView mChart;
	private XYSeries mSeries;

	private Handler mHandler = new Handler() {
		public void handleMessage(Message msg) {
			if (msg.what == MSG_UPDATE) {
				if (isFirstTime) {
					updateChart();
				} else {
					updateFirstChart();
					isFirstTime = true;
				}

				fetchRealValue();
			}
		}
	};

	private RelativeLayout realtimeLayout;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_realtime_monitor);
		setupViews();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		return super.onCreateOptionsMenu(menu);
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		if(isPagePaused && mHandler!=null) {
			mHandler.sendEmptyMessageDelayed(MSG_UPDATE, PERIOD);
			isPagePaused = false;
		}
	}

	@Override
	protected void onPause() {
		super.onPause();
		isPagePaused = true;
		if(mHandler!=null) {
			mHandler.removeMessages(MSG_UPDATE);
		}
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
	}

	private void setupViews() {
		realtimeLayout = (RelativeLayout) findViewById(R.id.layout_realtime);
	}

	@SuppressLint("HandlerLeak")
	private void setupChart() {
		mTitle = "��½";
		mSeries = new XYSeries(mTitle);
		mDataset = new XYMultipleSeriesDataset();
		mDataset.addSeries(mSeries);

		int color = Color.parseColor("#30A7FC");
		PointStyle style = PointStyle.CIRCLE;
		mRenderer = buildRenderer(color, style, true);

		setupRender(mRenderer, "X", "Y", 0, 20, mAddY, mAddY + 20.0,
				Color.BLACK, Color.BLACK);

		mChart = ChartFactory.getLineChartView(this, mDataset, mRenderer);
		realtimeLayout.addView(mChart, new LayoutParams(
				LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));

		fetchRealValue();
	}

	private XYMultipleSeriesRenderer buildRenderer(int color, PointStyle style,
			boolean fill) {
		XYMultipleSeriesRenderer renderer = new XYMultipleSeriesRenderer();

		XYSeriesRenderer r = new XYSeriesRenderer();
		r.setPointStyle(style);
		r.setFillPoints(fill);
		r.setLineWidth(3);
		r.setColor(color);
		renderer.addSeriesRenderer(r);

		return renderer;
	}

	@SuppressWarnings("deprecation")
	protected void setupRender(XYMultipleSeriesRenderer renderer,
			String xTitle, String yTitle, double xMin, double xMax,
			double yMin, double yMax, int axesColor, int labelsColor) {
		renderer.setChartTitle(mTitle);
		renderer.setXTitle(xTitle);
		renderer.setYTitle(yTitle);
		renderer.setXAxisMin(xMin);
		renderer.setXAxisMax(xMax);
		renderer.setYAxisMin(yMin);
		renderer.setYAxisMax(yMax);
		renderer.setAxesColor(axesColor);
		renderer.setLabelsColor(labelsColor);
		renderer.setShowGrid(true);
		renderer.setGridColor(Color.rgb(105, 105, 105));
		renderer.setXLabels(20);
		renderer.setYLabels(20);
		renderer.setXTitle("ʱ��/����");
		renderer.setYTitle("ppb");
		renderer.setZoomEnabled(false);
		renderer.setExternalZoomEnabled(false);
		renderer.setYLabelsAlign(Align.RIGHT);
		renderer.setPointSize((float) 2);
		renderer.setShowLegend(false);
		renderer.setDisplayChartValues(true);
		renderer.setApplyBackgroundColor(true);
		renderer.setBackgroundColor(Color.parseColor("#B2D531"));
		renderer.setMarginsColor(Color.parseColor("#B6CFE5"));
	}

	private void updateChart() {
		mDataset.removeSeries(mSeries);

		int length = mSeries.getItemCount();
		if (length >= ITEM_COUNT) {
			length = ITEM_COUNT;
			for (int i = 0; i < length - 1; i++) {
				mXvalue[i] = i;
				mYvalue[i] = mSeries.getY(i + 1);
			}
			mSeries.clear();

			for (int k = 0; k < length - 1; k++) {
				mSeries.add(mXvalue[k], mYvalue[k]);
			}

			mSeries.add(length - 1, mAddY);
		} else {
			for (int i = 0; i < length; i++) {
				mXvalue[i] = (int) mSeries.getX(i);
				mYvalue[i] = mSeries.getY(i);
			}
			mSeries.clear();

			for (int k = 0; k < length; k++) {
				mSeries.add(mXvalue[k], mYvalue[k]);
			}
			mSeries.add(mAddX++, mAddY);
		}

		mDataset.addSeries(mSeries);
		mChart.invalidate();
	}

	private void updateFirstChart() {
		mDataset.removeSeries(mSeries);
		mSeries.clear();
		mSeries.add(mAddX++, mAddY);
		mDataset.addSeries(mSeries);
		mChart.invalidate();
	}

	private void fetchRealValue() {
		final HashMap<String, String> params = new HashMap<String, String>();
		params.put("method", "onLogin");
		params.put("account", "csyw");
		params.put("passWord", "111111");

		Rocket.with(this).enableCookie(true)
				.requestParams(params)
				.requestTag(TAG)
				.targetType(UserInfo.class)
				.invoke(callback)
				.load(Constants.loginInfoUrl);
	}
	
	private JsonCallback<UserInfo> callback = new JsonCallback<UserInfo>() {

		@Override
		public void onCompleted(RocketError error, UserInfo result) {
			if (error == null) {
				
			} else {
				
			}
			
			if (!isPagePaused) {
				if(isFirstTime) {
					mHandler.sendEmptyMessageDelayed(MSG_UPDATE, PERIOD);
				} else {
					mHandler.sendEmptyMessage(MSG_UPDATE);
				}
			}
		}
	};
}
