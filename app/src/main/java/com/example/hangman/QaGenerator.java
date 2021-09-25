package com.example.hangman;

import java.util.ArrayList;
import java.util.List;

public class QaGenerator {
    private List<QuestionAnswer>  list;
    public List<QuestionAnswer> createQA(){
        list = new ArrayList<>();

        list.add(new QuestionAnswer("What OS are you using?", "Android"));
        list.add(new QuestionAnswer("Left or ", "Right"));
        list.add(new QuestionAnswer("_____ World", "Hello"));
        list.add(new QuestionAnswer("Bacon and C", "heese"));
        list.add(new QuestionAnswer("Apple is to red as Sky is to", "blue"));
        list.add(new QuestionAnswer("How many months of the year have at least 28 days", "all"));
        list.add(new QuestionAnswer("It belongs to you, but your friends use it more. What is it?", "Your Name"));
        list.add(new QuestionAnswer("I'm on the next", "level"));
        return  list;
    }
}
