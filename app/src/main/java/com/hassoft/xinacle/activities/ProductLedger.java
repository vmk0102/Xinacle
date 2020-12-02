package com.hassoft.xinacle.activities;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.hassoft.xinacle.R;
import com.hassoft.xinacle.adapter.productLedgerAdapter;
import com.hassoft.xinacle.adapter.runningStockAdapter;
import com.hassoft.xinacle.apis.GetProductLedger;
import com.hassoft.xinacle.apis.GetRunningStock;
import com.hassoft.xinacle.model.RunningStock;


public class ProductLedger extends AppCompatActivity {
ListView productLedgerList;
TextView fromdate;
TextView todate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.productledger);

        fromdate=(TextView)findViewById(R.id.fromdate);
        todate=(TextView)findViewById(R.id.todate);

        productLedgerList=(ListView)findViewById(R.id.productLedgerList);
        fromdate.setText("From: "+getIntent().getStringExtra("FromDate"));
        fromdate.setFocusable(false);
        fromdate.setClickable(true);
        todate.setText("To: "+getIntent().getStringExtra("ToDate"));
        todate.setFocusable(false);
        todate.setClickable(true);
        ProgressDialog pd =new ProgressDialog(ProductLedger.this);
        pd.setTitle("Please wait");
        pd.show();
        new Thread(new Runnable() {
            @Override
            public void run() {
                final String s = new GetProductLedger().GetData(ProductLedger.this,getIntent().getStringExtra("FromDate"),getIntent().getStringExtra("ToDate"),getIntent().getStringExtra("ProductID"),"1",getIntent().getStringExtra("WarehouseID"));

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        try {

                            com.hassoft.xinacle.model.ProductLedger[] pa = new Gson().fromJson(String.valueOf(s), com.hassoft.xinacle.model.ProductLedger[].class);
                            if (pa != null) {
                                productLedgerAdapter pta = new productLedgerAdapter(ProductLedger.this, pa);
                                productLedgerList.setAdapter(pta);
                                //totalNetSales.setText("Total Net Purchases: "+ sum);
                                pd.cancel();


                            }

                        }catch (Exception e){
                            Toast.makeText(ProductLedger.this, "Could not fetch data, please try again", Toast.LENGTH_SHORT).show();
                            pd.cancel();
                        }
                    }
                });
            }
        }).start();



    }
}

