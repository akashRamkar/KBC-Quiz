//package com.example.scorescreen;
package com.example.demoquiz;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ScoreActivity extends AppCompatActivity {
private TextView scoreTextView;
private Button exitButton;
private static int userScore;
public static int tempScore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);
        Intent intentFromMainActivity=getIntent();
         userScore=intentFromMainActivity.getIntExtra("points",0);
         tempScore=userScore;
        System.out.println(userScore);
        scoreTextView=findViewById(R.id.score_text_view);
        scoreTextView.setText("your score is "+userScore);
        System.out.println("user score "+userScore);
        exitButton=findViewById(R.id.Exit_button);


    }
    public void onExitBackTOStartActivity(View view){
        MainActivity.counter=0;
        MainActivity.score=0;
        StartActivity.isCheckBoxSelected=false;
        Intent intent=new Intent(ScoreActivity.this,StartActivity.class);
        startActivity(intent);
        finish();
    }
}



