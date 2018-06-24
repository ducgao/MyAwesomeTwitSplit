package zalora.assignment.duckie.twitsplit.view.login;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.twitter.sdk.android.core.identity.TwitterLoginButton;

import zalora.assignment.duckie.twitsplit.R;
import zalora.assignment.duckie.twitsplit.presenter.login.LoginPresenterImplementation;
import zalora.assignment.duckie.twitsplit.presenter.login.LoginViewPresenter;
import zalora.assignment.duckie.twitsplit.utility.Utils;
import zalora.assignment.duckie.twitsplit.utility.interfaces.NavigationDelegate;
import zalora.assignment.duckie.twitsplit.view.twit_hub.TwitHubActivity;

public class LoginFragment extends Fragment implements LoginView {

    Button tryDemoButton;
    TwitterLoginButton loginButton;

    NavigationDelegate delegate;
    LoginViewPresenter presenter;

    ProgressDialog progressDialog;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);

        bindingControls(view);
        initPresenter();
        configUI();
        configControlEvents();

        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        if (progressDialog!= null && progressDialog.isShowing()) {
            progressDialog.dismiss();
            progressDialog = null;
        }
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

    @Override
    public void showLoading() {
        if (progressDialog == null) {
            progressDialog = new ProgressDialog(getContext());
            progressDialog.setMessage(getString(R.string.dialog_processing_message));
            progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progressDialog.setIndeterminate(true);
            progressDialog.setCancelable(false);
        }

        progressDialog.show();
    }

    @Override
    public void hideLoading() {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
    }

    @Override
    public void showError(int error) {
        Utils.showErrorDialog(getContext(), error);
    }
}
