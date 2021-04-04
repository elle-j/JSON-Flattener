package dev.ellej;

import java.util.HashMap;
import java.util.Map;

public class Flattener {
  private final String DEFAULT_DELIMITER = ".";
  private String delimiter;

  public Flattener() {
    setDelimiter(DEFAULT_DELIMITER);
  }

  public Flattener(String delimiter) {
    setDelimiter(delimiter);
  }

  public Map<String, ? extends Object> flatten(Map<String, ? extends Object> input) {
    Map<String, ? super Object> output = new HashMap<>();
    flatten("", input, output);

    return output;
  }

  private void flatten(String keyPath, Map<String, ? extends Object> input, Map<String, ? super Object> output) {
    for (String key : input.keySet()) {
      String updatedKeyPath = getKeyPath(keyPath, key);
      Object value = input.get(key);
      if (isNestedObject(value))
        flatten(updatedKeyPath, (Map) value, output);
      else
        output.put(updatedKeyPath, value);
    }
  }

  private String getKeyPath(String previousPath, String key) {
    return isRoot(previousPath) ? key : previousPath + delimiter + key;
  }

  private boolean isRoot(String path) {
    return path.length() == 0;
  }

  private boolean isNestedObject(Object obj) {
    return obj instanceof Map;
  }

  private void setDelimiter(String delimiter) {
    if (delimiter.length() < 1)
      throw new IllegalArgumentException("Delimiter must contain at least 1 character.");

    this.delimiter = delimiter;
  }
}
