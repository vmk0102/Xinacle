package com.hassoft.xinacle.activities;

import android.app.ProgressDialog;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.google.gson.Gson;
import com.hassoft.xinacle.R;
import com.hassoft.xinacle.apis.GetDailySales;
import com.hassoft.xinacle.model.DailySales;
import com.hassoft.xinacle.model.PurchaseMaster;
import com.hassoft.xinacle.model.SalesSummary;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class DailySalesChart extends AppCompatActivity {
    LineChart mChart;
    DailySales[] sa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.salessummarychart);
        mChart=(LineChart) findViewById(R.id.salessummmarychart);


        mChart.setPinchZoom(true);
        mChart.animateXY(3000,3000);
        ProgressDialog pd = new ProgressDialog(DailySalesChart.this);
        pd.setMessage("Loading please wait");
        pd.show();
        new Thread(new Runnable() {
            @Override
            public void run() {
              String  ds=new GetDailySales().getData(DailySalesChart.this,getIntent().getStringExtra("FromDate"),getIntent().getStringExtra("ToDate"),1);
              runOnUiThread(new Runnable() {
                  @Override
                  public void run() {
                      pd.cancel();
                      Gson gson = new Gson();
                      sa = gson.fromJson(ds,DailySales[].class);
                      
                      ArrayList<ILineDataSet> set1=new ArrayList<>();
                      LineDataSet dataset1 = new LineDataSet(dataSet1(sa),"Sales Summary");
                      prepareData(dataset1,Color.BLUE);
                      ArrayList<String> dates= new ArrayList<>();
                      fillData(dates,sa);

                      XAxis xAxis = mChart.getXAxis();
                      xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
                      xAxis.setGranularity(1f); // minimum axis-step (interval) is 1
                      xAxis.setValueFormatter(new IndexAxisValueFormatter(dates));
                      set1.add(dataset1);
                      LineData fulldataset= new LineData(set1);
                      mChart.setData(fulldataset);


                  }
              });
            }
        }).start();






    }

    public ArrayList<Entry> dataSet1(DailySales[] sa){

        ArrayList<Entry> values = new ArrayList<>();
        int i =0;
        for(DailySales s : sa){

            values.add(new Entry(i, Float.parseFloat(String.valueOf(s.getTotalSales()))));
            i++;

        }
        return values;

    }
    public void fillData(ArrayList<String> dates,DailySales[] sa){
        for(DailySales s : sa){
            dates.add(s.getEDate().toString());
        }
    }


    public  void prepareData(LineDataSet set1,int c){

            set1.setDrawIcons(false);
           // set1.enableDashedLine(10f, 5f, 0f);
           // set1.enableDashedHighlightLine(10f, 5f, 0f);
            set1.setColor(c);
            set1.setCircleColor(c);
            set1.setLineWidth(2f);
            set1.setCircleRadius(3f);
            set1.setDrawCircleHole(false);
            set1.setValueTextSize(9f);
            set1.setDrawFilled(true);
            set1.setFormLineWidth(1f);
            set1.setFormLineDashEffect(new DashPathEffect(new float[]{10f, 5f}, 0f));
            set1.setFormSize(15.f);
            set1.setFillColor(Color.WHITE);

        }

}
