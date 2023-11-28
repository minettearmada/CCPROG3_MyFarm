/*
 * ID#12108115  ARMADA,BRENDA MINETTE
 * ID#12130796  UY,TYRONE ANGELO
 */
package GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import java.awt.*;

/**
 * Class that handles events related to the startmenu buttons
 */
public class StartMenuActionHandler implements ActionListener {
    JPanel mainPanel; // UI's main JPanel
    JPanel mainGame; // UI of the main game (playing area)

    /**
     * Construct the actionhandler by passing the JPanel mainPanel parameter
     * 
     * @param mainPanel // UI of the main game
     */
    StartMenuActionHandler(JPanel mainPanel) {
        this.mainPanel = mainPanel;
    }

    /**
     * Handles the choice from start menu buttons
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        String choice = e.getActionCommand(); //
        CardLayout scenes = (CardLayout) mainPanel.getLayout(); // Swap panels that's in the mainPanel

        switch (choice) {
            case "START":
                SoundHandler.playSFX("resources/Sound/click_sfx.wav"); // add click sound effects
                scenes.show(mainPanel, "fileChooser_panel"); // proceeds to the fileChooser panel
                break;
            case "HTP":
                SoundHandler.playSFX("resources/Sound/click_sfx.wav"); // add click sound effects
                scenes.show(mainPanel, "htp_panel"); // proceeds to the HowToPlay panel
                break;
            case "QUIT":
                SoundHandler.playSFX("resources/Sound/click_sfx.wav"); // add click sound effects
                System.exit(0); // exits the game
                break;
        }
    }
}
