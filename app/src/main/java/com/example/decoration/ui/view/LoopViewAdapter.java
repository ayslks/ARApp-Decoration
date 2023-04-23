package com.example.decoration.ui.view;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import java.util.ArrayList;

// 轮播图
public class LoopViewAdapter extends PagerAdapter {

    private ArrayList<ImageView> imageViewList;

    @Override
    public int getCount() {
        return Integer.MAX_VALUE; // 返回无限大实现无限循环
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object; // 判断是否使用缓存，false则创建instantiateItem，true则使用缓存好的
    }

    public LoopViewAdapter(ArrayList<ImageView> imageViewList){
        this.imageViewList = imageViewList;
    }

    // 1. 返回要显示的条目内容, 创建条目
    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position){
        // container: 容器: ViewPager
        // position: 当前要显示条目的位置
        View view = this.imageViewList.get(position % imageViewList.size());
        int newPosition = position % imageViewList.size();
        ImageView img = imageViewList.get(newPosition);
        ViewPager parent = (ViewPager) view.getParent();
        if (parent != null) {
            parent.removeView(view);
        }
        container.addView(img);
        return img;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        // 不写效果好一些
        // container.removeView((View)object);
    }

}
