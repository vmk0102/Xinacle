package com.hassoft.xinacle.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.hassoft.xinacle.R;
import com.hassoft.xinacle.apis.GetPurchaseSummary;
import com.hassoft.xinacle.model.AccountsLedger;
import com.hassoft.xinacle.model.PurchaseMaster;
import com.hassoft.xinacle.model.SalesSummary;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;


public class accountsLedgerAdapter extends BaseAdapter {
    Context context;
    AccountsLedger[] accountsLedgers =null;

    public accountsLedgerAdapter(Context context, AccountsLedger[] accountsLedgers){
        this.context=context;
        this.accountsLedgers=accountsLedgers;

    }
    DecimalFormat decimalFormat= new DecimalFormat("#,###.00");
    DecimalFormat noDecimalFormat=new DecimalFormat("#,###");
    // int pos=0;


    @Override
    public Object getItem(int i) {
        return accountsLedgers[i];
    }

    @Override
    public int getCount() {
        return accountsLedgers.length;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if(view==null){
            view= LayoutInflater.from(context).inflate(R.layout.accountsledger_single,viewGroup,false);
        }
        if(i%2==0){
            view.setBackgroundColor(context.getResources().getColor(R.color.white_greyish));
        }else{
            view.setBackgroundColor(context.getResources().getColor(R.color.white));
        }


        final AccountsLedger accountsLedger = (AccountsLedger) getItem(i);
        TextView VoucherID=(TextView)view.findViewById(R.id.txtVoucherID);
        TextView EDate=(TextView)view.findViewById(R.id.txtEDate);
        TextView TypeName=(TextView)view.findViewById(R.id.txtTypeName);
        TextView DebitAmount=(TextView)view.findViewById(R.id.txtDebitAmount);
        TextView CreditAmount=(TextView)view.findViewById(R.id.txtCreditAmount);
        TextView Balance=(TextView)view.findViewById(R.id.txtBalance);
        TextView AccountTitle=(TextView)view.findViewById(R.id.txtAccountTitle);

        SimpleDateFormat sdf=new SimpleDateFormat("dd-MM-yyyy");
        if(accountsLedger.getDebitAmount()!=null) {
            if (accountsLedger.getDebitAmount() > 0) {
                DebitAmount.setText(String.valueOf(decimalFormat.format(accountsLedger.getDebitAmount())));
            } else {
                DebitAmount.setText("0.00");
            }
        }
        if(accountsLedger.getCreditAmount()!=null) {
            if (accountsLedger.getCreditAmount() > 0) {
                CreditAmount.setText(String.valueOf(decimalFormat.format(accountsLedger.getCreditAmount())));
            } else {
                CreditAmount.setText("0.00");
            }
        }
        if(accountsLedger.getBalance()!=null) {
            if (accountsLedger.getBalance() > 0) {
                Balance.setText(String.valueOf(decimalFormat.format(accountsLedger.getBalance())));

            } else {
                Balance.setText("0.00");
            }
        }

        VoucherID.setText(accountsLedger.getVoucherID());
        EDate.setText(accountsLedger.getDate());
        AccountTitle.setText(accountsLedger.getAccountTitle());
        TypeName.setText(accountsLedger.getTypeName());

        return  view;
    }
    public String sumofNet(){
        double sum=0;
        for(AccountsLedger s: accountsLedgers ){
            sum+=s.getBalance();
        }
        return decimalFormat.format(sum);

    }

}
