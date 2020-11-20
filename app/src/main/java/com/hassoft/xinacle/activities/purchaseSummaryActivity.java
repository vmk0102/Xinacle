package com.hassoft.xinacle.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.hassoft.xinacle.R;
import com.hassoft.xinacle.adapter.purchaseSummaryAdapter;
import com.hassoft.xinacle.adapter.salesSummaryAdapter;
import com.hassoft.xinacle.apis.GetPurchaseSummary;
import com.hassoft.xinacle.apis.GetSalesSummary;
import com.hassoft.xinacle.model.PurchaseMaster;
import com.hassoft.xinacle.model.SalesSummary;


public class purchaseSummaryActivity extends AppCompatActivity {
TextView fromdate;
TextView todate;
ListView salesTransactionList;
TextView totalNetSales;
Button btnViewChart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.purchasesummary);
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
        btnViewChart=(Button)findViewById(R.id.showGraph);
        btnViewChart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        ProgressDialog pd =new ProgressDialog(purchaseSummaryActivity.this);
        pd.setTitle("Please wait");
        pd.show();
        new Thread(new Runnable() {
            @Override
            public void run() {
                final String s = new GetPurchaseSummary().getData(purchaseSummaryActivity.this,getIntent().getStringExtra("FromDate"),getIntent().getStringExtra("ToDate"),getIntent().getStringExtra("SupplierID"),"1");

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        PurchaseMaster[] sa = new Gson().fromJson(String.valueOf(s), PurchaseMaster[].class);
                        if(sa!=null) {
                            purchaseSummaryAdapter psa = new purchaseSummaryAdapter(purchaseSummaryActivity.this, sa);
                            salesTransactionList.setAdapter(psa);
                            String sum=psa.sumofNet();
                            totalNetSales.setText("Total Net Purchases: "+ sum);
                            pd.cancel();
                            btnViewChart.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Intent i = new Intent(purchaseSummaryActivity.this,PurchaseSummaryChart.class);
                                    i.putExtra("netpurchases",s);
                                    startActivity(i);
                                }
                            });


                        }

                    }
                });
            }
        }).start();



    }
}

