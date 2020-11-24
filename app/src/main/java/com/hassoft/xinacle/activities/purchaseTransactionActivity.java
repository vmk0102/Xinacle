package com.hassoft.xinacle.activities;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.hassoft.xinacle.R;
import com.hassoft.xinacle.adapter.purchaseSummaryAdapter;
import com.hassoft.xinacle.adapter.purchaseTransactionAdapter;
import com.hassoft.xinacle.apis.GetPurchaseSummary;
import com.hassoft.xinacle.apis.GetPurchaseTransaction;
import com.hassoft.xinacle.model.PurchaseMaster;
import com.hassoft.xinacle.model.PurchaseTransaction;


public class purchaseTransactionActivity extends AppCompatActivity {
TextView fromdate;
TextView todate;
ListView salesTransactionList;
TextView totalNetSales;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.purchasetransaction);
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
        ProgressDialog pd =new ProgressDialog(purchaseTransactionActivity.this);
        pd.setTitle("Please wait");
        pd.show();
        new Thread(new Runnable() {
            @Override
            public void run() {
                final String s = new GetPurchaseTransaction().getData(purchaseTransactionActivity.this,getIntent().getStringExtra("FromDate"),getIntent().getStringExtra("ToDate"),getIntent().getStringExtra("SupplierID"),getIntent().getStringExtra("ProductID"),"1");

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        PurchaseTransaction[] sa = new Gson().fromJson(String.valueOf(s), PurchaseTransaction[].class);
                        if(sa!=null) {
                            purchaseTransactionAdapter pta = new purchaseTransactionAdapter(purchaseTransactionActivity.this, sa);
                            salesTransactionList.setAdapter(pta);
                            String sum=pta.sumofNet();
                            totalNetSales.setText("Total Net Purchases: "+ sum);
                            pd.cancel();


                        }

                    }
                });
            }
        }).start();



    }
}

