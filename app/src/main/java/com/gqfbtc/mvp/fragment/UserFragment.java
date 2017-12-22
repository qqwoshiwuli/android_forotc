package com.gqfbtc.mvp.fragment;

import android.app.Activity;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.circledialog.CircleDialogHelper;
import com.fivefivelike.mybaselibrary.base.BasePullFragment;
import com.fivefivelike.mybaselibrary.entity.ToolbarBuilder;
import com.fivefivelike.mybaselibrary.utils.CommonUtils;
import com.fivefivelike.mybaselibrary.utils.GsonUtil;
import com.fivefivelike.mybaselibrary.utils.ToastUtil;
import com.fivefivelike.mybaselibrary.view.IconFontTextview;
import com.gqfbtc.R;
import com.gqfbtc.Utils.glide.GlideUtils;
import com.gqfbtc.adapter.SetListAdapter;
import com.gqfbtc.base.Application;
import com.gqfbtc.entity.SetMenuEntity;
import com.gqfbtc.entity.bean.UserData;
import com.gqfbtc.entity.bean.UserLogin;
import com.gqfbtc.greenDaoUtils.SingSettingDBUtil;
import com.gqfbtc.http.HttpUrl;
import com.gqfbtc.mvp.activity.main.LoginActivity;
import com.gqfbtc.mvp.activity.user.AboutUsActivity;
import com.gqfbtc.mvp.activity.user.CollectionAddressActivity;
import com.gqfbtc.mvp.activity.user.MyAddressActivity;
import com.gqfbtc.mvp.activity.user.MyAdvertisingActivity;
import com.gqfbtc.mvp.activity.user.RealNameAuthenticationActivity;
import com.gqfbtc.mvp.activity.user.RecommendActivity;
import com.gqfbtc.mvp.activity.user.SetActivity;
import com.gqfbtc.mvp.databinder.BaseActivityPullBinder;
import com.gqfbtc.mvp.delegate.BaseActivityPullDelegate;
import com.kyleduo.switchbutton.SwitchButton;
import com.zhy.adapter.recyclerview.wrapper.HeaderAndFooterWrapper;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;


public class UserFragment extends BasePullFragment<BaseActivityPullDelegate, BaseActivityPullBinder> {

    List<SetMenuEntity> defDatas;
    SetListAdapter setListAdapter;

    HeaderAndFooterWrapper adapter;

    String[] mTitle;
    //            = {"实名认证", "我的广告", "收款地址", "收币地址",
    //            "设置", "常见问题", "关于我们", "客服中心",
    //            "推荐给朋友",
    //            "退出登陆"};
    int[] mImgId = {R.string.ic_shimingrenzheng, R.string.ic_guanggaofuwudan, R.string.ic_fukuanfangshi, R.string.ic_qianbao, R.string.ic_shezhi,
            R.string.ic_changjianwenti, R.string.ic_guanyu, R.string.ic_kefu, R.string.ic_fenxiang,
            R.string.ic_tuichu};
    private CircleImageView ic_pic;
    private TextView tv_name;
    private TextView tv_volume;

    UserLogin userLogin;
    private IconFontTextview tv_pic;
    private TextView title_txt;
    private SwitchButton checkbox;
    private LinearLayout lin_online;
    private ImageView iv_real_name;
    UserData userData;
    boolean isOnline;
    boolean isHaveDot = false;

    public interface Linsener {
        void logout();
    }

    Linsener linsener;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        linsener = (Linsener) activity;
    }

    @Override
    protected Class<BaseActivityPullDelegate> getDelegateClass() {
        return BaseActivityPullDelegate.class;
    }

    @Override
    protected void bindEvenListener() {
        super.bindEvenListener();
        initToolbar(new ToolbarBuilder().setTitle("个人中心").setShowBack(false));
        userLogin = SingSettingDBUtil.getUserLogin();
        initList();
    }

    public void setHaveDot(boolean haveDot) {
        isHaveDot = haveDot;
        if (adapter != null) {
            initList();
        }
    }

    private void initList() {
        viewDelegate.setShowNoData(false);
        defDatas = new ArrayList<>();
        if (mTitle == null) {
            mTitle = CommonUtils.getStringArray(R.array.sa_title_userfragemnt);
        }
        if (SingSettingDBUtil.isUser()) {
            defDatas.add(new SetMenuEntity().setLeftImgId(mImgId[0]).setTitle(mTitle[0]).setHaveRightImg(true).setHavaContent(true).setContent(userData == null ? "" : (userData.isIsAuth() ? "已实名" : "未实名")).setContentColorId(R.color.color_font3));
            defDatas.add(new SetMenuEntity().setLeftImgId(mImgId[1]).setTitle(mTitle[1]).setHaveRightImg(true));

        }
        defDatas.add(new SetMenuEntity().setLeftImgId(mImgId[2]).setTitle(mTitle[2]).setHaveRightImg(true));
        defDatas.add(new SetMenuEntity().setLeftImgId(mImgId[3]).setTitle(mTitle[3]).setHaveRightImg(true).setHaveLine(true));
        if (SingSettingDBUtil.isUser()) {
            defDatas.add(new SetMenuEntity().setLeftImgId(mImgId[4]).setTitle(mTitle[4]).setHaveRightImg(true));
        }
        //defDatas.add(new SetMenuEntity().setLeftImgId(mImgId[4]).setTitle(mTitle[4]).setHaveRightImg(true));
        defDatas.add(new SetMenuEntity().setLeftImgId(mImgId[6]).setTitle(mTitle[6]).setHaveRightImg(true));
        defDatas.add(new SetMenuEntity().setLeftImgId(mImgId[7]).setTitle(mTitle[7]).setHaveRightImg(true).setHaveLine(true).setHavaContent(isHaveDot).setContent("您有一条新消息"));
        defDatas.add(new SetMenuEntity().setLeftImgId(mImgId[8]).setTitle(mTitle[8]).setHaveRightImg(true).setHaveLine(true).setHavaContent(true).setContent("推荐送BTC").setContentColorId(R.color.color_fe4a4b));
        defDatas.add(new SetMenuEntity().setLeftImgId(mImgId[9]).setTitle(mTitle[9]).setHaveRightImg(true).setHaveLine(true));

        if (setListAdapter == null) {
            setListAdapter = new SetListAdapter(getActivity(), defDatas);
            adapter = new HeaderAndFooterWrapper(setListAdapter);
            adapter.addHeaderView(initHeader());
            initRecycleViewPull(adapter, adapter.getHeadersCount(), new LinearLayoutManager(getActivity()));
            viewDelegate.setIsLoadMore(false);
            setListAdapter.setCheckClick(new SetListAdapter.CheckClick() {
                @Override
                public void checkBoxClick(int position) {

                }

                @Override
                public void onClick(int position) {
                    position = position - adapter.getHeadersCount();
                    click(position);
                }
            });
            viewDelegate.viewHolder.swipeRefreshLayout.setRefreshing(true);
        } else {
            setListAdapter.setData(defDatas);
            adapter.notifyDataSetChanged();
        }
    }

    private void click(int position) {
        if (SingSettingDBUtil.isUser()) {
            if (position == 0) {
                //实名认证
                if (userData != null) {
                    if (!userData.isIsAuth()) {
                        addRequest(binder.checkIsUpload(this));
                    } else {
                        ToastUtil.show(CommonUtils.getString(R.string.str_warning_through_certification));
                    }
                }
            } else if (position == 1) {
                //我的广告
                gotoActivity(MyAdvertisingActivity.class).startAct();
            } else if (position == 2) {
                //收款地址
                gotoActivity(CollectionAddressActivity.class).startAct();
            } else if (position == 3) {
                //收币地址
                gotoActivity(MyAddressActivity.class).startAct();
            } else if (position == 4) {
                //设置
                gotoActivity(SetActivity.class).startAct();
            } else if (position == 5) {
                //关于我们
                gotoActivity(AboutUsActivity.class).startAct();
            } else if (position == 6) {
                //客服中心
                Application.getInstance().startCustomerService(getActivity());
                setHaveDot(false);
            } else if (position == 7) {
                gotoActivity(RecommendActivity.class).startAct();
                //推荐给朋友
            } else if (position == 8) {
                //退出登陆
                logout();
            }
        } else {
            if (position == 0) {
                //收款地址
                gotoActivity(CollectionAddressActivity.class).startAct();
            } else if (position == 1) {
                //收币地址
                gotoActivity(MyAddressActivity.class).startAct();
            } else if (position == 2) {
                //关于我们
                gotoActivity(AboutUsActivity.class).startAct();
            } else if (position == 3) {
                //客服中心
                Application.getInstance().startCustomerService(getActivity());
                setHaveDot(false);
            } else if (position == 4) {
                gotoActivity(RecommendActivity.class).startAct();
                //推荐给朋友
            } else if (position == 5) {
                //退出登陆
                logout();
            }
        }
    }

    private void logout() {
        CircleDialogHelper.initDefaultDialog(getActivity(), CommonUtils.getString(R.string.str_warning_islogout), new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                linsener.logout();
                HttpUrl.getIntance().delectUidAndToken();
                SingSettingDBUtil.logout();
                if (SingSettingDBUtil.isUser()) {
                    binder.doLoginOut();
                } else {
                    binder.ic_doLoginOut();
                }
                gotoActivity(LoginActivity.class).setIsFinish(true).startAct();
            }
        }).show();
    }

    @Override
    public void onResume() {
        super.onResume();
        onRefresh();
    }

    private View initHeader() {
        View view = getActivity().getLayoutInflater().inflate(R.layout.layout_user_header, null);
        this.ic_pic = (CircleImageView) view.findViewById(R.id.ic_pic);
        this.tv_name = (TextView) view.findViewById(R.id.tv_name);
        this.tv_volume = (TextView) view.findViewById(R.id.tv_volume);
        this.tv_pic = (IconFontTextview) view.findViewById(R.id.tv_pic);
        this.title_txt = (TextView) view.findViewById(R.id.title_txt);
        this.checkbox = (SwitchButton) view.findViewById(R.id.checkbox);
        this.lin_online = (LinearLayout) view.findViewById(R.id.lin_online);
        this.iv_real_name = (ImageView) view.findViewById(R.id.iv_real_name);

        if (SingSettingDBUtil.isUser()) {
            lin_online.setVisibility(View.GONE);
        } else {
            checkbox.setChecked(userLogin.isOnLine());
            isOnline = userLogin.isOnLine();
            checkbox.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    binder.ic_editIsOnline(checkbox.isChecked(), "", UserFragment.this);
                }
            });
        }
        GlideUtils.loadImage(userLogin.getAvatar(), ic_pic);
        tv_name.setText(SingSettingDBUtil.isUser() ? userLogin.getNickName() : userLogin.getName());
        tv_volume.setVisibility(View.GONE);

        return view;
    }


    @Override
    public BaseActivityPullBinder getDataBinder(BaseActivityPullDelegate viewDelegate) {
        return new BaseActivityPullBinder(viewDelegate);
    }

    @Override
    protected void onServiceSuccess(String data, String info, int status, int requestCode) {
        switch (requestCode) {
            case 0x128:
                UserData userData = GsonUtil.getInstance().toObj(data, UserData.class);
                GlideUtils.loadImage(userData.getAvatar(), ic_pic);
                initUserData(userData);
                viewDelegate.viewHolder.swipeRefreshLayout.setRefreshing(false);
                break;
            case 0x129:
                gotoActivity(RealNameAuthenticationActivity.class).startAct();
                break;
        }
    }

    private void initUserData(UserData userData) {
        tv_volume.setVisibility(View.VISIBLE);
        tv_volume.setText(userData.getCountSumStr() + "  |  " + userData.getScoreStr());
        if (SingSettingDBUtil.isUser()) {
            iv_real_name.setVisibility(View.VISIBLE);
            iv_real_name.setImageResource(userData.isIsAuth() ? R.drawable.ic_user_real_yes : R.drawable.ic_user_real_no);
            this.userData = userData;
            initList();
        }

    }

    @Override
    protected void refreshData() {
        addRequest(binder.userCenter(SingSettingDBUtil.isUser(), this));
    }

}
