package zalora.assignment.duckie.twitsplit.presenter.authen.fragment.login;

import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.TwitterSession;

import zalora.assignment.duckie.twitsplit.adapter.ViewPagerAdapter;
import zalora.assignment.duckie.twitsplit.utility.NavigationDelegate;
import zalora.assignment.duckie.twitsplit.view.authen.fragment.login.LoginView;

public class LoginPresenterImplementation implements LoginViewPresenter {
    LoginView view;
    NavigationDelegate delegate;

    public LoginPresenterImplementation(LoginView view) {
        this.view = view;
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

    private Callback<TwitterSession> twitterSessionCallback = new Callback<TwitterSession>() {
        @Override
        public void success(Result<TwitterSession> result) {
            if (result.data != null) {
                view.moveToTwitHub();
            }
        }

        @Override
        public void failure(TwitterException exception) {
            //TODO
        }
    };
}
