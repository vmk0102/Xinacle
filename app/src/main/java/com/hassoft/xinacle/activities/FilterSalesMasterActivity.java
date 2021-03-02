package com.hassoft.xinacle.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Filter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.androidtrip.plugins.searchablespinner.SearchableSpinner;
import com.androidtrip.plugins.searchablespinner.interfaces.OnItemSelectedListener;
import com.google.gson.Gson;
import com.hassoft.xinacle.R;
import com.hassoft.xinacle.adapter.customerAdapter;
import com.hassoft.xinacle.adapter.salesmanAdapter;
import com.hassoft.xinacle.apis.GetCustomers;
import com.hassoft.xinacle.apis.GetSalesman;
import com.hassoft.xinacle.model.Customers;
import com.hassoft.xinacle.model.Salesman;
import com.ibotta.android.support.pickerdialogs.SupportedDatePickerDialog;

import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;


public class FilterSalesMasterActivity extends AppCompatActivity {
    TextView fromDate;
    TextView toDate;
    SearchableSpinner CustomerSpinner;
    SearchableSpinner  SalemanSpinner;
    Button SubmitButton;
    String CustomerID="";
    String SalemanID="";
    String globalFromDate="";
    String globalToDate="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.filtersalesmaaster);
        fromDate=(TextView)findViewById(R.id.fromDate);
        toDate=(TextView)findViewById(R.id.toDate);
        CustomerSpinner=(SearchableSpinner) findViewById(R.id.listcustomer);
        SalemanSpinner=(SearchableSpinner) findViewById(R.id.listsaleman);

        SubmitButton=(Button)findViewById(R.id.submitFilter);
        final SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        Calendar c = Calendar.getInstance();

    //  toDate.setText(sdf.format(c.getTime()));
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
                new SupportedDatePickerDialog(FilterSalesMasterActivity.this, R.style.SpinnerDatePickerDialogTheme, new SupportedDatePickerDialog.OnDateSetListener() {
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
                new SupportedDatePickerDialog(FilterSalesMasterActivity.this, R.style.SpinnerDatePickerDialogTheme, new SupportedDatePickerDialog.OnDateSetListener() {
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
                String s = new GetCustomers().getData(FilterSalesMasterActivity.this,"1");
                String sm= new GetSalesman().getData(FilterSalesMasterActivity.this,"1");

                if(s!=null){
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Gson gson= new Gson();
                            Customers[] customers=gson.fromJson(s,Customers[].class);
                            ArrayList<Customers> withNullCustomer = new ArrayList<>(Arrays.asList(customers));
                            Customers nullvaluecustomer= new Customers();
                            nullvaluecustomer.setCustomerID(0);
                            nullvaluecustomer.setCustomerName("All");
                            withNullCustomer.add(0,nullvaluecustomer);

                            customerAdapter ca = new customerAdapter(FilterSalesMasterActivity.this,withNullCustomer);
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
                            Salesman[] salesmen = gson.fromJson(sm,Salesman[].class);
                            ArrayList<Salesman> withNullSaleman= new ArrayList<>(Arrays.asList(salesmen));
                            Salesman nullsaleman=new Salesman();

                            nullsaleman.setSalesmanID(0);
                            nullsaleman.setSalesmanName("All");
                            withNullSaleman.add(0,nullsaleman);
                            salesmanAdapter sma = new salesmanAdapter(FilterSalesMasterActivity.this,withNullSaleman);



                            SalemanSpinner.setAdapter(sma);
                            SalemanSpinner.setSelectedItem(withNullSaleman.size()-1);
                            SalemanSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {
                                @Override
                                public void onItemSelected(View view, int position, long id) {
                                    Salesman salesman=(Salesman)SalemanSpinner.getSelectedItem();
                                    SalemanID=String.valueOf(salesman.getSalesmanID());
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
                Intent i = new Intent(FilterSalesMasterActivity.this,SalesSummaryActivity.class);
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


                if(SalemanID.equalsIgnoreCase("0")) {
                    i.putExtra("Salesman", "");
                }
                else{
                    i.putExtra("Salesman", SalemanID);
                }

                startActivity(i);


            }
        });

    }
}
