package com.cboy.rn.splashscreen;
import android.animation.Animator;
import android.app.Activity;
import android.app.Dialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import com.facebook.react,ReactRootView;

import java.lang.ref.WeakReference;
/**
 * SplashScreen
 * 启动屏
 * from：http://www.devio.org
 * Author:CrazyCodeBoy
 * GitHub:https://github.com/crazycodeboy
 * Email:crazycodeboy@gmail.com
 */
public class SplashScreen {
    private static WeakReference<Activity> mActivity;
    private static FrameLayout rootView;
    private static View splash;

    /**
     * 打开启动屏
     */
    public static void show(final Activity activity,int layoutId) {
        if (activity == null) return;
        if(layoutId == -1 && splash==null)return;
        mActivity = new WeakReference<Activity>(activity);
        rootView = (FrameLayout) activity.findViewById(android.R.id.content);

        if(splash == null){
            LayoutInflater inflater = LayoutInflater.from(activity);
            splash = inflater.inflate(layoutId,rootView,false);
        }

        rootView.addView(splash,new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT));
    }
    public static void show(final Activity activity){
        show(activity,-1);
    }

    /**
     * 关闭启动屏
     */
    public static void hide(Activity activity) {
        if (activity == null) activity = mActivity.get();
        if (activity == null) return;

        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                splash.animate().alpha(0).setDuration(500).setListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {
                        rootView.removeView(splash);
                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {

                    }

                    @Override
                    public void onAnimationRepeat(Animator animation) {

                    }
                }).start();
            }
        });
    }
}
