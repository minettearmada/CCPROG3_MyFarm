/*
 * ID#12108115  ARMADA,BRENDA MINETTE
 * ID#12130796  UY,TYRONE ANGELO
 */
package GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Class that handles events related to the seedsUI
 */
public class SeedsActionHandler implements ActionListener {
    private SeedsUI seedsUI; // UI for the seeds

    /**
     * Construct the actionhandler by passing a SeedsUI class parameter
     * 
     * @param seedsUI // UI for the seeds
     */
    SeedsActionHandler(SeedsUI seedsUI) {
        this.seedsUI = seedsUI;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String seed = e.getActionCommand();
        this.seedsUI.getToolsUI().setToolBtnPressed("");
        this.seedsUI.getToolsUI().untoggleButtons();
        switch (seed) {
            case "Turnip":
                if (seedsUI.getTurnipButton().isSelected()) {
                    seedsUI.setSeedBtnPressed("Turnip");
                    seedsUI.untoggleButtons();
                    // Make farm tile buttons clickable
                    for (int i = 0; i < 5; i++) {
                        for (int j = 0; j < 10; j++) {
                            seedsUI.getFarmTiles()[i][j].setEnabled(true);
                        }
                    }
                } else {
                    seedsUI.setSeedBtnPressed("");
                    // Make farm tile buttons unclickable
                    for (int i = 0; i < 5; i++) {
                        for (int j = 0; j < 10; j++) {
                            seedsUI.getFarmTiles()[i][j].setEnabled(false);
                        }
                    }
                }
                break;
            case "Carrot":
                if (seedsUI.getCarrotButton().isSelected()) {
                    seedsUI.setSeedBtnPressed("Carrot");
                    seedsUI.untoggleButtons();
                    // Make farm tile buttons clickable
                    for (int i = 0; i < 5; i++) {
                        for (int j = 0; j < 10; j++) {
                            seedsUI.getFarmTiles()[i][j].setEnabled(true);
                        }
                    }
                } else {
                    seedsUI.setSeedBtnPressed("");
                    // Make farm tile buttons unclickable
                    for (int i = 0; i < 5; i++) {
                        for (int j = 0; j < 10; j++) {
                            seedsUI.getFarmTiles()[i][j].setEnabled(false);
                        }
                    }
                }
                break;
            case "Potato":
                if (seedsUI.getPotatoButton().isSelected()) {
                    seedsUI.setSeedBtnPressed("Potato");
                    seedsUI.untoggleButtons();
                    // Make farm tile buttons clickable
                    for (int i = 0; i < 5; i++) {
                        for (int j = 0; j < 10; j++) {
                            seedsUI.getFarmTiles()[i][j].setEnabled(true);
                        }
                    }
                } else {
                    seedsUI.setSeedBtnPressed("");
                    // Make farm tile buttons unclickable
                    for (int i = 0; i < 5; i++) {
                        for (int j = 0; j < 10; j++) {
                            seedsUI.getFarmTiles()[i][j].setEnabled(false);
                        }
                    }
                }

                break;
            case "Rose":
                if (seedsUI.getRoseButton().isSelected()) {
                    seedsUI.setSeedBtnPressed("Rose");
                    seedsUI.untoggleButtons();
                    // Make farm tile buttons clickable
                    for (int i = 0; i < 5; i++) {
                        for (int j = 0; j < 10; j++) {
                            seedsUI.getFarmTiles()[i][j].setEnabled(true);
                        }
                    }
                } else {
                    seedsUI.setSeedBtnPressed("");
                    // Make farm tile buttons unclickable
                    for (int i = 0; i < 5; i++) {
                        for (int j = 0; j < 10; j++) {
                            seedsUI.getFarmTiles()[i][j].setEnabled(false);
                        }
                    }
                }
                break;
            case "Tulips":
                if (seedsUI.getTulipsButton().isSelected()) {
                    seedsUI.setSeedBtnPressed("Tulips");
                    seedsUI.untoggleButtons();
                    // Make farm tile buttons clickable
                    for (int i = 0; i < 5; i++) {
                        for (int j = 0; j < 10; j++) {
                            seedsUI.getFarmTiles()[i][j].setEnabled(true);
                        }
                    }
                } else {
                    seedsUI.setSeedBtnPressed("");
                    // Make farm tile buttons unclickable
                    for (int i = 0; i < 5; i++) {
                        for (int j = 0; j < 10; j++) {
                            seedsUI.getFarmTiles()[i][j].setEnabled(false);
                        }
                    }
                }
                break;
            case "Sunflower":
                if (seedsUI.getSunflowerButton().isSelected()) {
                    seedsUI.setSeedBtnPressed("Sunflower");
                    seedsUI.untoggleButtons();
                    // Make farm tile buttons clickable
                    for (int i = 0; i < 5; i++) {
                        for (int j = 0; j < 10; j++) {
                            seedsUI.getFarmTiles()[i][j].setEnabled(true);
                        }
                    }
                } else {
                    seedsUI.setSeedBtnPressed("");
                    // Make farm tile buttons unclickable
                    for (int i = 0; i < 5; i++) {
                        for (int j = 0; j < 10; j++) {
                            seedsUI.getFarmTiles()[i][j].setEnabled(false);
                        }
                    }
                }
                break;
            case "Mango":
                if (seedsUI.getMangoButton().isSelected()) {
                    seedsUI.setSeedBtnPressed("Mango");
                    seedsUI.untoggleButtons();
                    // Make farm tile buttons clickable
                    for (int i = 0; i < 5; i++) {
                        for (int j = 0; j < 10; j++) {
                            seedsUI.getFarmTiles()[i][j].setEnabled(true);
                        }
                    }
                } else {
                    seedsUI.setSeedBtnPressed("");
                    // Make farm tile buttons unclickable
                    for (int i = 0; i < 5; i++) {
                        for (int j = 0; j < 10; j++) {
                            seedsUI.getFarmTiles()[i][j].setEnabled(false);
                        }
                    }
                }
                break;
            case "Apple":
                if (seedsUI.getAppleButton().isSelected()) {
                    seedsUI.setSeedBtnPressed("Apple");
                    seedsUI.untoggleButtons();
                    // Make farm tile buttons clickable
                    for (int i = 0; i < 5; i++) {
                        for (int j = 0; j < 10; j++) {
                            seedsUI.getFarmTiles()[i][j].setEnabled(true);
                        }
                    }
                } else {
                    seedsUI.setSeedBtnPressed("");
                    // Make farm tile buttons unclickable
                    for (int i = 0; i < 5; i++) {
                        for (int j = 0; j < 10; j++) {
                            seedsUI.getFarmTiles()[i][j].setEnabled(false);
                        }
                    }
                }
                break;
        }
    }
}
