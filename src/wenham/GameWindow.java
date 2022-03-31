package wenham;

import javax.swing.*;
import java.awt.*;

public class GameWindow extends JFrame {

    public GameWindow(String title){
        super(title);
        setPreferredSize(new Dimension(1000,600));
        setSize(getPreferredSize());
        setBackground(new Color(0, 90, 0));
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setIconImage(new ImageIcon("res/Images/dzhoker.png").getImage());
        setVisible(true);
    }
}
