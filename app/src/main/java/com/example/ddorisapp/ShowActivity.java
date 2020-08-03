package com.example.ddorisapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import java.io.File;

public class ShowActivity extends AppCompatActivity {
    Button btnPrev, btnNext;
    picturesView pictures;
    int curNum;
    File[] imageFiles;
    String imageFname;
    TextView textView1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_main);
        setTitle("또리의 앱");
        ActivityCompat.requestPermissions(this,new String[] {
                android.Manifest.permission.WRITE_EXTERNAL_STORAGE}, MODE_PRIVATE);
       //연결하기
        btnPrev = (Button) findViewById(R.id.btnPrev);
        btnNext = (Button) findViewById(R.id.btnNext);
        pictures= (picturesView) findViewById(R.id.myPictureView1);
        textView1=(TextView)findViewById(R.id.textview1);

        //이미지파일패스
        String fileDirectory= getFilesDir().toString();
        String fullFileDirectory = fileDirectory+"/images";
        System.out.println(fullFileDirectory);
        imageFiles = new File(fullFileDirectory).listFiles();

        imageFname = imageFiles[0].toString();
        pictures.imagePath = imageFname;

        btnPrev.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if(curNum<=1) {
                    curNum=imageFiles.length;
                    imageFname=imageFiles[curNum-1].toString();
                    pictures.imagePath=imageFname;
                    pictures.invalidate();
                    textView1.setText(String.valueOf(curNum)+"/"+String.valueOf(imageFiles.length));
                }
                else {
                    curNum--;
                    imageFname=imageFiles[curNum-1].toString();
                    pictures.imagePath=imageFname;
                    pictures.invalidate();
                    textView1.setText(String.valueOf(curNum)+"/"+String.valueOf(imageFiles.length));
                }
            }
        });
        btnNext.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (curNum >= imageFiles.length) {
                    curNum = 1;
                    imageFname = imageFiles[curNum - 1].toString();
                    pictures.imagePath = imageFname;
                    pictures.invalidate();
                    textView1.setText(String.valueOf(curNum) + "/" + String.valueOf(imageFiles.length));
                } else {
                    curNum++;
                    imageFname = imageFiles[curNum - 1].toString();
                    pictures.imagePath = imageFname;
                    pictures.invalidate();
                    textView1.setText(String.valueOf(curNum) + "/" + String.valueOf(imageFiles.length));
                }
            }
        });
    }
}

