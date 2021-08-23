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
      System.out.println("You need to declare the file and the n-gram.");
      return;
    } else {
      int n = Integer.valueOf(argv[1]);
      String file = argv[0];
      ArrayList<String> words = words(file);
      ArrayList<String> ngrams = ngrams(words, n);
      for (String key : wordCounter(ngrams).keySet()) {
        int value = wordCounter(ngrams).get(key);
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
   * Using Ngrambib.ngrams. This method returns a ArrayList
   * with n-gram words.
   * @param words
   * @param n
   * @return
   */
  public static ArrayList<String> ngrams(ArrayList<String> words, int n) {
    ArrayList<String> ngrams = new ArrayList<>();
    ngrams = Ngrambib.ngrams(words, n);
    return ngrams;
  }

  /**
   * Using Ngrambib.words method. This method returns a ArrayList
   * of words based on a string. 
   * @param str
   * @return
   */
  public static ArrayList<String> words(String str) {
    ArrayList<String> words = new ArrayList<>();
    words = Ngrambib.sanitiseToWords(readFile(str));
    return words;
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
    return sb.toString().toLowerCase();
  }

}
