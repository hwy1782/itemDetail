package com.example.ItemDetail;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: hint
 * Date: 13-5-21
 * Time: 上午9:08
 * To change this template use File | Settings | File Templates.
 */
public class ItemDetail extends Activity {

    List<View> views;
    ViewPager viewPager;
    private int offset = 0;
    private int currIndex = 0;
    private int bmpW;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);    //To change body of overridden methods use File | Settings | File Templates.
        setContentView(R.layout.item_detail_layout);
    }

    /**
     * 初始化商品详情大图
     */
    private void initViewPager() {
        views = new ArrayList<View>();
        LayoutInflater inflater = getLayoutInflater();
//        view1=inflater.inflate(R.layout.view_detail, null);

        for(int i = 0; i < 3; i++){
            View tmpView = inflater.inflate(R.layout.item_picture, null);
            views.add(tmpView);
        }

       /* view1= new AtMtView(this);
        view1.setOnItemClickListener(new AtMtViewClickListener());
        view2=inflater.inflate(R.layout.lay2, null);
        view3=inflater.inflate(R.layout.lay3, null);
        views.add(view1);
        views.add(view2);
        views.add(view3);*/
        viewPager.setAdapter(new MyViewPagerAdapter(views));
        viewPager.setCurrentItem(0);
        viewPager.setOnPageChangeListener(new MyOnPageChangeListener());
    }

    public class MyViewPagerAdapter extends PagerAdapter {
        private List<View> mListViews;

        public MyViewPagerAdapter(List<View> mListViews) {
            this.mListViews = mListViews;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object)   {
            container.removeView(mListViews.get(position));
        }


        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            container.addView(mListViews.get(position), 0);
            return mListViews.get(position);
        }

        @Override
        public int getCount() {
            return  mListViews.size();
        }

        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            return arg0==arg1;
        }
    }

    public class MyOnPageChangeListener implements ViewPager.OnPageChangeListener {

        int one = offset * 2 + bmpW;// view1 -> view2
        //        int two = one * 2;
        public void onPageScrollStateChanged(int arg0) {


        }

        public void onPageScrolled(int arg0, float arg1, int arg2) {


        }

        public void onPageSelected(int arg0) {

            Animation animation = new TranslateAnimation(one*currIndex, one*arg0, 0, 0);
            currIndex = arg0;
            animation.setFillAfter(true);// True:
            animation.setDuration(300);
//            imageView.startAnimation(animation);
//            Toast.makeText(ViewPageTest.this, "current page is " + viewPager.getCurrentItem() , Toast.LENGTH_SHORT).show();
        }

    }


}
