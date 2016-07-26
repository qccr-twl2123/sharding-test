var pageId;
$().ready(function () {
    pageId = getUrlParam("pageId");

    $('#vbox').html('<i class="fa fa-spinner fa-pulse fa-3x"></i>');
    $('#vboxadd').hide();
    $.ajax({
        type: "get",
        url: "/admin/homeTemplate/loadModules.jhtml",
        data: {
            pageId:pageId,
            c: Math.round(Math.random() * 10000)
        },
        success: function (data) {
            $('#vbox').html('');
            var jsonobj = typeof (data) == "string" ? eval("(" + data + ")") : data;
            $("#pageName").text(jsonobj.pageName);
            $("#pageType").text(jsonobj.pageType==1?"用户版APP":"商户版APP");
            $("#pageStartTime").text(jsonobj.startTime);
            $("#pageStatus").text(jsonobj.status==1?"已发布":"未发布");
            $('#vbox').html("");
            if(jsonobj.list.length<=0){
                defaultVal();
            }else{
                for(i=0;i<jsonobj.list.length;i++){
                    var o=jsonobj.list[i];
                    var moduleObj=$(".vtmpl .itmplitem[moduleId='"+o.moduleId+"']");
                    moduleObj.attr("moduleName",o.moduleName);
                    moduleObj.attr("order",o.order);
                    moduleObj.attr("onlineTime",o.onlineTime);
                    moduleObj.attr("offlineTime",o.offlineTime);
                    moduleObj.attr("relaId",o.relaId);
                    moduleObj.attr("configElements",o.configElements);
                    $('#vbox').append($(moduleObj).parent().html());
                }
            }

            bindModule();
            $('#vboxadd').show();
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            $('#vbox').html('');
            $('#vboxadd').show();
            generate("error",textStatus);
        }
    });


    $(".vtmpl .itmplitem .tmp").on({
        mouseover:function(){
            $(this).find(".bglay").addClass("bglayshow");
            $(this).find(".bglay span").eq(0).hide();
            $(this).find(".bglay span").eq(1).hide();
            $(this).find(".bglay span").eq(2).removeClass("blue").addClass("blue").text('添加模块').show();
        },
        mouseout:function(){
            $(this).find(".bglay").removeClass("bglayshow");
        }
    });


    $("input:radio[name='moduleType']").change(function(){
        if ($("input:radio[name='moduleType']:checked").val() == '1') {
             $(".ibanner-tmpl").show();
             $(".icolumn-tmpl").hide();
             $(".iprozone-tmpl").hide();
         }
         if ($("input:radio[name='moduleType']:checked").val() == '2') {
            $(".ibanner-tmpl").hide();
            $(".icolumn-tmpl").hide();
            $(".iprozone-tmpl").show();
         } 
         if ($("input:radio[name='moduleType']:checked").val() == '3') {
            $(".ibanner-tmpl").hide();
            $(".icolumn-tmpl").show();
            $(".iprozone-tmpl").hide();
         }               
    });


    $(".itmplitem .tmp .bglay span.opadd").on('click',function(){
        $("#relaId").val("0");

        if (!check()) {
            return false;
        }

        if($("input:radio[name='moduleType']:checked").val()==3){
            if ($(".vbox .itmplitem .tmpl-column").length>0) {
                generate("warning","栏目不能重复添加");
                return false;
            }
        } 

        var itemtmpl=$(this).parent().parent().parent();
        itemtmpl.find(".bglay").removeClass("bglayshow");

        var onlineTime=$("#onlineTime").val();
        var onlineTimestamp = Date.parse(new Date(onlineTime));
        var offlineTime=$("#offlineTime").val();
        var offlineTimestamp = Date.parse(new Date(offlineTime));
        itemtmpl.attr("moduleName",$("#moduleName").val());
        itemtmpl.attr("order",$("#order").val());
        itemtmpl.attr("onlineTime",onlineTime);
        itemtmpl.attr("offlineTime",offlineTime);
        itemtmpl.attr("relaId",0);

        $("#moduleId").val(itemtmpl.attr("moduleId"));

        var order=parseInt($("#order").val());
        var iflag=true,iflagObj;
        $(".vbox .itmplitem").each(function(){
            var tmp_order=parseInt($(this).attr("order"));
            if(tmp_order>=order){
                if(iflag){
                    iflagObj=$(this);
                }
                iflag=false
                tmp_order+=1;
                $(this).attr("order",tmp_order)
            }
        });

        if(iflag){
            $('#vbox').append(itemtmpl.parent().html());
        }
        else {
            $(iflagObj).before(itemtmpl.parent().html());
        }
      
        $("#resetTmpl").click();
        $(".itmplattr").hide();
        $(".ibanner-tmpl").hide();
        $(".icolumn-tmpl").hide();
        $(".iprozone-tmpl").hide();      
        bindModule();
    });



    $(".vboxadd .itmplitemadd .tmpl-add").on({
        mouseover:function(){
            $(this).find(".bglay").addClass("bglayshow");
            $(this).find(".bglay span").eq(0).hide();
            $(this).find(".bglay span").eq(1).hide();
            $(this).find(".bglay span").eq(2).removeClass("blue").addClass("blue").text('添加新模块').show();
        },
        mouseout:function(){
            $(this).find(".bglay").removeClass("bglayshow");
        }
    });


    $(".itmplitemadd .tmpl-add .bglay span.opadd").on('click',function(){             
        $(".itmplattr").show();
        $(".ibanner-tmpl").hide();
        $(".icolumn-tmpl").hide();
        $(".iprozone-tmpl").hide();
        $("#resetTmpl").click();
    });

    $("#resetTmplBtn").on("click",function(){
        $("#resetTmpl").click();
        $(".ibanner-tmpl").hide();
        $(".icolumn-tmpl").hide();
        $(".iprozone-tmpl").hide();
    })

    //save
   $("#saveTmpl").on("click",function(){
        $("#saveTmpl").off("click");
        $("#saveTmpl").attr("disabled",true);
        saveModule();
   })

});

function defaultVal(){
    var moduleTmpl=$(".vtmpl .itmplitem[moduleId='9']");
    moduleTmpl.attr("moduleName","Banner");
    moduleTmpl.attr("order",1);
    moduleTmpl.attr("onlineTime",dateFormat(Date.parse(new Date()),"yyyy-MM-dd hh:00:00"));
    moduleTmpl.attr("offlineTime",dateFormat(Date.parse(new Date()),"yyyy-MM-dd hh:00:00"));
    $('#vbox').append(moduleTmpl.parent().html());
    moduleTmpl=$(".vtmpl .itmplitem[moduleId='10']");
    moduleTmpl.attr("moduleName","栏目");
    moduleTmpl.attr("order",2);
    moduleTmpl.attr("onlineTime",dateFormat(Date.parse(new Date()),"yyyy-MM-dd hh:00:00"));
    moduleTmpl.attr("offlineTime",dateFormat(Date.parse(new Date()),"yyyy-MM-dd hh:00:00"));
    $('#vbox').append(moduleTmpl.parent().html());
}


function bindModule(){
    $(".vbox .itmplitem").on({
        mouseover:function(){
            $(this).find(".bglay").addClass("bglayshow");
            $(this).find(".bglay span").eq(0).removeClass("blue").addClass("blue").text('修改').show();
            $(this).find(".bglay span").eq(1).removeClass("red").addClass("red").text('删除').show();
            if($(this).attr("moduletype")==3){
                $(this).find(".bglay span").eq(1).hide();
            }
            $(this).find(".bglay span").eq(2).hide();
        },
        mouseout:function(){
            $(this).find(".bglay").removeClass("bglayshow");
        }
    });

    $(".vbox .itmplitem").each(function(){
        var st=$(this).attr("moduleType");
        var stype="";
        if(st==1){stype="banner";}
        else if(st==2){stype="专区";}
        else if(st==3){stype="栏目";}
        $(this).popover({
            title:$(this).attr("moduleName"),
            container:'body',
            html:true,
            content:'类型：'+stype+'<br/>名称：'+$(this).attr("moduleName")+'<br/>顺序号：'+$(this).attr("order")+'<br/>上线时间：'+$(this).attr("onlineTime")+'<br/>下线时间：'+$(this).attr("offlineTime"),
            placement:'right',
            trigger:'hover'
        });        
    });



    //delete
    $(".vbox .itmplitem .tmp .bglay span.opdel").on('click',function(){
        var relaId=$(this).parent().parent().parent().attr("relaId");
        if(parseInt(relaId)==0){
            $(this).parent().parent().parent().remove();
            return false;
        }

        $.noty.closeAll();
        noty({
            text: '删除模块，模块中的元素也将全部删除，<br/>确定删除吗？',
            type: 'warning',
            dismissQueue: true,
            layout: 'topCenter',
            theme: 'defaultTheme',
            buttons: [
                {
                    addClass: 'btn btn-primary', text: '确定', onClick: function ($noty) {
                        $noty.close();
                        $.ajax({
                            type: "post",
                            url: "/admin/homeTemplate/deleteModule.jhtml",
                            data: {
                                pageId:pageId,
                                relaId:relaId,
                                c: Math.round(Math.random() * 10000)
                            },
                            success: function (data) {
                                var jsonobj = typeof (data) == "string" ? eval("(" + data + ")") : data;
                                if(jsonobj.success){
                                    generate("success",jsonobj.message);
                                }else{
                                    generate("error",jsonobj.message);
                                    return false;
                                }
                                location.href=location.href;
                            },
                            error: function (XMLHttpRequest, textStatus, errorThrown) {
                                generate("error",textStatus);
                            }
                        });
                    }
                },
                {
                    addClass: 'btn btn-danger', text: '取消', onClick: function ($noty) {
                        $noty.close();
                    }
                }
            ]
        });
        
    });
    
    //edit
    $(".vbox .itmplitem .tmp .bglay span.opedit").on('click',function(){
        $(".itmplattr").show();
        $(".vbox .itmplitem").removeClass("iactive");
        var tmplitem=$(this).parent().parent().parent();
        tmplitem.addClass("iactive");

        var relaId=tmplitem.attr("relaId");
        var moduleId=tmplitem.attr("moduleId");
        var moduleType=tmplitem.attr("moduleType");
        var moduleName=tmplitem.attr("moduleName");
        var order=tmplitem.attr("order");
        var onlineTime=tmplitem.attr("onlineTime");
        var offlineTime=tmplitem.attr("offlineTime");

        $("#relaId").val(relaId);
        $("#moduleId").val(moduleId);
        $("#moduleName").val(moduleName);
        $("#order").val(order);
        $("#onlineTime").val(onlineTime);
        $("#offlineTime").val(offlineTime);
        $("input:radio[name='moduleType'][value='"+moduleType+"']").click();
   });
}

//save
function saveModule(){
    if ($(".vbox .itmplitem .tmpl-column").length<=0) {
        generate("warning","请添加栏目");
        return false;
    }

    //if relaid>0 将修改的模块信息预存
    var relaid=$("#relaId").val();
    if(parseInt(relaid)>0){
        var tmpitem=$(".vbox .itmplitem[relaId='"+relaid+"']");
        tmpitem.attr("moduleName",$("#moduleName").val());
        tmpitem.attr("order",$("#order").val());
        tmpitem.attr("onlineTime",$("#onlineTime").val());
        tmpitem.attr("offlineTime",$("#offlineTime").val());
    }


    var tmplOjb={};
    var listMoule=[];
    tmplOjb.pageId=pageId;
    tmplOjb.list=listMoule;
     $(".vbox .itmplitem").each(function(){
        var moduleId,moduleName,order,onlineTime,offlineTime,relaId;
        relaId=$(this).attr("relaId");
        moduleId=parseInt($(this).attr("moduleId"));
        moduleName=$(this).attr("moduleName");
        order=parseInt($(this).attr("order"));
        onlineTime=$(this).attr("onlineTime");
        offlineTime=$(this).attr("offlineTime");

        var moduleOjb={};
        moduleOjb.relaId=relaId;
        moduleOjb.moduleId=moduleId;
        moduleOjb.moduleName=moduleName;
        moduleOjb.order=order;
        moduleOjb.onlineTime=onlineTime;
        moduleOjb.offlineTime=offlineTime;
        listMoule.push(moduleOjb);
    });

     
    $.ajax({
        type: "post",
        url: "/admin/homeTemplate/saveModule.jhtml",
        data: {
            pageId:pageId,
            modules:JSON.stringify(tmplOjb),
            c: Math.round(Math.random() * 10000)
        },
        success: function (data) {
            var jsonobj = typeof (data) == "string" ? eval("(" + data + ")") : data;
            if(jsonobj.success){
                generate("success",jsonobj.message);
            }else{
                generate("error",jsonobj.message);
                return false;
            }
            setTimeout(function(){
                location.href=location.href;
            },2000);
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            generate("error",textStatus);
        },
        complete:function(){
            $("#saveTmpl").attr("disabled",false);
        }
    });
}


function check() {
    var moduleType = $("input:radio[name='moduleType']:checked");
    var moduleName = $("#moduleName").val();
    var order = $("#order").val();
    var onlineTime = $("#onlineTime").val();
    var offlineTime = $("#offlineTime").val();

    if (moduleType.length<=0) {
        generate("warning","请输选择类型");
        return false;
    }
    if (moduleName=="") {
        generate("warning","请输入名称");
        $("#moduleName").focus();
        return false;
    }
    if (order=="") {
        generate("warning","请输入顺序");
        $("#order").focus();
        return false;
    }
    if (onlineTime=="") {
        generate("warning","请输入上线时间");
        $("#onlineTime").focus();
        return false;
    }
    if (offlineTime=="") {
        generate("warning","请输入下线时间");
        $("#offlineTime").focus();
        return false;
    }
    return true;
}

function getUrlParam(name){
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
    var r = window.location.search.substr(1).match(reg);
    if (r != null) return unescape(r[2]); return null;
}

function generate(type, text) {
    $.noty.closeAll();
    noty({
        text: text,
        type: type,
        dismissQueue: false,
        timeout: 1000,
        closeWith: ['click'],
        layout: 'topCenter',
        theme: 'defaultTheme',
        maxVisible: 1
    });
}
var dateFormat = function (date, format) {
    date = new Date(date);
    var map = {
        "M": date.getMonth() + 1, //月份
        "d": date.getDate(), //日
        "h": date.getHours(), //小时
        "m": date.getMinutes(), //分
        "s": date.getSeconds(), //秒
        "q": Math.floor((date.getMonth() + 3) / 3), //季度
        "S": date.getMilliseconds() //毫秒
    };
    format = format.replace(/([yMdhmsqS])+/g, function (all, t) {
        var v = map[t];
        if (v !== undefined) {
            if (all.length > 1) {
                v = '0' + v;
                v = v.substr(v.length - 2);
            }
            return v;
        }
        else if (t === 'y') {
            return (date.getFullYear() + '').substr(4 - all.length);
        }
        return all;
    });
    return format;
}
