import java.awt.*;
import java.awt.image.*;
import java.io.*;
import javax.swing.*;
import javax.imageio.ImageIO;
public class Question {
    int qNum;
    String answer;
    int response;
    String[] key = {"a", "b", "c", "d", "a", "b", "c", "d", "a", "b", "c", "d", "a", "b"};

    /**move questions array to Game class later**/
    String[] questions = {"Press button for to begin", "This revenger who?", "Conglaturations! A winner is you! Pray againe?",
            "Haha you loose, now world is will be destroy!"};

    public Question(int qNum) {
        this.qNum = qNum;

    }

    public void changeButtonDisplay(int qNum, boolean inGame){
        if (inGame == false){
            //button1-4.setText("PRESS ME DADDY");
        }
        if (qNum == 0){
            //button1.setText
            //button2.setText
            //button3.setText
            //button4.setText
        }
        /**for all questions**/
    }
    //method gets input from button
    //if input == key[qNum], correct++. qNum++.
    //if !=, disable button (?)
    
}