package com.example.library.factory;

import android.content.Context;

import com.example.library.proxy.ShapeProxy;
import com.example.library.shape.QuadrangleShape;
import com.example.library.shape.TriangleShape;

/**
 * Created by lizw on 2018/9/21.
 */

public class ShapeFactory {
    public static ShapeProxy getShape(int index, String text, Context context) {
       return getShape(index,text,context,0f);
    }
    public static ShapeProxy getShape(int index, String text, Context context,float scale) {
        ShapeProxy shapeProxy = new ShapeProxy();
        switch (index) {
            case 0://右下第一个，第一象限，正45度文字
                shapeProxy.init(new TriangleShape(context));
                shapeProxy.setTriangleType(TriangleShape.TriangleType.TRIANGLE_TYPE_FIRST_QUADRANT);
                shapeProxy.setRotate(true,45);
                break;
            case 1://右上第一个，第三象限，
                shapeProxy.init(new TriangleShape(context));
                shapeProxy.setTriangleType(TriangleShape.TriangleType.TRIANGLE_TYPE_THIRD_QUADRANT);
                break;
            case 2://正方形
                shapeProxy.init(new QuadrangleShape(context));
                shapeProxy.setScale(0);
                break;
            case 3://平行四边形
                shapeProxy.init(new QuadrangleShape(context));
                shapeProxy.setScale(scale);
                break;
            case 4://第二象限，文字倾斜
                shapeProxy.init(new TriangleShape(context));
                shapeProxy.setTriangleType(TriangleShape.TriangleType.TRIANGLE_TYPE_SECOND_QUADRANT);
                shapeProxy.setRotate(true,-45);
                break;
            case 5://第二、三象限
                shapeProxy.init(new TriangleShape(context));
                shapeProxy.setTriangleType(TriangleShape.TriangleType.TRIANGLE_TYPE_SECOND_THIRD_QUADRANT);
                break;
            case 6://第二象限
                shapeProxy.init(new TriangleShape(context));
                shapeProxy.setTriangleType(TriangleShape.TriangleType.TRIANGLE_TYPE_SECOND_QUADRANT);
                break;
            case 7://第四象限
                shapeProxy.init(new TriangleShape(context));
                shapeProxy.setTriangleType(TriangleShape.TriangleType.TRIANGLE_TYPE_FOURTH_QUADRANT);
                break;
            default:
                break;
        }
        if(shapeProxy!=null)
        shapeProxy.setText(text);
        return shapeProxy;
    }
}
