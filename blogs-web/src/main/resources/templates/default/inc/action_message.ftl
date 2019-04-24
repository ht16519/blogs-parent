<#--<#if msg??>-->
	<#--<div class="alert alert-danger">-->
		<#--<button type="button" class="close" data-dismiss="alert"><span aria-hidden="true">&times;</span></button>-->
		<#--${msg}-->
	<#--</div>-->
<#--</#if>-->
<#if code??>
	<#if (code == 0)>
		<div class="alert alert-success">
			<button type="button" class="close" data-dismiss="alert"><span aria-hidden="true">&times;</span></button>
			${msg}
		</div>
	<#else>
		<div class="alert alert-danger">
			<button type="button" class="close" data-dismiss="alert"><span aria-hidden="true">&times;</span></button>
			${msg}
		</div>
	</#if>
</#if>