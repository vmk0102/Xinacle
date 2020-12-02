package com.hassoft.xinacle.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.hassoft.xinacle.R;
import com.hassoft.xinacle.model.ProductLedger;
import com.hassoft.xinacle.model.PurchaseTransaction;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;


public class productLedgerAdapter extends BaseAdapter {
    Context context;
    ProductLedger[] productLedgers =null;

    public productLedgerAdapter(Context context, ProductLedger[] productLedgers){
        this.context=context;
        this.productLedgers=productLedgers;

    }
    DecimalFormat decimalFormat= new DecimalFormat("#,###.00");
    DecimalFormat noDecimalFormat=new DecimalFormat("#,###");
   // int pos=0;


    @Override
    public Object getItem(int i) {
        return productLedgers[i];
    }

    @Override
    public int getCount() {
        return productLedgers.length;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if(view==null){
            view= LayoutInflater.from(context).inflate(R.layout.productledger_single,viewGroup,false);
        }
        if(i%2==0){
            view.setBackgroundColor(context.getResources().getColor(R.color.white_greyish));
        }else{
            view.setBackgroundColor(context.getResources().getColor(R.color.white));
        }


        final ProductLedger productLedger = (ProductLedger) getItem(i);
        TextView EDate=(TextView)view.findViewById(R.id.txtEDate);
        TextView In=(TextView)view.findViewById(R.id.txtIn);
        TextView Out=(TextView)view.findViewById(R.id.txtOut);
        TextView Balance=(TextView)view.findViewById(R.id.txtBalance);
        TextView ProductName=(TextView)view.findViewById(R.id.txtProductName);
        TextView Particular=(TextView)view.findViewById(R.id.txtParticular);
        TextView OpeningStock=(TextView)view.findViewById(R.id.txtOpeningStock);
        TextView WarehouseName=(TextView)view.findViewById(R.id.txtWarehouseName);
        TextView TransactionNo=(TextView)view.findViewById(R.id.txtTransactionNo);

        SimpleDateFormat sdf=new SimpleDateFormat("dd-MM-yyyy");

        if(productLedger.getIn()!=null){
        if(productLedger.getIn()>0) {
            In.setText(String.valueOf(decimalFormat.format(productLedger.getIn())));
        }else{
            In.setText("0.00");
        }
        }else {
            In.setText("0.00");
        }

        if(productLedger.getOut()!=null) {
            if (productLedger.getOut() > 0) {
                Out.setText(String.valueOf(decimalFormat.format(productLedger.getOut())));
            } else {
                Out.setText("0.00");
            }
        }else{
            Out.setText("0.00");
        }
        if(String.valueOf(productLedger.getBalance())!=null) {
            if (productLedger.getBalance() > 0) {
                Balance.setText(String.valueOf(decimalFormat.format(productLedger.getBalance())));

            } else {
                Balance.setText("0.00");
            }
        }else {
            Balance.setText("0.00");
        }

        if(productLedger.getOpenningStock()!=null) {
            if (productLedger.getOpenningStock() > 0) {
                OpeningStock.setText(decimalFormat.format(productLedger.getOpenningStock()));
            } else {
                OpeningStock.setText("0.00");

            }
        }else{
            OpeningStock.setText("0.00");
        }
            if (productLedger.getTransactionNo() > 0) {
                TransactionNo.setText(decimalFormat.format(productLedger.getTransactionNo()));
            } else {
                TransactionNo.setText("0.00");

            }



        Particular.setText(productLedger.getParticular());
        EDate.setText(productLedger.getEDate());
        ProductName.setText(String.valueOf(productLedger.getProductName()));
        WarehouseName.setText(String.valueOf(productLedger.getWaraehouseName()));

        return  view;
    }


}
