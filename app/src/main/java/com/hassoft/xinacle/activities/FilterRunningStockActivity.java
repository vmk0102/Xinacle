package com.hassoft.xinacle.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.androidtrip.plugins.searchablespinner.SearchableSpinner;
import com.androidtrip.plugins.searchablespinner.interfaces.OnItemSelectedListener;
import com.google.gson.Gson;
import com.hassoft.xinacle.R;
import com.hassoft.xinacle.adapter.customerAdapter;
import com.hassoft.xinacle.adapter.productsAdapter;
import com.hassoft.xinacle.apis.GetCustomers;
import com.hassoft.xinacle.apis.GetProducts;
import com.hassoft.xinacle.model.Customers;
import com.hassoft.xinacle.model.Products;
import com.ibotta.android.support.pickerdialogs.SupportedDatePickerDialog;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;


public class FilterRunningStockActivity extends AppCompatActivity {
    TextView fromDate;
    TextView toDate;
    SearchableSpinner CustomerSpinner;
    SearchableSpinner  ProductSpinner;
    Button SubmitButton;
    String CustomerID="";
    String ProductName="";
    String globalFromDate="";
    String globalToDate="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.filterrunningstock);

        ProductSpinner=(SearchableSpinner) findViewById(R.id.listproduct);

        SubmitButton=(Button)findViewById(R.id.submitFilter);


        new Thread(new Runnable() {
            @Override
            public void run() {
                String sm= new GetProducts().getData(FilterRunningStockActivity.this,"1");
                Gson gson= new Gson();
                Products[] products = gson.fromJson(sm,Products[].class);

                if(sm!=null && sm!=null){
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            final ArrayList<Products> withNullProduct= new ArrayList<>(Arrays.asList(products));
                            Products nullproduct=new Products();

                            nullproduct.setProductID(0);;
                            nullproduct.setProductName("All");
                            withNullProduct.add(0,nullproduct);
                            productsAdapter pma = new productsAdapter(FilterRunningStockActivity.this,withNullProduct);



                            ProductSpinner.setAdapter(pma);
                            ProductSpinner.setSelectedItem(withNullProduct.size()-1);
                            ProductSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {
                                @Override
                                public void onItemSelected(View view, int position, long id) {
                                    Products product=(Products)withNullProduct.get(ProductSpinner.getSelectedPosition());

                                    ProductName=String.valueOf(product.getProductName());
                                    Log.d("ding dong bell",product.getProductName());
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
                Intent i = new Intent(FilterRunningStockActivity.this,RunningStockActivity.class);

                if(ProductName.equalsIgnoreCase("")) {
                    Toast.makeText(FilterRunningStockActivity.this, "Please select Product", Toast.LENGTH_SHORT).show();
                    return;
                }
                else{
                    i.putExtra("ProductNameRunningStock", ProductName);
                }

                startActivity(i);


            }
        });

    }
}
