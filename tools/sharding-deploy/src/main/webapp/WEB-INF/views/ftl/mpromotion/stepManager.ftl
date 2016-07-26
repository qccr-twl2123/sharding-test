<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>活动步骤</title>
		<link rel="stylesheet" href="../../../resources/sui/css/sui.css" />
		<link rel="stylesheet" href="../../../resources/sui/css/sui-append.css" />
		<link rel="stylesheet" href="../../../resources/css/demo.css" />
		<link rel="stylesheet" href="../../../resources/css/zTreeStyle.css" />
		<link rel="stylesheet" type="text/css" href="../../../resources/sui/custom.css" />
		
		<script type="text/javascript" src="../../../resources/sui/js/jquery.js"></script>
		<script type="text/javascript" src="../../../resources/sui/js/sui.js"></script>
		<script type="text/javascript" src="../../../resources/sui/js/jQuery_JSON2Str_plugin.js"></script>
		
		<script type="text/javascript" src="../../../resources/js/webuploader.js"></script>
		<script type="text/javascript" src="../../../resources/js/common.js"></script>
		<script type="text/javascript" src="../../../resources/js/jquery.ztree.core.js"></script>
		<script type="text/javascript" src="../../../resources/js/ztree_select.js"></script>
		
		<script type="text/javascript" src="../../../resources/js/mpromotion/firstStep.js"></script>
		<script type="text/javascript" src="../../../resources/js/mpromotion/secondStep.js"></script>
		<script type="text/javascript" src="../../../resources/js/mpromotion/threeStep.js"></script>

		<style>
		    .fileupload input[type=file]{
		         display:none;
		    }
		</style>
		<script>
			var promotionId;
		</script>
	</head>

	<body>
		<div class="sui-container" style="width: 97%">
			<div class="sui-steps steps-large steps-auto">
					<div class="wrap">
						<div class="current" id="oneStepTip">
							<label><span class="round"><i class="sui-icon icon-pc-right"></i></span><span>第一步 活动基础信息设置</span></label><i class="triangle-right-bg"></i><i class="triangle-right"></i>
						</div>
					</div>
					<div class="wrap">
						<div class="todo" id="twoStepTip">
							<label><span class="round">2</span><span>第二步 活动商品设置</span></label><i class="triangle-right-bg"></i><i class="triangle-right"></i>
						</div>
					</div>
					<div class="wrap">
						<div class="todo" id="threeStepTip">
							<label><span class="round">3</span><span>第三步 提交成功</span></label>
						</div>
					</div>
			</div>
			<#include "/mpromotion/firstStep.ftl">
			<#include "/mpromotion/secondStep.ftl">
			<#include "/mpromotion/threeStep.ftl">
			
		</div>
		

	</body>
</html>		
			