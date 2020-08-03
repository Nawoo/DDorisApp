package com.example.ddorisapp;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ModifyActivity extends AppCompatActivity {
    Button modButton,cancalButton;
    DatePickerDialog.OnDateSetListener callbackMethod;
    TextView textView_Date;
    EditText age,characteristic;
    RatingBar ratingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.modify_main);
        setTitle("또리의 앱");
        //연결하기
        age = (EditText) findViewById(R.id.age);
        characteristic = (EditText) findViewById(R.id.characteristic);
        modButton = (Button) findViewById(R.id.modButton);
        cancalButton = (Button) findViewById(R.id.cancelButton);
        textView_Date = (TextView) findViewById(R.id.txtdate);
        ratingBar = (RatingBar) findViewById(R.id.feeling);
        this.InitializeListener();
        //취소하기버튼
        cancalButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(ModifyActivity.this);
                builder.setTitle("안내창!!!").setMessage("정말 취소하시겠습니까?");
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent);
                        finish();
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                    }
                });
                builder.show();
            }
        });
        //수정하기버튼
        modButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                saveData();
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
//                intent.putExtra("AGE",age.getText().toString());
//                intent.putExtra("CHARACTERISTIC",characteristic.getText().toString());
//                intent.putExtra("DATE",textView_Date.getText().toString());
//                intent.putExtra("FEELING",ratingBar.getRating());
                startActivity(intent);
                finish();
            }
        });
    }
        //날짜설정
       public void InitializeListener(){
        callbackMethod = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                textView_Date.setText(year + "년" + month + "월" + dayOfMonth + "일");
            }
        };
       }
       //초기날짜설정
        public void OnClickHandler(View view) {
        DatePickerDialog dialog = new DatePickerDialog(this, callbackMethod, 2020, 6, 25);
        dialog.show();
    }
    //데이터 저장
    public void saveData(){
        SharedPreferences sharedPreferences = getSharedPreferences("sFile",MODE_PRIVATE);
        SharedPreferences.Editor editor= sharedPreferences.edit();
        String updatedAge = age.getText().toString();
        String updateCharac = characteristic.getText().toString();
        String updateDate = textView_Date.getText().toString();
        Float updateFeeling = ratingBar.getRating();
        editor.putString("AGE",updatedAge);
        editor.putString("CHARACTERISTIC",updateCharac);
        editor.putString("DATE",updateDate);
        editor.putFloat("FEELING",updateFeeling);
        editor.commit();
    }
    }

