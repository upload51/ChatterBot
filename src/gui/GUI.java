package gui;

import mind.ImitationOfTheMind;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI extends JFrame implements ActionListener {

    final String TITLE_OF_PROGRAMM = "Chatter: Bot";
    final int START_LOCATION = 200;
    final int WINDOW_WIDTH = 350;
    final int WINDOW_HIGHT = 450;
    final String CHB_AI = "AI";
    final String BTN_SAY = "Say";

    JTextPane dialogue;
    JCheckBox ai;
    JTextField message;
    ImitationOfTheMind sbot;
    SimpleAttributeSet botStyle;

    public static void main(String[] args) {
        new GUI();
    }

    GUI(){
        setTitle(TITLE_OF_PROGRAMM);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setBounds(START_LOCATION, START_LOCATION, WINDOW_WIDTH, WINDOW_HIGHT);
        //dialog
        dialogue = new JTextPane();
        dialogue.setEditable(true);
        dialogue.setContentType("text/html");
        JScrollPane scrollBar = new JScrollPane(dialogue);
        //style for the messages
        botStyle = new SimpleAttributeSet();
        StyleConstants.setItalic(botStyle, true);
        StyleConstants.setForeground(botStyle, java.awt.Color.blue);
        StyleConstants.setAlignment(botStyle, StyleConstants.ALIGN_RIGHT);
        //panel with checkbox, message field and button
        JPanel mb = new JPanel();
        mb.setLayout(new BoxLayout(mb, BoxLayout.X_AXIS));
        ai = new JCheckBox(CHB_AI);
        ai.doClick();
        message = new JTextField();
        message.addActionListener(this);
        JButton say = new JButton(BTN_SAY);
        say.addActionListener(this);
        //adding all elements to the chat window
        mb.add(ai);
        mb.add(message);
        mb.add(say);
        add(BorderLayout.CENTER, scrollBar);
        add(BorderLayout.SOUTH, mb);
        setVisible(true);
        sbot = new ImitationOfTheMind();
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        if (message.getText().trim().length() > 0) {
            try {
                StyledDocument doc = dialogue.getStyledDocument();
                doc.insertString(doc.getLength(), message.getText() + "\n",
                        new SimpleAttributeSet());
                doc.insertString(doc.getLength(),
                        TITLE_OF_PROGRAMM.substring(0, 9) +
                                sbot.sayInReturn(message.getText(), ai.isSelected()) + "\n",
                        botStyle);
            } catch (BadLocationException e) {
                e.printStackTrace();
            }
        }
        message.setText("");
        message.requestFocusInWindow();
    }
}
