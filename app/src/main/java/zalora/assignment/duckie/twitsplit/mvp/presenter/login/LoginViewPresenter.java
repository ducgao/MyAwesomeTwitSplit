package zalora.assignment.duckie.twitsplit.mvp.presenter.login;

import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.TwitterSession;

import zalora.assignment.duckie.twitsplit.core.interfaces.NavigationDelegate;

public interface LoginViewPresenter {
    void moveToDemoPage();
    void setNavigationDelegate(NavigationDelegate delegate);
    Callback<TwitterSession> getTwitterButtonCallback();
}
