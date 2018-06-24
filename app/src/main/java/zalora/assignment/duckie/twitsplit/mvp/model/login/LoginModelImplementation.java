package zalora.assignment.duckie.twitsplit.mvp.model.login;

import io.realm.Realm;
import zalora.assignment.duckie.twitsplit.core.TwitterHelper;
import zalora.assignment.duckie.twitsplit.core.interfaces.SimpleCallback;
import zalora.assignment.duckie.twitsplit.mvp.presenter.login.LoginModelPresenter;

public class LoginModelImplementation implements LoginModel {

    LoginModelPresenter presenter;

    public LoginModelImplementation(LoginModelPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void handleLoginSuccess() {
        clearOldData();
        loadUserData();
    }

    private void clearOldData() {
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        realm.deleteAll();
        realm.commitTransaction();
    }

    private void loadUserData() {
        TwitterHelper.fillCurrentUserData(new SimpleCallback() {
            @Override
            public void onSuccess() {
                presenter.prepareDataCompleted();
            }

            @Override
            public void onFail() {
                presenter.prepareDataFail();
            }
        });
    }
}
