/**
 * Created by Maedeh on 2/10/2017.
 */

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;


public class GameCreator extends JPanel implements ActionListener {

    public static ArrayList<GameCell_info> gameCellArray = new ArrayList<GameCell_info>();

    static JButton ImageButton, createButton;
    JFileChooser chooser;
    static ImageIcon imageIcon;
    static JTextField solutionText = new JTextField(16);
    static JTextField alphabetText = new JTextField(8);

    public GameCreator() {
        ImageButton = new JButton("Select An Image");
        ImageButton.addActionListener(this);
        ImageButton.setActionCommand("select-image");

        createButton = new JButton("Create New Game Object");
        createButton.addActionListener(this);
        createButton.setActionCommand("create");

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

                System.out.println("getSelectedFile() : "
                        + chooser.getSelectedFile());


                JFrame frame = new JFrame();
                frame.getContentPane();
                frame.setSize(getPreferredSize());

                imageIcon = new ImageIcon(String.valueOf(chooser.getSelectedFile()));
                JLabel label = new JLabel(imageIcon);
                frame.add(label);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);
            } else {
                System.out.println("No Selection ");
            }
        } else if (action.equals("create")) {
            //TODO create a new object

        }
    }

//    public void saveImage(Image image) {
//        RenderedImage rendered = null;
//        if (image instanceof RenderedImage) {
//            rendered = (RenderedImage) image;
//        } else {
//            BufferedImage buffered = new BufferedImage(imageIcon.getIconWidth(), imageIcon.getIconHeight(), BufferedImage.TYPE_INT_RGB);
//            Graphics2D g = buffered.createGraphics();
//            g.drawImage(image, 0, 0, null);
//            g.dispose();
//            rendered = buffered;
//        }
//        try {
//            ImageIO.write(rendered, "JPEG", new File("image.png"));
//        } catch (IOException e1) {
//            e1.printStackTrace();
//        }
//    }


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
        c.anchor = GridBagConstraints.LINE_START;

        panel.add(new JLabel("Enter Puzzle solution text here: "), c);
        c.gridy++;
        panel.add(new JLabel("Enter alphabets for puzzle here: "), c);
        c.gridy++;
        panel.add(ImageButton, c);
        c.gridy++;
        panel.add(createButton, c);

        ///////////////
        c.gridx = 1;
        c.gridy = 0;
        c.anchor = GridBagConstraints.LINE_START;

        panel.add(solutionText);
        c.gridy++;
        panel.add(alphabetText, c);
        //////////////////////////

        frame.add(panel);
        frame.pack();
        frame.setVisible(true);

        // game handling logic
        gameCellArray.add(new GameCell_info());
    }

    private static void saveCell(int level){
        int index = level -1;
        GameCell_info gameCellInfo = gameCellArray.get(index);

        String solution = solutionText.getText();
        String alphabets = alphabetText.getText();
        char[] alphabetsChar = new char[alphabets.length()];
        alphabets.getChars(0, alphabets.length(), alphabetsChar, 0);
        Image image = imageIcon.getImage();


        gameCellInfo.setSolution(solution);
        gameCellInfo.setAlphabets(alphabetsChar);
        gameCellInfo.setImage(image);
        gameCellInfo.setLevelNumber(level);
        if (level ==1){
            gameCellInfo.setLocked(false);
        }else{
            gameCellInfo.setLocked(true);
        }
        gameCellInfo.setSolved(false);
    }

    private static void loadCell(int level){
        int index = level -1;
        GameCell_info gameCellInfo = gameCellArray.get(index);

        solutionText.setText(gameCellInfo.getSolution());
        alphabetText.setText(gameCellInfo.getAlphabets().toString());
        // TODO: show picture, Image image = imageIcon.getImage();

    }

}
