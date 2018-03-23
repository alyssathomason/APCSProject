import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;

import java.awt.image.*;
import java.io.*;
import javax.imageio.ImageIO;

import javax.swing.*;
import javax.swing.plaf.metal.MetalButtonUI;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Game extends JFrame implements ActionListener {
    static final long serialVersionUID = 106664208;
    public boolean inGame;
    final int X = 1200, Y = 800;

    private int score = 0;
    private int currQuestion = 0;

    private JFrame window;
    private JMenuBar mnuMain;
    private JButton startGameButton;

    private JLabel mainTitle1, mainTitle2, mainTitle3, mainTitle4, lblQuestion, qPic; 
    private JMenuItem   mnuClearQuiz, mnuGameTitle, mnuStartQuiz, mnuExit;

    private JPanel pnlBar, pnlGame, pnlTitle, pnlTitlePicture, pnlTitlePage, pnlQuestion, pnlAnswer;
    public static JPanel pnlPicture;

    private Font fontAnswers = new Font("Impact", Font.BOLD, 70);
    private Font fontTitle = new Font("Impact", Font.BOLD, 30);
    private Font fontMenu = new Font("Impact", Font.BOLD, 25);

    BufferedImage image;

    private JButton answerChoices[] = new JButton[4];
    private String[] choices = new String[] {"A", "B", "C", "D"};
    private String[] answerKey = new String[] {"A", "B", "C", "D", "A", "D", "C", "C", "A", "A", "C", "B", "D", "D", "B"};
    private String[] questions = {"Press button for to begin", "This revenger who?",
            "Conglaturations! A winner is you! Pray againe?", "Haha you loose, now world is will be destroy!" 
    };
    private String[] pictures = new String[] { "1_tinman.png", "2_whitecheetah.PNG", "3_the_bulk.PNG", "4_fighting_device.PNG", "5_hammer_man.PNG", "6_america_man.png", "7_bird_vision.png", "8_crimson_sorceress.png", "9_dark_widow.png", "10_summer_soldier.png", "11_termiteMan.png",
    "12_arachnid_adultmale.png", "13_professor weird m.d..png", "14_ eyesight.png", "15_ thecrow.png"
    };
    
    public void init() {
        inGame = false;

        window = new JFrame("REVENGERS REUNITE");
        
        pnlBar = new JPanel();
        pnlGame = new JPanel();
        pnlTitlePage = new JPanel();
        pnlQuestion = new JPanel();
        pnlPicture = new JPanel();
        pnlAnswer = new JPanel();
        pnlTitle = new JPanel();
        pnlTitlePicture = new JPanel();

        window.setSize(X, Y);
        window.setLocation(300, 180);
        window.setResizable(true);
        window.setLayout(new BorderLayout());
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //start MENU initialization
        mnuMain = new JMenuBar();
        mnuGameTitle = new JMenuItem("   HOME");
        mnuClearQuiz = new JMenuItem("   CLEAR QUIZ");
        mnuStartQuiz = new JMenuItem("   START QUIZ");
        mnuExit = new JMenuItem("   GIVE UP");
        //adding menu items to overall menu
        mnuMain.add(mnuGameTitle);
        mnuGameTitle.setFont(fontMenu);
        mnuMain.add(mnuClearQuiz);
        mnuClearQuiz.setFont(fontMenu);
        mnuMain.add(mnuStartQuiz);
        mnuStartQuiz.setFont(fontMenu);
        mnuMain.add(mnuExit);
        mnuExit.setFont(fontMenu);
        //adding Action Listeners to menu buttons
        mnuClearQuiz.addActionListener(this);
        mnuExit.addActionListener(this);
        mnuStartQuiz.addActionListener(this);
        mnuGameTitle.addActionListener(this);
        //adding menu to panel
        pnlBar.add(mnuMain);
        pnlBar.setBackground(new Color(75, 255, 0)); //color = bright green
        //end menu initialization;
        
        //title page
        pnlTitlePage.setLayout(new BorderLayout());
        pnlTitlePage.setBackground(new Color(255, 0, 0)); //color = red
        mainTitle1 = new JLabel("wElCOmE");
        mainTitle1.setFont(fontTitle);
        pnlTitle.add(mainTitle1);
        mainTitle2 = new JLabel("tO ThE");
        mainTitle2.setFont(fontTitle);
        pnlTitle.add(mainTitle2);
        mainTitle3 = new JLabel("rEveNgErS");
        mainTitle3.setFont(fontTitle);
        pnlTitle.add(mainTitle3);
        mainTitle4 = new JLabel("QuIz");
        mainTitle4.setFont(fontTitle);
        pnlTitle.add(mainTitle4);
        pnlTitle.setBackground(new Color(255, 0, 0)); //color = red
        //adding title picture
        pnlTitlePage.add(pnlTitle, BorderLayout.NORTH);
        pnlTitlePage.add(pnlTitlePicture, BorderLayout.CENTER);
        pnlTitlePicture.setBackground(new Color(255, 0, 0));
        //adding the start game button
        startGameButton = new JButton("Press me daddy");
        startGameButton.setFont(fontTitle);
        startGameButton.addActionListener(this);
        startGameButton.setBackground(new Color(255, 255, 0)); //color = yellow
        pnlTitlePage.add(startGameButton, BorderLayout.SOUTH);
        startGameButton.setUI(new MetalButtonUI() {
            protected Color getDisabledTextColor() {
                return Color.GREEN;
            }
            protected Color getFocusColor() {
                return Color.BLACK;
            }
            protected Color getSelectColor() {
                return Color.BLACK;
            }
        });
        //end title page
        showTitlePage();

        window.add(pnlBar, BorderLayout.NORTH);
        window.setVisible(true);

        //game board
        pnlGame.setLayout(new BorderLayout());
        //question
        lblQuestion = new JLabel(questions[1]);
        lblQuestion.setFont(fontTitle);;
        pnlQuestion.add(lblQuestion);
        pnlQuestion.setBackground(new Color(160, 0, 255)); //color = purple
        pnlGame.add(pnlQuestion, BorderLayout.NORTH);
        //set up picture area
        pnlGame.add(pnlPicture, BorderLayout.CENTER);     
        loadPlay("revengerstheme.wav");
        pnlPicture.setBackground(new Color(255, 135, 0)); //color = burnt orange
        //set up answer choices
        pnlAnswer.setLayout(new FlowLayout(FlowLayout.CENTER));
        pnlAnswer.setBackground(new Color(0, 255, 245)); //background behind buttons; color = tourquoise 
        for (int x = 0; x < 4; x++) {
            answerChoices[x] = new JButton();
            answerChoices[x].setText(choices[x]);
            System.out.println(choices[x]);
            answerChoices[x].setFont(fontAnswers);
            answerChoices[x].setBackground(new Color(0, 247, 255)); //text color of symbol; color = bright blue
            answerChoices[x].addActionListener(this);
            pnlAnswer.add(answerChoices[x]);
            answerChoices[x].setEnabled(true);
            answerChoices[x].setUI(new MetalButtonUI() {
                protected Color getDisabledTextColor() {
                    return Color.WHITE;
                }
                protected Color getFocusColor() {
                    return Color.BLACK;
                }

                protected Color getSelectColor() {
                    return Color.BLACK;
                }
            });
        }
        pnlAnswer.requestFocus();
        pnlAnswer.setVisible(true);
    }
    public void actionPerformed(ActionEvent click) {
        System.out.println("Testing");
        // get the mouse click from the user
        Object source = click.getSource();
        if (source == mnuGameTitle) {
            showTitlePage();
        } else if (source == mnuClearQuiz) {
            clearGameBoard();
        } else if (source == mnuExit) {
            exitGame();
        } else if (source == mnuStartQuiz) {
            showGame();
            startGame(pictures[0]);
        } else if (source == startGameButton) {
            showGame();
            startGame(pictures[0]);
        }
        else {
            checkBoardClick(source);
        }
    }
    //methods for what the game will look like
    public void showTitlePage() {
        clearGameBoard();
        window.add(pnlTitlePage, BorderLayout.CENTER);
        startGameButton.setEnabled(true);
        startGameButton.setVisible(true);
        pnlTitlePage.requestFocus();
        pnlTitlePage.setVisible(true);
    }
    public void showGame() {
        clearGameBoard();
        window.add(pnlGame, BorderLayout.CENTER);
        pnlGame.add(pnlAnswer, BorderLayout.SOUTH);
        pnlAnswer.requestFocus();
        pnlGame.requestFocus();
        pnlGame.setVisible(true);
    }
    public void clearGameBoard() {
        window.remove(pnlGame);
        window.remove(pnlTitlePage);
        startGameButton.setEnabled(false);
        startGameButton.setVisible(false);
        pnlTitlePage.setVisible(false);
        pnlGame.setVisible(false);
    }
    public void newPicture() {
        pnlGame.remove(pnlPicture);

    }
    public void exitGame() {
        int option = JOptionPane.showConfirmDialog(null, "U gon to to let the world down?????????",
                "Quit" ,JOptionPane.YES_NO_OPTION);
        if(option == JOptionPane.YES_OPTION)
        {
            System.exit(0);
        }
    }
    public JLabel getPicture(String filename){      
        try {
        image = ImageIO.read(new File(filename));
        }   catch(FileNotFoundException ex){
            System.out.println("can't find image");
        }   catch(IOException ex){
            System.out.println("error reading file");
        }
        JLabel label = new JLabel(new ImageIcon(image));
        return label;
      }
    public void loadPlay(String filename){
         try{
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(this.getClass().getResource(filename));
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.loop(Clip.LOOP_CONTINUOUSLY);
            clip.start();
         }
         catch(Exception ex)
         {
             System.out.println("clip not loaded");
         }
     } 

    public void repeatPlay(){
         loadPlay("revengerstheme.wav");
     }
    //game-->
    private void checkBoardClick(Object source) {
        if(!inGame){
            return;
        }
        else if (source == answerChoices[0]) {
            if (answerKey[currQuestion].equals("A")) {
                score++;
                currQuestion++;
                System.out.println(currQuestion);
                System.out.println("Answer = A");
                startGame(pictures[currQuestion]);
            }
            else {
                currQuestion++;
                System.out.println(currQuestion);
                System.out.println("Answer != A");
                startGame(pictures[currQuestion]);
            }
        } else if (source == answerChoices[1]) {
            if (answerKey[currQuestion].equals("B")) {
                score++;
                currQuestion++;
                System.out.println("Answer = B");
                startGame(pictures[currQuestion]);
            }
            else {
                currQuestion++;
                System.out.println("Answer != B");
                startGame(pictures[currQuestion]);
            }
        } else if (source == answerChoices[2]) {
            if (answerKey[currQuestion].equals("C")) {
                score++;
                currQuestion++;
                System.out.println("Answer = C");
                startGame(pictures[currQuestion]);
            }
            else {
                currQuestion++;
                System.out.println("Answer != C");
                startGame(pictures[currQuestion]);
            }
        } else if (source == answerChoices[3]) {
            if (answerKey[currQuestion].equals("D")) {
                score++;
                currQuestion++;
                System.out.println("Answer = D");
                startGame(pictures[currQuestion]);
            }
            else {
                currQuestion++;
                System.out.println("Answer != D");
                startGame(pictures[currQuestion]);
            }
        }
    }
    private void startGame(String picFile) {
        inGame = true;
        // pnlGame.remove(pnlPicture);
        if (currQuestion > 0) {
            pnlPicture.remove(qPic);
            System.out.println("Removing pic");
        }
        qPic = getPicture(picFile);
        System.out.println(picFile);
        pnlPicture.add(qPic);
        pnlPicture.revalidate();
        pnlGame.add(pnlPicture, BorderLayout.CENTER);
        if (currQuestion == 14) {
            JOptionPane.showMessageDialog(null, "ur DUN. you got " + score + "right out of 15", "leave", JOptionPane.PLAIN_MESSAGE);
            inGame = false;
        }
    }
}
