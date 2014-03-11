package ca.ubc.jpacman.framework.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import org.jpacman.framework.ui.ButtonPanel;

public class UndoableButtonPanel extends ButtonPanel {

	private static final long serialVersionUID = -1231979178887149629L;

	@Override
	public void initialize() {
		JButton undoButton = createUndoButton();
		super.initialize();
		addButton(undoButton);
	}

	protected JButton createUndoButton() {
		JButton undoButton = new JButton("Undo");
		undoButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				getPacmanInteractor().undo();
			}
		});
		return undoButton;
	}

	@Override
	public UndoablePacmanInteraction getPacmanInteractor() {
		return (UndoablePacmanInteraction) super.getPacmanInteractor();
	}
}
