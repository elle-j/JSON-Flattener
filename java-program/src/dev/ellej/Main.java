package dev.ellej;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;

/**
 * The entry point of the program.
 */
public class Main {
  /**
   * Exercise the functionality of the program.
   *
   * @param args - User input arguments (JSON object).
   */
  public static void main(String[] args) {
    String jsonInput = getInput(args);

    var jsonHelper = new JSONHelper();
    Map<String, ? extends Object> parsedInput = jsonHelper.parse(jsonInput);

    var flattener = new Flattener();
    Map<String, ? extends Object> parsedOutput = flattener.flatten(parsedInput);

    String jsonOutput = jsonHelper.stringify(parsedOutput);
    System.out.println(jsonOutput);
  }

  private static String getInput(String[] args) {
    // user manually provides input
    if (args.length > 0)
      return args[0];

    // user provides input via a previous output process (e.g. through piping)
    var input = new StringBuilder();
    try (var br = new BufferedReader(new InputStreamReader(System.in))) {
      String line;
      while ((line = br.readLine()) != null)
        input.append(line + "\n");
    }
    catch (IOException e) {
      System.out.println("Please provide a JSON object.");
      System.exit(0);
    }

    return input.toString();
  }
}
