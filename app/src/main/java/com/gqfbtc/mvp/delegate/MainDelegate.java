package com.gqfbtc.mvp.delegate;

import android.view.View;
import android.widget.FrameLayout;

import com.fivefivelike.mybaselibrary.view.IconFontTextview;
import com.gqfbtc.R;
import com.gqfbtc.widget.BoomButtomIconText;
import com.gqfbtc.widget.CommonTabLayout;

/**
 * Created by 郭青枫 on 2017/10/14.
 */

public class MainDelegate extends IMDelegate {
    public ViewHolder viewHolder;

    @Override
    public void initView() {
        viewHolder = new ViewHolder(getRootView());

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void ImError() {
        if (imLinsener != null) {
            imLinsener.ImError();
        }
    }

    @Override
    public void ImSuccess() {
        if (imLinsener != null) {
            imLinsener.ImSuccess();
        }
    }

    public static class ViewHolder {
        public View rootView;
        public FrameLayout fl_root;
        public CommonTabLayout tl_2;
        public IconFontTextview tv_center_btn;
        public BoomButtomIconText boom;

        public ViewHolder(View rootView) {
            this.rootView = rootView;
            this.fl_root = (FrameLayout) rootView.findViewById(R.id.fl_root);
            this.tl_2 = (CommonTabLayout) rootView.findViewById(R.id.tl_2);
            this.tv_center_btn = (IconFontTextview) rootView.findViewById(R.id.tv_center_btn);
            this.boom = (BoomButtomIconText) rootView.findViewById(R.id.boom);
        }

    }

    //    public void initializeBmb2() {
    //        BoomMenuButton bmb = viewHolder.bmb;
    //13012345678   111111
    //        bmb.setPiecePlaceEnum(PiecePlaceEnum.DOT_4_1);
    //        bmb.setButtonPlaceEnum(ButtonPlaceEnum.Custom);
    //        bmb.setButtonEnum(ButtonEnum.SimpleCircle);
    //
    //        for (int i = 0; i < 4; i++)
    //            bmb.addBuilder(new SimpleCircleButton.Builder()
    //                    .normalImageRes(R.mipmap.ic_launcher));
    //
    //        bmb.getCustomButtonPlacePositions().add(new PointF(Util.dp2px(-80), Util.dp2px(-80)));
    //        bmb.getCustomButtonPlacePositions().add(new PointF(0, 0));
    //        bmb.getCustomButtonPlacePositions().add(new PointF(Util.dp2px(+80), Util.dp2px(+80)));
    //        bmb.getCustomButtonPlacePositions().add(new PointF(Util.dp2px(+80), Util.dp2px(-80)));
    //    }


}
