package com.hassoft.xinacle.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.GridView;
import android.widget.LinearLayout;

import com.hassoft.xinacle.R;
import com.hassoft.xinacle.adapter.optionsAdapter;

public class PurchaseActivity extends Activity {

    LinearLayout purchaseSummary;
    LinearLayout purchaseDetails;
    LinearLayout dailyPurchase;
    LinearLayout monthlyPurchase;
    LinearLayout topPurchase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchase);
        purchaseSummary=(LinearLayout)findViewById(R.id.btnpurchasesummary);
        purchaseSummary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PurchaseActivity.this,FilterPurchaseMasterActivity.class));
            }
        });
    /*    setContentView(R.layout.activity_main);
        lv=(GridView)findViewById(R.id.GridViewOptions);
        String[] OptionArray={"Purchase Transaction","Purchase Summary"};
        optionsAdapter oa = new optionsAdapter(PurchaseActivity.this,OptionArray);
        lv.setAdapter(oa);
*/



    }
}