package com.mycompany.bignerdranch.app.geoquiz;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.mycompany.bignerdranch.app.geoquiz.R;

import org.w3c.dom.Text;

public class CheatActivity extends ActionBarActivity {

    public static final String LOG_TAG = "CheatActivity";

    public static final int ACTIVITY_ID = 0;
    public static final String EXTRA_KEY_QUESTION_ANSWER = "com.mycompany.bignerdranch.app.geoquiz.QUESTION_ANSWER";
    public static final String EXTRA_KEY_QUESTION_SHOWN = "com.mycompany.bignerdranch.app.geoquiz.QUESTION_SHOWN";
    public static final String BUNDLE_KEY_IS_CHEATER = "is_cheater";

    private TextView mRuntimeVersionText;

    private TextView mAnswerText;
    private Button mShowAnswerButton;
    private boolean mQuestionAnswer;

    private boolean mIsCheater = false;

    public static void startInstanceForResult(Context context, boolean questionAnswer) {

        Intent intent = new Intent(context, CheatActivity.class);
        intent.putExtra(EXTRA_KEY_QUESTION_ANSWER, questionAnswer);

        ((Activity) context).startActivityForResult(intent, ACTIVITY_ID);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cheat);

        mRuntimeVersionText = (TextView)findViewById(R.id.runtimeVersionText);
        String apiLevelStr = "API Level " +Build.VERSION.SDK_INT;
        mRuntimeVersionText.setText(apiLevelStr);

        mQuestionAnswer = getIntent().getBooleanExtra(EXTRA_KEY_QUESTION_ANSWER, false);

        mAnswerText = (TextView) findViewById(R.id.answerTextView);

        mShowAnswerButton = (Button) findViewById(R.id.showAnswerButton);
        mShowAnswerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (mQuestionAnswer) {

                    mAnswerText.setText(R.string.true_button);

                } else {
                    mAnswerText.setText(R.string.false_button);
                }

                mIsCheater = true;
                setAnswerShownResult(mIsCheater);
            }
        });

        if(savedInstanceState != null){
            mIsCheater = savedInstanceState.getBoolean(BUNDLE_KEY_IS_CHEATER, false);
            setAnswerShownResult(mIsCheater);
        }

    }

    private void setAnswerShownResult(boolean isAnswerShown) {
        Intent intent = new Intent();
        intent.putExtra(EXTRA_KEY_QUESTION_SHOWN, isAnswerShown);
        setResult(Activity.RESULT_OK, intent);
    }

        /* Save and Restore Methods */

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.d(LOG_TAG, "onSaveInstanceState(Bundle) called");

        outState.putBoolean(BUNDLE_KEY_IS_CHEATER, mIsCheater);
    }

}