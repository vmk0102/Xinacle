package com.hassoft.xinacle.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.hassoft.xinacle.R;
import com.hassoft.xinacle.model.Customers;
import com.hassoft.xinacle.model.Salesman;


public class salesmanAdapter extends BaseAdapter {
    Context context;
    Salesman[] salesmen=null;

    public salesmanAdapter(Context context, Salesman[] salesmen){
        this.context=context;
        this.salesmen=salesmen;

    }
   // int pos=0;


    @Override
    public Object getItem(int i) {
        return salesmen[i];
    }

    @Override
    public int getCount() {
        return salesmen.length;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if(view==null){
            view= LayoutInflater.from(context).inflate(R.layout.spinnersalemansngle,viewGroup,false);
        }

        final Salesman salesman = (Salesman) getItem(i);
        TextView optionName = (TextView) view.findViewById(R.id.itemCustomerName);
        optionName.setText(salesman.getSalesmanName());
        /*optionName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context, SalesSummaryActivity.class);
                i.putExtra("FromDate","2020-01-01");
                i.putExtra("ToDate","2020-10-22");
                i.putExtra("CompanyID","1");
                context.startActivity(i);
            }
        });*/
        return  view;
    }

}
