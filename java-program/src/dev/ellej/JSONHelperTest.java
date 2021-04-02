package dev.ellej;

import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class JSONHelperTest {
  private JSONHelper sut = new JSONHelper();

  @Test
  void shouldParseNestedJsonIntoMap() {
    String nestedJsonInput = "{"
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
    Map<String, ? extends Object> expected = getNestedDummyMap();
    Map<String, ? extends Object> actual = sut.parse(nestedJsonInput);

    assertEquals(expected, actual);
  }

  @Test
  void shouldParsePositiveInteger() {
    String key = "a";
    String jsonPositiveIntValue = "1";

    Map<String, ? extends Object> parsed = sut.parse(getJson(key, jsonPositiveIntValue));
    boolean isInteger = parsed.get(key) instanceof Integer;

    assertTrue(isInteger);
  }

  @Test
  void shouldParseNegativeInteger() {
    String key = "a";
    String jsonNegativeIntValue = "-1";

    Map<String, ? extends Object> parsed = sut.parse(getJson(key, jsonNegativeIntValue));
    boolean isInteger = parsed.get(key) instanceof Integer;

    assertTrue(isInteger);
  }

  @Test
  void shouldParseDouble() {
    String key = "a";
    String jsonDoubleValue = "1.234";

    Map<String, ? extends Object> parsed = sut.parse(getJson(key, jsonDoubleValue));
    boolean isDouble = parsed.get(key) instanceof Double;

    assertTrue(isDouble);
  }

  @Test
  void shouldParseString() {
    String key = "a";
    String jsonStringValue = "\"string\"";

    Map<String, ? extends Object> parsed = sut.parse(getJson(key, jsonStringValue));
    boolean isString = parsed.get(key) instanceof String;

    assertTrue(isString);
  }

  @Test
  void shouldParseEmptyString() {
    String key = "a";
    String jsonStringValue = "\"\"";

    Map<String, ? extends Object> parsed = sut.parse(getJson(key, jsonStringValue));
    boolean isString = parsed.get(key) instanceof String;

    assertTrue(isString);
  }

  @Test
  void shouldParseBoolean() {
    String key = "a";
    String jsonBooleanValue = "true";

    Map<String, ? extends Object> parsed = sut.parse(getJson(key, jsonBooleanValue));
    boolean isBoolean = parsed.get(key) instanceof Boolean;

    assertTrue(isBoolean);
  }

  @Test
  void shouldParseObject() {
    String key = "a";
    String jsonObjectValue = "{ \"b\": 1 }";

    Map<String, ? extends Object> parsed = sut.parse(getJson(key, jsonObjectValue));
    boolean isObject = parsed.get(key) instanceof Map;

    assertTrue(isObject);
  }

  @Test
  void shouldParseNull() {
    String key = "a";
    String jsonNullValue = "null";

    Map<String, ? extends Object> parsed = sut.parse(getJson(key, jsonNullValue));
    Object actual = parsed.get(key);

    assertNull(actual);
  }

  @Test
  void shouldDetectIncorrectType() {
    String key = "a";
    String jsonIntValue = "1";

    Map<String, ? extends Object> parsed = sut.parse(getJson(key, jsonIntValue));
    Object actual = parsed.get(key);
    boolean isBoolean = actual instanceof Boolean;
    boolean isString = actual instanceof String;
    boolean isObject = actual instanceof Map;
    boolean isDouble = actual instanceof Double;

    assertFalse(isBoolean);
    assertFalse(isString);
    assertFalse(isObject);
    assertFalse(isDouble);
    assertNotNull(actual);
  }

  @Test
  void shouldStringifyNestedMapIntoJson() {
    String expected = "{"
            + "\n\t\"a\": 1,"
            + "\n\t\"b\": true,"
            + "\n\t\"c\": {"
            + "\n\t\t\"d\": {"
            + "\n\t\t\t\"f\": 3,"
            + "\n\t\t\t\"g\": 2,"
            + "\n\t\t\t\"h\": \"\""
            + "\n\t\t},"
            + "\n\t\t\"e\": \"test\""
            + "\n\t}"
            + "\n}";
    String actual = sut.stringify(getNestedDummyMap());

    assertTrue(areCorrespondingJson(expected, actual));
  }

  @Test
  void shouldStringifyFlattenedMapIntoJson() {
    String expected = "{"
            + "\n\t\"a\": 1,"
            + "\n\t\"b\": true,"
            + "\n\t\"c.d.f\": 3,"
            + "\n\t\"c.d.g\": 2,"
            + "\n\t\"c.d.h\": \"\","
            + "\n\t\"c.e\": \"test\""
            + "\n}";
    String actual = sut.stringify(getFlattenedDummyMap());

    assertTrue(areCorrespondingJson(expected, actual));
  }

  private boolean areCorrespondingJson(String expected, String actual) {
    List<String> expectedLines = new ArrayList<>(Arrays.asList(expected.split("\n")));
    String[] actualLines = actual.split("\n");

    if (expectedLines.size() != actualLines.length)
      return false;

    for (String line : actualLines) {
      if (!expectedLines.contains(line))
        return false;

      expectedLines.remove(line);
    }

    return true;
  }

  private String getJson(String key, String value) {
    return "{"
          + "  \"" + key + "\": " + value
          + "}";
  }

  private Map<String, ? extends Object> getNestedDummyMap() {
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

  private Map<String, ? extends Object> getFlattenedDummyMap() {
    Map<String, ? super Object> inputRoot = new HashMap<>();

    inputRoot.put("a", 1);
    inputRoot.put("b", true);
    inputRoot.put("c.d.f", 3);
    inputRoot.put("c.d.g", 2);
    inputRoot.put("c.d.h", "");
    inputRoot.put("c.e", "test");

    return inputRoot;
  }
}