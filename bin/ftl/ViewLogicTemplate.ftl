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
local ${Component.name} = class("${Component.name}", import(".view.${Component.name}Base"))
function ${Component.name}:ctor()
	${Component.name}.super.ctor(self)
	
end

function ${Component.name}:OnInit()
	${Component.name}.super.OnInit(self)
	
end

<#list Component.getChild() as child>

<#if child.extention == "Button">
<#if child.isExtComponent() == true>
function ${Component.name}:${child.oldName}Click()
	--todo
	print("on ${child.oldName}Click()--------------")
end
<#else>
function ${Component.name}:${child.name}Click()
	--todo
	print("on ${child.name}Click()--------------")
end
</#if>
</#if>	
</#list>

return ${Component.name}














