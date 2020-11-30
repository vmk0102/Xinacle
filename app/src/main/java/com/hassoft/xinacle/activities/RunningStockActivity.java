package com.hassoft.xinacle.activities;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.hassoft.xinacle.R;
import com.hassoft.xinacle.adapter.purchaseTransactionAdapter;
import com.hassoft.xinacle.adapter.runningStockAdapter;
import com.hassoft.xinacle.apis.GetPurchaseTransaction;
import com.hassoft.xinacle.apis.GetRunningStock;
import com.hassoft.xinacle.model.PurchaseTransaction;
import com.hassoft.xinacle.model.RunningStock;


public class RunningStockActivity extends AppCompatActivity {
ListView runningStockList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.runningstock);
/*
        fromdate=(TextView)findViewById(R.id.fromdate);
        todate=(TextView)findViewById(R.id.todate);
*/
        runningStockList=(ListView)findViewById(R.id.runningStockList);
//        totalNetSales=(TextView)findViewById(R.id.TotalNetAmount);
//        fromdate.setText("From: "+getIntent().getStringExtra("FromDate"));
//        fromdate.setFocusable(false);
//        fromdate.setClickable(true);
//        todate.setText("To: "+getIntent().getStringExtra("ToDate"));
//        todate.setFocusable(false);
 //       todate.setClickable(true);
        ProgressDialog pd =new ProgressDialog(RunningStockActivity.this);
        pd.setTitle("Please wait");
        pd.show();
        new Thread(new Runnable() {
            @Override
            public void run() {
                final String s = new GetRunningStock().getData(RunningStockActivity.this,"","","1","");

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        RunningStock[] ra = new Gson().fromJson(String.valueOf(s), RunningStock[].class);
                        if(ra!=null) {
                            runningStockAdapter pta = new runningStockAdapter(RunningStockActivity.this, ra);
                            runningStockList.setAdapter(pta);
                            //String sum=pta.sumofNet();
                            //totalNetSales.setText("Total Net Purchases: "+ sum);
                            pd.cancel();


                        }

                    }
                });
            }
        }).start();



    }
}

