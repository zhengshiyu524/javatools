package Main;

import java.util.HashMap;
import java.util.Map;

public class Cache {
	public static Map<String,Component> allComponent = new HashMap<>();
	public static Map<String,Component> allSubComponent = new HashMap<>();
	public static Map<String,Component> allBaseView = new HashMap<>();
	public static String packageName;
	public static String packageId;
}
