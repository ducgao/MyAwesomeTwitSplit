package zalora.assignment.duckie.twitsplit.model.login;

import io.realm.Realm;
import zalora.assignment.duckie.twitsplit.presenter.login.LoginModelPresenter;
import zalora.assignment.duckie.twitsplit.utility.interfaces.SimpleCallback;
import zalora.assignment.duckie.twitsplit.utility.TwitterHelper;

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
                //TODO
            }
        });
    }
}
