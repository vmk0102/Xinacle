package com.hassoft.xinacle.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.cardview.widget.CardView;

import com.hassoft.xinacle.R;
import com.hassoft.xinacle.adapter.optionsAdapter;

public class MainActivity extends Activity {

    LinearLayout salesBox;
    LinearLayout purchaseBox;
    LinearLayout AccountLedgerBox;
    LinearLayout InventoryBox;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        salesBox=(LinearLayout)findViewById(R.id.salesBox);
        purchaseBox=(LinearLayout)findViewById(R.id.purchaseBox);
        AccountLedgerBox=(LinearLayout)findViewById(R.id.accountsBox);
        InventoryBox=(LinearLayout)findViewById(R.id.inventoryBox);
        salesBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,SalesActivity.class));
            }
        });
        purchaseBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,PurchaseActivity.class));
            }
        });
        AccountLedgerBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,FilterAccountLedgerActivity.class));
            }
        });
        InventoryBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,InventoryActivity.class));
            }
        });
      /*  lv=(GridView)findViewById(R.id.GridViewOptions);
        String[] OptionArray={"Configuration","Accounts","Purchase","Sales","Inventory","HR & Payroll","Administration","POS"};
        optionsAdapter oa = new optionsAdapter(MainActivity.this,OptionArray);
        lv.setAdapter(oa);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Toast.makeText(MainActivity.this, "puppooo", Toast.LENGTH_SHORT).show();
                String item = (String)lv.getItemAtPosition(position);
                if(item.equalsIgnoreCase("Sales")){
                    startActivity(new Intent(MainActivity.this,SalesActivity.class));
                }else if(item.equalsIgnoreCase("Purchase")){
                    startActivity(new Intent(MainActivity.this,PurchaseActivity.class));
                }

            }
        });*/




    }
}