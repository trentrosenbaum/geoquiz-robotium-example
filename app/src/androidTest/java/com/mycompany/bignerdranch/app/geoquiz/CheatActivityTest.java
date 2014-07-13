package com.mycompany.bignerdranch.app.geoquiz;

import android.content.Intent;
import android.os.Build;
import android.test.ActivityInstrumentationTestCase2;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.robotium.solo.Solo;

import junit.framework.Assert;


public class CheatActivityTest extends ActivityInstrumentationTestCase2<CheatActivity> {

    private Solo solo;

    public CheatActivityTest() {
        super(CheatActivity.class);
    }

    public void setUp() throws Exception {

        Intent intent = new Intent();
        intent.putExtra(CheatActivity.EXTRA_KEY_QUESTION_ANSWER, true);
        setActivityIntent(intent);

        solo = new Solo(getInstrumentation(), getActivity());
    }

    public void testShowAnswerButtonIsInitialisedCorrectly() throws Exception {

        View showAnswerView = solo.getView(R.id.showAnswerButton);

        String buttonText = ((Button)showAnswerView).getText().toString();
        Assert.assertEquals(solo.getString(R.string.show_answer_button), buttonText);
    }

    public void testShowWarningIsDisplayed() throws Exception {

        Assert.assertTrue("Expected warning not displayed.", solo.searchText(solo.getString(R.string.warning_text)));
    }

    public void testShowAnswerDisplaysCorrectExpectedAnswer() throws Exception {

        Assert.assertFalse("Answer displayed when not expected.", solo.searchText(solo.getString(R.string.true_button)));

        solo.clickOnButton(solo.getString(R.string.show_answer_button));

        Assert.assertTrue("Expected answer not displayed.", solo.searchText(solo.getString(R.string.true_button)));
    }

    public void testScreenDisplaysCorrectAndroidApiLevel() throws Exception {

        Assert.assertTrue("Expected Android API level not displayed.", solo.searchText("API Level " + Build.VERSION.SDK_INT));
    }

    @Override
    public void tearDown() throws Exception {
        solo.finishOpenedActivities();
    }
}