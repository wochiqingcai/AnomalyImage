package com.example.library;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.text.TextUtils;

/**
 * Created by lizw on 2018/4/19.
 */


public class QuadrangleShape extends BaseShape {

    public QuadrangleShape(Context context) {
        initView(context);
    }

    @Override
    public void draw(Canvas canvas, Paint paint) {
        if (rect == null || rect.width() <= 0 || rect.height() <= 0) {
            return;
        }
        if (scale > 0.0f && scale < 1.0f) {
            offset = (int) (scale * rect.width());
            path.reset();
            path.moveTo(offset, rect.left);
            path.lineTo(rect.left, rect.bottom);
            path.lineTo(rect.right - offset, rect.bottom);
            path.lineTo(rect.right, 0);
        } else if (scale > -1.0f && scale < 0.0f) {
            offset = (int) (-scale * rect.width());
            path.reset();
            path.moveTo(0, 0);
            path.lineTo(offset, rect.bottom);
            path.lineTo(rect.right, rect.bottom);
            path.lineTo(rect.right - offset, 0);
        } else {
            path.reset();
            path.moveTo(offset, rect.left);
            path.lineTo(rect.left, rect.bottom);
            path.lineTo(rect.right - offset, rect.bottom);
            path.lineTo(rect.right, 0);
        }
        canvas.drawPath(path, paint);
        //蒙层
        if (needMatte&&mattePaint!=null)
            canvas.drawPath(path, mattePaint);

        //文字
        if (!TextUtils.isEmpty(this.text)) {
            int x = (rect.width() / 2) - textRect.centerX();
            int y = (rect.height() / 2) - textRect.centerY();
            canvas.drawText(this.text, x, y, this.textPaint);
        }
    }

    public boolean chackIn(float x, float y) {
        if (scale > 0.0f && scale < 1.0f) {
            //TODO 正平行四边形
            return true;
        } else if (scale > -1.0f && scale < 0.0f) {
            float offset = (-scale * rect.width());
            if (x >= 0 && x < offset && (x / y) >= (offset / (float) rect.height())) {
                if (y >= rect.top && y <= rect.bottom)
                    return true;
            } else if (x >= offset && x <= (rect.right - offset)) {
                if (y >= rect.top && y <= rect.bottom)
                    return true;
            } else if (x > rect.right - offset && x <= rect.right && ((x - (rect.right - offset)) / y) <= (offset / (float) rect.height())) {
                if (y >= rect.top && y <= rect.bottom)
                    return true;
            }
        } else if (scale == 0) {
            if (x <= rect.right && x >= rect.left && y <= rect.bottom && y >= rect.top)
                return true;
        }
        return false;
    }
}
