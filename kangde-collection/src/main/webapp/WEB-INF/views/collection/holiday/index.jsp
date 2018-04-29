<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/meta.jsp"%>

<script type="text/javascript">
$().ready(function(){
	load();
})	
		
function load(){
	$.ajax({
		type : "GET",
		url : "${ctx}/collection/holiday/holidays",
		dataType : "json",
		success : function(data) {
			render(data)
		}
	});
	initdelOne();
	initsave();
	initclear();
}

function initdelOne(){
	$("ol").on("click",'span', function(){
		if (confirm("真的要删除吗？")) {
				$.ajax({
					type : "GET",
					url : "${ctx}/collection/holiday/delDay?id="+$(this).attr('id'),
					dataType : "json",
					success : function(data) {
						render(data);
					}
				});
			} 
	});

}

function initclear(){

	$('#clearbutton').click(function(){
		
		if (confirm("真的要清空所有节假日吗？")) {
			$.ajax({
				type : "GET",
				url : "${ctx}/collection/holiday/clearholiday",
				dataType : "json",
				success : function(data) {
					render(data);
				}
			});
		} 
	})
}
function initsave(){

	$('#savebutton').click(function(){
		var holidays= $('#holidaytext').val();
		if(holidays.trim() == ''){
			alert('内容为空');
			return
		}
		var b=$(this);
		b.attr('disabled','disabled')
		$.ajax({
			type : "POST",
			url : "${ctx}/collection/holiday/saveholidays",
			data:{'content':holidays},
			dataType : "json",
			success : function(data) {
				render(data);
				b.removeAttr('disabled')

			}
		});
	})
}



function render(data){
	if(data.state==1){
		$('#holidays').html("");
		var datas=data.data
		$.each(datas,function(index, value, array) {
				$('#holidays').append('<li>'+ value.holiday+ '<span title="点击删除" style="color:red;margin-left:20px;cursor:pointer" id="'+value.id+'">X</span>'+ '</li>');
			})
		$("#holidays li:even").addClass("libg");  
	}else{
		alert(data.msg);
	}
}

</script>
<style>

li,.header{
         font-size:18px;
         margin:10px;
   }
 
   
.libg{background-color: #F2F2F2;}  
</style>
<body>

<div style="margin:10px;">
<button id='clearbutton' type="button">删除所有节假日</button>
</div>
<div>


<div>	
	<div style='height:535px;width:300px;float:left;overflow-y: scroll;border:2px solid;'>
	<div class='header'>节假日列表:</div>
		<ol id='holidays'>
		</ol>
	</div>
</div>

<div style='height:500px;width:300px;float:left;margin-left:50px;'>
	<textarea id='holidaytext' name="holidays" cols=40 rows=30 placeholder='请输入日期列表:每个日期一行,日期格式为yyyy-mm-dd, 如2017-01-01'></textarea>
	<button id='savebutton' type="button">提交</button>
</div>


</div>

</body>



