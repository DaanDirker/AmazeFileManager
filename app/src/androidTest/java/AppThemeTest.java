import android.support.test.runner.AndroidJUnit4;

import com.afollestad.materialdialogs.Theme;
import com.amaze.filemanager.utils.theme.AppTheme;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Calendar;

import static org.junit.Assert.*;

@RunWith(AndroidJUnit4.class)
public class AppThemeTest {

        @Test
        public void test_ifThemesAreValid() {
            //Arrange test and Act test
            AppTheme appThemeLight = AppTheme.getTheme(AppTheme.LIGHT_INDEX);
            AppTheme appThemeDark  = AppTheme.getTheme(AppTheme.DARK_INDEX);
            AppTheme appThemeTimed = AppTheme.getTheme(AppTheme.TIME_INDEX);
            AppTheme appThemeBlack = AppTheme.getTheme(AppTheme.BLACK_INDEX);

            //Assert test
            assertEquals(AppTheme.LIGHT, appThemeLight);
            assertEquals(AppTheme.DARK, appThemeDark);
            assertEquals(AppTheme.TIMED, appThemeTimed);
            assertEquals(AppTheme.BLACK, appThemeBlack);

        }

        @Test
        public void test_IfMaterialThemesAreValid() {
            //Arrange and Act test
            AppTheme materialThemeLight = AppTheme.getTheme(AppTheme.LIGHT_INDEX);
            AppTheme materialThemeDark  = AppTheme.getTheme(AppTheme.DARK_INDEX);
            AppTheme materialThemeTimed = AppTheme.getTheme(AppTheme.TIME_INDEX);
            AppTheme materialThemeBlack = AppTheme.getTheme(AppTheme.BLACK_INDEX);

            //Assert test
            int hour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
            if (hour <= 6 || hour >= 18) {
                assertEquals(Theme.DARK, materialThemeTimed.getMaterialDialogTheme());
            }
            else {
                assertEquals(Theme.LIGHT, materialThemeTimed.getMaterialDialogTheme());
            }
            assertEquals(Theme.LIGHT, materialThemeLight.getMaterialDialogTheme());
            assertEquals(Theme.DARK, materialThemeDark.getMaterialDialogTheme());
            assertEquals(Theme.DARK, materialThemeBlack.getMaterialDialogTheme());

        }

        @Test
        public void test_IfSimpleThemesAreValid(){
            //Arrange test and Act Test
            AppTheme simpleThemeLight  = AppTheme.getTheme(AppTheme.LIGHT_INDEX);
            AppTheme simpleThemeDark   = AppTheme.getTheme(AppTheme.DARK_INDEX);
            AppTheme simpleThemeTimed  = AppTheme.getTheme(AppTheme.TIME_INDEX);
            AppTheme simpleThemeBlack  = AppTheme.getTheme(AppTheme.BLACK_INDEX);

            //Assert test
            int hour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
            if (hour <= 6 || hour >= 18) {
                assertEquals(AppTheme.DARK, simpleThemeTimed.getSimpleTheme());
            }
            else
                assertEquals(AppTheme.LIGHT, simpleThemeTimed.getSimpleTheme());
            assertEquals(AppTheme.LIGHT, simpleThemeLight.getSimpleTheme());
            assertEquals(AppTheme.DARK, simpleThemeDark.getSimpleTheme());
            assertEquals(AppTheme.BLACK, simpleThemeBlack.getSimpleTheme());

        }

    }


