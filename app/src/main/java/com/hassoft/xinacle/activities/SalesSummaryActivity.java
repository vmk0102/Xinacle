package com.hassoft.xinacle.activities;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.hassoft.xinacle.R;
import com.hassoft.xinacle.adapter.salesSummaryAdapter;
import com.hassoft.xinacle.adapter.salesTransactionAdapter;
import com.hassoft.xinacle.apis.GetSalesSummary;
import com.hassoft.xinacle.apis.GetSalesTransaction;
import com.hassoft.xinacle.model.SalesSummary;
import com.hassoft.xinacle.model.SalesTransaction;


public class SalesSummaryActivity extends AppCompatActivity {
TextView fromdate;
TextView todate;
ListView salesTransactionList;
TextView totalNetSales;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.salessummary);
        fromdate=(TextView)findViewById(R.id.fromdate);
        todate=(TextView)findViewById(R.id.todate);
        salesTransactionList=(ListView)findViewById(R.id.salesTransactionList);
        totalNetSales=(TextView)findViewById(R.id.TotalNetAmount);
        fromdate.setText("From: "+getIntent().getStringExtra("FromDate"));
        fromdate.setFocusable(false);
        fromdate.setClickable(true);
        todate.setText("To: "+getIntent().getStringExtra("ToDate"));
        todate.setFocusable(false);
        todate.setClickable(true);
        ProgressDialog pd =new ProgressDialog(SalesSummaryActivity.this);
        pd.setTitle("Please wait");
        pd.show();
        new Thread(new Runnable() {
            @Override
            public void run() {
                final String s = new GetSalesSummary().getData(SalesSummaryActivity.this,getIntent().getStringExtra("FromDate"),getIntent().getStringExtra("ToDate"),getIntent().getStringExtra("CustomerID"),"","1");

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        SalesSummary[] sa = new Gson().fromJson(String.valueOf(s), SalesSummary[].class);
                        if(sa!=null) {
                            salesSummaryAdapter sta = new salesSummaryAdapter(SalesSummaryActivity.this, sa);
                            salesTransactionList.setAdapter(sta);
                            String sum=sta.sumofNet();
                            totalNetSales.setText("Total Net Sales: "+ sum);
                            pd.cancel();


                        }

                    }
                });
            }
        }).start();



    }
}

