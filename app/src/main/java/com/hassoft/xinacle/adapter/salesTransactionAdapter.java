package com.hassoft.xinacle.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.hassoft.xinacle.R;
import com.hassoft.xinacle.model.SalesTransaction;

import java.text.DecimalFormat;


public class salesTransactionAdapter extends BaseAdapter {
    Context context;
    SalesTransaction[] salesTransactions=null;

    public salesTransactionAdapter(Context context, SalesTransaction[] salesTransactions){
        this.context=context;
        this.salesTransactions=salesTransactions;

    }
    DecimalFormat decimalFormat= new DecimalFormat("#,###.00");
    DecimalFormat noDecimalFormat=new DecimalFormat("#,###");
   // int pos=0;


    @Override
    public Object getItem(int i) {
        return salesTransactions[i];
    }

    @Override
    public int getCount() {
        return salesTransactions.length;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if(view==null){
            view= LayoutInflater.from(context).inflate(R.layout.salestransaction_single,viewGroup,false);
        }
        if(i%2==0){
            view.setBackgroundColor(context.getResources().getColor(R.color.white_greyish));
        }else{
            view.setBackgroundColor(context.getResources().getColor(R.color.white));
        }


        final SalesTransaction salesTransaction = (SalesTransaction) getItem(i);
        TextView ProductName=(TextView)view.findViewById(R.id.txtProductName);
        TextView BoxQuantity=(TextView)view.findViewById(R.id.txtBoxQuantity);
        TextView Rate=(TextView)view.findViewById(R.id.txtRate);
        TextView DiscountRate=(TextView)view.findViewById(R.id.txtDiscountRate);
        TextView GrossAmount=(TextView)view.findViewById(R.id.txtGrossAmount);
        TextView NetAmount=(TextView)view.findViewById(R.id.txtNetAmount);
        TextView CustomerName=(TextView)view.findViewById(R.id.txtCustomerName);
        ProductName.setText(salesTransaction.getProductName());
        if(salesTransaction.getQuantityBox()>0) {
            BoxQuantity.setText(noDecimalFormat.format(salesTransaction.getQuantityBox()));
        }else{
            BoxQuantity.setText(String.valueOf(0));
        }
        if(salesTransaction.getRate()>0) {
            Rate.setText(String.valueOf(decimalFormat.format(salesTransaction.getRate())));
        }else{
            Rate.setText("0.00");
        }
        if(salesTransaction.getDiscount()>0) {
            DiscountRate.setText(String.valueOf(decimalFormat.format(salesTransaction.getDiscount())));
        }else{
            DiscountRate.setText("0.00");
        }
        if(salesTransaction.getGrossAmount()>0) {
            GrossAmount.setText(String.valueOf(decimalFormat.format(salesTransaction.getGrossAmount())));
        }else {
            GrossAmount.setText("0.00");
        }
        if(salesTransaction.getNetAmount()>0){
            NetAmount.setText(String.valueOf(decimalFormat.format(salesTransaction.getNetAmount())));

        }else {
            NetAmount.setText("0.00");
        }

        CustomerName.setText(String.valueOf(salesTransaction.getCustomerName()));


        return  view;
    }
    public String sumofNet(){
        double sum=0;
        for(SalesTransaction s: salesTransactions ){
            sum+=s.getNetAmount();
        }
        return decimalFormat.format(sum);

    }

}
