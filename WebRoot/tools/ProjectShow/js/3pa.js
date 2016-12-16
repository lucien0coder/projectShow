var userName = "";  //全局变量  本地用户的用户名
var userID = "";    //全局变量  本地用户的用户ID
var accessToken = "19d90b4c83a647158deb839ce4f165be";

//先使用这两个IP和aid，到时正式版上线再改IP和重新申请3PA
var BASE_URL= "http://test_core.wetoband.com/"; //核心IP  调用3PA接口时使用的IP
var WEB_URL="http://test.wetoband.com/"; //测试版吾托帮web路径      调用互动室消息工具时使用的IP
//var BASE_URL= "http://core.wetoband.com/"; //正式
//var WEB_URL="http://www.wetoband.com/"; //正式
//var BASE_URL_PARAMS="aid=47535171535347468515" + "&format=json";  //正式版aid
var BASE_URL_PARAMS="aid=40074504121116826719" + "&format=json";  //测试版aid


/**
 * 初始化本地用户名和用户ID或根据其他用户账号获取其他用户名
 * 
 * @param account
 */
function getUserNameByUserAccount(account){
	
	var field = '&field=["userID","userName"]'; //选择只返回用户ID和用户名（如不做限制即url不加上field，则返回所有数据）
	var query = '&query=' + '{"userAccount":"' + account + '"}'; //搜索参数
	var url = BASE_URL + 'core/v4/user?'+BASE_URL_PARAMS+'&access_token='+accessToken+field+query;
	var name ='';
	$.ajax({
		async: false,
		url : url,
		type : 'get',
		dataType : 'JSON',
		success : function(data) {
			if(undefined != data){
				if(0 != data.total){
					if(account == userAccount){
						userID=data.rows[0].userID;
						userName = data.rows[0].userName;
					}
					name = data.rows[0].userName;
				}
			}else{
				alert('获取用户信息失败！');
			}
		}
	});
    return name;
}

/** 将其他ID 转为自己的视图ID*/
function getViewIDByObjId(objid){
	var bViewId = '';
	var objID = '&objID='+objid;
	var url = BASE_URL + 'core/v4/user/'+userID+'/obj?'+BASE_URL_PARAMS+'&access_token='+accessToken+objID;
	$.ajax({
		async:false,
		url:url,
		type:'get',
		timeout:6000,
		success:function(data){
			if(data.total != 0){
				bViewId = data.rows[0].objID;
			}else{
				return 0;
			}
		},
		error :function(err){
			
		}
	});
	return bViewId;
}


/**
 * 获取某帮区的所有用户
 * 
 * @param long
 *            bvid 帮区视图ID
 * @param function
 *            success 成功回调
 * @param function
 *            error 失败回调
 */
function getBandUsers(bvid, success, error) {
	var sort = '&sort=' + '[{"userID":"desc"}]';    //根据用户ID进行排序
	var field = '&field=["userAccount","userName"]';  //选择只返回用户ID和用户名
	var url = BASE_URL + 'core/v4/band/'+bvid+'/user?'+BASE_URL_PARAMS+'&access_token='+accessToken+field+sort;
	$.ajax({
		url : url,
		type : 'get',
		dataType : 'JSON',
		success : function(data) {
			success && success(data);
		},
		error : function(data) {
			error && error(data);
		}
	});
}


/**
 * 获取帮区下的互动室
 * 
 * @param bvid
 *            帮区id
 * @param success
 *            成功回调方法
 * @param error
 *            失败回调方法
 */
function getChatRoomByBand(bvid){
	var sort = '&sort=' + '[{"objID":"desc"}]';   //排序
	var field = '&field=["chatroomID","name"]';  //只获取互动室ID和互动室名
	var url = BASE_URL + 'core/v4/band/'+bvid+'/chatroom?'+BASE_URL_PARAMS+'&access_token='+accessToken+field+sort;
	var chatRoomList = '';
	$.ajax({
		async: false,
		url : url,
		type : 'get',
		dataType : 'JSON',
		success : function(data) {
			chatRoomList = data.rows;
		},
		error : function(data) {
			return ;
		}
	});
	return  chatRoomList;
}


/**
 * 
 * 根据互动室viewId发送消息
 * 
 * @param chatroomIDs
 *            互动室ID
 * @param msg
 *            消息内容
 * @param bandViewIDs
 *            进入的帮区
 * @param bandOperateTips
 *            帮区字面提示
 * @param toolViewIDs
 *            运行工具的id
 * @param toolBandViewIDs
 *            工具安装的帮区
 * @param toolOperateTips
 *            工具字面提示
 */
function sendMessByChatroomViewId(chatroomIDs, msg, bandViewIDs,
		bandOperateTips, toolViewIDs, toolBandViewIDs, toolOperateTips, success,
		error) {
	var toolID ='';   //互动室消息工具的工具ID
	getToolId('ChatroomTool',function(data){
		toolID = data.ChatroomToolCallID;
	},function(data){
		alter('获取消息工具ID失败！');
		return;
	});
	var param = {
			'action':'createChatroomMessageForAllTools',
			'senderID': userID,  //发送消息的用户ID
//			'chatroomIDs' : chatroomIDs,
//			'content' : msg,
//			'bandViewIDs' : bandViewIDs,       //进入的帮区
//			'bandOperateTips': bandOperateTips,       //帮区字面提示
//			'toolViewIDs' : toolViewIDs,        //运行工具的id
//			'toolBandViewIDs' : toolBandViewIDs,   //工具安装的帮区
//			'toolOperateTips' : toolOperateTips,    //工具字面提示
//			'docViewIDs' : docViewIDs,  //文档的ID
//			'toolRunParams':"", //工具运行的参数信息，如果有多个，以逗号隔开
//			'docOperateTips':"" //文档的操作信息，如果有多个，以逗号隔开
	};
	
	if (chatroomIDs != '') {
		param.chatroomIDs = chatroomIDs;
	}
	if (msg != '') {
		param.content = msg;
	}
	if (bandViewIDs != '') {
		param.bandViewIDs = bandViewIDs;
	}
	if (bandOperateTips != '') {
		param.bandOperateTips = bandOperateTips;
	}

	if (toolViewIDs != '') {
		param.toolViewIDs = toolViewIDs;  //toolId为全局变量
		
		if (toolBandViewIDs != '') {
			param.toolBandViewIDs = toolBandViewIDs;
		}

		if (toolOperateTips != '') {
			param.toolOperateTips = toolOperateTips;
		}
	}
	
	var url = encodeURI(WEB_URL + 'runSystemTool?toolID='+toolID+'&access_token='+accessToken);
	$.ajax({
		type : 'post',
		url : url,
		dataType : 'json',
		data:{
			param:JSON.stringify(param)
		},
		success : function(data) {
			if (data.result == 'false') {
				error();
				// alert("消息:[" + msg + "]发送失败！");
				// jAlert("消息:[" + msg + "]发送失败！");
			} else {
				// alert('消息发送成功！');
				// jAlert('消息发送成功！');
				success&&success(data);
			}
		},
		error : function(data) {
			// alert("消息:[" + msg + "]发送失败");
			// jAlert("消息:[" + msg + "]发送失败");
			error&&error(data);
		}
	});
}

//根据工具类型获取工具ID
function getToolId(toolType,success,error){
	var url = WEB_URL + 'json/'+toolType+'.json';
	$.ajax({
		async:false,
		type:'get',
		url:url,
		dataType:'json',
		success:function(data){
			success && success(data);
		},
		error:function(data){
			error && error(data);
		}
	});
}