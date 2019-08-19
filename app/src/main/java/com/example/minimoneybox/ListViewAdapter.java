package com.example.minimoneybox;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.example.minimoneybox.Model.ProductResponses;

import java.util.ArrayList;
import java.util.List;

public class ListViewAdapter extends BaseAdapter {
    private Context mcontect;
    private ArrayList<ProductResponses> productResponses;
    public ListViewAdapter(Context context,ArrayList<ProductResponses> productResponsesa ){
        mcontect=context;
        productResponses=productResponsesa;
    }

    @Override
    public int getCount() {
        return productResponses.size();
    }

    @Override
    public Object getItem(int i) {
        return productResponses.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView==null){
            convertView= LayoutInflater.from(mcontect).inflate(R.layout.listdata,parent,false);
        }
        ProductResponses temp=(ProductResponses) getItem(position);
        TextView account=(TextView)convertView.findViewById(R.id.accName);
        TextView plan=(TextView)convertView.findViewById(R.id.palnValue);
        TextView moneyBox=(TextView)convertView.findViewById(R.id.moneyBox);
        account.setText("Account name: "+temp.getProduct().getName());
        plan.setText("Plan Value: "+temp.getPlanValue());
        moneyBox.setText("Money Box: "+temp.getMoneybox()+"");
        return convertView;
    }
}
