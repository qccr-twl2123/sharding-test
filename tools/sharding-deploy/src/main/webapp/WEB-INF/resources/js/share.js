//下拉菜单汇总

/*将树状结构数组解析为select下拉框dom
treeArr 树状结构数组
dataVal data-value对应的键值
dataText item里显示文字对应的键值
inpId <input 的id跟name值(提交时用于获取下拉框data-value)
children 如果有子元素，子元素所对应的键值
id 子元素的唯一标识
parentId 父元素的唯一标识
appendName 如果存在appendName，则把生成的dom append进入
type 缩进 0   含有加减号1
callfun 回调函数(例如 编辑时数值的回填 可能用到)
*/
var getSelect=function(treeArr, dataVal, dataText, inpId, children, id, parentId, appendName, type, callfun){
	if(treeArr.length>0&&type==0){
		var sele = "<div class='item' data-value='-1'>请选择</div>" + getChildren0(treeArr, 0, dataVal, dataText, children);
	}else if(treeArr.length>0&&type==1){
		var sele = "<div class='item' data-value='-1'>请选择</div>" + getChildren1(treeArr, 0, dataVal, dataText, children, id, parentId);
	}
	var dom = "<div class='ui selection dropdown'><input type='hidden' name='" + inpId + "' id='" + inpId + "' value='-1'><div class='default text'>请选择</div><i class='dropdown icon'></i><div class='menu'>" + sele + "</div></div>";
	if(appendName){
		$(appendName).empty();
		$(appendName).append($(dom));
		$('div.dropdown').dropdown();
	}
	if(type==1){//含有加减号时 添加事件
		$("#"+inpId).siblings().find("i.icon").on("click",function(event){
			var childItem=$(".item[parentId="+$(this).parent().attr("id")+"]");
			if($(this).hasClass("plus")){
				$(this).removeClass("plus icon");
				$(this).addClass("minus icon");
				childItem.toggle();
			}else if($(this).hasClass("minus")){
				$(this).removeClass("minus icon");
				$(this).addClass("plus icon");
				childItem.toggle();
			}
			event.stopPropagation();
		})
		var def=$("#"+inpId).siblings(".default.text");
		$("#"+inpId).on("change",function(){
			var txt=def.html();
			txt=txt.replace(/(<.[^>]*>)/ig, '');
			def.text(txt);
		})
	}
	if(callfun){//回调函数
		callfun();
	}
	return dom;
}
//遍历树状数组 生成缩进样式
var getChildren0 = function(data, level, dataVal, dataText, children) {
	var dom = "";
	for (var i = 0; i < data.length; i++) {
		if (typeof(data[i]["" + children + ""]) != "undefined" && data[i]["" + children + ""].length > 0) { //父节点
			dom += "<div class='item' data-value='" + data[i]["" + dataVal + ""] + "' style='padding-left:" + 15 * (level + 1) + "px !important;font-weight: bold !important;'>" + data[i]["" + dataText + ""] + "</div>";
			dom += getChildren0(data[i]["" + children + ""], level + 1, dataVal, dataText, children);
		} else { //叶子结点
			dom += "<div class='item' data-value='" + data[i]["" + dataVal + ""] + "' style='padding-left:" + 15 * (level + 1) + "px !important;'>" + data[i]["" + dataText + ""] + "</div>";
		}
	}
	return dom;
}
//遍历树状数组 生成加减号样试
var getChildren1 = function(data, level, dataVal, dataText, children, id, parentId) {
	var dom = "";
	for (var i = 0; i < data.length; i++) {
		var display="";
		if(level==0)
			display="";
		else
			display="display:none";
		if (typeof(data[i]["" + children + ""]) != "undefined" && data[i]["" + children + ""].length > 0) { //父节点
			dom += "<div class='item' data-value='" + data[i]["" + dataVal + ""] + "' id='"+data[i][""+id+""]+"' parentId='"+data[i][""+parentId+""]+"' style='padding-left:" + 15 * (level + 1) + "px !important;font-weight: bold !important;"+display+"'><i class='plus icon' style='margin-right: 0 !important;'></i>" + data[i]["" + dataText + ""] + "</div>";
			dom += getChildren1(data[i]["" + children + ""], level + 1, dataVal, dataText, children,id, parentId);
		} else { //叶子结点
			dom += "<div class='item' data-value='" + data[i]["" + dataVal + ""] + "' id='"+data[i][""+id+""]+"' parentId='"+data[i][""+parentId+""]+"' style='padding-left:" + 18 * (level + 1) + "px !important;"+display+"'>" + data[i]["" + dataText + ""] + "</div>";
		}
	}
	return dom;
}
var getComSelect=function(data,dataVal,dataText){
	var dom="";
	for(var i=0;i<data.length;i++){
		dom+="<div class='item' data-value='" + data[i]["" + dataVal + ""] + "'>" + data[i]["" + dataText + ""] + "</div>";
	}
	return dom;
}
//普通下拉菜单(没缩进 没折叠)
var getCommonSel=function(arr, dataVal, dataText, inpId, appendName,callfun){
	var sele = "<div class='item' data-value='-1'>请选择</div>" + getComSelect(arr,dataVal, dataText);
	var dom = "<div class='ui selection dropdown'><input type='hidden' name='" + inpId + "' id='" + inpId + "' value='-1'><div class='default text'>请选择</div><i class='dropdown icon'></i><div class='menu'>" + sele + "</div></div>";	
	if(appendName){
		$(appendName).empty();
		$(appendName).append($(dom));
		$('div.dropdown').dropdown();
	}
	if(callfun){//回调函数
		callfun();
	}
	return dom;
}
/////////////////////////////////////////////////////////////////////////////////////经常使用的封装方法
/**
 * 下拉菜单（带缩进跟搜索）。样例库见存管理storage.itemlist.ent.js
 * children:孩子节点字段
 * dataVal:用来传递的select字段
 * dataText:用来显示的select字段
 * isAllAbled:是否都可以选择 true：都可以选择 false：叶子节点可以选择
 * isLeaf:判断是否是叶子节点的字段
 */
var getSelectSearchTree=function(jsonData){
	var msg={
		children:jsonData.children,
		dataVal:jsonData.dataVal,
		dataText:jsonData.dataText,
		isAllAbled:typeof(jsonData.isAllAbled)=="undefined"?false:jsonData.isAllAbled,
		isLeaf:jsonData.isLeaf
	}
	var select= "<div class='item' data-value='-1'>请选择</div>" + getChildren(jsonData.data,msg,0);
	return select;
}
var getChildren=function(data,msg,level){
	if(msg.isAllAbled){
		var dom="";
		for(var i=0;i<data.length;i++){	
			if(typeof(data[i][""+msg.children+""])!="undefined"&&data[i][""+msg.children+""].length>0){//父节点
				dom += "<div class='item' data-value='" + data[i][""+msg.dataVal+""]+ "' style='padding-left:" + 15 * (level + 1) + "px !important;font-weight: bold !important;'>" + data[i][""+msg.dataText+""] + "</div>";
				dom += getChildren(data[i][""+msg.children+""],msg,level+1);
			}else{//子节点
				dom += "<div class='item' data-value='" + data[i][""+msg.dataVal+""] + "' style='padding-left:" + 15 * (level + 1) + "px !important;'>" + data[i][""+msg.dataText+""] + "</div>";
			}
		}
		return dom;
	}else{
		var dom="";
		for(var i=0;i<data.length;i++){	
			if(typeof(data[i][""+msg.children+""])!="undefined"&&data[i][""+msg.children+""].length>0){//父节点
				if(data[i][""+msg.isLeaf+""]=='0'||data[i][""+msg.isLeaf+""]==false){	
					dom += "<div class='item disabled' data-value='" + data[i][""+msg.dataVal+""]+ "' style='padding-left:" + 15 * (level + 1) + "px !important;font-weight: bold !important;'>" + data[i][""+msg.dataText+""] + "</div>";
				}else{
					dom += "<div class='item' data-value='" + data[i][""+msg.dataVal+""]+ "' style='padding-left:" + 15 * (level + 1) + "px !important;font-weight: bold !important;'>" + data[i][""+msg.dataText+""] + "</div>";
				}
				dom += getChildren(data[i][""+msg.children+""],msg,level+1);
			}else{//子节点
				if(data[i][""+msg.isLeaf+""]=='0'||data[i][""+msg.isLeaf+""]==false){	
					dom += "<div class='item disabled' data-value='" + data[i][""+msg.dataVal+""] + "' style='padding-left:" + 15 * (level + 1) + "px !important;'>" + data[i][""+msg.dataText+""] + "</div>";
				}else{
					dom += "<div class='item' data-value='" + data[i][""+msg.dataVal+""] + "' style='padding-left:" + 15 * (level + 1) + "px !important;'>" + data[i][""+msg.dataText+""] + "</div>";
				}
			}
		}
		return dom;
	}
}



/*将普通数组整理成树状结构的数组
arr 返回数据最外层数组 
arrNode arr中的某一个 
mId自身唯一标识 
pId所属父级唯一标识
*/
function getTree(arr, arrNode, mId, pId) {
	for (var i = 0; i < arr.length; i++) {
		if (arr[i]["" + pId + ""] == arrNode["" + mId + ""]) {
			if (arrNode.children) {
				arrNode.children.push(arr[i]);
			} else {
				arrNode.children = [arr[i]];
			}
			getTree(arr, arr[i], mId, pId);
		}
	}
}




//全选

/*checkALLBtn
 *checkboxs
 *checkALL
 *uncheckALL
*/
var checkAll = function(checkALLBtn, checkboxs, checkALL, uncheckALL) {
	$('.table .tbody .checkbox').checkbox('attach events', '.check.button', 'check');
	$('.table .tbody .checkbox').checkbox('attach events', '.uncheck.button', 'uncheck');
	var $checkALL = $(checkALL);
	var $uncheckALL = $(uncheckALL);
	$uncheckALL.trigger("click");
	if ($(checkALLBtn).attr("checked")) {
		$(checkALLBtn).click();
	}
	$(checkALLBtn).unbind("click");
	$(checkALLBtn).click(function() {
		if ($(this).attr("index") == "1") {
			$uncheckALL.trigger("click");
			$(this).attr("index", "0");
		} else {
			$checkALL.trigger("click");
			$(this).attr("index", "1");
		}
	});
}

//去掉字符串里面的所有空格
var delAllSpace=function(str){
	var patrn=/\s+/g;
	return str.replace(patrn,"");
}


//数据校验
/*
 data={'value':val,'msg':'错误提示'}
 */
var checkNull=function(data){
		for(var i=0;i<data.length;i++){
			if(isNull(data[i].value)){
				showMsg(data[i].msg,"error");
				return false;
			}
		}
		return true;
}
//校验URL http://www.baidu.com
var isUrl=function(url){
	var patrn=/(http|ftp|https):\/\/[\w\-_]+(\.[\w\-_]+)+([\w\-\.,@?^=%&amp;:/~\+#]*[\w\-\@?^=%&amp;/~\+#])?/;
	if(!patrn.exec(url)) return false;
	return true;
}

/**
 *显示遮罩层(宽高100%)。。。loading
 */
var showDimmer=function(msg){
	var str=(msg==undefined?'loading':msg);
	var dimmer='<div class="ui segment dimmable dimmed" style="position: absolute; height: 100%; width: 100%; z-index: 10; opacity: 0.5;"><div class="ui inverted dimmer transition visible active" style="display: block !important;"><div class="ui text loader">'+str+'</div></div></div>';
	$("body").prepend($(dimmer));
}
/**
 *取消遮罩层(宽高100%)。。。loading
 */
var hideDimmer=function(){
	$(".ui.segment.dimmable.dimmed").remove();
}