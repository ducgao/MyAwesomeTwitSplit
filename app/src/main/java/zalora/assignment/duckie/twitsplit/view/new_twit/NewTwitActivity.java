package zalora.assignment.duckie.twitsplit.view.new_twit;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;

import zalora.assignment.duckie.twitsplit.R;
import zalora.assignment.duckie.twitsplit.custom.BeautyTextInput;
import zalora.assignment.duckie.twitsplit.presenter.new_twit.NewTwitPresenterImplementation;
import zalora.assignment.duckie.twitsplit.presenter.new_twit.NewTwitViewPresenter;
import zalora.assignment.duckie.twitsplit.utility.BaseActivity;

public class NewTwitActivity extends BaseActivity implements NewTwitView {

    Button cancelButton, postButton;
    BeautyTextInput twitContent;

    NewTwitViewPresenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_twit);

        bindingComponents();
        initPresenter();
        configControlEvents();
    }

    private void bindingComponents() {
        postButton = (Button) findViewById(R.id.btn_post);
        cancelButton = (Button) findViewById(R.id.btn_cancel);
        twitContent = (BeautyTextInput) findViewById(R.id.bedt_input);
    }

    private void initPresenter() {
        presenter = new NewTwitPresenterImplementation(this);
    }

    private void configControlEvents() {
        postButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.postMessage(twitContent.getContent());
            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NewTwitActivity.this.finish();
            }
        });
    }

    @Override
    public void closeInstance() {
        this.finish();
    }

    @Override
    public void showInputError(int error) {
        this.twitContent.setError(error);
    }
}
