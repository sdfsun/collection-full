var _ctx = _ctx;//参考west.jsp
var $menu_tree;
$(function() {
    //初始化导航菜单
    initMenuTree();
});


function initMenuTree(){
    //组织机构树
    $menu_tree = $("#menu_tree").tree({
        url : ctx+"/sys/resource/navTree",
        animate:true,
        onClick:function(node){
            var url = node.attributes.url;
            if(url){
                if(url.substring(0,4) == "http"){

                }else if(url.substring(0,1) == "/" ){
                    url = ctx + url;
                }else{
                    url = ctx+'/' + url;
                }
                eu.addTab(layout_center_tabs,node.text,url,true,node.iconCls);
            }else{
				$("#menu_tree").tree('toggle', node.target);
            }
        }
    });
}