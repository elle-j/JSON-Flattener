package dev.ellej;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import java.util.Map;

class FlattenerTest {
  private Flattener sut = new Flattener();

  @Test
  void shouldReturnCorrectObjectSize() {
    int expected = getFlattenedDummyMap().size();
    int actual = sut.flatten(getNestedDummyMap()).size();

    assertEquals(expected, actual);
  }

  @Test
  void shouldFlattenNestedObject() {
    Map<String, ? extends Object> expected = getFlattenedDummyMap();
    Map<String, ? extends Object> actual = sut.flatten(getNestedDummyMap());

    assertEquals(expected, actual);
  }

  @Test
  void shouldHandleEmptyObject() {
    Map<String, ? extends Object> expected = new HashMap<>();
    Map<String, ? extends Object> actual = sut.flatten(new HashMap<>());

    assertEquals(expected, actual);
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