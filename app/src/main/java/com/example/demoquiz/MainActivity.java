package com.example.demoquiz;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;

import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;


import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private TextView Quiz, countDownTime,quizcount;
    private Button option1, option2, option3, option4;
    private  ProgressBar loader;
    private OkHttpClient client;
    private Request request;
    public static int counter=0;
    private static String quest, optionA, optionB, optionC, optionD, ans;
    private static int seconds;
    private static CountDownTimer timer;
    public static int score;
    private static String inputlevel;
    private static boolean isButtonSelected=false;
    private final int COUNTDOWN_DURATION_INMILLIES = 31000;//31 seconds havin 1 seconds buffer

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent startActivityIntent=getIntent();
         inputlevel=startActivityIntent.getStringExtra("level");
        option1 = findViewById(R.id.opt1);
        option2 = findViewById(R.id.opt2);
        option3 = findViewById(R.id.opt3);
        option4 = findViewById(R.id.opt4);
        Quiz = findViewById(R.id.quiz);
        quizcount=findViewById(R.id.question_counter);
        countDownTime = findViewById(R.id.countTime);
        loader = findViewById(R.id.loader);
        setButtonsOnClickListener();
        loadQuiz();
        counter++;
//        countDownTime.setText(duration);
//        updateTimeValue();
    }
    private void showCorrectAnswerToUser(String answer){
        switch (answer){
            case "a":{
                option1.setTextColor(Color.GREEN);
                break;
            }
            case "b":{
                option2.setTextColor(Color.GREEN);
                break;
            }
            case "c":{
                option3.setTextColor(Color.GREEN);
                break;
            }
            case "d":{
                option4.setTextColor(Color.GREEN);
                break;
            }

        }

    }
    private void setButtonsOnClickListener(){
        option1.setOnClickListener(MainActivity.this);
        option2.setOnClickListener(MainActivity.this);
        option3.setOnClickListener(MainActivity.this);
        option4.setOnClickListener(MainActivity.this);
    }
// this method can handle all the clicks on the buttons that were clicked
    @Override
    public void onClick(View selectedButton) {
                   timer.cancel(); //this stops the timer on current time
       switch (selectedButton.getId()){
           case (R.id.opt1):{
               if(!isButtonSelected) {
                   option1.setEnabled(false);
                   isButtonSelected=true;
                   option1.setTextColor(Color.RED);
                   if(ans.equals("a")){
                   option1.setTextColor(Color.GREEN);
                       score++;
                       showThisToastOnMainUI("a");
                   }else{
                       showCorrectAnswerToUser(ans);
                   }
               }
               break;
           }
           case (R.id.opt2):{
               if(!isButtonSelected) {
                   option2.setEnabled(false);
                   isButtonSelected=true;
                   option2.setTextColor(Color.RED);
                   if(ans.equals("b")){
                       option2.setTextColor(Color.GREEN);
                       score++;
                       showThisToastOnMainUI("b");
                   }else{
                       showCorrectAnswerToUser(ans);
                   }
               }
               break;
           }
           case (R.id.opt3):{
               if(!isButtonSelected) {
                   option3.setEnabled(false);
                   isButtonSelected=true;
                   option3.setTextColor(Color.RED);
                   if(ans.equals("c")){
                       option3.setTextColor(Color.GREEN);
                       score++;
                       showThisToastOnMainUI("c");
                   }else{
                       showCorrectAnswerToUser(ans);
                   }
               }
               break;
           }
           default:{
               if(!isButtonSelected) {
                   option4.setEnabled(false);
                   isButtonSelected=true;
                   option4.setTextColor(Color.RED);
                   if(ans.equals("d")){
                       option4.setTextColor(Color.GREEN);
                       score++;
                       showThisToastOnMainUI("d");
                   }else{
                       showCorrectAnswerToUser(ans);
                   }
               }
               break;
           }
       }
    }

    protected static class singleton {
        private singleton() {
        }

        static OkHttpClient apiClient = null;
        static Request apiRequest = null;
        Response response;

        public static OkHttpClient getClientInstance() {
            if (apiClient == null) {
                apiClient = new OkHttpClient();
                return apiClient;
            }
            return apiClient;
        }

        public static Request getRequestInstance(String QuizUrl) {
            if (apiRequest == null) {
                apiRequest = new Request.Builder().url(QuizUrl).build();
                return apiRequest;
            }
            return apiRequest;
        }

    }

    public void loadQuiz() {
        setButtonsToDefaults();
        countDownTime.setVisibility(View.VISIBLE);
        loader.setVisibility(View.VISIBLE);
        String quizUrl ="https://quizzrapi.herokuapp.com/difficulty/"+inputlevel;
//        System.out.println(quizUrl);
        client = singleton.getClientInstance();
        request = singleton.getRequestInstance(quizUrl);
        Thread thread = new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    Response response = client.newCall(request).execute();
                    String responseData = response.body().string();
                    MainActivity.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            try {
//                                Toast.makeText(MainActivity.this,quizUrl,Toast.LENGTH_SHORT).show();
                                JSONObject jsonObject = new JSONObject(responseData);
                                quest = jsonObject.getString("question");
//                                System.out.println(quest);
                                optionA = jsonObject.getString("a");
                                optionB = jsonObject.getString("b");
                                optionC = jsonObject.getString("c");
                                optionD = jsonObject.getString("d");
                            ans=jsonObject.getString("answer");
                                //setting question no.on screen
                                quizcount.setText(String.format("Quiz :%02d/10",counter));
                                Quiz.setText(quest);
                                option1.setText("A: " + optionA);
                                option2.setText("B: " + optionB);
                                option3.setText("C: " + optionC);
                                option4.setText("D: " + optionD);
//                            answer.setText("answer is : "+ans);
//                                System.out.println("Answer is :"+ans);
//                                    showThisToastOnMainUI("Ans is :"+ans);

                                loader.setVisibility(View.GONE);
//                                updateTimeValue();

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    });


                } catch (IOException e) {
                    e.printStackTrace();
                }
                catch (NullPointerException e){
                    e.printStackTrace();
                }
            }
        });
        thread.start();
        updateTimeValue();
    }

    //This method displays the countdown timer on the screen
    public void updateTimeValue() {
       timer= new CountDownTimer(COUNTDOWN_DURATION_INMILLIES, 1000) {
            @Override
            public void onTick(long timeLeftInMillies) {
                seconds=(int)timeLeftInMillies/1000; //
                MainActivity.this.runOnUiThread(setTimeText );

            }

            @Override
            public void onFinish() {

                countDownTime.setVisibility(View.GONE);
                showCorrectAnswerToUser(ans);
                isButtonSelected=true;
                showThisToastOnMainUI("Times Up");


}


        }.start(); //starting of counter
    }

    private final Runnable setTimeText = new Runnable() {
        @Override
        public void run() {
            String time = String.format("%02d", seconds);
            countDownTime.setText(time);

        }
    };
private void setButtonsToDefaults(){
    //setting to default color
    option1.setTextColor(Color.WHITE);
    option2.setTextColor(Color.WHITE);
    option3.setTextColor(Color.WHITE);
    option4.setTextColor(Color.WHITE);
    isButtonSelected=false;
    //this enables the disabled buttons
    option1.setEnabled(true);
    option2.setEnabled(true);
    option3.setEnabled(true);
    option4.setEnabled(true);
}
private void showThisToastOnMainUI(String message){
    MainActivity.this.runOnUiThread(new Runnable() {
        @Override
        public void run() {
            Toast.makeText(MainActivity.this,message,Toast.LENGTH_SHORT).show();
        }
    });
}
public void showLastQuestionToastOnMainUI(){
        MainActivity.this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(getApplicationContext(),"This is last Question",Toast.LENGTH_SHORT).show();
            }
        });
}
    public void getNextQuiz(View view) {
        if (counter < 10) {
            timer.cancel();
            loadQuiz();
        }
        counter++;
        if (counter > 10) {
            showLastQuestionToastOnMainUI();
            showThisToastOnMainUI("your score is "+score);
            Intent intent=new Intent(MainActivity.this,ScoreActivity.class);
            intent.putExtra("points",score);
            startActivity(intent);
            finish();
        }
    }

}