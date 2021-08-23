import java.util.ArrayList;
import java.util.List;

public class Tests {

  static String txt = "Se cada um vai a casa de cada um é porque cada um quer que cada um vá lá Porque se cada um não fosse a casa de cada um é porque cada um não queria que cada um fosse lá";
  public static void main(String[] args) {
    tests();
  }

  public static void ngramTest(int n, int size) {
    List<String> ngrams = Ngram.ngrams(n, txt);
    if(ngrams.size() != size) {
      System.out.println("Teste ngram para N="+ n + " PALAVRAS=" + size + " falhou!");
      return;
    }
  }

  public static void wordCounterTest(String word, int count, int n) {
    List<Word> words = Ngram.wordCounter(Ngram.ngrams(n, txt));
    for (Word w : words) {
      if(w.getWord().equals(word)) {
        System.out.println("Teste reprovou para palavra=" + word);
        if(w.getCount() != count) {
          System.out.println("Teste reprovou para contador=" + count);
          return;
        }
      }
    }
  }

  public static void tests() {
    ngramTest(1, 41);
    ngramTest(2, 40);
    ngramTest(3, 39);
    ngramTest(4, 38);
    ngramTest(5, 37);
    ngramTest(6, 36);
    ngramTest(7, 35);
    ngramTest(8, 34);
    ngramTest(9, 33);
    ngramTest(10, 32);
    wordCounterTest("cada", 8, 1);
    wordCounterTest("um", 8, 1);
    wordCounterTest("porque", 3, 1);
    wordCounterTest("cada", 8, 2);
    wordCounterTest("cada um", 8, 2);
    wordCounterTest("a casa", 3, 2);
    wordCounterTest("casa de", 3, 2);

  }

}

