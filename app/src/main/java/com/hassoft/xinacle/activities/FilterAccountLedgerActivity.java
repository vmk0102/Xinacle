package com.hassoft.xinacle.activities;

import android.accounts.Account;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.androidtrip.plugins.searchablespinner.SearchableSpinner;
import com.androidtrip.plugins.searchablespinner.interfaces.OnItemSelectedListener;
import com.google.gson.Gson;
import com.hassoft.xinacle.R;
import com.hassoft.xinacle.adapter.accountsAdapter;
import com.hassoft.xinacle.adapter.suppliersAdapter;
import com.hassoft.xinacle.apis.GetAccountCodes;
import com.hassoft.xinacle.apis.GetSupplier;
import com.hassoft.xinacle.model.Accounts;
import com.hassoft.xinacle.model.Supplier;
import com.ibotta.android.support.pickerdialogs.SupportedDatePickerDialog;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;


public class FilterAccountLedgerActivity extends AppCompatActivity {
    TextView fromDate;
    TextView toDate;
    SearchableSpinner AccountsSpinner;
    Button SubmitButton;
    String AccountID="";
    SimpleDateFormat sdf;
    String globalFromDate;
    String globalToDate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.filteraccountledger);
        sdf=new SimpleDateFormat("yyyy-MM-dd");
        fromDate=(TextView)findViewById(R.id.fromDate);
        toDate=(TextView)findViewById(R.id.toDate);
        AccountsSpinner =(SearchableSpinner) findViewById(R.id.listaccounts);

        SubmitButton=(Button)findViewById(R.id.submitFilter);
        final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();

        //toDate.setText(sdf.format(c.getTime()));
        Calendar fd=Calendar.getInstance();
        fd.set(Calendar.MONTH,c.getTime().getMonth());
        fd.set(Calendar.DAY_OF_MONTH,1);
        fd.set(Calendar.YEAR,c.getTime().getYear()+1900);
        //fromDate.setText(sdf.format(fd.getTime()));

        globalFromDate=sdf.format(fd.getTime());
        globalToDate=sdf.format(c.getTime());

        fromDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Calendar currentDate = Calendar.getInstance();
                int year = currentDate.get(Calendar.YEAR);
                int month = currentDate.get(Calendar.MONTH);
                int dayOfMonth = currentDate.get(Calendar.DAY_OF_MONTH);
               new SupportedDatePickerDialog(FilterAccountLedgerActivity.this, R.style.SpinnerDatePickerDialogTheme, new SupportedDatePickerDialog.OnDateSetListener() {
                   @Override
                   public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                       i1=i1+1;
                       fromDate.setText(String.valueOf(i2+"-"+i1+"-"+i));
                       globalFromDate=i+"-"+i1+"-"+i2;

                   }
               }, year, month, dayOfMonth).show();

            }
        });
        toDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Calendar currentDate = Calendar.getInstance();
                int year = currentDate.get(Calendar.YEAR);
                int month = currentDate.get(Calendar.MONTH);
                int dayOfMonth = currentDate.get(Calendar.DAY_OF_MONTH);
                new SupportedDatePickerDialog(FilterAccountLedgerActivity.this, R.style.SpinnerDatePickerDialogTheme, new SupportedDatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        i1=i1+1;
                        toDate.setText(String.valueOf(i2+"-"+i1+"-"+i));
                        globalToDate=i+"-"+i1+"-"+i2;

                    }
                }, year, month, dayOfMonth).show();
            }
        });
        new Thread(new Runnable() {
            @Override
            public void run() {
                String s = new GetAccountCodes().getData(FilterAccountLedgerActivity.this,"1");

                if(s!=null){
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Gson gson= new Gson();
                            Accounts[] accounts=gson.fromJson(s,Accounts[].class);
                            Accounts nullvalueAccounts= new Accounts();
                            nullvalueAccounts.setAccountCode("0");
                            nullvalueAccounts.setAccountTitle("All");


                            ArrayList<Accounts> withNullAccounts = new ArrayList<>(Arrays.asList(accounts));
                            withNullAccounts.add(0,nullvalueAccounts);

                            accountsAdapter aa  = new accountsAdapter(FilterAccountLedgerActivity.this,withNullAccounts);
                            AccountsSpinner.setAdapter(aa);
                            AccountsSpinner.setSelectedItem(withNullAccounts.size()-1);
                            AccountsSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {
                                @Override
                                public void onItemSelected(View view, int position, long id) {
                                    Accounts sp =(Accounts) AccountsSpinner.getSelectedItem();
                                    AccountID=String.valueOf(sp.getAccountCode());

                                }

                                @Override
                                public void onNothingSelected() {

                                }
                            });
                        }
                    });

                }
            }
        }).start();


        SubmitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(FilterAccountLedgerActivity.this, AccountLedgersActivity.class);
                if(fromDate.getText()!=null && !fromDate.getText().toString().trim().equalsIgnoreCase("")) {
                    i.putExtra("FromDate", globalFromDate.toString());
                }else{
                    i.putExtra("FromDate", "");
                }
                if(toDate.getText().toString().trim()!=null && !toDate.getText().toString().trim().equalsIgnoreCase("")) {
                    i.putExtra("ToDate", globalToDate.toString());
                }else {
                    i.putExtra("ToDate", "");
                }

                if(AccountID.equalsIgnoreCase("0")) {
                    i.putExtra("AccountCode", "");
                }
                else{
                    i.putExtra("AccountCode", AccountID);
                }

                startActivity(i);


            }
        });

    }
}
