package com.mycompany.bignerdranch.app.geoquiz;

import android.test.ActivityInstrumentationTestCase2;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.robotium.solo.Solo;

import junit.framework.Assert;


public class QuizActivityTest extends ActivityInstrumentationTestCase2<QuizActivity> {

    private Solo solo;

    public QuizActivityTest() {
        super(QuizActivity.class);
    }

    public void setUp() throws Exception {
        solo = new Solo(getInstrumentation(), getActivity());
    }

    public void testTrueButtonIsInitialisedCorrectly(){

        View trueView = solo.getView(R.id.true_button);
        Assert.assertTrue(trueView instanceof Button);

        String text = ((Button) trueView).getText().toString();
        Assert.assertEquals(solo.getString(R.string.true_button), text);
    }

    public void testFalseButtonIsInitialisedCorrectly(){

        View falseView = solo.getView(R.id.false_button);
        Assert.assertTrue(falseView instanceof Button);

        String text = ((Button) falseView).getText().toString();
        Assert.assertEquals(solo.getString(R.string.false_button), text);
    }

    public void testCheatButtonIsInitialisedCorrectly(){

        View cheatView = solo.getView(R.id.cheat_button);
        Assert.assertTrue(cheatView instanceof Button);

        String text = ((Button) cheatView).getText().toString();
        Assert.assertEquals(solo.getString(R.string.cheat_button), text);
    }

    public void testNextButtonIsInitialisedCorrectly() throws Exception {

        View nextView = solo.getView(R.id.next_button);
        Assert.assertTrue(nextView instanceof ImageButton);

        String contentDescription = nextView.getContentDescription().toString();
        Assert.assertEquals(solo.getString(R.string.next_button), contentDescription);
    }

    public void testPreviousButtonIsInitialisedCorrectly(){

        View previousView = solo.getView(R.id.previous_button);
        Assert.assertTrue(previousView instanceof ImageButton);

        String contentDescription = previousView.getContentDescription().toString();
        Assert.assertEquals(solo.getString(R.string.previous_button), contentDescription);
    }

    public void testExpectedActivityIsCreatedCorrectly() throws Exception {

        solo.assertCurrentActivity("Not expected Android Activity.", QuizActivity.class);
    }

    public void testFirstQuestionIsDisplayCorrectly() throws Exception {

        Assert.assertTrue("Expected question not displayed.", solo.searchText(solo.getString(R.string.question_oceans)));
    }

    public void testNextButtonWorksCorrectly() throws Exception {

        View nextButton = solo.getView(R.id.next_button);

        solo.clickOnView(nextButton);

        Assert.assertTrue("Expected question not displayed.", solo.searchText(solo.getString(R.string.question_mideast)));
    }

    public void testPreviousButtonWorksCorrectly() throws Exception {

        View previousButton = solo.getView(R.id.previous_button);

        solo.clickOnView(previousButton);

        Assert.assertTrue("Expected question not displayed.", solo.searchText(solo.getString(R.string.question_asia)));
    }

    public void testCorrectToastIsDisplayed() throws Exception {

        Assert.assertTrue("Expected question not displayed.", solo.searchText(solo.getString(R.string.question_oceans)));

        solo.clickOnButton(solo.getString(R.string.true_button));

        Assert.assertTrue("Expected Toast message not displayed.", solo.searchText(solo.getString(R.string.correct_toast)));
    }

    public void testInCorrectToastIsDisplayed() throws Exception {

        Assert.assertTrue("Expected question not displayed.", solo.searchText(solo.getString(R.string.question_oceans)));

        solo.clickOnButton(solo.getString(R.string.false_button));

        Assert.assertTrue("Expected Toast message not displayed.", solo.searchText(solo.getString(R.string.incorrect_toast)));
    }

    public void testSameQuestionIsDisplayedAfterRotation() throws Exception {

        Assert.assertTrue("Expected question not displayed.", solo.searchText(solo.getString(R.string.question_oceans)));

        solo.setActivityOrientation(Solo.LANDSCAPE);

        Assert.assertTrue("Expected question not displayed.", solo.searchText(solo.getString(R.string.question_oceans)));
    }


    @Override
    public void tearDown() throws Exception {
        solo.finishOpenedActivities();
    }
}