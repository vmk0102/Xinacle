package com.hassoft.xinacle.activities;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.hassoft.xinacle.R;
import com.hassoft.xinacle.apis.GetSalesTransaction;
import com.hassoft.xinacle.model.SalesTransaction;
import com.hassoft.xinacle.adapter.salesTransactionAdapter;

import org.json.JSONArray;


public class SalesTransactionActivity extends AppCompatActivity {
TextView fromdate;
TextView todate;
ListView salesTransactionList;
TextView totalNetSales;
Button ShowChart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.salestransaction);
        fromdate=(TextView)findViewById(R.id.fromdate);
        todate=(TextView)findViewById(R.id.todate);
        salesTransactionList=(ListView)findViewById(R.id.salesTransactionList);
        totalNetSales=(TextView)findViewById(R.id.TotalNetAmount);
        fromdate.setText("From: "+getIntent().getStringExtra("FromDate"));
        todate.setText("To: "+getIntent().getStringExtra("ToDate"));
        new Thread(new Runnable() {
            @Override
            public void run() {
                final String s = new GetSalesTransaction().getData(SalesTransactionActivity.this,getIntent().getStringExtra("FromDate"),getIntent().getStringExtra("ToDate"),getIntent().getStringExtra("CustomerID"),getIntent().getStringExtra("ProductID"),1);

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        SalesTransaction[] sa = new Gson().fromJson(String.valueOf(s),SalesTransaction[].class);
                        if(sa!=null) {
                            salesTransactionAdapter sta = new salesTransactionAdapter(SalesTransactionActivity.this, sa);
                            salesTransactionList.setAdapter(sta);
                            String sum=sta.sumofNet();
                            totalNetSales.setText("Total Net Sales: "+ sum);


                        }

                    }
                });
            }
        }).start();



    }
}

