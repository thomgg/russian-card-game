package wenham;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InputButtonListener implements ActionListener {
    GamePanel game;

    public InputButtonListener(GamePanel game){
        this.game = game;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        game.ready = true;
    }
}
