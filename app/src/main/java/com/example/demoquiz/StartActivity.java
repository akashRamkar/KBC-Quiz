//package com.example.kbcquiz;
package com.example.demoquiz;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

public class StartActivity extends AppCompatActivity implements View.OnClickListener{
private  CheckBox checkBox1,checkBox2,checkBox3,checkBox4,checkBox5;
private  Button playbutton,highscorebtn;
protected static boolean isCheckBoxSelected=false;
private static String level;
private static int userSelectedLevel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        checkBox1=findViewById(R.id.checkBox_1);
        checkBox2=findViewById(R.id.checkBox_2);
        checkBox3=findViewById(R.id.checkBox_3);
        checkBox4=findViewById(R.id.checkBox_4);
        checkBox5=findViewById(R.id.checkBox_5);
        highscorebtn=findViewById(R.id.highscoreButton);
        playbutton=findViewById(R.id.play_Button);
        checkBox1.setOnClickListener(StartActivity.this);
        checkBox2.setOnClickListener(StartActivity.this);
        checkBox3.setOnClickListener(StartActivity.this);
        checkBox4.setOnClickListener(StartActivity.this);
        checkBox5.setOnClickListener(StartActivity.this);

    }
    //checkbox reference no is checkbox no;
    private void onlyEnableThisCheckBox(int checkBoxReference_no){
        checkBox1.setEnabled(false);
        checkBox2.setEnabled(false);
        checkBox3.setEnabled(false);
        checkBox4.setEnabled(false);
        checkBox5.setEnabled(false);
        switch (checkBoxReference_no){
            case 1:{
                checkBox1.setEnabled(true);
                break;
            }
            case 2:{
                checkBox2.setEnabled(true);
                break;
            }
            case 3:{
                checkBox3.setEnabled(true);
                break;
            }
            case 4:{
                checkBox4.setEnabled(true);
                break;
            }
            default:{
                checkBox5.setEnabled(true);
                break;
            }
        }
    }


private void setCheckBoxToDefaults(){
        checkBox1.setEnabled(true);
        checkBox2.setEnabled(true);
        checkBox3.setEnabled(true);
        checkBox4.setEnabled(true);
        checkBox5.setEnabled(true);
}
private void showWarningToast(){
        StartActivity.this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(StartActivity.this,"please select something",Toast.LENGTH_SHORT).show();
            }
        });
}

    @Override
    public void onClick(View view) {
        if(view==(playbutton)){

        }
        else{
//            isCheckboxSelected=true;

        }
        switch (view.getId()){
            case R.id.play_Button:{
                if(isCheckBoxSelected){
                Intent intent = new Intent(StartActivity.this, MainActivity.class);
                intent.putExtra("level",level);
                startActivity(intent);
//            finishAfterTransition();
                finish();
                }else{
                    showWarningToast();
                }
                break;
            }
            case R.id.checkBox_1:{

                if(checkBox1.isChecked()) {
                    isCheckBoxSelected=true;
                    checkBox1.setTextColor(Color.GREEN);

                    onlyEnableThisCheckBox(1);
                level="1";
                }else{
                    isCheckBoxSelected=false;
                    checkBox1.setTextColor(Color.WHITE);
                    setCheckBoxToDefaults();
                }

                break;
            }
            case R.id.checkBox_2:{
                if(checkBox2.isChecked()) {
                    isCheckBoxSelected=true;
                    checkBox2.setTextColor(Color.GREEN);

                    onlyEnableThisCheckBox(2);
                    level = "2";
                }else{
                    isCheckBoxSelected=false;
                    checkBox2.setTextColor(Color.WHITE);
                    setCheckBoxToDefaults();
                }
                break;
            }
            case R.id.checkBox_3:{
                if(checkBox3.isChecked()) {
                    isCheckBoxSelected=true;
                    checkBox3.setTextColor(Color.GREEN);

                    onlyEnableThisCheckBox(3);

                    level = "3";
                }else{
                    isCheckBoxSelected=false;
                    checkBox3.setTextColor(Color.WHITE);
                    setCheckBoxToDefaults();
                }
                break;
            }
            case R.id.highscoreButton:{
                Thread t=new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Intent i=new Intent(getApplicationContext(),HistoryActivity.class);
                        startActivity(i);
//                showWarningToast();
                    }
                });
                t.start();

//                    startActivity(new Intent(StartActivity.this,HistoryActivity.class));
//                    finish();


                break;
            }
            case R.id.checkBox_4:{
                if(checkBox4.isChecked()) {
                    isCheckBoxSelected=true;
                    checkBox4.setTextColor(Color.GREEN);

                    onlyEnableThisCheckBox(4);
                    level = "4";
                }else{
                    isCheckBoxSelected=false;
                    checkBox4.setTextColor(Color.WHITE);
                    setCheckBoxToDefaults();
                }
                break;
            } default:{
                if(checkBox5.isChecked()) {
                    isCheckBoxSelected=true;
                    checkBox5.setTextColor(Color.GREEN);

                    onlyEnableThisCheckBox(5);
                level="5";
                }else{
                    isCheckBoxSelected=false;
                    checkBox5.setTextColor(Color.WHITE);
                    setCheckBoxToDefaults();
                }
                break;
            }
        }
    }
}
