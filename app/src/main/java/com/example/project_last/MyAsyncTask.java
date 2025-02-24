package com.example.project_last;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

class MyAsyncTask extends AsyncTask<String, String, String> {
    //Context context;
    String title;
    private AsyncCallback asyncCallback;
    static String clientId = "";
    static String clientSecret = "";
    ArrayList<item_getset> list = new ArrayList<item_getset>();

    public MyAsyncTask(String title, AsyncCallback asyncCallback) {
        this.title = title;
        this.asyncCallback = asyncCallback;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(String response) {
        System.out.println(response);

        super.onPostExecute(response);
        asyncCallback.onSuccess(response);
    }

    @Override
    protected void onProgressUpdate(String... result) {
        super.onProgressUpdate(result[0]);
    }



    @Override
    protected String doInBackground(String... strings) {

        StringBuffer response = new StringBuffer();

        try {
            String apiURL = "https://openapi.naver.com/v1/search/movie?query=" + title;
            URL url = new URL(apiURL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestProperty("X-Naver-Client-Id", clientId);
            conn.setRequestProperty("X-Naver-Client-Secret", clientSecret);
            int responseCode = conn.getResponseCode();
            BufferedReader br;
            if (responseCode == 200) {
                br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            } else {
                br = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
            }
            String inputLine;
            while ((inputLine = br.readLine()) != null) {
                response.append(inputLine);
            }
            br.close();

        } catch (Exception e) {
            e.printStackTrace();
        }



        return String.valueOf(response);
    }
}
