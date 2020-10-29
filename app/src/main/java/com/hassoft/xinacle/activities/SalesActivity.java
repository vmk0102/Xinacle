package com.hassoft.xinacle.activities;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.hassoft.xinacle.R;
import com.hassoft.xinacle.adapter.optionsAdapter;

public class SalesActivity extends Activity {

    GridView lv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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