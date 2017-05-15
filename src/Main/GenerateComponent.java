package Main;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.Map;

import javax.lang.model.element.TypeElement;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

public class GenerateComponent {
	public void run(String filePath) throws Exception {
		
		ParseXml tools = new ParseXml();
		Map<String, String> xmlMap = tools.run(filePath);
		String packageXml = xmlMap.get("package.xml");
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = dbf.newDocumentBuilder();
		Document doc = db.parse(new InputSource(new StringReader((packageXml.trim()))));
		Element root = doc.getDocumentElement();
		String mapKey = root.getNodeName();

		NodeList nodeList = root.getElementsByTagName("resources");
		String packageid = root.getAttribute("id");
		String packagename = root.getAttribute("name");

		int count = 0;
		for (int i = 0; i < nodeList.getLength(); i++) {
			for (int j = 0; j < nodeList.item(i).getChildNodes().getLength(); j++) {

				Node item = nodeList.item(i).getChildNodes().item(j);
				if (item instanceof Element) {
					Component component = new Component();
					Element com = (Element) item;
					String componentSrc = com.getAttribute("id");
					String componentName = com.getAttribute("name");
					String componentType = com.getTagName();
					String componentSize = com.getAttribute("size");

					component.setPackageId(packageid);
					component.setPackageName(packagename);
					component.setSrc(componentSrc);
					component.setName(componentName);
					component.setType(componentType);
					component.setUi("ui://" + packageid + com.getAttribute("id"));
					component.setSize(componentSize);
					component.setXmlPath(com.getAttribute("id") + ".xml");
					Cache.allComponent.put(packageid + "_" + componentSrc, component);
					Cache.packageId = packageid;
					Cache.packageName = packagename;
					count++;
				}
			}

		}
		System.out.println("发现包" + packagename + count + "个节点");
		System.out.println("</br>");
	}

}
