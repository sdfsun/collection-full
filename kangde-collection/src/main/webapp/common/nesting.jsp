<%@ page contentType="text/html;charset=UTF-8"%>
<script type="text/javascript" charset="utf-8">
	function openCase(value, row, index) {
		return '<a style="color:blue;text-decoration: underline;cursor:pointer" onclick=window.open("'
				+ ctx
				+ '/collection/case?batchCode='
				+ row.id
				+ '")>'
				+ value + '</a>';
	}
	function openCaseDetailed(value, row, index) {
		return '<span style="text-decoration: underline;cursor:pointer" onclick=window.open("'
				+ ctx
				+ '/collection/casedetail?caseId='
				+ row.caseId
				+ '")>'
				+ value + '</span>';
	}
	function openEnclosure(value, row, index) {
		if(row.fzsize>0){
			return '<a href="javascript:void(0)" style="color:red;cursor:pointer" onclick=viewDocu("'
			+ row.id + '")>附件管理</a>';
		}else{
		return '<a href="javascript:void(0)" style="color:blue;cursor:pointer" onclick=viewDocu("'
				+ row.id + '")>附件管理</a>';
		}
	}
	function fmmoney(value) {
		if (value < 0) {
			return 0.00;
		}else if(null==value){
			return 0.00;
		}
		return $.fmoney(value);
	}
	function fmDate(value) {
		if (value) {
			return $.formatDate(value, 'yyyy-MM-dd');
		}
		return value;
	}
	function fmDateTime(value) {
		if (value) {
			return $.formatDate(value, 'yyyy-MM-dd HH:mm:ss');
		}
		return value;
	}
	function convert(rows){
	    function exists(rows, parentId){
	        for(var i=0; i<rows.length; i++){
	            if (rows[i].id == parentId) return true;
	        }
	        return false;
	    }
	    
	    var nodes = [];
	    // get the top level nodes
	    for(var i=0; i<rows.length; i++){
	        var row = rows[i];
	        if (!exists(rows, row.parentId)){
	            nodes.push({
	                id:row.id,
	                text:row.text
	            });
	        }
	    }
	    
	    var toDo = [];
	    for(var i=0; i<nodes.length; i++){
	        toDo.push(nodes[i]);
	    }
	    while(toDo.length){
	        var node = toDo.shift();    // the parent node
	        // get the children nodes
	        for(var i=0; i<rows.length; i++){
	            var row = rows[i];
	            if (row.parentId == node.id){
	                var child = {id:row.id,text:row.text};
	                if (node.children){
	                    node.children.push(child);
	                } else {
	                    node.children = [child];
	                }
	                toDo.push(child);
	            }
	        }
	    }
	   
	    return nodes;
	}
</script>
