package com.api.monitor.fragments;

import java.util.ArrayList;
import java.util.List;

import com.api.monitor.R;
import com.api.monitor.adapters.ExpandAdapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;

public class RealtimeMonitorFragment extends Fragment {
	
	private List<String> groupData = new ArrayList<String>(); //一级目录标题列表
	private List<List<String>> childrenData = new ArrayList<List<String>>(); //二级目录标题列表

	private BaseExpandableListAdapter mAdapter; //可扩展的列表适配器

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		init();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_realtime, container,
				false);
		ExpandableListView listView = (ExpandableListView) rootView.findViewById(R.id.expand_listView);
		
		mAdapter = new ExpandAdapter(getActivity(), groupData, childrenData);
		listView.setAdapter(mAdapter);
		return rootView;
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
	}

	@Override
	public void onDestroyView() {
		super.onDestroyView();
	}
	
	private void init() {
		groupData.add("用户模块");
		ArrayList<String> userList = new ArrayList<String>();
		userList.add("用户注册");
		userList.add("用户登陆");
		userList.add("用户退出");
		childrenData.add(userList);
		
		groupData.add("计费模块");
		ArrayList<String> billList = new ArrayList<String>();
		billList.add("费用查询");
		billList.add("费用支付");
		billList.add("费用取消");
		childrenData.add(billList);
		
		groupData.add("评论模块");
		ArrayList<String> commentList = new ArrayList<String>();
		commentList.add("列表评论");
		commentList.add("详情评论");
		commentList.add("综合评论");
		childrenData.add(commentList);
	}

}
