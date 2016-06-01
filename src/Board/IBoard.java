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

package Board;

import Utils.IConsumer;

/**
 * The board contains the current state of the game.
 * 
 * @author Fabian Pijcke
 * @param <Piece>
 *            The class of pieces that will be put on the board.
 * @param <Coordinate>
 *            The coordinates used on the board.
 */
public interface IBoard<Piece, Coordinate> {
	
	/**
	 * Puts the given piece at the given coordinate. The behaviour is not
	 * specified if the piece was already present on the board.
	 * 
	 * @param coord
	 * @param piece
	 */
	default public void setPieceAt(Coordinate coord, Piece piece) {
		/*if (isReadOnly()) {
			throw new ReadOnlyBoardException();
		}
		if (!has(coord)) {
			throw new IllegalArgumentException();
		}*/
	}
	
	/**
	 * @param coord
	 * @return the piece at coordinate coord.
	 */
	public abstract Piece getPieceAt(Coordinate coord);
	
	/**
	 * @param coord
	 * @return true if and only if the coordinate coord belongs to the board.
	 */
	public abstract boolean has(Coordinate coord);
	
	/**
	 * applies a function to each of the piece on the board (skipping nulls).
	 * @param consumer
	 */
	public abstract void forEach(IConsumer<Piece> consumer);

	/**
	 * @return true if this board is read only. This should be the case for
	 *         boards passed to players so that they can choose their next move.
	 *         When a board is read only, the method setPieceAt will throw a
	 *         ReadOnlyBoardException if called.
	 */
	public abstract boolean isReadOnly();
	
	/**
	 * @return a read-only version of the board. This read-only version should
	 */
	public abstract IBoard<Piece, Coordinate> readOnlyBoard();
	
	/**
	 * @return a clone of the board; as the pieces should be immutable, the
	 *         board content is a priori untouched.
	 */
	public abstract IBoard<Piece, Coordinate> clone();
	
}
