package dev.ellej;

import java.util.HashMap;
import java.util.Map;

public class Main {

  public static void main(String[] args) {
    var jsonHelper = new JSONHelper();
    Map<String, ? extends Object> parsedInput = jsonHelper.parse(getDummyJson());

    var flattener = new Flattener();
    Map<String, ? extends Object> parsedOutput = flattener.flatten(parsedInput);

    String json = jsonHelper.stringify(parsedOutput);
    System.out.println(json);

    // Test the stringify method for nested JSON as well
    String unflattenedJson = jsonHelper.stringify(getDummyMap());
    System.out.println(unflattenedJson);
  }

  private static String getDummyJson() {
    return "{"
            + "  \"a\": 1,"
            + "  \"b\": true,"
            + "  \"c\": {"
            + "    \"d\": {"
            + "        \"f\": 3,"
            + "        \"g\": 2,"
            + "        \"h\": \"\""
            + "    },"
            + "    \"e\": \"test\""
            + "  }"
            + "}";
  }

  private static Map<String, ? extends Object> getDummyMap() {
    Map<String, ? super Object> inputRoot = new HashMap<>();
    Map<String, ? super Object> inputC = new HashMap<>();
    Map<String, ? super Object> inputD = new HashMap<>();

    inputRoot.put("a", 1);
    inputRoot.put("b", true);
    inputRoot.put("c", inputC);
    inputC.put("d", inputD);
    inputC.put("e", "test");
    inputD.put("f", 3);
    inputD.put("g", 2);
    inputD.put("h", "");

    return inputRoot;
  }
}
