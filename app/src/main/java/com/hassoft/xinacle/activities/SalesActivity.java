package com.hassoft.xinacle.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.hassoft.xinacle.R;
import com.hassoft.xinacle.adapter.optionsAdapter;

public class SalesActivity extends Activity {

    LinearLayout SalesSummary;
    LinearLayout SalesDetails;
    LinearLayout TopSales;
    LinearLayout MonthlySales;
    LinearLayout DailySales;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sales);
        SalesSummary=(LinearLayout)findViewById(R.id.btnsalessummary);
        SalesDetails=(LinearLayout)findViewById(R.id.btnsalesdetails);
        DailySales=(LinearLayout)findViewById(R.id.btndailysales);
        MonthlySales=(LinearLayout)findViewById(R.id.btnmonthlySales);
        TopSales=(LinearLayout)findViewById(R.id.btntopsales);
        SalesSummary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SalesActivity.this,FilterSalesMasterActivity.class));
            }
        });
        SalesDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SalesActivity.this,FilterSalesTransactionActivity.class));
            }
        });
        DailySales.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SalesActivity.this,FilterDailySales.class));
            }
        });
        MonthlySales.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(SalesActivity.this, "Under Development", Toast.LENGTH_SHORT).show();
            }
        });
        TopSales.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(SalesActivity.this, "Under Development", Toast.LENGTH_SHORT).show();
            }
        });

       /* lv=(GridView)findViewById(R.id.GridViewOptions);
        String[] OptionArray={"Sales Transaction","Sales Summary"};
        optionsAdapter oa = new optionsAdapter(SalesActivity.this,OptionArray);
        lv.setAdapter(oa);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String i = (String)lv.getItemAtPosition(position);
                if(i.equals("Sales Transaction")){

                }else if(i.equals("Sales Summary")){

                }
            }
        });*/





    }
}