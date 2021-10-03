# JSON Flattener

A Java program taking JSON input and outputting a flattened JSON object.

## Prerequisites

* Install Java Development Kit (JDK)
  * Includes:
    * Java Compiler - For compiling Java source code
    * Java Runtime Environment (JRE) & Java Virtual Machine (JVM) - For running Java byte code
  * Downloads & Platform Specific Instructions:
    * [Oracle JDK Installation Guide](https://docs.oracle.com/en/java/javase/16/install/overview-jdk-installation.html#GUID-8677A77F-231A-40F7-98B9-1FD0B48C346A)

## Compile and Run the Program

1. Open a terminal window (e.g. GitBash) and change the directory to where you want to place the cloned repo.
2. Clone the repo
```bash
# using ssh
git clone git@github.com:elle-j/JSON-Flattener.git

# using https
git clone https://github.com/elle-j/JSON-Flattener.git
```

3. Change directory to the source code directory
```bash
cd ./JSON-Flattener/java-program/src/
```

4. Compile the source code
```bash
javac -cp . ./dev/ellej/*.java
```

5. Run the program
```bash
# replace <json_object> with a valid JSON object
java dev.ellej.Main <json_object>

# example 1:
java dev.ellej.Main '{ "a": 1, "b": true, "c": { "d": 3, "e": "test" } }'

# example 2:
cat /path/to/file.json | java dev.ellej.Main
```

## Assumptions

* The program will always receive an input when run
* The input will always be a valid JSON object
* The JSON object will not contain arrays
