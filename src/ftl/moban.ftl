
<#list viewList as v>
${packageName} = {
    layerTag    = l_tagEnum.game.uiTag,
    controlPath = "script.oyeahgame.${v.viewName}.control.${v.viewName}Control",
    viewPath    = "image/${v.id}",
    plist		={${v.filename}},
    isHide      = false,
    bgType      = l_bgEnum.BG_FULL,
    openSound   = l_soundEnum.OpenMusic_WINDOW,
    closeSound  = l_soundEnum.CloseMusic_CLOSE
}

</#list>

