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

import Games.Nim.Game;
import Games.Nim.Move;
import Games.Nim.Boards.Default;
import Piece.AnonymousToken;

/**
 * Implementation of a basic Nim player. The additional methods provide
 * information on the state of the game. Classes extending this one juste have
 * to implement the pickMove() and informWinners() methods.
 * 
 * Additionaly we ensure that players do not mess with their avatar once it has
 * been created.
 * 
 * @author Fabian Pijcke
 */
public abstract class Player extends Core.Player<AnonymousToken, Integer, Default, String, Game, Move> {

	protected int maxLeap;

	/**
	 * Will be called at the beginning of a game.
	 * 
	 * @param maxLeap
	 */
	public void informMaxLeap(int maxLeap) {
		this.maxLeap = maxLeap;
	}

}
