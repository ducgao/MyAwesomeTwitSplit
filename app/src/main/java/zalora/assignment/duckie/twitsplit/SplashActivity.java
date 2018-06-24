package zalora.assignment.duckie.twitsplit;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.twitter.sdk.android.core.TwitterAuthToken;
import com.twitter.sdk.android.core.TwitterCore;
import com.twitter.sdk.android.core.TwitterSession;

import zalora.assignment.duckie.twitsplit.utility.BaseActivity;
import zalora.assignment.duckie.twitsplit.view.authen.AuthenticationActivity;
import zalora.assignment.duckie.twitsplit.view.twit_hub.TwitHubActivity;

public class SplashActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        checkTwitterStatus();
    }

    private void checkTwitterStatus() {
        TwitterSession session = TwitterCore.getInstance().getSessionManager().getActiveSession();
        if (session == null) {
            moveToLogin();
            return;
        }

        moveToTwitHub();
    }

    private void moveToLogin() {
        moveTo(AuthenticationActivity.class);
        finish();
    }

    private void moveToTwitHub() {
        moveTo(TwitHubActivity.class);
        finish();
    }
}
