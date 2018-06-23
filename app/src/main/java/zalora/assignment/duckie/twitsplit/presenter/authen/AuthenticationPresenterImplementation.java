package zalora.assignment.duckie.twitsplit.presenter.authen;

import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;

import zalora.assignment.duckie.twitsplit.adapter.ViewPagerAdapter;
import zalora.assignment.duckie.twitsplit.view.authen.AuthenticationView;

public class AuthenticationPresenterImplementation implements AuthenticationViewPresenter {

    AuthenticationView view;

    ViewPagerAdapter adapter;

    public AuthenticationPresenterImplementation(AuthenticationView view) {
        this.view = view;
    }

    @Override
    public void integrateViewPager(ViewPager viewPager, FragmentManager fragmentManager) {
        adapter = new ViewPagerAdapter(fragmentManager, viewPager);
        viewPager.setAdapter(adapter);
    }

    /*
     * private methods
     */
}
