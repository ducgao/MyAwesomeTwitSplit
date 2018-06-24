package zalora.assignment.duckie.twitsplit.custom;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.util.AttributeSet;
import android.view.View;

import zalora.assignment.duckie.twitsplit.R;

public class BeautyTextInput extends TextInputLayout {

    Context context;

    View rootView;
    TextInputLayout editTextWrapper;
    TextInputEditText editText;

    public BeautyTextInput(Context context) {
        super(context);
        init(context);
    }

    public BeautyTextInput(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context) {
        init(context, null);
    }

    private void init(Context context, AttributeSet attrs) {
        this.context = context;

        this.rootView = inflate(context, R.layout.layout_beauty_input, this);
        this.editTextWrapper = rootView.findViewById(R.id.edt_input_wrapper);
        this.editText = rootView.findViewById(R.id.edt_input);

        String hint = getAttributeStringValue(attrs, R.styleable.BeautyTextInput_hint);
        if (hint != null) {
            this.editTextWrapper.setHint(hint);
        }
    }

    private String getAttributeStringValue(AttributeSet attrs, int styleableID) {
        if (attrs == null) return null;

        String returnString = null;
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.BeautyTextInput, 0, 0);
        try {
            int hintResourceID = ta.getInteger(styleableID, 0);
            returnString = context.getString(hintResourceID);
        }
        catch (Exception ex) {
            returnString = ta.getString(styleableID);
        }
        finally {
            ta.recycle();
            return returnString;
        }
    }

    public String getContent() {
        return editText.getText().toString();
    }

    public void setError(String error) {
        this.editTextWrapper.setError(error);
    }

    public void setError(int error) {
        String errorString = context.getString(error);
        setError(errorString);
    }
}
