package com.example.library;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.text.TextUtils;

/**
 * Created by lizw on 2018/4/10.
 */

public class TriangleShape extends BaseShape {

    public enum TriangleType {
        TRIANGLE_TYPE_FIRST_QUADRANT, TRIANGLE_TYPE_SECOND_QUADRANT, TRIANGLE_TYPE_THIRD_QUADRANT, TRIANGLE_TYPE_SECOND_THIRD_QUADRANT, TRIANGLE_TYPE_FOURTH_QUADRANT
    }

    public TriangleShape(Context context) {
        initView(context);
    }

    @Override
    public void draw(Canvas canvas, Paint paint) {
        if (rect == null || rect.width() <= 0 || rect.height() <= 0) {
            return;
        }
        int x = 0, y = 0;
        path.reset();
        switch (triangleType) {
            case TRIANGLE_TYPE_FIRST_QUADRANT:
                path.moveTo(rect.left, rect.top);
                path.lineTo(rect.left, rect.bottom);
                path.lineTo(rect.right, rect.bottom);
                if (rotate) {
                    x = (rect.width() * 1 / 2 - textRect.height() * 2 / 3) - textRect.centerX();
                    y = (rect.height() * 1 / 2 + textRect.height() * 2 / 3) - textRect.centerY();
                } else {
                    x = (rect.width() * 2 / 3) - textRect.centerX();
                    y = (rect.height() * 2 / 3) - textRect.centerY();
                }
                break;
            case TRIANGLE_TYPE_SECOND_QUADRANT:
                path.moveTo(rect.left, rect.bottom);
                path.lineTo(rect.right, rect.bottom);
                path.lineTo(rect.right, rect.top);
                if (rotate) {
                    x = (rect.width() * 1 / 2 + textRect.height() * 2 / 3) - textRect.centerX();
                    y = (rect.height() * 1 / 2 + textRect.height() * 2 / 3) - textRect.centerY();
                } else {
                    x = (rect.width() * 3 / 4) - textRect.centerX();
                    y = (rect.height() * 3 / 4) - textRect.centerY();
                }
                break;
            case TRIANGLE_TYPE_THIRD_QUADRANT:
                path.moveTo(rect.left, rect.top);
                path.lineTo(rect.right, rect.top);
                path.lineTo(rect.right, rect.bottom);
                x = (rect.width() * 3 / 4) - textRect.centerX();
                y = (rect.height() / 3) - textRect.centerY();
                break;
            case TRIANGLE_TYPE_FOURTH_QUADRANT:
                path.moveTo(rect.left, rect.top);
                path.lineTo(rect.left, rect.bottom);
                path.lineTo(rect.right, rect.top);
                x = (rect.width() / 4) - textRect.centerX();
                y = (rect.height() / 4) - textRect.centerY();
                break;
            case TRIANGLE_TYPE_SECOND_THIRD_QUADRANT:
                path.moveTo(rect.left, rect.top);
                path.lineTo(rect.left, rect.bottom);
                path.lineTo(rect.right / 2, rect.bottom / 2);
                x = (rect.width() / 5) - textRect.centerX();
                y = (rect.height() / 2) - textRect.centerY();
                break;
        }

        drawPath(canvas, paint);

        //文字
        if (!TextUtils.isEmpty(this.text)) {
            if (rotate) {
                switch (triangleType) {
                    case TRIANGLE_TYPE_FIRST_QUADRANT:
                        canvas.rotate(mDegrees, rect.width() / 2 - textRect.height() * 2 / 3, rect.height() / 2 + textRect.height() * 2 / 3);
                        break;
                    case TRIANGLE_TYPE_SECOND_QUADRANT:
                        canvas.rotate(mDegrees, rect.width() / 2 + textRect.height() * 2 / 3, rect.height() / 2 + textRect.height() * 2 / 3);
                        break;
                    default:
                        canvas.rotate(mDegrees, rect.width() / 2 - textRect.height() * 2 / 3, rect.height() / 2 + textRect.height() * 2 / 3);
                        break;
                }

            }
            canvas.drawText(this.text, x, y, this.textPaint);
        }
    }

    private void drawPath(Canvas canvas, Paint paint) {
        canvas.drawPath(path, paint);
        //蒙层
        if (needMatte&&mattePaint!=null)
            canvas.drawPath(path, mattePaint);
    }

    @Override
    public boolean chackIn(float x, float y) {
        switch (triangleType) {
            case TRIANGLE_TYPE_FOURTH_QUADRANT:
                if (x >= rect.left && x <= rect.right && y >= rect.top && y <= rect.bottom && (y / ((float) rect.width() - x)) <= ((float) rect.height() / (float) rect.width())) {
                    return true;
                }
                break;
            case TRIANGLE_TYPE_SECOND_QUADRANT:
                if (x >= rect.left && x <= rect.right && y >= rect.top && y <= rect.bottom && (y / ((float) rect.width() - x)) >= ((float) rect.height() / (float) rect.width())) {
                    return true;
                }
                break;
            case TRIANGLE_TYPE_THIRD_QUADRANT:
                if (x >= rect.left && x <= rect.right && y >= rect.top && y <= rect.bottom && (x / y) >= ((float) rect.width() / (float) rect.height())) {
                    return true;
                }
                break;
            case TRIANGLE_TYPE_FIRST_QUADRANT:
                if (x >= rect.left && x <= rect.right && y >= rect.top && y <= rect.bottom && (x / y) <= ((float) rect.width() / (float) rect.height())) {
                    return true;
                }
                break;
            case TRIANGLE_TYPE_SECOND_THIRD_QUADRANT:
                if (x >= rect.left && x <= rect.right / 2 && y >= rect.top && y <= rect.bottom) {
                    if (y <= rect.right / 2 && (x / y) <= ((float) rect.width() / (float) rect.height())) {
                        return true;
                    } else if (y > rect.right / 2 && (y / ((float) rect.width() - x)) <= ((float) rect.height() / (float) rect.width())) {
                        return true;
                    }
                }
                break;
        }

        return false;
    }
}
