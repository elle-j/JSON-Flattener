package dev.ellej;

import java.util.HashMap;
import java.util.Map;

public class Main {

  public static void main(String[] args) {
    var flattener = new Flattener();
    Map<String, ? extends Object> output = flattener.flatten(getDummyMap());
    for (Map.Entry entry : output.entrySet())
      System.out.println(entry.getKey() + ": " + entry.getValue());
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
