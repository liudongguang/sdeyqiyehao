package com.weixin.constant;

public interface WeixinConstant {
	String YYYY_MM_DD = "yyyy-MM-dd";
	String YYYY_MM_DD_hh_mm_ss = "yyyy-MM-dd HH:mm:ss";
	String HuanHang = "\r\n";
	String SHA1 = "SHA-1";
	String WX_PARAM_TOKEN = "token";
	String WX_CORPID = "corpid";
	String WX_secret = "secret";
	String WX_ENCODINGAESKEY = "encodingaeskey";
	String GETACCESS_TOKENURL = "getAccess_tokenURL";
	String SENDMSG = "sendMSG";
	String GETWXSERVERIP = "getWXServerIp";
	///////////////////// 客服
	String WXKF_PARAM_TOKEN = "kf_token";
	String WXKF_ENCODINGAESKEY = "kf_EncodingAESKey";
	String WXINKF_PARAM_TOKEN = "inkf_token";
	String WXINKF_ENCODINGAESKEY = "inkf_EncodingAESKey";
	String WXKF_TOKF = "sendToKF";
	///////////////////////

	///////// 会话
	String WXHUIHUA_PARAM_TOKEN = "huihua_token";
	String WXHUIHUA_ENCODINGAESKEY = "huihua_EncodingAESKey";
	//////////

	String WX_UTF8 = "UTF-8";
	String NOTNEED_CDATA_1 = "CreateTime";
	String NOTNEED_CDATA_2 = "MsgId";
	String NOTNEED_CDATA_3 = "Location_X";
	String NOTNEED_CDATA_4 = "Location_Y";
	String NOTNEED_CDATA_5 = "Scale";
	String NOTNEED_CDATA_6 = "PackageId";

	String WX_MESSAGETYPE_TEXT = "text"; // 文本消息
	String WX_MESSAGETYPE_IMAGE = "image"; // 图片消息
	String WX_MESSAGETYPE_VOICE = "voice"; // 语音消息
	String WX_MESSAGETYPE_VIDEO = "video"; // 视频消息
	String WX_MESSAGETYPE_SHORTVIDEO = "shortvideo"; // 小视频消息
	String WX_MESSAGETYPE_LOCATION = "location"; // 地理位置消息
	String WX_MESSAGETYPE_LINK = "link"; // 地理位置消息

	String WX_MESSAGETYPE_EVENT = "event";

	String IN_COMM_ToUserName = "ToUserName";
	String IN_COMM_FromUserName = "FromUserName";
	String IN_COMM_CreateTime = "CreateTime";
	String IN_COMM_MsgType = "MsgType";
	String IN_COMM_MsgId = "MsgId";

	String IN_COMM_AgentID = "AgentID";
	String IN_COMM_Event = "Event";

	String IN_COMM_Event_enter_agent = "enter_agent";
	String IN_COMM_Event_LOCATION = "LOCATION";
	String IN_COMM_EventKey = "EventKey";
	String IN_COMM_EVENT_LOCATION_Latitude = "Latitude";
	String IN_COMM_EVENT_LOCATION_Longitude = "Longitude";
	String IN_COMM_EVENT_LOCATION_Precision = "Precision";

	String IN_TEXT_Content = "Content";

	String IN_Encrypt = "Encrypt";

	String IN_IMAGE_PICURL = "PicUrl";

	String IN_COMMON_MEDIAID = "MediaId";
	String IN_COMMON_FORMAT = "Format";
	String IN_COMMON_THUMBMEDIAID = "ThumbMediaId";
	String IN_VOICE_RECOGNITION = "Recognition";
	String IN_LOCATION_LABEL = "Label";

	String IN_LINK_TITLE = "Title";
	String IN_LINK_DESCRIPTION = "Description";
	String IN_LINK_URL = "Url";

	String KF_PACKAGEID = "PackageId";
	////////////////////////// 授权
	String SQ_getCodeURL = "sq_getCodeURL";
	String SQ_getUserInfoURL = "sq_getUserInfoURL";
	String SQ_getDetailUserInfoURL = "sq_getDetailUserInfoURL";
	////////
	String SQ_sq_loginpage = "sq_loginpage";
	String SQ_sq_getUserDetil = "sq_getUserDetil";
	String SERVER = "server";

	////////// jssdk
	String jssdk_get_jsapi_ticketURL = "get_jsapi_ticketURL";
	/////// 企业号管理组权限验证
	String getGroup_ticketURL = "getGroup_ticketURL";
	///////////////////// 资源接口
	String qy_getAppInfo = "qy_getAppInfo";
	String qy_setAppInfo = "qy_setAppInfo";
	String qy_getAppList = "qy_getAppList";
	String qy_getAllDepart = "getAllDepart";

	String getEmployeeByDepartMentID = "getEmployeeByDepartMentID";
	String GETXIANGXIEMPLOYEEBYDEPARTMENTID = "getXiangxiEmployeeByDepartMentID";
	//////获取临时素材
	String getLinShiSC="getLinShiSC";
	String TYPE_jpeg="image/jpeg";
	
	
	String uploadPath_liuyan="liuyanbanIMG";
	String session_wxUSER="session_wxUSER";
	String session_WXDepartment="session_WXDepartment";
}
