

package com.api.monitor.adapters;

import com.api.monitor.R;
import com.api.monitor.fragments.ConfigurationFragment;
import com.api.monitor.fragments.HistoryMonitorFragment;
import com.api.monitor.fragments.RealtimeMonitorFragment;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;


/**
 * Pager adapter
 */
public class MainPagerAdapter extends FragmentPagerAdapter {

    private final Resources resources;

    /**
     * Create pager adapter
     *
     * @param resources
     * @param fragmentManager
     */
    public MainPagerAdapter(Resources resources, FragmentManager fragmentManager) {
        super(fragmentManager);
        this.resources = resources;
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public Fragment getItem(int position) {
        Bundle bundle = new Bundle();
        switch (position) {
        case 0:
        	RealtimeMonitorFragment realtimeFragment = new RealtimeMonitorFragment();
            realtimeFragment.setArguments(bundle);
            return realtimeFragment;
        case 1:
        	HistoryMonitorFragment historyFragment = new HistoryMonitorFragment();
            historyFragment.setArguments(bundle);
            return historyFragment;
        case 2:
        	ConfigurationFragment configFragment = new ConfigurationFragment();
            configFragment.setArguments(bundle);
            return configFragment;
        default:
            return null;
        }
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
        case 0:
            return resources.getString(R.string.pager_realtime);
        case 1:
            return resources.getString(R.string.pager_history);
        case 2:
            return resources.getString(R.string.pager_config);
        default:
            return null;
        }
    }
}
