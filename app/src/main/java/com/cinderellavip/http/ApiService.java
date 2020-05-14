package com.cinderellavip.http;


import com.cinderellavip.bean.ListCoupons;
import com.cinderellavip.bean.ListOrders;
import com.cinderellavip.bean.UploadImageResult;
import com.cinderellavip.bean.local.CouponsBean;
import com.cinderellavip.bean.local.HomeGoods;
import com.cinderellavip.bean.local.MineCouponsBean;
import com.cinderellavip.bean.local.OrderBean;
import com.cinderellavip.bean.local.SelectCouponsBean;
import com.cinderellavip.bean.net.BrandResult;
import com.cinderellavip.bean.net.HomeCategoryResult;
import com.cinderellavip.bean.net.IntegralExchangeLogistics;
import com.cinderellavip.bean.net.NetCityBean;
import com.cinderellavip.bean.net.ShopResult;
import com.cinderellavip.bean.net.UserInfo;
import com.cinderellavip.bean.net.cart.CartResult;
import com.cinderellavip.bean.net.goods.GoodsCommentResult;
import com.cinderellavip.bean.net.goods.GoodsResult;
import com.cinderellavip.bean.net.home.CateMoreList;
import com.cinderellavip.bean.net.home.HomeGoodsResult;
import com.cinderellavip.bean.net.home.ShopHomeResult;
import com.cinderellavip.bean.net.mine.IntegralResult;
import com.cinderellavip.bean.net.mine.MineBalanceResult;
import com.cinderellavip.bean.net.mine.MineInfo;
import com.cinderellavip.bean.net.mine.MineInviterResult;
import com.cinderellavip.bean.net.mine.RankMonthItem;
import com.cinderellavip.bean.net.mine.RankResult;
import com.cinderellavip.bean.net.mine.WithDrawHistoryResult;
import com.cinderellavip.bean.net.order.CreateOrderBean;
import com.cinderellavip.bean.net.order.OrderInfo;
import com.cinderellavip.bean.net.order.OrderInfoResult;
import com.cinderellavip.bean.net.order.OrderSettleResult;
import com.cinderellavip.bean.net.order.GetPayResult;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import rx.Observable;


/**
 * Created by jumpbox on 16/5/2.
 */
public interface ApiService {

    @Multipart
    @POST(HttpUrl.upload)
    Observable<BaseResult<UploadImageResult>>
    getUploadImg(@Part() List<MultipartBody.Part> parts);
    /**
     * 登录
     * @param
     * @return
     */
    @POST(HttpUrl.login)
    @FormUrlEncoded
    Observable<BaseResult<UserInfo>>
    getLogin(@FieldMap TreeMap<String, String> map);
    @POST(HttpUrl.get_code)
    @FormUrlEncoded
    Observable<BaseResult>
    getCode(@FieldMap TreeMap<String, String> map);

    @POST(HttpUrl.code_login)
    @FormUrlEncoded
    Observable<BaseResult<UserInfo>>
    getCodeLogin(@FieldMap TreeMap<String, String> map);

    @POST(HttpUrl.register)
    @FormUrlEncoded
    Observable<BaseResult>
    getRegister(@FieldMap TreeMap<String, String> map);

    @POST(HttpUrl.forget_pass)
    @FormUrlEncoded
    Observable<BaseResult>
    getForgetPass(@FieldMap TreeMap<String, String> map);

    @GET(HttpUrl.home_category)
    Observable<BaseResult<HomeCategoryResult>>
    getHomeCategory();


    @GET(HttpUrl.home_index)
    Observable<BaseResult<ShopHomeResult>>
    getHome(@Query("first_category_id") String first_category_id);

    @GET(HttpUrl.home_goods)
    Observable<BaseResult<HomeGoodsResult>>
    getHomeGoods(@QueryMap TreeMap<String, String> map);


    @GET(HttpUrl.home_more_cate)
    Observable<BaseResult<ListResult<CateMoreList>>>
    getHomeMoreCate(@QueryMap TreeMap<String, String> map);

    @GET(HttpUrl.home_more_goods)
    Observable<BaseResult<ListResult<HomeGoods>>>
    getHomeMoreGoods(@QueryMap TreeMap<String, String> map);

    @GET(HttpUrl.search_goods)
    Observable<BaseResult<ListResult<HomeGoods>>>
    getSearchGoods(@QueryMap TreeMap<String, String> map);

    @GET(HttpUrl.search_words)
    Observable<BaseResult<ListResult<String>>>
    getSearchWords();

    @GET(HttpUrl.goods_detail+"{id}")
    Observable<BaseResult<GoodsResult>>
    getGoodsDetail(@Path("id") String id);


    @GET(HttpUrl.order_list)
    Observable<BaseResult<ListOrders<OrderBean>>>
    getOrderList(@QueryMap TreeMap<String, String> map);

    @GET(HttpUrl.order_info+"{id}")
    Observable<BaseResult<OrderInfoResult<OrderInfo>>>
    getOrderDetail(@Path("id") String id);

    @GET(HttpUrl.order_receipt+"{id}")
    Observable<BaseResult>
    getOrderReceipt(@Path("id") String id);

    @GET(HttpUrl.order_logistics+"{id}")
    Observable<BaseResult<IntegralExchangeLogistics>>
    getLogistics(@Path("id") String id);

    @GET(HttpUrl.order_cancel+"{id}")
    Observable<BaseResult>
    getOrderCancel(@Path("id") String id);

    @GET(HttpUrl.goods_comment)
    Observable<BaseResult<GoodsCommentResult>>
    getGoodsComment(@QueryMap TreeMap<String, String> map);

    @GET(HttpUrl.goods_coupons)
    Observable<BaseResult<GoodsResult>>
    getGoodsCoupons(@QueryMap TreeMap<String, String> map);

    @GET(HttpUrl.coupons_receive)
    Observable<BaseResult>
    getReceiveCoupons(@QueryMap TreeMap<String, String> map);

    @GET(HttpUrl.shop_detail)
    Observable<BaseResult<ShopResult>>
    getShopInfo(@QueryMap TreeMap<String, String> map);

    @GET(HttpUrl.brand_detail)
    Observable<BaseResult<BrandResult>>
    getBrandInfo(@QueryMap TreeMap<String, String> map);

    @GET(HttpUrl.goods_for_brand_and_shop)
    Observable<BaseResult<ListResult<HomeGoods>>>
    getBrandGoods(@QueryMap TreeMap<String, String> map);

    @GET(HttpUrl.collect)
    Observable<BaseResult>
    getCollect(@QueryMap TreeMap<String, String> map);

    @GET(HttpUrl.add_cart)
    Observable<BaseResult>
    getAddCart(@QueryMap TreeMap<String, String> map);

    @GET(HttpUrl.modify_cart_number)
    Observable<BaseResult>
    modifyCartNumber(@QueryMap TreeMap<String, String> map);

    @GET(HttpUrl.delete_cart)
    Observable<BaseResult>
    deleteCart(@QueryMap TreeMap<String, String> map);

    @GET(HttpUrl.order_pay_likes)
    Observable<BaseResult<ListResult<HomeGoods>>>
    getLicks();

    @GET(HttpUrl.cart_list)
    Observable<BaseResult<CartResult>>
    getCartData();

    @GET(HttpUrl.settlement_product)
    Observable<BaseResult<OrderSettleResult>>
    getSettlementProduct(@QueryMap TreeMap<String, String> map);

    @GET(HttpUrl.settlement_cart)
    Observable<BaseResult<OrderSettleResult>>
    getSettlementCart(@QueryMap TreeMap<String, String> map);

    @GET(HttpUrl.create_order_product)
    Observable<BaseResult<CreateOrderBean>>
    createOrderByProduct(@QueryMap TreeMap<String, String> map);

    @GET(HttpUrl.create_order_cart)
    Observable<BaseResult<CreateOrderBean>>
    createOrderByCart(@QueryMap TreeMap<String, String> map);

    @GET(HttpUrl.order_pay)
    Observable<BaseResult<GetPayResult>>
    orderPay(@QueryMap TreeMap<String, String> map);

    @GET(HttpUrl.address_list)
    Observable<BaseResult<ListResult<NetCityBean>>>
    getAddressList();

    @POST(HttpUrl.address_edit)
    @FormUrlEncoded
    Observable<BaseResult>
    getEditAddress(@FieldMap TreeMap<String, String> map);

    @GET(HttpUrl.address_delete)
    Observable<BaseResult>
    deleteAddress(@Query("id") String id);

    @GET(HttpUrl.coupons_center)
    Observable<BaseResult<ListCoupons<CouponsBean>>>
    couponsCenter();

    @GET(HttpUrl.coupons_settlement)
    Observable<BaseResult<ListCoupons<SelectCouponsBean>>>
    couponsSettlement(@QueryMap TreeMap<String, String> map);

    @GET(HttpUrl.coupons_mine)
    Observable<BaseResult<ListResult<MineCouponsBean>>>
    getMineCoupons(@QueryMap TreeMap<String, String> map);

    @GET(HttpUrl.mine_center)
    Observable<BaseResult<MineInfo>>
    getMineInfo();

    @GET(HttpUrl.apply_vip)
    Observable<BaseResult>
    applyVip(@Query("invite_code") String invite_code);

    @POST(HttpUrl.update_info)
    @FormUrlEncoded
    Observable<BaseResult>
    updateInfo(@FieldMap TreeMap<String, String> map);

    @POST(HttpUrl.password_reset)
    @FormUrlEncoded
    Observable<BaseResult>
    updatePass(@FieldMap TreeMap<String, String> map);

    @GET(HttpUrl.balance)
    Observable<BaseResult<MineBalanceResult>>
    mineBalance(@QueryMap TreeMap<String, String> map);

    @GET(HttpUrl.withdrawal_apply)
    Observable<BaseResult>
    applyWithDraw(@QueryMap TreeMap<String, String> map);

    @GET(HttpUrl.withdrawal)
    Observable<BaseResult<WithDrawHistoryResult>>
    withDrawHistory(@QueryMap TreeMap<String, String> map);

    @GET(HttpUrl.ming_inviter)
    Observable<BaseResult<MineInviterResult>>
    mine_inviter(@QueryMap TreeMap<String, String> map);

    @GET(HttpUrl.mine_integral)
    Observable<BaseResult<IntegralResult>>
    mine_integral(@QueryMap TreeMap<String, String> map);

    @GET(HttpUrl.ranking)
    Observable<BaseResult<RankResult>>
    ranking(@QueryMap TreeMap<String, String> map);

    @GET(HttpUrl.ranking_history)
    Observable<BaseResult<ListResult<RankMonthItem>>>
    rankingMonth(@QueryMap TreeMap<String, String> map);




}
