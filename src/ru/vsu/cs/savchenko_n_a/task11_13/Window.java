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


    public Window() throws UnsupportedLookAndFeelException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        setTitle("TASK 11 13");
        setContentPane(panel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension dimension = toolkit.getScreenSize();
        int width = 800;
        int height = 500;
        setBounds(dimension.width / 2 - width / 2, dimension.height / 2 - height / 2, width, height);


        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File("."));
        FileFilter filter = new FileNameExtensionFilter("Text files", "txt");
        fileChooser.addChoosableFileFilter(filter);


        buttonInput.addActionListener(e -> {
            try {
                if (fileChooser.showOpenDialog(panel) == JFileChooser.APPROVE_OPTION) {
                    textFieldInput.setText(MyUtils.readLineFromFile(fileChooser.getSelectedFile().getPath()));
                }
            } catch (Exception e1) {
                SwingUtils.showErrorMessageBox(e1);
            }
        });


        buttonOutput.addActionListener(e -> {
            try {
                if (fileChooser.showSaveDialog(panel) == JFileChooser.APPROVE_OPTION) {
                    MyUtils.writeLineToFile(fileChooser.getSelectedFile().getPath(), textFieldOutput.getText());
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
