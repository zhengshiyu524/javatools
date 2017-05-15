package Main;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

public class GenerateSubComponent {
	public void run(String filePath) throws Exception {
		
		ParseXml tools = new ParseXml();
		Map<String, String> xmlMap = tools.run(filePath);
		//每个组件详细信息
		for (Map.Entry<String, String> entry : xmlMap.entrySet()) {
			if(entry.getKey().equals("package.xml") || entry.getKey().indexOf(".xml") == -1 )
			continue;
			Component component = new Component();
			String packageXml = xmlMap.get(entry.getKey());
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document doc = db.parse(new InputSource(new StringReader((packageXml.trim()))));
			Element root = doc.getDocumentElement();
			
			String extention = root.getAttribute("extention");
			String mapKey = root.getNodeName();

			NodeList nodeList = root.getElementsByTagName("displayList");
			String src = entry.getKey().substring(0,entry.getKey().length()- 4);
			component.setSrc(src);
			component.setPackageId(Cache.packageId);
			component.setPackageName(Cache.packageName);
			component.setExtention(extention);
			ArrayList<Component> child = new ArrayList<>();
			for (int i = 0; i < nodeList.getLength(); i++) {
				for (int j = 0; j < nodeList.item(i).getChildNodes().getLength(); j++) {
					Node item = nodeList.item(i).getChildNodes().item(j);
					if (item instanceof Element) {
						Component sub = new Component();
						Element com = (Element) item;
						String componentId = com.getAttribute("id");
						String componentName = com.getAttribute("name");
						String componentType = com.getTagName();
						String componentSrc = com.getAttribute("src");
						String componentPkg= com.getAttribute("pkg");
						sub.setPackageId(Cache.packageId);
						sub.setUi("ui://" + Cache.packageId + componentSrc);
						if(componentPkg!= "")
						{	
							sub.setPackageId(componentPkg);
							sub.setUi("ui://" + componentPkg + componentSrc);
							Cache.allBaseView.put(componentPkg + "_" + componentSrc, sub);
						}
						if(componentType.equals(ComponentType.Component))
						{
							sub.setExtComponent(true);
						}
						sub.setPackageName(Cache.packageName);
						sub.setId(componentId);
						sub.setName(componentName);
						sub.setOldName(componentName);
						sub.setType(componentType);
						sub.setSrc(componentSrc);
						sub.setXmlPath(com.getAttribute("id") + ".xml");
						child.add(sub);
					}
				}
				
				component.setChild(child);
			}
			Cache.allSubComponent.put(component.getPackageId() + "_" + component.getSrc(), component);
		}

	}
}
