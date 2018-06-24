package zalora.assignment.duckie.twitsplit.view.twit_hub;

import android.view.View;

public interface TwitHubView {
    View getMainView();
    void goToNewTwit();

    void showEmptyTwit();
    void hideEmptyTwit();
}
