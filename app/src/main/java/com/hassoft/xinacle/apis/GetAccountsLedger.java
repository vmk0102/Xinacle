package com.hassoft.xinacle.apis;

import android.content.Context;
import android.util.Log;

import com.hassoft.xinacle.GlobalFunctions;
import com.hassoft.xinacle.R;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

public class GetAccountsLedger {

    public String getData(Context context, String fromDate, String toDate, String AccountCode, String CompanyBranchID) {
        if (GlobalFunctions.isInternetAvailable(context)) {
            try {

                String getUrl = context.getResources().getString(R.string.apilinkprefix) + "Ledgers?FromDate=" + fromDate + "&ToDate=" + toDate + "&AccountCode=" + AccountCode + "&CompanyBranchID=" + CompanyBranchID;//https://www.pakistanscrabble.org/api.php";
                Log.v("SOMEONE ONCE SAID", "postURL: " + getUrl);

                HttpClient httpClient = new DefaultHttpClient();

                HttpGet httpGet = new HttpGet(getUrl);


                HttpResponse response = httpClient.execute(httpGet);
                HttpEntity resEntity = response.getEntity();

                if (resEntity != null) {

                    String responseStr = EntityUtils.toString(resEntity).trim();
                    responseStr = responseStr.replace("\\", "");
                    responseStr = responseStr.substring(1, responseStr.length() - 1);
                    return responseStr;


                    // you can add an if statement here and do other actions based on the response
                } else {
                    return null;
                }
            } catch (Exception e) {
                Log.v("exception", e.getMessage());
                return null;

            }
        }else {
            return null;
        }

    }
}
