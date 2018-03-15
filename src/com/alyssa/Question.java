package com.alyssa;

public class Question {
    int qNum;
    String answer;
    int response;
    String[] key = {"a", "b", "c", "d", "a", "b", "c", "d", "a", "b", "c", "d", "a", "b"};
    public Question(int qNum) {
        this.qNum = qNum;
    }
    //method gets input from button
    //if input == key[qNum], correct++. qNum++.
    //if !=, disable button (?)
    public boolean checkCorrect(int response){
        return true;
    }
}
