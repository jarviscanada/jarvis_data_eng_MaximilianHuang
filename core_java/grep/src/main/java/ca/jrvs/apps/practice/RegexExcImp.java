package ca.jrvs.apps.practice;
import java.util.regex.Pattern;

public class RegexExcImp implements RegexExc {

  @Override
  public boolean matchJpeg(String filename) {
    Pattern pattern = Pattern.compile(".+\\.(jpg|jpeg)$", Pattern.CASE_INSENSITIVE);
    return pattern.matcher(filename).matches();
  }

  @Override
  public boolean matchIp(String ip) {
    return Pattern.matches("(\\d{1,3}\\.){3}\\d{1,3}", ip);
  }

  @Override
  public boolean isEmptyLine(String line) {
    return Pattern.matches("^$", line);
  }
}
