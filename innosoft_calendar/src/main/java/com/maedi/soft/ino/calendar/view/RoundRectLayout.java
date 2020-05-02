package com.maedi.soft.ino.calendar.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import com.maedi.soft.ino.calendar.R;

public class RoundRectLayout extends LinearLayout {

    private Paint paint, paintBorder;
    private Context ctx;
    private int color, borderColor, roundRadius;

    public RoundRectLayout(Context context) {
        super(context);
        ctx = context;
        initialize();
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public RoundRectLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        ctx = context;

        TypedArray a = context.getTheme().obtainStyledAttributes(
                attrs,
                R.styleable.RoundRectLayout,
                0, 0
        );
        try {
            if(null == a)
            {
                color = ctx.getColor(R.color.white);
                borderColor = ctx.getColor(R.color.Dashboard_Card_Dot_Border);
                roundRadius = 0;
            }
            else
            {
                color = a.getColor(R.styleable.RoundRectLayout_roundRectColor, ctx.getColor(R.color.white));
                borderColor = a.getColor(R.styleable.RoundRectLayout_roundRectBorderColor, ctx.getColor(R.color.Dashboard_Card_Dot_Border));
                roundRadius = a.getInt(R.styleable.RoundRectLayout_roundRectRadius, 0);
            }
        } finally {
            // release the TypedArray so that it can be reused.
            a.recycle();
        }

        initialize();
    }

    public RoundRectLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        ctx = context;
        initialize();
    }

    private void initialize() {
        this.setWillNotDraw(false);
        paint = new Paint();
        paint.setAntiAlias(true);
        paintBorder = new Paint();
        paintBorder.setAntiAlias(true);
    }

    @Override
    protected void onDraw(Canvas canvas){
        super.onDraw(canvas);

        int w = getWidth();
        int h = getHeight();
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(color);//(ctx.getResources().getColor(R.color.red));
        paintBorder.setColor(borderColor);
        RectF rect = new RectF(0,0, w, h);
        canvas.drawRoundRect(rect , roundRadius, roundRadius, paint);
        roundRadius = roundRadius+2;
        canvas.drawRoundRect(rect , roundRadius, roundRadius, paintBorder);
    }

    public void setBackgroundColor(int newColor){
        color = newColor;
    }

    public int getBackgroundColor(){
        return color;
    }

    public void setBorderColor(int newColor){
        borderColor = newColor;
    }

    public int getBorderColor(){
        return borderColor;
    }

    public void setRadius(int newRadius){
        roundRadius = newRadius;
    }

    public int getRadius(){
        return roundRadius;
    }
}