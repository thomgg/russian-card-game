package wenham;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Card extends JComponent implements Comparable<Card> {
    private int x, y;
    private int suit; //1 = clover, 2 = hearts, 3 = spades, 4 = diamonds
    private int number; //1 = ace, 11 = jack, 12 = queen, 13 = king;
    private BufferedImage sprite_sheet = null;
    private GameWindow frame;
    public static final int LENGTH = 72, HEIGHT = 97;

    public Card(int number, int suit) {
        setPos(50, 50);
        this.suit = suit;
        this.number = number;
        try {
            sprite_sheet = ImageIO.read(new File("res/Images/cards_sheet_RU.png"));
        } catch (IOException e) {
            System.out.println("Could not open sprite sheet :(");
            System.exit(1);
        }
    }

    public void setPos(int x, int y) {
        this.x = x;
        this.y = y;
        setBounds(x, y, LENGTH, HEIGHT);
    }

    public void setX(int x) {
        this.x = x;
        setBounds(x, y, LENGTH, HEIGHT);
    }

    public void setY(int y) {
        this.y = y;
        setBounds(x, y, LENGTH, HEIGHT);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void changeCard(int number, int suit){
        this.number = number;
        this.suit = suit;
    }

    public void paint(Graphics g) {
        int img_x = number + (number - 1) * LENGTH;
        int img_y = suit + (suit - 1) * HEIGHT;
        g.drawImage(sprite_sheet, x, y, x + LENGTH, y + HEIGHT, img_x, img_y, img_x + LENGTH, img_y + HEIGHT, null);
    }

    public static void paintBack(Graphics g, int x, int y) {
        BufferedImage card_back_img = null;
        try {
            card_back_img = ImageIO.read(new File("res/Images/card_reverse.png"));
        } catch (IOException e) {
            System.out.println("Could not open sprite sheet :(");
            System.exit(1);
        }
        g.drawImage(card_back_img, x, y, x + LENGTH, y + HEIGHT, 0, 0, LENGTH, HEIGHT, null);
    }

    @Override
    public int compareTo(Card other) {
        return (this.number - other.number != 0) ? this.number - other.number : this.suit - other.suit;
    }
}
