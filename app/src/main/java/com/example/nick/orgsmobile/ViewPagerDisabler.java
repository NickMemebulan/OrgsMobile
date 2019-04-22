package com.example.nick.orgsmobile;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.animation.DecelerateInterpolator;
import android.widget.Scroller;

import java.lang.reflect.Field;

public class ViewPagerDisabler extends ViewPager {

    private boolean swipingEnabled;

    public ViewPagerDisabler(Context context){
        super(context);
        setMyScroller();
    }

    public ViewPagerDisabler(Context context, AttributeSet attrs){
        super(context, attrs);
        setMyScroller();
        this.swipingEnabled = false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event){
        if(this.swipingEnabled){
            return super.onTouchEvent(event);
        }

        return false;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        if(this.swipingEnabled){
            return super.onInterceptTouchEvent(event);
        }
        // Never allow swiping to switch between pages
        return false;
    }

    public void setSwipeDisabler(boolean swipingEnabled){
        this.swipingEnabled = swipingEnabled;
    }

    private void setMyScroller(){
        try {
            Class<?> viewpager = ViewPager.class;
            Field scroller = viewpager.getDeclaredField("mScroller");
            scroller.setAccessible(true);
            scroller.set(this, new MyScroller(getContext()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public class MyScroller extends Scroller {
        public MyScroller(Context context) {
            super(context, new DecelerateInterpolator());
        }

        @Override
        public void startScroll(int startX, int startY, int dx, int dy, int duration) {
            super.startScroll(startX, startY, dx, dy, 350 /*1 secs*/);
        }
    }
}
