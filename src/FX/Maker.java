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
import javafx.scene.text.Text;

/**
 * Makers are factories creating objects. The getConfigPane() gives a Node
 * asking user for parameters, and a getX(...) method to be defined creates an
 * instance using parameters given by the configuration pane or as arguments.
 * 
 * @author Fabian Pijcke
 *
 */
public interface Maker {

	/**
	 * @return a configuration pane asking users for parameters of the instance
	 *         being constructed. The default asks no additional parameters.
	 */
	default Node getConfigPane() {
		return new Text("");
	}
	
}
