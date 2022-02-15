import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class GameS extends JFrame {
    JPanel snakepanel = new SnakePanel();
    JPanel score = new Score();
    JButton restart = new JButton("restart");
    JButton exit = new JButton("exit");

    GameS(){
        this.setTitle("Snake");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setLayout(null);
        this.setVisible(true);
        this.setSize(600,640);
        this.setLocationRelativeTo(null);

        this.add(snakepanel);
        snakepanel.setBounds(0,0,450,600);
        snakepanel.setFocusable(true);
        snakepanel.requestFocusInWindow();

        this.add(score);
        score.setBounds(475, 50, 100, 100);

        this.add(restart);
        restart.setBounds(475, 200, 100, 50);
        restart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                Score.score = 0;
                Score.sco.setText(String.valueOf(Score.score));
                new GameS();
            }
        });

        this.add(exit);
        exit.setBounds(475, 350, 100, 50);
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                System.exit(0);
            }
        });
    }
}

class Score extends JPanel{
    JLabel sc = new JLabel("Score: ");
    static int score;
    static JLabel sco = new JLabel(String.valueOf(score));

    Score(){
        setLayout(new GridLayout(2,1,0,0));
        add(sc);
        add(sco);
    }
}

public class SnakeOwn {
    public static void main(String[] args) {
        new GameS();
    }
}
