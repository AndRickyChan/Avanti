package com.ricky.avanti.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

import com.ricky.avanti.R;


/**
 * 自定义圆形进度条
 * Created by Ricky on 2017-4-28.
 */

public class RoundProgressBar extends View {

    private Context mContext;

    private Paint mRoundPaint;
    private Paint mProgressPaint;
    private int roundColor;
    private int progressColor;
    private float roundWidth;

    private int max;
    private float progress;

    public RoundProgressBar(Context context) {
        this(context, null);
    }

    public RoundProgressBar(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RoundProgressBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.RoundProgressBar);
        roundColor = typedArray.getColor(R.styleable.RoundProgressBar_roundColor, Color.parseColor("#FFEEEEEE"));
        progressColor = typedArray.getColor(R.styleable.RoundProgressBar_progressColor, getResources().getColor(R.color.colorPrimary));
        roundWidth = typedArray.getDimension(R.styleable.RoundProgressBar_roundWidth, dp2px(context, 2));
        max = typedArray.getInteger(R.styleable.RoundProgressBar_max, 100);
        progress = typedArray.getFloat(R.styleable.RoundProgressBar_progress, 0);
        typedArray.recycle();
        init(context);
    }

    private void init(Context mContext) {
        this.mContext = mContext;
        mRoundPaint = new Paint();
        mRoundPaint.setAntiAlias(true);
        mRoundPaint.setStyle(Paint.Style.STROKE);
        mRoundPaint.setStrokeWidth(roundWidth);
        mRoundPaint.setColor(roundColor);

        mProgressPaint = new Paint();
        mProgressPaint.setAntiAlias(true);
        mProgressPaint.setStrokeWidth(roundWidth);
        mProgressPaint.setStyle(Paint.Style.STROKE);
        mProgressPaint.setColor(progressColor);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        int center = getWidth() / 2;
        int radius = (int) (center - roundWidth / 2);
        canvas.drawCircle(center, center, radius, mRoundPaint);

        RectF rectF = new RectF(center - radius, center - radius, center + radius, center + radius);
        canvas.drawArc(rectF, 0, 360 * progress / max, false, mProgressPaint);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(measureWidth(widthMeasureSpec), measureHeight(heightMeasureSpec));
    }

    private int measureWidth(int widthMeasureSpec) {
        int result = 0;
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        if (widthMode == MeasureSpec.EXACTLY) {
            result = widthSize;
        } else {
            result = dp2px(mContext, 24);
            if (widthMode == MeasureSpec.AT_MOST) {
                result = Math.min(result, widthSize);
            }
        }
        return result;
    }

    private int measureHeight(int heightMeasureSpec) {
        int result = 0;
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        if (heightMode == MeasureSpec.EXACTLY) {
            result = heightSize;
        } else {
            result = dp2px(mContext, 24);
            if (heightMode == MeasureSpec.AT_MOST) {
                result = Math.min(result, heightSize);
            }
        }
        return result;
    }

    public int getRoundColor() {
        return roundColor;
    }

    public void setRoundColor(int roundColor) {
        this.roundColor = roundColor;
        postInvalidate();
    }

    public int getProgressColor() {
        return progressColor;
    }

    public void setProgressColor(int progressColor) {
        this.progressColor = progressColor;
        postInvalidate();
    }

    public float getRoundWidth() {
        return roundWidth;
    }

    public void setRoundWidth(float roundWidth) {
        this.roundWidth = roundWidth;
        postInvalidate();
    }

    public synchronized int getMax() {
        return max;
    }

    public synchronized void setMax(int max) {
        this.max = max;
        postInvalidate();
    }

    public synchronized float getProgress() {
        return progress;
    }

    public synchronized void setProgress(float progress) {
        if (progress < 0) {
            throw new IllegalArgumentException("progress cannot less than 0");
        }
        if (progress > max) {
            progress = max;
        }
        if (progress < max) {
            this.progress = progress;
            postInvalidate();
        }
    }

    public int dp2px(Context context, float dpVal) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dpVal, context.getResources().getDisplayMetrics());
    }
}
