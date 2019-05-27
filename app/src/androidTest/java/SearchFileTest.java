import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.amaze.filemanager.activities.MainActivity;
import com.amaze.filemanager.filesystem.HybridFileParcelable;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;

@RunWith(AndroidJUnit4.class)
public class SearchFileTest {

    private HybridFileParcelable fileOne;
    private HybridFileParcelable fileTwo;
    private HybridFileParcelable fileThree;
    
    @Rule
    public ActivityTestRule<MainActivity> activityRule = new ActivityTestRule<>(MainActivity.class, false, false);

    @Before
    public void beforeTesting()  {
        fileOne = new HybridFileParcelable("/storage/sdcard0/TestDirectory/TestFile.txt");
        fileTwo = new HybridFileParcelable("/storage/sdcard0/TestDirectory/TestFile2.txt");
        fileThree = new HybridFileParcelable("/storage/sdcard0/TestDirectory/TestFile3.txt");

    }

    @Test
    public void testMethod() {
        assertEquals("TestFile.txt", fileOne.getName());
        assertEquals("TestFile2.txt", fileTwo.getName());
        assertEquals("TestFile3.txt", fileThree.getName());
    }
}
