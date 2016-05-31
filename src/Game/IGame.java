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

package Game;

import java.util.List;

import Board.IBoard;

/**
 * This class defines the rules of the game. The DecisionMaker need not have
 * access to instances of this class, as it would give them access to the other
 * DecisionMaker and would allow them to ask for next moves of the opponents.
 * Note that this is not an issue if only the game can create new boards and the
 * DecisionMaker opponents are sufficiently specific.
 *
 * @author Fabian Pijcke
 *
 * @param <Piece>
 * @param <Coordinate>
 * @param <Board>
 * @param <Avatar>
 */
public interface IGame<Piece,
		Coordinate,
		Board extends IBoard<Piece, Coordinate>,
		Avatar> {

	/**
	 * @return the game board.
	 */
	Board getBoard();
	
	/**
	 * @return a clone of the board (to be passed to players). This is done in
	 *         the Game class to ensure correctness of the returned type.
	 */
	Board getBoardClone();

	/**
	 * The players list may vary as the game goes on.
	 * 
	 * The game implementation decides whether the players having lost still
	 * appear in this list or not.
	 * 
	 * Note that the players will not have direct access to this method. Hence
	 * it is not necessary to protect it with, for example,
	 * Collections.unmodifiableList.
	 * 
	 * @return the players list.
	 */
	List<Avatar> getPlayers();

	/**
	 * @return the player which has to make the next move.
	 */
	Avatar getCurrentPlayer();


	/**
	 * @return true if the winners list is known already.
	 */
	boolean isGameEnded();

	/**
	 * @return the winners list.
	 */
	List<Avatar> getWinners();

	/**
	 * Disqualifies a player (after two consecutive illegal moves, for example).
	 * @param avatar
	 */
	void disqualify(Avatar avatar);
	
}
