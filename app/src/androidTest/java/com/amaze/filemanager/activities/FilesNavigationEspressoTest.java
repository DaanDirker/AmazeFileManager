package com.amaze.filemanager.activities;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

import static org.hamcrest.Matchers.allOf;

import android.content.Context;
import android.preference.PreferenceManager;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.amaze.filemanager.R;
import com.amaze.filemanager.adapters.data.LayoutElementParcelable;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;

@RunWith(AndroidJUnit4.class)
public class FilesNavigationEspressoTest {

    @Rule
    public ActivityTestRule<MainActivity> activityRule = new ActivityTestRule<>(MainActivity.class, false, false);

    private ArrayList<LayoutElementParcelable> initialItems;

    @Before
    public void setUp() {
        //Arrange test
        Context context = InstrumentationRegistry.getTargetContext();

        //Clear sharedPreference
        PreferenceManager.getDefaultSharedPreferences(context).edit().clear().commit();

        activityRule.launchActivity(null);
        initialItems = activityRule.getActivity().getCurrentMainFragment().getElementsList();
    }

    @Test
    public void testFilesList_itemClicked_listChanged() {
        //Act test
        onView(withId(R.id.home)).perform(click());
        onView(allOf(withId(R.id.filesListView), isDisplayed())).perform(click());
        ArrayList<LayoutElementParcelable> newItems = activityRule.getActivity().getCurrentMainFragment().getElementsList();

        //Assert test
        Assert.assertNotEquals(initialItems, newItems);
    }
}
