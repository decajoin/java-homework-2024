package gui.net;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class StudentGradeApp extends JFrame {
    private JTextField idField, basicScoreField, mainFunctionScoreField, function1ScoreField, function2ScoreField, totalScoreField;
    private JTextArea sourceCodeArea;
    private JTextArea resultArea;
    private List<File> cFiles = new ArrayList<>();
    private int currentFileIndex = 1 ;
    private File saveDirectory = new File("files/Ctest");
    private File scoreDirectory = new File("files/Ctest");

    public StudentGradeApp() {
        setTitle("Hello");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JTabbedPane tabbedPane = new JTabbedPane();

        JPanel gradingPanel = createGradingPanel();
        JPanel resultPanel = createResultPanel();


        tabbedPane.addTab("阅卷", gradingPanel);
        tabbedPane.addTab("统计学生成绩", resultPanel);
        tabbedPane.addTab("计算器", new JPanel()); // 占位符

        tabbedPane.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                if (tabbedPane.getSelectedIndex() == 2) {
                    // 打开Calculator JFrame
                    Calculator calculator = new Calculator();
                    calculator.setVisible(true);
                }
            }
        });

        add(tabbedPane);

        if (!saveDirectory.exists()) {
            saveDirectory.mkdirs();
        }

        if (!scoreDirectory.exists()) {
            scoreDirectory.mkdirs();
        }

        loadCFiles();

        // 读取第一个学生的C程序
        loadFirstStudentCFile();
    }

    private void loadCFiles() {
        File[] files = saveDirectory.listFiles((dir, name) -> name.endsWith(".c"));
        if (files != null) {
            for (File file : files) {
                cFiles.add(file);
            }
        }
    }

    private void loadFirstStudentCFile() {
        if (!cFiles.isEmpty()) {
            File cFile = cFiles.get(0);
            try {
                sourceCodeArea.setText(new String(Files.readAllBytes(Paths.get(cFile.getAbsolutePath()))));
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
    }

    private JPanel createGradingPanel() {
        JPanel panel = new JPanel(new BorderLayout());

        JPanel inputPanel = new JPanel(new GridLayout(7, 2));
        panel.add(inputPanel, BorderLayout.NORTH);

        inputPanel.add(new JLabel("学号"));
        idField = new JTextField();
        inputPanel.add(idField);

        inputPanel.add(new JLabel("录入基本分"));
        basicScoreField = new JTextField();
        addScoreFieldListener(basicScoreField);
        inputPanel.add(basicScoreField);

        inputPanel.add(new JLabel("主函数得分"));
        mainFunctionScoreField = new JTextField();
        addScoreFieldListener(mainFunctionScoreField);
        inputPanel.add(mainFunctionScoreField);

        inputPanel.add(new JLabel("函数1得分"));
        function1ScoreField = new JTextField();
        addScoreFieldListener(function1ScoreField);
        inputPanel.add(function1ScoreField);

        inputPanel.add(new JLabel("函数2得分"));
        function2ScoreField = new JTextField();
        addScoreFieldListener(function2ScoreField);
        inputPanel.add(function2ScoreField);

        inputPanel.add(new JLabel("总分"));
        totalScoreField = new JTextField();
        totalScoreField.setEditable(false);
        inputPanel.add(totalScoreField);

        JButton nextButton = new JButton("下一个学生");
        nextButton.addActionListener(new NextButtonListener());
        inputPanel.add(nextButton);

        JButton saveButton = new JButton("保存");
        saveButton.addActionListener(new SaveButtonListener());
        inputPanel.add(saveButton);

        sourceCodeArea = new JTextArea();
        panel.add(new JScrollPane(sourceCodeArea), BorderLayout.CENTER);

        return panel;
    }

    private void addScoreFieldListener(JTextField scoreField) {
        scoreField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                calculateTotalScore();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                calculateTotalScore();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                calculateTotalScore();
            }
        });
    }

    private void calculateTotalScore() {
        try {
            double basicScore = Double.parseDouble(basicScoreField.getText());
            double mainFunctionScore = Double.parseDouble(mainFunctionScoreField.getText());
            double function1Score = Double.parseDouble(function1ScoreField.getText());
            double function2Score = Double.parseDouble(function2ScoreField.getText());
            double totalScore = basicScore + mainFunctionScore + function1Score + function2Score;
            totalScoreField.setText(String.valueOf(totalScore));
        } catch (NumberFormatException e) {
            totalScoreField.setText("");
        }
    }

    private JPanel createResultPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        resultArea = new JTextArea();
        resultArea.setEditable(false);
        panel.add(new JScrollPane(resultArea), BorderLayout.CENTER);

        JButton loadButton = new JButton("加载成绩");
        loadButton.addActionListener(new LoadButtonListener());
        panel.add(loadButton, BorderLayout.SOUTH);

        return panel;
    }

    private class NextButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (currentFileIndex < cFiles.size()) {
                File cFile = cFiles.get(currentFileIndex);
                try {
                    sourceCodeArea.setText(new String(Files.readAllBytes(Paths.get(cFile.getAbsolutePath()))));
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
                currentFileIndex++;
            } else {
                JOptionPane.showMessageDialog(null, "没有更多的C文件");
            }
        }
    }

    private class SaveButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String id = idField.getText();
            double basicScore = Double.parseDouble(basicScoreField.getText());
            double mainFunctionScore = Double.parseDouble(mainFunctionScoreField.getText());
            double function1Score = Double.parseDouble(function1ScoreField.getText());
            double function2Score = Double.parseDouble(function2ScoreField.getText());
            double totalScore = Double.parseDouble(totalScoreField.getText());

            Student student = new Student(id, basicScore, mainFunctionScore, function1Score, function2Score);
            File scoreFile = new File(scoreDirectory, "score.txt");

            try (BufferedWriter writer = new BufferedWriter(new FileWriter(scoreFile, true))) {
                writer.write(student.toString());
                writer.newLine();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }

            if (currentFileIndex - 1 < cFiles.size()) {
                File cFile = cFiles.get(currentFileIndex - 1);
                File renamedFile = new File(saveDirectory, "x" + (currentFileIndex) + ".c");
                cFile.renameTo(renamedFile);
            }
        }
    }

    private class LoadButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            resultArea.setText("");
            File scoreFile = new File(scoreDirectory, "score.txt");
            if (scoreFile.exists()) {
                try (BufferedReader reader = new BufferedReader(new FileReader(scoreFile))) {
                    String line;
                    while ((line = reader.readLine()) != null) {
                        resultArea.append(line + "\n");
                    }
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            StudentGradeApp app = new StudentGradeApp();
            app.setVisible(true);
        });
    }
}



