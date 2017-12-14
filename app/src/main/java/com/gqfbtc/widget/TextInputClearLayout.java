package com.gqfbtc.widget;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.gqfbtc.R;


/**
 * package : com.junseek.version40.widget<br>
 * description:<B>一个带有清除输入文字内容的布局，在一些带有输入框的页面中，需要点击小叉清空输入的内容，则使用此布局</B><br>
 * date : 17/8/1 11:40 <br>
 *
 * @author XieGuoKang
 * @version v4.0
 */
public class TextInputClearLayout extends RelativeLayout {
    private EditText mEditText;
    private ImageView clearImageView;

    public TextInputClearLayout(Context context) {
        this(context, null);
    }

    public TextInputClearLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TextInputClearLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        setGravity(Gravity.END | Gravity.CENTER_VERTICAL);
        clearImageView = new ImageView(context);
        clearImageView.setImageResource(R.drawable.icon_delete);
        clearImageView.setId(R.id.delete);
        clearImageView.setScaleType(ImageView.ScaleType.FIT_END);
        LayoutParams params = new LayoutParams(160, 40);
        params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        params.addRule(CENTER_VERTICAL);
        params.addRule(Gravity.RIGHT);
        addView(clearImageView, params);
        clearImageView.setVisibility(GONE);
        clearImageView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mEditText != null) {
                    mEditText.getEditableText().clear();
                    mEditText.requestFocus();
                }
            }
        });
    }

    @Override
    public void addView(View child, int index, ViewGroup.LayoutParams params) {
        if (child instanceof EditText) {
            setEditText((EditText) child);
            LayoutParams p = (LayoutParams) params;
            p.addRule(LEFT_OF, R.id.delete);
            p.addRule(CENTER_VERTICAL);
        }
        super.addView(child, index, params);

    }

    public void setEditText(EditText editText) {
        if (mEditText != null) {
            throw new IllegalArgumentException("We already have an EditText, can only have one");
        }

        this.mEditText = editText;

        mEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                clearImageView.setVisibility(s.length() == 0 ? GONE : VISIBLE);
            }
        });
    }
}