package zalora.assignment.duckie.twitsplit.view.authen.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import zalora.assignment.duckie.twitsplit.R;
import zalora.assignment.duckie.twitsplit.adapter.ViewPagerAdapter;
import zalora.assignment.duckie.twitsplit.utility.NavigationDelegate;

public class LoginFragment extends Fragment {

    Button tryDemoButton;
    NavigationDelegate delegate;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);

        bindingControls(view);
        configUI();
        configControlEvents();

        return view;
    }

    private void bindingControls(View view) {
        tryDemoButton = (Button) view.findViewById(R.id.btn_try_demo);
    }

    private void configUI() {
        tryDemoButton.setTransformationMethod(null);
    }

    private void configControlEvents() {
        tryDemoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (delegate != null) {
                    delegate.moveToPageIndex(ViewPagerAdapter.DEMON_PAGE_INDEX);
                }
            }
        });
    }

    public void setNavigationDelegate(NavigationDelegate delegate) {
        this.delegate = delegate;
    }
}
