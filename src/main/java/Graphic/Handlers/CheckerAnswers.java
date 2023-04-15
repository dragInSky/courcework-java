package Graphic.Handlers;

import crossword.Tuple;
import crossword.Word;
import java.util.List;
import javax.swing.JTextField;

public class CheckerAnswers {

  public int countOfRightAnswers(final int N, final int M, final JTextField[][] cells,
      List<Word> wordsInformation) {
    int count = 0;
    for (Word word : wordsInformation) {
      boolean orientation = word.orientation();
      boolean flag = true;
      Tuple cell = word.tuple();
      int x = cell.x();
      int y = cell.y();
      if (orientation) {
        //вертикаль
        for (int i = 0; i < word.word().length(); i++) {
          System.out.println(cells[x + i][y].getText() + " x+i = " + (x + i) + " y = " + y);
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
          if (!cells[x][y + i].getText().equals("")) {
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

