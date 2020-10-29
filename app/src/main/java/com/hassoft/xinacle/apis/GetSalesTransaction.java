package com.hassoft.xinacle.apis;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.hassoft.xinacle.R;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;

public class GetSalesTransaction {

    public String getData(Context context,String fromDate, String toDate, int CustomerID, int CompanyBranchID) {

        try {

            String getUrl = context.getResources().getString(R.string.apilinkprefix)+"Sales/GetSalesTransaction?FromDate=2019-01-01&ToDate=2020-12-12&CustomerID=1&ProductID=2&CompanyBranchID=1";//https://www.pakistanscrabble.org/api.php";
            Log.v("SOMEONE ONCE SAID", "postURL: " + getUrl);

            HttpClient httpClient = new DefaultHttpClient();

            HttpGet httpGet = new HttpGet(getUrl);


            HttpResponse response = httpClient.execute(httpGet);
            HttpEntity resEntity = response.getEntity();

            if (resEntity != null) {

                String responseStr = EntityUtils.toString(resEntity).trim();
                responseStr=responseStr.replace("\\","");
                responseStr=responseStr.substring(1,responseStr.length()-1);
                return  responseStr;


                // you can add an if statement here and do other actions based on the response
            } else {
                return null;
            }
        } catch (Exception e) {
            Log.v("exception",e.getMessage());
            return null;

        }
    }
}
