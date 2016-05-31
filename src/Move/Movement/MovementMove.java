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

package Move.Movement;

import Board.IBoard;
import Core.IMove;
import Game.IGame;

/**
 * This model of movement is indicated when the pieces are moved from one place
 * to another on the board. It is not adapted to games where moves can combine
 * several atomic movements (like checkers).
 * 
 * @author Fabian Pijcke
 *
 * @param <Piece>
 * @param <Coordinate>
 * @param <Board>
 * @param <Avatar>
 * @param <Game>
 */
public abstract class MovementMove<Piece,
		Coordinate,
		Board extends IBoard<Piece, Coordinate>,
		Avatar,
		Game extends IGame<Piece, Coordinate, Board, Avatar>>
	implements IMove<Piece, Coordinate, Board, Avatar, Game> {

	private final Coordinate start, destination;
	private Piece startPiece, destinationPiece;

	/**
	 * Constructs a basic movement move.
	 * 
	 * @param start
	 * @param destination
	 */
	public MovementMove(Coordinate start, Coordinate destination) {
		this.start = start;
		this.destination = destination;
	}

	/**
	 * @return the start position of the move.
	 */
	public Coordinate getStart() {
		return start;
	}

	/**
	 * @return the destination position of the move.
	 */
	public Coordinate getDestination() {
		return destination;
	}

	@Override
	public void apply(Game game) {
		startPiece = game.getBoard().getPieceAt(getStart());
		destinationPiece = game.getBoard().getPieceAt(getDestination());
		game.getBoard().setPieceAt(getStart(), null);
		game.getBoard().setPieceAt(getDestination(), startPiece);
	}
	
	@Override
	public void cancel(Game game) {
		game.getBoard().setPieceAt(getDestination(), destinationPiece);
		game.getBoard().setPieceAt(getStart(), startPiece);
	}
	
}
