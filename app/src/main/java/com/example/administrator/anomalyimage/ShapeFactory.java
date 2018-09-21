package com.example.administrator.anomalyimage;

import android.content.Context;

import com.example.library.BaseShape;
import com.example.library.QuadrangleShape;
import com.example.library.TriangleShape;

/**
 * Created by lizw on 2018/9/21.
 */

public class ShapeFactory {
    public static BaseShape getShape(int index, String text, Context context) {
       return getShape(index,text,context,0f);
    }
    public static BaseShape getShape(int index, String text, Context context,float scale) {
        BaseShape baseShape = null;
        switch (index) {
            case 0://右下第一个，第一象限，正45度文字
                baseShape = new TriangleShape(context);
                baseShape.setTriangleType(TriangleShape.TriangleType.TRIANGLE_TYPE_FIRST_QUADRANT);
                baseShape.setRotate(true,45);
                break;
            case 1://右上第一个，第三象限，
                baseShape = new TriangleShape(context);
                baseShape.setTriangleType(TriangleShape.TriangleType.TRIANGLE_TYPE_THIRD_QUADRANT);
                break;
            case 2://正方形
                baseShape = new QuadrangleShape(context);
                baseShape.setScale(0);
                break;
            case 3://平行四边形
                baseShape = new QuadrangleShape(context);
                baseShape.setScale(scale);
                break;
            case 4://第二象限，文字倾斜
                baseShape = new TriangleShape(context);
                baseShape.setTriangleType(TriangleShape.TriangleType.TRIANGLE_TYPE_SECOND_QUADRANT);
                baseShape.setRotate(true,-45);
                break;
            case 5://第二、三象限
                baseShape = new TriangleShape(context);
                baseShape.setTriangleType(TriangleShape.TriangleType.TRIANGLE_TYPE_SECOND_THIRD_QUADRANT);
                break;
            case 6://第二象限
                baseShape = new TriangleShape(context);
                baseShape.setTriangleType(TriangleShape.TriangleType.TRIANGLE_TYPE_SECOND_QUADRANT);
                break;
            case 7://第四象限
                baseShape = new TriangleShape(context);
                baseShape.setTriangleType(TriangleShape.TriangleType.TRIANGLE_TYPE_FOURTH_QUADRANT);
                break;
            default:
                break;
        }
        if(baseShape!=null)
        baseShape.setText(text);
        return baseShape;
    }
}
