package zalora.assignment.duckie.twitsplit.mvp.view.new_twit;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;

import zalora.assignment.duckie.twitsplit.R;
import zalora.assignment.duckie.twitsplit.custom.BeautyTextInput;
import zalora.assignment.duckie.twitsplit.mvp.presenter.new_twit.NewTwitPresenterImplementation;
import zalora.assignment.duckie.twitsplit.mvp.presenter.new_twit.NewTwitViewPresenter;
import zalora.assignment.duckie.twitsplit.utility.BaseActivity;

public class NewTwitActivity extends BaseActivity implements NewTwitView {

    Button cancelButton, postButton;
    BeautyTextInput twitContent;

    NewTwitViewPresenter presenter;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_twit);

        bindingComponents();
        initPresenter();
        configControlEvents();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
            progressDialog = null;
        }
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

    @Override
    public void showLoading() {
        if (progressDialog == null) {
            progressDialog = new ProgressDialog(NewTwitActivity.this);
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
}
