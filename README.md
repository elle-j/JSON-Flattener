# JSON Flattener

A Java program taking JSON input and outputting a flattened JSON object.

## Prerequisites for Running the Program

  * Install Java Development Kit (JDK)
    * Includes:
      * Java Compiler - For compiling Java source code
      * Java Runtime Environment (JRE) & Java Virtual Machine (JVM) - For running Java byte code
    * Downloads & Platform Specific Instructions:
      * [Oracle JDK Installation Guide](https://docs.oracle.com/en/java/javase/16/install/overview-jdk-installation.html#GUID-8677A77F-231A-40F7-98B9-1FD0B48C346A)

## How to Compile and Run the Program

  1. Open a terminal window (e.g. GitBash) and change the directory to where you want to place the cloned repo.
  2. Clone the repo
      * Using HTTPS, run `https://github.com/elle-j/mongodb-json.git`
      * Using SSH, run `git@github.com:elle-j/mongodb-json.git` 
  3. Change directory to the source code directory
      * Run ` cd ./mongodb-json/java-program/src/`
  4. Compile the source code
      * Run `javac -cp . ./dev/ellej/*.java`
  5. Run the program
      * Run `java dev.ellej.Main <json_object>` (replace <json_object> with a valid JSON object)
        * E.g. `java dev.ellej.Main '{ "a": 1, "b": true, "c": { "d": 3, "e": "test" } }'`
        * E.g. `cat /path/to/file.json | java dev.ellej.Main`

## Assumptions

  * The program will always receive an input when run
  * The input will always be a valid JSON object
  * The JSON object will not contain arrays
