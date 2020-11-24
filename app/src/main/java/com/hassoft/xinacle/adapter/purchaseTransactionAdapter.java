package com.hassoft.xinacle.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.hassoft.xinacle.R;
import com.hassoft.xinacle.model.PurchaseMaster;
import com.hassoft.xinacle.model.PurchaseTransaction;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;


public class purchaseTransactionAdapter extends BaseAdapter {
    Context context;
    PurchaseTransaction[] purchaseTransactions =null;

    public purchaseTransactionAdapter(Context context, PurchaseTransaction[] purchaseTransactions){
        this.context=context;
        this.purchaseTransactions=purchaseTransactions;

    }
    DecimalFormat decimalFormat= new DecimalFormat("#,###.00");
    DecimalFormat noDecimalFormat=new DecimalFormat("#,###");
   // int pos=0;


    @Override
    public Object getItem(int i) {
        return purchaseTransactions[i];
    }

    @Override
    public int getCount() {
        return purchaseTransactions.length;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if(view==null){
            view= LayoutInflater.from(context).inflate(R.layout.purchasetransaction_single,viewGroup,false);
        }
        if(i%2==0){
            view.setBackgroundColor(context.getResources().getColor(R.color.white_greyish));
        }else{
            view.setBackgroundColor(context.getResources().getColor(R.color.white));
        }


        final PurchaseTransaction purchaseTransaction = (PurchaseTransaction) getItem(i);
        TextView SalesNo=(TextView)view.findViewById(R.id.txtPurchaseNo);
        TextView EDate=(TextView)view.findViewById(R.id.txtEDate);
        TextView CustomerName=(TextView)view.findViewById(R.id.txtSupplierName);
        TextView GrossAmount=(TextView)view.findViewById(R.id.txtGrossAmount);
        TextView DiscountAmount=(TextView)view.findViewById(R.id.txtDiscountAmount);
        TextView SalesTaxAmount=(TextView)view.findViewById(R.id.txtSalesTaxAmount);
        TextView NetAmount=(TextView)view.findViewById(R.id.txtNetAmount);
        TextView PaidAmount=(TextView)view.findViewById(R.id.txtPaidAmount);
        TextView BalanceAmount=(TextView)view.findViewById(R.id.txtBalanceAmount);
        TextView ReturnAmount=(TextView)view.findViewById(R.id.txtReturnAmount);

        SimpleDateFormat sdf=new SimpleDateFormat("dd-MM-yyyy");

        if(String.valueOf(purchaseTransaction.getDiscountAmount())!=null){
        if(purchaseTransaction.getDiscountAmount()>0) {
            DiscountAmount.setText(String.valueOf(decimalFormat.format(purchaseTransaction.getDiscountAmount())));
        }else{
            DiscountAmount.setText("0.00");
        }
        }else {
            DiscountAmount.setText("0.00");
        }

        if(String.valueOf(purchaseTransaction.getGrossAmount())!=null) {
            if (purchaseTransaction.getGrossAmount() > 0) {
                GrossAmount.setText(String.valueOf(decimalFormat.format(purchaseTransaction.getGrossAmount())));
            } else {
                GrossAmount.setText("0.00");
            }
        }else{
            GrossAmount.setText("0.00");
        }
        if(String.valueOf(purchaseTransaction.getNetAmount())!=null) {
            if (purchaseTransaction.getNetAmount() > 0) {
                NetAmount.setText(String.valueOf(decimalFormat.format(purchaseTransaction.getNetAmount())));

            } else {
                NetAmount.setText("0.00");
            }
        }else {
            NetAmount.setText("0.00");
        }

        if(String.valueOf(purchaseTransaction.getSalesTaxAmount())!=null) {
            if (purchaseTransaction.getSalesTaxAmount() > 0) {
                SalesTaxAmount.setText(decimalFormat.format(purchaseTransaction.getSalesTaxAmount()));
            } else {
                SalesTaxAmount.setText("0.00");

            }
        }else{
            SalesTaxAmount.setText("0.00");
        }
        if(String.valueOf(purchaseTransaction.getPaidAmount())!=null) {
            if (purchaseTransaction.getPaidAmount() > 0) {
                PaidAmount.setText(decimalFormat.format(purchaseTransaction.getPaidAmount()));
            } else {
                PaidAmount.setText("0.00");

            }
        }else{
            PaidAmount.setText("0.00");
        }

        if(purchaseTransaction.getBalanceAmount()!=null) {
            if (purchaseTransaction.getBalanceAmount() > 0) {
                BalanceAmount.setText(decimalFormat.format(purchaseTransaction.getBalanceAmount()));
            } else {
                BalanceAmount.setText("0.00");

            }
        }else {
            BalanceAmount.setText("0.00");
        }


        if(purchaseTransaction.getReturnAmount()!=null) {
            if (purchaseTransaction.getReturnAmount() > 0) {
                ReturnAmount.setText(decimalFormat.format(purchaseTransaction.getReturnAmount()));
            } else {
                ReturnAmount.setText("0.00");
            }
        }else {
            ReturnAmount.setText("0.00");
        }




        SalesNo.setText(purchaseTransaction.getPurchaseNo());
        EDate.setText(purchaseTransaction.getEDate());
        CustomerName.setText(String.valueOf(purchaseTransaction.getSupplierName()));

        return  view;
    }
    public String sumofNet(){
        double sum=0;
        for(PurchaseTransaction s: purchaseTransactions ){
            sum+=s.getNetAmount();
        }
        return decimalFormat.format(sum);

    }

}
