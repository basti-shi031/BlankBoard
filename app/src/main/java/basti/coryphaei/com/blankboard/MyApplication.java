package basti.coryphaei.com.blankboard;

import android.app.Application;

import com.squareup.leakcanary.LeakCanary;

/**
 * Created by Bowen on 2015-11-04.
 */
public class MyApplication extends Application{

    @Override
    public void onCreate() {
        super.onCreate();
        LeakCanary.install(this);
    }
}
