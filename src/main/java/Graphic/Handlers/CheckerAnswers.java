package Graphic.Handlers;

import crossword.Tuple;
import crossword.Word;
import java.util.List;
import javax.swing.JTextField;

public class CheckerAnswers {

  public int countOfRightAnswers(final int N, final int M, final JTextField[][] cells,
      List<Word> wordsInformation) {
    int count = 0;

    System.out.println("Пришли узнать ответы");

    for (int i = 0; i < N; i++) {
      for (int j = 0; j < M; j++) {
        System.out.print(cells[i][j].getText() + " ");
      }
      System.out.println();
    }

    for (Word word : wordsInformation) {
      boolean orientation = word.orientation();
      boolean flag = true;
      Tuple cell = word.tuple();
      int x = cell.y();//макс корявый
      int y = cell.x();
      if (!orientation) {
        //вертикаль
        for (int i = 0; i < word.word().length(); i++) {
          if (!cells[x + i][y].getText().equals("")) {
            if (!(cells[x + i][y].getText().charAt(0) == (word.word().charAt(i)))) {
              flag = false;
              break;
            }
          } else {
            flag = false;
            break;
          }
        }
      } else {
        //горизонталь
        for (int i = 0; i < word.word().length(); i++) {
          System.out.println(cells[x][y + i].getText() + " x = " + x + " y+i = " + (i + y));
          if (!cells[x][y + i].getText().equals("_")) {
            if (!(cells[x][y + i].getText().charAt(0) == (word.word().charAt(i)))) {
              flag = false;
              break;
            }
          } else {
            flag = false;
            break;
          }
        }
      }
      if (flag) {
        count++;
      }
    }
    return count;
  }

}

