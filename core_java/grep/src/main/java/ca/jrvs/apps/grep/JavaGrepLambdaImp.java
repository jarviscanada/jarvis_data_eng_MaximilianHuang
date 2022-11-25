package ca.jrvs.apps.grep;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class JavaGrepLambdaImp extends JavaGrepImp {

  public static void main(String[] args) {
    if (args.length != 3) {
      throw new IllegalArgumentException("USAGE: JavaGrep regex rootPath outFile");
    }

    JavaGrepImp javaGrepLambdaImp = new JavaGrepLambdaImp();
    javaGrepLambdaImp.setRegex(args[0]);
    javaGrepLambdaImp.setRootPath(args[1]);
    javaGrepLambdaImp.setOutFile(args[2]);

    try {
      javaGrepLambdaImp.process();
    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }

  @Override
  public void process() throws IOException {
    Stream<File> fileList = listFilesToStream(getRootPath())
        .filter(Files::isRegularFile)
        .map(Path::toFile);
    Stream<String> matchedLines = fileList
        .map(this::readLines)
        .flatMap(Collection::stream);
    writeToFileFromStream(matchedLines);
  }

  public Stream<Path> listFilesToStream(String rootDir) {
    Path dir = Paths.get(rootDir);
    try {
      return Files.walk(dir);
    } catch (IOException ex) {
      logger.error("Error: unable to open directory" + ex);
      ex.printStackTrace();
      return null;
    }
  }

  @Override
  public List<String> readLines(File inputFile) {
    try (BufferedReader bufferedReader = Files.newBufferedReader(inputFile.toPath())) {
      return bufferedReader
          .lines()
          .filter(this::containsPattern)
          .collect(Collectors.toList());
    } catch (IOException ex) {
      logger.error("Error: unable to read file" + ex);
      ex.printStackTrace();
      return null;
    }
  }

  public void writeToFileFromStream(Stream<String> lines) throws IOException {
    try (BufferedWriter bufferedWriter = Files.newBufferedWriter(Paths.get(getOutFile()))) {
      lines.forEach(line -> {
        try {
          bufferedWriter.write(line);
          bufferedWriter.newLine();
        } catch (IOException ex) {
          logger.error("Error: unable to write line" + line);
          ex.printStackTrace();
        }
      });
    }
  }
}
