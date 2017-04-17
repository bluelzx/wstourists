$(document).ready(function() {
	//alert("ss");
	loadDataGrid();
});

function loadDataGrid() {
	var path=$("#path").val();
	var data = {
		
	};
	//alert(path+"/admin/queryUsers");
	$.post(path+"/admin/userBack/getUser", data, function(data) {
		$('#tabaleDatas').html(data);
	});
};
//分页
function goPage(x) {
	
	var path=$("#path").val();
	var vipLevel=$("#vipLevel").val();
	var phone=$("#phone").val();
	var startTime=$("#startTime").val();
	var endTime=$("#endTime").val();
	// $("#curPage").val(x);
	var data = {
		curPage : x,
		vipLevel : vipLevel,
		phone : phone,
		startTime : startTime,
		endTime : endTime
	};
	$.post(path+"/admin/userBack/getUser", data, function(data) {
		$('#tabaleDatas').html(data);
	});
};

function searchform(){
	var path=$("#path").val();
	var vipLevel=$("#vipLevel").val();
	var phone=$("#phone").val();
	var startTime=$("#startTime").val();
	var endTime=$("#endTime").val();
	// $("#curPage").val(x);
	var data = {
		curPage : 1,
		vipLevel : vipLevel,
		phone : phone,
		startTime : startTime,
		endTime : endTime
	};
	$.post(path+"/admin/userBack/getUser", data, function(data) {
		$('#tabaleDatas').html(data);
	});
}




