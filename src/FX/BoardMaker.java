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

import java.util.List;

import Board.IBoard;

/**
 * Board Maker.
 * @see Maker
 * 
 * @author Fabian Pijcke
 *
 * @param <Piece>
 * @param <Coordinate>
 * @param <Board>
 * @param <Avatar>
 */
public interface BoardMaker<Piece, Coordinate, Board extends IBoard<Piece, Coordinate>, Avatar> extends Maker {

	/**
	 * @param avatars
	 * @return a Board instance.
	 */
	Board getBoard(List<Avatar> avatars);
	
	/**
	 * @return The minimum number of players required to play on this board.
	 * Default is 2.
	 */
	default int getMinPlayers() {
		return 2;
	}
	
	/**
	 * @return The maximum number of players allowed to play on this board.
	 * Default is Integer.MAX_VALUE.
	 */
	default int getMaxPlayers() {
		return Integer.MAX_VALUE;
	}

}
