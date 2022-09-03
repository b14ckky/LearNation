package com.example.pr_bg2.Models;

public class SendData {
    int Correct;
    int Wrong;

public SendData(){}

    public int getCorrect() {
        return Correct;
    }

    public void setCorrect(int correct) {
        this.Correct = correct;
    }

    public int getWrong() {
        return Wrong;
    }

    public void setWrong(int wrong) {
        this.Wrong = wrong;
    }

    public SendData(int correct, int wrong) {
        this.Correct = correct;
        this.Wrong = wrong;

    }
}
