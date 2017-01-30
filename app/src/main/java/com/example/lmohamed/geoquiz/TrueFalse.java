package com.example.lmohamed.geoquiz;

/**
 * Created by lmohamed on 1/29/17.
 */

public class TrueFalse {

    /**
     * Member variables
     */
    private int mQuestion;
    private boolean mTrueQuestion;


    /**
     * Constructor
     */
    public TrueFalse(int question, boolean trueQuestion){
        mQuestion = question;
        mTrueQuestion = trueQuestion;

    }

    /**
     * Getter and Setter methods for each variable
     * @return
     */
    public int getQuestion() {
        return mQuestion;
    }

    public void setQuestion(int mQuestion) {
        this.mQuestion = mQuestion;
    }

    public boolean isTrueQuestion() {
        return mTrueQuestion;
    }

    public void setTrueQuestion(boolean mTrueQuestion) {
        this.mTrueQuestion = mTrueQuestion;
    }
}
