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
	protected static final int TYPE_REALTIME = 0x00;
	protected static final int TYPE_HISTORY = 0x01;
	
	protected List<String> groupData = new ArrayList<String>(); //һ��Ŀ¼�����б�
	protected List<List<String>> childrenData = new ArrayList<List<String>>(); //����Ŀ¼�����б�

	protected BaseExpandableListAdapter mAdapter; //����չ���б�������

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
		
		setAdapter();
		listView.setAdapter(mAdapter);
		return rootView;
	}
	
	public void setAdapter() {
		mAdapter = new ExpandAdapter(getActivity(), groupData, childrenData, TYPE_REALTIME);
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
		groupData.add("�û�ģ��");
		ArrayList<String> userList = new ArrayList<String>();
		userList.add("�û�ע��");
		userList.add("�û���½");
		userList.add("�û��˳�");
		childrenData.add(userList);
		
		groupData.add("�Ʒ�ģ��");
		ArrayList<String> billList = new ArrayList<String>();
		billList.add("���ò�ѯ");
		billList.add("����֧��");
		billList.add("����ȡ��");
		childrenData.add(billList);
		
		groupData.add("����ģ��");
		ArrayList<String> commentList = new ArrayList<String>();
		commentList.add("�б�����");
		commentList.add("��������");
		commentList.add("�ۺ�����");
		childrenData.add(commentList);
	}

}
