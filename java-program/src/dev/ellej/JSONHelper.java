package dev.ellej;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.util.HashMap;
import java.util.Map;

public class JSONHelper {
  private ScriptEngine engine;

  public JSONHelper() {
    engine = new ScriptEngineManager().getEngineByName("javascript");
  }

  public Map<String, ? extends Object> parse(String json) {
    Map<String, ? extends Object> parsed = new HashMap<>();

    try {
      parsed = (Map) engine.eval("Java.asJSONCompatible(" + json + ")");
    }
    catch (ScriptException e) {
      e.printStackTrace();
    }

    return parsed;
  }

  public String stringify(Map<String, ? extends Object> parsed) {
    var result = new StringBuilder();

    result.append("{");
    stringify(parsed, result, 1);
    result.append("\n}");

    return result.toString();
  }

  private void stringify(Map<String, ? extends Object> parsed, StringBuilder result, int depth) {
    int count = 0;
    for (String key : parsed.keySet()) {
      boolean isLast = ++count == parsed.size();

      result.append("\n" + getIndentation(depth) + "\"" + key + "\": ");

      Object value = parsed.get(key);
      if (value instanceof Map) {
        result.append("{");
        stringify((Map) value, result, depth + 1);
        result.append("\n" + getIndentation(depth) + "}");
      }
      else {
        if (value instanceof String)
          result.append("\"" + value + "\"");
        else
          result.append(value);
      }

      if (!isLast)
        result.append(",");
    }
  }

  private String getIndentation(int tabs) {
    var indentation = new StringBuilder();
    for (var i = 0; i < tabs; i++)
      indentation.append("\t");

    return indentation.toString();
  }
}
