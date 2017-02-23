/**
 * Created by Maedeh on 2/16/2017.
 */

import java.awt.*;
import java.io.Serializable;


/**
 * Created by Maedeh on 2/16/2017.
 */

public class GameCell_info implements Serializable {

    private String solution = "";
    private boolean isLocked, isSolved;
    private char[] alphabets = {};
    private int levelNumber;
    private byte[] image;


    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public int getLevelNumber() {
        return levelNumber;
    }

    public void setLevelNumber(int levelNumber) {
        this.levelNumber = levelNumber;
    }

    public char[] getAlphabets() {
        return alphabets;
    }
    public void setAlphabets(char[] alphabets) {
        this.alphabets = alphabets;
    }

    public boolean isSolved() {
        return isSolved;
    }

    public void setSolved(boolean solved) {
        isSolved = solved;
    }

    public boolean isLocked() {
        return isLocked;
    }

    public void setLocked(boolean locked) {
        isLocked = locked;
    }

    public String getSolution() {
        return solution;
    }

    public void setSolution(String solution) {
        this.solution = solution;
    }

}
