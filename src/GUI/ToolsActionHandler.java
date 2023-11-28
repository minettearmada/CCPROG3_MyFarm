/*
 * ID#12108115  ARMADA,BRENDA MINETTE
 * ID#12130796  UY,TYRONE ANGELO
 */
package GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Class that handles events related to the toolsUI
 */
public class ToolsActionHandler implements ActionListener {
    private ToolsUI toolsUI; // UI for tools

    /**
     * Construct the actionhandler by passing a toolsUI class parameter
     * 
     * @param toolsUI // UI for the tools
     */
    ToolsActionHandler(ToolsUI toolsUI) {
        this.toolsUI = toolsUI;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String tool = e.getActionCommand();
        this.toolsUI.getSeedsUI().setSeedBtnPressed("");
        this.toolsUI.getSeedsUI().untoggleButtons();
        switch (tool) {
            case "Plow":
                if (toolsUI.getPlowButton().isSelected()) {
                    toolsUI.setToolBtnPressed("Plow");
                    toolsUI.untoggleButtons();
                    // Make farm tile buttons clickable
                    for (int i = 0; i < 5; i++) {
                        for (int j = 0; j < 10; j++) {
                            toolsUI.getFarmTiles()[i][j].setEnabled(true);
                        }
                    }
                } else {
                    toolsUI.setToolBtnPressed("");
                    // Make farm tile buttons unclickable
                    for (int i = 0; i < 5; i++) {
                        for (int j = 0; j < 10; j++) {
                            toolsUI.getFarmTiles()[i][j].setEnabled(false);
                        }
                    }
                }
                break;
            case "Watering Can":
                if (toolsUI.getWateringCanButton().isSelected()) {
                    toolsUI.setToolBtnPressed("Watering Can");
                    toolsUI.untoggleButtons();
                    // Make farm tile buttons clickable
                    for (int i = 0; i < 5; i++) {
                        for (int j = 0; j < 10; j++) {
                            toolsUI.getFarmTiles()[i][j].setEnabled(true);
                        }
                    }
                } else {
                    toolsUI.setToolBtnPressed("");
                    // Make farm tile buttons unclickable
                    for (int i = 0; i < 5; i++) {
                        for (int j = 0; j < 10; j++) {
                            toolsUI.getFarmTiles()[i][j].setEnabled(false);
                        }
                    }
                }
                break;
            case "Fertilizer":
                if (toolsUI.getFertilizerButton().isSelected()) {
                    toolsUI.setToolBtnPressed("Fertilizer");
                    toolsUI.untoggleButtons();
                    // Make farm tile buttons clickable
                    for (int i = 0; i < 5; i++) {
                        for (int j = 0; j < 10; j++) {
                            toolsUI.getFarmTiles()[i][j].setEnabled(true);
                        }
                    }
                } else {
                    toolsUI.setToolBtnPressed("");
                    // Make farm tile buttons unclickable
                    for (int i = 0; i < 5; i++) {
                        for (int j = 0; j < 10; j++) {
                            toolsUI.getFarmTiles()[i][j].setEnabled(false);
                        }
                    }
                }
                break;
            case "Pickaxe":
                if (toolsUI.getPickaxeButton().isSelected()) {
                    toolsUI.setToolBtnPressed("Pickaxe");
                    toolsUI.untoggleButtons();
                    // Make farm tile buttons unclickable
                    for (int i = 0; i < 5; i++) {
                        for (int j = 0; j < 10; j++) {
                            toolsUI.getFarmTiles()[i][j].setEnabled(true);
                        }
                    }
                } else {
                    toolsUI.setToolBtnPressed("");
                    // Make farm tile buttons unclickable
                    for (int i = 0; i < 5; i++) {
                        for (int j = 0; j < 10; j++) {
                            toolsUI.getFarmTiles()[i][j].setEnabled(false);
                        }
                    }
                }
                break;
            case "Shovel":
                if (toolsUI.getShovelButton().isSelected()) {
                    toolsUI.setToolBtnPressed("Shovel");
                    toolsUI.untoggleButtons();
                    // Make farm tile buttons unclickable
                    for (int i = 0; i < 5; i++) {
                        for (int j = 0; j < 10; j++) {
                            toolsUI.getFarmTiles()[i][j].setEnabled(true);
                        }
                    }
                } else {
                    toolsUI.setToolBtnPressed("");
                    // Make farm tile buttons unclickable
                    for (int i = 0; i < 5; i++) {
                        for (int j = 0; j < 10; j++) {
                            toolsUI.getFarmTiles()[i][j].setEnabled(false);
                        }
                    }
                }
                break;
        }
    }
}
