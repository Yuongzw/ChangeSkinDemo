package com.yuong.skinlibrary.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;

import com.yuong.skinlibrary.R;
import com.yuong.skinlibrary.core.ViewsMatch;
import com.yuong.skinlibrary.model.AttrsBean;

public class SkinnableConstraintLayout extends ConstraintLayout implements ViewsMatch {

    private AttrsBean attrsBean;
    public SkinnableConstraintLayout(Context context) {
        this(context, null);
    }

    public SkinnableConstraintLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SkinnableConstraintLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        attrsBean = new AttrsBean();

        // 根据自定义属性，匹配控件属性的类型集合，如：background + textColor
        TypedArray typedArray = context.obtainStyledAttributes(attrs,
                R.styleable.SkinnableConstraintLayout,
                defStyleAttr, 0);
        // 存储到临时JavaBean对象
        attrsBean.saveViewResource(typedArray, R.styleable.SkinnableConstraintLayout);
        // 这一句回收非常重要！obtainStyledAttributes()有语法提示！！
        typedArray.recycle();
    }

    @Override
    public void skinnableView() {
        // 根据自定义属性，获取styleable中的background属性
        int key = R.styleable.SkinnableConstraintLayout[R.styleable.SkinnableConstraintLayout_android_background];
        // 根据styleable获取控件某属性的resourceId
        int backgroundResourceId = attrsBean.getViewResource(key);
        if (backgroundResourceId > 0) {
            // 兼容包转换
            Drawable drawable = ContextCompat.getDrawable(getContext(), backgroundResourceId);
            // 控件自带api，这里不用setBackgroundColor()因为在9.0测试不通过
            // setBackgroundDrawable本来过时了，但是兼容包重写了方法
            setBackgroundDrawable(drawable);
        }
    }
}
