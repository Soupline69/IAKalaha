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
package FX;

import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

/**
 * In most games, the avatar is merely a String. This Maker asks the user for
 * some text and creates an Avatar (a String) from it.
 * 
 * @author Fabian Pijcke
 */
public class StringAvatarMaker implements AvatarMaker<String> {
	
	private static int playerId = 0;
	
	TextField nameField = new TextField("Player " + ++playerId);
	
	@Override
	public Node getConfigPane() {
		HBox hbox = new HBox();
		hbox.getChildren().add(new Text("Name "));
		hbox.getChildren().add(nameField);
		return hbox;
	}

	@Override
	public String getAvatar() {
		return nameField.getText();
	}

}
