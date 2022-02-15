import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;

public class SnakePanel extends JPanel implements ActionListener {

    char way = 'R';
    int width = 450;
    int height = 600;
    int sizeComp = 30;
    final int[] snakex = new int[((width * height) / (sizeComp * sizeComp))];
    final int[] snakey = new int[((width * height) / (sizeComp * sizeComp))];
    int foodx, foody;
    int snakeLength = 5;
    Timer timer;
    Random random;
    boolean runs = true;
    boolean winner = false;


    SnakePanel() {
        random = new Random();
        this.setBackground(Color.LIGHT_GRAY);
        this.addKeyListener(new MyListener());
        startGame();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    public void startGame() {
        addFood();
        timer = new Timer(100, this);
        timer.start();
    }

    public void draw(Graphics g) {
        //Field
        for (int i = 0; i <= width; i += sizeComp) {
            g.setColor(Color.black);
            g.drawLine(i, 0, i, height);
        }
        for (int j = 0; j <= height; j += sizeComp) {
            g.setColor(Color.black);
            g.drawLine(0, j, width, j);
        }

        //Apple
        if (runs) {
            g.setColor(Color.green);
            g.fillOval(foodx, foody, sizeComp, sizeComp);

            //Snake
            g.setColor(Color.red);
            for (int i = 0; i < snakeLength; i++) {
                g.fillRect(snakex[i], snakey[i], sizeComp, sizeComp);
            }
        } else {
            g.setColor(Color.black);
            g.setFont( new Font("Arial",Font.BOLD, 40));
            if(winner){
                g.drawString("Winner! Score: " + Score.score, 100, 300);
            }else {
                g.drawString("Game Over! Score: " + Score.score, 20, 300);
            }
        }
    }

    public void moveSnake() {
        for (int i = snakeLength; i > 0; i--) {
            snakex[i] = snakex[i - 1];
            snakey[i] = snakey[i - 1];
        }

        switch (way) {
            case 'R':
                snakex[0] = snakex[0] + sizeComp;
                break;
            case 'L':
                snakex[0] = snakex[0] - sizeComp;
                break;
            case 'U':
                snakey[0] = snakey[0] - sizeComp;
                break;
            case 'D':
                snakey[0] = snakey[0] + sizeComp;
                break;
        }
    }

    public void addFood() {
        boolean check = true;
        foodx = random.nextInt((int) (width / sizeComp)) * sizeComp;
        foody = random.nextInt((int) (height / sizeComp)) * sizeComp;
        while(check) {
            for (int i = 0; i < snakeLength; i++) {
                if (foodx == snakex[i] && foody == snakey[i]) {
                    foodx = random.nextInt((int) (width / sizeComp)) * sizeComp;
                    foody = random.nextInt((int) (height / sizeComp)) * sizeComp;
                } else {
                    check = false;
                }
            }
        }
    }

    public void checkBoom() {
        if (snakex[0] < 0 || snakex[0] > 450 || snakey[0] < 0 || snakey[0] > 600) {
            runs = false;
        }
        for(int i = 1; i <= snakeLength; i++){
            if (snakex[0] == snakex[i] && snakey[0] == snakey[i]) {
                runs = false;
                break;
            }
        }
    }

    public void checkApple() {
        if (snakex[0] == foodx && snakey[0] == foody) {
            addFood();
            snakeLength++;
            Score.score++;
            Score.sco.setText(String.valueOf(Score.score));
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(Score.score == ((width * height) / (sizeComp * sizeComp))){
            winner = true;
        }
        if (runs) {
            moveSnake();
            checkBoom();
            checkApple();
        }
        repaint();
    }

    class MyListener extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_LEFT:
                    way = 'L';
                    break;
                case KeyEvent.VK_RIGHT:
                    way = 'R';
                    break;
                case KeyEvent.VK_UP:
                    way = 'U';
                    break;
                case KeyEvent.VK_DOWN:
                    way = 'D';
                    break;
            }
        }
    }
}


