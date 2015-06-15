package andres.dev.com.menurestaurant.Provider;

import android.content.SharedPreferences;

import andres.dev.com.menurestaurant.MenuApplication;
import andres.dev.com.menurestaurant.Utils.SharedPrefKey;


public class SharePreference {

    public static void saveDataApplication(SharedPrefKey preKey, String value){
        SharedPreferences sharedPreference = getCustomizePref();
        SharedPreferences.Editor editor = sharedPreference.edit();
        editor.putString(preKey.toString(), value);
        editor.commit();
    }

    public static String getApplicationValue(SharedPrefKey prefKey){
        SharedPreferences customize_pref = getCustomizePref();
        return customize_pref.getString(prefKey.toString(), "");
    }


    private static SharedPreferences getCustomizePref() {
        return MenuApplication.getAppContext().getSharedPreferences(SharedPrefKey.APP_DATA_FILE.toString(), 0);
    }
}
