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
import com.hassoft.xinacle.adapter.customerAdapter;
import com.hassoft.xinacle.adapter.productsAdapter;
import com.hassoft.xinacle.adapter.salesmanAdapter;
import com.hassoft.xinacle.apis.GetCustomers;
import com.hassoft.xinacle.apis.GetProducts;
import com.hassoft.xinacle.apis.GetSalesman;
import com.hassoft.xinacle.model.Customers;
import com.hassoft.xinacle.model.Products;
import com.hassoft.xinacle.model.Salesman;
import com.ibotta.android.support.pickerdialogs.SupportedDatePickerDialog;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;


public class FilterSalesTransactionActivity extends AppCompatActivity {
    TextView fromDate;
    TextView toDate;
    SearchableSpinner CustomerSpinner;
    SearchableSpinner  ProductSpinner;
    Button SubmitButton;
    String CustomerID="";
    String ProductID="";
    String globalFromDate="";
    String globalToDate="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.filtersalestransaction);
        fromDate=(TextView)findViewById(R.id.fromDate);
        toDate=(TextView)findViewById(R.id.toDate);
        CustomerSpinner=(SearchableSpinner) findViewById(R.id.listcustomer);
        ProductSpinner=(SearchableSpinner) findViewById(R.id.listproduct);

        SubmitButton=(Button)findViewById(R.id.submitFilter);
        final SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        Calendar c = Calendar.getInstance();

       // toDate.setText(sdf.format(c.getTime()));
        Calendar fd=Calendar.getInstance();
        fd.set(Calendar.MONTH,c.getTime().getMonth());
        fd.set(Calendar.DAY_OF_MONTH,1);
        fd.set(Calendar.YEAR,c.getTime().getYear()+1900);
      //  fromDate.setText(sdf.format(fd.getTime()));

        globalFromDate=sdf.format(fd.getTime());
        globalToDate=sdf.format(c.getTime());
        fromDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar currentDate = Calendar.getInstance();
                int year = currentDate.get(Calendar.YEAR);
                int month = currentDate.get(Calendar.MONTH);
                int dayOfMonth = currentDate.get(Calendar.DAY_OF_MONTH);
                new SupportedDatePickerDialog(FilterSalesTransactionActivity.this, R.style.SpinnerDatePickerDialogTheme, new SupportedDatePickerDialog.OnDateSetListener() {
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
                new SupportedDatePickerDialog(FilterSalesTransactionActivity.this, R.style.SpinnerDatePickerDialogTheme, new SupportedDatePickerDialog.OnDateSetListener() {
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
                String s = new GetCustomers().getData(FilterSalesTransactionActivity.this,"1");
                String sm= new GetProducts().getData(FilterSalesTransactionActivity.this,"1");
                Gson gson= new Gson();
                Customers[] customers=gson.fromJson(s,Customers[].class);
                Products[] products = gson.fromJson(sm,Products[].class);

                if(s!=null && sm!=null){
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            ArrayList<Customers> withNullCustomer = new ArrayList<>(Arrays.asList(customers));
                            Customers nullvaluecustomer= new Customers();
                            nullvaluecustomer.setCustomerID(0);
                            nullvaluecustomer.setCustomerName("All");
                            withNullCustomer.add(0,nullvaluecustomer);

                            customerAdapter ca = new customerAdapter(FilterSalesTransactionActivity.this,withNullCustomer);
                            CustomerSpinner.setAdapter(ca);
                            CustomerSpinner.setSelectedItem(withNullCustomer.size()-1);
                            CustomerSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {
                                @Override
                                public void onItemSelected(View view, int position, long id) {
                                    Customers c = (Customers)CustomerSpinner.getSelectedItem();
                                    CustomerID=String.valueOf(c.getCustomerID());
                                }

                                @Override
                                public void onNothingSelected() {

                                }
                            });
                            ArrayList<Products> withNullProduct= new ArrayList<>(Arrays.asList(products));
                            Products nullproduct=new Products();

                            nullproduct.setProductID(0);;
                            nullproduct.setProductName("All");
                            withNullProduct.add(0,nullproduct);
                            productsAdapter pma = new productsAdapter(FilterSalesTransactionActivity.this,withNullProduct);



                            ProductSpinner.setAdapter(pma);
                            ProductSpinner.setSelectedItem(withNullProduct.size()-1);
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
                Intent i = new Intent(FilterSalesTransactionActivity.this,SalesTransactionActivity.class);
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
                if(CustomerID.equalsIgnoreCase("0")) {
                    i.putExtra("CustomerID", "");
                }
                else{
                    i.putExtra("CustomerID", CustomerID);
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
