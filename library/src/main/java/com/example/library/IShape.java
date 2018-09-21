package com.example.library;

import android.content.Context;

/**
 * Created by lizw on 2018/9/20.
 */

public interface IShape {
    void initView(Context context);
    boolean chackIn(float x, float y);
    void setRotate(boolean rotate, int mDegrees);
    void setTriangleType(TriangleShape.TriangleType triangleType);
}
