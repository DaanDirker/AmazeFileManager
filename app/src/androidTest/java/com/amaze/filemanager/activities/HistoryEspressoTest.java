package com.amaze.filemanager.activities;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.amaze.filemanager.R;
import com.amaze.filemanager.utils.DataUtils;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.Matchers.allOf;
import static org.junit.Assert.assertEquals;

@RunWith(AndroidJUnit4.class)
public class HistoryEspressoTest {

    @Rule
    public ActivityTestRule<MainActivity> activityRule = new ActivityTestRule<>(
            MainActivity.class, false, true);

    private DataUtils dataUtils;

    @Before
    public void setUp() {
        //Arrange test
        dataUtils = DataUtils.getInstance();
        dataUtils.clear();
    }

    @Test
    public void testHistory_newFolderNavigation_historyUpdated() {
        //Act test
        onView(withId(R.id.home)).perform(click());
        onView(allOf(withId(R.id.filesListView), isDisplayed())).perform(click());

        String currentPath = activityRule.getActivity().mainFragment.getCurrentPath();
        String latestHistoryPath = dataUtils.getHistory().getFirst();

        //Assert test
        assertEquals(currentPath, latestHistoryPath);
    }
}
