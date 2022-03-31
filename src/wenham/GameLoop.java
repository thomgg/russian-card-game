package wenham;

import com.sun.corba.se.impl.io.TypeMismatchException;

import javax.swing.*;
import java.awt.*;
import java.util.Scanner;

import static wenham.GameLogic.*;

public class GameLoop extends Thread {
    private GameWindow frame;
    private GamePanel screen;
    private int gameRecord[][] = new int[13][3];

    public GameLoop() {
        frame = new GameWindow("Besikovitch's game");
        screen = new GamePanel(frame);
    }

    public void startTextGame() {
        Scanner scn = new Scanner(System.in);
        int p1_pts = 0, p2_pts = 0;
        int stake, player, computer;
        for (int i = 0; i < 13; i++) {
            System.out.println("What is the stake card?");
            stake = scn.nextInt();
            gameRecord[i][0] = stake;
            System.out.println("What card will you bid?");
            player = scn.nextInt();
            gameRecord[i][1] = player;
            computer = chooseCardP2(gameRecord, stake);
            gameRecord[i][2] = computer;
            validateGameHistory(gameRecord);
            System.out.println("Your bid: " + player + ". Computer's bid: " + computer + ".");
            if (player > computer) {
                System.out.println("Player wins " + stake + " points.");
                p1_pts += stake;
            } else if (computer > player) {
                System.out.println("Computer wins " + stake + " points.");
                p2_pts += stake;
            } else System.out.println("Draw. No one wins.");
            System.out.println("Current Score -- Player: " + p1_pts + " Computer: " + p2_pts);
            if (p1_pts > 46 || p2_pts > 46) break;
        }
        if (p1_pts > p2_pts) System.out.println("Player wins!");
        else if (p2_pts > p1_pts) System.out.println("Computer wins!");
        else System.out.println("It's a draw!");
    }

    public void start() {
        int stake, player, computer;
        int[][] flipped_gameRecord = new int[13][3];
        int p1_pts = 0, p2_pts = 0;
        int p1_pts_old = p1_pts;
        int p2_pts_old = p2_pts;
        screen.stake_revealed = false;
        screen.bids_revealed = false;
        frame.repaint();
        for (int i = 0; i < 13; i++) {
            stake = 0;
            boolean valid_move = false;
            while (!valid_move) {
                String input = "";
                try {
                    while (!screen.ready) {
                        sleep(160);
                    }
                    input = screen.txtfInput.getText();
                    screen.ready = false;
                    stake = Integer.parseInt(input);
                    if (stake < 1 || stake > 13) throw new NumberFormatException();
                    gameRecord[i][0] = stake;
                    validateGameWithUnfinishedRound(gameRecord);
                    valid_move = true;
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "Invalid input, try again");
                } catch (IllegalArgumentException e) {
                    JOptionPane.showMessageDialog(null, "This card has already been used");
                } catch (InterruptedException e) {
                    System.out.println("Interrupted");
                    System.exit(2);
                } finally {
                    screen.txtfInput.setText("");
                }
            }
            screen.changeStake(stake, 3);
            screen.stake_revealed = true;
            frame.repaint();

            valid_move = false;
            while (!valid_move) {
                String input = "";
                try {
                    while (!screen.ready) {
                        sleep(160);
                    }
                    input = screen.txtfInput.getText();
                    screen.ready = false;
                    player = Integer.parseInt(input);
                    if (player < 1 || player > 13) throw new NumberFormatException();
                    gameRecord[i][1] = player;
                    validateGameWithUnfinishedRound(gameRecord);
                    valid_move = true;
                    screen.playPlayerCard(new Card(player, 1));
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "Invalid input, try again");
                } catch (IllegalArgumentException e) {
                    JOptionPane.showMessageDialog(null, "This card has already been used");
                } catch (InterruptedException e) {
                    System.out.println("Interrupted");
                    System.exit(2);
                }
            }
            frame.repaint();

            computer = chooseCardP2(gameRecord, stake);
            gameRecord[i][2] = computer;
            screen.playComputerCard(new Card(computer, 2));

            screen.bids_revealed = true;
            frame.repaint();

            p1_pts_old = p1_pts;
            p2_pts_old = p2_pts;

            p1_pts = calcPlayer1Score(gameRecord);

            flipped_gameRecord[i][0] = gameRecord[i][0];
            flipped_gameRecord[i][1] = gameRecord[i][2];
            flipped_gameRecord[i][2] = gameRecord[i][1];
            p2_pts = calcPlayer1Score(flipped_gameRecord);

            int winner = 0; int add_points = 0;
            if (p1_pts - p1_pts_old > 0) {
                JOptionPane.showMessageDialog(frame, "Player wins the stake");
                winner = 1;
                add_points = p1_pts - p1_pts_old;
            } else if (p2_pts - p2_pts_old > 0){
                JOptionPane.showMessageDialog(frame, "Computer wins the stake");
                winner = 2;
                add_points = p2_pts - p2_pts_old;
            } else {
                JOptionPane.showMessageDialog(frame, "There is not winner for this stake");
            }

            try {
                for (int p = 1; p <= add_points; p++) {
                    screen.addPoint(winner);
                    frame.repaint();
                    sleep(333);
                }
            } catch (InterruptedException e) {
                System.out.println("Interrupted");
                System.exit(2);
            }

            screen.stake_revealed = false;
            screen.bids_revealed = false;
            screen.txtfInput.setText("");
            frame.repaint();

            if (p1_pts > 46 || p2_pts > 46) break;
            JOptionPane.showMessageDialog(frame, "Next round");
        }
        if (p1_pts > p2_pts) JOptionPane.showMessageDialog(frame, "Player wins!");
        else if (p2_pts > p1_pts) JOptionPane.showMessageDialog(frame, "Computer wins!");
        else JOptionPane.showMessageDialog(frame, "It's a draw!");
    }

    public static void main(String[] args) {
        GameLoop theGame = new GameLoop();
        theGame.start();

    }
}
