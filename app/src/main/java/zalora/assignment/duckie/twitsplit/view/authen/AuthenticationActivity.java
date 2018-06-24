package zalora.assignment.duckie.twitsplit.view.authen;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v4.app.Fragment;
import android.os.Bundle;

import java.util.List;

import zalora.assignment.duckie.twitsplit.R;
import zalora.assignment.duckie.twitsplit.presenter.authen.AuthenticationPresenterImplementation;
import zalora.assignment.duckie.twitsplit.presenter.authen.AuthenticationViewPresenter;
import zalora.assignment.duckie.twitsplit.utility.BaseActivity;
import zalora.assignment.duckie.twitsplit.view.login.LoginFragment;

public class AuthenticationActivity extends BaseActivity implements AuthenticationView {

    ViewPager viewPager;
    AuthenticationViewPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bindingComponents();
        initPresenter();
    }

    private void bindingComponents() {
        viewPager = (ViewPager) findViewById(R.id.vp_container);
    }

    private void initPresenter() {
        presenter = new AuthenticationPresenterImplementation(this);
        presenter.integrateViewPager(viewPager, getSupportFragmentManager());
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        List<Fragment> fragments = getSupportFragmentManager().getFragments();
        for (Fragment fragment : fragments) {
            if (fragment instanceof LoginFragment) {
                fragment.onActivityResult(requestCode, resultCode, data);
            }
        }
    }
}
