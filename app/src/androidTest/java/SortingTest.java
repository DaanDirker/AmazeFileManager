import android.support.test.runner.AndroidJUnit4;

import com.amaze.filemanager.adapters.data.LayoutElementParcelable;
import com.amaze.filemanager.utils.OpenMode;
import com.amaze.filemanager.utils.files.FileListSorter;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.*;

@RunWith(AndroidJUnit4.class)
public class SortingTest {

        @Test
        public void test_IfFilesAreSortedByTitle() {
            FileListSorter fileSorter = new FileListSorter(-1,0,1);

            /** This tests if the first file length is bigger than the length of the second file.
             *  Expected result: positive
             */
            LayoutElementParcelable firstFile = new LayoutElementParcelable("mockDataFileBigger.txt", "C:\\AmazeFileManager\\mockDataFileBigger", "user",
                    "symlink", "100", 123L, true,
                    "1234", false,false, OpenMode.UNKNOWN);
            LayoutElementParcelable secondFile = new LayoutElementParcelable("mockDataFile.txt", "C:\\AmazeFileManager\\mockDataFile", "user",
                    "symlink", "100", 123L, true,
                    "1234", false,false, OpenMode.UNKNOWN);
            assertThat(fileSorter.compare(firstFile, secondFile), greaterThan(0));

            /** This tests if the third file length is smaller than the length of the fourth file.
             *  Expected result: negative
             */
            LayoutElementParcelable thirdFile = new LayoutElementParcelable("mockDataFile.txt", "C:\\AmazeFileManager\\mockDataFile", "user",
                    "symlink", "100", 123L, true,
                    "1234", false,false, OpenMode.UNKNOWN);
            LayoutElementParcelable fourthFile = new LayoutElementParcelable("mockDataFileBigger.txt", "C:\\AmazeFileManager\\mockDataFileBigger", "user",
                    "symlink", "100", 123L, true,
                    "1234", false,false, OpenMode.UNKNOWN);
            assertThat(fileSorter.compare(thirdFile, fourthFile), lessThan(0));

            /** This tests if the fifth file length is the same as the length of the sixth file.
             *  Expected result: zero
             */
            LayoutElementParcelable fifthFile = new LayoutElementParcelable("mockDataFile.txt", "C:\\AmazeFileManager\\mockDataFile", "user",
                    "symlink", "100", 123L, true,
                    "1234", false,false, OpenMode.UNKNOWN);
            LayoutElementParcelable sixthFile = new LayoutElementParcelable("mockDataFile.txt", "C:\\AmazeFileManager\\mockDataFile", "user",
                    "symlink", "100", 123L, true,
                    "1234", false,false, OpenMode.UNKNOWN);
            assertEquals(fileSorter.compare(fifthFile, sixthFile), 0);

        }
    }

