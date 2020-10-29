package com.hassoft.xinacle.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.hassoft.xinacle.R;
import com.hassoft.xinacle.activities.SalesSummaryActivity;
import com.hassoft.xinacle.activities.SalesTransactionActivity;
import com.hassoft.xinacle.model.SalesTransaction;


public class optionsAdapter extends BaseAdapter {
    Context context;
    String[] options=null;

    public optionsAdapter(Context context, String[] options){
        this.context=context;
        this.options=options;

    }
   // int pos=0;


    @Override
    public Object getItem(int i) {
        return options[i];
    }

    @Override
    public int getCount() {
        return options.length;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if(view==null){
            view= LayoutInflater.from(context).inflate(R.layout.option_single,viewGroup,false);
        }

        final String option = (String) getItem(i);
        TextView optionName = (TextView) view.findViewById(R.id.buttonOption);
        optionName.setText(option);
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
