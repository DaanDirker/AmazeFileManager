package com.amaze.filemanager.activities;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.amaze.filemanager.utils.MainActivityHelper;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.File;

@RunWith(AndroidJUnit4.class)
public class FolderCheckEspressoTest {

    @Rule
    public ActivityTestRule<MainActivity> activityRule = new ActivityTestRule<>(MainActivity.class, false, true);

    private MainActivityHelper mainActivityHelper;
    private String unknownFolder = "/ak21d19d1dsa124";
    private Context context;
    private File file;

    @Before
    public void setUp() {
        context = InstrumentationRegistry.getTargetContext();
        mainActivityHelper = activityRule.getActivity().mainActivityHelper;
    }

    @Test
    public void testFolder_folderIsNotExistent_doesNotExist() {
        file = new File(activityRule.getActivity().getCurrentMainFragment().getCurrentPath() + unknownFolder);

        int checkResult = mainActivityHelper.checkFolder(file, context);
        Assert.assertEquals(MainActivityHelper.DOESNT_EXIST, checkResult);
    }

    @Test
    public void testFolder_folderAlreadyExists_canCreateFile() {
        file = new File(activityRule.getActivity().getCurrentMainFragment().getCurrentPath());

        int checkResult = mainActivityHelper.checkFolder(file, context);
        Assert.assertEquals(MainActivityHelper.CAN_CREATE_FILES, MainActivityHelper.WRITABLE_OR_ON_SDCARD, checkResult);
    }
}
