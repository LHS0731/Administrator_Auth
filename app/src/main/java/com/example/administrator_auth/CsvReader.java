package com.example.administrator_auth;

import android.content.Context;
import android.util.Log;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class CsvReader implements Serializable {
    List<ListData> objects = new ArrayList<ListData>();


    public void reader(Context context, String whatfiless) {
        String whatfiles = whatfiless;
        try {
            InputStream inputStream = new FileInputStream("/sdcard/Android/data/com.example.administrator_auth/files/gustjq2536@gmail.com/"+whatfiles);
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferReader = new BufferedReader(inputStreamReader);
            String line;
            while ((line = bufferReader.readLine()) != null) {
                ListData data = new ListData();
                String[] RowData = line.split(",");//
                data.setname(RowData[0]);
                data.setprice(RowData[1]);
                data.setEA(RowData[2]);
                data.setamount(RowData[3]);

                Log.d("deb", RowData[0]);
                objects.add(data);
            }
            bufferReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

