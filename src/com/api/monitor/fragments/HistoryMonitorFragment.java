package com.api.monitor.fragments;

import com.api.monitor.adapters.ExpandAdapter;


public class HistoryMonitorFragment extends RealtimeMonitorFragment {

	@Override
	public void setAdapter() {
		mAdapter = new ExpandAdapter(getActivity(), groupData, childrenData, TYPE_HISTORY);
	}
}
