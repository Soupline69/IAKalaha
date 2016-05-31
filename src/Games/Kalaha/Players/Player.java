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

import Games.Kalaha.Game;
import Games.Kalaha.Move;
import Games.Kalaha.Boards.Board;

/**
 * A Kalaha Player plays like in other games but as this game has two
 * parameters, the player is informed of which set of rules is used for the
 * current game. For this reason a single instance can not play two games
 * simultaneously if the rules are not the same (unless he does not care about
 * these rules).
 * 
 * @author Fabian Pijcke
 *
 */
public abstract class Player extends Core.Player<Integer, Integer, Board, String, Game, Move> {
	
	protected Game.LeftTokensGrantee leftTokensGrantee;
	protected boolean emptyCapture; 
	
	/**
	 * Who are the remaining tokens for when the game finishes ?
	 * @param leftTokensGrantee
	 */
	public void informLeftTokensGrantee(Game.LeftTokensGrantee leftTokensGrantee) {
		this.leftTokensGrantee = leftTokensGrantee;
	}
	
	/**
	 * Is a capture of zero opposing tokens still a capture ?
	 * @param emptyCapture
	 */
	public void informEmptyCapture(boolean emptyCapture) {
		this.emptyCapture = emptyCapture;
	}
	
}
