var layout_center_tabs;
var layout_center_tabsMenu;
$(function() {
    layout_center_tabs = $('#layout_center_tabs').tabs();
    layout_center_tabsMenu = $('#layout_center_tabsMenu').menu({
        onClick : function(item) {
            var curTabTitle = $(this).data('tabTitle');
            var type = $(item.target).attr('type');
            //刷新
            if (type === 'refresh') {
                refresh(layout_center_tabs.tabs('getTab',curTabTitle));
                return;
            }
            //关闭
            if (type === 'close') {
                cancel();
                return;
            }

            var allTabs = layout_center_tabs.tabs('tabs');
            var closeTabsTitle = [];

            $.each(allTabs, function() {
                var opt = $(this).panel('options');
                if (opt.closable && opt.title != curTabTitle && type === 'closeOther') {
                    closeTabsTitle.push(opt.title);
                } else if (opt.closable && type === 'closeAll') {
                    closeTabsTitle.push(opt.title);
                }
            });

            for ( var i = 0; i < closeTabsTitle.length; i++) {
                layout_center_tabs.tabs('close', closeTabsTitle[i]);
            }
            //选中当前Tab
            layout_center_tabs.tabs('select', curTabTitle);
        }
    });

    //活动中的tip消息
    var activeTip;
    layout_center_tabs.tabs({
        fit : true,
        border : false,
        tools:'#layout_center_tabs_full-tools',
//        tools:[{
//            text:'',
//            iconCls:'easyui-icon-reload',
//            handler:function(){refresh()}
//        },{
//            text:'',
//            iconCls:'easyui-icon-cancel',
//            handler:function(){cancel()}
//        }],
        onContextMenu : function(e, title,index) {
            e.preventDefault();
            layout_center_tabsMenu.menu('show', {
                left : e.pageX,
                top : e.pageY
            }).data('tabTitle', title);
        },
        onAdd:function(title,index){
            //tip标题提示
            var tab = $(this).tabs('getTab',index).panel('options').tab;
            tab.unbind('mouseenter').bind('mouseenter',function(e){
                activeTip = $(this).tooltip({
                    position: 'top',
                    content: title
                }).tooltip('show',e);
            });
        },
        onBeforeClose:function(title,index){
            if(activeTip){
                activeTip.tooltip('destroy');
            }
        }
    });

    var indexTitle = "首页";
    layout_center_tabs.tabs('add',{
        title:indexTitle,
        iconCls:'eu-icon-home',
        closable:false,
        /*content:'<img alt="homePage" src="'+ctx+'/static/img/homePage.png" style="width:100%;height:100%;">'*/
                content:'<iframe scrolling="yes" frameborder="0" src="'+ctx+'/collection/targetAchieve/add" style="width:100%;height:100%;scrolling:yes;margin:0px;padding:0px;"></iframe>'
           });

    //首页tip标题提示
    layout_center_tabs.tabs('getTab',indexTitle).panel('options').tab.unbind('mouseenter').bind('mouseenter',function(e){
        $(this).tooltip({
            position: 'top',
            content: indexTitle
        }).tooltip('show',e);
    });
    
    //菜单渲染
    /*$.ajax({
        url: ctx+"/sys/resource/navTree",
        type: 'post',
        dataType: 'json',
        success: function (data) {
        	 if(data.length>0){
        		 var $menuNvn = $("#menu_nvn");
        		 for(var i=0;i<data.length;i++){
        			 var n = data[i];
        			 var $domLi = $("<li></li>");
        			 //$domLi.css('text-align','center');
        			 var $domA = $("<a></a>"); 
        			 //$("<img src='"+ctxStatic+"/img/menu/next.png'/>");
        			 var $domImg = $("<div style='background-image:url("+ctxStatic+"/js/easyui-1.4/extend/icon/icons/extjs_icons/app/"+n.iconCls.substring(8)+".png);height:30px;background-repeat: no-repeat;background-position: 12px 0px;'></div>");
        			 var $domText = $("<span style='color:white;'>"+n.text+"</span>");
        			 
        			 //处理事件
    				 bindMenuClick($domA,n);
    				 //创建子菜单
        			 createMenuItem(n,$domLi);
        			 $domA.append($domImg);
        			 $domA.append($domText);
        			 $domLi.append($domA);
        			 $menuNvn.append($domLi);
        		 }
        	 }
        	 //初始化菜单
        	 amazonmenu.init({
        		menuid: 'mysidebarmenu'
    		});
        }
    });*/
    
});
//创建菜单项
function createMenuItem(n,pli){
	if(undefined != n.children && n.children!=null && n.children.length >0){
		var $domUl = $("<ul></ul>");
		for(var i=0;i<n.children.length;i++){
			var cn = n.children[i];
			var $domLi = $("<li style='margin: 0;padding: 0;'></li>");
			var $domA = $("<a></a>"); 
			var $domImg = $("<span class='tabs-icon "+cn.iconCls+"'></span><span class='tree-indent'></span>");//$("<img style='vertical-align:middle;' src='"+ctxStatic+"/img/menu/next.png'/>");
			var $domText = $("<span style='color:white;margin-left:0px;'>"+cn.text+"</span>");
			
			//处理事件
			bindMenuClick($domA,cn);
			//递归
			createMenuItem(cn,$domLi);
			
			$domA.append($domImg);
			$domA.append($domText);
			$domLi.append($domA);
			$domUl.append($domLi);
		}
		pli.append($domUl);
	}
}
//绑定菜单事件
function bindMenuClick(dom,n){
	dom.click(function(){
		var url = n.attributes.url;
        if(url){
            if(url.substring(0,1) == "/" ){
                url = ctx + url;
            }else{
                url = ctx+'/' + url;
            }
            eu.addTab(layout_center_tabs,n.text,url,true,n.iconCls+"-black");
        }
	});
}

//刷新
function refresh(selectedTab){
    var tab;
    if(selectedTab){
        tab = selectedTab;
    }else{
        tab = layout_center_tabs.tabs('getSelected');
    }
    if(tab == undefined){//未添加任何tab
        return;
    }
    var href = tab.panel('options').href;
    if (href) {/*说明tab是以href方式引入的目标页面*/
        var index = layout_center_tabs.tabs('getTabIndex', tab);
        layout_center_tabs.tabs('getTab', index).panel('refresh');
    } else {/*说明tab是以content方式引入的目标页面*/
        var panel = tab.panel('panel');
        var iframe = panel.find('iframe');
        layout_center_tabs.tabs('updateIframeTab',{
            which:tab.panel('options').title,
            iframe:{src:iframe[0].src}
        });
    }
}
//关闭
function cancel(){
    var index = layout_center_tabs.tabs('getTabIndex', layout_center_tabs.tabs('getSelected'));
    var tab = layout_center_tabs.tabs('getTab', index);
    if(tab == undefined){//未添加任何tab
        return;
    }
    if (tab.panel('options').closable) {
        layout_center_tabs.tabs('close', index);
    } else {
        eu.showAlertMsg('[' + tab.panel('options').title + ']不可以被关闭.', 'error');
    }
}
/**
 *  全屏切换
 * @param flag
 */
function screenToggle(flag){
    var tools ;
    $(".easyui-tooltip").tooltip("hide","click");//  点击图标 隐藏tooltip提示
    //选中的tab页 防止 全屏导致的自动选择第一个tab
    var selectedTab = layout_center_tabs.tabs("getSelected");
    var selectedTab_Title = undefined;
    if(selectedTab){
        selectedTab_Title = selectedTab.panel("options").title;
    }

    if(flag){  //全屏
        tools = "#layout_center_tabs_unfull-tools";
        parent.indexLayout.layout("fullCenter");

    }else{ //退出全屏
        tools = "#layout_center_tabs_full-tools";
        parent.indexLayout.layout("unFullCenter");
    }
    layout_center_tabs.tabs({tools:tools});
    if(selectedTab_Title){
        layout_center_tabs.tabs("select",selectedTab_Title);
    }
}