package com.example.library.proxy;

import android.graphics.Rect;
import android.graphics.drawable.shapes.Shape;

import com.example.library.shape.TriangleShape;

/**
 * Created by lizw on 2018/9/20.
 */

public interface IShape {
    boolean chackIn(float x, float y);
    void setText(String text);
    String getText();
    boolean checkMattePaint();
    void setMatte(boolean isMatte);
    boolean getMatte();
    void setRect(Rect rect);
    Shape getShape();
    void setTriangleType(TriangleShape.TriangleType triangleType);
    void setRotate(boolean rotate, int mDegrees);
    void setScale(float scale);
}
