package zalora.assignment.duckie.twitsplit.presenter.authen.fragment.login;

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
}
