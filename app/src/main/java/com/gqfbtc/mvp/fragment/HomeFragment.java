package com.gqfbtc.mvp.fragment;

import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.blankj.utilcode.util.ScreenUtils;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.fivefivelike.mybaselibrary.base.BasePullFragment;
import com.fivefivelike.mybaselibrary.entity.ToolbarBuilder;
import com.fivefivelike.mybaselibrary.utils.CommonUtils;
import com.fivefivelike.mybaselibrary.utils.GsonUtil;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.gqfbtc.R;
import com.gqfbtc.Utils.glide.GlideUtils;
import com.gqfbtc.adapter.AdvertisingAdapter;
import com.gqfbtc.callback.PopuListOnItemClick;
import com.gqfbtc.entity.TabEntity;
import com.gqfbtc.entity.bean.CheckFrozen;
import com.gqfbtc.entity.bean.HomeAdvertising;
import com.gqfbtc.entity.bean.HomeBanner;
import com.gqfbtc.greenDaoUtils.SingSettingDBUtil;
import com.gqfbtc.mvp.activity.BuyAndSellBTCActivity;
import com.gqfbtc.mvp.activity.ChooseBuyBtcModeActivity;
import com.gqfbtc.mvp.activity.main.WebVeiwActivity;
import com.gqfbtc.mvp.activity.user.RecommendActivity;
import com.gqfbtc.mvp.databinder.HomeBinder;
import com.gqfbtc.mvp.delegate.HomeDelegate;
import com.gqfbtc.mvp.popu.HomeLeftPopu;
import com.gqfbtc.mvp.popu.HomeRightPopu;
import com.gqfbtc.widget.CommonTabLayout;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;
import com.zhy.adapter.recyclerview.wrapper.HeaderAndFooterWrapper;

import java.util.ArrayList;
import java.util.List;

import cn.bingoogolapple.bgabanner.BGABanner;
import cn.bingoogolapple.bgabanner.BGABannerUtil;

/**
 * Created by 郭青枫 on 2017/10/16.
 */

public class HomeFragment extends BasePullFragment<HomeDelegate, HomeBinder> {

    List<HomeAdvertising> defDatas;
    List<Integer> bannerErrorIds;
    HeaderAndFooterWrapper adapter;
    AdvertisingAdapter advertisingAdapter;
    String[] selectLeft = {"买BTC", "卖BTC"
            //  , "买ETH", "卖ETH"
    };
    String[] selectRight = {"挂单卖BTC", "挂单买BTC"
            //, "挂单买ETH", "挂单卖ETH"
    };
    int[] selectRightIds = {R.string.ic_btc, R.string.ic_btc
            //, R.string.ic_eth, R.string.ic_eth
    };
    HomeRightPopu homeRightPopu;
    HomeLeftPopu homeLeftPopu;
    boolean isBuy = true;
    List<HomeBanner> homeBanners;
    int chooseType;
    private static final int buy_btc = 0;
    private static final int sell_btc = 1;

    @Override
    protected void bindEvenListener() {
        super.bindEvenListener();
        initToolbar(new ToolbarBuilder().setTitle("交易").setShowBack(false));//.setSubTitle("发布交易\t" + getResources().getString(R.string.ic_jia)));
        //((IconFontTextview)viewDelegate.getmToolbarSubTitle()).setText(Html.fromHtml("发布交易" + "<small>" + getResources().getString(R.string.ic_jia) + "</small>"));
        initList();
        viewDelegate.viewHolder.lin_top_left_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initTopLeftPopu(v);
            }
        });
        viewDelegate.viewHolder.lin_release.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rightPopu(v);
            }
        });
    }

    private void changeSelect(int position) {
        isBuy = position == 0;
        viewDelegate.viewHolder.tv_select.setText(isBuy ? "买BTC" : "卖BTC");
        onRefresh();
    }

    //显示左上角弹窗
    private void initTopLeftPopu(View view) {
        if (homeLeftPopu == null) {
            homeLeftPopu = new HomeLeftPopu(getActivity());
            homeLeftPopu.setSelectItem(selectLeft);
            homeLeftPopu.setSelectPositon(tl_9.getCurrentTab());
            homeLeftPopu.setPopuListOnItemClick(new PopuListOnItemClick() {
                @Override
                public void onItemClick(int position) {
                    changeSelect(position);
                    tl_9.setCurrentTab(position);
                }
            });
            homeLeftPopu.showDropDown(view);
        } else {
            homeLeftPopu.setSelectPositon(tl_9.getCurrentTab());
            homeLeftPopu.showDropDown(view);
        }
    }


    private ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();
    private ArrayList<CustomTabEntity> mTabEntitie2s = new ArrayList<>();
    CommonTabLayout tl_9;
    BGABanner bgaBanner;

    public View initTopView() {
        View view = getActivity().getLayoutInflater().inflate(R.layout.layout_home_top_view, null);
        bgaBanner = (BGABanner) view.findViewById(R.id.banner_guide_content);
        view.findViewById(R.id.lin_coin).setVisibility(View.GONE);
        //轮播初始化
        List<View> views = new ArrayList<>();
        views.add(BGABannerUtil.getItemImageView(getActivity(), R.drawable.banner1));
        views.add(BGABannerUtil.getItemImageView(getActivity(), R.drawable.banner2));
        views.add(BGABannerUtil.getItemImageView(getActivity(), R.drawable.banner3));
        bgaBanner.setData(views);
        //宽640 高240
        ViewGroup.LayoutParams layoutParams = bgaBanner.getLayoutParams();
        layoutParams.height = ScreenUtils.getScreenWidth() * 240 / 750;
        bgaBanner.setLayoutParams(layoutParams);
        String[] mTitles = {"BTC"
                //, "ETH"
        };
        String[] mTitle2s = {"去买币", "去卖币"};

        CommonTabLayout tl_8 = (CommonTabLayout) view.findViewById(R.id.tl_8);
        tl_9 = (CommonTabLayout) view.findViewById(R.id.tl_9);
        mTabEntities.add(new TabEntity(mTitles[0], 0, 0));
        for (int i = 0; i < mTitle2s.length; i++) {
            mTabEntitie2s.add(new TabEntity(mTitle2s[i], 0, 0));
        }
        tl_8.setmIndicatorId(R.drawable.shap_gray_border_max_radiu);
        tl_9.setmIndicatorId(R.drawable.shap_gray_border_max_radiu);
        tl_8.setTabData(mTabEntities);
        tl_9.setTabData(mTabEntitie2s);
        tl_9.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                //同步左上角弹窗选项
                if (homeLeftPopu != null) {
                    homeLeftPopu.setSelectPositon(position);
                }
                changeSelect(position);
            }

            @Override
            public void onTabReselect(int position) {

            }
        });
        return view;

    }

    private void initList() {
        defDatas = new ArrayList<>();
        advertisingAdapter = new AdvertisingAdapter(getActivity(), defDatas);
        advertisingAdapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
                BuyAndSellBTCActivity.startAct(getActivity(), advertisingAdapter.getDatas().get(position - adapter.getHeadersCount()));
            }

            @Override
            public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
                return false;
            }
        });
        adapter = new HeaderAndFooterWrapper(advertisingAdapter);
        adapter.addHeaderView(initTopView());
        viewDelegate.setNoDataTxt(CommonUtils.getString(R.string.str_nodata_home));
        initRecycleViewPull(adapter, adapter.getHeadersCount(), new LinearLayoutManager(getActivity()));
        viewDelegate.viewHolder.swipeRefreshLayout.setRefreshing(true);
        onRefresh();
    }

    //右上角弹窗
    private void rightPopu(View view) {
        homeRightPopu = new HomeRightPopu(getActivity());
        homeRightPopu.setSelectItem(selectRight, selectRightIds);
        homeRightPopu.showAsDropDown(view);
        homeRightPopu.setPopuListOnItemClick(new PopuListOnItemClick() {
            @Override
            public void onItemClick(int position) {
                homeRightPopu.dismiss();
                if (position == 0) {
                    advertisingSellBtc();
                } else if (position == 1) {
                    advertisingBuyBtc();
                }
            }
        });
    }

    //发布卖广告
    public void advertisingSellBtc() {
        if (SingSettingDBUtil.isLogin(getActivity())) {
            chooseType = sell_btc;
            addRequest(binder.checkUserIsFrozen("1", true, this));
        }
    }
    //发布买广告
    public void advertisingBuyBtc() {
        if (SingSettingDBUtil.isLogin(getActivity())) {
            chooseType = buy_btc;
            addRequest(binder.checkUserIsFrozen("1", false, this));
        }
    }

    @Override
    public HomeBinder getDataBinder(HomeDelegate viewDelegate) {
        return new HomeBinder(viewDelegate);
    }


    @Override
    protected void onServiceSuccess(String data, String info, int status, int requestCode) {
        switch (requestCode) {
            case 0x123:
                //是否冻结
                CheckFrozen checkFrozen = GsonUtil.getInstance().toObj(data, CheckFrozen.class);
                if (chooseType == buy_btc) {
                    ChooseBuyBtcModeActivity.startAct(getActivity(), ChooseBuyBtcModeActivity.type_buy, ChooseBuyBtcModeActivity.action_advertising, checkFrozen, 0x123);
                } else {
                    ChooseBuyBtcModeActivity.startAct(getActivity(), ChooseBuyBtcModeActivity.type_sell, ChooseBuyBtcModeActivity.action_advertising, checkFrozen, 0x123);
                }
                break;
            case 0x124:
                //广告列表
                List<HomeAdvertising> list = GsonUtil
                        .getInstance().toList(data, HomeAdvertising.class);
                getDataBack(defDatas, list, adapter);

                break;
            case 0x125:
                //轮播
                homeBanners = GsonUtil.getInstance().toList(data, HomeBanner.class);
                initBanner();
                break;
        }
    }

    public View getStartSpotView() {
        return tl_9.getmTabsContainer().getChildAt(1);
    }

    //初始化轮播
    private void initBanner() {
        List<String> paths = new ArrayList<>();
        List<String> title = new ArrayList<>();
        bannerErrorIds = new ArrayList<>();
        for (int i = 0; i < homeBanners.size(); i++) {
            paths.add(homeBanners.get(i).getImgAddress());
            title.add("");
        }
        bgaBanner.setAdapter(new BGABanner.Adapter<ImageView, String>() {
            @Override
            public void fillBannerItem(final BGABanner banner, ImageView itemView, String model, final int position) {
                GlideUtils.loadImage(homeBanners.get(position).getImgAddress(), itemView, R.drawable.default_banner, new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        bannerErrorIds.add(position);
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        return false;
                    }
                });
            }
        });
        bgaBanner.setData(paths, title);
        bgaBanner.setDelegate(new BGABanner.Delegate<ImageView, String>() {
            @Override
            public void onBannerItemClick(BGABanner banner, ImageView itemView, String model, int position) {
                if (bannerErrorIds != null && bannerErrorIds.size() > 0) {
                    if (bannerErrorIds.contains(position)) {
                        WebVeiwActivity.startAct(getActivity(), "https://forotc.wordpress.com/");
                        return;
                    }
                }
                if (!RecommendActivity.startAct(getActivity(), homeBanners.get(position).getImgUrl())) {
                    WebVeiwActivity.startAct(getActivity(), homeBanners.get(position).getImgUrl());
                }
            }
        });
    }

    @Override
    protected Class<HomeDelegate> getDelegateClass() {
        return HomeDelegate.class;
    }

    @Override
    protected void refreshData() {
        viewDelegate.viewHolder.swipeRefreshLayout.setRefreshing(true);
        addRequest(binder.ad(!isBuy ? "buy" : "sale", this));
        addRequest(binder.listCarouselFigure(this));
    }
}
