package puzzle;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class CircleListener implements MouseListener {

    private final Puzzle puzzle;

    public CircleListener(Puzzle puzzle) {
        this.puzzle = puzzle;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        MyCircle source = (MyCircle) e.getSource();
        if (puzzle.isGameOver()) {
            return;
        }

        puzzle.updateState(source.getXCoordinate(),source.getYCoordinate());
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
