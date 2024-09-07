package ui;

import helperMethods.Constants;
import objects.Tower;
import scenes.Playing;

import java.awt.*;

import static main.GameStates.MENU;
import static main.GameStates.SetGameState;

public class ActionBar extends Bar {

    private Playing playing;
    private MyButton buttonMenu;

    private MyButton[] towerButtons;
    private Tower selectedTower;
    private Tower displayedTower;

    public ActionBar(int x, int y, int width, int height, Playing playing) {
        super(x, y, width, height);
        this.playing = playing;

        initButtons();
    }

    private void initButtons() {
        buttonMenu = new MyButton("Menu", 2, 642, 100, 30);

        towerButtons = new MyButton[3];
        int width = 50;
        int height = 50;
        int xStart = 110;
        int yStart = 650;
        int xOffset = (int) (width * 1.1);

        for (int i = 0; i < towerButtons.length; i++) {
            towerButtons[i] = new MyButton("", xStart + xOffset * i, yStart, width, height, i);
        }
    }

    public void draw(Graphics g) {
        g.setColor(new Color(220, 123, 15));
        g.fillRect(x, y, width, height);

        drawButtons(g);

        drawDisplayedTower(g);
    }

    public void displayTower(Tower t) {
        displayedTower = t;
    }

    private void drawDisplayedTower(Graphics g) {
        if (displayedTower != null) {
            g.setColor(Color.gray);
            g.fillRect(410, 645, 220, 85);
            g.setColor(Color.black);
            g.drawRect(410, 645, 220, 85);
            g.drawRect(420, 650, 50, 50);
            g.drawImage(playing.getTowerManager().getTowerImgs()[displayedTower.getTowerType()], 420, 650, 50, 50, null);
            g.setFont(new Font("LucidaSans", Font.BOLD, 15));
            g.drawString(Constants.Towers.getName(displayedTower.getTowerType()), 490, 660);
            g.drawString("ID: " + displayedTower.getId(), 490, 675);

            drawDisplayedTowerRange(g);
            drawDisplayedTowerBorder(g);
        }
    }

    private void drawDisplayedTowerRange(Graphics g) {
        g.setColor(Color.WHITE);
        g.drawOval(
                displayedTower.getX() + 16 - (int)displayedTower.getRange()/2,
                displayedTower.getY()  + 16 - (int)displayedTower.getRange()/2,
                (int)displayedTower.getRange(),
                (int)displayedTower.getRange()
        );
    }

    private void drawDisplayedTowerBorder(Graphics g) {
        g.setColor(Color.CYAN);
        g.drawRect(displayedTower.getX(), displayedTower.getY(), 32, 32);
    }

    public void drawButtons(Graphics g) {
        buttonMenu.draw(g);
        for (MyButton button : towerButtons) {
            g.setColor(Color.gray);
            g.fillRect(button.x, button.y, button.width, button.height);
            g.drawImage(playing.getTowerManager().getTowerImgs()[button.getId()], button.x, button.y, button.width, button.height, null);

            drawButtonFeedback(g, button);
        }
    }

    public void mouseClicked(int x, int y) {
        if (buttonMenu.getBounds().contains(x, y)) {
            SetGameState(MENU);
        } else {
            for (MyButton button : towerButtons) {
                if (button.getBounds().contains(x, y)) {
                    selectedTower = new Tower(0,0, -1, button.getId());
                    playing.setSelectedTower(selectedTower);
                    return;
                }
            }
        }
    }

    public void mouseMoved(int x, int y) {
        buttonMenu.setMouseOver(false);
        for (MyButton button : towerButtons) {
            button.setMouseOver(false);
        }

        if (buttonMenu.getBounds().contains(x, y)) {
            buttonMenu.setMouseOver(true);
        } else {
            for (MyButton button : towerButtons) {
                if (button.getBounds().contains(x, y)) {
                    button.setMouseOver(true);
                    return;
                }
            }
        }
    }

    public void mousePressed(int x, int y) {
        if (buttonMenu.getBounds().contains(x, y)) {
            buttonMenu.setMousePressed(true);
        } else {
            for (MyButton button : towerButtons) {
                if (button.getBounds().contains(x, y)) {
                    button.setMousePressed(true);
                    return;
                }
            }
        }
    }

    public void mouseReleased(int x, int y) {
        buttonMenu.resetBooleans();
        for (MyButton button : towerButtons) {
            button.resetBooleans();
        }
    }
}
