package com.hassoft.xinacle.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.hassoft.xinacle.R;
import com.hassoft.xinacle.model.SalesSummary;
import com.hassoft.xinacle.model.SalesTransaction;

import java.sql.Date;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;


public class salesSummaryAdapter extends BaseAdapter {
    Context context;
    SalesSummary[] salesSummaries =null;

    public salesSummaryAdapter(Context context, SalesSummary[] salesSummaries){
        this.context=context;
        this.salesSummaries=salesSummaries;

    }
    DecimalFormat decimalFormat= new DecimalFormat("#,###.00");
    DecimalFormat noDecimalFormat=new DecimalFormat("#,###");
   // int pos=0;


    @Override
    public Object getItem(int i) {
        return salesSummaries[i];
    }

    @Override
    public int getCount() {
        return salesSummaries.length;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if(view==null){
            view= LayoutInflater.from(context).inflate(R.layout.salessummary_single,viewGroup,false);
        }
        if(i%2==0){
            view.setBackgroundColor(context.getResources().getColor(R.color.white_greyish));
        }else{
            view.setBackgroundColor(context.getResources().getColor(R.color.white));
        }


        final SalesSummary salesSummary = (SalesSummary) getItem(i);
        TextView SalesNo=(TextView)view.findViewById(R.id.txtSalesNo);
        TextView EDate=(TextView)view.findViewById(R.id.txtEDate);
        TextView CustomerName=(TextView)view.findViewById(R.id.txtCustomerName);
        TextView GrossAmount=(TextView)view.findViewById(R.id.txtGrossAmount);
        TextView DiscountAmount=(TextView)view.findViewById(R.id.txtDiscountAmount);
        TextView SalesTaxAmount=(TextView)view.findViewById(R.id.txtSalesTaxAmount);
        TextView NetAmount=(TextView)view.findViewById(R.id.txtNetAmount);
        TextView PaidAmount=(TextView)view.findViewById(R.id.txtPaidAmount);
        TextView BalanceAmount=(TextView)view.findViewById(R.id.txtBalanceAmount);
        TextView ReturnAmount=(TextView)view.findViewById(R.id.txtReturnAmount);

        SimpleDateFormat sdf=new SimpleDateFormat("dd-MM-yyyy");

        if(salesSummary.getDiscountAmount()>0) {
            DiscountAmount.setText(String.valueOf(decimalFormat.format(salesSummary.getDiscountAmount())));
        }else{
            DiscountAmount.setText("0.00");
        }
        if(salesSummary.getGrossAmount()>0) {
            GrossAmount.setText(String.valueOf(decimalFormat.format(salesSummary.getGrossAmount())));
        }else {
            GrossAmount.setText("0.00");
        }
        if(salesSummary.getNetAmount()>0){
            NetAmount.setText(String.valueOf(decimalFormat.format(salesSummary.getNetAmount())));

        }else {
            NetAmount.setText("0.00");
        }
        if(salesSummary.getSalesTaxAmount()>0) {
            SalesTaxAmount.setText(decimalFormat.format(salesSummary.getSalesTaxAmount()));
        }else{
            SalesTaxAmount.setText("0.00");

        }
        if(salesSummary.getPaidAmount()>0) {
            PaidAmount.setText(decimalFormat.format(salesSummary.getPaidAmount()));
        }else{
            PaidAmount.setText("0.00");

        }
        if(salesSummary.getBalanceAmount()>0) {
             BalanceAmount.setText(decimalFormat.format(salesSummary.getBalanceAmount()));
        }else{
            BalanceAmount.setText("0.00");

        }
        if(salesSummary.getReturnAmount()>0) {
            ReturnAmount.setText(decimalFormat.format(salesSummary.getReturnAmount()));
        }else{
            ReturnAmount.setText("0.00");
        }
        SalesNo.setText(salesSummary.getSalesNo());
        EDate.setText(salesSummary.getEDate());
        CustomerName.setText(String.valueOf(salesSummary.getCustomerName()));

        return  view;
    }
    public String sumofNet(){
        double sum=0;
        for(SalesSummary s: salesSummaries ){
            sum+=s.getNetAmount();
        }
        return decimalFormat.format(sum);

    }

}
