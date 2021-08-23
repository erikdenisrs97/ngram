/**
* Return number of words on a text file.
* You can run this program using CLI running:
* java (filename.txt) (n-gram)
* Example:
* java file.txt 2
*/

import java.io.File;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.io.IOException;

public class Ngram {
  
  public static void main(String[] argv) {
    if(argv.length < 1) {
      System.out.println("You need to declare the file and the n-gram");
      return;
    } else {
      int n = Integer.valueOf(argv[1]);
      String file = argv[0];
      for(Word word : wordCounter(ngrams(n, readFile(file)))) {
        System.out.println(word.getCount() + " - " + word.getWord());
      }
    }
  }

  /**
   * This method returns a List with words and your
   * number counted.
   * Example:
   * 1 - Bird
   * 2 - Fly
   * 1 - Black
   * @param list
   * @return
   */
  public static List<Word> wordCounter(List<String> list) {
    List<Word> words = new ArrayList<>();
    int i = 0;
    int j = 0;
    while(list.size() > 0) {
      int counter = 0;
      Word w = new Word();
      w.setWord(list.get(i));
      for(j = 0; j < list.size(); j++) {
        if(list.get(j).equals(w.getWord())) {
          counter++;
          list.remove(j);
        }
      }
      w.setCount(counter);
      words.add(w);
    }
    words.sort(Comparator.comparing(Word::getCount));
    return words;
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
    String word = "";
    for(int n = start; n < end; n++) {
      if(end == 1) {
        word = word.concat(words[n] + "");
      } else {
        word = word.concat(words[n] + " ");
      }
    }
    return word.toLowerCase();
  }

  public static String readFile(String file) {
    List<String> words = new ArrayList<>();
    try {
      File myObj = new File(file);
      Scanner myReader = new Scanner(myObj);
      while (myReader.hasNextLine()) {
        String data = myReader.nextLine();
        words.add(data);
      }
      myReader.close();
    } catch (IOException e) {
      System.out.println("There was a problem reading the file.");
      e.printStackTrace();
    }
    return words.toString().replace("[", "").replace("]", "");
  }

}