package com.ricky.avanti.widget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.AppCompatEditText;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * 自定义可删除EditText
 * Created by Ricky on 2017-4-28.
 */

public class CustomDeleteEditText extends AppCompatEditText {

    private Drawable mRightDrawable;

    public CustomDeleteEditText(Context context) {
        super(context);
        init();
    }

    public CustomDeleteEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CustomDeleteEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        Drawable mDrawable[] = this.getCompoundDrawables();//获取控件上下左右插入的图片
        mRightDrawable = mDrawable[2];
        setOnFocusChangeListener(new OnFocusChangeImpl());
        addTextChangedListener(new TextWatcherImpl());
    }


    private class OnFocusChangeImpl implements OnFocusChangeListener {

        @Override
        public void onFocusChange(View v, boolean hasFocus) {
            if (hasFocus) {
                //如果获取焦点
                boolean isNotNull = getText().toString().length() >= 1;
                setClearDrawableVisible(isNotNull);
            } else {
                setClearDrawableVisible(false);
            }
        }
    }

    private void setClearDrawableVisible(boolean isNotNull) {
        Drawable rightDrawable;
        if (isNotNull)
            rightDrawable = mRightDrawable;
        else
            rightDrawable = null;
        setCompoundDrawables(getCompoundDrawables()[0], getCompoundDrawables()[1]
                , rightDrawable, getCompoundDrawables()[3]);
    }

    private class TextWatcherImpl implements TextWatcher {

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            //修改之前
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            //修改之中
        }

        @Override
        public void afterTextChanged(Editable s) {
            boolean isNotNull = getText().toString().length() >= 1;
            setClearDrawableVisible(isNotNull);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_UP:
                int length1 = getWidth() - getPaddingRight();//删除图片右侧到EditText控件最左侧的距离
                int length2 = getWidth() - getTotalPaddingRight();//删除图片左侧到EditText控件最左侧的距离
                boolean isClean = (event.getX() > length2 && event.getX() < length1);
                if (isClean)
                    setText("");
                break;
            default:
                break;
        }
        return super.onTouchEvent(event);
    }
}
