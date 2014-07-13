package com.mycompany.bignerdranch.app.geoquiz;

import android.annotation.TargetApi;
import android.app.ActionBar;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;


public class QuizActivity extends ActionBarActivity {

    public static final String LOG_TAG = "QuizActivity";
    public static final String BUNDLE_KEY_CURRENT_INDEX = "current_index";
    public static final String BUNDLE_KEY_CHEATER_TRACKER = "cheater_tracker";

    private Button mTrueButton;
    private Button mFalseButton;
    private Button mCheatButton;

    private ImageButton mPreviousButton;
    private ImageButton mNextButton;

    private TextView mQuestionTextView;

    private TrueFalse[] mQuestionBank = new TrueFalse[]{
            new TrueFalse(R.string.question_oceans, true),
            new TrueFalse(R.string.question_mideast, false),
            new TrueFalse(R.string.question_africa, false),
            new TrueFalse(R.string.question_americas, true),
            new TrueFalse(R.string.question_asia, true)
    };

    private int mCurrentIndex = 0;
    private boolean[] mCheatTracker = new boolean[]{
            false,
            false,
            false,
            false,
            false
    };

    @TargetApi(11)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(LOG_TAG, "onCreate(Bundle) called.");

        setContentView(R.layout.activity_quiz);

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            ActionBar actionBar = getActionBar();
            actionBar.setSubtitle(getString(R.string.app_subtitle));
        }

        mQuestionTextView = (TextView)findViewById(R.id.question_text_view);
        mQuestionTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCurrentIndex = (mCurrentIndex + 1) % mQuestionBank.length;
                updateQuestion();
            }
        });

        mTrueButton = (Button)findViewById(R.id.true_button);
        mTrueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkQuestionAnswer(true);
            }
        });

        mFalseButton = (Button)findViewById(R.id.false_button);
        mFalseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkQuestionAnswer(false);
            }
        });

        mCheatButton = (Button)findViewById(R.id.cheat_button);
        mCheatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                boolean questionAnswer = mQuestionBank[mCurrentIndex].isTrueQuestion();
                CheatActivity.startInstanceForResult(QuizActivity.this, questionAnswer);
            }
        });

        mPreviousButton = (ImageButton)findViewById(R.id.previous_button);
        mPreviousButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(mCurrentIndex > 0){
                    mCurrentIndex = mCurrentIndex - 1;
                } else {
                    mCurrentIndex = 4;
                }

                updateQuestion();
            }
        });

        mNextButton = (ImageButton)findViewById(R.id.next_button);
        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mCurrentIndex = (mCurrentIndex + 1) % mQuestionBank.length;
                updateQuestion();
            }
        });

        if(savedInstanceState != null){
            mCurrentIndex = savedInstanceState.getInt(BUNDLE_KEY_CURRENT_INDEX, 0);
            mCheatTracker = savedInstanceState.getBooleanArray(BUNDLE_KEY_CHEATER_TRACKER);
        }

        updateQuestion();
    }

    private void updateQuestion(){
        int questionResId = mQuestionBank[mCurrentIndex].getQuestionResId();
        mQuestionTextView.setText(questionResId);
    }

    private void checkQuestionAnswer(boolean userAnswer){

        boolean questionAnswer = mQuestionBank[mCurrentIndex].isTrueQuestion();

        int messageResId = 0;

        if(mCheatTracker[mCurrentIndex]){

            messageResId = R.string.cheater_toast;

        } else {

            if (userAnswer == questionAnswer) {

                messageResId = R.string.correct_toast;

            } else {

                messageResId = R.string.incorrect_toast;
            }
        }

        Toast.makeText(QuizActivity.this, messageResId, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){

        if(requestCode == CheatActivity.ACTIVITY_ID){

            if(data == null){
                return;
            } else {

                mCheatTracker[mCurrentIndex] = data.getBooleanExtra(CheatActivity.EXTRA_KEY_QUESTION_SHOWN, false);
            }

        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.quiz, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /* Save and Restore Methods */

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.d(LOG_TAG, "onSaveInstanceState(Bundle) called");

        outState.putInt(BUNDLE_KEY_CURRENT_INDEX, mCurrentIndex);
        outState.putBooleanArray(BUNDLE_KEY_CHEATER_TRACKER, mCheatTracker);
    }

    /* Lifecycle Methods */

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(LOG_TAG, "onStart() called.");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(LOG_TAG, "onPause() called.");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(LOG_TAG, "onResume() called.");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(LOG_TAG, "onStop() called.");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(LOG_TAG, "onDestroy() called.");
    }

}
