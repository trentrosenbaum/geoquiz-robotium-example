package com.mycompany.bignerdranch.app.geoquiz;

import android.test.ActivityInstrumentationTestCase2;
import android.view.View;

import com.robotium.solo.Solo;

import junit.framework.Assert;

/**
 * Created by tjr on 13/07/14.
 */
public class CheatingTest extends ActivityInstrumentationTestCase2<QuizActivity> {

    private Solo solo;

    public CheatingTest(){
        super(QuizActivity.class);
    }

    public void setUp() {
        solo= new Solo(getInstrumentation(), getActivity());
    }

    public void testCheatingToastAppearsWhenCheatButtonClicked() throws Exception {


        Assert.assertTrue("Expected question not displayed.", solo.searchText(solo.getString(R.string.question_oceans)));

        View nextButton = solo.getView(R.id.cheat_button);
        solo.clickOnView(nextButton);

        Assert.assertTrue("Current activity is not " + CheatActivity.class, solo.waitForActivity(CheatActivity.class));

        View showAnswerButton = solo.getView(R.id.showAnswerButton);
        solo.clickOnView(showAnswerButton);

        solo.goBack();

        View trueButton = solo.getView(R.id.true_button);
        solo.clickOnView(trueButton);

        Assert.assertTrue("Expected Toast message not displayed.", solo.searchText(solo.getString(R.string.cheater_toast)));
    }

    public void testCheatingToastAppearsWhenCheatingAndQuizActivityIsRotated() throws Exception {

        Assert.assertTrue("Expected question not displayed.", solo.searchText(solo.getString(R.string.question_oceans)));

        View nextButton = solo.getView(R.id.cheat_button);
        solo.clickOnView(nextButton);

        Assert.assertTrue("Current activity is not " + CheatActivity.class, solo.waitForActivity(CheatActivity.class));

        View showAnswerButton = solo.getView(R.id.showAnswerButton);
        solo.clickOnView(showAnswerButton);

        solo.goBack();

        View trueButton = solo.getView(R.id.true_button);
        solo.clickOnView(trueButton);

        Assert.assertTrue("Expected Toast message not displayed.", solo.searchText(solo.getString(R.string.cheater_toast)));

        solo.setActivityOrientation(Solo.LANDSCAPE);

        trueButton = solo.getView(R.id.true_button);
        solo.clickOnView(trueButton);

        Assert.assertTrue("Expected Toast message not displayed.", solo.searchText(solo.getString(R.string.cheater_toast)));
    }

    public void testCheatingToastAppearsWhenCheatingActivityIsRotatedAndQuizDisplayed() throws Exception {

        Assert.assertTrue("Expected question not displayed.", solo.searchText(solo.getString(R.string.question_oceans)));

        View nextButton = solo.getView(R.id.cheat_button);
        solo.clickOnView(nextButton);

        Assert.assertTrue("Current activity is not " + CheatActivity.class, solo.waitForActivity(CheatActivity.class));

        View showAnswerButton = solo.getView(R.id.showAnswerButton);
        solo.clickOnView(showAnswerButton);

        solo.setActivityOrientation(Solo.LANDSCAPE);

        solo.goBack();

        View trueButton = solo.getView(R.id.true_button);
        solo.clickOnView(trueButton);

        Assert.assertTrue("Expected Toast message not displayed.", solo.searchText(solo.getString(R.string.cheater_toast)));

        trueButton = solo.getView(R.id.true_button);
        solo.clickOnView(trueButton);

        Assert.assertTrue("Expected Toast message not displayed.", solo.searchText(solo.getString(R.string.cheater_toast)));

    }

    public void tearDown(){
        solo.finishOpenedActivities();
    }

}
