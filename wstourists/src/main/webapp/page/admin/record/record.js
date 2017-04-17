$(document).ready(function() {
	//alert("ss");
	loadDataGrid();
});

function loadDataGrid() {
	var path=$("#path").val();
	var data = {
		
	};
	//alert(path+"/admin/queryUsers");
	$.post(path+"/admin/withDrawBack/gethRecord", data, function(data) {
		$('#tabaleDatas').html(data);
	});
};
//分页
function goPage(x) {
	var path=$("#path").val();
	// $("#curPage").val(x);
	var recordClss=$("#recordClss").val();
	var recordState=$("#recordState").val();
	var phone=$("#phone").val();
	var startTime=$("#startTime").val();
	var endTime=$("#endTime").val();
	// $("#curPage").val(x);
	var data = {
		curPage : x,
		recordClss : recordClss,
		recordState : recordState,
		recordState : recordState,
		phone : phone,
		startTime : startTime,
		endTime : endTime
	};
	$.post(path+"/admin/withDrawBack/gethRecord", data, function(data) {
		$('#tabaleDatas').html(data);
	});
};

function searchform(){
	var path=$("#path").val();
	var recordClss=$("#recordClss").val();
	var recordState=$("#recordState").val();
	var phone=$("#phone").val();
	var startTime=$("#startTime").val();
	var endTime=$("#endTime").val();
	// $("#curPage").val(x);
	var data = {
		curPage : 1,
		recordClss : recordClss,
		recordState : recordState,
		recordState : recordState,
		phone : phone,
		startTime : startTime,
		endTime : endTime
	};
	$.post(path+"/admin/withDrawBack/gethRecord", data, function(data) {
		$('#tabaleDatas').html(data);
	});
}

function changeClss(recordId){
	
	var path=$("#path").val();
	var recordClss=$("#recordClss").val();
	var recordState=$("#recordState").val();
	var phone=$("#phone").val();
	var startTime=$("#startTime").val();
	var endTime=$("#endTime").val();
	var curPage=$("#curPage").val();
	// $("#curPage").val(x);
	var data = {
		curPage : curPage,
		recordId : recordId,
		recordClss : recordClss,
		recordState : recordState,
		recordState : recordState,
		phone : phone,
		startTime : startTime,
		endTime : endTime
	};
	$.post(path+"/admin/withDrawBack/updateRecord", data, function(data) {
		$('#tabaleDatas').html(data);
	});
}





