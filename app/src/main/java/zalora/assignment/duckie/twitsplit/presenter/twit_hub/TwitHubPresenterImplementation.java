package zalora.assignment.duckie.twitsplit.presenter.twit_hub;

import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.List;

import zalora.assignment.duckie.twitsplit.R;
import zalora.assignment.duckie.twitsplit.adapter.TwitHubAdapter;
import zalora.assignment.duckie.twitsplit.entity.Twit;
import zalora.assignment.duckie.twitsplit.model.twit_hub.TwitHubModelImplementation;
import zalora.assignment.duckie.twitsplit.model.twit_hub.TwitHubModel;
import zalora.assignment.duckie.twitsplit.view.twit_hub.TwitHubView;

public class TwitHubPresenterImplementation implements TwitHubViewPresenter, TwitHubModelPresenter {
    TwitHubView view;
    TwitHubModel model;

    TwitHubAdapter adapter;

    Snackbar snackbar;

    public TwitHubPresenterImplementation(TwitHubView view) {
        this.view = view;
        this.model = new TwitHubModelImplementation(this);
    }

    @Override
    public void loadTwits() {
        this.model.loadTwits();
    }

    @Override
    public void integrateRecycleView(RecyclerView recycleView) {
        adapter = new TwitHubAdapter();
        recycleView.setAdapter(adapter);
    }

    @Override
    public View.OnClickListener getPostButtonClickHandler() {
        return onPostButtonClickHandler;
    }

    @Override
    public void loadTwitsCompleted(List<Twit> twits) {
        this.adapter.setNewData(twits);
    }

    /*
     * private methods
     */
    private void showNewTwitConfirmMessage() {
        View mainView = view.getMainView();
        snackbar = Snackbar.make(mainView, R.string.twit_hub_add_new_twit_confirm_message, Snackbar.LENGTH_LONG);
        snackbar.show();
    }

    private boolean readyForNewTwit() {
        return snackbar != null && snackbar.isShown();
    }

    private void goToNewTwit() {
        view.goToNewTwit();
    }

    /*
     * integration objects
     */
    View.OnClickListener onPostButtonClickHandler = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (readyForNewTwit()) {
                goToNewTwit();
            }
            else {
                showNewTwitConfirmMessage();
            }
        }
    };
}

