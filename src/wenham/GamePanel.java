package wenham;

import javax.swing.*;
import java.awt.*;
import java.util.TreeSet;

public class GamePanel extends JPanel {
    private GameWindow frame;
    private TreeSet<Card> p1_cards;
    private Card stake_card = null;
    private Card[] cards_in_play = new Card[2];
    private int[] scores = new int[2];
    public boolean stake_revealed;
    public boolean bids_revealed = false;
    public JLabel text_info = new JLabel();
    public JTextField txtfInput = new JTextField();
    private JButton butInput = new JButton("Enter");
    public boolean ready = false;
    private boolean initialised = false;

    public GamePanel(GameWindow frame) {
        this.frame = frame;
        frame.add(this);
        setBounds(frame.getBounds());
        setLayout(null);
        this.add(text_info);
        text_info.setText("Enter the stake card");
        text_info.setHorizontalTextPosition(SwingConstants.CENTER);
        this.add(txtfInput);
        txtfInput.setSize(150, 30);
        this.add(butInput);
        butInput.setSize(150, 30);
        butInput.addActionListener(new InputButtonListener(this));
        stake_revealed = false;
        p1_cards = new TreeSet<>();
        for (int i = 1; i <= 13; i++) p1_cards.add(new Card(i, 1));
        initialised = true;
    }

    public void changeStake(int number, int suit) {
        if (stake_card == null) stake_card = new Card(number, suit);
        else stake_card.changeCard(number, suit);
    }

    public void playPlayerCard(Card card) {
        p1_cards.remove(card);
        cards_in_play[0] = card;
    }

    public void playComputerCard(Card card) {
        cards_in_play[1] = card;
    }

    public void addPoint(int player) {
        if (player != 1 && player != 2) throw new IllegalArgumentException();
        scores[player - 1]++;
    }

    public void paintComponent(Graphics g) {
        if (initialised) {
            Rectangle panel_box = getBounds();
            int x2 = (int) panel_box.getWidth();
            int y2 = (int) panel_box.getHeight();
            g.setColor(new Color(145, 0, 3));
            g.fillRect(x2 - 200, 0, 200, 30);
            g.fillRect(x2 - 200, y2 - 30, 200, 30);

            text_info.setLocation(x2 - 175, y2 / 2 + 80);
            txtfInput.setLocation(x2 - 175, y2 / 2 + 110);
            butInput.setLocation(x2 - 175, y2 / 2 + 140);

            g.setColor(new Color(255, 216, 0));
            g.drawString("CP Score:", x2 - 195, 15);
            g.drawString(scores[1] + "", x2 - 100, 15);
            g.drawString("P1 Score:", x2 - 195, y2 - 15);
            g.drawString(scores[0] + "", x2 - 100, y2 - 15);
            g.drawString("Stake card:", x2 - 100 - Card.LENGTH / 2, y2 / 2 - 5);

            if (stake_revealed && stake_card != null) {
                stake_card.setPos(x2 - 100 - Card.LENGTH / 2, y2 / 2 - Card.HEIGHT / 2);
                stake_card.paint(g);
            } else Card.paintBack(g, x2 - 100 - Card.LENGTH / 2, y2 / 2 - Card.HEIGHT / 2);

            int horSpace = x2 - 250;
            int cardspacing = 0;
            if (p1_cards.size() > 0) cardspacing = horSpace / p1_cards.size();
            int x = 25, y = y2 - Card.HEIGHT - 25;


            for (Card card : p1_cards) {
                card.setPos(x, y);
                card.paint(g);
                x += cardspacing;
            }

            x = 25;
            y = 25;
            for (int i = 0; i < p1_cards.size(); i++) {
                Card.paintBack(g, x, y);
                x += cardspacing;
            }

            if (bids_revealed) {
                cards_in_play[0].setPos((x2 - 250) / 2, y2 / 2 + 10);
                cards_in_play[0].paint(g);
                cards_in_play[1].setPos((x2 - 250) / 2, y2 / 2 - Card.HEIGHT - 10);
                cards_in_play[1].paint(g);
            }
        }
    }
}
