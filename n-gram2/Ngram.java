import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.SortedMap;

public class Ngram {
  
  public static void main(String[] argv) {
    if(argv.length < 1) {
      System.out.println("You need to declare the file and the n-gram");
      return;
    } else {
      int n = Integer.valueOf(argv[1]);
      String file = argv[0];
      for(String key : wordCounter(ngrams(n, readFile(file))).keySet()) {
        int value = wordCounter(ngrams(n, readFile(file))).get(key);
        System.out.println(value + " - " + key);
      }
    }
  }

  /**
   * Return a LinkedHashMap<Word, Counter>
   * @param list
   * @return
   */
  public static LinkedHashMap<String, Integer> wordCounter(List<String> list) {
    LinkedHashMap<String, Integer> words = new LinkedHashMap<>();
    LinkedHashMap<String, Integer> sortedMap = new LinkedHashMap<>();
    for (String string : list) {
      Integer count = words.get(string);
      if(count == null) {
        words.put(string, 1);
      } else {
        words.put(string, count + 1);
      }
    }

    words.entrySet().stream().sorted(SortedMap.Entry.comparingByValue())
    .forEachOrdered(x -> sortedMap.put(x.getKey(), x.getValue()));

    return sortedMap;
  }

  /**
   * Return a list of words based on specified 
   * n-gram (param n).
   * @param n
   * @param str
   * @return
   */
  public static List<String> ngrams(int n, String str) {
    List<String> ngrams = new ArrayList<>();
    String[] words = str.split(" ");
    for(int i = 0; i < words.length - n + 1; i++) {
      ngrams.add(returnWord(words, i, i + n).trim());
    }
    return ngrams;
  }

  /**
   * Return a String based on n-gram specified at ngrams method.
   * Ex for n = 2 : [Black, Eyes]
   * Return: [Black Eyes]
   * @param words
   * @param start
   * @param end
   * @return
   */
  public static String returnWord(String[] words, int start, int end) {
    StringBuilder sb = new StringBuilder();
    for(int n = start; n < end; n++) {
      sb.append((n > start ? " " : "") + words[n]);
    }
    return sb.toString().toLowerCase();
  }

  public static String readFile(String file) {
    StringBuilder sb = new StringBuilder();

    try {
      BufferedReader br = Files.newBufferedReader(Paths.get(file));
      String line;
      while ((line = br.readLine()) != null) {
        sb.append(line).append("\n");
      }
    } catch (IOException e) {
      System.out.println("There was a problem reading the file.");
    }
    return sb.toString();
  }

}
