package ru.vsu.cs.savchenko_n_a.task11_13;


import ru.vsu.cs.util.MyUtils;
import ru.vsu.cs.util.SwingUtils;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.*;

public class Window extends JFrame {
    private JTextField textFieldInput;
    private JPanel panel;
    private JButton buttonInput;
    private JButton buttonOutput;
    private JTextArea textFieldOutput;
    private JButton buttonExecute;

    private final JFileChooser fileChooserSave;

    public Window() throws UnsupportedLookAndFeelException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        setTitle("TASK 11 13");
        setContentPane(panel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();

        JFileChooser fileChooserOpen = new JFileChooser();
        fileChooserSave = new JFileChooser();
        fileChooserOpen.setCurrentDirectory(new File("."));
        fileChooserSave.setCurrentDirectory(new File("."));
        FileFilter filter = new FileNameExtensionFilter("Text files", "txt");
        fileChooserOpen.addChoosableFileFilter(filter);
        fileChooserSave.addChoosableFileFilter(filter);
        fileChooserSave.setAcceptAllFileFilterUsed(false);
        fileChooserSave.setDialogType(JFileChooser.SAVE_DIALOG);
        fileChooserSave.setApproveButtonText("Save");


        buttonInput.addActionListener(e -> {
            try {
                if (fileChooserOpen.showOpenDialog(panel) == JFileChooser.APPROVE_OPTION) {
                    textFieldInput.setText(MyUtils.readLineFromFile(fileChooserOpen.getSelectedFile().getPath()));
                }
            } catch (Exception e1) {
                SwingUtils.showErrorMessageBox(e1);
            }
        });


        buttonOutput.addActionListener(e -> {
            try {
                if (fileChooserSave.showSaveDialog(panel) == JFileChooser.APPROVE_OPTION) {
                    MyUtils.writeLineToFile(fileChooserSave.getSelectedFile().getPath(), textFieldOutput.getText());
                }
            } catch (Exception e1) {
                SwingUtils.showErrorMessageBox(e1);
            }
        });


        buttonExecute.addActionListener(e -> {
            try {
                textFieldOutput.setText(Solution.convertIntToString(Integer.parseInt(textFieldInput.getText())));
            } catch (Exception e1) {
                textFieldOutput.setText(textFieldInput.getText());
            }
        });
        KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(e -> {
            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                try {
                    textFieldOutput.setText(Solution.convertIntToString(Integer.parseInt(textFieldInput.getText())));
                } catch (Exception e1) {
                    textFieldOutput.setText(textFieldInput.getText());
                }
            }
            return false;
        });
    }
}
