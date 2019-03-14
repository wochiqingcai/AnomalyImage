package com.example.library.shape;


import android.content.Context;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.drawable.shapes.Shape;
import android.text.TextUtils;

import com.example.library.R;
import com.example.library.proxy.IShape;

public abstract class BaseShape extends Shape implements IShape {
    protected Path path=new Path();
    protected Paint textPaint=new Paint();
    protected Paint mattePaint;
    protected String text;
    protected Rect textRect=new Rect();
    protected Rect rect=new Rect();
    protected int offset;
    protected float scale = -1f;
    protected Context context;
    protected boolean needMatte = true;//点击隐藏显示蒙层开关
    protected boolean rotate = false;//文字是否倾斜
    protected int mDegrees;//文字倾斜角度
    public BaseShape(Context context){
        initView(context);
    }
    public void initView(Context context) {
        this.context = context;
        textPaint.setColor(context.getResources().getColor(R.color.paint_text_color));
        textPaint.setAntiAlias(true);
        textPaint.setTextSize(context.getResources().getDimensionPixelSize(R.dimen.shape_text_size));
        textPaint.setFakeBoldText(true);
        textRect.set(textRect.centerX(), textRect.centerY() - 30, textRect.centerX() + textRect.width(), textRect.centerY() + textRect.height());
        mattePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mattePaint.setColor(context.getResources().getColor(R.color.paint_matte_color));
        mattePaint.setStyle(Paint.Style.FILL);
   }

    public void setMatte(boolean needMatte) {
        this.needMatte = needMatte;
    }

    public boolean getMatte() {
        return this.needMatte;
    }

    public String getText() {
        return text;
    }
    //关闭蒙层
    public void closeMette() {
        mattePaint=null;
    }
    public void setText(String text) {
        this.text = text;
        if (!TextUtils.isEmpty(text))
            textPaint.getTextBounds(this.text, 0, this.text.length(), textRect);
    }

    @Override
    public boolean checkMattePaint() {
        return mattePaint!=null;
    }
    public void setRect(Rect rect) {
        this.rect = rect;
    }

    public boolean isRotate() {
        return rotate;
    }

    public void setRotate(boolean rotate, int mDegrees) {
        this.rotate = rotate;
        this.mDegrees = mDegrees;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public void setScale(float scale) {
        this.scale = scale;
    }

    @Override
    public Shape getShape() {
        return this;
    }
}
