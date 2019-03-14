package com.example.library.factory;

import android.content.Context;

import com.example.library.proxy.IShape;
import com.example.library.proxy.ShapeInvocationHandler;
import com.example.library.shape.QuadrangleShape;
import com.example.library.shape.TriangleShape;


/**
 * Created by lizw on 2019/3/14.
 */
public class DynamicProxyShapeFactory {
    public static IShape getShape(int index, String text, Context context) {
        return getShape(index,text,context,0f);
    }
    public static IShape getShape(int index, String text, Context context,float scale) {
        IShape iShape = null;
        switch (index) {
            case 0://右下第一个，第一象限，正45度文字
                iShape= new ShapeInvocationHandler(new TriangleShape(context)).getIshapeProxy();
                iShape.setTriangleType(TriangleShape.TriangleType.TRIANGLE_TYPE_FIRST_QUADRANT);
                iShape.setRotate(true,45);
                break;
            case 1://右上第一个，第三象限，
                iShape= new ShapeInvocationHandler(new TriangleShape(context)).getIshapeProxy();
                iShape.setTriangleType(TriangleShape.TriangleType.TRIANGLE_TYPE_THIRD_QUADRANT);
                break;
            case 2://正方形
                iShape= new ShapeInvocationHandler(new QuadrangleShape(context)).getIshapeProxy();
                iShape.setScale(0);
                break;
            case 3://平行四边形
                iShape= new ShapeInvocationHandler(new QuadrangleShape(context)).getIshapeProxy();
                iShape.setScale(scale);
                break;
            case 4://第二象限，文字倾斜
                iShape= new ShapeInvocationHandler(new TriangleShape(context)).getIshapeProxy();
                iShape.setTriangleType(TriangleShape.TriangleType.TRIANGLE_TYPE_SECOND_QUADRANT);
                iShape.setRotate(true,-45);
                break;
            case 5://第二、三象限
                iShape= new ShapeInvocationHandler(new TriangleShape(context)).getIshapeProxy();
                iShape.setTriangleType(TriangleShape.TriangleType.TRIANGLE_TYPE_SECOND_THIRD_QUADRANT);
                break;
            case 6://第二象限
                iShape= new ShapeInvocationHandler(new TriangleShape(context)).getIshapeProxy();
                iShape.setTriangleType(TriangleShape.TriangleType.TRIANGLE_TYPE_SECOND_QUADRANT);
                break;
            case 7://第四象限
                iShape= new ShapeInvocationHandler(new TriangleShape(context)).getIshapeProxy();
                iShape.setTriangleType(TriangleShape.TriangleType.TRIANGLE_TYPE_FOURTH_QUADRANT);
                break;
            default:
                break;
        }
        if(iShape!=null)
            iShape.setText(text);
        return iShape;
    }
}
