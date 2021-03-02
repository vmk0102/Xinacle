package com.hassoft.xinacle.activities;

import android.content.Context;
import android.content.SharedPreferences;
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
import com.hassoft.xinacle.model.PurchaseMaster;
import com.hassoft.xinacle.model.SalesSummary;

import java.util.ArrayList;

public class SalesSummaryChart extends AppCompatActivity {
    LineChart mChart;
    SalesSummary[] sa;
    PurchaseMaster[] pa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.salessummarychart);
        mChart=(LineChart) findViewById(R.id.salessummmarychart);


        mChart.setPinchZoom(true);
        mChart.animateXY(3000,3000);




        ArrayList<ILineDataSet> set1=new ArrayList<>();
        ArrayList<Entry> ds = dataSet1();
        if(ds!=null) {
            LineDataSet dataset1 = new LineDataSet(ds, "Sales Summary");
            prepareData(dataset1, Color.BLUE);
            ArrayList<String> dates = new ArrayList<>();
            fillData(dates);

            XAxis xAxis = mChart.getXAxis();
            xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
            xAxis.setGranularity(1f); // minimum axis-step (interval) is 1
            xAxis.setValueFormatter(new IndexAxisValueFormatter(dates));
            set1.add(dataset1);
            LineData fulldataset = new LineData(set1);
            mChart.setData(fulldataset);
        }
    }

    public ArrayList<Entry> dataSet1(){
        SharedPreferences sf = getApplicationContext().getSharedPreferences("mypref", Context.MODE_PRIVATE);
        String st = sf.getString("SalesSummaryData",null);
                if(st!=null) {
                    sa = new Gson().fromJson(st, SalesSummary[].class);
                    ArrayList<Entry> values = new ArrayList<>();
                    int i = 0;
                    for (SalesSummary s : sa) {

                        values.add(new Entry(i, Float.parseFloat(String.valueOf(s.getNetAmount()))));
                        i++;

                    }
                    return values;
                }
                return null;

    }
    public void fillData(ArrayList<String> dates){
        for(SalesSummary s : sa){
            dates.add(s.getEDate().toString());
        }
    }
    public ArrayList<Entry> dataSet2(){
        pa = new Gson().fromJson(String.valueOf(getIntent().getStringExtra("netpurchases")), PurchaseMaster[].class);
        ArrayList<Entry> values = new ArrayList<>();
        int i =1;
        for(PurchaseMaster p : pa){
            values.add(new Entry(Float.parseFloat(p.getEDate().toString().replace("-","")), Float.parseFloat(String.valueOf(p.getNetAmount()))));
            i++;
        }
        return values;

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
