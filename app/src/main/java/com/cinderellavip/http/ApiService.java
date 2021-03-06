package com.cinderellavip.http;


import com.cinderellavip.bean.AppletsCode;
import com.cinderellavip.bean.ListBean;
import com.cinderellavip.bean.ListCoupons;
import com.cinderellavip.bean.ListData;
import com.cinderellavip.bean.ListOrders;
import com.cinderellavip.bean.OrderResult;
import com.cinderellavip.bean.SearchListResult;
import com.cinderellavip.bean.SearchStore;
import com.cinderellavip.bean.ServiceType;
import com.cinderellavip.bean.SignResult;
import com.cinderellavip.bean.UploadImageResult;
import com.cinderellavip.bean.VersionBean;
import com.cinderellavip.bean.direct.DirectMapPersonItem;
import com.cinderellavip.bean.direct.DirectPersonComment;
import com.cinderellavip.bean.direct.DirectPersonInfo;
import com.cinderellavip.bean.direct.DirectProjectInfo;
import com.cinderellavip.bean.local.CouponsBean;
import com.cinderellavip.bean.local.HomeGoods;
import com.cinderellavip.bean.local.MineCouponsBean;
import com.cinderellavip.bean.local.OperateProductBean;
import com.cinderellavip.bean.local.OrderBean;
import com.cinderellavip.bean.local.SelectCouponsBean;
import com.cinderellavip.bean.net.BrandResult;
import com.cinderellavip.bean.net.HomeCategoryItem;
import com.cinderellavip.bean.net.HomeCategoryResult;
import com.cinderellavip.bean.net.HotList;
import com.cinderellavip.bean.net.IntegralExchangeLogistics;
import com.cinderellavip.bean.net.LifeCityBean;
import com.cinderellavip.bean.net.NetCityBean;
import com.cinderellavip.bean.net.PhoneResult;
import com.cinderellavip.bean.net.ShopResult;
import com.cinderellavip.bean.net.UserInfo;
import com.cinderellavip.bean.net.cart.CartResult;
import com.cinderellavip.bean.net.find.DiscussInfoResult;
import com.cinderellavip.bean.net.find.FindItem;
import com.cinderellavip.bean.net.find.HotTopicItem;
import com.cinderellavip.bean.net.find.ListDiscussesResult;
import com.cinderellavip.bean.net.find.TopicInfoResult;
import com.cinderellavip.bean.net.goods.GoodsCommentResult;
import com.cinderellavip.bean.net.goods.GoodsResult;
import com.cinderellavip.bean.net.home.CateMoreList;
import com.cinderellavip.bean.net.home.HomeGoodsResult;
import com.cinderellavip.bean.net.home.HomeSpikeItem;
import com.cinderellavip.bean.net.home.ShopHomeResult;
import com.cinderellavip.bean.net.life.CategoryResult;
import com.cinderellavip.bean.net.life.DirectCategory;
import com.cinderellavip.bean.net.life.HotSearchItem;
import com.cinderellavip.bean.net.life.LifeCoupon;
import com.cinderellavip.bean.net.life.LifeOrderCommentLabel;
import com.cinderellavip.bean.net.life.LiftCategoryItem;
import com.cinderellavip.bean.net.life.LiftHomeResult;
import com.cinderellavip.bean.net.life.LiftHomeServiceItem;
import com.cinderellavip.bean.net.life.LongOrderDetailResult;
import com.cinderellavip.bean.net.life.LongOrderItem;
import com.cinderellavip.bean.net.life.PayCheckResult;
import com.cinderellavip.bean.net.life.ServiceProjectDetail;
import com.cinderellavip.bean.net.life.ShortOrderItem;
import com.cinderellavip.bean.net.life.ShortPreOrderResult;
import com.cinderellavip.bean.net.life.ShortTimeResult;
import com.cinderellavip.bean.net.mine.ApplyResult;
import com.cinderellavip.bean.net.mine.BlacklistResult;
import com.cinderellavip.bean.net.mine.IntegralResult;
import com.cinderellavip.bean.net.mine.MessageItem;
import com.cinderellavip.bean.net.mine.MineBalanceResult;
import com.cinderellavip.bean.net.mine.MineInfo;
import com.cinderellavip.bean.net.mine.MineInviterNumber;
import com.cinderellavip.bean.net.mine.MineInviterResult;
import com.cinderellavip.bean.net.mine.RankMonthItem;
import com.cinderellavip.bean.net.mine.RankResult;
import com.cinderellavip.bean.net.mine.WithDrawHistoryResult;
import com.cinderellavip.bean.net.order.CreateOrderBean;
import com.cinderellavip.bean.net.order.GetPayResult;
import com.cinderellavip.bean.net.order.InvoiceBean;
import com.cinderellavip.bean.net.order.OrderInfo;
import com.cinderellavip.bean.net.order.OrderInfoResult;
import com.cinderellavip.bean.net.order.OrderSettleResult;
import com.cinderellavip.bean.net.order.ReturnOrderInfoResult;
import com.cinderellavip.bean.potato.MinePotatoResult;
import com.cinderellavip.bean.score.WithDrawExplain;
import com.cinderellavip.bean.spike.SpikeTime;

import java.util.List;
import java.util.TreeMap;

import okhttp3.MultipartBody;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import rx.Observable;


/**
 * Created by jumpbox on 16/5/2.
 */
public interface ApiService {

    @GET(HttpUrl.version)
    Observable<BaseResult<VersionBean>>
    getVersion(@QueryMap TreeMap<String, String> map);

    @Multipart
    @POST(HttpUrl.upload)
    Observable<BaseResult<UploadImageResult>>
    getUploadImg(@Part() List<MultipartBody.Part> parts);
    @Multipart
    @POST(HttpUrl.uploads)
    Observable<BaseResult<UploadImageResult>>
    getUploadImgs(@Part() List<MultipartBody.Part> parts);

    @GET(HttpUrl.get_qr_code)
    Observable<BaseResult<AppletsCode>>
    getAppletsCode(@QueryMap TreeMap<String, String> map);

    @GET(HttpUrl.get_phone)
    Observable<BaseResult<PhoneResult>>
    getPhone();
    /**
     * 登录
     * @param
     * @return
     */
    @POST(HttpUrl.login)
    @FormUrlEncoded
    Observable<BaseResult<UserInfo>>
    getLogin(@FieldMap TreeMap<String, String> map);

    @POST(HttpUrl.is_bind)
    @FormUrlEncoded
    Observable<BaseResult<UserInfo>>
    isBind(@FieldMap TreeMap<String, String> map);

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

    @POST(HttpUrl.bind)
    @FormUrlEncoded
    Observable<BaseResult<UserInfo>>
    getBind(@FieldMap TreeMap<String, String> map);

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
    Observable<BaseResult<SearchListResult<HomeGoods>>>
    getSearchGoods(@QueryMap TreeMap<String, String> map);

     @GET(HttpUrl.search_store)
    Observable<BaseResult<SearchListResult<SearchStore>>>
    getSearchStore(@QueryMap TreeMap<String, String> map);

    @GET(HttpUrl.search_words)
    Observable<BaseResult<HotList<String>>>
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

    @GET(HttpUrl.group_order_info+"{id}")
    Observable<BaseResult<OrderInfoResult<OrderInfo>>>
    getGroupOrderDetail(@Path("id") String id);

    @GET(HttpUrl.order_receipt+"{id}")
    Observable<BaseResult>
    getOrderReceipt(@Path("id") String id);

    @POST(HttpUrl.order_comment)
    @FormUrlEncoded
    Observable<BaseResult>
    orderComment(@FieldMap TreeMap<String, String> map);

    @GET(HttpUrl.order_logistics+"{id}")
    Observable<BaseResult<IntegralExchangeLogistics>>
    getLogistics(@Path("id") String id);

    @GET(HttpUrl.order_cancel+"{id}")
    Observable<BaseResult>
    getOrderCancel(@Path("id") String id);

    @POST(HttpUrl.send_order_cancel)
    @FormUrlEncoded
    Observable<BaseResult>
    getSendOrderCancel(@FieldMap TreeMap<String, String> map);

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

    @GET(HttpUrl.store_category)
    Observable<BaseResult<ListResult<HomeCategoryItem>>>
    storeCategory(@QueryMap TreeMap<String, String> map);

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

    @GET(HttpUrl.group_order_create)
    Observable<BaseResult<CreateOrderBean>>
    createOrderByGroup(@QueryMap TreeMap<String, String> map);

    @GET(HttpUrl.order_pay)
    Observable<BaseResult<GetPayResult>>
    orderPay(@QueryMap TreeMap<String, String> map);


    @GET(HttpUrl.group_order_pay)
    Observable<BaseResult<GetPayResult>>
    orderGroupPay(@QueryMap TreeMap<String, String> map);

    @GET(HttpUrl.group_order_list)
    Observable<BaseResult<ListResult<OrderBean>>>
    getGroupOrderList(@QueryMap TreeMap<String, String> map);

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

    @GET(HttpUrl.score_withdraw_explain)
    Observable<BaseResult<WithDrawExplain>>
    withDrawExplain();

    @GET(HttpUrl.score_withdraw_apply)
    Observable<BaseResult>
    withDrawApply(@QueryMap TreeMap<String, String> map);

    @GET(HttpUrl.collects)
    Observable<BaseResult<ListResult<FindItem>>>
    mineCollect(@QueryMap TreeMap<String, String> map);

    @GET(HttpUrl.withdrawal_apply)
    Observable<BaseResult>
    applyWithDraw(@QueryMap TreeMap<String, String> map);

    @GET(HttpUrl.withdrawal)
    Observable<BaseResult<WithDrawHistoryResult>>
    withDrawHistory(@QueryMap TreeMap<String, String> map);

    @GET(HttpUrl.score_withdraw_history)
    Observable<BaseResult<WithDrawHistoryResult>>
    scoreWithDrawHistory(@QueryMap TreeMap<String, String> map);

    @GET(HttpUrl.ming_inviter_number)
    Observable<BaseResult<MineInviterNumber>>
    mine_inviter_number();

    @GET(HttpUrl.ming_inviter)
    Observable<BaseResult<MineInviterResult>>
    mine_inviter(@QueryMap TreeMap<String, String> map);

    @GET(HttpUrl.mine_integral)
    Observable<BaseResult<IntegralResult>>
    mine_integral(@QueryMap TreeMap<String, String> map);

    @GET(HttpUrl.sign)
    Observable<BaseResult<SignResult>>
    sign();

    @GET(HttpUrl.ranking)
    Observable<BaseResult<RankResult>>
    ranking(@QueryMap TreeMap<String, String> map);

    @GET(HttpUrl.ranking_history)
    Observable<BaseResult<ListResult<RankMonthItem>>>
    rankingMonth(@QueryMap TreeMap<String, String> map);

    @GET(HttpUrl.apply_supplier_cate)
    Observable<BaseResult<ListResult<OperateProductBean>>>
    getOperateCate();

    @POST(HttpUrl.apply_supplier)
    @FormUrlEncoded
    Observable<BaseResult>
    applySupplier(@FieldMap TreeMap<String, String> map);

    @GET(HttpUrl.apply_supplier_result)
    Observable<BaseResult<ApplyResult>>
    applyResult();

    @GET(HttpUrl.shield_users)
    Observable<BaseResult<BlacklistResult>>
    getBlackList(@QueryMap TreeMap<String, String> map);

    @GET(HttpUrl.discuss_shield)
    Observable<BaseResult>
    getShield(@QueryMap TreeMap<String, String> map);

    @GET(HttpUrl.message_center)
    Observable<BaseResult<ListResult<MessageItem>>>
    messageCenter();

    @GET(HttpUrl.message_list)
    Observable<BaseResult<ListResult<MessageItem>>>
    messageList(@QueryMap TreeMap<String, String> map);

    @GET(HttpUrl.mine_potato)
    Observable<BaseResult<MinePotatoResult>>
    minePotato(@QueryMap TreeMap<String, String> map);

    @GET(HttpUrl.refund_reason)
    Observable<BaseResult<ListResult<String>>>
    refundReason();

    @POST(HttpUrl.refund_commit)
    @FormUrlEncoded
    Observable<BaseResult>
    refundCommit(@FieldMap TreeMap<String, String> map);

    @GET(HttpUrl.refund_order_list)
    Observable<BaseResult<ListOrders<OrderBean>>>
    refundOrderList(@QueryMap TreeMap<String, String> map);

    @GET(HttpUrl.refund_info)
    Observable<BaseResult<OrderResult<ReturnOrderInfoResult>>>
    refundOrderInfo(@QueryMap TreeMap<String, String> map);

    @GET(HttpUrl.group_order_settle)
    Observable<BaseResult<OrderSettleResult>>
    getSettlementGroup(@QueryMap TreeMap<String, String> map);

    @GET(HttpUrl.discuss_index)
    Observable<BaseResult<ListDiscussesResult>>
    getFindList(@QueryMap TreeMap<String, String> map);

    @GET(HttpUrl.discuss_info)
    Observable<BaseResult<DiscussInfoResult>>
    getDiscussInfo(@QueryMap TreeMap<String, String> map);

    @GET(HttpUrl.topic_info)
    Observable<BaseResult<TopicInfoResult>>
    getTopicInfo(@QueryMap TreeMap<String, String> map);

    @POST(HttpUrl.discuss_report)
    @FormUrlEncoded
    Observable<BaseResult>
    getDiscussReport(@FieldMap TreeMap<String, String> map);

    @GET(HttpUrl.discuss_collects)
    Observable<BaseResult<ListDiscussesResult>>
    getDiscussCollects();

    @GET(HttpUrl.discuss_search)
    Observable<BaseResult<ListDiscussesResult>>
    getDiscussSearch(@QueryMap TreeMap<String, String> map);

    @POST(HttpUrl.discuss_reply)
    @FormUrlEncoded
    Observable<BaseResult>
    discussReply(@FieldMap TreeMap<String, String> map);

    @POST(HttpUrl.discuss_comment)
    @FormUrlEncoded
    Observable<BaseResult>
    commentReply(@FieldMap TreeMap<String, String> map);

    @GET(HttpUrl.discuss_search_product)
    Observable<BaseResult<ListResult<HomeGoods>>>
    discuss_search_product(@QueryMap TreeMap<String, String> map);


    @GET(HttpUrl.discuss_search_topic)
    Observable<BaseResult<ListResult<HotTopicItem>>>
    discuss_search_topic(@QueryMap TreeMap<String, String> map);

    @POST(HttpUrl.discuss_release)
    @FormUrlEncoded
    Observable<BaseResult>
    discuss_release(@FieldMap TreeMap<String, String> map);

    @POST(HttpUrl.topic_release)
    @FormUrlEncoded
    Observable<BaseResult>
    topic_release(@FieldMap TreeMap<String, String> map);

    @POST(HttpUrl.life_home)
    @FormUrlEncoded
    Observable<BaseResult<LiftHomeResult>>
    life_home(@FieldMap TreeMap<String, String> map);

    @POST(HttpUrl.life_checklist)
    @FormUrlEncoded
    Observable<BaseResult<ListResult<LiftCategoryItem>>>
    life_checklist(@FieldMap TreeMap<String, String> map);

    @POST(HttpUrl.life_category)
    @FormUrlEncoded
    Observable<BaseResult<CategoryResult>>
    life_category(@FieldMap TreeMap<String, String> map);

    @POST(HttpUrl.life_details)
    @FormUrlEncoded
    Observable<BaseResult<ServiceProjectDetail>>
    serviceProjectAndPackageDetail(@FieldMap TreeMap<String, String> map);

    @POST(HttpUrl.life_search_popular)
    Observable<BaseResult<List<HotSearchItem>>>
    getLifeSearchWords();

    @POST(HttpUrl.life_searchList)
    @FormUrlEncoded
    Observable<BaseResult<ListData<LiftHomeServiceItem>>>
    getLifeSearchResult(@FieldMap TreeMap<String, String> map);

    @POST(HttpUrl.life_address_list)
    @FormUrlEncoded
    Observable<BaseResult<ListData<LifeCityBean>>>
    lifeAddress(@FieldMap TreeMap<String, String> map);

    @POST(HttpUrl.life_address_edit)
    @FormUrlEncoded
    Observable<BaseResult>
    editLifeAddress(@FieldMap TreeMap<String, String> map);

    @POST(HttpUrl.life_OneProjectList)
    Observable<BaseResult<List<ServiceType>>>
    lifeCategory();

    @POST(HttpUrl.life_long_order_confirm_order)
    @FormUrlEncoded
    Observable<BaseResult>
    preLongOrder(@FieldMap TreeMap<String, String> map);

    @POST(HttpUrl.life_long_order_list)
    @FormUrlEncoded
    Observable<BaseResult<ListData<LongOrderItem>>>
    longOrderList(@FieldMap TreeMap<String, String> map);

    @POST(HttpUrl.life_order_list)
    @FormUrlEncoded
    Observable<BaseResult<ListData<ShortOrderItem>>>
    shortOrderList(@FieldMap TreeMap<String, String> map);

    @POST(HttpUrl.life_long_order_confirm_price)
    @FormUrlEncoded
    Observable<BaseResult>
    longOrderConfirm(@FieldMap TreeMap<String, String> map);

    @POST(HttpUrl.life_long_cancel)
    @FormUrlEncoded
    Observable<BaseResult>
    cancelLongOrder(@FieldMap TreeMap<String, String> map);

     @POST(HttpUrl.life_order_cancel)
    @FormUrlEncoded
    Observable<BaseResult>
    cancelShortOrder(@FieldMap TreeMap<String, String> map);

    @POST(HttpUrl.life_long_order_prepay)
    @FormUrlEncoded
    Observable<BaseResult<PayCheckResult>>
    prePayLongOrder(@FieldMap TreeMap<String, String> map);

    @POST(HttpUrl.life_coupons)
    @FormUrlEncoded
    Observable<BaseResult<ListData<LifeCoupon>>>
    lifeCoupon(@FieldMap TreeMap<String, String> map);

     @POST(HttpUrl.life_direct_order_coupins)
    @FormUrlEncoded
    Observable<BaseResult<ListData<LifeCoupon>>>
    directCoupon(@FieldMap TreeMap<String, String> map);

    @POST(HttpUrl.life_coupons_receive_condition)
    @FormUrlEncoded
    Observable<BaseResult<ListData<LifeCoupon>>>
    categoryCoupon(@FieldMap TreeMap<String, String> map);

    @POST(HttpUrl.life_coupons_receive)
    @FormUrlEncoded
    Observable<BaseResult>
    receiveCategoryCoupon(@FieldMap TreeMap<String, String> map);

    @POST(HttpUrl.life_long_order_coupons)
    @FormUrlEncoded
    Observable<BaseResult<ListData<LifeCoupon>>>
    longOrderCanUse(@FieldMap TreeMap<String, String> map);

    @POST(HttpUrl.life_long_pay)
    @FormUrlEncoded
    Observable<BaseResult<GetPayResult>>
    lifeOrderPay(@FieldMap TreeMap<String, String> map);

    @POST(HttpUrl.life_long_order_info)
    @FormUrlEncoded
    Observable<BaseResult<LongOrderDetailResult>>
    longOrderDetail(@FieldMap TreeMap<String, String> map);

    @POST(HttpUrl.life_order_info)
    @FormUrlEncoded
    Observable<BaseResult<ShortOrderItem>>
    shortOrderDetail(@FieldMap TreeMap<String, String> map);

    @POST(HttpUrl.life_order_pre_order)
    @FormUrlEncoded
    Observable<BaseResult<ShortPreOrderResult>>
    shortPreOrder(@FieldMap TreeMap<String, String> map);

    @POST(HttpUrl.life_direct_order_pre)
    @FormUrlEncoded
    Observable<BaseResult<ShortPreOrderResult>>
    directPreOrder(@FieldMap TreeMap<String, String> map);

    @POST(HttpUrl.life_order_time)
    @FormUrlEncoded
    Observable<BaseResult<ShortTimeResult>>
    shortOrderTime(@FieldMap TreeMap<String, String> map);

    @POST(HttpUrl.life_direct_time)
    @FormUrlEncoded
    Observable<BaseResult<ShortTimeResult>>
    directPersonTime(@FieldMap TreeMap<String, String> map);

    @POST(HttpUrl.life_order_confirm_order)
    @FormUrlEncoded
    Observable<BaseResult<Integer>>
    createShortPreOrder(@FieldMap TreeMap<String, String> map);

    @POST(HttpUrl.life_direct_order_confirm)
    @FormUrlEncoded
    Observable<BaseResult<Integer>>
    createDirectOrder(@FieldMap TreeMap<String, String> map);


    @POST(HttpUrl.life_order_pay_info)
    @FormUrlEncoded
    Observable<BaseResult<PayCheckResult>>
    prePayShortOrder(@FieldMap TreeMap<String, String> map);

    @POST(HttpUrl.life_pay)
    @FormUrlEncoded
    Observable<BaseResult<GetPayResult>>
    projectOrderPay(@FieldMap TreeMap<String, String> map);

    @POST(HttpUrl.life_direct_cate)
    Observable<BaseResult<List<DirectCategory>>>
    directCate();

    @GET(HttpUrl.spike_time)
    Observable<BaseResult<ListResult<SpikeTime>>>
    spikeTime();

    @GET(HttpUrl.spike_list)
    Observable<BaseResult<ListResult<HomeSpikeItem>>>
    spikeList(@QueryMap TreeMap<String, String> map);

    @GET(HttpUrl.spike_alert)
    Observable<BaseResult>
    spikeAlert(@QueryMap TreeMap<String, String> map);

    @POST(HttpUrl.life_direct_list)
    @FormUrlEncoded
    Observable<BaseResult<ListData<DirectMapPersonItem>>>
    getDirectPerson(@FieldMap TreeMap<String, String> map);

    @POST(HttpUrl.life_direct_list_map)
    @FormUrlEncoded
    Observable<BaseListResult<DirectMapPersonItem>>
    getDirectMapPerson(@FieldMap TreeMap<String, String> map);

    @POST(HttpUrl.life_service_person_detail)
    @FormUrlEncoded
    Observable<BaseResult<DirectPersonInfo>>
    getPersonInfo(@FieldMap TreeMap<String, String> map);

    @POST(HttpUrl.life_project_comment)
    @FormUrlEncoded
    Observable<BaseResult<ListBean<ListData<DirectPersonComment>>>>
    getPersonComment(@FieldMap TreeMap<String, String> map);

    @POST(HttpUrl.life_project_detail)
    @FormUrlEncoded
    Observable<BaseResult<DirectProjectInfo>>
    getDirectProjectInfo(@FieldMap TreeMap<String, String> map);

    @POST(HttpUrl.life_order_comment_label)
    Observable<BaseListResult<LifeOrderCommentLabel>>
    getCommentLabel();
    @POST(HttpUrl.life_order_comment)
    @FormUrlEncoded
    Observable<BaseResult>
    getComment(@FieldMap TreeMap<String, String> map);

    @POST(HttpUrl.apply_invoice)
    @FormUrlEncoded
    Observable<BaseResult>
    apply_invoice(@FieldMap TreeMap<String, String> map);

    @GET(HttpUrl.invoice_info)
    Observable<BaseResult<InvoiceBean>>
    invoice_info(@QueryMap TreeMap<String, String> map);

}
