package com.example.hangman;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

public class TimeAttackActivity extends AppCompatActivity {
    private  char [] wordFound;
    private TextView txAnswer;
    private TextView txQuestion;
    private ImageView ivHangman;
    private TextView txTimer;
    private TextView tvScore;
    private int level = 0;
    private List<QuestionAnswer> list;
    private String answer;
    private List<String> used_keys = new ArrayList<>();
    private int noOfErrors = 0;
    private int scores = 0;
    private int time_counter = 0;
    private final int total_time = 600000;
    private int images[] = { R.drawable.img1,
            R.drawable.img2,
            R.drawable.img3,
            R.drawable.img4,
            R.drawable.img5,
            R.drawable.img6,
            R.drawable.img7,
            R.drawable.img8,
            R.drawable.img9};
    private int [] buttons = {
            R.id.btn1, R.id.btn2, R.id.btn3, R.id.btn4, R.id.btn5, R.id.btn6, R.id.btn7, R.id.btn8, R.id.btn9,
            R.id.btnA, R.id.btnB, R.id.btnC, R.id.btnD, R.id.btnE, R.id.btnF, R.id.btnG, R.id.btnH, R.id.btnI,
            R.id.btnJ, R.id.btnK, R.id.btnL, R.id.btnM, R.id.btnN, R.id.btnO, R.id.btnP, R.id.btnQ, R.id.btnR,
            R.id.btnS, R.id.btnT, R.id.btnU, R.id.btnV, R.id.btnW, R.id.btnX, R.id.btnY, R.id.btnZ,
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_attack);

        txAnswer =  findViewById(R.id.txAnswer);
        txQuestion = findViewById(R.id.txQuestion);
        ivHangman = findViewById(R.id.ivHangman);
        tvScore = findViewById(R.id.tvScore);
        txTimer = findViewById(R.id.txTimer);


        new CountDownTimer(total_time, 1000){
            public void onTick(long millisUntilFinished){
                NumberFormat f = new DecimalFormat("00");

                //long hour = (millisUntilFinished / 3600000) % 24;

                long min = (millisUntilFinished / 60000) % 60;

                long sec = (millisUntilFinished / 1000) % 60;

                txTimer.setText( f.format(min) + ":" + f.format(sec));
            }
            public void onFinish() {
                gameOver();
            }
        }.start();

        startGame();
    }

    //Starting point of the game
    private void startGame(){
        list = new QaGenerator().createQA();
        txQuestion.setText(list.get(level).getQuestions());
        answer = list.get(level).getAnswers().toUpperCase();
        turnAnswerToDashes(answer);
        ivHangman.setImageResource(images[noOfErrors]);
        noOfErrors++;
    }

    //generates next question
    private void nextQuestion(){
        tvScore.setText("Score : " + scores);
        txQuestion.setText(list.get(level).getQuestions());
        answer = list.get(level).getAnswers().toUpperCase();
        turnAnswerToDashes(answer);
        noOfErrors = 0;
        ivHangman.setImageResource(images[noOfErrors]);
        noOfErrors++;
        used_keys.clear();
        resetButtonColors();
    }

    private void goToMainMenu(){
        Intent intent = new Intent(TimeAttackActivity.this, MainMenuActivity.class);
        startActivity(intent);
    }

    private void gameOver(){
        //instance of AlertDialog
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        //initialize our widgets
        TextView tvGameOver;
        Button btnMainMenu;
        TextView tvFinalScore;

        //creating class to create the view
        LayoutInflater inflater = this.getLayoutInflater();
        View view = inflater.inflate(R.layout.layout_game_over,null);

        tvGameOver = view.findViewById(R.id.tvGameOver);
        btnMainMenu = view.findViewById(R.id.buttonMainMenu);
        tvFinalScore = view.findViewById(R.id.tvFinalScore);
        //setting the view to AlertDialog
        builder.setView(view);
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
        tvFinalScore.setText("Score: " + scores);
        btnMainMenu.setOnClickListener(view1 -> {
            goToMainMenu();
            alertDialog.dismiss();
        });
        alertDialog.setCancelable(false);

    }

    private void resetButtonColors(){
        //@TODO implement an efficient approach
        for(int i=0; i<buttons.length; i++){
            findViewById(buttons[i]).setBackground(ContextCompat.getDrawable(this,R.drawable.btn_background));
        }
    }

    //magic method
    private void turnAnswerToDashes(String answer){
        wordFound = new char[answer.length()];
        for(int i=0; i<answer.length(); i++){
            if(answer.charAt(i) != ' '){
                wordFound[i] = '_';
            }else{
                wordFound[i] = ' ';
            }
        }
        txAnswer.setText(charBackToString());
    }

    // Method returning the state of the word found by the user until by now
    private String charBackToString() {
        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < wordFound.length; i++) {
            builder.append(wordFound[i]);

            if (i < wordFound.length - 1) {
                builder.append(" ");
            }
        }

        return builder.toString();
    }

    //checks answer with the user word
    private Boolean wordMatched (){
        return answer.equals(new String(wordFound));
    }

    private void youWin(){
        //instance of AlertDialog
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        //initialize our widgets
        Button btnMainMenu;

        //creating class to create the view
        LayoutInflater inflater = this.getLayoutInflater();
        View view = inflater.inflate(R.layout.layout_win,null);

        btnMainMenu = view.findViewById(R.id.buttonMainMenu);

        //setting the view to AlertDialog
        builder.setView(view);
        AlertDialog alertDialog = builder.create();
        alertDialog.show();

        btnMainMenu.setOnClickListener(view1 -> {
            goToMainMenu();
            alertDialog.dismiss();
        });
        alertDialog.setCancelable(false);
    }

    private void popUpWindow(){
        //instance of AlertDialog
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        //initialize our widgets
        TextView tvMessage;
        Button btnNextLevel;
        Button btnMainMenu;

        //creating class to create the view
        LayoutInflater inflater = this.getLayoutInflater();
        View view = inflater.inflate(R.layout.layout_popup,null);

        tvMessage = view.findViewById(R.id.tvMessage);
        btnNextLevel = view.findViewById(R.id.btnNextLevel);
        btnMainMenu = view.findViewById(R.id.buttonMainMenu);

        //setting the view to AlertDialog
        builder.setView(view);
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
        tvMessage.setText("Correct Answer: " + answer);
        btnNextLevel.setOnClickListener(view1 -> {
            nextQuestion();
            alertDialog.dismiss();
        });
        btnMainMenu.setOnClickListener(view2 ->{
            goToMainMenu();
            alertDialog.dismiss();
        });
        alertDialog.setCancelable(false);

    }

    //when user guesses the word
    private void guess(String character){
        if(!used_keys.contains(character)){
            if(answer.contains(character)){
                //include the char on the correct position
                int index = answer.indexOf(character);
                while(index >= 0) {
                    wordFound[index] = character.charAt(0);
                    index = answer.indexOf(character, index + 1);
                } //apple wordFound = "_____"
                txAnswer.setText(charBackToString());


                //Log.d("wordFound",new String(wordFound));
                //checks if user entered word matches to our answer
                //show next question
                if(wordMatched()) {
                    //Add Score and update to Score text view
                    scores += 10;
                    level++;
                    if(level < list.size()){
                        popUpWindow();
                    }else{
                        youWin();
                    }

                    //Add condition to exit the game when score becomes 100
                    //Toast.makeText(this,"Word matched",Toast.LENGTH_SHORT).show();
                }


            }else{
                //show the hangman
                //Toast.makeText(this, "Incorrect, please try again!",Toast.LENGTH_SHORT).show();

                ivHangman.setImageResource(images[noOfErrors]);

                if(noOfErrors == 8){
                    //Game Over
                    gameOver();
                }
                noOfErrors++;
            }
        }
        else {
            Toast.makeText(this,"This key has already been entered, Try another one",Toast.LENGTH_SHORT).show();
        }
        used_keys.add(character);

    }

    //when custom key are pressed
    public void keyPress(View v){
        Button btn = ((Button) v);
        String key = btn.getText().toString();
        btn.setBackgroundColor(Color.GRAY);
        guess(key);

    }
}