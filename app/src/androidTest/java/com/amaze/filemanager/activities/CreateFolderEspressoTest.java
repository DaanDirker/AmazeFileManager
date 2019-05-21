package com.amaze.filemanager.activities;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.assertTrue;

import com.amaze.filemanager.R;
import com.amaze.filemanager.fragments.MainFragment;
import com.amaze.filemanager.fragments.TabFragment;
import com.amaze.filemanager.utils.MainActivityHelper;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.File;

@RunWith(AndroidJUnit4.class)
public class CreateFolderEspressoTest {

    @Rule
    public ActivityTestRule<MainActivity> activityRule = new ActivityTestRule<>(MainActivity.class, false, true);

    private MainActivityHelper mainActivityHelper;
    private File folder;
    private Context context;

    private String unknownFolder = "alksjdlk1234124jdald12";

    @Before
    public void setUp() {
        context = InstrumentationRegistry.getTargetContext();
        mainActivityHelper = activityRule.getActivity().mainActivityHelper;

        MainFragment ma = (MainFragment) ((TabFragment) activityRule.getActivity()
                .getSupportFragmentManager().findFragmentById(R.id.content_frame)).getCurrentTabFragment();
        String path = ma.getCurrentPath();

        folder = new File(path + "/" + unknownFolder);
    }

    @Test
    public void test() {
        onView(withId(R.id.fab_expand_menu_button)).perform(click());
        onView(withId(R.id.menu_new_folder)).perform(click());
        onView(withId(R.id.singleedittext_input)).perform(typeText(unknownFolder));
        onView(withText(R.string.create)).perform(click());

        assertTrue(folder.exists());
    }

    @After
    public void tearDown() {
        if (folder.exists()) {
            folder.delete();
        }
    }
}
