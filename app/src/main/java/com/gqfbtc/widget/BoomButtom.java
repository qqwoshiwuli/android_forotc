package com.gqfbtc.widget;

import android.animation.ValueAnimator;
import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnticipateOvershootInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.ScreenUtils;
import com.fivefivelike.mybaselibrary.utils.CommonUtils;
import com.fivefivelike.mybaselibrary.utils.callback.DefaultClickLinsener;
import com.fivefivelike.mybaselibrary.view.IconFontTextview;
import com.gqfbtc.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 郭青枫 on 2017/10/15.
 */

public class BoomButtom extends FrameLayout {
    boolean isShow = false;

    List<View> boomBtn;
    List<BoomBtnEntity> entities;
    int xCenter = 0;
    int yCenter = 0;
    ValueAnimator animator;
    Context mContext;

    View controlView;

    public void setControlView(View controlView) {
        this.controlView = controlView;
        controlView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isShow()) {
                    dimess();
                } else {
                    show();
                }
            }
        });
    }

    public boolean isShow() {
        return isShow;
    }

    public void setBoomBtn(List<View> boomBtn) {
        this.boomBtn = boomBtn;
    }

    public void setEntities(List<BoomBtnEntity> entities) {
        this.entities = entities;
    }

    public void setCenter(int xCenter, int yCenter) {
        this.xCenter = xCenter;
        this.yCenter = yCenter;
    }

    public BoomButtom(Context context) {
        super(context);
        initView(context);
    }

    public BoomButtom(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public BoomButtom(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    DefaultClickLinsener defaultClickLinsener;

    public void setDefaultClickLinsener(DefaultClickLinsener defaultClickLinsener) {
        this.defaultClickLinsener = defaultClickLinsener;
    }

    private void initView(Context context) {
        mContext = context;
        this.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isShow) {
                    dimess();
                }
            }
        });
        boomBtn = new ArrayList<>();
        xCenter = ScreenUtils.getScreenWidth() / 2 - context.getResources().getDimensionPixelOffset(R.dimen.trans_80px);
        yCenter = ScreenUtils.getScreenHeight() - context.getResources().getDimensionPixelOffset(R.dimen.trans_60px);
        //        this.setPadding(mContext.getResources().getDimensionPixelOffset(R.dimen.trans_25px),
        //                0, mContext.getResources().getDimensionPixelOffset(R.dimen.trans_25px), 0);
    }

    public void show() {
        this.setBackgroundResource(R.color.color_dark);
        this.setClickable(true);
        if (boomBtn.size() == 0) {
            this.removeAllViews();
            for (int i = 0; i < entities.size(); i++) {
                View view = ((LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.layout_boom_btn, null);
                boomBtn.add(view);
                ViewGroup parent = (ViewGroup) view.getParent();
                if (parent != null)
                    parent.removeView(view);
                addView(view);

                view.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        defaultClickLinsener.onClick(view, boomBtn.indexOf(view), null);
                    }
                });

            }
        }

        isShow = true;
        if (boomBtn.size() != 0 && entities != null) {
            for (int i = 0; i < entities.size(); i++) {
                IconFontTextview boom_icon = (IconFontTextview) boomBtn.get(i).findViewById(R.id.boom_icon);
                TextView boom_title = (TextView) boomBtn.get(i).findViewById(R.id.boom_title);
                TextView boom_subtitle = (TextView) boomBtn.get(i).findViewById(R.id.boom_subtitle);
                ImageView bgImg = (ImageView) boomBtn.get(i).findViewById(R.id.boom_bg);
                LinearLayout boomLin = (LinearLayout) boomBtn.get(i).findViewById(R.id.boom_lin);


                bgImg.setImageResource(entities.get(i).getBgColor());
                boom_icon.setTextColor(mContext.getResources().getColor(entities.get(i).getTxtColor()));
                boom_title.setTextColor(mContext.getResources().getColor(entities.get(i).getTxtColor()));
                boom_subtitle.setTextColor(mContext.getResources().getColor(entities.get(i).getTxtColor()));


                if (!TextUtils.isEmpty(entities.get(i).getmSubTitle())) {
                    boom_subtitle.setVisibility(VISIBLE);
                    boom_subtitle.setText(entities.get(i).getmSubTitle());
                } else {
                    boom_subtitle.setVisibility(GONE);
                }
                boom_title.setText(entities.get(i).getmTitle());
                boom_icon.setText(CommonUtils.getString(entities.get(i).getmIcon()));

                ((FrameLayout.LayoutParams) bgImg.getLayoutParams()).setMargins(entities.get(i).getPadding(), entities.get(i).getPadding(), entities.get(i).getPadding(), entities.get(i).getPadding());
                boomLin.setPadding(entities.get(i).getPadding(), entities.get(i).getPadding(), entities.get(i).getPadding(), entities.get(i).getPadding());

                if (this.getChildCount() < entities.size()) {
                    this.addView(boomBtn.get(i));
                }

                //设置宽高
                LayoutParams layoutParams = (FrameLayout.LayoutParams) boomBtn.get(i).getLayoutParams();
                layoutParams.gravity = Gravity.BOTTOM;
                layoutParams.width = entities.get(i).getWidth();
                layoutParams.height = entities.get(i).getWidth();
                layoutParams.setMargins(entities.get(i).getxPositions(), 0, 0, entities.get(i).getyPositions());
                boomBtn.get(i).setLayoutParams(layoutParams);
                boomBtn.get(i).setVisibility(VISIBLE);


                //设置字体大小
                int sizeHeight = (entities.get(i).getWidth() - entities.get(i).getPadding() - entities.get(i).getPadding());

                boom_icon.setTextSize(TypedValue.COMPLEX_UNIT_PX, sizeHeight / 100 * 45 - 10);
                boom_title.setTextSize(TypedValue.COMPLEX_UNIT_PX, sizeHeight / 5 - 7);
                boom_subtitle.setTextSize(TypedValue.COMPLEX_UNIT_PX, sizeHeight / 100 * 18 - 7);


            }
        }

        for (int i = 0; i < boomBtn.size(); i++) {
            boomBtn.get(i).setVisibility(VISIBLE);
            boomBtn.get(i).setClickable(false);
        }
        final int time = 500;
        if (animator != null) {
            if (animator.isRunning()) {
                animator.cancel();
            }
        }
        animator = ValueAnimator.ofInt(0, time);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int progress = (int) animation.getAnimatedValue();
                for (int i = 0; i < boomBtn.size(); i++) {
                    LayoutParams layoutParams = (FrameLayout.LayoutParams) boomBtn.get(i).getLayoutParams();
                    layoutParams.setMargins(
                            xCenter + (entities.get(i).getxPositions() - xCenter) * progress / time,
                            0, 0,
                            yCenter + (entities.get(i).getyPositions() - yCenter) * progress / time);

                    boomBtn.get(i).setLayoutParams(layoutParams);

                    float scale = ((float) progress / (float) time);
                    boomBtn.get(i).setScaleX(scale);
                    boomBtn.get(i).setScaleY(scale);

                    controlView.setRotation(-45 * scale);

                    if (progress == time) {
                        boomBtn.get(i).setClickable(true);
                    }
                }
            }
        });
        animator.setDuration(time);
        animator.setInterpolator(new AnticipateOvershootInterpolator());
        animator.start();
    }

    public void dimess() {
        //动画
        if (animator != null) {
            if (animator.isRunning()) {
                animator.cancel();
            }
        }
        final int time = 500;
        animator = ValueAnimator.ofInt(time, 0);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int progress = (int) animation.getAnimatedValue();
                for (int i = 0; i < boomBtn.size(); i++) {
                    LayoutParams layoutParams = (FrameLayout.LayoutParams) boomBtn.get(i).getLayoutParams();
                    layoutParams.setMargins(
                            xCenter + (layoutParams.leftMargin - xCenter) * progress / time,
                            0, 0,
                            yCenter + (layoutParams.bottomMargin - yCenter) * progress / time);

                    boomBtn.get(i).setLayoutParams(layoutParams);

                    float scale = ((float) progress / (float) time);
                    boomBtn.get(i).setScaleX(boomBtn.get(i).getScaleX() * scale);
                    boomBtn.get(i).setScaleY(boomBtn.get(i).getScaleY() * scale);

                    controlView.setRotation(controlView.getRotation() * scale);

                    if (progress == 0) {
                        boomBtn.get(i).setVisibility(GONE);

                    }
                }
            }
        });
        animator.setDuration(time);
        animator.setInterpolator(new DecelerateInterpolator());
        animator.start();
        this.setBackground(null);

        for (int i = 0; i < boomBtn.size(); i++) {
            boomBtn.get(i).setClickable(false);
        }
        isShow = false;
        this.setClickable(false);


    }


    public static class BoomBtnEntity {
        String mTitle;
        String mSubTitle;
        int mIcon;

        int xPositions;
        int yPositions;

        int width;
        int padding = 0;

        int txtColor = R.color.white;
        int bgColor = R.color.color_blue;

        public int getTxtColor() {
            return txtColor;
        }

        public BoomBtnEntity setTxtColor(int txtColor) {
            this.txtColor = txtColor;
            return this;
        }

        public int getBgColor() {
            return bgColor;
        }

        public BoomBtnEntity setBgColor(int bgColor) {
            this.bgColor = bgColor;
            return this;
        }

        public int getWidth() {
            return width;
        }

        public BoomBtnEntity setWidth(int width) {
            this.width = width;
            return this;
        }

        public String getmTitle() {
            return mTitle;
        }

        public BoomBtnEntity setmTitle(String mTitle) {
            this.mTitle = mTitle;
            return this;
        }

        public String getmSubTitle() {
            return mSubTitle;
        }

        public BoomBtnEntity setmSubTitle(String mSubTitle) {
            this.mSubTitle = mSubTitle;
            return this;
        }

        public int getmIcon() {
            return mIcon;
        }

        public BoomBtnEntity setmIcon(int mIcon) {
            this.mIcon = mIcon;
            return this;
        }

        public int getxPositions() {
            return xPositions;
        }

        public BoomBtnEntity setxPositions(int xPositions, int yPositions) {
            this.xPositions = xPositions;
            this.yPositions = yPositions;
            return this;
        }

        public int getyPositions() {
            return yPositions;
        }


        public int getPadding() {
            return padding;
        }

        public BoomBtnEntity setPadding(int padding) {
            this.padding = padding;
            return this;
        }
    }
}
