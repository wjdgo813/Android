package com.example.lg.firstgdg2;

import android.os.AsyncTask;
import android.util.Log;

import java.util.ArrayList;

public class OpenRestAPITast extends AsyncTask<String,Void,ArrayList> {
    @Override
    public ArrayList doInBackground(String... params) {

        RestAPIClient client = new RestAPIClient();
        ArrayList dataList = client.getData(params[0]);


        return dataList;

    }

}
