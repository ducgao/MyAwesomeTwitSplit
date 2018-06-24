package zalora.assignment.duckie.twitsplit.view.demo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import io.realm.Realm;
import zalora.assignment.duckie.twitsplit.R;
import zalora.assignment.duckie.twitsplit.adapter.ViewPagerAdapter;
import zalora.assignment.duckie.twitsplit.entity.Twit;
import zalora.assignment.duckie.twitsplit.utility.NavigationDelegate;
import zalora.assignment.duckie.twitsplit.view.twit_hub.TwitHubActivity;

/*
 * This class is quite simple so I don't need to apply MVP model to this
 * This just a fragment with one behavior: swipe up to open twit hub.
 */
public class DemoConfirmFragment extends Fragment implements View.OnTouchListener {

    Button backButton, continueButton;
    NavigationDelegate delegate;

    GestureDetector gestureDetector;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_demo_confirm, container, false);
        view.setOnTouchListener(this);

        bindingControls(view);
        configUI();
        configControlEvents();

        return view;
    }

    private void bindingControls(View view) {
        backButton = (Button) view.findViewById(R.id.btn_back);
        continueButton = (Button) view.findViewById(R.id.btn_continue);
    }

    private void configUI() {
        continueButton.setTransformationMethod(null);

        gestureDetector = new GestureDetector(getContext(), onGestureListener);
    }

    private void configControlEvents() {
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (delegate != null) {
                    delegate.moveToPageIndex(ViewPagerAdapter.LOGIN_PAGE_INDEX);
                }
            }
        });
    }

    private void handleOnSwipeUp() {
        prepareForDemonstrationSection();
        moveToDemonstrationSection();
    }

    private void prepareForDemonstrationSection() {
        Twit twit = new Twit();
        twit.id = 0;
        twit.postDate = 1529731480251l;
        twit.content = "This is a demo twit content, make one by tap on twitter button.";

        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        realm.deleteAll();
        realm.copyToRealm(twit);
        realm.commitTransaction();
    }

    private void moveToDemonstrationSection() {
        Intent intent = new Intent(getContext(), TwitHubActivity.class);
        startActivity(intent);

        getActivity().finish();
    }

    private GestureDetector.OnGestureListener onGestureListener = new GestureDetector.OnGestureListener() {
        @Override
        public boolean onDown(MotionEvent e) {
            return false;
        }

        @Override
        public void onShowPress(MotionEvent e) {}

        @Override
        public boolean onSingleTapUp(MotionEvent e) {
            return false;
        }

        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
            return false;
        }

        @Override
        public void onLongPress(MotionEvent e) {}

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            if (e1.getY() > e2.getY()) {
                handleOnSwipeUp();
            }
            return true;
        }
    };

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        gestureDetector.onTouchEvent(event);
        return true;
    }

    public void setNavigationDelegate(NavigationDelegate delegate) {
        this.delegate = delegate;
    }
}
