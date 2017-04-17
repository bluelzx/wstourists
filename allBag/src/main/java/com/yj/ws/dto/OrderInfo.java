package com.yj.ws.dto;

import java.net.URLEncoder;
import java.util.Date;
import java.util.UUID;

import com.yj.ws.util.common.DateUtil;

public class OrderInfo {
	/**
	 * create the order info. 创建订单信息
	 * 
	 */
	public static String getOrderInfo( String out_trade_no,String subject,String body, String price,String url) throws Exception{
		// 合作者身份ID
		String orderInfo = "partner=" + "\"" + Keys.DEFAULT_PARTNER + "\"";

		// 卖家支付宝账号
		orderInfo += "&seller_id=" + "\"" + Keys.DEFAULT_SELLER + "\"";

		// 商户网站唯一订单号
		orderInfo += "&out_trade_no=" + "\"" + out_trade_no + "\"";

		// 商品名称
		orderInfo += "&subject=" + "\"" + subject + "\"";

		// 商品详情
		orderInfo += "&body=" + "\"" + body + "\"";

		// 商品金额
		orderInfo += "&total_fee=" + "\"" + price + "\"";

		// 服务器异步通知页面路径 //服务器异步通知页面路径  参数 notify_url，如果商户没设定，则不会进行该操作
		orderInfo += "&notify_url=" + "\"" + url + "\"";

		// 接口名称， 固定值
		orderInfo += "&service=\"mobile.securitypay.pay\"";

		// 支付类型， 固定值
		orderInfo += "&payment_type=\"1\"";

		// 参数编码， 固定值
		orderInfo += "&_input_charset=\"utf-8\"";

		// 设置未付款交易的超时时间
		// 默认30分钟，一旦超时，该笔交易就会自动被关闭。
		// 取值范围：1m～15d。
		// m-分钟，h-小时，d-天，1c-当天（无论交易何时创建，都在0点关闭）。
		// 该参数数值不接受小数点，如1.5h，可转换为90m。
		orderInfo += "&it_b_pay=\"30m\"";
		// 支付宝处理完请求后，当前页面跳转到商户指定页面的路径，可空
		orderInfo += "&show_url=\"m.alipay.com\"";
		String apen = sign(orderInfo);
		apen = URLEncoder.encode(apen, "utf-8");
		orderInfo += "&sign=" + "\"" + apen + "\"";
		orderInfo += "&sign_type=\"RSA\"";
		// 调用银行卡支付，需配置此参数，参与签名， 固定值
		// orderInfo += "&paymethod=\"expressGateway\"";
		//log.info("ddddd:"+orderInfo);
		System.out.println(orderInfo);
		return orderInfo;
	}
	/**
	 * sign the order info. 对订单信息进行签名
	 * 
	 * @param content
	 *            待签名订单信息
	 */
	public static String sign(String content) {
		return SignUtils.sign(content, Keys.PRIVATE);
	}
	/**
	 * 生成支付码
	 * @return
	 */
	public static String payCode(){
		UUID uu = UUID.randomUUID();
		StringBuffer sb=new StringBuffer();
		String struu=uu.toString().replaceAll("-", "");
		sb.append(struu);
		String dt=DateUtil.dateFormatToString(new Date(), "yyyyMMdd");
		sb.append(dt);
		return sb.toString();
	}
}



