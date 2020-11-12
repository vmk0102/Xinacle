package com.hassoft.xinacle.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.hassoft.xinacle.R;
import com.hassoft.xinacle.adapter.customerAdapter;
import com.hassoft.xinacle.adapter.suppliersAdapter;
import com.hassoft.xinacle.apis.GetCustomers;
import com.hassoft.xinacle.apis.GetSalesman;
import com.hassoft.xinacle.apis.GetSupplier;
import com.hassoft.xinacle.model.Customers;
import com.hassoft.xinacle.model.PurchaseMaster;
import com.hassoft.xinacle.model.Salesman;
import com.hassoft.xinacle.model.Supplier;
import com.ibotta.android.support.pickerdialogs.SupportedDatePickerDialog;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;


public class FilterPurchaseMasterActivity extends AppCompatActivity {
    TextView fromDate;
    TextView toDate;
    Spinner SupplierSpinner;
    Button SubmitButton;
    String SupplierID="";
    SimpleDateFormat sdf;
    String globalFromDate;
    String globalToDate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.filterpurchasemaaster);
        sdf=new SimpleDateFormat("yyyy-MM-dd");
        fromDate=(TextView)findViewById(R.id.fromDate);
        toDate=(TextView)findViewById(R.id.toDate);
        SupplierSpinner=(Spinner)findViewById(R.id.listsupplier);

        SubmitButton=(Button)findViewById(R.id.submitFilter);
        final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();

        toDate.setText(sdf.format(c.getTime()));
        Calendar fd=Calendar.getInstance();
        fd.set(Calendar.MONTH,c.getTime().getMonth());
        fd.set(Calendar.DAY_OF_MONTH,1);
        fd.set(Calendar.YEAR,c.getTime().getYear()+1900);
        fromDate.setText(sdf.format(fd.getTime()));

        globalFromDate=sdf.format(fd.getTime());
        globalToDate=sdf.format(c.getTime());

        fromDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Calendar currentDate = Calendar.getInstance();
                int year = currentDate.get(Calendar.YEAR);
                int month = currentDate.get(Calendar.MONTH);
                int dayOfMonth = currentDate.get(Calendar.DAY_OF_MONTH);
               new SupportedDatePickerDialog(FilterPurchaseMasterActivity.this, R.style.SpinnerDatePickerDialogTheme, new SupportedDatePickerDialog.OnDateSetListener() {
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
                new SupportedDatePickerDialog(FilterPurchaseMasterActivity.this, R.style.SpinnerDatePickerDialogTheme, new SupportedDatePickerDialog.OnDateSetListener() {
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
                String s = new GetSupplier().getData(FilterPurchaseMasterActivity.this,"1");

                if(s!=null){
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Gson gson= new Gson();
                            Supplier[] suppliers=gson.fromJson(s,Supplier[].class);
                            ArrayList<Supplier> withNullSupplier = new ArrayList<>(Arrays.asList(suppliers));
                            Supplier nullvalueSupplier= new Supplier();
                            nullvalueSupplier.setSupplierID(0);
                            nullvalueSupplier.setSupplierName("ALL");
                            withNullSupplier.add(nullvalueSupplier);

                            suppliersAdapter sa  = new suppliersAdapter(FilterPurchaseMasterActivity.this,withNullSupplier);
                            SupplierSpinner.setAdapter(sa);
                            SupplierSpinner.setSelection(withNullSupplier.size()-1);
                            SupplierSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                @Override
                                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                    Supplier sp =(Supplier) SupplierSpinner.getItemAtPosition(position);
                                    SupplierID=String.valueOf(sp.getSupplierID());

                                }

                                @Override
                                public void onNothingSelected(AdapterView<?> parent) {

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
                Intent i = new Intent(FilterPurchaseMasterActivity.this, purchaseSummaryActivity.class);
                if(fromDate.getText()!=null && !fromDate.getText().toString().trim().equalsIgnoreCase("")) {
                    i.putExtra("FromDate", globalFromDate.toString());
                }else{
                    i.putExtra("FromDate", "");
                }
                if(toDate.getText().toString().trim()!=null && !toDate.getText().toString().trim().equalsIgnoreCase("")) {
                    i.putExtra("ToDate", globalToDate.toString());
                }else {
                    i.putExtra("ToDate", "");
                }

                if(SupplierID.equalsIgnoreCase("0")) {
                    i.putExtra("SupplierID", "");
                }
                else{
                    i.putExtra("Supplier", SupplierID);
                }

                startActivity(i);

            }
        });

    }
}
