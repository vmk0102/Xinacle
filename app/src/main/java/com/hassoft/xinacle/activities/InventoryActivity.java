package com.hassoft.xinacle.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.hassoft.xinacle.R;

public class InventoryActivity extends Activity {

    LinearLayout runningStock;
    LinearLayout productLedger;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventory);
        runningStock=(LinearLayout)findViewById(R.id.btnrunninigstock);
        productLedger=(LinearLayout)findViewById(R.id.btnproductledger);



    }
}