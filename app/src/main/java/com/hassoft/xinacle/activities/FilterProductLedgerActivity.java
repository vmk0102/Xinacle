package com.hassoft.xinacle.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.androidtrip.plugins.searchablespinner.SearchableSpinner;
import com.androidtrip.plugins.searchablespinner.interfaces.OnItemSelectedListener;
import com.google.gson.Gson;
import com.hassoft.xinacle.R;
import com.hassoft.xinacle.adapter.productsAdapter;
import com.hassoft.xinacle.adapter.suppliersAdapter;
import com.hassoft.xinacle.adapter.warehouseAdapter;
import com.hassoft.xinacle.apis.GetProducts;
import com.hassoft.xinacle.apis.GetSupplier;
import com.hassoft.xinacle.apis.GetWarehouse;
import com.hassoft.xinacle.model.Products;
import com.hassoft.xinacle.model.Supplier;
import com.hassoft.xinacle.model.Warehouse;
import com.ibotta.android.support.pickerdialogs.SupportedDatePickerDialog;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;


public class FilterProductLedgerActivity extends AppCompatActivity {
    TextView fromDate;
    TextView toDate;
    SearchableSpinner  ProductSpinner;
    Button SubmitButton;
    String WarehouseID="";
    String ProductID="";
    String globalFromDate="";
    String globalToDate="";
    SearchableSpinner WarehouseSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.filterproductledger);
        fromDate=(TextView)findViewById(R.id.fromDate);
        toDate=(TextView)findViewById(R.id.toDate);
        ProductSpinner=(SearchableSpinner) findViewById(R.id.listproduct);
        WarehouseSpinner=(SearchableSpinner)findViewById(R.id.listwarehouse);
        WarehouseSpinner.setVisibility(View.GONE);
        SubmitButton=(Button)findViewById(R.id.submitFilter);
        final SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        Calendar c = Calendar.getInstance();
      //  toDate.setText(sdf.format(c.getTime()));
        Calendar fd=Calendar.getInstance();
        fd.set(Calendar.MONTH,c.getTime().getMonth());
        fd.set(Calendar.DAY_OF_MONTH,1);
        fd.set(Calendar.YEAR,c.getTime().getYear()+1900);
       // fromDate.setText(sdf.format(fd.getTime()));

        globalFromDate=sdf.format(fd.getTime());
        globalToDate=sdf.format(c.getTime());
        fromDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar currentDate = Calendar.getInstance();
                int year = currentDate.get(Calendar.YEAR);
                int month = currentDate.get(Calendar.MONTH);
                int dayOfMonth = currentDate.get(Calendar.DAY_OF_MONTH);
                new SupportedDatePickerDialog(FilterProductLedgerActivity.this, R.style.SpinnerDatePickerDialogTheme, new SupportedDatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        i1=i1+1;
                        fromDate.setText(String.valueOf(i2+"-"+i1+"-"+i));
                        globalFromDate=i+"-"+i1+"-"+i2;

                    }
                }, year, month, dayOfMonth).show();


            }
        });
        toDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar currentDate = Calendar.getInstance();
                int year = currentDate.get(Calendar.YEAR);
                int month = currentDate.get(Calendar.MONTH);
                int dayOfMonth = currentDate.get(Calendar.DAY_OF_MONTH);
                new SupportedDatePickerDialog(FilterProductLedgerActivity.this, R.style.SpinnerDatePickerDialogTheme, new SupportedDatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        i1=i1+1;
                        toDate.setText(String.valueOf(i2+"-"+i1+"-"+i));
                        globalToDate=i+"-"+i1+"-"+i2;

                    }
                }, year, month, dayOfMonth).show();


            }
        });
        new Thread(new Runnable() {
            @Override
            public void run() {
                String s = new GetWarehouse().getData(FilterProductLedgerActivity.this,"1");
                String sm= new GetProducts().getData(FilterProductLedgerActivity.this,"1");
                Gson gson= new Gson();
                Warehouse[] warehouses=gson.fromJson(s,Warehouse[].class);
                Products[] products = gson.fromJson(sm,Products[].class);

                if(s!=null && sm!=null){
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            /*ArrayList<Warehouse> withNullWarehouse = new ArrayList<>(Arrays.asList(warehouses));
                            Warehouse nullvaluewarehouse= new Warehouse();
                            nullvaluewarehouse.setWarehouseID(0);
                            nullvaluewarehouse.setWarehouseName("ALL");
                            withNullWarehouse.add(nullvaluewarehouse);

                            warehouseAdapter sa = new warehouseAdapter(FilterProductLedgerActivity.this,withNullWarehouse);
                            WarehouseSpinner.setAdapter(sa);
                            WarehouseSpinner.setSelectedItem(withNullWarehouse.size()-1);
                            WarehouseSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {
                                @Override
                                public void onItemSelected(View view, int position, long id) {
                                    Warehouse s = (Warehouse) WarehouseSpinner.getSelectedItem();
                                    WarehouseID=String.valueOf(s.getWarehouseID());
                                }

                                @Override
                                public void onNothingSelected() {

                                }
                            });*/
                            ArrayList<Products> withNullProduct= new ArrayList<>(Arrays.asList(products));
                            productsAdapter pma = new productsAdapter(FilterProductLedgerActivity.this,withNullProduct);



                            ProductSpinner.setAdapter(pma);
                            ProductSpinner.setSelectedItem(0);
                            ProductSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {
                                @Override
                                public void onItemSelected(View view, int position, long id) {
                                    Products product=(Products) ProductSpinner.getSelectedItem();
                                    ProductID=String.valueOf(product.getProductID());
                                }

                                @Override
                                public void onNothingSelected() {

                                }
                            });



                        }
                    });

                }
            }
        }).start();


        SubmitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(FilterProductLedgerActivity.this, ProductLedger.class);
                if(fromDate.getText()!=null && !fromDate.getText().toString().trim().equalsIgnoreCase("")) {
                    i.putExtra("FromDate", globalFromDate);
                }else{
                    i.putExtra("FromDate", "");
                }
                if(toDate.getText().toString().trim()!=null && !toDate.getText().toString().trim().equalsIgnoreCase("")) {
                    i.putExtra("ToDate", globalToDate);
                }else {
                    i.putExtra("ToDate", "");
                }
                if(WarehouseID.equalsIgnoreCase("0")) {
                    i.putExtra("WarehouseID", "");
                }
                else{
                    i.putExtra("WarehouseID", WarehouseID);
                }

                if(ProductID.equalsIgnoreCase("0")) {
                    i.putExtra("ProductID", "");
                }
                else{
                    i.putExtra("ProductID", ProductID);
                }

                startActivity(i);


            }
        });

    }
}
