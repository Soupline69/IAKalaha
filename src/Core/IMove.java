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

package Core;

import Board.IBoard;
import Game.IGame;

/**
 * A move is made of two actions. One going forward and the other backward.
 * 
 * @author Fabian Pijcke
 *
 * @param <Piece>
 * @param <Coordinate>
 * @param <Board>
 * @param <Avatar>
 * @param <Game>
 */
public interface IMove<Piece,
		Coordinate,
		Board extends IBoard<Piece, Coordinate>,
		Avatar,
		Game extends IGame<Piece, Coordinate, Board, Avatar>> {

	/**
	 * Applies the move.
	 * 
	 * @param game
	 */
	void apply(Game game);

	/**
	 * Cancels the move. Assuming the state of the board is the one after apply
	 * has been called, the board should be in its state just before after this
	 * method is applied. As the framework is built with the construction of AIs
	 * in mind, the cancelling of a movement is mandatory if we don't want the
	 * AI to reimplement the game.
	 * 
	 * @param game
	 */
	void cancel(Game game);
	
	/**
	 * @param game
	 * @return true if and only if the move is legal according to the game rules.
	 */
	boolean isLegal(Game game);
}
