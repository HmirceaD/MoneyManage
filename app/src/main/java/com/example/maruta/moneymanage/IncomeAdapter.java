package com.example.maruta.moneymanage;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class IncomeAdapter extends BaseAdapter{

    private Context mContext;
    private List<IncomeListItem> incList;

    public IncomeAdapter(Context c, List<IncomeListItem> ls){

        this.mContext = c;
        this.incList = ls;
    }

    @Override
    public int getCount() {

        return incList.size();

    }

    @Override
    public Object getItem(int position) {

        return incList.get(position);
    }

    @Override
    public long getItemId(int position) {

        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = View.inflate(mContext, R.layout.income_list_item, null);

        TextView name = v.findViewById(R.id.bName);
        TextView money = v.findViewById(R.id.bNum);
        TextView day = v.findViewById(R.id.bDay);

        name.setText(incList.get(position).getBudgetName());
        money.setText(incList.get(position).getBudget());
        day.setText(incList.get(position).getDay());

        return v;
    }
}
