package com.example.decoration.ui.fragments.First;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;

import com.example.decoration.R;
import com.example.decoration.base.BaseMainFragment;
import com.example.decoration.ui.view.LoopViewAdapter;

import java.util.ArrayList;

/**
 * Created by fay on 20/8/1.
 */
public class FirstPageFragment extends BaseMainFragment {
    @Override
    public int getLayoutId() {
        return 0;
    }
    public static FirstPageFragment newInstance() {
        Bundle args = new Bundle();
        FirstPageFragment fragment = new FirstPageFragment();
        fragment.setArguments(args);
        return fragment;
    }

    private ViewPager viewPager;
    private int[] mImg;
    private int[] mImg_id;
    private ArrayList<ImageView> imageViewList;
    private int previousSelectedPosition = 0;
    boolean isRunning = false;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.firstpagefragment, container, false);
        initLoopView(view);
        return view;
    }

    private void initLoopView(View view){
        // 实例化
        viewPager = view.findViewById(R.id.carousel_vp);
        mImg = new int[]{
                R.drawable.loop_img1,
                R.drawable.loop_img2,
                R.drawable.loop_img3
        };
        mImg_id = new int[]{
                R.id.pager_img1,
                R.id.pager_img2,
                R.id.pager_img3
        };
        // 初始化
        imageViewList = new ArrayList<ImageView>();
        for(int i=0; i<mImg.length; i++){
            ImageView imageView = new ImageView(getContext());
            imageView.setBackgroundResource(mImg[i]);
            imageView.setId(mImg_id[i]);
            imageViewList.add(imageView);
        }
        // 放入适配器
        LoopViewAdapter loopViewAdapter= new LoopViewAdapter(imageViewList);
        viewPager.setAdapter(loopViewAdapter);
        // 循环
        int m = (Integer.MAX_VALUE / 2) %imageViewList.size();
        int currentPosition = Integer.MAX_VALUE / 2 - m;
        viewPager.setCurrentItem(currentPosition);

        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                int newPosition = position % imageViewList.size();
                previousSelectedPosition = newPosition;
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        new Thread(){
            @Override
            public void run() {
                isRunning = true;
                while(isRunning){
                    try{
                        Thread.sleep(4000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    //下一条
                    _mActivity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            viewPager.setCurrentItem(viewPager.getCurrentItem()+1);
                        }
                    });
                }
            }
        }.start();
    }
}
