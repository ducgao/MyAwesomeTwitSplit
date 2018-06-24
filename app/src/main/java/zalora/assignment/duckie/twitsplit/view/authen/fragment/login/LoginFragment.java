package zalora.assignment.duckie.twitsplit.view.authen.fragment.login;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import zalora.assignment.duckie.twitsplit.R;
import zalora.assignment.duckie.twitsplit.adapter.ViewPagerAdapter;
import zalora.assignment.duckie.twitsplit.presenter.authen.fragment.login.LoginPresenterImplementation;
import zalora.assignment.duckie.twitsplit.presenter.authen.fragment.login.LoginViewPresenter;
import zalora.assignment.duckie.twitsplit.utility.NavigationDelegate;

public class LoginFragment extends Fragment implements LoginView {

    Button tryDemoButton;

    NavigationDelegate delegate;
    LoginViewPresenter presenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);

        bindingControls(view);
        initPresenter();
        configUI();
        configControlEvents();

        return view;
    }

    private void bindingControls(View view) {
        tryDemoButton = (Button) view.findViewById(R.id.btn_try_demo);
    }

    private void initPresenter() {
        presenter = new LoginPresenterImplementation(this);
        presenter.setNavigationDelegate(this.delegate);
    }

    private void configUI() {
        tryDemoButton.setTransformationMethod(null);
    }

    private void configControlEvents() {
        tryDemoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.moveToDemoPage();
            }
        });
    }

    public void setNavigationDelegate(NavigationDelegate delegate) {
        this.delegate = delegate;
    }
}
