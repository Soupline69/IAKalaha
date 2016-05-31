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

package Games.Nim.Players;

import java.util.List;
import java.util.Optional;

import Games.Nim.Move;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextInputDialog;

/**
 * Allows a human to play using a really basic GUI.
 * 
 * @author Fabian Pijcke
 */
public class HumanDialogBox extends Player {
	
	@Override
	public Move pickMove(String avatar) {
		TextInputDialog dialog = new TextInputDialog();
		dialog.setTitle("Your turn to play, " + avatar + " !");
		dialog.setHeaderText("The token is on position " + board.getTokenPosition());
		dialog.setContentText("How much do you want to move it?");
		Optional<String> result = dialog.showAndWait();
		if (result.isPresent()) {
			return new Move(Integer.parseInt(result.get()));
		}
		return pickMove(avatar);
	}
	
	@Override
	public void informEnd(List<String> winners) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Game has ended");
		alert.setContentText("The winners are " + winners.stream().reduce("", (s, t) -> s + " " + t));
		alert.showAndWait();
	}

}
