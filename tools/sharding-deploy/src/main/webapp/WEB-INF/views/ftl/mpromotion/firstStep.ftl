


<div id="firstStepDiv">
	<form id="baseInfoForm" class="sui-form form-horizontal" novalidate="novalidate">
		<div class="control-group">
			<label class="control-label">活动名称：</label>
			<div class="controls">
				<input type="text" class="input-xlarge input-fat" name="name">
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">活动平台：</label>
			<div class="controls">
				<label class="checkbox-pretty inline">
					<input type="checkbox" value="1" name="supportPlatFormApp" ><span>App</span>
				</label>
				<label class="checkbox-pretty inline">
					<input type="checkbox" value="2" name="supportPlatFormWeb" ><span>Web</span>
				</label>
			</div>
		</div>
		<div data-toggle="datepicker" class="control-group input-daterange">
			<label class="control-label">活动周期：</label>
			<div class="controls">
				<input type="text" name="startTime" class="input-fat input-date"><span>-</span>
				<input type="text" name="endTime"  class="input-fat input-date">
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">活动图片：</label>
			<div class="controls fileupload">
				<input type="text" id="imageUrl" name="imageUrl" class="input-xlarge"/>
				<a href="javascript:;" id="filePicker" class="sui-btn btn-primary">上传图片</a>
				<img id="imgTemp" style="margin-top:16px; display:inherit;"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">促销形式：</label>
			<div class="controls">
				<label class="radio-pretty inline checked">
					<input type="radio" name="style" checked="checked" value="1"><span>优惠价</span>
				</label>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"></label>
			<div class="controls">
				<button id="nextBtn" type="button" class="sui-btn btn-primary btn-xlarge">下一步,活动商品设置</button>
			</div>
		</div>
	</form>
</div>
