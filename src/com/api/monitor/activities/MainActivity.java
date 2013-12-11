package com.api.monitor.activities;

import android.os.Bundle;
import android.support.v4.view.ViewPager;

import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.api.monitor.R;
import com.api.monitor.adapters.MainPagerAdapter;
import com.viewpagerindicator.TitlePageIndicator;

public class MainActivity extends SherlockFragmentActivity {
	private TitlePageIndicator indicator;
    private ViewPager pager;
    
	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		setContentView(R.layout.activity_main);
		setupViews();
	}

	private void setupViews() {
		indicator = (TitlePageIndicator) findViewById(R.id.tpi_header);
		pager = (ViewPager) findViewById(R.id.vp_pages);
		pager.setAdapter(new MainPagerAdapter(getResources(), getSupportFragmentManager()));
		indicator.setViewPager(pager);
	}
}
