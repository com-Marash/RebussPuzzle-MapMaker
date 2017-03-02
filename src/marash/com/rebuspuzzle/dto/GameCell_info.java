package marash.com.rebuspuzzle.dto;

import java.io.Serializable;


/**
 * Created by Maedeh on 2/16/2017.
 */

public class GameCell_info implements Serializable {

    private String solution = "";
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

    public String getSolution() {
        return solution;
    }

    public void setSolution(String solution) {
        this.solution = solution;
    }

}
