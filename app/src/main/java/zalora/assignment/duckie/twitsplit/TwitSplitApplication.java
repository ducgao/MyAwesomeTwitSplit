package zalora.assignment.duckie.twitsplit;

import android.app.Application;

import io.realm.Realm;

public class TwitSplitApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);
    }
}
