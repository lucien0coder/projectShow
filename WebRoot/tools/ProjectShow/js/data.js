var taskList ='';

var projectList = '';
var configInfo = '';

var first_panel_data = '';
var second_panel_data = '';
var third_panel_data = '';
var four_panel_data = '';

/** 获取项目列表*/
function getProjectList(){
	$.ajax({
		url:fyToolUrl,
		type:'GET',
		data:{
			toolAction :'all'
		},
		dataType:'json',
		success:function(data){
			if(0 != data.total){
				projectList = data.projectList;
			}else{
				alert('目前没有项目');
			}
		},
		error:function(err){
			console.log(err);
		}
	});
}

/** 获取用户配置信息*/
function getUserConfig(buildPanel){
	$.ajax({
		url:fyToolUrl,
		type:'GET',
		data:{
			toolAction:'configAndProjects'
		},
		dataType:'json',
		success : function(data){
			if(0 != data.total){
				projectList = data.projectList;
				configInfo = data.config;
				buildPanel(projectList,configInfo);
			}else{
				alert('当前没有项目');
			}
		},
		error:function(err){
			console.log(err);
		}
	});
}

/** 获取任务列表   timeType:today/week;other:''(default) */
function getTaskList(timeType,pName,userName,tName){
	
	var targetDOM = $("#cd-timeline");
	var typeDom = $('.tag.is-danger');   
	var typeNote = ''; 
	timeType=='today'? typeNote ='当天':typeNote = '本周';
//	timeType = 'week';
//	console.log(timeType)
	typeDom.empty().append(typeNote);
	
	$('#loading').css('display','block');
	$.ajax({
		url:fyToolUrl,
		type:'POST',
		async:false,
		data:{
			toolAction :'listTasks',
			'timeType' : timeType,
			'pName':pName,
			'userName':userName,
			'tName': tName
		},
		dataType:'json',
		success:function(data){
			var dom = '';
			if(0 != data.total){
				dom = viewTimeLineBlock(data.TaskList);
			}else{
				var thisToday = (timeType=='today' && true) || (timeType=='week' && false);
				var warning = '没有本周需完成的任务';
				if(thisToday){
					warning = '没有今天需完成的任务';
					alert(warning);
					timeType = 'week';
					getTaskList(timeType,pName,userName,tName);
					return ;
				}
				alert(warning);
			}
			targetDOM.empty().append(dom);
			$('#loading').css('display','none');
		},
		error:function(err){
			$('#loading').css('display','none');
			console.log(err);
		}
	});
}
