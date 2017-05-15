package Main;
import java.io.File;
import java.util.Map;


public class Main {
	
	static void delFiles(String filePath) throws Exception {
		File root = new File(filePath);
		File[] files = root.listFiles();
		for (File file : files) {
			if (file.isDirectory()) {
				delFiles(file.getAbsolutePath());
			} else {
				new File(file.getAbsolutePath()).delete();
			}
		}
	}
	static void getFiles(String filePath) throws Exception {
		File root = new File(filePath);
		File[] files = root.listFiles();
		for (File file : files) {
			if (file.isDirectory()) {
				getFiles(file.getAbsolutePath());
			} else {
				if(file.getAbsolutePath().endsWith(".bytes") && file.getAbsolutePath().indexOf("@") == -1)
				{	
					//将所有的组件信息存储
					new GenerateComponent().run(file.getAbsolutePath());
					//将所有的组件详情信息存储
					new GenerateSubComponent().run(file.getAbsolutePath());
				}
			}
		}
	}

	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		//delFiles("/Users/douzi/Desktop/unity/client/Assets/UI");
		getFiles("/Users/douzi/Desktop/unity/client/Assets/UI");
		new ModelToLua().run();
		
		
		System.out.println("</br>");
		System.out.println("生成完成");
	}

}
