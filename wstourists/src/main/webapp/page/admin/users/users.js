$(document).ready(function() {
	//alert("ss");
	loadDataGrid();
});

function loadDataGrid() {
	var path=$("#path").val();
	var data = {
		
	};
	//alert(path+"/admin/queryUsers");
	$.post(path+"/admin/queryUsers", data, function(data) {
		$('#tabaleDatas').html(data);
	});
};
//分页
function goPage(x) {
	var path=$("#path").val();
	// $("#curPage").val(x);
	var data = {
		curPage : x
	};
	$.post(path+"/admin/queryUsers", data, function(data) {
		$('#tabaleDatas').html(data);
	});
};









