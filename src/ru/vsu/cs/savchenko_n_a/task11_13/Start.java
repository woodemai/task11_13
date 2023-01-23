package ru.vsu.cs.savchenko_n_a.task11_13;


import ru.vsu.cs.util.MyUtils;
import ru.vsu.cs.util.SwingUtils;

import javax.swing.*;
import java.awt.*;
import java.io.PrintStream;

import java.util.Locale;


public class Start {

    public static class CmdParams {
        public String inputFile;
        public String outputFile;
        public boolean n;
        public boolean error;
        public boolean help;
        public boolean window;
    }

    public static CmdParams parseArgs(String[] args) {
        CmdParams params = new CmdParams();
        if (args.length > 0) {
            if (args[0].equals("--help")) {
                params.help = true;
                return params;
            }
            if (args[0].equals("--window")) {
                params.window = true;
                return params;
            }
            params.inputFile = args[0];
            if (args.length > 1) {
                params.outputFile = args[1];
            }
            if (args.length > 2) {
                params.help = true;
                params.error = true;
            }
        } else {
            params.help = true;
            params.error = true;
        }
        return params;
    }


    public static void main(String[] args) throws Exception {
        CmdParams params = parseArgs(args);
        if (params.help) {
            PrintStream out = params.error ? System.err : System.out;
            out.println("Usage:");
            out.println("  <cmd> args <input-file> (<output-file>)");
            out.println("  <cmd> --window ");
            out.println("  <cmd> --help");
            System.exit(params.error ? 1 : 0);
        }
        if (params.window) {
            Locale.setDefault(Locale.ROOT);
            java.awt.EventQueue.invokeLater(() -> {
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                    SwingUtils.setDefaultFont(Font.MONOSPACED, 24);
                    new Window().setVisible(true);
                } catch (UnsupportedLookAndFeelException | ClassNotFoundException | InstantiationException |
                         IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            });
        } else {
            int num = Integer.parseInt(MyUtils.readLineFromFile(params.inputFile));
            PrintStream out = (params.outputFile != null) ? new PrintStream(params.outputFile) : System.out;
            out.print(Solution.convertIntToString(num));
            out.close();
        }
    }
}