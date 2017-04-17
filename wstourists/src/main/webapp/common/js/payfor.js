var index=0;
			var now=0;
			$("#listTitle").on("click","em",function(){	
				var $obj=$(this).parent();	
				var iNow=$obj.attr("index");
				$obj.nextAll().remove();
				$obj.remove();
				
				if(iNow==1){
					$("#step2").hide();
					$("#step1").show();	
					index=0;
					now=iNow;
					$("#step2").children().addClass("none");
					$("#chooseBrand").removeClass("none");
					$("#step2").children().eq(now).removeClass("none");
					$(".brand_step").addClass("none");
					$(".brand_step:eq("+index+")").removeClass("none");					
					now=0;
				}else{
					now=iNow-1;
					$("#step2").children().addClass("none");
					$("#chooseBrand").removeClass("none");
					$("#step2").children().eq(now).removeClass("none");
					index=$("#step2").children().eq(now).attr("index");
					$(".brand_step").addClass("none");
					$(".brand_step:eq("+index+")").removeClass("none");								
				}
			});
        	$("#carBrands").on("click","a",function(){
				now++;
				index++;
				$(".brand_step").addClass("none");
				$(".brand_step:eq("+index+")").removeClass("none");
				$("#step1").hide();
				$("#step2").show();
				var value=$(this).find("span").html();
				$("#listTitle").append('<a href="javascript:void(0)" index='+now+'><span class="f_l" id="2">'+value+'</span><em></em></a>');		
			});
			chooseType("#carSeries","#carModels","#carSeries",false);
			chooseType("#carModels","#carStep","#carModels",false);
			chooseType("#carStep","#carGears","#carStep",true);
			chooseType("#carGears","#carDisplacement","#carGears",false);
			chooseType("#carDisplacement","#carSetting","#carDisplacement",true);
			chooseType("#carSetting","#carYears","#carSetting",false);
			chooseType("#carYears","#success","#carYears",true);
			
			
			
			function chooseType(obj,showOne,hideOne,doIndex){
				$(obj).on("click","a",function(){
					now++;
					if(doIndex){
						index++;
						$(".brand_step").addClass("none");
						$(".brand_step:eq("+index+")").removeClass("none");									
					}					
					$(hideOne).addClass("none");
					$(showOne).removeClass("none");
					var value=$(this).html();
					$("#listTitle").append('<a href="javascript:void(0)" index='+now+'><span class="f_l" id="2">'+value+'</span><em></em></a>');
					console.log(index)
					if(index==4){
						var str="";
						$("#listTitle a").each(function(index, element) {
							var value=$(this).find("span").html();
							str=str+value+" ";
						});
						$("#carType").html(str);
						$("#selectCar ,#selectCar1").attr("value",str);
						setTimeout(function(){
							$("#carDiv").hide();
							$(".carDiv_bj").hide();
							$("#step2").hide();
							$("#step1").show();														
							$("#step2").children().addClass("none");
							$("#chooseBrand").removeClass("none");
							$("#step2").children().eq(0).removeClass("none");
							$("#step2").children().eq(1).removeClass("none");							
							$(".brand_step").addClass("none");
							$(".brand_step:eq(0)").removeClass("none");	
						 	index=0;
						 	now=0;	
							$("#listTitle").html('<span class="f_l">已选车型：</span>');	
							$("#success").addClass("none");																									
						},1000)
					}
				});									
			}
				
			$("#selectCar").click(function(){
				$("#carDiv").show();
				$(".carDiv_bj").show();
			})			
			$("#close").click(function(){
				$("#carDiv").hide();
				$(".carDiv_bj").hide();
				
					
			})		