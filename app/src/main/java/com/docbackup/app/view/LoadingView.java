package com.docbackup.app.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.SweepGradient;
import android.os.Build;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.View;

public class LoadingView extends View {

    public static final int DELAY_DEFAULT = -1;
    public static final int DELAY_NO_DELAY = 0;

    private static final int DEFAULT_DELAY_TIME = 400; // msec

    private static final float FRAMES_PER_SECOND = 32;
    private static final float DEGREES_PER_FRAME = 10F;

    private static final float DEFAULT_SIZE = 6F;
    private static final float DEFAULT_RADIUS = 20F;

    private int startDelay = DELAY_DEFAULT;

    private long startTime = 0;
    private long lastFrameAt = 0;

    private int centerX;
    private int centerY;

    private float currentAngle = 0;

    private Paint paintCircle;
    private Paint paintHead;

    private int color = 0xffff0000;
    private float radius = -1;
    private float size = -1;
    private float scaleFactor = 1F;

    public LoadingView(Context context) {
        super(context);
        init(null);
    }

    public LoadingView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public LoadingView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public LoadingView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(attrs);
    }

    private void init(@Nullable AttributeSet attrs) {
        float density = getContext().getResources().getDisplayMetrics().density;

        switch (startDelay) {
            case DELAY_DEFAULT:
                startDelay = DEFAULT_DELAY_TIME;
                break;
            case DELAY_NO_DELAY:
                startDelay = 0;
                break;
        }

        if (size == -1) {
            size = DEFAULT_SIZE * density;
        }

        if (radius == -1) {
            radius = DEFAULT_RADIUS * density;
        }

        paintCircle = new Paint(Paint.ANTI_ALIAS_FLAG);
        paintCircle.setStyle(Paint.Style.STROKE);
        paintHead = new Paint(Paint.ANTI_ALIAS_FLAG);
        paintHead.setStyle(Paint.Style.FILL);

        updatePaint();
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);

        int cachedWidth = getWidth() - getPaddingLeft() - getPaddingRight();
        int cachedHeight = getHeight() - getPaddingTop() - getPaddingBottom();

        centerX = cachedWidth/2+getPaddingLeft();
        centerY = cachedHeight/2+getPaddingTop();
    }

    @Override
    public void draw(@NonNull Canvas canvas) {
        if (startDelay > 0) {
            long uptime = SystemClock.elapsedRealtime();

            if (uptime < startTime) {
                postInvalidateDelayed(startTime - uptime);
                return;
            }
        }

        super.draw(canvas);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.save();
        canvas.translate(centerX, centerY);
        if (scaleFactor != 1) {
            canvas.scale(scaleFactor, scaleFactor);
        }

        long time = SystemClock.elapsedRealtime();

        if (lastFrameAt == 0) {
            lastFrameAt = time;
        }

        float ofFrame = FRAMES_PER_SECOND * (time-lastFrameAt)/1000F;
        float degrees = ofFrame * DEGREES_PER_FRAME;

        currentAngle = (currentAngle + degrees) % 360F;

        canvas.rotate(120 - currentAngle);

        // draw circle
        canvas.drawCircle(0, 0, radius, paintCircle);

        // draw head
        canvas.drawCircle(radius, 0, size/2, paintHead);

        canvas.restore();

        ViewCompat.postInvalidateOnAnimation(this);

        lastFrameAt = time;
    }

    @Override
    public void setVisibility(int visibility) {
        if (startDelay > 0) {
            startTime = SystemClock.elapsedRealtime() + startDelay;
        }
        super.setVisibility(visibility);
    }

    /**
     * Stroke width
     * @param size    in pixels
     */
    public void setSize(float size) {
        this.size = size;
        paintCircle.setStrokeWidth(size);
        postInvalidate();
    }

    public float getSize() {
        return size;
    }

    /**
     * Circle radius
     * @param radius    in pixels
     */
    public void setRadius(float radius) {
        this.radius = radius;
        postInvalidate();
    }

    public float getRadius() {
        return radius;
    }

    /**
     * Scale factor
     * @param scaleFactor    1..0
     */
    public void setScaleFactor(float scaleFactor) {
        this.scaleFactor = scaleFactor;
        postInvalidate();
    }

    public float getScaleFactor() {
        return scaleFactor;
    }

    /**
     * Start animation after visibility change with delay
     * @param startDelay    in milliseconds
     */
    public void setStartDelay(int startDelay) {
        this.startDelay = startDelay;
    }

    public int getStartDelay() {
        return startDelay;
    }

    public void setColor(int color) {
        this.color = color;
        updatePaint();
        postInvalidate();
    }

    public int getColor() {
        return color;
    }

    private void updatePaint() {
        int colorRgb = color & 0x00ffffff;

        SweepGradient circle = new SweepGradient(
                .5F, .5F,
                new int[]{ 0xff000000 | colorRgb, 0x0f000000 | colorRgb, colorRgb, colorRgb },
                new float[]{ 0F, .6F, 0.75F, 1F }
        );

        paintCircle.setShader(circle);
        paintCircle.setStrokeWidth(size);

        paintHead.setColor(0xff000000 | colorRgb);
    }
}
