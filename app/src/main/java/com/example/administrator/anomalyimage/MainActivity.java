package com.example.administrator.anomalyimage;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.library.ShapeImageView;

public class MainActivity extends Activity {
    private HeaderViewHolder headerViewHolder;
    int topClassifyWidth;
    private String[] classifyNames;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        View headerView=findViewById(R.id.include_anomaly__image_header);
        headerViewHolder=new HeaderViewHolder(headerView);
        int screenWidth=getResources().getDisplayMetrics().widthPixels;
        topClassifyWidth=(screenWidth-getResources().getDimensionPixelSize(R.dimen.classify_top_cut_size))/5;
        int margin=getResources().getDimensionPixelSize(R.dimen.size_10_dip);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams
                (LinearLayout.LayoutParams.MATCH_PARENT, topClassifyWidth*2+margin/2);
        layoutParams.setMargins(margin,margin,margin,margin);
        headerView.setLayoutParams(layoutParams);
        classifyNames = getResources().getStringArray(R.array.tingwan_home_classify_name);
        initHeaderView();
    }

    /**
     * 设置大小、形状、点击事件
     */
    private void initHeaderView() {
        int margin=getResources().getDimensionPixelSize(R.dimen.size_5_dip);
        setTopClassifySize(headerViewHolder.sivFirstQuadrantRotate,topClassifyWidth);
        headerViewHolder.sivFirstQuadrantRotate.setShap(ShapeFactory.getShape(0,classifyNames[0],this.getApplicationContext()));
        setTopClassifySize(headerViewHolder.sivThirdQuadrant,topClassifyWidth*2-margin);
        headerViewHolder.sivThirdQuadrant.setShap(ShapeFactory.getShape(1,classifyNames[1],this.getApplicationContext()));
        setTopClassifySize(headerViewHolder.sivSquare,topClassifyWidth);
        headerViewHolder.sivSquare.setShap(ShapeFactory.getShape(2,classifyNames[2],this.getApplicationContext()));
        setTopClassifySize(headerViewHolder.sivParallelogram,topClassifyWidth*2+margin,topClassifyWidth-margin);
        headerViewHolder.sivParallelogram.setShap(ShapeFactory.getShape(3,classifyNames[3],this.getApplicationContext(),-(((float) topClassifyWidth-margin)/(topClassifyWidth*2+margin))));
        setTopClassifySize(headerViewHolder.sivSecondQuadrantRotate,topClassifyWidth);
        headerViewHolder.sivSecondQuadrantRotate.setShap(ShapeFactory.getShape(4,classifyNames[4],this.getApplicationContext()));
        setTopClassifySize(headerViewHolder.sivSecondThirdQuadrant,topClassifyWidth*2);
        headerViewHolder.sivSecondThirdQuadrant.setShap(ShapeFactory.getShape(5,classifyNames[5],this.getApplicationContext()));
        setTopClassifySize(headerViewHolder.sivSecondQuadrant,topClassifyWidth*2-margin);
        headerViewHolder.sivSecondQuadrant.setShap(ShapeFactory.getShape(6,classifyNames[6],this.getApplicationContext()));
        setTopClassifySize(headerViewHolder.sivFourthQuadrant,topClassifyWidth*2-margin);
        headerViewHolder.sivFourthQuadrant.setShap(ShapeFactory.getShape(7,classifyNames[7],this.getApplicationContext()));

        headerViewHolder.sivFirstQuadrantRotate.setOnShapeImageClickListener(onShapeImageClickListener);
        headerViewHolder.sivThirdQuadrant.setOnShapeImageClickListener(onShapeImageClickListener);
        headerViewHolder.sivSquare.setOnShapeImageClickListener(onShapeImageClickListener);
        headerViewHolder.sivParallelogram.setOnShapeImageClickListener(onShapeImageClickListener);
        headerViewHolder.sivSecondQuadrantRotate.setOnShapeImageClickListener(onShapeImageClickListener);
        headerViewHolder.sivSecondThirdQuadrant.setOnShapeImageClickListener(onShapeImageClickListener);
        headerViewHolder.sivSecondQuadrant.setOnShapeImageClickListener(onShapeImageClickListener);
        headerViewHolder.sivFourthQuadrant.setOnShapeImageClickListener(onShapeImageClickListener);
    }

    private void setTopClassifySize(ShapeImageView view,int size) {
        setTopClassifySize(view,size,size);
    }
    private void setTopClassifySize(ShapeImageView view,int width,int height) {
        RelativeLayout.LayoutParams lpFirstQuadrantRotate= (RelativeLayout.LayoutParams) view.getLayoutParams();
        lpFirstQuadrantRotate.width=width;
        lpFirstQuadrantRotate.height=height;
        view.setLayoutParams(lpFirstQuadrantRotate);
    }


    public ShapeImageView.OnShapeImageClickListener onShapeImageClickListener=new ShapeImageView.OnShapeImageClickListener() {
        @Override
        public void onClickListener(View view, String tag) {
            if(!TextUtils.isEmpty(tag)) {
                Toast.makeText(MainActivity.this,tag,Toast.LENGTH_SHORT).show();
            }
        }
    };

    public static class HeaderViewHolder {
        ShapeImageView sivFirstQuadrantRotate;
        ShapeImageView sivThirdQuadrant;
        ShapeImageView sivSquare;
        ShapeImageView sivParallelogram;
        ShapeImageView sivSecondQuadrantRotate;
        ShapeImageView sivSecondThirdQuadrant;
        ShapeImageView sivSecondQuadrant;
        ShapeImageView sivFourthQuadrant;

        public HeaderViewHolder(View view) {
            sivFirstQuadrantRotate=view.findViewById(R.id.siv_first_quadrant_rotate);
            sivThirdQuadrant=view.findViewById(R.id.siv_third_quadrant);
            sivSquare=view.findViewById(R.id.siv_square);
            sivParallelogram=view.findViewById(R.id.siv_parallelogram);
            sivSecondQuadrantRotate=view.findViewById(R.id.siv_second_quadrant_rotate);
            sivSecondThirdQuadrant=view.findViewById(R.id.siv_second_third_quadrant);
            sivSecondQuadrant=view.findViewById(R.id.siv_second_quadrant);
            sivFourthQuadrant=view.findViewById(R.id.siv_fourth_quadrant);
        }
    }
}
