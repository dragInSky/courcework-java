package Graphic;

import static Graphic.AdditionalClasses.DeleteActionWrapper.removeBEEP;

import Graphic.AdditionalClasses.BeautifulButton;
import Graphic.AdditionalClasses.JTextFieldLimit;
import Graphic.Handlers.EventButtonHandler;
import Graphic.Handlers.EventKeyboardHadler;
import Graphic.Handlers.ScreenSettingsManager;
import Graphic.Handlers.ScrollManager;
import crossword.Tuple;
import crossword.Word;
import generation.Generator;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.text.BadLocationException;


public class GamePanel extends JFrame {

  private static final StringBuilder verticalDescription = new StringBuilder();
  private static final StringBuilder gorizontalDescription = new StringBuilder();

  public GamePanel() throws InterruptedException, BadLocationException {
    super("Кроссворд");

    Generator generator = Generator.getInstance();
    String[][] crossword = generator.getCrossword();
    Tuple size = generator.getSize();
    List<Word> wordsInformation = generator.getWordsInformation();

    JLabel testLabel = new JLabel(
        "1)эластичный продольный тяж, который является осевым скелетом предковых и некоторых современных форм животных организмов.");
    String[] descriptionsVertical = {
        testLabel.getText(),
        "2)инструмент для черчения окружностей и дуг, также может быть использован для измерения расстояний, в частности, на картах.",
        "3)отрезок, соединяющий центр окружности (или сферы) с любой точкой, лежащей на окружности (или сфере), а также длина этого отрезка. Радиус составляет половину диаметра.",
        "4)отрезок, соединяющий две точки на окружности и проходящий через центр окружности, а также длина этого отрезка"};
    final int M = size.x();
    final int N = size.y();

    ScreenSettingsManager screenSettingsManager = new ScreenSettingsManager();
    screenSettingsManager.setSizeOfMainFrame(this);
    screenSettingsManager.centerFrame(this);

    setDefaultCloseOperation(EXIT_ON_CLOSE);
    JPanel mainGrid = new JPanel(new GridLayout(0, 2, 0, 0));

    JPanel crosswordPanel = new JPanel();

    crosswordPanel.setLayout(new GridBagLayout());
    GridBagConstraints constraints = new GridBagConstraints();
    JTextField[][] cells = new JTextField[N][M];
    fillTheCells(crossword, N, M, cells, crosswordPanel, constraints);
    fillDescriptions(wordsInformation, cells);
    new EventKeyboardHadler().handleEventFromKeyboard(crossword, N, M, cells);

    JButton sendAnswers = BeautifulButton.getInstance();

    new EventButtonHandler().handleEventFromButtonClickToSendAnswers(crossword, N, M, cells,
        sendAnswers);

    JPanel rightPanel = new JPanel(new GridLayout(2, 0));
    JPanel topRightPanel = new JPanel(new GridLayout(0, 2));
    JPanel bottomRightPanel = new JPanel(new GridLayout(0, 2));

    ScrollManager scrollManager = new ScrollManager();
    topRightPanel.add(
        scrollManager.getScrollerOnDescriptionPanels(GamePanel.verticalDescription.toString()));
    topRightPanel.add(
        scrollManager.getScrollerOnDescriptionPanels(GamePanel.gorizontalDescription.toString()));

    bottomRightPanel.add(sendAnswers);
    bottomRightPanel.add(getTimePanel());

    rightPanel.add(topRightPanel);
    rightPanel.add(bottomRightPanel);
    mainGrid.add(crosswordPanel);
    mainGrid.add(rightPanel);
    getContentPane().add(mainGrid);
    //pack();
    setVisible(true);
  }

  public void fillTheCells(final String[][] crossword, final int N, final int M,
      final JTextField[][] cells, final JPanel grid, final GridBagConstraints constraints) {
    for (int i = 0; i < N; i++) {
      for (int j = 0; j < M; j++) {
        final JTextField inputCEll = new JTextField(3);
        inputCEll.setDocument(new JTextFieldLimit(1));
        removeBEEP(inputCEll);
        if (!crossword[i][j].equals("_")) {
          inputCEll.setFont(new Font("Serif", Font.BOLD, 15));
        } else {
          inputCEll.setFont(new Font("Serif", Font.BOLD, 15));
          inputCEll.setBackground(Color.BLACK);
          //туда нельзя ходить
        }
        cells[i][j] = inputCEll;
        constraints.gridx = j;
        constraints.gridy = i;

        grid.add(inputCEll, constraints);
      }

    }
  }

  public JLabel getTimePanel() {
    final JLabel timeDisplayer = new JLabel("", SwingConstants.CENTER);
    timeDisplayer.setFont(new Font("Arial", Font.BOLD, 23));

    Timer timer = new Timer(1000, e -> {
      if (count > 0) {
        timeDisplayer.setText("Осталось " + count-- + " сек");
      } else {
        timeDisplayer.setText("Game over");
      }
    });
    timer.start();
    return timeDisplayer;

  }

  public void fillDescriptions(List<Word> wordsInformation, JTextField[][] cells) {
    int count = 0;
    for (Word word : wordsInformation) {
      boolean orient = word.orientation();
      if (orient) {
        gorizontalDescription.append("Описание слов по горизонтали\n").append(++count).append(") ")
            .append(
                word.repr());
      } else {
        verticalDescription.append("Описание слов по вертикали\n").append(++count).append(") ")
            .append(
                word.repr());
      }
      cells[word.tuple().y()][word.tuple().x()].setText(String.valueOf(count));
    }
  }

  private volatile int count = 180;


  public static void main(String[] args) throws InterruptedException, BadLocationException {
    new GamePanel();
  }


}

