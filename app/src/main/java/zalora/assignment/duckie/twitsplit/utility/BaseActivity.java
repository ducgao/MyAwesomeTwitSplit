package zalora.assignment.duckie.twitsplit.utility;

import android.app.ActivityManager;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import zalora.assignment.duckie.twitsplit.R;
import zalora.assignment.duckie.twitsplit.view.new_twit.NewTwitActivity;

public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        configTaskDescription();
    }

    protected void configTaskDescription() {
        String appName = getString(R.string.app_name);
        setTaskDescription(new ActivityManager.TaskDescription(appName, null, Color.WHITE));
    }

    protected void moveTo(Class<?> cls) {
        Intent intent = new Intent(BaseActivity.this, cls);
        startActivity(intent);
    }
}
