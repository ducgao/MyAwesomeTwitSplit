package zalora.assignment.duckie.twitsplit.presenter.login;

import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.TwitterSession;

import zalora.assignment.duckie.twitsplit.utility.NavigationDelegate;

public interface LoginViewPresenter {
    void moveToDemoPage();
    void setNavigationDelegate(NavigationDelegate delegate);
    Callback<TwitterSession> getTwitterButtonCallback();
}
