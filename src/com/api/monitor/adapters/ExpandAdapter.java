package com.api.monitor.adapters;

import java.util.List;

import com.api.monitor.R;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ExpandAdapter extends BaseExpandableListAdapter {
	private List<String> mGroupData;
	private List<List<String>> mChildrenData;
	private LayoutInflater layoutInflater;
	private Context mContext;

	public ExpandAdapter(Context context, List<String> groupData,
			List<List<String>> childrenData) {
		mGroupData = groupData;
		mChildrenData = childrenData;
		layoutInflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		mContext = context;
	}

	@Override
	public Object getChild(int arg0, int arg1) {
		return mChildrenData.get(arg0).get(arg1);
	}

	@Override
	public long getChildId(int arg0, int arg1) {
		return 0;
	}

	@Override
	public View getChildView(final int arg0, final int arg1, boolean arg2, View convertView,
			ViewGroup arg4) {
		ChildHolder holder = null;
		if (convertView == null) {    
			convertView =  layoutInflater.inflate(R.layout.row_expand_child, null);
			holder = new ChildHolder();
			holder.title = (TextView) convertView.findViewById(R.id.title);
			convertView.setTag(holder);
        } else {    
        	holder = (ChildHolder) convertView.getTag(); 
        }    
		
		holder.title.setText(mChildrenData.get(arg0).get(arg1));
		
		convertView.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View view) {
//				Intent intent = new Intent(mContext, GovFunctionDetailActivity.class);
//				intent.putExtra("pname", mGroupData.get(arg0));
//				intent.putExtra("title", mChildrenData.get(arg0).get(arg1));
//				mContext.startActivity(intent);
			}
		});
		
		return convertView;
	}

	@Override
	public int getChildrenCount(int arg0) {
		return mChildrenData.get(arg0).size();
	}

	@Override
	public Object getGroup(int arg0) {
		return mGroupData.get(arg0);
	}

	@Override
	public int getGroupCount() {
		return mGroupData.size();
	}

	@Override
	public long getGroupId(int arg0) {
		return 0;
	}

	@Override
	public View getGroupView(int arg0, boolean arg1, View convertView, ViewGroup arg3) {
		ParentHolder holder = null;
		if (convertView == null) {    
			convertView =  layoutInflater.inflate(R.layout.row_expand_parent, null);
			holder = new ParentHolder();
			holder.indicator = (ImageView) convertView.findViewById(R.id.group_indicator);
			holder.title = (TextView) convertView.findViewById(R.id.title);
			convertView.setTag(holder);
        } else {    
        	holder = (ParentHolder) convertView.getTag(); 
        }    
		
		int imageResourceId = arg1 ? R.drawable.btn_arrow_down : R.drawable.btn_arrow_right;
		holder.indicator.setImageResource(imageResourceId);
		
		holder.title.setText(mGroupData.get(arg0));
		
		return convertView;
	}

	@Override
	public boolean hasStableIds() {
		return false;
	}

	@Override
	public boolean isChildSelectable(int arg0, int arg1) {
		return false;
	}

	static class ParentHolder {
		ImageView indicator; //根据点击事件而变化的indicator
		TextView title; //一级标题
	}
	
	static class ChildHolder {
		TextView title; //二级标题
	}
}
