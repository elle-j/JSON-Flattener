package dev.ellej;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.util.HashMap;
import java.util.Map;

/**
 * A helper for transforming a JSON object.
 */
public class JSONHelper {
  /**
   * Parse JSON into a Java Map.
   *
   * @param json - The JSON to parse.
   * @return The parsed JSON.
   */
  public Map<String, ? extends Object> parse(String json) {
    ScriptEngine engine = new ScriptEngineManager().getEngineByName("javascript");
    Map<String, ? extends Object> parsed = new HashMap<>();

    try {
      parsed = (Map) engine.eval("Java.asJSONCompatible(" + json + ")");
    }
    catch (ScriptException e) {
      e.printStackTrace();
    }

    return parsed;
  }

  /**
   * Convert parsed JSON into valid stringified JSON.
   *
   * @param parsed - The parsed JSON.
   * @return Valid JSON.
   */
  public String stringify(Map<String, ? extends Object> parsed) {
    var result = new StringBuilder();
    stringify(parsed, result, 1);

    return result.toString();
  }

  private void stringify(Map<String, ? extends Object> parsed, StringBuilder result, int depth) {
    result.append("{");

    int count = 0;
    for (String key : parsed.keySet()) {
      boolean isLast = ++count == parsed.size();

      result.append(formatNewLine("\"" + key + "\": ", depth));

      Object value = parsed.get(key);
      if (isNestedObject(value))
        stringify((Map) value, result, depth + 1);
      else
        result.append(formatValue(value));

      if (!isLast)
        result.append(",");
    }

    result.append(formatNewLine("}", depth - 1));
  }

  private String formatNewLine(String content, int depth) {
    return "\n" + getIndentation(depth) + content;
  }

  private Object formatValue(Object value) {
    return value instanceof String ? "\"" + value + "\"" : value;
  }

  private String getIndentation(int tabs) {
    var indentation = new StringBuilder();
    for (var i = 0; i < tabs; i++)
      indentation.append("\t");

    return indentation.toString();
  }

  private boolean isNestedObject(Object obj) {
    return obj instanceof Map;
  }
}
