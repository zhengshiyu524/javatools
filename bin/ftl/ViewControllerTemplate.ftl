--
--                   _ooOoo_
--                  o8888888o
--                  88" . "88
--                  (| -_- |)
--                  O\  =  /O
--               ____/`---'\____
--             .'  \\|     |//  `.
--            /  \\|||  :  |||//  \
--           /  _||||| -:- |||||-  \
--           |   | \\\  -  /// |   |
--           | \_|  ''\---/''  |   |
--           \  .-\__  `-`  ___/-. /
--         ___`. .'  /--.--\  `. . __
--      ."" '<  `.___\_<|>_/___.'  >'"".
--     | | :  `- \`.;`\ _ /`;.`/ - ` : | |
--     \  \ `-.   \_ __\ /__ _/   .-` /  /
--======`-.____`-.___\_____/___.-`____.-'======
--                   `=---='
--^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
--     God bless shiyu and beibei
--
<#list Component.getChild() as child>
<#if child.isExtComponent() == true>
<#if child.name != "">
local ${child.name}	=	require("oyeahgame.${child.packageName}.${child.name}")
<#else>
local ${child.name}	=	require("oyeahgame.${child.packageName}.${child.name}")
</#if>
</#if>
</#list>
<#if Component.isWinComponent() = true>
local ${Component.name}Base = class("${Component.name}Base", BaseWindow)
<#else>
local ${Component.name}Base = class("${Component.name}Base", BaseView)
</#if>
function ${Component.name}Base:ctor( args)
	${Component.name}Base.super.ctor(self,ModuleConfig.${Component.packageName}.${Component.name},args)
end
function ${Component.name}Base:OnInit()
	${Component.name}Base.super.OnInit(self)
	local contentPane 			=  		self.contentPane
	<#list Component.getChild() as child>
	<#if child.isExtComponent() == true>
	self.${child.oldName} 			=	${child.name}.New(contentPane:GetChild("${child.oldName}"))                --${child.type}
	<#else>
	self.${child.oldName} 			=	contentPane:GetChild("${child.oldName}")                       --${child.type}
	</#if>
	<#if Component.isWinComponent() == true>
	<#if child.extention == "Button">
	self.${child.oldName}.onClick:Add(self.${child.oldName}Click or function() print "not implement" end,self)    --${child.extention}Click
	</#if>
	</#if>
	</#list>
end

return ${Component.name}Base















