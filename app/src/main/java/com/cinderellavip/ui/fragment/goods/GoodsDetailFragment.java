package com.cinderellavip.ui.fragment.goods;

import android.graphics.Paint;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cinderellavip.R;
import com.cinderellavip.adapter.recycleview.CommentAdapter;
import com.cinderellavip.adapter.recycleview.RecommentGoodsAdapter;
import com.cinderellavip.adapter.viewpager.DetailBannerAdapter;
import com.cinderellavip.bean.local.CouponsBean;
import com.cinderellavip.bean.local.GoodsDetialBanner;
import com.cinderellavip.bean.net.goods.GoodsInfo;
import com.cinderellavip.bean.net.goods.GoodsResult;
import com.cinderellavip.bean.net.goods.GroupInfo;
import com.cinderellavip.bean.net.goods.SpikeInfo;
import com.cinderellavip.global.ImageUtil;
import com.cinderellavip.http.ApiManager;
import com.cinderellavip.http.BaseResult;
import com.cinderellavip.http.Response;
import com.cinderellavip.toast.DialogUtil;
import com.cinderellavip.ui.activity.home.BrandDetailActivity;
import com.cinderellavip.ui.activity.home.GoodsDetailActivity;
import com.cinderellavip.weight.CountDownView;
import com.cinderellavip.weight.MyIndicator;
import com.dueeeke.videoplayer.player.VideoView;
import com.tozzais.baselibrary.http.RxHttp;
import com.tozzais.baselibrary.ui.BaseFragment;
import com.tozzais.baselibrary.util.log.LogUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
import butterknife.BindView;
import butterknife.OnClick;

public class GoodsDetailFragment extends BaseFragment implements ViewPager.OnPageChangeListener{
    ViewPager xbanner;
    MyIndicator indicator;
    TextView tvGoodsName; //名称
    TextView tvPrice; //价格
    TextView tvAdvancePrice;//原价
    TextView tv_ship;//快递 包邮
    TextView tv_tax;//快递 包邮
    TextView tv_intro;//销量
    TextView tvCouponText;//满500减100
    TextView tv_promotion_text;//满500减100
    ImageView merchantIcon; //商家图标
    TextView merchantName;//商家名称
    TextView tvCommentNumber; //商品评价（4396）
    View llRecommetCookbookSpace;//间隔
    LinearLayout ll_merchant;//品牌布局
    RecyclerView rlComment; //评价
    RecyclerView rlRecommend;//推荐
    TextView tvGroupOldPrice;
    CountDownView timeView;
    TextView tvGroupPrice;
    TextView tvGroupTip;
    LinearLayout llGroup;
    LinearLayout llCoupon;
    LinearLayout llPromotion;
    LinearLayout ll_normal_price;
    TextView tv_unit;
    TextView tv_return_integral;
    View view_space;
    TextView tv_no_vip_tip;
    TextView tv_group_return;
    LinearLayout ll_address;
    View space_address;
    TextView tv_address;


    @Override
    public int setLayout() {
        return R.layout.fragment_goodsdetail;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        super.initView(savedInstanceState);
        initView();
        LogUtil.e("fragment == initView"+(savedInstanceState != null));
        setRetainInstance(true);
    }

    private void initView(){
        xbanner = mRootView.findViewById(R.id.xbanner);
         indicator= mRootView.findViewById(R.id.indicator);
         tvGoodsName= mRootView.findViewById(R.id.tv_goods_name);
         tvPrice= mRootView.findViewById(R.id.tv_price);
         tvAdvancePrice= mRootView.findViewById(R.id.tv_advance_price);
         tv_ship= mRootView.findViewById(R.id.tv_ship);
         tv_tax= mRootView.findViewById(R.id.tv_tax);
         tv_intro= mRootView.findViewById(R.id.tv_intro);
         tvCouponText= mRootView.findViewById(R.id.tv_coupon_text);
         tv_promotion_text= mRootView.findViewById(R.id.tv_promotion_text);
         merchantIcon= mRootView.findViewById(R.id.merchant_icon);
         merchantName= mRootView.findViewById(R.id.merchant_name);
         tvCommentNumber= mRootView.findViewById(R.id.tv_comment_number);
         llRecommetCookbookSpace= mRootView.findViewById(R.id.ll_recommet_cookbook_space);
         ll_merchant= mRootView.findViewById(R.id.ll_merchant);
         rlComment= mRootView.findViewById(R.id.rl_comment);
         rlRecommend= mRootView.findViewById(R.id.rl_recommend);
         tvGroupOldPrice= mRootView.findViewById(R.id.tv_group_old_price);
         timeView= mRootView.findViewById(R.id.time_view);
         tvGroupPrice= mRootView.findViewById(R.id.tv_group_price);
         tvGroupTip= mRootView.findViewById(R.id.tv_group_tip);
         llGroup= mRootView.findViewById(R.id.ll_group);
         llCoupon= mRootView.findViewById(R.id.ll_coupon);
         llPromotion= mRootView.findViewById(R.id.ll_promotion);
         ll_normal_price= mRootView.findViewById(R.id.ll_normal_price);
         tv_unit= mRootView.findViewById(R.id.tv_unit);
         tv_return_integral= mRootView.findViewById(R.id.tv_return_integral);
         view_space= mRootView.findViewById(R.id.view_space);
         tv_no_vip_tip= mRootView.findViewById(R.id.tv_no_vip_tip);
         tv_group_return= mRootView.findViewById(R.id.tv_group_return);
         ll_address= mRootView.findViewById(R.id.ll_address);
         space_address= mRootView.findViewById(R.id.space_address);
         tv_address= mRootView.findViewById(R.id.tv_address);
    }

    //评价
    private CommentAdapter commentInAdapter;
    //推荐
    private RecommentGoodsAdapter recommentInAdapter;

    @Override
    public void loadData() {

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mActivity);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rlComment.setLayoutManager(linearLayoutManager);
        commentInAdapter = new CommentAdapter();
        rlComment.setAdapter(commentInAdapter);


        LinearLayoutManager recommentLayoutManager = new LinearLayoutManager(mActivity);
        recommentLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        rlRecommend.setLayoutManager(recommentLayoutManager);
        recommentInAdapter = new RecommentGoodsAdapter();
        rlRecommend.setAdapter(recommentInAdapter);


    }

    private GoodsResult goodsResult;

    public void setData(GoodsResult goodsResult) {
        if (!isAdded()){
            new Handler().postDelayed(()->{setData(goodsResult);},500);
            return;
        }
        this.goodsResult = goodsResult;
        GoodsInfo productInfo = goodsResult.product_info;
        List<GoodsDetialBanner> bannerList = new ArrayList<>();
        List<String> images = productInfo.images;
        for (int i = 0; i< images.size(); i++){
            String path =images.get(i);
            if (i == 0 && !TextUtils.isEmpty(productInfo.video)){
                bannerList.add(new GoodsDetialBanner(path, true,productInfo.video));
            }else {
                bannerList.add(new GoodsDetialBanner(path, false));
            }
        }
        bannerAdapter = new DetailBannerAdapter(bannerList, mActivity);
        xbanner.setAdapter(bannerAdapter);
        indicator.bindViewPager(xbanner, bannerList.size());
        View childAt = xbanner.getChildAt(0);
        if (mVideoView == null && childAt != null )
            mVideoView = childAt.findViewById(R.id.player);
        xbanner.setOffscreenPageLimit(bannerList.size());
        xbanner.addOnPageChangeListener(this);
        tvGroupOldPrice.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG); //中划线
        tvGoodsName.setText(productInfo.name);
        tv_ship.setText("运费：" +productInfo.getShip());
        tv_tax.setText("销量：" + productInfo.sale + "件");
        tv_intro.setText("发货：" + productInfo.send_area);

        if (productInfo.hasGroup ){
            //是团购
            llGroup.setVisibility(View.VISIBLE);
            GroupInfo group_info = goodsResult.group_info;
                if (goodsResult.user_is_vip){
                    tv_group_return.setVisibility(View.VISIBLE);
                    if (!TextUtils.isEmpty(goodsResult.integral_rate))
                        tv_group_return.setText("返积分"+goodsResult.integral_rate);
                }else {
                    tv_group_return.setVisibility(View.GONE);

                }
            tvGroupPrice.setText(group_info.getGroup_price());
            tvGroupOldPrice.setText("￥"+group_info.getProduct_price());
            tvGroupTip.setText(group_info.group_user+"人团，"+group_info.has_user+"人已参团");

            timeView.startTime(group_info.end_time - group_info.timestamp);
        } else if (productInfo.hasSpike ){
            //秒杀
            llGroup.setVisibility(View.VISIBLE);
            SpikeInfo group_info = goodsResult.spike_info;
                if (goodsResult.user_is_vip){
                    tv_group_return.setVisibility(View.VISIBLE);
                    if (!TextUtils.isEmpty(goodsResult.integral_rate))
                        tv_group_return.setText("返积分"+goodsResult.integral_rate);
                }else {
                    tv_group_return.setVisibility(View.GONE);
                }
            tvGroupPrice.setText(group_info.getSpikePrice());
            tvGroupOldPrice.setText("￥"+group_info.getProductPrice());
            tvGroupTip.setText("进行中  已抢"+group_info.spike_num+"件");
            timeView.startTime(group_info.end_time - group_info.timestamp);
        }else {
            ll_normal_price.setVisibility(View.VISIBLE);
            if (goodsResult.user_is_vip){
                view_space.setVisibility(View.VISIBLE);
                tv_no_vip_tip.setVisibility(View.GONE);
                tv_unit.setText("会员");
                tvPrice.setText( productInfo.getPrice());
                tvAdvancePrice.setText("非会员￥" + productInfo.getOld_price());
                tv_return_integral.setVisibility(View.VISIBLE);
                if (!TextUtils.isEmpty(goodsResult.integral_rate))
                tv_return_integral.setText("返积分"+goodsResult.integral_rate);
            }else {
                view_space.setVisibility(View.GONE);
                tv_no_vip_tip.setVisibility(View.VISIBLE);
                tv_unit.setText("非会员");
                tvPrice.setText("" + productInfo.getOld_price());
                tvAdvancePrice.setText("会员￥" + productInfo.getPrice());
            }
        }

        List<CouponsBean> coupons = goodsResult.coupons;
        if (coupons == null || coupons.size() == 0) {
            tvCouponText.setText("暂无优惠券");
            if (isAdded()){
                //https://www.jianshu.com/p/7986206aa9d4
                tvCouponText.setTextColor(getResources().getColor(R.color.grayText));
            }

        } else {
            CouponsBean couponsBean = coupons.get(0);
            tvCouponText.setText(couponsBean.condition);
        }
        if (goodsResult.actives == null || goodsResult.actives.size() == 0) {
            tv_promotion_text.setText("暂无活动");
            if (isAdded()){
                //https://www.jianshu.com/p/7986206aa9d4
                tv_promotion_text.setTextColor(getResources().getColor(R.color.grayText));
            }

        } else {

        }

        if (TextUtils.isEmpty(goodsResult.address)){
            ll_address.setVisibility(View.GONE);
            space_address.setVisibility(View.GONE);
        }else {
            ll_address.setVisibility(View.VISIBLE);
            space_address.setVisibility(View.VISIBLE);
            tv_address.setText(goodsResult.address);
        }
        if (productInfo.brand_id == 0){
            ll_merchant.setVisibility(View.GONE);
            llRecommetCookbookSpace.setVisibility(View.GONE);
        }else {
            ll_merchant.setVisibility(View.VISIBLE);
            llRecommetCookbookSpace.setVisibility(View.VISIBLE);
            ImageUtil.loadNet1(mActivity, merchantIcon, productInfo.brand_image);
            merchantName.setText(productInfo.brand_name);
        }

        tvCommentNumber.setText("商品评价(" + goodsResult.comments_num + ")");
        recommentInAdapter.setNewData(goodsResult.recommend_products);
        commentInAdapter.setNewData(goodsResult.comments);





    }

    @OnClick({R.id.ll_coupon, R.id.ll_comment, R.id.ll_merchant})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_coupon:
                getCoupons();
                break;
            case R.id.ll_comment:
                ((GoodsDetailActivity) mActivity).setCurrent(2);
                break;
            case R.id.ll_merchant:
                if (goodsResult != null){
                    GoodsInfo goodsInfo = goodsResult.product_info;
//                    BrandDetailActivity.launch(mActivity);
//                    ShopDetailActivity.launchShop(mActivity,goodsInfo.brand_id+"",goodsInfo.brand_name);
                    BrandDetailActivity.launch(mActivity,goodsInfo.brand_id+"");
                }
                break;
        }
    }

    private void getCoupons(){
        TreeMap<String, String> hashMap = new TreeMap<>();
        hashMap.put("product_id", ((GoodsDetailActivity)mActivity).getId());
        new RxHttp<BaseResult<GoodsResult>>().send(ApiManager.getService().getGoodsCoupons(hashMap),
                new Response<BaseResult<GoodsResult>>(isLoad,getContext()) {
                    @Override
                    public void onSuccess(BaseResult<GoodsResult> result) {
                        DialogUtil.showReceiveCouponDialog(mActivity, result.data.coupons);
                    }

                });
    }


    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    DetailBannerAdapter bannerAdapter;

    @Override
    public void onPageSelected(int position) {
        LogUtil.e(position+"");
        if (position != 0 && mVideoView != null){
            mVideoView.pause();
        }else if (position == 0 && mVideoView != null){
            mVideoView.resume();
        }

    }

    public VideoView mVideoView;
    @Override
    public void onPageScrollStateChanged(int state) {


    }
    @Override
    public void onResume() {
        super.onResume();
        if (mVideoView != null) {
            mVideoView.resume();
        }
    }


    @Override
    public void onPause() {
        super.onPause();
        if (mVideoView != null) {
            mVideoView.pause();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mVideoView != null) {
            mVideoView.release();
        }
    }





}
