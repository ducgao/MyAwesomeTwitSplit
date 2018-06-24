package zalora.assignment.duckie.twitsplit.mvp.view.twit_hub;

import android.view.View;

public interface TwitHubView {
    View getMainView();

    void goToNewTwit();
    void goToAuthentication();

    void showEmptyTwit();
    void hideEmptyTwit();
}
