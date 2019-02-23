package com.example.library.proxy;

import android.graphics.Rect;
import android.graphics.drawable.shapes.Shape;

import com.example.library.shape.BaseShape;
import com.example.library.shape.TriangleShape;


/**
 * Created by lizw on 2019/2/23.
 */
public class ShapeProxy implements IShape {
    private  IShape iShape;
    public void init(BaseShape iShape){
        this.iShape=iShape;
    }

    @Override
    public boolean chackIn(float x, float y) {
        return iShape.chackIn(x,y);
    }

    @Override
    public void setText(String text) {
        iShape.setText(text);
    }

    @Override
    public String getText() {
        return iShape.getText();
    }

    @Override
    public boolean checkMattePaint() {
        return iShape.checkMattePaint();
    }

    @Override
    public void setMatte(boolean isMatte) {
        iShape.setMatte(isMatte);
    }

    @Override
    public boolean getMatte() {
        return iShape.getMatte();
    }

    @Override
    public void setRect(Rect rect) {
        iShape.setRect(rect);
    }

    @Override
    public Shape getShape() {
        return (BaseShape)iShape;
    }

    @Override
    public void setTriangleType(TriangleShape.TriangleType triangleType) {
        iShape.setTriangleType(triangleType);
    }

    @Override
    public void setRotate(boolean rotate, int mDegrees) {
        iShape.setRotate(rotate,mDegrees);
    }

    @Override
    public void setScale(float scale) {
        iShape.setScale(scale);
    }
}
