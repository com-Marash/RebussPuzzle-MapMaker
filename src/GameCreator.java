/**
 * Created by Maedeh on 2/10/2017.
 */

import marash.com.rebuspuzzle.dto.GameCell_info;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.*;
import java.awt.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;


public class GameCreator extends JPanel implements ActionListener {

    static ArrayList<GameCell_info> gameCellArray = new ArrayList<>();
    static ArrayList<String> imagesPathArray = new ArrayList<>();
    static JButton ImageButton, createButton, saveButton, nextButton, previousButton, saveFileButton, loadFileButton;
    JFileChooser chooser;
    static JTextArea levelLabel = new JTextArea();
    static int totalLevelNumber = 1;
    static int levelNumber = 1;
    static JTextArea imagePathName = new JTextArea("");
    static JTextField solutionText = new JTextField(16);
    static JTextField alphabetText = new JTextField(8);

    public GameCreator() {
        ImageButton = new JButton("Select An Image");
        ImageButton.addActionListener(this);
        ImageButton.setActionCommand("select-image");

        createButton = new JButton("Create New Game Object");
        createButton.addActionListener(this);
        createButton.setActionCommand("create");

        saveButton = new JButton("Save");
        saveButton.addActionListener(this);
        saveButton.setActionCommand("SaveGameCell");

        nextButton = new JButton("Next");
        nextButton.addActionListener(this);
        nextButton.setActionCommand("NextGameCell");

        previousButton = new JButton("Previous");
        previousButton.addActionListener(this);
        previousButton.setActionCommand("PreviousGameCell");

        saveFileButton = new JButton("Save File");
        saveFileButton.addActionListener(this);
        saveFileButton.setActionCommand("saveFileButton");


        loadFileButton = new JButton("Load File");
        loadFileButton.addActionListener(this);
        loadFileButton.setActionCommand("loadFileButton");
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        String action = e.getActionCommand();

        if (action.equals("select-image")) {
            chooser = new JFileChooser();
            FileNameExtensionFilter filter = new FileNameExtensionFilter(
                    "JPG & PNG Images", "jpg", "png");
            chooser.setFileFilter(filter);
            if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {


                imagePathName.setText(String.valueOf(chooser.getSelectedFile()));

            } else {
                System.out.println("No Selection ");
            }
        } else if (action.equals("create")) {
            createCell();

        } else if (action.equals("SaveGameCell")) {
            saveCell(levelNumber);

        } else if (action.equals("NextGameCell")) {
            if (levelNumber < totalLevelNumber) {
                nextGameCell();
            }

        } else if (action.equals("PreviousGameCell")) {
            if (levelNumber > 1) {
                previousGameCell();
            }
        } else if (action.equals("saveFileButton")) {
            saveFile();

        } else if (action.equals("loadFileButton")) {
            loadFile();
        }
    }


    public static void main(String s[]) {
        GameCreator game = new GameCreator();
        JFrame frame = new JFrame("");
        frame.addWindowListener(
                new WindowAdapter() {
                    public void windowClosing(WindowEvent e) {
                        System.exit(0);
                    }
                }
        );

        JPanel panel = new JPanel();
        panel.setBackground(Color.pink);

        panel.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(5, 1, 1, 1);
        //////////////
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 2; //it means fill whole row. Do not divide row to 2 columns.
        c.anchor = GridBagConstraints.CENTER;
        panel.add(levelLabel, c);


        //separator, draws a horizontal line between level number & other elements.
        //GridBagConstraints separatorConstraint = new GridBagConstraints();
        c.gridy++;
        c.weightx = 1.0;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridwidth = GridBagConstraints.BELOW_BASELINE_LEADING;
        panel.add(new JSeparator(JSeparator.HORIZONTAL), c);
        ///////////////////////////////////////////////////////////////////////////

        c.fill = GridBagConstraints.NONE;// No need that the elements fill their spaces.
        c.gridwidth = 1;
        c.gridy++;
        c.anchor = GridBagConstraints.LINE_START;
        panel.add(new JLabel("Enter Puzzle solution text here: "), c);
        c.gridy++;
        panel.add(new JLabel("Enter alphabets for puzzle here: "), c);
        c.gridy++;
        panel.add(ImageButton, c);
        c.gridy++;
        panel.add(createButton, c);
        c.gridy++;
        panel.add(previousButton, c);
        c.gridy++;
        panel.add(saveFileButton, c);
        //////////////////////////////////////////
        c.anchor = GridBagConstraints.LINE_START;
        c.gridx = 1;
        c.gridy = 2;
        panel.add(solutionText, c);
        c.gridy++;
        panel.add(alphabetText, c);
        c.gridy++;
        panel.add(imagePathName, c);
        c.gridy++;
        panel.add(saveButton, c);
        c.gridy++;
        panel.add(nextButton, c);
        c.gridy++;
        panel.add(loadFileButton, c);
        /////////////////////////////////////////

        frame.add(panel);
        frame.pack();
        frame.setVisible(true);

        // game handling logic
        gameCellArray.add(new GameCell_info());
        imagesPathArray.add("");
        loadCell(1);
    }

    private static void createCell() {
        gameCellArray.add(new GameCell_info());
        imagesPathArray.add("");
        totalLevelNumber++;
        levelNumber = totalLevelNumber;
        loadCell(levelNumber);
    }

    private static void saveCell(int level) {
        int index = level - 1;
        GameCell_info gameCellInfo = gameCellArray.get(index);

        String solution = solutionText.getText();
        String alphabets = alphabetText.getText();
        char[] alphabetsChar = new char[alphabets.length()];
        alphabets.getChars(0, alphabets.length(), alphabetsChar, 0);

        String imagePathString = imagePathName.getText();
        imagesPathArray.set(index, imagePathString);

        if (imagePathString != null && !imagePathString.isEmpty()) {
            try {
                Path path = Paths.get(imagePathString);
                gameCellInfo.setImage(Files.readAllBytes(path));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        gameCellInfo.setSolution(solution);
        gameCellInfo.setAlphabets(alphabetsChar);
        //gameCellInfo.setImage(image);
        gameCellInfo.setLevelNumber(level);
    }

    private static void loadCell(int level) {
        int index = level - 1;
        GameCell_info gameCellInfo = gameCellArray.get(index);

        solutionText.setText(gameCellInfo.getSolution());
        alphabetText.setText(String.valueOf(gameCellInfo.getAlphabets()));
        imagePathName.setText(imagesPathArray.get(index));

        // update top text
        levelLabel.setText(" Level " + levelNumber + " of " + totalLevelNumber + " ");
    }

    private static void nextGameCell() {
        levelNumber++;
        loadCell(levelNumber);
        levelLabel.setText(" Level " + levelNumber + " of " + totalLevelNumber + " ");
    }

    private static void previousGameCell() {
        levelNumber--;
        loadCell(levelNumber);
        levelLabel.setText(" Level " + levelNumber + " of " + totalLevelNumber + " ");
    }

    private static void saveFile() {
        try {
            FileOutputStream fout = new FileOutputStream("rebussPuzzle.marash");
            ObjectOutputStream oos = new ObjectOutputStream(fout);
            oos.writeObject(gameCellArray);
            oos.close();
            fout.close();
            // save imagePathArray
            fout = new FileOutputStream("imagesPath.marash");
            oos = new ObjectOutputStream(fout);
            oos.writeObject(imagesPathArray);
            oos.close();
            fout.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void loadFile() {
        try {
            FileInputStream streamIn = new FileInputStream("rebussPuzzle.marash");
            ObjectInputStream objectinputstream = new ObjectInputStream(streamIn);
            gameCellArray = (ArrayList<GameCell_info>) objectinputstream.readObject();
            objectinputstream.close();
            streamIn.close();
            // load imagePaths
            streamIn = new FileInputStream("imagesPath.marash");
            objectinputstream = new ObjectInputStream(streamIn);
            imagesPathArray = (ArrayList<String>) objectinputstream.readObject();
            objectinputstream.close();
            streamIn.close();

            totalLevelNumber = gameCellArray.size();
            levelNumber = 1;
            loadCell(levelNumber);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
