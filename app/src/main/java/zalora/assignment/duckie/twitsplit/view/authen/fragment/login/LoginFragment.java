package zalora.assignment.duckie.twitsplit.view.authen.fragment.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.twitter.sdk.android.core.identity.TwitterLoginButton;

import zalora.assignment.duckie.twitsplit.R;
import zalora.assignment.duckie.twitsplit.presenter.authen.fragment.login.LoginPresenterImplementation;
import zalora.assignment.duckie.twitsplit.presenter.authen.fragment.login.LoginViewPresenter;
import zalora.assignment.duckie.twitsplit.utility.NavigationDelegate;
import zalora.assignment.duckie.twitsplit.view.twit_hub.TwitHubActivity;

public class LoginFragment extends Fragment implements LoginView {

    Button tryDemoButton;
    TwitterLoginButton loginButton;

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
        loginButton = (TwitterLoginButton) view.findViewById(R.id.btn_login);
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

        loginButton.setCallback(presenter.getTwitterButtonCallback());
    }

    public void setNavigationDelegate(NavigationDelegate delegate) {
        this.delegate = delegate;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        loginButton.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void moveToTwitHub() {
        Intent intent = new Intent(getContext(), TwitHubActivity.class);
        startActivity(intent);
        getActivity().finish();
    }
}
