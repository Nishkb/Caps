package com.example.caps;
import androidx.appcompat.app.AppCompatActivity;

import android.media.AudioManager;
import android.media.ToneGenerator;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "DEBUG:MainActivity";
    private Game game = new Game();
    private String question;
    private String answer;
    private int score;
    private int questionNum = 1;
    private String logEntry;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ask();
        Log.d(TAG, "onCreate called");
        game = new Game();
        score = 0;
        questionNum = 1;
        ask();
        Log.d(TAG, "ask method called");
    }


    private void ask(){
        Log.d(TAG, "ask running");
        String[] a = game.qa().split("[\\n]+");
        this.question = a[0];
        this.answer = a[1];

        ((TextView) findViewById(R.id.question)).setText(question);
    }

    public void onDone(View v){
        {
            Log.d(TAG, "onDone clicked");
            if(questionNum >= 10)
            {
                finish();
            }
            else
            {
                String result = ((EditText) findViewById(R.id.answer)).getText().toString().trim();
                if (result.equalsIgnoreCase(this.answer))
                {
                    score ++;
                }

                logEntry = "Q# " + questionNum + ": " + question + "\n";
                logEntry += "Your answer: " + result.toUpperCase() + "\n";
                logEntry += "Correct Answer: " + answer + "\n";


                questionNum++;

                String scoreResult = "Score " + this.score;
                ((TextView) findViewById(R.id.score)).setText(scoreResult);
                TextView logBox = (TextView) findViewById(R.id.log);

                String history = logBox.getText().toString();
                logBox.setText(logEntry + "\n");
                logBox.append(history);

                if(questionNum >= 10)
                {
                    String gameFinished = "GAME OVER!";
                    ((TextView) findViewById(R.id.serial)).setText(gameFinished);
                    ((Button) findViewById(R.id.done)).setEnabled(false);
                }
                else
                {
                    String questionNum = "Q# " + this.questionNum;
                    ((TextView) findViewById(R.id.serial)).setText(questionNum);
                    String scored = "Score " + this.score;
                    ((TextView) findViewById(R.id.score)).setText(scored);
                    ask();

                }
            }
        }
    }
}