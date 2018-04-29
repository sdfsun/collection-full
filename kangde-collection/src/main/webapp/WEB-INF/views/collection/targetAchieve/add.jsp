<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/meta.jsp"%>

<style>

body{font-family:"Microsoft YaHei";font-size:12px;}
.clear{ clear:both} 
*{margin:0px;padding:0px;}
.contents{background:#ffffff;width:100%;height:100%;/*border:1px solid red;*/margin-top:22px;}
ul{list-style: none;}
.headerImg ul li{font-size:16px;position:relative;float:left;width:17.2%;border:1px solid #dedede;background:#f6f6f6;margin:0px 2% 0px 0px;height:76px;border-radius:6px;}
.headerImg ul li:hover{background:#ffffff;cursor: pointer;}
.headerImg ul li.fristChild{margin-left:30px;}
.clear{clear:both;}
.headerImg ul li img{vertical-align: middle;position:absolute;top:17px;left:12px;}
.headerImg ul li p{margin-left:68px;}
.headerImg ul li .p1{margin-top:20px;}
.headerImg ul li .p2{color:#37b9f4;}
.conTab{margin-left:30px;margin-top:22px;}
/*.conTab ul{float:left;}*/
/*.conTab .leftTab{margin-right:2%;width:53.8%;float:left;}*/
.conTab .borders{margin-right:2%;width:60%;float:left;border-top-left-radius: 8px;border-top-right-radius: 8px;border:1px solid #c5dde4;} 
.conTab .borders p.p{border-bottom:1px solid #c5dde4;background:#eff7fa;line-height:30px;height:30px;padding-left:6px;/*margin-bottom:10px;*/}
/*.conTab .leftTab{border-top-left-radius: 8px;border-top-right-radius: 8px;border:1px solid #c5dde4;} */
.conTab .rightTab{border-top-left-radius: 8px;border-top-right-radius: 8px;border:1px solid #c5dde4;} 
.conTab .leftTab p.p{color:#37b9f4;cursor: pointer;/*border-radius:4px;*/}
.conTab .rightTab p.p{color:#f16100;/*border-radius:4px;*/}
.conTab .rightTab{width:35%;overflow:hidden;}
.conTab .leftTab div{float:left;/*border-right:1px solid #000;*/}
/*.conTab .leftTab div{border:1px solid red;width:48%;}*/
/*.conTab .leftTab div:nth-child(1){border-right:1px solid blue;margin-right:1%;}*/
.conTab .leftTab div:nth-child(2){/*border-left:1px solid blue;margin-left:2%;*/float:right;}
/*.conTab .leftTab  ul li div:first-of-type{width:50%;border:1px solid red;}*/
.conTab .leftTab li .date{float:right;margin-right:8px;}
/*.conTab .leftTab li span.b{font-weight: bold;}*/
.conTab .leftTab li span .Fnum{font-weight: bold;}
.conTab .leftTab li .date .greys{color:#c7c7c7;}
/*.conTab .data{color:red}*/
/*.conTab .leftTab li .date{color:#000000;font-weight:bold;}*/
.conTab .Sec{margin-top:20px;margin-bottom:20px;}
.conTab .Fir p.p{color:#38b7f3;}
.conTab .Sec p.p{color:#910b0b;}
.conTab .Sec .SecUl div:hover{color:#910b0b;cursor: pointer;}
/*.conTab .leftTab li{border-bottom:1px solid #dedede;margin-left:6px;margin-right:6px;}*/
/*.conTab .leftTab li{width:49%;float:left;margin-left:6px;border-bottom:1px solid #dedede;}*/
.conTab .FirUl li:nth-child(odd){width:48.5%;float:left;border-bottom:1px solid #dedede;margin-left:6px;}
/*.conTab .leftTab li:nth-child(odd):last-child{border-bottom: none;}*/
.conTab .FirUl li:nth-child(even){width:48.5%;float:left;border-bottom:1px solid #dedede;padding-left:1%;}
.conTab .SecUl li{border-bottom:1px solid #dedede;margin-left:6px;margin-right:6px;}
/*.conTab .leftTab li:nth-child(2n+1){border-right:1px solid #dedede;line-height: 30px;height:30px;}*/
/*.odds{display: block;color:#e8e8e8;font-size: 18px;}*/
/*.evens{display:none;}*/
/*.conTab .leftTab li:nth-child(odd) span:nth-child(1){display: none;color:#e8e8e8;font-size: 18px;}*/
.conTab .leftTab li:nth-child(odd) .sps span:nth-child(1){display:none;}
.conTab .leftTab li .sps span:nth-child(1){color:#dedede;}
/*.conTab .leftTab li:nth-child(odd) .sps span:nth-child(2){padding-left:6px;}*/
.conTab .leftTab li:nth-child(even) span:nth-child(1){padding-right:16px;}
/*.conTab .leftTab li:nth-child(even) .img:nth-child(1){display: block;padding-left:1%;display:inline;padding-right:6px;/*color:#e8e8e8;font-size: 18px;}
/*.conTab .leftTab li:nth-child(even) .img{padding-top:7px;padding-bottom:7px;display:inline;}*/
/*.conTab .leftTab li:nth-child(even) .img:nth-child(0){padding-top:7px;padding-bottom:7px;display:inline;}*/
/*.conTab .leftTab li:nth-child(even)+div{display:block;padding-top:7px;padding-bottom:7px;padding-right:8px;}*/
/*.conTab .leftTab li:last-of-type{border-bottom:none;} */
.conTab .SecUl li .sps{display:inline-block;text-overflow:ellipsis; white-space:nowrap; overflow:hidden;width:500px;}
.conTab .FirUl li .sps{display:inline-block;text-overflow:ellipsis; white-space:nowrap; overflow:hidden;width:265px;}
.conTab .rightTab li .date{float:right;margin-right:6px;}
.conTab .rightTab .Text{width:100%;margin-top:10px;padding-left:10px;display: inline-block;display:none;margin-bottom:30px;clear:both;overflow:hidden;}
.conTab .rightTab .Text b{color:#333333;line-height:30px;height:30px;}
.num,.cons{float:left;line-height:20px;display: inline-block;}
.num{width:3%;color:#999999;}
.cons{width:95%;color:#999999;}
/*.conTab .rightTab .Text p span{color:#999999;float:left;line-height:20px;display: inline-block;}*/
.conTab .rightTab div p{background:none;border:none;padding:0;}
/*.rightTab b{margin-top:30px;}*/
.conTab ul li:last-of-type{border-bottom:1px solid #dedede;} 
.conTab ul p.p{border-bottom:1px solid #c5dde4;background:#eff7fa;line-height:30px;height:30px;padding-left:6px;/*margin-bottom:10px;*/}
/*.conTab ul{border:2px solid #c5dde4;}*/
/*.conTab ul>ul:not(:first-child){border:2px solid #c5dde4;}*/
.conTab .leftTab li{/*border:1px solid #dedede;*/line-height:30px;height:30px;}
.conTab .leftTab{height:316px;overflow-y: auto;}
.conTab .leftTab::-webkit-scrollbar{
    width: 12px;
    height: 16px;
    background-color: #f0f1f2;
}
.conTab .leftTab::-webkit-scrollbar-track{
    -webkit-box-shadow: inset 0 0 6px rgba(183,183,183,.6);
    border-radius: 4px;
    background-color: #f0f1f2;
}
.conTab .leftTab::-webkit-scrollbar-thumb{
    /*width: 10px;*/
    height: 20px;
    border-radius: 4px;
    -webkit-box-shadow: inset 0 0 6px rgba(183,183,183,.6);
    background-color: #e3f4fa;
}
/*.conTab #scroll::-webkit-scrollbar-track{border: 2px solid black;background-color: #969fa4;margin-top:40px;}
.conTab #scroll::-webkit-scrollbar-thumb{background-color: #38b7f3;}
.conTab #scroll::-webkit-scrollbar-thumb{background-color: #38b7f3;}*/
.conTab .rightTab li{padding-top:7px;padding-bottom:7px;margin-left:7px;border-bottom:1px solid #c5dde4;margin-right:7px;}
.conTab .rightTab li:last-of-type{border-bottom:none;}
/*.conTab ul li span{padding-top:15px;padding-bottom:15px;}*/
/*.conTab ul li.lastLi{border-bottom:1px solid #dedede;}*/
.conTab .SecUl li:hover{color:#910b0b;cursor: pointer;}
.conTab .Fir li:hover{color:#37b9f4;cursor: pointer;}
.conTab .rightTab li:hover{color:#f16100;cursor: pointer;}


.isread{
font-weight: bold;
}

</style>
<script type="text/javascript">

$().ready(function(){
	
	$(".FirUl li").click(function(){
		var url=$(this).attr('url')
		var pagetitle=$(this).attr('pagetitle')
		showpage(url, pagetitle);
	});
	
	$(".SecUl li").click(function(){
		viewReminder($(this).attr('id'),$(this).attr('isRead'),$(this).attr('url'),$(this).attr('title'))
		$(this).removeClass('isRead');
	
	});
	
});



function viewReminder(id, isread, url, title) {
	$(this).removeClass('isRead');
	if(isread==1){
		showpage(url, title);
	}else{
		$.post('${pageContext.request.contextPath}/collection/messageReminder/readMessageReminder', {
			id : id
		}, function(data) {
			showpage(url, title);
		}, 'json');	
	}
 }


 function showpage(url, title){
	 if(url){
		 if('案件详情'==title){
				window.open('${pageContext.request.contextPath}'+url);
			}else{
	 			eu.addTab(parent.layout_center_tabs,title,'${pageContext.request.contextPath}'+url,true,'');
			}
	 }
 }
 
 function myfunction(){
	   var DisVal = document.getElementById('disparityVal');
	   var stringDisVal =  DisVal.innerHTML;
	   stringDisVal = Number(stringDisVal.substring(0,1));
	  if(stringDisVal>=0){
	  	DisVal.style.color = "#f16100";  	
	  }else{
	  	DisVal.style.color = "#37b9f4";
	  }
  }

 
 function play(i){
   var div=document.getElementById('div'+i);
   var img=document.getElementById('img'+i);
   if(div.style.display=="block"){
      img.src="${pageContext.request.contextPath}/static/img/close.png";
      div.style.display="none";
    }else{
      div.style.display="block";
      img.src="${pageContext.request.contextPath}/static/img/open.png";
  	}
}
</script>


<body onload="myfunction()">
   <div class="contents">
   	<div class="headerImg">
   	 <ul>
     <li class="fristChild">
     	    <img src="${pageContext.request.contextPath}/static/img/target.png" alt="" >
     	    <p class="p1">目标</p>
     	    <p class="p2" id="targetVal">${CURRENT_USER.achieve}元</p>

     </li>  	
     <li>
     		<img src="${pageContext.request.contextPath}/static/img/achievement.png" alt="">
            <p class="p1">确认业绩</p>
     	    <p class="p2">${CURRENT_USER.paidAchieve}元</p>
     </li>  
     <li>
     		<img src="${pageContext.request.contextPath}/static/img/waitting.png" alt="">
     		<p class="p1">待确认业绩</p>
     	    <p class="p2">${CURRENT_USER.cpAchieve}元</p>
     </li>  
     <li>
     		<img src="${pageContext.request.contextPath}/static/img/disparity.png" alt="">
     		<p class="p1">距离目标</p>
     	    <p class="p2" id="disparityVal">${CURRENT_USER.distance}元</p>
     </li>  
     <li>
     		<img src="${pageContext.request.contextPath}/static/img/Reach.png" alt="">
     		<p class="p1">目标达成率</p>
     	    <p class="p2">${CURRENT_USER.rate}</p>
     </li>   
     <div class="clear"></div>	
   	 </ul>
   	</div>

    <div class="conTab">
       <div class="borders Fir">
            <p class="p">我的待办</p>
    	<ul class="leftTab FirUl">
    	 	<c:forEach var="item" items="${todoList}" varStatus="status">
	    		<li url='${item.url }' pagetitle='${item.pagetitle }'>
	                <span class="sps"> <span>|</span><img src="${pageContext.request.contextPath}/static/img/square.png" alt="">${item.message }</span>
	            	<span class="date b"><b>${item.num }</b><span class="greys">个</span></span> 
	            </li>
    	 	</c:forEach>
          
            <div class="clear"></div>

    	</ul>
    </div>




  <div class="borders Sec">
            <p class="p">消息提醒</p>
        <ul class="leftTab SecUl">
	        <c:forEach var="item" items="${reminderList}" varStatus="status">
	            <li <c:if test="${item.isRead==0}">class='isRead'</c:if>  id='${item.id }' isRead='${item.isRead }' url='${item.url }' title='${item.title }'>
	                <span class="sps"><img src="${pageContext.request.contextPath}/static/img/notice.png" alt="">${item.content }</span>
	            	<span class="date b"><fmt:formatDate value="${item.createTime }"  pattern="yyyy/MM/dd HH:mm:ss"/></span> 
	            </li>
	        </c:forEach>
        </ul>
    </div>




<div class="rightTab">
   <ul>
            <p class="p">公告</p>
             <c:forEach var="item" items="${noticeList}" varStatus="status">
	    		<li onclick="play('${item.id}')">
		    		<span><img src="${pageContext.request.contextPath}/static/img/close.png" alt="" id="img${item.id }">${item.title}</span>
		    		<span class="date"><fmt:formatDate value="${item.createTime }"  pattern="yyyy/MM/dd"/></span> 
		            <div class="clear"></div>
		            <div class="Text" id="div${item.id }">
		            	${item.content}
		            </div>
		            <div class="clear"></div>
	    		</li>	
             </c:forEach>

            
           
    	</ul>   
</div>






    </div>


   </div>



</body>
