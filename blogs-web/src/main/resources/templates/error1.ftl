<#include "/default/utils/layout.ftl"/>

<@ui_simple "消息提示">

<div class="panel panel-default" style="min-height: 300px; max-width: 460px; margin: 30px auto;">
	<div class="panel-heading">提示</div>
	<div class="panel-body">
		<fieldset>
			<#if msg??>
				${msg}
			</#if>
		</fieldset>
	</div><!-- /panel-content -->
</div><!-- /panel -->
</@ui_simple>