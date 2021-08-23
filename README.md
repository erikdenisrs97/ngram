# NGram

Neste repositório existem 3 implementações distintas de um algoritmo que gera frequências ordenadas dos N-Grams.

### n-gram1

1. Utiliza a classe Scanner para ler arquivo;
2. Separa a String baseada no **N** escolhido com o método `concat`;
3. Carrega em uma List do tipo String;
4. Percorre a List, instancia objetos Word e carrega numa nova List;
5. Main percorre a nova List e imprime <quantidade - palavra> na tela;

### n-gram2

1. Utiliza BufferedReader para ler arquivo;
2. Separa a String baseado no **N** escolhido com o método `append`;
3. Carrega em uma List do tipo String;
4. Strings são carregadas num LinkedHashMap;
5. Main percorre o LinkedHashMap e imprime.

### n-gram3

Desta vez, utilizei uma [biblioteca externa](https://github.com/DanielJohnBenton/Ngrams.java) para gerar N-Grams.

1. Utiliza BufferedReader para ler arquivo;
2. Separa a String por palavras e carrega num ArrayList utilizando o método `sanitiseToWords`;
3. Concatena as Strings baseado no **N** escolhido e retorna um novo ArrayList utilizando o método `ngrams`;
4. A Strings são carregadas num LinkedHashMap;
5. Main percorre o LinkedHashMap e imprime.

# Testes

Alguns testes foram implementados na implementação n-gram1.

# Desempenho (Benchmarking)

Testes de desempenho comparando os 3 algoritmos não foram feitos.

# Como Testar

1. Compile usando `javac`;
2. Execute usando `java <arquivo.txt> <N>`