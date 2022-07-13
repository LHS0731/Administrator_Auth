package com.example.administrator_auth;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DownloadManager;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;


public class ReceiptViewActivity extends AppCompatActivity {

    private String[] files;
    int length;
    int n = 0;
    String whatfiles_view;
    Button pre, nex, rst;
    EditText whatcsv;

    FirebaseStorage firebaseStorage;
    StorageReference storageReference;
    StorageReference ref1, ref2, ref3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receipt_view);

        whatcsv = findViewById(R.id.ttext);

        File myspace = new File("/sdcard/Android/data/com.example.administrator_auth/files/gustjq2536@gmail.com/");
        files = myspace.list();
        length = files.length;

        whatfiles_view = files[n];
        csvreading();

        whatcsv.setText(whatfiles_view);

        pre = findViewById(R.id.previous);
        pre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                n--;
                if(n<0){
                    n=length-1;
                    whatfiles_view = files[n];
                    csvreading();
                    whatcsv.setText(whatfiles_view);
                }
                whatfiles_view = files[n];
                csvreading();
                whatcsv.setText(whatfiles_view);
            }
        });

        nex = findViewById(R.id.next);
        nex.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                n++;
                if(n>length-1){
                    n=0;
                    whatfiles_view = files[n];
                    csvreading();
                    whatcsv.setText(whatfiles_view);
                }
                whatfiles_view = files[n];
                csvreading();
                whatcsv.setText(whatfiles_view);
            }
        });

        rst = findViewById(R.id.rst);
        rst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                download();
            }
        });

    }
    public void csvreading(){
        whatfiles_view = files[n];
        CsvReader parser = new CsvReader();
        parser.reader(getApplicationContext(),whatfiles_view);
        //CsvReader내 reader메서드 실행, 이때 파일명을 입력함.
        ListViewAdapter listViewAdapter = new ListViewAdapter(ReceiptViewActivity.this, 0, parser.objects);
        ListView listView = (ListView)findViewById(R.id.list);
        listView.setAdapter(listViewAdapter);
    }

    public void download(){

        storageReference=firebaseStorage.getInstance().getReference();
        ref1=storageReference.child("gustjq2536@gmail.com/shopping.csv");
        ref2=storageReference.child("gustjq2536@gmail.com/shopping2.csv");
        ref3=storageReference.child("gustjq2536@gmail.com/shopping3.csv");
        ref1.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {


                String url = uri.toString();
                downloadFiles(ReceiptViewActivity.this, "shopping", ".csv", "/gustjq2536@gmail.com", url);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

            }
        });
        ref2.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {


                String url = uri.toString();
                downloadFiles(ReceiptViewActivity.this, "shopping2", ".csv", "/gustjq2536@gmail.com", url);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

            }
        });
        ref3.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {


                String url = uri.toString();
                downloadFiles(ReceiptViewActivity.this, "shopping3", ".csv", "/gustjq2536@gmail.com", url);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

            }
        });

    }

    public void downloadFiles(Context context, String fileName, String fileExtension, String destinationDirectory, String url){
        DownloadManager downloadManager = (DownloadManager) context.
                getSystemService(Context.DOWNLOAD_SERVICE);
        Uri uri = Uri.parse(url);
        DownloadManager.Request request = new DownloadManager.Request(uri);

        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        request.setDestinationInExternalFilesDir(context, destinationDirectory, fileName + fileExtension);

        downloadManager.enqueue(request);
    }
}