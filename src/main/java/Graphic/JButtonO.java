package Graphic;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class JButtonO extends JFrame {

  String[] values = {"henry", "Michael", "Uche", "John", "Ullan", "Nelly",
      "Ime", "Lekan", "Austine", "jussi", "Ossi", "Imam", "Empo", "Austine", "Becky",
      "Scholar", "Ruth", "Anny"};

  public JButtonO() {
    super("the button");
    this.setSize(400, 200);
    JPanel panel = new JPanel();
    JLabel label = new JLabel("Output Items:");
    label.setAlignmentX(1);
    label.setAlignmentY(1);
    JList conList = new JList(values);
    conList.setVisibleRowCount(5);
    JScrollPane scroller = new JScrollPane(conList);
    panel.add(label);
    panel.add(scroller);

    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.add(panel);
    this.setVisible(true);


  }

  public static void main(String[] args) {
    new JButtonO();
  }
}
