package com.fivefivelike.mybaselibrary.utils;

/**
 * Created by 郭青枫 on 2017/1/10.
 * 全局浮动的按钮
 */

public class FloatButtonUtils {
//    private static  float ALPHA_HOMEPAGE_INDICATOR = 0.5f;
//    private static View homepage_indicator;
//    private static  WindowManager.LayoutParams wmParams;
//    //创建浮动窗口设置布局参数的对象
//    private static WindowManager mWindowManager;
//    private static FloatButtonUtils floatButtonUtils;
//    private FloatButtonUtils() {
//    }
//    public static FloatButtonUtils getInstance(){
//            if(floatButtonUtils==null){
//                synchronized (FloatButtonUtils.class){
//                if(floatButtonUtils==null){
//                    floatButtonUtils=new FloatButtonUtils();
//                }
//                }
//            }
//            return  floatButtonUtils;
//    }
//    public static void initFloatingView(Context mContext) {
//        wmParams = new WindowManager.LayoutParams();
//        //获取的是WindowManagerImpl.CompatModeWrapper
//        mWindowManager = (WindowManager) mContext.getSystemService(WINDOW_SERVICE);
//        Log.i(TAG, "mWindowManager--->" + mWindowManager);
//        //设置window type
//        wmParams.type = WindowManager.LayoutParams.TYPE_PHONE;
//        //设置图片格式，效果为背景透明
//        wmParams.format = PixelFormat.RGBA_8888;
//        //设置浮动窗口不可聚焦（实现操作除浮动窗口外的其他可见窗口的操作）
//        wmParams.flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;
//        //调整悬浮窗显示的停靠位置为左侧置顶
//        wmParams.gravity = Gravity.LEFT | Gravity.TOP;
//        // 以屏幕左上角为原点，设置x、y初始值，相对于gravity
//        wmParams.x = 0;
//        wmParams.y = 100;
//        //设置悬浮窗口长宽数据
//        wmParams.width = WindowManager.LayoutParams.WRAP_CONTENT;
//        wmParams.height = WindowManager.LayoutParams.WRAP_CONTENT;
//         /*// 设置悬浮窗口长宽数据
//        wmParams.width = 200;
//        wmParams.height = 80;*/
//
//        wmParams.alpha = ALPHA_HOMEPAGE_INDICATOR;
//
//        final int screenWidth = mWindowManager.getDefaultDisplay().getWidth();
//        final int screenHeight = mWindowManager.getDefaultDisplay().getHeight();
//
//        homepage_indicator = View.inflate(mContext, R.layout.view_homepage_indicator, null);
//        homepage_indicator.setOnTouchListener(new View.OnTouchListener() {
//            int[] downParams = new int[]{0, 0};
//            long action_down_time;
//
//            @Override
//            public boolean onTouch(final View v, final MotionEvent event) {
//
//                final int rawX = (int) event.getRawX();
//                final int rawY = (int) event.getRawY();
//
//                switch (event.getAction()) {
//                    case MotionEvent.ACTION_DOWN:
//                        downParams[0] = (int) event.getX();
//                        downParams[1] = rawY - v.getTop();
//                        startActionDownAnim(v);
//                        action_down_time = System.currentTimeMillis();
//                        break;
//
//                    case MotionEvent.ACTION_MOVE:
//                        wmParams.x = (int) event.getRawX() - v.getMeasuredWidth() / 2;
//                        wmParams.y = (int) event.getRawY() - v.getMeasuredHeight() / 2;
//                        mWindowManager.updateViewLayout(v, wmParams);
//                        LogUtil.i("Action_move---X:" + wmParams.x + " ||| Y:" + wmParams.y);
//                        break;
//                    case MotionEvent.ACTION_UP:
//                        int[] distances = new int[]{rawX - v.getMeasuredWidth() / 2, rawY - v.getMeasuredHeight() / 2, screenWidth - rawX - v.getMeasuredWidth() / 2, screenHeight - rawY - v.getMeasuredHeight() / 2};
//                        int minDistance = distances[0];
//                        int position = 0;
//                        for (int i = 1; i < distances.length; i++) {
//                            if (distances[i] < minDistance) {
//                                position = i;
//                                minDistance = distances[i];
//                            }
//                        }
//                        ValueAnimator translateAnim = ValueAnimator.ofInt(minDistance, 0);
//                        final int finalPosition = position;
//                        final int finalMinDistance = minDistance;
//                        translateAnim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
//                            @Override
//                            public void onAnimationUpdate(ValueAnimator animation) {
//                                int curValue = (int) animation.getAnimatedValue();
//                                switch (finalPosition) {
//                                    case 0:// left
//                                        wmParams.x = curValue;
//                                        break;
//
//                                    case 1:// top
//                                        wmParams.y = curValue;
//                                        break;
//
//                                    case 2:// right
//                                        wmParams.x = screenWidth - curValue - v.getMeasuredWidth();
//                                        break;
//
//                                    case 3:// bottom
//                                        wmParams.y = screenHeight - curValue - v.getMeasuredHeight();
//                                        break;
//                                }
//
//                                wmParams.alpha = (1 - ALPHA_HOMEPAGE_INDICATOR) * curValue / finalMinDistance + ALPHA_HOMEPAGE_INDICATOR;
//                                LogUtil.i("floating indicator X:" + wmParams.x + " ||| floating indicator Y:" + wmParams.y);
//                                mWindowManager.updateViewLayout(v, wmParams);
//                            }
//                        });
//                        translateAnim.setDuration(500);
//                        translateAnim.start();
//                        if (System.currentTimeMillis() - action_down_time > 100)
//                            return true;
//                        break;
//
//                }
//
//                return false;
//            }
//
//            private void startActionDownAnim(final View v) {
//                ValueAnimator fadeInAnim = ValueAnimator.ofFloat(ALPHA_HOMEPAGE_INDICATOR, 1f);
//                fadeInAnim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
//                    @Override
//                    public void onAnimationUpdate(ValueAnimator animation) {
//                        wmParams.alpha = (float) animation.getAnimatedValue();
//                        LogUtil.i("floating indicator X:" + wmParams.x + " ||| floating indicator Y:" + wmParams.y);
//                        mWindowManager.updateViewLayout(v, wmParams);
//                    }
//                });
//                fadeInAnim.setDuration(500);
//                fadeInAnim.start();
//            }
//        });
//        homepage_indicator.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
////                Intent intent = new Intent(ActUtil.getInstance().getTopActivity(), MainAcR.class);
////                startActivity(intent);
//            }
//        });
//        //添加view
//        mWindowManager.addView(homepage_indicator, wmParams);
//    }
//
//    public void setFloatingIndicatorVisibility(int visibility) {
//        if (homepage_indicator!=null&&homepage_indicator.getVisibility() != visibility) {
//            homepage_indicator.setVisibility(visibility);
//        }
//    }
//
//    public void onTerminate() {
//        if (homepage_indicator != null) {
//            //移除悬浮窗口
//            mWindowManager.removeView(homepage_indicator);
//        }
//    }
//
//    public void setFloatingIndicatorClickListener(View.OnClickListener onClickListener) {
//        if(homepage_indicator!=null) {
//            homepage_indicator.setOnClickListener(onClickListener);
//        }
//    }
}
