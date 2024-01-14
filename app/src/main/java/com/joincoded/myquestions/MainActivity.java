package com.joincoded.myquestions;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private TextView questionTextView, correctTextView, wrongTextView, GradeTextView;
    private Button trueButton, falseButton, nextButton;
    private  Button restartButton;


    private ArrayList<String> questions;
    private ArrayList<Boolean> answers;
    private int currentIndex = 0;

    int score = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        questionTextView = findViewById(R.id.questionTextView);
        correctTextView = findViewById(R.id.correctTextView);
        wrongTextView = findViewById(R.id.WrongTextView);
        trueButton = findViewById(R.id.truee);
        falseButton = findViewById(R.id.falsee);
        nextButton = findViewById(R.id.nextQ);
        GradeTextView = findViewById(R.id.GradeTextView);
        restartButton=findViewById(R.id.restart);
        restartButton.setOnClickListener(view -> restartQuiz());
        theQuistions();
    }

    private void displayQuestion() {
        theSettings();
    }

    private void checkAnswer(boolean userAnswer) {
        boolean correctAnswer = answers.get(currentIndex);

        if (userAnswer == correctAnswer) {
            correctAnsr();
            theGrade();

        } else {
            wrongAnsr();
        }
    }
    public void theGrade() {
        score++;
        GradeTextView.setText(String.valueOf(score));
    }
    public void correctAnsr() {
        correctTextView.setVisibility(View.VISIBLE);
        wrongTextView.setVisibility(View.INVISIBLE);
        trueButton.setVisibility(View.INVISIBLE);
        falseButton.setVisibility(View.INVISIBLE);
        nextButton.setVisibility(View.VISIBLE);
    }

    public void wrongAnsr() {
        correctTextView.setVisibility(View.INVISIBLE);
        wrongTextView.setVisibility(View.VISIBLE);
        trueButton.setVisibility(View.VISIBLE);
        falseButton.setVisibility(View.VISIBLE);
        nextButton.setVisibility(View.INVISIBLE);
    }
    private void theQuistions() {
        questions = new ArrayList<>();
        answers = new ArrayList<>();

        questions.add("penguins can fly");
        answers.add(false);

        questions.add("the water boils at 200c");
        answers.add(false);

        questions.add("people can fly");
        answers.add(true);

        displayQuestion();

        trueButton.setOnClickListener(view -> checkAnswer(true));
        falseButton.setOnClickListener(view -> checkAnswer(false));
        nextButton.setOnClickListener(view -> nextQuestion());
    }

    private void theSettings() {
        questionTextView.setText(questions.get(currentIndex));

        GradeTextView.setText(String.valueOf(score));

        correctTextView.setVisibility(View.INVISIBLE);
        wrongTextView.setVisibility(View.INVISIBLE);
        nextButton.setVisibility(View.INVISIBLE);
        trueButton.setVisibility(View.VISIBLE);
        falseButton.setVisibility(View.VISIBLE);
    }

    private void restartQuiz(){
        currentIndex=0;
        score=0;
        displayQuestion();
        restartButton.setVisibility(View.INVISIBLE);
        //nextButton.setVisibility(View.VISIBLE);
    }

    private void nextQuestion() {
        currentIndex++;
        if (currentIndex < questions.size()) {
            displayQuestion();
        } else {
            nextButton.setVisibility(View.INVISIBLE);
            restartButton.setVisibility(View.VISIBLE);

        }
    }
}