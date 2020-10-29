package com.hassoft.xinacle.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.hassoft.xinacle.R;
import com.hassoft.xinacle.adapter.customerAdapter;
import com.hassoft.xinacle.adapter.salesmanAdapter;
import com.hassoft.xinacle.apis.GetCustomers;
import com.hassoft.xinacle.apis.GetSalesman;
import com.hassoft.xinacle.model.Customers;
import com.hassoft.xinacle.model.Salesman;

import java.text.SimpleDateFormat;


public class FilterSalesMasterActivity extends AppCompatActivity {
    TextView fromDate;
    TextView toDate;
    Spinner CustomerSpinner;
    Spinner SalemanSpinner;
    Button SubmitButton;
    String CustomerID="";
    String SalemanID="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.filtersalesmaaster);
        fromDate=(TextView)findViewById(R.id.fromDate);
        toDate=(TextView)findViewById(R.id.toDate);
        CustomerSpinner=(Spinner)findViewById(R.id.listcustomer);
        SalemanSpinner=(Spinner)findViewById(R.id.listsaleman);

        SubmitButton=(Button)findViewById(R.id.submitFilter);
        final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        fromDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            /*    new SingleDateAndTimePickerDialog.Builder(FilterSalesMasterActivity.this)
                        //.bottomSheet()
                        //.curved()
                        //.stepSizeMinutes(15)
                        //.displayHours(false)
                        //.displayMinutes(false)
                        //.todayText("aujourd'hui")
                        .displayListener(new SingleDateAndTimePickerDialog.DisplayListener() {
                            @Override
                            public void onDisplayed(SingleDateAndTimePicker picker) {
                                // Retrieve the SingleDateAndTimePicker
                            }

                            public void onClosed(SingleDateAndTimePicker picker) {
                                // On dialog closed
                            }
                        })
                        .title("Simple")
                        .displayMinutes(false)
                        .displayAmPm(false)
                        .displayHours(false)
                        .displayYears(true)

                        .listener(new SingleDateAndTimePickerDialog.Listener() {
                            @Override
                            public void onDateSelected(Date date) {
                                fromDate.setText(sdf.format(date));

                            }
                        }).display();*/

            }
        });
        toDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                /*new SingleDateAndTimePickerDialog.Builder(FilterSalesMasterActivity.this)
                        //.bottomSheet()
                        //.curved()
                        //.stepSizeMinutes(15)
                        //.displayHours(false)
                        //.displayMinutes(false)
                        //.todayText("aujourd'hui")
                        .displayListener(new SingleDateAndTimePickerDialog.DisplayListener() {
                            @Override
                            public void onDisplayed(SingleDateAndTimePicker picker) {
                                // Retrieve the SingleDateAndTimePicker
                            }

                            public void onClosed(SingleDateAndTimePicker picker) {
                                // On dialog closed
                            }
                        })
                        .title("Simple")
                        .displayYears(true)
                        .displayMinutes(false)
                        .displayAmPm(false)
                        .displayHours(false)


                        .listener(new SingleDateAndTimePickerDialog.Listener() {
                            @Override
                            public void onDateSelected(Date date) {
                                toDate.setText(sdf.format(date));

                            }
                *///        }).display();

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
                            customerAdapter ca = new customerAdapter(FilterSalesMasterActivity.this,customers);
                            CustomerSpinner.setAdapter(ca);
                            CustomerSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                @Override
                                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                    Customers c =(Customers) CustomerSpinner.getItemAtPosition(position);
                                    CustomerID=String.valueOf(c.getCustomerID());

                                }

                                @Override
                                public void onNothingSelected(AdapterView<?> parent) {

                                }
                            });
                            Salesman[] salesmen = gson.fromJson(sm,Salesman[].class);
                            salesmanAdapter sma = new salesmanAdapter(FilterSalesMasterActivity.this,salesmen);
                            SalemanSpinner.setAdapter(sma);
                            SalemanSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                @Override
                                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                    Salesman sm = (Salesman)SalemanSpinner.getItemAtPosition(position);
                                    SalemanID=String.valueOf(sm.getSalesmanID());
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
                Intent i = new Intent(FilterSalesMasterActivity.this,SalesSummaryActivity.class);
                if(fromDate.getText()!=null && !fromDate.getText().toString().trim().equalsIgnoreCase("")) {
                    i.putExtra("FromDate", fromDate.getText().toString());
                }else{
                    i.putExtra("FromDate", "");
                }
                if(toDate.getText().toString().trim()!=null && !toDate.getText().toString().trim().equalsIgnoreCase("")) {
                    i.putExtra("ToDate", toDate.getText().toString());
                }else {
                    i.putExtra("ToDate", "");
                }
                i.putExtra("CustomerID",CustomerID);
                i.putExtra("Salesman",SalemanID);
                startActivity(i);

            }
        });

    }
}
