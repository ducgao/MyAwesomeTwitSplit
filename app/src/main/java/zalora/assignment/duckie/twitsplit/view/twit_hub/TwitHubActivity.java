package zalora.assignment.duckie.twitsplit.view.twit_hub;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import zalora.assignment.duckie.twitsplit.R;
import zalora.assignment.duckie.twitsplit.presenter.twit_hub.TwitHubPresenterImplementation;
import zalora.assignment.duckie.twitsplit.presenter.twit_hub.TwitHubViewPresenter;
import zalora.assignment.duckie.twitsplit.utility.BaseActivity;
import zalora.assignment.duckie.twitsplit.view.new_twit.NewTwitActivity;

public class TwitHubActivity extends BaseActivity implements TwitHubView {

    TwitHubViewPresenter presenter;

    RecyclerView twitHubView;
    FloatingActionButton newPostButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_twit_hub);

        bindingComponents();
        initPresenter();
        configControlEvents();
    }

    @Override
    protected void onResume() {
        super.onResume();

        presenter.loadTwits();
    }

    private void bindingComponents() {
        twitHubView = (RecyclerView) findViewById(R.id.rv_twit_hub);
        newPostButton = (FloatingActionButton) findViewById(R.id.fab_new_twit);
    }

    private void configControlEvents() {
        newPostButton.setOnClickListener(presenter.getPostButtonClickHandler());
    }

    private void initPresenter() {
        presenter = new TwitHubPresenterImplementation(this);
        presenter.integrateRecycleView(twitHubView);
    }

    @Override
    public View getMainView() {
        return getWindow().getCurrentFocus();
    }

    @Override
    public void goToNewTwit() {
        moveTo(NewTwitActivity.class);
    }
}