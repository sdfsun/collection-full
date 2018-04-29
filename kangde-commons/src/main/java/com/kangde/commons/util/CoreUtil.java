package com.kangde.commons.util;

import java.util.Collection;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;

import com.kangde.commons.easyui.Combobox;
import com.kangde.commons.easyui.SelectType;
import com.kangde.commons.easyui.TreeNode;

/**
 * 核心工具类
 * @author lisuo
 *
 */
public abstract class CoreUtil {
	
	/**
	 * 获取一条数据
	 * @param list 集合
	 * @return 集合索引为0的数据
	 */
	public static <T> T getOne(List<T> list){
		if(CollectionUtils.isNotEmpty(list)){
			return list.get(0);
		}
		return null;
	}
	
	/**
	 * 获取combo标题
	 * @param container 结果容器
	 * @param selectType 全部("all") ,请选择("select") 
	 */
	public static void getComboTitle(Collection<Combobox> container,String selectType){
		if(StringUtils.isNotBlank(selectType)){
    		SelectType s = SelectType.getSelectTypeValue(selectType);
            if (s != null) {
                Combobox selectCombobox = new Combobox("",s.getDescription());
                container.add(selectCombobox);
            }
    	}
	}
	
	/**
	 * 获取tree标题
	 * @param container 结果容器
	 * @param selectType 全部("all") ,请选择("select") 
	 */
	public static void getTreeTitle(List<TreeNode> container,String selectType){
		if(StringUtils.isNotBlank(selectType)){
			//---请选择---"
			SelectType s = SelectType.getSelectTypeValue(selectType);
			TreeNode selectTreeNode = new TreeNode("", s.getDescription());
			container.add(selectTreeNode);
		}
	}
	
	/**
	 * 递归判断是否包含节点
	 * @param treeNodes 节点集合id
	 * @param nodeId 节点id
	 * @return
	 */
	public static boolean treeContains(List<TreeNode> treeNodes,String nodeId){
		if(StringUtils.isNotBlank(nodeId)){
			if(CollectionUtils.isNotEmpty(treeNodes)){
				for(TreeNode node:treeNodes){
					if(nodeId.equals(node.getId())){
						return true;
					}else{
						if(treeContains(node.getChildren(), nodeId)){
							return true;
						}
					}
				}
			}
		}
		return false;
	}
	
	/**
	 * 隐藏中间四位
	 * @param str
	 * @return
	 */
	public static String middleFourHide(String str){
		if(StringUtils.isNotBlank(str)){
		if(str.length() > 5){
			int idx = str.length() / 2;
			String prefix = str.substring(0,idx-2)+"****";
			String subfix = str.substring(idx+2,str.length());
			return prefix+subfix;
		}
		}
		return str;
	}
	
	/**
	 * 隐藏后四位
	 * @param str
	 * @return
	 */
	public static String lastFourHide(String str){
		if(StringUtils.isNotBlank(str)){
			if(str.length() > 4){
				String subfix = str.substring(str.length() - 4);
				String prefix = str.substring(0, str.lastIndexOf(subfix));
				return prefix+"****";
			}
		}
		return str;
	}
	
}
