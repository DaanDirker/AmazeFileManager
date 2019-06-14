import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.rule.GrantPermissionRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.v4.app.ActivityCompat;

import com.amaze.filemanager.activities.MainActivity;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;

@RunWith(AndroidJUnit4.class)
public class PermissionsTest {
    //Arrange test
    private Context context;
    @Rule
    public GrantPermissionRule permissionRule = GrantPermissionRule.grant(android.Manifest
            .permission.WRITE_EXTERNAL_STORAGE);
    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<>(MainActivity.class, false, true);

    @Before
    public void act_Test() {
        //Act test
        context = InstrumentationRegistry.getTargetContext(); }

    @Test
    public void test_IfUserHasPermission_ToWrite() {
        //Assert test
        assertEquals(String.valueOf(true), ActivityCompat.checkSelfPermission(context,
                Manifest.permission.WRITE_EXTERNAL_STORAGE), PackageManager.PERMISSION_GRANTED);
    }
}
