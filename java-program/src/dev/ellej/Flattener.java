package dev.ellej;

import java.util.HashMap;
import java.util.Map;

public class Flattener {
  public Map<String, ? extends Object> flatten(Map<String, ? extends Object> input) {
    Map<String, ? super Object> output = new HashMap<>();
    flatten("", input, output);

    return output;
  }

  private void flatten(String keyPath, Map<String, ? extends Object> input, Map<String, ? super Object> output) {
    //  pseudo code:
    //  for each key in the input map
    //    if the key path is not at the root
    //		  add a period to the path
    //    add the key to the path
    //	  if the value is a map
    //		  flatten again from the updated path
    //	  else
    //		  add the path and the value to the result map

    for (String key : input.keySet()) {
      var updatedKeyPath = new StringBuilder();
      if (keyPath.length() > 0)
        updatedKeyPath.append(keyPath + '.');
      updatedKeyPath.append(key);

      Object value = input.get(key);
      if (value instanceof Map)
        flatten(updatedKeyPath.toString(), (Map) value, output);
      else
        output.put(updatedKeyPath.toString(), value);
    }
  }
}
