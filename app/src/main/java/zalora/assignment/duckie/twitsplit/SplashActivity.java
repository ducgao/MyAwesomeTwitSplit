package zalora.assignment.duckie.twitsplit;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.twitter.sdk.android.core.TwitterCore;
import com.twitter.sdk.android.core.TwitterSession;

import zalora.assignment.duckie.twitsplit.utility.BaseActivity;
import zalora.assignment.duckie.twitsplit.utility.interfaces.SimpleCallback;
import zalora.assignment.duckie.twitsplit.utility.TwitterHelper;
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

        handleLoggedUser();
    }

    private void moveToLogin() {
        moveTo(AuthenticationActivity.class);
        finish();
    }

    private void moveToTwitHub() {
        moveTo(TwitHubActivity.class);
        finish();
    }

    private void handleLoggedUser() {
        TwitterHelper.fillCurrentUserData(new SimpleCallback() {
            @Override
            public void onSuccess() {
                moveToTwitHub();
            }

            @Override
            public void onFail() {
                moveToLogin();
            }
        });
    }
}
