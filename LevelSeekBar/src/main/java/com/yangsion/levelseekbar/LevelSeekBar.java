package com.yangsion.levelseekbar;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class LevelSeekBar extends View {

    private int view_width = 0;//LevelSeekBar的宽
    private int view_height = 0;//LevelSeekBar的高

    //进度条的背景图片
    private Drawable level_progressBackgroundDrawable;
    //进度条图片
    private Drawable level_progressDrawable;
    //气泡默认icon图片List，数量同 level_number相同
    private List<Drawable> bubbleDefaultIconList = new ArrayList<>();
    //气泡当前icon图片List ，...
    private List<Drawable> bubbleIconList = new ArrayList<>();
    //阶段默认icon图片List ，...
    private List<Drawable> levelDefaultIconList = new ArrayList<>();
    //阶段当前icon图片List ，...
    private List<Drawable> levelIconList = new ArrayList<>();

    private List<String> bubbleTextList = new ArrayList<>();
    private List<String> levelTextList = new ArrayList<>();

    private List<Rect> bubbleTextBoundsList = new ArrayList<>();
    private List<Rect> levelTextBoundsList = new ArrayList<>();

    private boolean show_bubble_default_icon = false;//是否显示默认气泡图标
    private int level_number = 5;//level数量
    private int level_progress_max = 100;//进度条最大长度
    private int level_progress = 10;//当前进度条长度占比
    private int level_progress_height = 30;//进度条的高
    private int level_progressBackground_height = 30;//进度条背景的高
    private int bubble_default_icon_width = 120;//气泡默认icon的宽
    private int bubble_default_icon_height = 120;//气泡默认icon的高
    private int bubble_icon_width = 120;//气泡当前icon的宽
    private int bubble_icon_height = 120;//气泡当前icon的高
    private int level_default_icon_width = 120;//阶段默认icon的宽
    private int level_default_icon_height = 120;//阶段默认icon的高
    private int level_icon_width = 120;//阶段当前icon的宽
    private int level_icon_height = 120;//阶段当前icon的高

    private int bubble_textColor;//气泡文字的颜色
    private int level_textColor;//阶段文字的颜色

    private Paint bubbleTextPaint = new Paint();
    private Paint levelTextPaint = new Paint();
    private Rect bubbleTextBound = new Rect();
    private Rect levelTextBound = new Rect();

    private int progress_width = 0;//当前进度条长度


    public LevelSeekBar(Context context) {
        this(context, null);
    }

    public LevelSeekBar(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LevelSeekBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, 0);
    }

    public LevelSeekBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.LevelSeekBar, 0, 0);
        try {
            show_bubble_default_icon = a.getBoolean(R.styleable.LevelSeekBar_show_bubble_default_icon, false);

            level_progressBackgroundDrawable = a.getDrawable(R.styleable.LevelSeekBar_level_progressBackground);
            level_progressDrawable = a.getDrawable(R.styleable.LevelSeekBar_level_progressDrawable);

            level_number = a.getInteger(R.styleable.LevelSeekBar_level_number, 5);
            for (int i = 0; i < level_number; i++) {
                bubbleDefaultIconList.add(a.getDrawable(R.styleable.LevelSeekBar_bubble_default_icon));
                bubbleIconList.add(a.getDrawable(R.styleable.LevelSeekBar_bubble_icon));
                levelDefaultIconList.add(a.getDrawable(R.styleable.LevelSeekBar_level_default_icon));
                levelIconList.add(a.getDrawable(R.styleable.LevelSeekBar_level_icon));
                bubbleTextList.add(String.valueOf(i + 1));
                levelTextList.add(String.valueOf(i + 1));
            }

            level_progress_max = a.getInteger(R.styleable.LevelSeekBar_level_progress_max, 100);
            level_progress = a.getInteger(R.styleable.LevelSeekBar_level_progress, 10);
            level_progress_height = a.getDimensionPixelSize(R.styleable.LevelSeekBar_level_progress_height, 30);
            level_progressBackground_height = a.getDimensionPixelSize(R.styleable.LevelSeekBar_level_progressBackground_height, 30);
            bubble_default_icon_width = a.getDimensionPixelSize(R.styleable.LevelSeekBar_bubble_default_icon_width, 120);
            bubble_default_icon_height = a.getDimensionPixelSize(R.styleable.LevelSeekBar_bubble_default_icon_height, 60);
            bubble_icon_width = a.getDimensionPixelSize(R.styleable.LevelSeekBar_bubble_icon_width, 120);
            bubble_icon_height = a.getDimensionPixelSize(R.styleable.LevelSeekBar_bubble_icon_height, 60);
            level_default_icon_width = a.getDimensionPixelSize(R.styleable.LevelSeekBar_level_default_icon_width, 120);
            level_default_icon_height = a.getDimensionPixelSize(R.styleable.LevelSeekBar_level_default_icon_height, 120);
            level_icon_width = a.getDimensionPixelSize(R.styleable.LevelSeekBar_level_icon_width, 120);
            level_icon_height = a.getDimensionPixelSize(R.styleable.LevelSeekBar_level_icon_height, 120);

            bubble_textColor = a.getColor(R.styleable.LevelSeekBar_bubble_textColor, Color.BLACK);
            level_textColor = a.getColor(R.styleable.LevelSeekBar_level_textColor, Color.BLACK);
        } finally {
            a.recycle();
        }
        init(context);
    }

    private void init(Context context) {
        bubbleTextPaint.setAntiAlias(true);//抗锯齿
        bubbleTextPaint.setDither(true);//防抖动
        bubbleTextPaint.setFakeBoldText(true);//仿粗体
//        bubbleTextPaint.setColor(bubble_textColor);
        bubbleTextPaint.setTextSize(30f);
        levelTextPaint.setAntiAlias(true);
        levelTextPaint.setDither(true);
        levelTextPaint.setFakeBoldText(true);
        levelTextPaint.setColor(level_textColor);
        levelTextPaint.setTextSize(30f);


    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
//        view_width = w;
//        view_height = h;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width_size = MeasureSpec.getSize(widthMeasureSpec);
        int width_mode = MeasureSpec.getMode(widthMeasureSpec);
        int height_size = MeasureSpec.getSize(heightMeasureSpec);
        int height_mode = MeasureSpec.getMode(heightMeasureSpec);
        if (width_mode == MeasureSpec.EXACTLY) {
            view_width = width_size;
        } else if (width_mode == MeasureSpec.AT_MOST) {
            view_width = getPaddingStart() + level_progressBackgroundDrawable.getBounds().width() + getPaddingEnd();
        }
        if (height_mode == MeasureSpec.EXACTLY) {
            view_height = height_size;
        } else if (height_mode == MeasureSpec.AT_MOST) {
            view_height = getPaddingTop() + levelDefaultIconList.get(0).getBounds().height() + getPaddingBottom();
        }
        Log.d("ls", "view_width:" + view_width);
        Log.d("ls", "view_height:" + view_height);

        int lpb_top = view_height / 2 - level_progressBackground_height / 2;
        level_progressBackgroundDrawable.setBounds(getPaddingStart(), lpb_top, view_width - getPaddingEnd() - level_progressBackground_height / 2, lpb_top + level_progressBackground_height);

        int lpbd_width = level_progressBackgroundDrawable.getBounds().width();
        Log.d("ls", "lpbd_width:" + lpbd_width);
        int lp_top = view_height / 2 - level_progress_height / 2;
        float percent = (float) level_progress / (float) level_progress_max;
        progress_width = (int) (lpbd_width * percent);
        Log.d("ls", "progress_width:" + progress_width);
        Rect lpd_bounds = level_progressBackgroundDrawable.getBounds();
        level_progressDrawable.setBounds(lpd_bounds.left, lp_top, progress_width + getPaddingEnd(), lp_top + level_progress_height);

        int ldi_width = (int) (lpbd_width / level_number);
        Log.d("ls", "ldi_width:" + ldi_width);
        int levelDefaultIcon_top = view_height / 2 - level_default_icon_height / 2;
        int bubbleDefaultIcon_top = view_height / 2 - bubble_default_icon_height / 2;
        Log.d("ls", "ldi_top:" + levelDefaultIcon_top);
        for (int ldi = 0; ldi < level_number; ldi++) {
            int num = ldi + 1;

            int levelDefaultIcon_left = getPaddingStart() + (ldi_width * num - level_default_icon_width / 2);
            int levelDefaultIcon_right = (ldi_width * num - level_default_icon_width / 2) + level_default_icon_width + levelDefaultIcon_top;
            int levelDefaultIcon_bottom = levelDefaultIcon_top + level_default_icon_height;
            levelDefaultIconList.get(ldi).setBounds(levelDefaultIcon_left, levelDefaultIcon_top, levelDefaultIcon_right, levelDefaultIcon_bottom);
            levelIconList.get(ldi).setBounds(levelDefaultIcon_left, levelDefaultIcon_top, levelDefaultIcon_right, levelDefaultIcon_bottom);

            int bubbleDefaultIcon_left = getPaddingStart() + (ldi_width * num - bubble_default_icon_width / 2);
            int bubbleDefaultIcon_right = (ldi_width * num - bubble_default_icon_width / 2) + bubble_default_icon_width + bubbleDefaultIcon_top;
            int bubbleDefaultIcon_bottom = bubbleDefaultIcon_top + bubble_default_icon_height;
            bubbleDefaultIconList.get(ldi).setBounds(bubbleDefaultIcon_left, bubbleDefaultIcon_top / 5, bubbleDefaultIcon_right, bubbleDefaultIcon_bottom / 3 + 10);
            bubbleIconList.get(ldi).setBounds(bubbleDefaultIcon_left, bubbleDefaultIcon_top / 5, bubbleDefaultIcon_right, bubbleDefaultIcon_bottom / 3 + 10);

            Rect ldil_bounds = levelDefaultIconList.get(ldi).getBounds();
            levelTextBoundsList.add(ldil_bounds);
            Rect bdil_bounds = bubbleDefaultIconList.get(ldi).getBounds();
            bubbleTextBoundsList.add(bdil_bounds);

        }

        setMeasuredDimension(view_width, view_height);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        level_progressBackgroundDrawable.draw(canvas);
        level_progressDrawable.draw(canvas);
        for (int ldi = 0; ldi < level_number; ldi++) {
            levelDefaultIconList.get(ldi).draw(canvas);
            bubbleDefaultIconList.get(ldi).draw(canvas);
            bubbleTextPaint.setColor(bubble_textColor);
            if (progress_width >= levelTextBoundsList.get(ldi).centerX() - getPaddingEnd()) {
                bubbleTextPaint.setColor(Color.WHITE);
                levelIconList.get(ldi).draw(canvas);
                bubbleIconList.get(ldi).draw(canvas);
            }

            bubbleTextPaint.getTextBounds(bubbleTextList.get(ldi), 0, bubbleTextList.get(ldi).length(), bubbleTextBound);
            levelTextPaint.getTextBounds(levelTextList.get(ldi), 0, levelTextList.get(ldi).length(), levelTextBound);
            canvas.drawText(bubbleTextList.get(ldi), bubbleTextBoundsList.get(ldi).centerX() - bubbleTextBound.width() / 2, bubbleTextBoundsList.get(ldi).centerY() + bubbleTextBound.height() / 2 - 5, bubbleTextPaint);
            canvas.drawText(levelTextList.get(ldi), levelTextBoundsList.get(ldi).centerX() - levelTextBound.width() / 2, levelTextBoundsList.get(ldi).bottom + level_default_icon_height / 4, levelTextPaint);
        }
        //invalidate();
        /**
         * 1. 动画效果未加上
         * 2. 进度条可显示为斑马条纹 参考这篇文章：https://blog.csdn.net/HongHuaZu/article/details/127045087
         * 3. 文字、文字大小、等等可自定义未实现
         * 4. paint设置 参考文章：https://blog.csdn.net/zjws23786/article/details/59074806
         * 5. 都做好后，同步到GitHub上，写用例和添加依赖
         */

    }


}
