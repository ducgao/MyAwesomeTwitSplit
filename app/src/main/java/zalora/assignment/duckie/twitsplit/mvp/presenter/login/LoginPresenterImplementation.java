package zalora.assignment.duckie.twitsplit.mvp.presenter.login;

import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.TwitterSession;

import zalora.assignment.duckie.twitsplit.R;
import zalora.assignment.duckie.twitsplit.adapter.ViewPagerAdapter;
import zalora.assignment.duckie.twitsplit.core.interfaces.NavigationDelegate;
import zalora.assignment.duckie.twitsplit.mvp.model.login.LoginModel;
import zalora.assignment.duckie.twitsplit.mvp.model.login.LoginModelImplementation;
import zalora.assignment.duckie.twitsplit.mvp.view.login.LoginView;

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
        view.showLoading();
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
            view.hideLoading();
            view.showError(R.string.dialog_twitter_token_error);
        }
    };

    @Override
    public void prepareDataCompleted() {
        view.moveToTwitHub();
    }

    @Override
    public void prepareDataFail() {
        view.hideLoading();
        view.showError(R.string.dialog_twitter_token_error);
    }
}
