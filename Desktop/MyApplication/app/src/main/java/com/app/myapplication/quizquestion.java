package com.app.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class quizquestion extends AppCompatActivity {
    private TextView questionview;
    private TextView scoreview;
    private TextView questioncount;
    private TextView timerview;
    private RadioGroup radgroup;
    private RadioButton radbtn1;
    private RadioButton radbtn2;
    private Button confirm;
    private ColorStateList textColorDefaultRb;
    private List <quizwork> quizworkList;
    private int questioncounter;
    private  int Counttotal;
    private quizwork Newquestion;
    private int scoring;
    private boolean selectedans;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quizquestion);
        questionview = findViewById(R.id.quiz1);
        scoreview = findViewById(R.id.score1);
        questioncount = findViewById(R.id.question1);
        timerview = findViewById(R.id.timer);
        radgroup = findViewById(R.id.radiog1);
        radbtn1 = findViewById(R.id.radiobtn1);
        radbtn2 = findViewById(R.id.radiobtn2);
        confirm = findViewById(R.id.btn2);
        textColorDefaultRb = radbtn1.getTextColors();
        appDbHelper dbHelper = new appDbHelper(this);
        quizworkList = dbHelper.getallquestions();
        Counttotal = quizworkList.size();
        Collections.shuffle(quizworkList);
        shownext();
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!selectedans){
                    if(radbtn1.isChecked() ||radbtn2.isChecked()){
                        checkans();
                    } else {
                        Toast.makeText(quizquestion.this, "Please select one answer", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    shownext();
                }
            }
        });
    }
    private void shownext()
    {
        radbtn1.setTextColor(textColorDefaultRb);
        radbtn2.setTextColor(textColorDefaultRb);
        radgroup.clearCheck();
        if (questioncounter < Counttotal){
            Newquestion = quizworkList.get(questioncounter);
            questionview.setText(Newquestion.getQuestion());
            radbtn1.setText(Newquestion.getOption1());
            radbtn2.setText(Newquestion.getOption2());
            questioncounter ++;
            questioncount.setText("Question: "+ questioncounter + "/" + Counttotal);
            selectedans = false;
            confirm.setText("confirm");

        }
        else{
            finishquiz();
        }
    }
    private void checkans(){
        selectedans=true;
        RadioButton radioButton = findViewById(radgroup.getCheckedRadioButtonId());
        int ansnmber = radgroup.indexOfChild(radioButton)+ 1;
        if (ansnmber==Newquestion.getAnswers()){
            scoring++;
            scoreview.setText(("Score: "+scoring));
        }
        showrightans();
    }
    private void showrightans(){
        radbtn1.setTextColor(Color.RED);
        radbtn2.setTextColor(Color.RED);
        switch(Newquestion.getAnswers()){
            case 1:
                radbtn1.setTextColor(Color.GREEN);
                questionview.setText("The correct answer is option1");
                break;
            case 2:
                radbtn1.setTextColor(Color.GREEN);
                questionview.setText("The correct answer is option1");
                break;

            case 3:
                radbtn1.setTextColor(Color.GREEN);
                questionview.setText("The correct answer is option1");
                break;

            case 4:
                radbtn2.setTextColor(Color.GREEN);
                questionview.setText("The correct answer is option2");
                break;

            case 5:
                radbtn2.setTextColor(Color.GREEN);
                questionview.setText("The correct answer is option2");
                break;

        }
        if (questioncounter < Counttotal){
            confirm.setText("Next please");
        } else {
            confirm.setText("Finish");
        }
    }
    private void finishquiz(){
        finish();
    }
}