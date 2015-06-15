package andres.dev.com.menurestaurant;

import android.app.Application;
import android.content.Context;

public class MenuApplication extends Application {

    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
    }

    public static Context getAppContext() {
        return mContext;
    }
}