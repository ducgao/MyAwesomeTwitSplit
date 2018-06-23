package zalora.assignment.duckie.twitsplit.view.authen;

import android.support.v4.view.ViewPager;
import android.os.Bundle;

import zalora.assignment.duckie.twitsplit.R;
import zalora.assignment.duckie.twitsplit.presenter.authen.AuthenticationPresenterImplementation;
import zalora.assignment.duckie.twitsplit.presenter.authen.AuthenticationViewPresenter;
import zalora.assignment.duckie.twitsplit.utility.BaseActivity;

public class AuthenticationActivity extends BaseActivity implements AuthenticationView {

    ViewPager viewPager;
    AuthenticationViewPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bindingComponents();
        initPresenter();
        configControlEvents();
    }

    private void bindingComponents() {
        viewPager = (ViewPager) findViewById(R.id.vp_container);
    }

    private void initPresenter() {
        presenter = new AuthenticationPresenterImplementation(this);
        presenter.integrateViewPager(viewPager, getSupportFragmentManager());
    }

    private void configControlEvents() {

    }
}
