package org.example;

public class Main {

  public static void main(String[] args) {
    String[] words = {"хорда", "Макс"};

    CrosswordGenerator CG = new CrosswordGenerator(words);

    CG.appendHorizontal(words[0]);
    CG.crosswordFill(words[0], words);

    CG.clearEmptyCells();
    CG.crosswordPrint();
  }
}

