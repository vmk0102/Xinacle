package com.hassoft.xinacle.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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
import com.hassoft.xinacle.adapter.salesmanAdapter;
import com.hassoft.xinacle.apis.GetCustomers;
import com.hassoft.xinacle.apis.GetDailySales;
import com.hassoft.xinacle.apis.GetSalesman;
import com.hassoft.xinacle.model.Customers;
import com.hassoft.xinacle.model.Salesman;
import com.ibotta.android.support.pickerdialogs.SupportedDatePickerDialog;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;


public class FilterDailySales extends AppCompatActivity {
    TextView fromDate;
    TextView toDate;
    SearchableSpinner CustomerSpinner;
    SearchableSpinner  SalemanSpinner;
    Button SubmitButton;
    String CustomerID="";
    String SalemanID="";
    String globalFromDate="";
    String globalToDate="";
    String ds="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.filterdailysales);
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

                new SupportedDatePickerDialog(FilterDailySales.this, R.style.SpinnerDatePickerDialogTheme, new SupportedDatePickerDialog.OnDateSetListener() {
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
                new SupportedDatePickerDialog(FilterDailySales.this, R.style.SpinnerDatePickerDialogTheme, new SupportedDatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        i1=i1+1;
                        toDate.setText(String.valueOf(i2+"-"+i1+"-"+i));
                        globalToDate=i+"-"+i1+"-"+i2;

                    }
                }, year, month, dayOfMonth).show();


            }
        });



        SubmitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(FilterDailySales.this,DailySalesChart.class);
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
                Log.v("boom chinga",ds);



                startActivity(i);


            }
        });

    }
}
