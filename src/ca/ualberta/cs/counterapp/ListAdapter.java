package ca.ualberta.cs.counterapp;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class ListAdapter extends BaseAdapter
{
	
	ArrayList<CounterModel> counterModels;
	private LayoutInflater inflater;

	@Override
	public int getCount()
	{
		if (counterModels == null){
			return 0;
		}
		else{
			return counterModels.size();
		}
		
	}

	@Override
	public Object getItem(int position)
	{

		return counterModels.get(position);
	}

	@Override
	public long getItemId(int position)
	{

		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent)
	{

		if (convertView == null)
		{
			convertView = inflater.inflate(R.layout.counter_object, null);
		}
		TextView text1 = (TextView) convertView.findViewById(R.id.textView1);
		TextView text2 = (TextView) convertView.findViewById(R.id.textView2);
		text1.setText(counterModels.get(position).getName());
		text2.setText(Integer.toString(counterModels.get(position).getCount()));

		return convertView;
	}

	public ListAdapter(Context context, ArrayList<CounterModel> counterList)
	{

		super();
		this.inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		this.counterModels = counterList;

	}
}