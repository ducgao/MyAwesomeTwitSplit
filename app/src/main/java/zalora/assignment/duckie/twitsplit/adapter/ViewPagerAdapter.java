package zalora.assignment.duckie.twitsplit.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;

import zalora.assignment.duckie.twitsplit.utility.NavigationDelegate;
import zalora.assignment.duckie.twitsplit.view.authen.fragment.demo.DemoConfirmFragment;
import zalora.assignment.duckie.twitsplit.view.authen.fragment.login.LoginFragment;

public class ViewPagerAdapter extends FragmentStatePagerAdapter implements NavigationDelegate {

    public final static int LOGIN_PAGE_INDEX = 0;
    public final static int DEMON_PAGE_INDEX = 1;

    LoginFragment loginFragment;
    DemoConfirmFragment demoConfirmFragment;

    ViewPager viewPager;

    public ViewPagerAdapter(FragmentManager fm, ViewPager viewPager) {
        super(fm);
        this.viewPager = viewPager;

        initFragments();
    }

    private void initFragments() {
        loginFragment = new LoginFragment();
        loginFragment.setNavigationDelegate(this);

        demoConfirmFragment = new DemoConfirmFragment();
        demoConfirmFragment.setNavigationDelegate(this);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case LOGIN_PAGE_INDEX:
                return loginFragment;
            case DEMON_PAGE_INDEX:
                return demoConfirmFragment;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public void moveToPageIndex(int pageIndex) {
        viewPager.setCurrentItem(pageIndex);
    }
}
