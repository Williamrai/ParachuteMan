package com.example.hangman;

public class QuestionAnswer {
    public QuestionAnswer(String question, String answer){
        // What is your name? : apple
        questions = question;
        answers = answer;
    }

    public String getAnswers() {
        return answers;
    }

    public String getQuestions() {
        return questions;
    }

    private String questions;
    private String answers;


}

