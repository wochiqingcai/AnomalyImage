package com.example.library;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Shader.TileMode;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ShapeDrawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

import com.example.library.proxy.IShape;

public class ShapeImageView extends ImageView {
	public static final String TAG = "ShapeImageView";
	private ShapeDrawable mShapeDrawable;
	private IShape mShape;
	private boolean mIsShape;
	private boolean mRebuildShape;
	private OnShapeImageClickListener onShapeImageClickListener;

	public ShapeImageView(Context context) {
		this(context, null);
	}

	public ShapeImageView(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public ShapeImageView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	//设置shape
	public void setShap(IShape shape) {
		mShape = shape;
		mIsShape = true;
		mRebuildShape = true;
	}
	//设置文字给shape
	public void setShapeText(String text) {
		if(mShape!=null) {
			mShape.setText(text);
		}
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		if(mShape==null) {
			return super.onTouchEvent(event);
		}
		switch (event.getAction()) {
			case MotionEvent.ACTION_DOWN:
//				Toast.makeText(this.getContext(),"down",Toast.LENGTH_SHORT).show();
				if(mShape.chackIn(event.getX(),event.getY())) {
					if(mShape.checkMattePaint()) {
						mShape.setMatte(false);
						invalidate();
					}
					return true;
				}
				break;
			case MotionEvent.ACTION_UP:
				if(mShape.chackIn(event.getX(),event.getY())&&onShapeImageClickListener!=null) {
					onShapeImageClickListener.onClickListener(this,getText());
				}
				if(!mShape.getMatte()&&mShape.checkMattePaint()) {
					mShape.setMatte(true);
					invalidate();
				}
				return true;
			case MotionEvent.ACTION_CANCEL:
				if(!mShape.getMatte()&&mShape.checkMattePaint()) {
					mShape.setMatte(true);
					invalidate();
				}
				return true;

		}
		return super.onTouchEvent(event);
	}

	@SuppressLint("DrawAllocation")
	@Override
	protected void onDraw(Canvas canvas) {

		if (mIsShape) {
			//获取ImageView的drawble，当调用过setShape方法时，走下面的流程
			Drawable oldDrawable = getDrawable();
			if (oldDrawable == null || mShape == null) {
				return;
			}

			Rect bounds = oldDrawable.getBounds();

			if (bounds == null || bounds.width() == 0 || bounds.height() == 0) {
				return;
			}
			if (mShapeDrawable == null) {
				mShapeDrawable = new ShapeDrawable();
			}
			//设置shapedrawable的bounds
			mShapeDrawable.setBounds(bounds);

			if (mRebuildShape) {
				mRebuildShape = false;
				//获取bitmap
				Bitmap bm = drawableToBitmap(oldDrawable);
				if (bm == null) {
					return;
				}
				//创建一个bitmapshader，shapedrawable设置这个位图渲染
				BitmapShader bitmapShader = new BitmapShader(bm,
						TileMode.CLAMP, TileMode.CLAMP);
				mShapeDrawable.getPaint().setFlags(Paint.ANTI_ALIAS_FLAG);
				mShapeDrawable.getPaint().setStyle(Paint.Style.FILL);
				mShapeDrawable.getPaint().setShader(bitmapShader);
				mShapeDrawable.setShape(mShape.getShape());
			}
			mShape.setRect(bounds);
			int paddingTop = getPaddingTop();
			int paddingLeft = getPaddingLeft();

			//将mShapeDrawable画在canvas
			if (paddingTop == 0 && paddingLeft == 0) {
				mShapeDrawable.draw(canvas);
			} else {
				int saveCount = canvas.getSaveCount();
				canvas.save();

				canvas.translate(paddingLeft, paddingTop);
				mShapeDrawable.draw(canvas);
				canvas.restoreToCount(saveCount);
			}
		} else {
			super.onDraw(canvas);
		}

	}

	//此方法用于创建一个bitmap
	public static Bitmap drawableToBitmap(Drawable drawable) {
		if (drawable == null || drawable.getBounds() == null) {
			return null;
		}
		Bitmap bitmap;
		int width = drawable.getBounds().width();
		int height = drawable.getBounds().height();
		try {
			bitmap = Bitmap.createBitmap(width, height, Config.ARGB_8888);
			Canvas canvas = new Canvas(bitmap);
			drawable.draw(canvas);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
			bitmap = null;
		}
		return bitmap;
	}
	public void setOnShapeImageClickListener(OnShapeImageClickListener onShapeImageClickListener) {
		this.onShapeImageClickListener=onShapeImageClickListener;
	}

	public String getText() {
		return mShape==null?"":mShape.getText();
	}

	public interface OnShapeImageClickListener{
		void onClickListener(View view,String text);
	}
}
