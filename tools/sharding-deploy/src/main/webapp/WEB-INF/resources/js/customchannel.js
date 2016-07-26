$(function(){
	askList();
} )
function askList(){ 
	var username=getQueryString("username");
	var param = {"userName":username};
	//var param = {};
	$.ajax({
        type: "get",
        dataType: "json",
        data:param,
        url: "/channel/customedChannelDetail.json",
        success: function (data) {
        	if(data.webResult.data&&data.webResult.stateCode.code==0){
        		var dataResult=data.webResult.data;	
        		//var dataResult=data.webResult.data;	
            	$('#channelName').html(dataResult.channelName);
            	//alert(dataResult.channelName);
            	$('#nick').html(dataResult.userNick);
            	$('#phoneNum').html(dataResult.phoneNum);
            	$('#inviteCode').html(dataResult.inviteCode);	
            	$('#extra').html(dataResult.channelExtraInfo);
        		if(data.webResult.data.inviteCode){
        			$('#inCode').attr('src','getPhoto.do?inviteCode='+dataResult.inviteCode) 
        		}	       	
        	}else{
        		//
        	}
      
        	
        },
        error:function(XMLHttpRequest, textStatus, errorThrown){
        	console.log(XMLHttpRequest.status);
        	console.log(XMLHttpRequest.readyState);
        	console.log(textStatus);
        }
    });
 } 
    function button_onclick(){
    	 DownLoad();	 		 
	}
     function DownLoad() { 
         var form = $("<form>");   //定义一个form表单
         form.attr('style', 'display:none');   //在form表单中添加查询参数
         form.attr('target', '');
         form.attr('method', 'post');
         form.attr('action', "/channel/downloadPhoto");
         var input1 = $('<input>');
         var sizelist=document.getElementsByName('size');   
         var size=256; 
         //循环拿到选择的二维码规格
         for (var i = 0; i < sizelist.length; i++) {    
	       if (sizelist[i].checked == true) {
	       	   size=sizelist[i].value;       
	       }          
       }                       
         input1.attr('type','hidden'); 
         input1.attr('name','size'); 
         input1.attr('value',size);
     
    //拿到邀请码     
         var input2 = $('<input>');
         var inviteCode=document.getElementById('inviteCode').innerHTML;
         input2.attr('type','hidden'); 
         input2.attr('name','inviteCode'); 
         input2.attr('value',inviteCode);
         
         $('body').append(form);  //将表单放置在web中 
         form.append(input1);   //将查询参数控件提交到表单上
         form.append(input2);
         form.submit();

      }