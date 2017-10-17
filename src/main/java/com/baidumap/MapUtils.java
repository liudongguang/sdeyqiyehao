package com.baidumap;

import com.baidumap.vo.AddressToJingWei;
import com.baidumap.vo.JingWeiToAddress;
import com.ldg.api.util.JsonUtil;
import com.weixin.util.HttpClientUtil;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.LinkedHashMap;
import java.util.Map;

public class MapUtils {
	private final static String ak = "WTiG3alm8f5HlGgHaAgDxYBq5neWAEg6";
	private final static String sk = "P4V4EoLDGEWdHPdfk7ZbWv5oEt5qdxRB";

	/**
	 * 只写详细地址
	 * 
	 * @param addressName
	 * @throws UnsupportedEncodingException
	 */
	public static AddressToJingWei getJingWeiDuByAddressName(String addressName) throws UnsupportedEncodingException {
		SnCal snCal = new SnCal();
		Map<String, String> paramsMap = new LinkedHashMap<String, String>();
		paramsMap.put("address", addressName);
		paramsMap.put("output", "json");
		paramsMap.put("ak", ak);
		// 调用下面的toQueryString方法，对LinkedHashMap内所有value作utf8编码，拼接返回结果address=%E7%99%BE%E5%BA%A6%E5%A4%A7%E5%8E%A6&output=json&ak=yourak
		String paramsStr = snCal.toQueryString(paramsMap);
		// 对paramsStr前面拼接上/geocoder/v2/?，后面直接拼接yoursk得到/geocoder/v2/?address=%E7%99%BE%E5%BA%A6%E5%A4%A7%E5%8E%A6&output=json&ak=yourakyoursk
		String wholeStr = new String("/geocoder/v2/?" + paramsStr + sk);
		// 对上面wholeStr再作utf8编码
		String tempStr = URLEncoder.encode(wholeStr, "UTF-8");
		String sn = snCal.MD5(tempStr);
		StringBuilder url = new StringBuilder("http://api.map.baidu.com/geocoder/v2/?");
		url.append("address=").append(URLEncoder.encode(addressName, "UTF-8")).append("&output=json&ak=").append(ak)
				.append("&sn=").append(sn);
		String rs = HttpClientUtil.getInstance().sendHttpGet(url.toString());
		AddressToJingWei addressJingWei = JsonUtil.getObjectByJSON(rs, AddressToJingWei.class);
		return addressJingWei;
	}

	/**
	 * "淄博市", "波扎店村"
	 * 
	 * @param city
	 * @param addressName
	 * @throws UnsupportedEncodingException
	 */
	public static AddressToJingWei getJingWeiDuByAddressName(String city, String addressName)
			throws UnsupportedEncodingException {
		SnCal snCal = new SnCal();
		Map<String, String> paramsMap = new LinkedHashMap<String, String>();
		paramsMap.put("city", city);
		paramsMap.put("address", addressName);
		paramsMap.put("output", "json");
		paramsMap.put("ak", ak);
		// 调用下面的toQueryString方法，对LinkedHashMap内所有value作utf8编码，拼接返回结果address=%E7%99%BE%E5%BA%A6%E5%A4%A7%E5%8E%A6&output=json&ak=yourak
		String paramsStr = snCal.toQueryString(paramsMap);
		// 对paramsStr前面拼接上/geocoder/v2/?，后面直接拼接yoursk得到/geocoder/v2/?address=%E7%99%BE%E5%BA%A6%E5%A4%A7%E5%8E%A6&output=json&ak=yourakyoursk
		String wholeStr = new String("/geocoder/v2/?" + paramsStr + sk);
		// 对上面wholeStr再作utf8编码
		String tempStr = URLEncoder.encode(wholeStr, "UTF-8");
		String sn = snCal.MD5(tempStr);
		StringBuilder url = new StringBuilder("http://api.map.baidu.com/geocoder/v2/?city=");
		url.append(URLEncoder.encode(city, "UTF-8")).append("&address=").append(URLEncoder.encode(addressName, "UTF-8"))
				.append("&output=json&ak=").append(ak).append("&sn=").append(sn);
		String rs = HttpClientUtil.getInstance().sendHttpGet(url.toString());
		AddressToJingWei addressJingWei = JsonUtil.getObjectByJSON(rs, AddressToJingWei.class);
		return addressJingWei;
	}

	/**
	 * @param lat
	 *            纬度
	 * @param lng
	 *            经度
	 * 
	 * @throws UnsupportedEncodingException
	 */
	public static JingWeiToAddress getAddressNameByJingWeiDu(String lng, String lat, boolean zhoubian)
			throws UnsupportedEncodingException {
		String zuobiao = lat + "," + lng;
		///////////////////////////////// 获取sn
		SnCal snCal = new SnCal();
		Map<String, String> paramsMap = new LinkedHashMap<String, String>();
		paramsMap.put("location", zuobiao);
		if (zhoubian) {
			paramsMap.put("pois", "1");
		}
		paramsMap.put("output", "json");
		paramsMap.put("ak", ak);
		// 调用下面的toQueryString方法，对LinkedHashMap内所有value作utf8编码，拼接返回结果address=%E7%99%BE%E5%BA%A6%E5%A4%A7%E5%8E%A6&output=json&ak=yourak
		String paramsStr = snCal.toQueryString(paramsMap);
		// 对paramsStr前面拼接上/geocoder/v2/?，后面直接拼接yoursk得到/geocoder/v2/?address=%E7%99%BE%E5%BA%A6%E5%A4%A7%E5%8E%A6&output=json&ak=yourakyoursk
		String wholeStr = new String("/geocoder/v2/?" + paramsStr + sk);
		// 对上面wholeStr再作utf8编码
		String tempStr = URLEncoder.encode(wholeStr, "UTF-8");
		String sn = snCal.MD5(tempStr);
		////////////////////////// 38.76623,116.43213 lat<纬度>,lng<经度>
		StringBuilder url = new StringBuilder("http://api.map.baidu.com/geocoder/v2/?");
		url.append("location=").append(URLEncoder.encode(zuobiao, "UTF-8"));
		if (zhoubian) {
			url.append("&pois=1");
		}
		url.append("&output=json&ak=").append(ak).append("&sn=").append(sn);
		String rs = HttpClientUtil.getInstance().sendHttpGet(url.toString());
		JingWeiToAddress jwta = JsonUtil.getObjectByJSON(rs, JingWeiToAddress.class);
		return jwta;
	}

	public static JingWeiToAddress getAddressNameByJingWeiDu(String lng, String lat)
			throws UnsupportedEncodingException {
		return getAddressNameByJingWeiDu(lng, lat, false);
	}

	public static void main(String[] args) throws UnsupportedEncodingException {
		AddressToJingWei aj = getJingWeiDuByAddressName("淄博市", "波扎店村");
		// AddressToJingWei aj = getJingWeiDuByAddressName("济南市千佛山东路16号");
		JingWeiToAddress jwta = getAddressNameByJingWeiDu(aj.getResult().getLocation().getLng().toString(),
				aj.getResult().getLocation().getLat().toString(), true);
		System.out.println(jwta);
	}
}
