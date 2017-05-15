package Main;

import java.util.ArrayList;

public class Component {
	private String packageId;
	private String packageName;
	private String xmlPath;
	private String size;
	private String id;
	private String ui;
	public String getUi() {
		return ui;
	}
	public void setUi(String ui) {
		this.ui = ui;
	}
	public String getOldName() {
		return oldName;
	}
	public void setOldName(String oldName) {
		this.oldName = oldName;
	}
	private String name;
	private String oldName = "";  //预留字段保存该组件在嵌套组件中的名字
	private String type;
	private String src;
	private boolean isWinComponent = false;
	private String extention = "";
	public String getExtention() {
		return extention;
	}
	public void setExtention(String extention) {
		this.extention = extention;
	}
	public boolean isWinComponent() {
		if(this.getSize().equals("640,960"))
		{
			return true;
		}
		return false;
	}
	public void setWinComponent(boolean isWinComponent) {
		this.isWinComponent = isWinComponent;
	}
	public boolean isExtComponent() {
		return isExtComponent;
	}
	public void setExtComponent(boolean isExtComponent) {
		this.isExtComponent = isExtComponent;
	}
	private boolean isExtComponent = false;
	private ArrayList<Component> child = new ArrayList<>(); //所有子节点
	private String allDependPackageName; //所有依赖包名
	public String getAllDependPackageName() {
		return allDependPackageName;
	}
	@Override
	public String toString() {
		return "Component [packageId=" + packageId + ", packageName=" + packageName + ", xmlPath=" + xmlPath + ", size="
				+ size + ", id=" + id + ", name=" + name + ", type=" + type + ", src=" + src + ", child=" + child
				+ ", allDependPackageName=" + allDependPackageName + "]";
	}
	public void setAllDependPackageName(String allDependPackageName) {
		this.allDependPackageName = allDependPackageName;
	}
	public String getPackageId() {
		return packageId;
	}
	public void setPackageId(String packageId) {
		this.packageId = packageId;
	}
	public String getPackageName() {
		return packageName;
	}
	public String getSrc() {
		return src;
	}
	public void setSrc(String src) {
		this.src = src;
	}
	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}
	public String getXmlPath() {
		return xmlPath;
	}
	public void setXmlPath(String xmlPath) {
		this.xmlPath = xmlPath;
	}
	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public ArrayList<Component> getChild() {
		return child;
	}
	public void setChild(ArrayList<Component> child) {
		this.child = child;
	}
	
}
