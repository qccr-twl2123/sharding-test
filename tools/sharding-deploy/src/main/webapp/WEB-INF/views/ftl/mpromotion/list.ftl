<!DOCTYPE html>
<html>

	<head>
		<meta charset="utf-8">
		<title>活动列表</title>
		<link rel="stylesheet" href="../../../resources/sui/css/sui.css" />
		<link rel="stylesheet" href="../../../resources/sui/css/sui-append.css" />
		<link rel="stylesheet" type="text/css" href="../../../resources/sui/custom.css" />
		<script type="text/javascript" src="../../../resources/sui/js/jquery.js"></script>
		<script type="text/javascript" src="../../../resources/sui/js/sui.js"></script>
		<script type="text/javascript" src="../../../resources/sui/js/jQuery_JSON2Str_plugin.js"></script>
		<script type="text/javascript" src="../../../resources/js/common.js"></script>
		<script type="text/javascript" src="../../../resources/js/jquery.tmpl.js"></script>
		<script type="text/javascript" src="../../../resources/js/mpromotion/list.js"></script>
	</head>
	<#noparse>
		<script id="listTableTmpl" type="text/x-jquery-tmpl">
			<tr id="${promotionId}">
				<td>${promotionId}</td>
				<td>${name}</td>
				<td>
				{{each(i,supportPlatformKey) supportPlatformList}}
					{{if supportPlatformKey==1}}
						app
					{{/if}}
					{{if supportPlatformKey==2}}
						web
					{{/if}}
				{{/each}}
				</td>
				<td>${startTime}</td>
				<td>${endTime}</td>
				<td>
					{{if status==1}}
						编辑中
					{{/if}}
					{{if status==2}}
						待审核
					{{/if}}
					{{if status==3}}
						审批不通过
					{{/if}}
					{{if status==4}}
						上线
					{{/if}}
					{{if status==5}}
						下线
					{{/if}}
				</td>
				<td>
					{{if status==1}}
						<button type="button"  class="sui-btn btn-block btn-primary btn-small">修改</button>
						<button type="button" onclick="del(${promotionId})"  class="sui-btn btn-block btn-primary btn-small">删除</button>
					{{/if}}
					{{if status==2}}
						<button type="button"  class="sui-btn btn-block btn-primary btn-small">查看详情</button>
						<button type="button"  class="sui-btn btn-block btn-primary btn-small">快速复制</button>
						<button type="button"  class="sui-btn btn-block btn-primary btn-small">修改</button>
						<button type="button" onclick="del(${promotionId})" class="sui-btn btn-block btn-primary btn-small">删除</button>
						<button type="button"  class="sui-btn btn-block btn-primary btn-small">审核</button>
					{{/if}}
					{{if status==3}}
						<button type="button"  class="sui-btn btn-block btn-primary btn-small">查看详情</button>
						<button type="button"  class="sui-btn btn-block btn-primary btn-small">快速复制</button>
						<button type="button"  class="sui-btn btn-block btn-primary btn-small">修改</button>
						<button type="button" onclick="del(${promotionId})"  class="sui-btn btn-block btn-primary btn-small">删除</button>
					{{/if}}
					{{if status==4}}
						<button type="button"  class="sui-btn btn-block btn-primary btn-small">查看详情</button>
						<button type="button"  class="sui-btn btn-block btn-primary btn-small">快速复制</button>
						<button type="button" href="" class="sui-btn btn-block btn-primary btn-small">下线</button>
					{{/if}}
					{{if status==5}}
						<button type="button"  class="sui-btn btn-block btn-primary btn-small">查看详情</button>
						<button type="button"  class="sui-btn btn-block btn-primary btn-small">快速复制</button>
					{{/if}}	
				</td>
			</tr>
		</script>
	</#noparse>

	<body>
		<div class="container">
			<div class="sui-form ui-sortable form-horizontal" action="" style="margin-left: -20px;">
				<div class="control-group float-left">
					<label class="control-label">活动名称：</label>
					<div class="controls">
						<input type="text" class="input-large input-fat" name="name">
					</div>
				</div>

				<div class="control-group float-left">
					<label class="control-label">起始时间：</label>
					<div class="controls">
						<input type="text" class="input-medium input-fat beginDate ml110" name="startTimeFrom"><span>-</span>
						<input type="text" class="input-medium input-fat beginDate ml110" name="startTimeTo">
					</div>
				</div>

				<div class="control-group float-left">
					<label class="control-label">活动平台：</label>
					<div class="controls">
						<select class="input-fat input-large supportPlatform">
							<option value="" selected="selected">请选择渠道</option>
							<option value="1">app端</option>
							<option value="2">web端</option>
						</select>
					</div>
				</div>

				<div class="control-group float-left">
					<label class="control-label">活动状态：</label>
					<div class="controls">
						<select id="" class="input-fat input-large status">
							<option value="" selected="selected">全部</option>
							<option value="0">待审核</option>
							<option value="1">上线</option>
							<option value="2">下线</option>
							<option value="3">审核不通过</option>
						</select>
					</div>
				</div>
				
				<div class="control-group float-left">
					<label class="control-label">结束时间：</label>
					<div class="controls">
						<input type="text" class="input-medium input-fat endDate ml110"  name="endTimeFrom">-
						<input type="text" class="input-medium input-fat endDate ml110"  name="endTimeTo">
					</div>
				</div>

				<div class="control-group float-left" style="margin-left: 30px;">
					<div class="controls">
						<button type="button" id="queryBtn" class="sui-btn btn-primary btn-large">查询</button>
						<button type="button" id="resetBtn" class="sui-btn btn-large">重置</button>
						<button type="button" id="addPromotion" class="sui-btn btn-large">新增活动</button>
					</div>
				</div>
			</div>
			<table class="sui-table">
				<thead>
					<tr>
						<th>活动编号</th>
						<th>活动名称</th>
						<th>活动平台</th>
						<th>开始时间</th>
						<th>结束时间</th>
						<th>活动状态</th>
						<th>操作</th>
					</tr>
				</thead>
				<tbody id="listContent">
				
				</tbody>
			</table>
			
		</div>
	</body>
</html>