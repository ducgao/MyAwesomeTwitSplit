package zalora.assignment.duckie.twitsplit.presenter.login;

import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.TwitterSession;

import zalora.assignment.duckie.twitsplit.adapter.ViewPagerAdapter;
import zalora.assignment.duckie.twitsplit.model.login.LoginModel;
import zalora.assignment.duckie.twitsplit.model.login.LoginModelImplementation;
import zalora.assignment.duckie.twitsplit.utility.interfaces.NavigationDelegate;
import zalora.assignment.duckie.twitsplit.view.login.LoginView;

public class LoginPresenterImplementation implements LoginViewPresenter, LoginModelPresenter {
    LoginView view;
    LoginModel model;
    NavigationDelegate delegate;

    public LoginPresenterImplementation(LoginView view) {
        this.view = view;
        this.model = new LoginModelImplementation(this);
    }

    @Override
    public void moveToDemoPage() {
        if (delegate != null) {
            delegate.moveToPageIndex(ViewPagerAdapter.DEMON_PAGE_INDEX);
        }
    }

    @Override
    public void setNavigationDelegate(NavigationDelegate delegate) {
        this.delegate = delegate;
    }

    @Override
    public Callback<TwitterSession> getTwitterButtonCallback() {
        return twitterSessionCallback;
    }

    private void handleOnLoginSuccess() {
        model.handleLoginSuccess();
    }

    private Callback<TwitterSession> twitterSessionCallback = new Callback<TwitterSession>() {
        @Override
        public void success(Result<TwitterSession> result) {
            if (result.data != null) {
                handleOnLoginSuccess();
            }
        }

        @Override
        public void failure(TwitterException exception) {
            //TODO
        }
    };

    @Override
    public void prepareDataCompleted() {
        view.moveToTwitHub();
    }
}
