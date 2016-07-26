var pageId;
$().ready(function () {
    elementFun.init();
});


var elementFun={
    init:function(){
        pageId = elementFun.getUrlParam("pageId");

        elementFun.loadModules();
        elementFun.loadCities();

        var $filePicker = $("#filePicker");
        $filePicker.uploader({complete:function(element, data){
            $("#imageUrl").attr("src",data.url).parent().parent().show();
        }});
        $("#imageUrl").parent().parent().hide();

        $("#citysel").on("click",function(){
            $("#vcheckbox input[type='checkbox']").attr("checked",false)
            var selids=$("#areaId").val();
            if(selids!=""){
                var arrid=selids.split(',');
                for(i=0;i<arrid.length;i++){
                    $("#vcheckbox input[type='checkbox'][value='"+arrid[i]+"']").attr("checked",true);
                }
            }
            $('#cities').modal('show');
        });


        $("#cityok").on("click",function(){
            var selids="";
            var selnames="";
            $("#vcheckbox input[type='checkbox']:checked").each(function(){
                selids+=selids==""?$(this).val():","+$(this).val();
                var name="<span>"+$(this).parent().text()+"</span>"
                selnames+=selnames==""?name:","+name;
            });
            $("#areaId").val(selids);
            $("#areaName").html(selnames);
            $('#cities').modal('hide');
        });
            

        $("#bigType").change(function(){
            var bigtypeid=$(this).val();
            $(".edit-banner .eletype").hide();
            $(".edit-banner #elementType"+bigtypeid).show();
            $(".edit-banner #elementType"+bigtypeid).val(0);
            $(".edit-banner .eletypeattr").hide();
        });
        $(".edit-banner .eletype").change(function(){
            $("#elementLink").val("");
            $("#elementContent").val("");
            $("#secKillStartTime").val("");
            $("#secKillEndTime").val("");
            
            $(".edit-banner .expt").hide();
            var bigType=$("#bigType").val();
            var elementType=$(this).val();
            if((bigType==1&&elementType==26)||(bigType==1&&elementType==3)||bigType==2){//列表&&品牌，列表&&商品列表，详情
                $(".edit-banner .eletypeattr").show();
                $(".edit-banner .elementContent").show();
                $(".edit-banner .selectContent").show();
            }else if(bigType==3&&elementType==6){//功能&&秒杀
                $(".edit-banner .eletypeattr").show();
                $(".edit-banner .elementContent").show();
                $(".edit-banner .selectContent").show();
                $(".edit-banner .secKillStartTime").show();
                $(".edit-banner .secKillEndTime").show();
            }else if(bigType==4){//web
                $(".edit-banner .eletypeattr").show();
                $(".edit-banner .elementLink").show();
            }else{
                $(".edit-banner .eletypeattr").hide();
            }
        });

        //选择
        $("#selectContent").click(function(){
            $(".gift_box_out").show();
            var bigType=$("#bigType").val();
            var elementType=$("#elementType"+bigType).val();
            if(bigType==1&&elementType==26){//列表&&品牌
                $("#selectIframe").attr("src","/admin/homeTemplate/select/brandList.jhtml");
            }
            else if(bigType==1&&elementType==3){//列表&&商品列表
                $("#selectIframe").attr("src","/admin/homeTemplate/select/goodCategoryBrand.jhtml");
            }
            else if(bigType==2&&elementType==4){//详情&&商品
                $("#selectIframe").attr("src","/admin/homeTemplate/select/goodsList.jhtml");
            }
            else if(bigType==2&&elementType==5){//详情&&门店
                $("#selectIframe").attr("src","/admin/homeTemplate/select/storeList.jhtml");
            }
            else if(bigType==2&&elementType==25){//详情&&轮胎
                $("#selectIframe").attr("src","/admin/homeTemplate/select/categoryList.jhtml?categoryId=10");
            }
            else if(bigType==3&&elementType==6){//功能&&秒杀
                $("#selectIframe").attr("src","/admin/homeTemplate/select/goodsseckill.jhtml");
            }
        });
        
        $(".gift_box_close").click(function(){
            $(".gift_box_out").hide();
        })

        $("#saveEle").on("click",function(){
            elementFun.saveElement();
        })

        $("#resetEleBtn").on("click",function(){
            elementFun.reset();
        })
    },
    loadModules:function(){
        $('#vbox').html('<i class="fa fa-spinner fa-pulse fa-3x"></i>');
        $.ajax({
            type: "GET",
            url: "/admin/homeTemplate/loadModules.jhtml",
            data: {
                pageId:pageId,
                c: Math.round(Math.random() * 10000)
            },
            success: function (data) {
                $('#vbox').html('');
                var pageobj = typeof (data) == "string" ? eval("(" + data + ")") : data;
                $("#pageName").text(pageobj.pageName);
                $("#pageType").text(pageobj.pageType==1?"用户版APP":"商户版APP");
                $("#pageStartTime").text(pageobj.startTime);
                $("#pageStatus").text(pageobj.status==1?"已发布":"未发布");
                $('#vbox').html("");
                for(i=0;i<pageobj.list.length;i++){
                    var m=pageobj.list[i];
                    var moduleTmpl=$(".edit-vtmpl .itmplitem[moduleId='"+m.moduleId+"']");
                    moduleTmpl.attr("moduleType",m.moduleType);
                    moduleTmpl.attr("moduleName",m.moduleName);
                    moduleTmpl.attr("order",m.order);
                    moduleTmpl.attr("onlineTime",m.onlineTime);
                    moduleTmpl.attr("offlineTime",m.offlineTime);
                    moduleTmpl.attr("relaId",m.relaId);
                    moduleTmpl.attr("configElements",m.configElements);
                    moduleTmpl.attr("pageId",pageobj.pageId);

                    $('#vbox').append($(moduleTmpl).parent().html());
                }

                elementFun.bindModule();
            },
            error: function (XMLHttpRequest, textStatus, errorThrown) {
                $('#vbox').html('');
                elementFun.generate("error",textStatus);
            }
        });
    },
    loadCities:function(){
        $.ajax({
            type: "GET",
            url: "/admin/homeTemplate/getAllCity.jhtml",
            data: {
                c: Math.round(Math.random() * 10000)
            },
            success: function (data) {
                var list = typeof (data) == "string" ? eval("(" + data + ")") : data;
                var htm="",countryId,countryName,province,pid,pname,cities,cid,cname;
                $("#vcheckbox").html("");
                countryId=list.id;
                countryName=list.area_name
                province=list.cities;

                htm="<div class='row chkcity'>";
                htm+="<label class='checkbox-inline'>";
                htm+="<input type='checkbox' id='chk_"+countryId+"' value='"+countryId+"'> "+countryName;
                htm+="</label>";
                htm+="<hr/></div>";
                $("#vcheckbox").append(htm);

                for(i=0;i<province.length;i++){
                    pid=province[i].id;
                    pname=province[i].area_name;
                    htm="";
                    htm="<div class='row chkcity'>";
                    htm+="<label class='checkbox-inline'>";
                    htm+=pname;
                    htm+="</label>";
                    htm+="<br/>";

                    cities=province[i].cities;
                    for(j=0;j<cities.length;j++){
                        cid=cities[j].id;
                        cname=cities[j].area_name;
                        htm+="<label class='checkbox-inline'>";
                        htm+="<input type='checkbox' id='chk_"+cid+"' value='"+cid+"'> "+cname;
                        htm+="</label>";
                    }
                    htm+="<hr/></div>";
                    $("#vcheckbox").append(htm);
                }
            },
            error: function (XMLHttpRequest, textStatus, errorThrown) {
                elementFun.generate("error",textStatus);
            }
        });
    },
    bindModule:function(){
        $(".vbox .itmplitem .ele").on({
            mouseover:function(){
                $(this).find(".bglay").addClass("bglayshow");
                $(this).find(".bglay span").eq(0).removeClass("red").addClass("red").text('编辑');
                $(this).find(".bglay span").eq(1).removeClass("blue").addClass("blue").text('添加');
            },
            mouseout:function(){
                $(this).find(".bglay").removeClass("bglayshow");
            }
        });
       $(".vbox .itmplitem .ele .bglay span").on('click',function(){
            elementFun.clearVar();
            var tmplitem=$(this).parent().parent().parent().parent();
            var eleitem=$(this).parent().parent();

            $(".vbox .itmplitem .ele").removeClass("iactive");
            eleitem.addClass("iactive");

            var moduleRelaId=tmplitem.attr("relaId");
            $("#moduleRelaId").val(moduleRelaId);
            $("#moduleId").val(tmplitem.attr("moduleId"));
            $("#moduleType").val(tmplitem.attr("moduleType"));
            $("#moduleName").text(tmplitem.attr("moduleName"));

            var moduleType=tmplitem.attr("moduleType");
            if(moduleType==1){
                $("#order").val("");
                $("#order").attr("disabled",false);
                $(".eleoptional").show();
                elementFun.optionVisibility(true);
            }else if(moduleType==2){
                $("#order").val(eleitem.attr("order"));
                $("#order").attr("disabled",true);
                $(".eleoptional").show();
                elementFun.optionVisibility(true);
            }else if(moduleType==3){
                $("#order").val("");
                $("#order").attr("disabled",false);
                $(".eleoptional").hide();
                elementFun.optionVisibility(false);
            }
         
            $("#infotips").html("");
            if(eleitem.attr("data-tips"))
                $("#infotips").html(eleitem.attr("data-tips"));
            elementFun.loadElementList(moduleRelaId);
       });
    },
    loadElementList:function(moduleRelaId){
        var moduleType=$("#moduleType").val();
        $.ajax({
            type: "GET",
            url: "/admin/homeTemplate/loadElements.jhtml",
            data: {
                moduleRelaId:moduleRelaId,
                c: Math.round(Math.random() * 10000)
            },
            success: function (data) {
                var eleobj = typeof (data) == "string" ? eval("(" + data + ")") : data;
                $("#tb_element tbody").html("");
                for(i=0;i<eleobj.length;i++){
                    var statuscss=eleobj[i].status==0?"":"class='rowonline'";
                    var tr="";
                    var operation="";
                    var stat=eleobj[i].status==0?1:0;
                    var optext=eleobj[i].status==1?"下线":"上线";
                    operation="<a href='javascript:void(0)' onclick='elementFun.onoffLine("+eleobj[i].elementId+","+stat+")'>"+optext+"</a>&nbsp;&nbsp;";
                    operation+="<a href='javascript:void(0)' onclick='elementFun.deleteElement("+eleobj[i].elementId+")'>删除</a>";
                    if(moduleType==2){
                        var order=$("#order").val();
                        if(order==eleobj[i].order){
                            tr="<tr data-row='"+JSON.stringify(eleobj[i])+"' "+statuscss+">";
                            tr+="<th scope='row'>"+eleobj[i].order+"</th>";
                            tr+="<td>"+eleobj[i].elementName+"</td>";
                            tr+="<td>"+elementFun.getTerminalTypeName(eleobj[i].terminalType)+"</td>";
                            tr+="<td>"+elementFun.getEleStatusName(eleobj[i].status)+"</td>";
                            tr+="<td>"+eleobj[i].onlineTime+"</td>";
                            tr+="<td>"+eleobj[i].offlineTime+"</td>";
                            tr+="<td>"+operation+"</td>";
                            tr+="</tr>";
                        }
                    }else{
                        tr="<tr data-row='"+JSON.stringify(eleobj[i])+"' "+statuscss+">";
                        tr+="<th scope='row'>"+eleobj[i].order+"</th>";
                        tr+="<td>"+eleobj[i].elementName+"</td>";
                        tr+="<td>"+elementFun.getTerminalTypeName(eleobj[i].terminalType)+"</td>";
                        tr+="<td>"+elementFun.getEleStatusName(eleobj[i].status)+"</td>";
                        tr+="<td>"+eleobj[i].onlineTime+"</td>";
                        tr+="<td>"+eleobj[i].offlineTime+"</td>";
                        tr+="<td>"+operation+"</td>";
                        tr+="</tr>";
                    }
                    $("#tb_element tbody").append(tr);
                }

                elementFun.clearVar();

                $("#tb_element tbody tr").on("click",function(){
                    $("#tb_element tbody tr").removeClass("rowactive");
                    $(this).addClass("rowactive");
                    var data=eval("(" + $(this).attr("data-row")+ ")");
                    elementFun.editElementRow(data);
                });

            },
            error: function (XMLHttpRequest, textStatus, errorThrown) {
                elementFun.generate("error",textStatus);
            }
        });
    },
    editElementRow:function(data){
        $("#moduleRelaId").val(data.moduleRelaId);
        $("#moduleId").val(data.moduleId);
        $("#relaId").val(data.relaId);
        $("#elementId").val(data.elementId);
        $("#areaId").val(data.areaId);

        $("#elementName").val(data.elementName);
        $("#terminalType").val(data.terminalType);
        $("#versionNo").val(data.versionNo);  
        if(!data.imageUrl){
            $("#imageUrl").attr("src",data.imageUrl).parent().parent().hide();
        }else{
            $("#imageUrl").attr("src",data.imageUrl).parent().parent().show();
        }    

        $("#onlineTime").val(data.onlineTime);
        $("#offlineTime").val(data.offlineTime);
        $("#order").val(data.order);
        $("#areaName").html(data.areaName);
        if(data.status==0){
            $("#onoffLineEle").val("上线");
        }else if(data.status==1){
            $("#onoffLineEle").val("下线");
        }
        elementFun.showType(data);
    },
    showType:function(data){
        $(".edit-banner .eletype").hide();
        $(".edit-banner .eletypeattr").hide();
        $(".edit-banner .expt").hide();
        var bigType=data.bigType;
        var elementType=data.elementType;
        $("#bigType").val(data.bigType);
        $("#elementType"+bigType).val(data.elementType);
        $("#elementType"+bigType).show();
        if((bigType==1&&elementType==26)||(bigType==1&&elementType==3)||bigType==2){//列表&&品牌，列表&&商品列表，详情
            $(".edit-banner .eletypeattr").show();
            $(".edit-banner .elementContent").show();
            $(".edit-banner .selectContent").show();
            $("#elementContent").val(data.elementContent);
        }else if(bigType==3&&elementType==6){//功能&&秒杀
            $(".edit-banner .eletypeattr").show();
            $(".edit-banner .elementContent").show();
            $(".edit-banner .selectContent").show();
            $(".edit-banner .secKillStartTime").show();
            $(".edit-banner .secKillEndTime").show();

            if(data.elementContent!=""){
                var jsonseckill=eval("("+data.elementContent+")");
                $("#elementContent").val(jsonseckill.id);
                $("#secKillStartTime").val(elementFun.dateFormat(jsonseckill.startTime,"yyyy-MM-dd hh:mm:ss"));
                $("#secKillEndTime").val(elementFun.dateFormat(jsonseckill.endTime,"yyyy-MM-dd hh:mm:ss"));
            }
        }else if(bigType==4){//web
            $(".edit-banner .eletypeattr").show();
            $(".edit-banner .elementLink").show();
            $("#elementLink").val(data.elementLink);
        }else{
            $(".edit-banner .eletypeattr").hide();
        }
    },
    saveElement:function(){
        $("#saveEle").off("click");
        $("#saveEle").attr("disabled",true);

        if(!elementFun.check()){
            $("#saveEle").attr("disabled",false);
            $("#saveEle").on("click",elementFun.saveElement);
            return false;
        }

        var elementOjb={};
        var moduleRelaId,moduleId,areaId,elementName,relaId,elementId,bigType,elementType,terminalType,versionNo = "",
        imageUrl,elementLink,elementContent,onlineTime,offlineTime,order;
        var eleLink="",eleCon="";

        moduleRelaId=$("#moduleRelaId").val();
        moduleId=$("#moduleId").val();
        relaId=$("#relaId").val();
        elementId=$("#elementId").val();
        areaId=$("#areaId").val();
        elementName = $("#elementName").val();
        imageUrl = $("#imageUrl").attr("src");

        bigType=$("#bigType").val();
        elementType=$("#elementType"+bigType).val();
        if((bigType==1&&elementType==26)||(bigType==1&&elementType==3)||bigType==2){//列表&&品牌，列表&&商品列表，详情
            eleCon = $("#elementContent").val();
        }
        else if(bigType==3&&elementType==6){//功能&&秒杀
            var secKillStartTime=$("#secKillStartTime").val();
            var secKillStartTimestamp = Date.parse(new Date(secKillStartTime.replace(/-/g,"/")));
            var secKillEndTime=$("#secKillEndTime").val();
            var secKillEndTimestamp = Date.parse(new Date(secKillEndTime.replace(/-/g,"/")));
            eleCon = "{\"id\":"+$("#elementContent").val()+",\"startTime\":"+secKillStartTimestamp+",\"endTime\":"+secKillEndTimestamp+"}";
        }
        else if(bigType==4){//web
            eleLink = $("#elementLink").val();
        }
        elementLink=eleLink;
        elementContent=eleCon;
        terminalType = $("#terminalType").find("option:selected").val();

        if($("#versionNo").parent().parent().is(":visible")){
            versionNo = $("#versionNo").val();
        }
        if($("#areaName").parent().parent().is(":visible")){
            areaId=areaId;
        }
        order = $("#order").val();
        onlineTime = $("#onlineTime").val();
        offlineTime = $("#offlineTime").val();


        elementOjb.pageId=pageId;
        elementOjb.moduleRelaId=moduleRelaId;
        elementOjb.moduleId=moduleId;
        elementOjb.areaId=areaId;

        elementOjb.relaId=relaId;
        elementOjb.elementId=elementId;
        elementOjb.elementName=elementName;
        elementOjb.bigType=bigType;
        elementOjb.elementType=elementType;
        elementOjb.versionNo=versionNo;
        elementOjb.terminalType=terminalType;
        elementOjb.imageUrl=imageUrl;
        elementOjb.elementLink=elementLink;
        elementOjb.elementContent=elementContent;
        elementOjb.onlineTime=onlineTime;
        elementOjb.offlineTime=offlineTime;
        elementOjb.order=order;

        $.ajax({
            type: "post",
            url: "/admin/homeTemplate/saveElement.jhtml",
            data: {
                element:JSON.stringify(elementOjb),
                c: Math.round(Math.random() * 10000)
            },
            success: function (data) {
                var jsonobj = typeof (data) == "string" ? eval("(" + data + ")") : data;
                if(jsonobj.success){
                    elementFun.generate("success",jsonobj.message);
                }else{
                    elementFun.generate("error",jsonobj.message);
                    return false;
                }

                elementFun.loadElementList($("#moduleRelaId").val());
            },
            error: function (XMLHttpRequest, textStatus, errorThrown) {
                elementFun.generate("error",textStatus);
            },
            complete:function(){
                $("#saveEle").attr("disabled",false);
                $("#saveEle").on("click",elementFun.saveElement);
            }
        });
    },
    onoffLine:function(elementId,status){
        var text="";
        text=status==1?"上线":"下线";
        $.noty.closeAll();
        noty({
            text: '确定要'+text+'吗？',
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
                            url: "/admin/homeTemplate/releaseElement.jhtml",
                            data: {
                                elementId:elementId,
                                status:status,
                                c: Math.round(Math.random() * 10000)
                            },
                            success: function (data) {
                                var jsonobj = typeof (data) == "string" ? eval("(" + data + ")") : data;
                                if(jsonobj.success){
                                    elementFun.generate("success",jsonobj.message);
                                }else{
                                    elementFun.generate("error",jsonobj.message);
                                    return false;
                                }
                                
                                elementFun.loadElementList($("#moduleRelaId").val());
                            },
                            error: function (XMLHttpRequest, textStatus, errorThrown) {
                                elementFun.generate("error",textStatus);
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
    },
    deleteElement:function(elementId){
        $.noty.closeAll();
        noty({
            text: '确定删除吗？',
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
                            url: "/admin/homeTemplate/deleteElement.jhtml",
                            data: {
                                elementId:elementId,
                                c: Math.round(Math.random() * 10000)
                            },
                            success: function (data) {
                                var jsonobj = typeof (data) == "string" ? eval("(" + data + ")") : data;
                                if(jsonobj.success){
                                    elementFun.generate("success",jsonobj.message);
                                }else{
                                    elementFun.generate("error",jsonobj.message);
                                    return false;
                                }
                                
                                elementFun.loadElementList($("#moduleRelaId").val());
                            },
                            error: function (XMLHttpRequest, textStatus, errorThrown) {
                                elementFun.generate("error",textStatus);
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
    },
    check:function(){
        var elementName,imageUrl,bigType,elementType,terminalType,versionNo,areaId,
        elementLink,elementContent,order,onlineTime,offlineTime,secKillStartTime,secKillEndTime;

        elementName = $("#elementName").val();
        imageUrl = $("#imageUrl").attr("src");
        bigType = $("#bigType").val();
        elementType=$("#elementType"+bigType).val();

        elementLink = $("#elementLink").val();
        elementContent = $("#elementContent").val();
        secKillStartTime = $("#secKillStartTime").val();
        secKillEndTime = $("#secKillEndTime").val();

        versionNo = $("#versionNo").val();
        order = $("#order").val();
        onlineTime = $("#onlineTime").val();
        offlineTime = $("#offlineTime").val();
        areaId=$("#areaId").val();

        if (elementName=="") {
            elementFun.generate("warning","请输入名称");
            $("#elementName").focus();
            return false;
        }
        if (imageUrl=="") {
            elementFun.generate("warning","请选择图片");
            return false;
        }
        if(bigType==0){
            elementFun.generate("warning","请选择元素类别");
            $("#bigType").focus();
            return false;
        }
        if($(".eletype:visible").length<=0||elementType==0){
            elementFun.generate("warning","请选择元素类型");
            $("#elementType"+bigType).focus();
            return false;
        }

        if((bigType==1&&elementType==26)||(bigType==1&&elementType==3)||bigType==2){//列表&&品牌,详情，列表&&商品列表
            elementContent = $("#elementContent").val();
            if (elementContent=="") {
                elementFun.generate("warning","请选择元素");
                $("#elementContent").focus();
                return false;
            }
        }else if(bigType==3&&elementType==6){//功能&&秒杀
            elementContent = $("#elementContent").val();
            secKillStartTime = $("#secKillStartTime").val();
            secKillEndTime = $("#secKillEndTime").val();
            if (elementContent=="") {
                elementFun.generate("warning","请选择元素类型");
                $("#elementContent").focus();
                return false;
            }
            if (secKillStartTime=="") {
                elementFun.generate("warning","请选择开始时间");
                $("#secKillStartTime").focus();
                return false;
            }
            if (secKillEndTime=="") {
                elementFun.generate("warning","请选择结束时间");
                $("#secKillEndTime").focus();
                return false;
            }
        }else if(bigType==4){//web
            elementLink = $("#elementLink").val();
            if (elementLink=="") {
                elementFun.generate("warning","请选择链接地址");
                $("#elementLink").focus();
                return false;
            }
        }
        if($("#versionNo").parent().parent().is(":visible")){
            if (versionNo=="") {
                elementFun.generate("warning","请输入版本号");
                $("#versionNo").focus();
                return false;
            }
        }
        if($("#areaName").parent().parent().is(":visible")){
            if (areaId=="") {
                elementFun.generate("warning","请选择区域");
                return false;
            }
        }
        
        if (order=="") {
            elementFun.generate("warning","请输入顺序");
            $("#order").focus();
            return false;
        }
        if (onlineTime=="") {
            elementFun.generate("warning","请输入上线时间");
            $("#onlineTime").focus();
            return false;
        }
        if (offlineTime=="") {
            elementFun.generate("warning","请输入下线时间");
            $("#offlineTime").focus();
            return false;
        }

        return true;
    },
    clearVar:function(){
        $(".edit-banner .eletype").hide();
        $(".edit-banner .eletypeattr").hide();
        $(".edit-banner .expt").hide();

        $("#relaId").val(0);
        $("#elementId").val(0);
        $("#areaId").val("");
        $("#areaName").html("");
        $("#status").val("");
        $("#imageUrl").attr("src","").parent().parent().hide();
        elementFun.reset();
    },
    reset:function(){
        var or=$("#order").val();
        $("#resetEle").click();
        if($("#order").attr("disabled")){
            $("#order").val(or);
        } 
    },
    getTerminalTypeName:function(terminalType){
        var o=$("#terminalType option[value='"+terminalType+"']");
        return typeof o.html()=="undefined"?"":o.html();
    },
    getEleStatusName:function(status){
        return status==1?"上线":"下线";
    },
    optionVisibility:function(isShow){
        return $("select option.eleoptional").each(function () {
            var $this = $(this);
            if (navigator.userAgent.indexOf('MSIE') > -1 || navigator.userAgent.indexOf('Trident') > -1) {
                if (isShow) {
                    $this.filter("span > option").unwrap();
                }else {
                    $this.filter(":not(span > option)").wrap("<span>").parent().hide();
                }
            }
            else {              
                if (isShow) {
                    $this.show();
                }else {
                    $this.hide();
                }
            }
        });
    },
    cancel:function(){
        $(".gift_box_out").hide();
    },
    sure:function(){
        var list = $("#selectIframe").contents().find("input[name=ids]:checked");
        if (list.length != 1){
            alert("亲，请选择一条记录！");
            return;
        }
        $(".gift_box_out").hide();
        var json = list.eq(0).val();
        json = json.replace(/\r\n/g,"").replace(/\n/g,"");
        var o = eval("["+json+"]")[0];

        bigType = $("#bigType").val();
        elementType=$("#elementType"+bigType).val();
        if(bigType==1&&elementType==26){//列表&&品牌
            $("#elementContent").val(o.name).focus();
        }else if(bigType==1&&elementType==3){//列表&&商品列表
            $("#elementContent").val(JSON.stringify(o)).focus();
        }else if(bigType==3&&elementType==6){//功能&&秒杀
            $("#elementContent").val(o.id).focus();
            $("#secKillStartTime").val(o.startTime);
            $("#secKillEndTime").val(o.endTime);
        }else{
            $("#elementContent").val(o.id).focus();
        }
    },
    getUrlParam:function(name){
        var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
        var r = window.location.search.substr(1).match(reg);
        if (r != null) return unescape(r[2]); return null;
    },
    generate:function(type, text){
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
    },
    dateFormat:function (date, format) {
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
}
