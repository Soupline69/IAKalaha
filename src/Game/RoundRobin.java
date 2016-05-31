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

import Board.IBoard;

/**
 * In several games, the round-robin principle is applied to players. I.e., the
 * players play in turn, one after the other always in the same order. This
 * interface provides default implementations for this behaviour.
 * 
 * @author Fabian Pijcke
 *
 * @param <Piece>
 * @param <Coordinate>
 * @param <Board>
 * @param <Avatar>
 */
public interface RoundRobin<Piece, Coordinate, Board extends IBoard<Piece, Coordinate>, Avatar> extends IGame<Piece, Coordinate, Board, Avatar> {

	@Override
	public default Avatar getCurrentPlayer() {
		return getPlayers().get(0);
	}
	
	/**
	 * Forwards in the player order.
	 */
	public default void setNextPlayer() {
		getPlayers().add(getPlayers().remove(0));
	}
	
	/**
	 * Backwards in the player order.
	 */
	public default void setPreviousPlayer() {
		getPlayers().add(0, getPlayers().remove(getPlayers().size() - 1));
	}
	
	@Override
	public default void disqualify(Avatar avatar) {
		getPlayers().remove(avatar);
	}
}
