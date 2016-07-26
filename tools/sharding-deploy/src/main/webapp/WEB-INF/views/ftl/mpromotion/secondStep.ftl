<div id="secondStepDiv" style="display: none">
	<ul id="secondStepDivDateList" class="sui-nav nav-tabs mb0">
	</ul>
	<div class="tab-content tab-wraped mh600" style="background-color: #fff;overflow:auto">
		<div id="index" class="tab-pane active">
			<form class="sui-form form-horizontal shopAddForm" style="display: inline-table;width: 100%;">

			</form>
		</div>
		<div class="control-group" style="100%">
			<div style="margin: 0 auto;width: 290px;">
				<label class="control-label"></label>
				<div class="controls">
					<button type="button" id="add_Date" class="sui-btn btn-primary btn-xlarge">新增</button>
					<button type="button" id="saveShopInfo" class="sui-btn btn-primary btn-xlarge">保存当前页</button>
					<button type="submit" id="" class="sui-btn btn-primary btn-xlarge">提交活动</button>
				</div>
			</div>
		</div>
	</div>
</div>
<!-- 时间模版 -->
<div class="add_Date" style="display: none;">
	<div class="control_Date">
		<div class="control-group">
			<label class="control-label">活动时间：</label>
			<div class="controls">
				<select name="beginTime" style="width: 130px;">
					<option value="00">00</option><option value="01">01</option><option value="02">02</option><option value="03">03</option><option value="04">04</option><option value="05">05</option>
					<option value="06">06</option><option value="07">07</option><option value="08">08</option><option value="09">09</option><option value="10">10</option><option value="11">11</option>
					<option value="12">12</option><option value="13">13</option><option value="14">14</option><option value="15">15</option><option value="16">16</option><option value="17">17</option>
					<option value="18">18</option><option value="19">19</option><option value="20">20</option><option value="21">21</option><option value="22">22</option><option value="23">23</option>
				</select> -
				<select name="endTime" style="width: 130px;">
					<option value="00">00</option><option value="01">01</option><option value="02">02</option><option value="03">03</option><option value="04">04</option><option value="05">05</option>
					<option value="06">06</option><option value="07">07</option><option value="08">08</option><option value="09">09</option><option value="10">10</option><option value="11">11</option>
					<option value="12">12</option><option value="13">13</option><option value="14">14</option><option value="15">15</option><option value="16">16</option><option value="17">17</option>
					<option value="18">18</option><option value="19">19</option><option value="20">20</option><option value="21">21</option><option value="22">22</option><option value="23">23</option>
				</select>
			</div>
			<label class="control-label">
                <a class="deleteControl"><img src="../../../resources/sui/img/delete.png" /></a>
            </label>
		</div>
		<div class="control-group">
			<label class="control-label">活动商品：</label>
			<div class="controls" style="position: relative;">
				<button id="J_addsuppliers" data-toggle="modal" data-target="#J_addsuppliersDialog" data-width="large" data-backdrop="static" class="sui-btn btn-xlarge btn-primary J_addsuppliers">选择商品</button>
			</div>
		</div>
		<div class="control-group shopTab" style="width: 100%;">
			<table class='sui-table table-bordered-simple' style="display: none;">
				<thead>
					<tr>
						<th class='center aligned'>商品信息</th>
						<th class='center aligned'>商城价</th>
						<th class='center aligned'>商品库存</th>
						<th class='center aligned'>前台展示名称</th>
						<th class='center aligned'>促销价</th>
						<th class='center aligned'>促销数量</th>
						<th class='center aligned'>单个用户限购</th>
						<th class='center aligned'>操作</th>
					</tr>
				</thead>
				<tbody>

				</tbody>
			</table>
		</div>
	</div>
</div>
<!-- 选择商品模版 -->
<div id="J_addsuppliersDialog" role="dialog" class="sui-modal hide fade">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" data-dismiss="modal" aria-hidden="true" class="sui-close">×</button>
				<h4 id="myModalLabel" class="modal-title">商品选择</h4>
			</div>
			<div class="modal-body sui-form form-horizontal">
				<form class="sui-form form-horizontal ui-sortable" id="form_shop">
					<div class="control-group">
						<label class="control-label v-top wt">商品名称：</label>
						<div class="controls">
							<input type="text" value="" name="itemName">
						</div>
						<label class="control-label v-top wt">价格：</label>
						<div class="controls">
							<input type="text" value="" name="minSalePrice" placeholder="最小价格">-<input type="text" value="" name="maxSalePrice" placeholder="最大价格">
						</div>
						<label class="control-label v-top ">商品类目：</label>
						<div class="controls">
							<input id="citySel" type="text" readonly value="" style="width:162px;" />
							<input type="hidden" name="categoryId" value="" />
						</div>
					</div>
					<div class="control-group">
						<label class="control-label v-top wt">商品编码：</label>
						<div class="controls">
							<input type="text" value="" name="itemSN">
						</div>
						<label class="control-label v-top wt">库存：</label>
						<div class="controls">
							<input type="text" value="" name="minLimitNum">-<input type="text" value="" name="maxLimitNum">
						</div>
						<label class="control-label v-top ">店铺名称：</label>
						<div class="controls">
							<span class="sui-dropdown dropdown-bordered select"><span class="dropdown-inner"><a id="select" role="button" href="javascript:void(0);" data-toggle="dropdown" class="dropdown-toggle">
                                <input value="" name="city" type="hidden"><i class="caret"></i><span  class="storeShopName">选择名称</span></a>
							<ul role="menu" aria-labelledby="drop1" class="sui-dropdown-menu">
								<li id="1" role="presentation"><a role="menuitem" tabindex="-1" href="javascript:void(0);">C端商品</a></li>
								<li id="2" role="presentation"><a role="menuitem" tabindex="-1" href="javascript:void(0);">B端商品</a></li>
							</ul>
							</span>
							</span>
						</div>
					</div>
					<div class="control-group" style="width: 100%;">
						<div style="margin: 0 auto; width: 160px;">
							<button type="button" id="queryShopBtn" class="sui-btn btn-primary btn-xlarge">查询</button>
							<button type="button" id="restform" class="sui-btn btn-primary btn-xlarge" style="margin-left: 12px;">重置</button>
						</div>
					</div>
				</form>
				<div class="control-group">
					<input type="checkbox" class="allcheckbox"><span>全选</span>
					<button type="button" id="btn-submit" data-dismiss="modal" aria-hidden="true" class="sui-btn btn-primary btn-xlarge" style="margin-left: 12px;">选择商品</button>
				</div>
				<div id="tableTemplate">
					<table class="sui-table table-bordered-simple shopTab">
						<thead>
							<tr>
								<th class='center aligned'>&nbsp;</th>
								<th class='center aligned'>商品信息</th>
								<th class='center aligned'>售卖价</th>
								<th class='center aligned'>在售库存</th>
								<th class='center aligned'>创建时间</th>
								<th class='center aligned'>最新上架时间</th>
							</tr>
						</thead>
						<tbody id="shopList">

						</tbody>
					</table>
					<div class="sui-pagination pagination-large" id="pagetest" style="text-align: right;">

					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<!--
	树形容器
-->
<div id="menuContent" class="menuContent" style="display:none; position: absolute;z-index: 9999;">
	<ul id="treeDemo" class="ztree" style="margin-top:0; width:160px;"></ul>
</div>