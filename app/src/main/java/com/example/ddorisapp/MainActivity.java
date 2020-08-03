package com.example.ddorisapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.Image;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    TextView age,characteristic,date;
    RatingBar ratingBar;
    Button btnLegend;
    ImageView imageView;
    String updatedAge,updateCharac,updateDate;
    Float updateFeeling;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle("또리의 앱");
        //연결하기
        age = (TextView) findViewById(R.id.age);
        characteristic = (TextView) findViewById(R.id.characteristic);
        date = (TextView) findViewById(R.id.date);
        ratingBar = (RatingBar) findViewById(R.id.feeling);
        btnLegend = (Button) findViewById(R.id.legend);
        imageView = (ImageView) findViewById(R.id.image);
        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        editor = preferences.edit();
        //값 초기화하기
        initialize();

        //또리레전드사진보기로 넘어가기
        btnLegend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(getApplicationContext(),ShowActivity.class);
                startActivity(intent2);
            }
        });
        }
        //메뉴만들기
        @Override
        public boolean onCreateOptionsMenu(Menu menu){
            getMenuInflater().inflate(R.menu.menu,menu);
            return super.onCreateOptionsMenu(menu);
        }
        @Override
        public boolean onOptionsItemSelected(MenuItem item){
             switch (item.getItemId()){
                 case R.id.menu1 :
                    Intent intent1 = new Intent(getApplicationContext(),ModifyActivity.class);
                    startActivity(intent1);
                    finish();
                    break;
                 case R.id.menu2 :
                     final String[] colorArray = new String[] {"검정색","연노란색","하늘색","연분홍색","흰색"};
                     AlertDialog.Builder dlg = new AlertDialog.Builder(MainActivity.this);
                     dlg.setTitle("원하는 배경색을 고르세요").setPositiveButton("확인", null);
                     dlg.setItems(colorArray, new DialogInterface.OnClickListener() {
                         @Override
                         public void onClick(DialogInterface dialog, int which) {
                             Toast.makeText(MainActivity.this,"<"+colorArray[which]+">을 선택했습니다",Toast.LENGTH_SHORT).show();
                             System.out.println("****************"+colorArray[which]);
                             if(colorArray[which]=="검정색"){
                                 imageView.setBackgroundColor(Color.parseColor("#000000"));
                                 saveData(which);
                             }else if(colorArray[which]=="연노란색"){
                                 imageView.setBackgroundColor(Color.parseColor("#FFFACD"));
                                 saveData(which);
                             }else if(colorArray[which]=="하늘색"){
                                 imageView.setBackgroundColor(Color.parseColor("#87CEFA"));
                                 saveData(which);
                             }else if(colorArray[which]=="연분홍색") {
                                 imageView.setBackgroundColor(Color.parseColor("#FFC0CB"));
                                 saveData(which);
                             }else {
                                 imageView.setBackgroundColor(Color.parseColor("#FFFFFF"));
                                 saveData(which);
                             }
                         }
                     });
                     dlg.setPositiveButton("확인",null);
                     dlg.show();
                     break;
                 case R.id.menu3:
                     finish();
                     break;
            }
            return true;
        }
        //초기값설정하기
        public void initialize(){
            preferences = getSharedPreferences("sFile",MODE_PRIVATE);
            age.setText(preferences.getString("AGE",null));
            characteristic.setText(preferences.getString("CHARACTERISTIC",null));
            date.setText(preferences.getString("DATE",null));
            ratingBar.setRating(preferences.getFloat("FEELING",0));
            imageView.setBackgroundColor(Color.parseColor(preferences.getString("BACKGROUND_COLOR","#FFFFFF")));
        }
        //색 데이터 저장하기
        public void saveData(int which){
         SharedPreferences sharedPreferences = getSharedPreferences("sFile",MODE_PRIVATE);
         SharedPreferences.Editor editor= sharedPreferences.edit();
          if(which==0){
            editor.putString("BACKGROUND_COLOR","#000000");
          }else if(which==1){
            editor.putString("BACKGROUND_COLOR","#FFFACD");
          }else if(which==2){
            editor.putString("BACKGROUND_COLOR","#87CEFA");
          }else if(which==3){
            editor.putString("BACKGROUND_COLOR","#FFC0CB");
          }else{
            editor.putString("BACKGROUND_COLOR","#FFFFFF");
        }
        editor.commit();
    }
//        public void updateInfo(){
//            //수정정보 받아오기
//            Intent intent = getIntent();
//            updatedAge = intent.getStringExtra("AGE");
//            updateCharac = intent.getStringExtra("CHARACTERISTIC");
//            updateDate = intent.getStringExtra("DATE");
//            updateFeeling = intent.getFloatExtra("FEELING",0);
//
//            //수정된 값 넣기
//            age.setText(updatedAge);
//            characteristic.setText(updateCharac);
//            date.setText(updateDate);
//            ratingBar.setRating(updateFeeling);
//        }
}
