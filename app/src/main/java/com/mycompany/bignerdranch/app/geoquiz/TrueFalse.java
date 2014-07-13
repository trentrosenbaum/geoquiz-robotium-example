package com.mycompany.bignerdranch.app.geoquiz;

public class TrueFalse {

    private int mQuestionResId;

    private boolean mTrueQuestion;

    public TrueFalse(int questionResId, boolean trueQuestion){
        this.mQuestionResId = questionResId;
        this.mTrueQuestion = trueQuestion;
    }

    public int getQuestionResId() {
        return mQuestionResId;
    }

    public boolean isTrueQuestion() {
        return mTrueQuestion;
    }
}
