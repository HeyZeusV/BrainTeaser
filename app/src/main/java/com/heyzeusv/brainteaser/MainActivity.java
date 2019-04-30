package com.heyzeusv.brainteaser;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Button start;
    Button answer1;
    Button answer2;
    Button answer3;
    Button answer4;
    TextView timer;
    TextView score;
    TextView equation;

    int answer;
    ArrayList<Integer> answersList = new ArrayList<Integer>();
    ArrayList<Button> buttons = new ArrayList<Button>();

    Random random = new Random();

    public void startGame(View view) {

        start   .setVisibility(View.INVISIBLE);
        answer1 .setVisibility(View.VISIBLE);
        answer2 .setVisibility(View.VISIBLE);
        answer3 .setVisibility(View.VISIBLE);
        answer4 .setVisibility(View.VISIBLE);
        timer   .setVisibility(View.VISIBLE);
        score   .setVisibility(View.VISIBLE);
        equation.setVisibility(View.VISIBLE);

        new CountDownTimer(31000, 1000) {

            @Override
            public void onTick(long millisUntilFinished) {

                timer.setText(String.valueOf((int) millisUntilFinished / 1000));
                timer.append("s");
            }

            @Override
            public void onFinish() {

            }
        }.start();
        questionAnswer();
    }

    public void questionAnswer() {

        int operator = random.nextInt(3);
        int operand1 = random.nextInt(20) + 1;
        int operand2 = random.nextInt(20) + 1;

        int fakeAnswer1;
        int fakeAnswer2;
        int fakeAnswer3;
        answer = 0;

        String eq;

        Collections.shuffle(buttons);

        switch (operator) {

            case 0:
                answer = operand1 + operand2;
                eq = String.valueOf(operand1) + " + " + String.valueOf(operand2);
                equation.setText(eq);
                break;

            case 1:
                answer = operand1 - operand2;
                eq = String.valueOf(operand1) + " - " + String.valueOf(operand2);
                equation.setText(eq);
                break;

            case 2:
                answer = operand1 * operand2;
                eq = String.valueOf(operand1) + " * " + String.valueOf(operand2);
                equation.setText(eq);
                break;
        }

        do {
            fakeAnswer1 = random.nextInt(10 + 1 + 10) - 10 + answer;
            fakeAnswer2 = random.nextInt(10 + 1 + 10) - 10 + answer;
            fakeAnswer3 = random.nextInt(10 + 1 + 10) - 10 + answer;
        } while (fakeAnswer1 == answer || fakeAnswer2 == answer || fakeAnswer3 == answer);

        ArrayList<Integer> answers = new ArrayList<Integer>();
        answers.add(answer);
        answers.add(fakeAnswer1);
        answers.add(fakeAnswer2);
        answers.add(fakeAnswer3);

        for (int i = 0; i < buttons.size(); i++) {

            buttons.get(i).setText(String.format(answers.get(i).toString()));
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        timer    = (TextView) findViewById(R.id.timeLeft);
        score    = (TextView) findViewById(R.id.score);
        equation = (TextView) findViewById(R.id.equation);
        start    = (Button) findViewById(R.id.startButton);
        answer1  = (Button) findViewById(R.id.answer1);
        answer2  = (Button) findViewById(R.id.answer2);
        answer3  = (Button) findViewById(R.id.answer3);
        answer4  = (Button) findViewById(R.id.answer4);

        answersList.add(0);
        answersList.add(0);
        answersList.add(0);
        answersList.add(0);

        buttons.add(answer1);
        buttons.add(answer2);
        buttons.add(answer3);
        buttons.add(answer4);
    }
}
