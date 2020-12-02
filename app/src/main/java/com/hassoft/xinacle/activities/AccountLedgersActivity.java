package com.hassoft.xinacle.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.hassoft.xinacle.R;
import com.hassoft.xinacle.adapter.accountsAdapter;
import com.hassoft.xinacle.adapter.accountsLedgerAdapter;
import com.hassoft.xinacle.apis.GetAccountsLedger;
import com.hassoft.xinacle.apis.GetPurchaseSummary;
import com.hassoft.xinacle.model.AccountsLedger;
import com.hassoft.xinacle.model.PurchaseMaster;


public class AccountLedgersActivity extends AppCompatActivity {
TextView fromdate;
TextView todate;
ListView accountledgersList;
TextView totalNetSales;
Button btnViewChart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.accountledgers);
        fromdate=(TextView)findViewById(R.id.fromdate);
        todate=(TextView)findViewById(R.id.todate);
        accountledgersList=(ListView)findViewById(R.id.accountsLedgerList);
        totalNetSales=(TextView)findViewById(R.id.TotalNetAmount);
        fromdate.setText("From: "+getIntent().getStringExtra("FromDate"));
        fromdate.setFocusable(false);
        fromdate.setClickable(true);
        todate.setText("To: "+getIntent().getStringExtra("ToDate"));
        todate.setFocusable(false);
        todate.setClickable(true);
        //btnViewChart=(Button)findViewById(R.id.showGraph);
//        btnViewChart.setVisibility(View.GONE);
        ProgressDialog pd =new ProgressDialog(AccountLedgersActivity.this);
        pd.setTitle("Please wait");
        pd.show();
        new Thread(new Runnable() {
            @Override
            public void run() {
                final String s = new GetAccountsLedger().getData(AccountLedgersActivity.this,getIntent().getStringExtra("FromDate"),getIntent().getStringExtra("ToDate"),getIntent().getStringExtra("AccountID"),"1");

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        try{
                        AccountsLedger[] sa = new Gson().fromJson(String.valueOf(s), AccountsLedger[].class);
                        if(sa!=null) {
                            accountsLedgerAdapter asa = new accountsLedgerAdapter(AccountLedgersActivity.this, sa);
                          accountledgersList.setAdapter(asa);
                            String sum=asa.sumofNet();
                            totalNetSales.setText("Total Balance: "+ sum);
                            pd.cancel();
                          /*  btnViewChart.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Intent i = new Intent(AccountLedgersActivity.this,PurchaseSummaryChart.class);
                                    i.putExtra("netpurchases",s);
                                    startActivity(i);
                                }
                            });*/
                            }

                        }catch (Exception e){
                            Toast.makeText(AccountLedgersActivity.this, "Could not fetch data, please try again", Toast.LENGTH_SHORT).show();
                            pd.cancel();
                        }

                    }
                });
            }
        }).start();



    }
}

