return {
<#list PackageList as package>
<#if package ??>
${package.packageName} = {
<#list package.components as componentmodel>
	${componentmodel.name} = {
	    file="${componentmodel.xmlPath}",
	    className="${componentmodel.name}",
	    viewPath="oyeahgame.${package.packageName}.${componentmodel.name}",
	    controlPath="oyeahgame.${package.packageName}.view.${componentmodel.name}Base",
	    packageNames={${componentmodel.getAllDependPackageName()}},
	    ui="${componentmodel.getUi()}",
	},
</#list>
},
</#if>
</#list>
}



