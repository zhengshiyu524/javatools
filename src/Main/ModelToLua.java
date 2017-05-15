package Main;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import freemarker.template.Configuration;
import freemarker.template.Template;

public class ModelToLua {
	// 生成config文件
			String BaseUrl = "/Users/douzi/Desktop/unity/client/Assets/Lua/oyeahgame/";
	public class Moudle {
		public String packageName = "";
		public ArrayList<Component> components = new ArrayList<>();

		public String getPackageName() {
			return packageName;
		}

		public void setPackageName(String packageName) {
			this.packageName = packageName;
		}

		public ArrayList<Component> getComponents() {
			return components;
		}

		public void setComponents(ArrayList<Component> components) {
			this.components = components;
		}

	}

	// 组装ModuleAutoData
	@SuppressWarnings({ "unused", "unchecked" })
	public void getModuleAutoData() throws Exception {
		Map<String, ArrayList<Component>> allView = new HashMap<>();
		ArrayList<Component> allViewList = new ArrayList<>();

		for (String key : Cache.allComponent.keySet()) {
			Component component = Cache.allComponent.get(key);
			if (component.getType().equals(ComponentType.Component)) {
				StringBuilder dependPackageName = new StringBuilder();
				dependPackageName.append("'" + component.getPackageName() + "'");
				// 寻找子节点，组织依赖包
				Component comp = Cache.allSubComponent.get(key);
				if (comp != null) {
					ArrayList<Component> childs = comp.getChild();
					for (int i = 0; i < childs.size(); i++) {
						if (childs.get(i).getType().equals(ComponentType.Component)) {
							String packageId = childs.get(i).getPackageId();
							String componentSrc = childs.get(i).getSrc();
							Component childcomp = Cache.allComponent.get(packageId + "_" + componentSrc);
							childs.get(i).setName(childcomp.getName());
							
							childs.get(i).setPackageName(childcomp.getPackageName());
							Component detailcomp = Cache.allSubComponent.get(packageId + "_" + componentSrc);
							childs.get(i).setExtention(detailcomp.getExtention());
							if (childcomp == null) {
								System.out.println("有面板没导出!!!!!!!!");
							}
							if (!childcomp.getPackageName().equals(component.getPackageName())) {
								dependPackageName.append(",'" + childcomp.getPackageName() + "'");
							}
						}
					}
					component.setChild(childs);
				}
				component.setAllDependPackageName(dependPackageName.toString());
				if (allView.get(component.getPackageId() + "_" + component.getPackageName()) == null) {
					ArrayList<Component> childs = new ArrayList<>();
					childs.add(component);
					allView.put(component.getPackageId() + "_" + component.getPackageName(), childs);
				} else {
					ArrayList<Component> childs = allView
							.get(component.getPackageId() + "_" + component.getPackageName());
					childs.add(component);
					allView.put(component.getPackageId() + "_" + component.getPackageName(), childs);
				}
			}
		}
		ArrayList<Moudle> moudles = new ArrayList<>();
		// 转list
		for (String key : allView.keySet()) {
			if (allView.get(key) != null) {
				Moudle moudle = new Moudle();
				moudle.packageName = key.split("_")[1];
				moudle.components.addAll(allView.get(key));
				moudles.add(moudle);
			}

		}

		
		Configuration cfg = new Configuration();
		cfg.setClassForTemplateLoading(Main.class, "/ftl");
		Template temp = cfg.getTemplate("MoudleAutoTemplate.ftl");
		@SuppressWarnings("rawtypes")
		Map root = new HashMap();
		root.put("PackageList", moudles);


		File file_dir = new File(BaseUrl + "config/");
		if (!file_dir.exists()) {
			file_dir.mkdir();
		}
		File file = new File(BaseUrl + "config/" + "ModuleConfigAuto.lua");
		System.out.println("生成" +"ModuleConfigAuto");
		System.out.println("</br>");
		
		if (!file.exists()) {
			file.createNewFile();
		}
		temp.process(root, new FileWriter(file));
	}

	public void getControllerData() throws Exception {
		ArrayList<Component> allViewList = new ArrayList<>();
		for (String key : Cache.allComponent.keySet()) {
			Component component = Cache.allComponent.get(key);
			if (component.getType().equals(ComponentType.Component)) {
				StringBuilder dependPackageName = new StringBuilder();
				dependPackageName.append("'" + component.getPackageName() + "'");
				// 寻找子节点，组织依赖包
				Component comp = Cache.allSubComponent.get(key);
				if (comp != null) {
					ArrayList<Component> childs = comp.getChild();
					for (int i = 0; i < childs.size(); i++) {
						if (childs.get(i).getType().equals(ComponentType.Component)) {
							String packageId = childs.get(i).getPackageId();
							String componentSrc = childs.get(i).getSrc();
							Component childcomp = Cache.allComponent.get(packageId + "_" + componentSrc);
							
							childs.get(i).setName(childcomp.getName());
							childs.get(i).setPackageName(childcomp.getPackageName());
							
							Component detailcomp = Cache.allSubComponent.get(packageId + "_" + componentSrc);
							childs.get(i).setExtention(detailcomp.getExtention());
							if (childcomp == null) {
								System.out.println("有面板没导出!!!!!!!!");
							}
							if (!childcomp.getPackageName().equals(component.getPackageName())) {
								dependPackageName.append(",'" + childcomp.getPackageName() + "'");
							}
						}
					}
					component.setChild(childs);
				}
				if(Cache.allBaseView.get(key)!=null)
				{
					component.setWinComponent(false);
				}
				component.setAllDependPackageName(dependPackageName.toString());
				allViewList.add(component);
			}
		}

		// 写入子文件
		for (int i = 0; i < allViewList.size(); i++) {
			Component cmodel = allViewList.get(i);

			File fileDir = new File(BaseUrl + cmodel.getPackageName() + "/");
			if (!fileDir.exists()) {
				fileDir.mkdirs();
			}
			File fileViewDir = new File(BaseUrl + cmodel.getPackageName() + "/view");
			if (!fileViewDir.exists()) {
				fileViewDir.mkdirs();
			}
			File controllerFile = new File(BaseUrl + cmodel.getPackageName() + "/view/" +"/"
					+ cmodel.getName() + "Base.lua");
			if (!controllerFile.exists()) {
				controllerFile.createNewFile();
			}
			
			System.out.println("生成" + cmodel.getPackageName()  +"/"
					+ cmodel.getName()+ "base");
			System.out.println("</br>");
			
			File logicFileLua = new File(BaseUrl + cmodel.getPackageName()  +"/"
					+ cmodel.getName() + ".lua");
			String fileType = ".lua";
			if (logicFileLua.exists()) {
				fileType = ".sy";
			}
			File logicFile = new File(BaseUrl + cmodel.getPackageName()  +"/"
					+ cmodel.getName() + fileType);
			if (!logicFile.exists()) {
				logicFile.createNewFile();
			}
			
			System.out.println("生成" + cmodel.getPackageName()  +"/"
					+ cmodel.getName()+ "logic");
			System.out.println("</br>");
			
			Configuration cfg_controller = new Configuration();
			cfg_controller.setClassForTemplateLoading(ModelToLua.class, "/ftl");
			Template temp_controller = cfg_controller.getTemplate("ViewControllerTemplate.ftl");
			// 创建数据模型
			@SuppressWarnings("rawtypes")
			Map root_controller = new HashMap();
			root_controller.put("Component", cmodel);
			temp_controller.process(root_controller, new FileWriter(controllerFile));
			
			
			Configuration cfg_logic = new Configuration();
			cfg_logic.setClassForTemplateLoading(ModelToLua.class, "/ftl");
			Template temp_logic = cfg_logic.getTemplate("ViewLogicTemplate.ftl");
			// 创建数据模型
			@SuppressWarnings("rawtypes")
			Map root_logic = new HashMap();
			root_logic.put("Component", cmodel);
			temp_logic.process(root_logic, new FileWriter(logicFile));
		}

	}

	public void run() throws Exception {
		

		// 开始组装数据
		getModuleAutoData();

		// 开始组装controller数据
		getControllerData();
	}
}
