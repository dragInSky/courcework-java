package Graphic.Handlers;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;

public class ScrollManager {

  public JScrollPane getScrollerOnDescriptionPanels(String phrase) throws BadLocationException {
    JTextPane textPane = new JTextPane();
    Font font = new Font("Times New Roman", Font.ITALIC, 22);
    textPane.setFont(font);
    SimpleAttributeSet attributeSet = new SimpleAttributeSet();
    StyleConstants.setItalic(attributeSet, true);
    StyleConstants.setForeground(attributeSet, Color.blue);
    StyleConstants.setBackground(attributeSet, Color.white);
    textPane.setCharacterAttributes(attributeSet, true);
    textPane.setText(phrase);
    JScrollPane scrollPane = new JScrollPane(textPane);
    scrollPane.setVerticalScrollBarPolicy(
        javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
    return scrollPane;
  }

}
