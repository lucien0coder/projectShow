var j = 1; 
var timeType = 'today';

$(function(){
	
	init();

	$('#loading').css('display','none');	
	
	getTaskList(timeType,'','','');
	
	$("#search").click(function(){
		
		var selectValue = $("#typeCode option:selected").val();
		//此处得到的应该是#typeCode的值
		var typeCodeValue = $("#typeCodeValue").val();
		//获取typeCode与对应的TypeCode的值，放入getTask中即可
		
		var typeDom = $('.tag.is-danger');
		var typeDomText = typeDom.text();
		typeDomText == '当天' ?timeTpye = 'today' : timeType = 'week';
		
		switch(selectValue){
			case "userName": getTaskList(timeType,'',typeCodeValue,'');break;
			case "pName": getTaskList(timeType,typeCodeValue,'','');break;
			case "tName": getTaskList(timeType,'','',typeCodeValue);break;
		}
		
		$("#typeCodeValue").val("");
		
		//需要再次给三个小图标添加绑定事件
		activateIcom();
		 
	});
	
	//点击发送消息
	$("#sendMsg_btn").click(function(){
		var chatRoomID = $("#sendToChat input:radio:checked").val();
		if(chatRoomID == null){
			alert("请选择一个互动式接收消息!");
			return ;
		}
		var textMessage = $("#theSendMessage").val();
		sendMessByChatroomViewId(chatRoomID,textMessage,'','','','','','','');
		$("#theSendMessage").val("");
		$('#select_chatroom_modal').modal('hide');
	});
	
	activateIcom();
	
});

function activateIcom(){
	//进入到该任务的帮区中
	$(".fa.fa-envelope").each(function(){
		$(this).on('click',function(){
			var encodeBViewId = getAndChangeToMyBandId(this); 
			if(encodeBViewId == 0){
				alert("您没有该任务帮区的权限！sorry！");
				return ;
			}
			window.open(WEB_URL+'band/gotoBandDecorate?' + 'bandID=' + encodeBViewId);
		});	
	});	
	
	//调用项目管理工具，管理该任务所属的项目
	//此功能暂时没有完成，因为需要本工具安装在一个帮区中，需要工具的帮区ID
	$(".fa.fa-check-square").each(function(){
		this.onclick = function(){
			var kPITool = '1632195';
			var url = 'httpCallTool?bvId='+bvid+'&toolID='+kPITool+'&userAccount='+userAccount+'&param=null';
			window.open(url);
		};
	});
	
	
	//进入到任务所在项目帮区中的互动式发送消息
	$(".fa.fa-forward").each(function(){
		this.onclick = function(){
			 var encodeBViewId = getAndChangeToMyBandId(this); 
			if(encodeBViewId == 0){
				alert("您没有该任务帮区的权限！sorry！");
				return ;
			}
			//获取到的帮区下的互动式的列表
			var chatRoomList = getChatRoomByBand(encodeBViewId);
			if(chatRoomList == null){
				alert("您不在帮区的互动室中！");
				return ;
			}
			var dom = viewChatroomCheckbox(chatRoomList);
			var noticDOM = $('#message_eara');
			noticDOM.empty().append(dom);
			$('#select_chatroom_modal').modal('show');
		};
	});
	
}

function getAndChangeToMyBandId(obj){
	
	var nodeValue = $(obj).children(":first").val();
	var startIndex = nodeValue.indexOf("(");
	var endIndex = nodeValue.indexOf(")");
	var bViewId = nodeValue.substring(startIndex+1,endIndex);
	var encodeBViewId = getViewIDByObjId(bViewId);
	return encodeBViewId;
}


$('#swiftType').click(function(){
	var a = '';
	timeType == 'today'? a = 'week':a = 'today';
	timeType = a;
	getTaskList(a,'','','')
	activateIcom();
});

function init(){
	getUserNameByUserAccount(userAccount);
}

/** 渲染项目面板
 * function 获取项目列表
 * 
 * */
function panelProjectModel(buildP,getCfg){
	//1 获取用户配置信息、获取项目数据
	//2 将数据进行显示
	getCfg(buildP);
}

function _goto(select){
	
	var index = select.selectedIndex;
	var option = select.options[index];
	var value = option.value;
	//此时的value是pName
	//然后发送ajax请求，此时的timeType是一定的，发送请求，然后回调
//	alert(value);
	
	var typeDom = $('.tag.is-danger');
	var typeDomText = typeDom.text();
	typeDomText == '当天' ?timeTpye = 'today' : timeType = 'week';
	
//	$("#projectCode").find("option:selected").remove();
	
	if(value == ''){
		$("#typeCode").append("<option value='pName'>项目</option>");
	}else{
		$("#typeCode option[value='pName']").remove();						
	}
	getTaskList(timeType,value,'','');
	activateIcom();
}

/** 组装列表*/
function buildProjectPanel(pl,ci){
	var type = ci.type;
	var monitor = ci.moniter;
	var extend = new Array();
	var quotaString = ci.typeExtend;//指标

	//项目分屏数
	if(1 == monitor){
		extend = quotaString;
	}else {
		var quota = quotaString.split(';');
		if(monitor == quota.length){
			extend = quota;
		}
	}
	divideData(type,extend,pl);
}

/** 根据条件，将data分组*/
var presents = new Array();
function divideData(type,quota,datas){
	//项目分类类型
	switch(type){
	case '0' ://项目进度
		
		$.each(quota,function(index,quo){
			window[quo] = new Array();
			var present = parseInt(quo);
			presents.push(present);
		});
		presents = sorting.shellSort(presents);//小到大排序。
		$.each(datas,function(index,data){//将每个项目分组
			var eachProgress = data.pProgress * 100;
			for(var i=0;i < presents.length;i++){
				if(eachProgress <= presents[i]){
					var preStr = presents[i]+'';
					window[preStr].push(data);
					break;
				}
			}
		});
		viewProjectPanelByPress(presents,1);
		break;
	case '1' ://项目剩时
		$.each(datas,function(index,data){
			data.pExpectFinishTime;
		});
		break;
	case '2'://项目负责人
		$.each(datas,function(index,data){
			data.pLeaderAccount;
		});
		break;
	}
	
}

/** sort util*/
var sorting = {
	    //利用sort方法进行排序
	    systemSort: function(arr){
	        return arr.sort(function(a,b){
	            return a-b;
	        });
	    },
	 
	    //冒泡排序
	    bubbleSort: function(arr){
	        var len=arr.length, tmp;
	        for(var i=0;i<len-1;i++){
	            for(var j=0;j<len-1-i;j++){
	                if(arr[j]>arr[j+1]){
	                    tmp = arr[j];
	                    arr[j] = arr[j+1];
	                    arr[j+1] = tmp;
	                }
	            }
	        }
	        return arr;
	    },
	 
	    //快速排序
	    quickSort: function(arr){
	        var low=0, high=arr.length-1;
	        sort(low,high);
	        function sort(low, high){
	            if(low<high){
	                var mid = (function(low, high){
	                    var tmp = arr[low];
	                    while(low<high){
	                        while(low<high&&arr[high]>=tmp){
	                            high--;
	                        }
	                        arr[low] = arr[high];
	                        while(low<high&&arr[low]<=tmp){
	                            low++;
	                        }
	                        arr[high] = arr[low];
	                    }
	                    arr[low] = tmp;
	                    return low;
	                })(low, high);
	                sort(low, mid-1);
	                sort(mid+1,high);
	            }
	        }
	        return arr;
	    },
	 
	    //插入排序
	    insertSort: function(arr){
	        var len = arr.length;
	        for(var i=1;i<len;i++){
	            var tmp = arr[i];
	            for(var j=i-1;j>=0;j--){
	                if(tmp<arr[j]){
	                    arr[j+1] = arr[j];
	                }else{
	                    arr[j+1] = tmp;
	                    break;
	                }
	            }
	        }
	        return arr;
	    },
	 
	    //希尔排序
	    shellSort: function(arr){
	        var h = 1;
	        while(h<=arr.length/3){
	            h = h*3+1;  //O(n^(3/2))by Knuth,1973
	        }
	        for( ;h>=1;h=Math.floor(h/3)){
	            for(var k=0;k<h;k++){
	                for(var i=h+k;i<arr.length;i+=h){
	                    for(var j=i;j>=h&&arr[j]<arr[j-h];j-=h){
	                        var tmp = arr[j];
	                        arr[j] = arr[j-h];
	                        arr[j-h] = tmp;
	                    }
	                }
	            }
	        }
	        return arr;
	    }
};

/** 时间对象，转时间字符串*/
function formatDate(date, format){
	var dateStr = '';
	var nowDate = new Date();
	if(date != '' && date != undefined){
		if(format == 'yyyy-MM-dd'){
			if(typeof(date) == 'string')	dateStr = date.substr(0,10); //2015-05-27
			else dateStr = parseInt(date.year+1900)+'-'+parseInt(date.month+1)+'-'+date.date;
		}
		if(format == 'yyyy-MM-dd hh'){
			if(date.indexOf('T') != -1){ dateStr = date.replace(/T/," ").substr(0,13)+'时';//2015-05-27T15:30 ---> 2015-05-27 15时
			}else{
				if(typeof(date) == 'string')	dateStr = date.substr(0,13)+'时'; //2015-05-27 15
				else dateStr = parseInt(date.year+1900)+'-'+parseInt(date.month+1)+'-'+date.date+' '+date.hours+'时';
			}
		}
		if(format == 'yyyy-MM-dd hh:mm:ss'){
			if(typeof(date) == 'string')	dateStr = date; //2015-05-27 15:55:24
			else dateStr = parseInt(date.year+1900)+'-'+parseInt(date.month+1)+'-'+date.date+' '+date.hours+':'+date.minutes+':'+date.seconds;
		}
		if(format == 'yyyy-MM-dd-week hh:mm:ss'){
			if(typeof(date) == 'string')	dateStr = date; //2015-05-27 15:55:24
			else {
				var day = (date.day==0 && '(周日)') || (date.day==1 && '(周一)') || (date.day==2 && '(周二)') || (date.day==3 && '(周三)') || (date.day==4 && '(周四)') || (date.day==5 && '(周五)') || (date.day==6 && '(周六)')
				dateStr = parseInt(date.year+1900)+'-'+parseInt(date.month+1)+'-'+date.date+ day +' '+date.hours+':'+date.minutes+':'+date.seconds;
			}
		}
		if(format == 'yyyy-MM-dd hh:mm'){
			if(typeof(date) == 'string')	dateStr = date.substr(0,16); //2015-05-27 15:55
			else dateStr = parseInt(date.year+1900)+'-'+parseInt(date.month+1)+'-'+date.date+' '+date.hours+':'+date.minutes;
		}
		if(format == 'yyyy-MM-ddThh:mm:ss'){
			if(typeof(date == 'string')) dateStr = date.replace(' ','T');
			else;
		}
		if(format == 'remDate'){
			var mydate;
			if(typeof(date) == 'string'){ //字符串 转 时间格式
				var strDate = new Date(date.replace(/-/g,'/'));
				mydate = strDate.getTime();
			}else{
				mydate = date.time;
			}
			dateStr = parseInt((mydate - nowDate.getTime())/3600/24/1000);
			if(dateStr < 0){//剩余时间，去除负数
				dateStr = 0;
			}
		}
		if(format == 'spaDate'){
			dateStr = parseInt((nowDate.getTime() - date.time)/3600/24/1000);
		}
	}
	return dateStr;
}