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
    textPane.setText(phrase
        + "\n1)This is demo text4.This is demo text5. This is demo text6. "
        + "\n2)This is demo text7. This is demo text8. This is demo text9. "
        + "\n3)This is demo text10. This is demo text11. This is demo text12."
        + "\n4)This is demo text13. This is demo text13. This is demo text14."
        + "\n5)This is demo text15. This is demo text13. This is demo text16."
        + "\n6)This is demo text17. This is demo text13. This is demo text18."
        + "\n7)This is demo text19.This is demo text13.This is demo text20.");
    JScrollPane scrollPane = new JScrollPane(textPane);
    scrollPane.setVerticalScrollBarPolicy(
        javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
    return scrollPane;
  }

}
