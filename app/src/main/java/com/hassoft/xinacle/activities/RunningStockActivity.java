package com.hassoft.xinacle.activities;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.common.base.Predicates;
import com.google.common.collect.Collections2;
import com.google.common.collect.Lists;
import com.google.gson.Gson;
import com.hassoft.xinacle.R;
import com.hassoft.xinacle.adapter.purchaseTransactionAdapter;
import com.hassoft.xinacle.adapter.runningStockAdapter;
import com.hassoft.xinacle.apis.GetPurchaseTransaction;
import com.hassoft.xinacle.apis.GetRunningStock;
import com.hassoft.xinacle.model.PurchaseTransaction;
import com.hassoft.xinacle.model.RunningStock;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;


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
                        try {

                            RunningStock[] ra = new Gson().fromJson(String.valueOf(s), RunningStock[].class);
                            List<RunningStock> temp = Arrays.asList(ra);
                            ArrayList<RunningStock> ras= Lists.newArrayList(Collections2.filter(temp,new RunningStockFilter(getIntent().getStringExtra("ProductNameRunningStock"))));

                            if (ra != null) {
                                runningStockAdapter pta = new runningStockAdapter(RunningStockActivity.this, ras);
                                runningStockList.setAdapter(pta);
                                //String sum=pta.sumofNet();
                                //totalNetSales.setText("Total Net Purchases: "+ sum);
                                pd.cancel();


                            }


                        }catch (Exception e){
                            Toast.makeText(RunningStockActivity.this, "Could not fetch data, please try again", Toast.LENGTH_SHORT).show();
                            pd.cancel();
                        }
                    }
                });
            }
        }).start();



    }
}

