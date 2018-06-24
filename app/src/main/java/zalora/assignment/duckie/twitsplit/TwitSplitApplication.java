package zalora.assignment.duckie.twitsplit;

import android.app.Application;
import android.util.Log;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.twitter.sdk.android.core.DefaultLogger;
import com.twitter.sdk.android.core.Twitter;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.twitter.sdk.android.core.TwitterConfig;

import io.realm.Realm;
import zalora.assignment.duckie.twitsplit.entity.User;

public class TwitSplitApplication extends Application {

    private static User user;

    @Override
    public void onCreate() {
        super.onCreate();

        initFresco();
        initRealm();
        initTwitter();
    }

    private void initFresco() {
        Fresco.initialize(getApplicationContext());
    }

    private void initRealm() {
        Realm.init(this);
    }

    private void initTwitter() {
        String consumerKey = getString(R.string.com_twitter_sdk_android_CONSUMER_KEY);
        String consumerSecret = getString(R.string.com_twitter_sdk_android_CONSUMER_SECRET);
        TwitterAuthConfig twitterAuthConfig = new TwitterAuthConfig(consumerKey, consumerSecret);

        TwitterConfig config = new TwitterConfig.Builder(this)
                .logger(new DefaultLogger(Log.DEBUG))
                .twitterAuthConfig(twitterAuthConfig)
                .debug(true)
                .build();
        Twitter.initialize(config);
    }

    public static User getUser() {
        if (user == null) return User.getFakeUser();
        return user;
    }

    public static void setUser(User user) {
        TwitSplitApplication.user = user;
    }
}
