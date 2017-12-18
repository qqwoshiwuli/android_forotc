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
import com.gqfbtc.mvp.activity.ChooseBuyBtcModeActivity;
import com.gqfbtc.mvp.activity.advertising.BigDealsAdvertisingActivity;
import com.gqfbtc.mvp.activity.advertising.BuyAndSellBTCActivity;
import com.gqfbtc.mvp.activity.main.WebVeiwActivity;
import com.gqfbtc.mvp.activity.posted.PostedBigDealBuyActivity;
import com.gqfbtc.mvp.activity.posted.PostedBigDealSellActivity;
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
import io.reactivex.disposables.Disposable;

/**
 * Created by 郭青枫 on 2017/10/16.
 */

public class HomeFragment extends BasePullFragment<HomeDelegate, HomeBinder> {

    List<HomeAdvertising> defDatas;
    List<Integer> bannerErrorIds;
    HeaderAndFooterWrapper adapter;
    AdvertisingAdapter advertisingAdapter;
    String[] selectLeft = {"买BTC", "卖BTC"
            , "买链克", "卖链克"
    };
    String[] selectRight = {"挂单卖BTC", "挂单买BTC"
            , "挂单卖链克", "挂单买链克"
    };
    int[] selectRightIds = {R.string.ic_btc, R.string.ic_btc
            , R.string.ic_ucx, R.string.ic_ucx
    };
    HomeRightPopu homeRightPopu;
    HomeLeftPopu homeLeftPopu;
    boolean isBuy = true;//广告 买 还是 卖
    List<HomeBanner> homeBanners;
    int chooseType;//选择发广告类型
    boolean isRefushBanner = true;
    Disposable disposable;
    private static final int buy_btc = 0;
    private static final int sell_btc = 1;
    private static final int buy_ucx = 2;
    private static final int sell_ucx = 3;

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
        isBuy = position == 0 || position == 2;
        String type = "";
        if (position == 0) {
            type = "买BTC";
        } else if (position == 1) {
            type = "卖BTC";
        } else if (position == 2) {
            type = "买链克";
        } else if (position == 3) {
            type = "卖链克";
        }
        //                    "买BTC", "卖BTC"
        //                            , "买链克", "卖链克"
        viewDelegate.viewHolder.tv_select.setText(type);
        //禁止banner刷新
        isRefushBanner = false;
        //刷新
        onRefresh();
    }

    //显示左上角弹窗
    private void initTopLeftPopu(View view) {
        if (homeLeftPopu == null) {
            homeLeftPopu = new HomeLeftPopu(getActivity());
            homeLeftPopu.setSelectItem(selectLeft);
            homeLeftPopu.setSelectPositon(tl_8.getCurrentTab(), tl_9.getCurrentTab());
            homeLeftPopu.setPopuListOnItemClick(new PopuListOnItemClick() {
                @Override
                public void onItemClick(int position) {
                    if (position == 0) {
                        tl_8.setCurrentTab(0);
                        tl_9.setCurrentTab(0);
                    } else if (position == 1) {
                        tl_8.setCurrentTab(0);
                        tl_9.setCurrentTab(1);
                    } else if (position == 2) {
                        tl_8.setCurrentTab(1);
                        tl_9.setCurrentTab(0);
                    } else if (position == 3) {
                        tl_8.setCurrentTab(1);
                        tl_9.setCurrentTab(1);
                    }
                    changeSelect(position);

                }
            });
            homeLeftPopu.showDropDown(view);
        } else {
            homeLeftPopu.setSelectPositon(tl_8.getCurrentTab(), tl_9.getCurrentTab());
            homeLeftPopu.showDropDown(view);
        }
    }


    private ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();
    private ArrayList<CustomTabEntity> mTabEntitie2s = new ArrayList<>();
    CommonTabLayout tl_9;
    CommonTabLayout tl_8;
    BGABanner bgaBanner;

    public View initTopView() {
        View view = getActivity().getLayoutInflater().inflate(R.layout.layout_home_top_view, null);
        bgaBanner = (BGABanner) view.findViewById(R.id.banner_guide_content);
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
                , "链克"
        };
        String[] mTitle2s = {"买币", "卖币"};
        tl_8 = (CommonTabLayout) view.findViewById(R.id.tl_8);
        tl_9 = (CommonTabLayout) view.findViewById(R.id.tl_9);
        for (int i = 0; i < mTitle2s.length; i++) {
            mTabEntities.add(new TabEntity(mTitles[i], 0, 0));
            mTabEntitie2s.add(new TabEntity(mTitle2s[i], 0, 0));
        }
        tl_8.setmIndicatorId(R.drawable.shap_gray_border_max_radiu);
        tl_9.setmIndicatorId(R.drawable.shap_gray_border_max_radiu);
        tl_8.setTabData(mTabEntities);
        tl_9.setTabData(mTabEntitie2s);
        OnTabSelectListener linsener = new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                //同步左上角弹窗选项
                if (homeLeftPopu != null) {
                    homeLeftPopu.setSelectPositon(tl_8.getCurrentTab(), tl_9.getCurrentTab());
                }
                int firstPositon = tl_8.getCurrentTab();
                int secondPosition = tl_9.getCurrentTab();
                if (firstPositon == 0 && secondPosition == 0) {
                    changeSelect(0);
                } else if (firstPositon == 0 && secondPosition == 1) {
                    changeSelect(1);
                } else if (firstPositon == 1 && secondPosition == 0) {
                    changeSelect(2);
                } else if (firstPositon == 1 && secondPosition == 1) {
                    changeSelect(3);
                }
            }

            @Override
            public void onTabReselect(int position) {

            }
        };
        tl_9.setOnTabSelectListener(linsener);
        tl_8.setOnTabSelectListener(linsener);
        return view;

    }

    private void initList() {
        defDatas = new ArrayList<>();
        advertisingAdapter = new AdvertisingAdapter(getActivity(), defDatas);
        advertisingAdapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
                if (HomeAdvertising.coin_type_btc.equals(advertisingAdapter.getDatas().get(position - adapter.getHeadersCount()).getCurrency())) {
                    BuyAndSellBTCActivity.startAct(getActivity(), advertisingAdapter.getDatas().get(position - adapter.getHeadersCount()));
                } else {
                    BigDealsAdvertisingActivity.startAct(getActivity(), advertisingAdapter.getDatas().get(position - adapter.getHeadersCount()));
                }
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
                } else if (position == 2) {
                    advertisingSellUcx();
                } else if (position == 3) {
                    advertisingBuyUcx();
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

    public void advertisingSellUcx() {
        if (SingSettingDBUtil.isLogin(getActivity())) {
            chooseType = sell_ucx;
            addRequest(binder.adwkc_beforeSaveAd("3", true, this));
        }
    }

    public void advertisingBuyUcx() {
        if (SingSettingDBUtil.isLogin(getActivity())) {
            chooseType = buy_ucx;
            addRequest(binder.adwkc_beforeSaveAd("3", false, this));
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
                } else if (chooseType == sell_btc) {
                    ChooseBuyBtcModeActivity.startAct(getActivity(), ChooseBuyBtcModeActivity.type_sell, ChooseBuyBtcModeActivity.action_advertising, checkFrozen, 0x123);
                } else if (chooseType == buy_ucx) {
                    PostedBigDealBuyActivity.startAct(getActivity(), checkFrozen, 0x123);
                } else if (chooseType == sell_ucx) {
                    PostedBigDealSellActivity.startAct(getActivity(), checkFrozen, 0x123);
                }
                break;
            case 0x124:
                //广告列表
                List<HomeAdvertising> list = GsonUtil
                        .getInstance().toList(data, HomeAdvertising.class);
                getDataBack(defDatas, list, adapter);
                isRefushBanner = true;
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
        if (disposable != null) {
            disposable.dispose();
        }
        if (tl_8.getCurrentTab() == 0) {
            disposable = binder.ad(!isBuy ? "buy" : "sale", this);
        } else {
            disposable = binder.adUcx(!isBuy ? "buy" : "sale", this);
        }
        addRequest(disposable);
        if (isRefushBanner) {
            addRequest(binder.listCarouselFigure(this));
        }
    }
}
