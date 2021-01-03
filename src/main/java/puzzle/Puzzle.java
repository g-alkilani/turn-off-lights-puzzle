package puzzle;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class Puzzle {
    private static final int FIELD_SIZE = 4;
    private static final int NUMBER_OF_FLIPS = 15;
    private static final int[][] SHIFTS = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    private final JFrame frame;
    private final Random random = new Random();
    private volatile JPanel rootPanel;
    private volatile boolean[][] grid;

    public Puzzle() {
        prepareGrid();
        frame = new JFrame("Turn off the lights");
        frame.setResizable(false);
        frame.setSize(600, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void display() {
        rootPanel = new JPanel(null);
        rootPanel.add(createGameField());
        rootPanel.add(completionLabel());
        frame.add(rootPanel);
        frame.setVisible(true);
    }

    public boolean isGameOver() {
        for (int i = 0; i < FIELD_SIZE; i++) {
            for (int j = 0; j < FIELD_SIZE; j++) {
                if (grid[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }

    public void updateState(int x, int y) {
        boolean[][] grid = this.grid;
        grid[x][y] = !grid[x][y];
        for (int[] shift : SHIFTS) {
            int shiftedX = x + shift[0];
            int shiftedY = y + shift[1];
            if (withinGrid(shiftedX) && withinGrid(shiftedY)) {
                grid[shiftedX][shiftedY] = !grid[shiftedX][shiftedY];
            }
        }
        if (isGameOver()) {
            endTheGame();
            return;
        }
        updatePuzzle();
    }

    private void prepareGrid() {
        grid = new boolean[FIELD_SIZE][FIELD_SIZE];
        int bound = FIELD_SIZE * FIELD_SIZE;
        for (int i = 0; i < NUMBER_OF_FLIPS; i++) {
            int next = random.nextInt(bound);
            int x = next / FIELD_SIZE;
            int y = next % FIELD_SIZE;
            grid[x][y] = !grid[x][y];
            for (int[] shift : SHIFTS) {
                int shiftedX = x + shift[0];
                int shiftedY = y + shift[1];
                if (withinGrid(shiftedX) && withinGrid(shiftedY)) {
                    grid[shiftedX][shiftedY] = !grid[shiftedX][shiftedY];
                }
            }
        }
    }

    private boolean withinGrid(int shiftedCoordinate) {
        return shiftedCoordinate >= 0 && shiftedCoordinate < FIELD_SIZE;
    }

    private JPanel createGameField() {
        JPanel innerPanel = new JPanel();
        innerPanel.setLayout(new GridLayout(FIELD_SIZE, FIELD_SIZE));

        for (int i = 0; i < FIELD_SIZE; i++) {
            for (int j = 0; j < FIELD_SIZE; j++) {
                MyCircle circle = new MyCircle(grid[i][j], i, j, 50);
                circle.addMouseListener(new CircleListener(this));
                innerPanel.add(circle);
            }
        }
        innerPanel.setBounds(200, 100, FIELD_SIZE * 50 + 10, FIELD_SIZE * 50 + 10);
        return innerPanel;
    }

    public void endTheGame() {
        rootPanel.removeAll();
        rootPanel.add(createGameField());
        rootPanel.add(completionLabel());
        rootPanel.revalidate();
        rootPanel.repaint();
    }

    private JLabel completionLabel() {
        JLabel jLabel = new JLabel("Congratulations!");
        jLabel.setHorizontalAlignment(JLabel.CENTER);
        jLabel.setBounds(250, 150 + FIELD_SIZE * 50, 100, 100);
        return jLabel;
    }

    private void updatePuzzle() {
        rootPanel.removeAll();
        rootPanel.add(createGameField());
        rootPanel.revalidate();
        rootPanel.repaint();
    }

}
