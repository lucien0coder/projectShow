/** 将项目列表数据显示到面板上*/
function viewProjectPanelByPress(quto,page){
	console.log('page:'+page);
	for(var i=0; i <quto.length;i++){
		var panel_title = '';
		if(i == 0){//
			panel_title = '进度小于'+quto[i]+'%的项目';
		}else if(100 == quto[i]){//要显示已完成
			panel_title = '已完成的项目';
		}else{
			var before = quto[i-1];
			var thisPress = quto[i];
			panel_title =  '进度在'+before+'%-'+thisPress+'%之间的项目';
		}
		switch(quto.length){
		case 1:
			break;
		case 2:
			break;
		case 4:
			viewBuildFourPanel(i,panel_title,window[quto[i]],page);
			break;
		}
	}
}

/** 组件某个面板显示*/
function viewBuildFourPanel(idx,title,data,page){
	var thisTitle = title + '<span class="badge">'+data.length+'</span>';
	//黄色小  蓝色中下  绿色中上  红色满
	var thisClass = 'warning';
	switch(idx){
	case 0:
		break;
	case 1:
		thisClass = 'info';
		break;
	case 2:
		thisClass = 'success';
		break;
	case 3:
		thisClass = 'danger';
		break;
	}
	$('#'+idx+'_title').empty().html(thisTitle);
	var items = '';
	var thisDatas = new Array(); 
	//1 0-2  2 3-5  3 6-8          (page*3-3)>=0  (page*3-1)<=size
	var y = data.length % 3;
	var max_page = '';
	if(y == 0){//整除
		max_page = parseInt(data.length /3);
	}else{
		max_page = parseInt(data.length/3+1);
	}
	if(data.length >= 3){//是否需要翻页？小于3则不需
		if(page > max_page-1){//page 过大，需要重置
			j = 0;
		}
		if(page*3>=3){//条件1 第一页  
			if(page*3<=data.length+1){//条件2  最后一页，否则会越界
				var _do = page*3-3;
				thisDatas.push(data[_do]);
				var _dtw = page*3-2;
				thisDatas.push(data[_dtw]);
				var _dtr = page*3-1;
				thisDatas.push(data[_dtr]);
			}else{
				var i = 2;
				while(page*3 == data.length+i){
					var _do = page*3-3;
					thisDatas.push(data[_do]);
					i++;
				}
			}
		}
	}else{
		thisDatas = data;
	}
	$.each(thisDatas,function(index,thisItem){
		var bears = thisItem.pOwnerAccount;//参与人数
		var bearsNum = '0';
		if(undefined != bears && '' != bears){
			bearStr = bears.split(',');
			bearsNum = bearStr.length;
		}
		var expTime = formatDate(thisItem.pExpectFinishTime,'yyyy-MM-dd');//期限
		if(undefined == expTime || '' == expTime){
			expTime = '0';
		}
		var remainDay = formatDate(expTime,'remDate');//剩时
		var thisRateW = Math.round(thisItem.pProgress * 100) +'%';
		var thisRate = thisRateW;
		if(1 == thisItem.pProgress){
			thisRateW = 100+'%';
			thisRate = '完成';
		}else if(0.1 > thisItem.pProgress){
			thisRate = '0'+thisRateW;
		}
		items += '<li class="list-group-item">'+
		'<label>项目名称：</label><span>'+thisItem.pName+'</span><br>'+
		'<span class="pro_detail">'+
//		'<label>负责人：</label>'+thisItem.employee_name+'&nbsp;&nbsp;&nbsp;'+
		'<label>参与人数：</label>'+bearsNum+'&nbsp;&nbsp;&nbsp;'+
		'<label>剩时：</label>'+remainDay+'天&nbsp;&nbsp;&nbsp;'+
		'<label>期限：</label>'+expTime+'&nbsp;&nbsp;&nbsp; '+
		'<div class="progress ring '+thisClass+' progress-striped active">'+
		'<div class="progress-bar progress-bar-'+thisClass+'" role="progressbar" aria-valuenow="35" aria-valuemin="0" '+
		'aria-valuemax="100" style="width: '+thisRateW+';"></div>'+
		'<span class="rate">'+thisRate+'</span>'+
		'</div>'+
		'</span>'+
		'</li>';
	});
	var tPanelDOM = $('#'+idx+'_panel_group');
	tPanelDOM.empty().html(items);
}

/**
 * 渲染任务块  show.jsp
 * @param tl
 * @returns
 */
function viewTimeLineBlock(tl){
	var dom = '';
	
	$.each(tl,function(index,t){
		var band = t.bandInfo;//任务区
		var username = t.userName;//任务负责人
		var endate = formatDate(t.tExpectFinishTime,'yyyy-MM-dd-week hh:mm:ss');//任务需完成时间
		var urgDom = '';
		var checkDom = '';
		if(t.tDevelopStatus == 'closed' || t.tDevelopStatus == 'completed') {
			endate = '<del>' + endate + '</del>'
			if(t.tDevelopStatus == 'completed')
				checkDom = '<a class="level-item"><span class="icon is-small"><i class="fa fa-check-square"></i></span></a>';
		}else{
			urgDom = '<a class="level-item"><span class="icon is-small"><i class="fa fa-forward"><input type="hidden" value="'+band+'"/></i></span></a>';
		}
		var pid = t.pId;//项目id
		var pName = t.pName;//项目名称
		var tid = t.tId;//任务id
		var tName = t.tName;//任务名称
		var tDesc = t.tDesc;//任务描述
		var tLevel = (t.tLevel=='h' && 'danger_color') || (t.tLevel=='e' && 'success_color') || (t.tLevel=='n' && 'primary_color') || (t.tLevel=='c' && 'warning_color');//任务等级
		dom += '<div class="cd-timeline-block">'+
		'<div class="cd-timeline-img cd-picture '+tLevel+'"></div>' +
		'<div class="cd-timeline-content">' + 
		'<article class="media">'+
		'<div class="media-content">'+
		'<div class="content">'+
		'<p>'+
		'<strong>'+username+'</strong> <small>'+pName+'</small> <small>'+tName+'</small>'+
		'<br>'+
		'<span>任务描述：</span>'+tDesc+
		'</p>'+
		'</div>'+
		'<nav class="level">'+
		'<div class="level-left">'+
		'<a class="level-item">'+
		'<span class="icon is-small"><i class="fa fa-envelope"><input type="hidden" value="'+ band +'"/></i></span>'+
		'</a>'+ urgDom + checkDom +
		'</div>'+
		'</nav>'+
		'</div>'+
		'</article>'+
		'<span class="cd-date">交付时间：'+endate+'</span>'+
		'</div>'+
		'</div>';

		var pNameArray = [];
		$("#projectCode option").each(function(){
			pNameArray.push($(this).val());
		});
		if(-1 == $.inArray(pName,pNameArray)){
			var pNameViewVlue = changePnameLength(pName);
			$("#projectCode").append("<option value='" + pName + "' title='"+pName+"'>" + pNameViewVlue + "</option>");				
		}
	});	
	return dom;
}

function changePnameLength(pName){
	
	var length = pName.length;
	if(length > 10){
		pName = pName.substring(0,10) + "...";
	}
	return pName;
}

/** 组建互动室的多选选项*/
function viewChatroomCheckbox(chatLs){
	var dom ='<div>消息发送到哪个互动室？</div>'+
			'<div class="btn-group" data-toggle="buttons" id="sendToChat">';
	$.each(chatLs,function(index,chat){
		var chatroomName = chat.name;				//后
		var chatroomViewID = chat.chatroomID;		//后
		dom += '<div><label class="btn btn-default">'+
		'<input type="radio" name="optionsRadios" value="'+chatroomViewID+'" autocomplete="off">'+ chatroomName+''+
		'</label></div>';
	});
	dom += '</div>';
	return dom;
}
