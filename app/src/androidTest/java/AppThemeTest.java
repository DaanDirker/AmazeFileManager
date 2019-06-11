
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
            AppTheme appThemeLight = AppTheme.getTheme(AppTheme.LIGHT_INDEX);
            assertEquals(AppTheme.LIGHT, appThemeLight);

            AppTheme appThemeDark = AppTheme.getTheme(AppTheme.DARK_INDEX);
            assertEquals(AppTheme.DARK, appThemeDark);

            AppTheme appThemeTimed = AppTheme.getTheme(AppTheme.TIME_INDEX);
            assertEquals(AppTheme.TIMED, appThemeTimed);

            AppTheme appThemeBlack = AppTheme.getTheme(AppTheme.BLACK_INDEX);
            assertEquals(AppTheme.BLACK, appThemeBlack);

        }

        @Test
        public void test_IfMaterialThemesAreValid() {
            AppTheme materialThemeLight = AppTheme.getTheme(AppTheme.LIGHT_INDEX);
            assertEquals(Theme.LIGHT, materialThemeLight.getMaterialDialogTheme());

            AppTheme materialThemeDark = AppTheme.getTheme(AppTheme.DARK_INDEX);
            assertEquals(Theme.DARK, materialThemeDark.getMaterialDialogTheme());

            AppTheme materialThemeTimed = AppTheme.getTheme(AppTheme.TIME_INDEX);
            int hour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
            if (hour <= 6 || hour >= 18) {
                assertEquals(Theme.DARK, materialThemeTimed.getMaterialDialogTheme());
            }
            else
                assertEquals(Theme.LIGHT, materialThemeTimed.getMaterialDialogTheme());

            AppTheme materialThemeBlack = AppTheme.getTheme(AppTheme.BLACK_INDEX);
            assertEquals(Theme.DARK, materialThemeBlack.getMaterialDialogTheme());

        }

        @Test
        public void test_IfSimpleThemesAreValid(){
            AppTheme simpleThemeLight  = AppTheme.getTheme(AppTheme.LIGHT_INDEX);
            assertEquals(AppTheme.LIGHT, simpleThemeLight.getSimpleTheme());

            AppTheme simpleThemeDark  = AppTheme.getTheme(AppTheme.DARK_INDEX);
            assertEquals(AppTheme.DARK, simpleThemeDark.getSimpleTheme());

            AppTheme simpleThemeTimed  = AppTheme.getTheme(AppTheme.TIME_INDEX);
            int hour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
            if (hour <= 6 || hour >= 18) {
                assertEquals(AppTheme.DARK, simpleThemeTimed.getSimpleTheme());
            }
            else
                assertEquals(AppTheme.LIGHT, simpleThemeTimed.getSimpleTheme());

            AppTheme simpleThemeBlack  = AppTheme.getTheme(AppTheme.BLACK_INDEX);
            assertEquals(AppTheme.BLACK, simpleThemeBlack.getSimpleTheme());

        }

    }


