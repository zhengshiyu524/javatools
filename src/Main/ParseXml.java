package Main;

import java.io.File;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import com.lz.util.FileUtil;



public class ParseXml {
	public Map<String, String> run(String filename) throws Exception {
		Map<String, String> mappingList = new HashMap<>();
		File file = new File(filename);
		InputStream st;
		String xmlStr= FileUtil.readFileToStr(filename);
		
		
		int curr = 0;
		String packageName;
		String content;
		
		int size;
		while(true)
		{
			int pos = xmlStr.indexOf("|", curr);
			if (pos == -1)
				break;
			packageName = xmlStr.substring(curr, pos);
			curr = pos + 1;
			pos = xmlStr.indexOf("|", curr);
			size = Integer.parseInt(xmlStr.substring(curr, pos));
			curr = pos + 1;
			content = xmlStr.substring(curr, curr + size);
			mappingList.put(packageName, content);
			curr += size;
		}
		
		return mappingList;
	}
}
