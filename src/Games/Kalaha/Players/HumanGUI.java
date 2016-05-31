/*
 Copyright 2015-2016 Fabian Pijcke

 This file is part of MetaBoard.

 MetaBoard is free software: you can redistribute it and/or modify
 it under the terms of the GNU General Public License as published by
 the Free Software Foundation, either version 3 of the License, or
 (at your option) any later version.

 MetaBoard is distributed in the hope that it will be useful,
 but WITHOUT ANY WARRANTY; without even the implied warranty of
 MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 GNU General Public License for more details.

 You should have received a copy of the GNU General Public License
 along with MetaBoard. If not, see <http://www.gnu.org/licenses/>.
 */
package Games.Kalaha.Players;

import java.util.List;

import Games.Kalaha.Move;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * A graphical user interface for a human to play. This is mainly intended for
 * debugging.
 * 
 * @author Fabian Pijcke
 */
public class HumanGUI extends Player {

	private class ChoseMoveDialog extends Stage {
		private final double halfW = 320.;
		private final double halfH = 240.;
		
		private Move selectedMove;
		
		private final Pane pane = new Pane();
		
		private double getX(int i, double factor) {
			return halfW + factor * halfW * Math.cos(Math.PI * 2 * i / board.getLength());
		}
		
		private double getY(int i, double factor) {
			return halfH + factor * halfH * Math.sin(Math.PI * 2 * i / board.getLength());
		}
		
		public ChoseMoveDialog(String avatar) {
			initStyle(StageStyle.UTILITY);
			initModality(Modality.APPLICATION_MODAL);
			
			VBox main = new VBox();
			
			setScene(new Scene(main));
			sizeToScene();
			String rem = "";
			switch (leftTokensGrantee) {
			case OWNER: rem = "owner"; break;
			case ENDER: rem = "ender"; break;
			case NOBODY: rem = "nobody"; break;
			}
			
			main.getChildren().add(new Text(avatar + " - remaining tokens for " + rem));
			main.getChildren().add(new Text("Scores : "));
			board.getScores(leftTokensGrantee).forEach((a, s) -> main.getChildren().add(new Text(a + ": " + s)));
			main.getChildren().add(pane);
			
			for (int i = 0; i < board.getLength(); ++i) {
				final int j = i; 
				Button button = new Button("" + board.getPieceAt(i));
				button.setDisable(true);
				if (board.isKalaha(i)) {
					button.setStyle("-fx-background-color: white;");
				}
				else if (board.getPlayer(i).equals(avatar)) {
					button.setDisable(false);
					button.setOnAction(event -> {
						selectedMove = new Move(j);
						close();
					});
				}
				button.relocate(getX(i, 0.9) - 15, getY(i, 0.9) - 15); // 15 is a bout half the edge of a button.
				for (Integer c : board.getCaptures(i)) {
					Line line = new Line(getX(i, 0.8), getY(i, 0.8), getX(c, 0.8), getY(c, 0.8));
					pane.getChildren().add(line);
				}
				pane.getChildren().add(button);
			}
		}
		
		public Move getMove() {
			return selectedMove;
		}
	}
	
	@Override
	public Move pickMove(String avatar) {
		ChoseMoveDialog dialog = new ChoseMoveDialog(avatar);
		dialog.showAndWait();
		return dialog.getMove();
	}
	
	@Override
	public void informEnd(List<String> winners) {
		boolean won = avatars.stream().filter(avatar -> winners.contains(avatar)).count() > 0;
		final StringBuilder scores = new StringBuilder();
		board.getScores(leftTokensGrantee).forEach((a, s) -> scores.append(a + ": " + s + "\n"));

		Alert alert = new Alert(AlertType.INFORMATION, scores.toString());
		alert.setTitle("Game ended");
		alert.setHeaderText(won ? "You won !" : "You lose");
		alert.initStyle(StageStyle.UTILITY);
		alert.initModality(Modality.APPLICATION_MODAL);
		alert.showAndWait();
	}

}
