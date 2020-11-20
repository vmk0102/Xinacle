package com.hassoft.xinacle.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.hassoft.xinacle.R;
import com.hassoft.xinacle.model.AccountsLedger;
import com.hassoft.xinacle.model.RunningStock;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;


public class runningStockAdapter extends BaseAdapter {
    Context context;
    RunningStock[] runningStocks =null;

    public runningStockAdapter(Context context, RunningStock[] runningStocks){
        this.context=context;
        this.runningStocks=runningStocks;

    }
    // int pos=0;


    @Override
    public Object getItem(int i) {
        return runningStocks[i];
    }

    @Override
    public int getCount() {
        return runningStocks.length;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if(view==null){
            view= LayoutInflater.from(context).inflate(R.layout.runningstock_single,viewGroup,false);
        }
        if(i%2==0){
            view.setBackgroundColor(context.getResources().getColor(R.color.white_greyish));
        }else{
            view.setBackgroundColor(context.getResources().getColor(R.color.white));
        }


        final RunningStock runningStock = (RunningStock) getItem(i);
        TextView ProductID=(TextView)view.findViewById(R.id.txtProductID);
        TextView ProductName=(TextView)view.findViewById(R.id.txtProductName);
        TextView RunningStock=(TextView)view.findViewById(R.id.txtRunningStock);
        TextView SerialNo=(TextView)view.findViewById(R.id.txtSerialNo);
        TextView ProductNumber=(TextView)view.findViewById(R.id.txtProductNumber);

        ProductID.setText(String.valueOf(runningStock.getProductID()));
        ProductName.setText(String.valueOf(runningStock.getProductName()));
        RunningStock.setText(String.valueOf(runningStock.getRunningStock()));
        SerialNo.setText(String.valueOf(runningStock.getSerialNo()));
        ProductNumber.setText(String.valueOf(runningStock.getProductNumber()));

        return  view;
    }

}
