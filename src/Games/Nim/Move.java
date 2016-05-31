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

package Games.Nim;

import Core.IMove;
import Games.Nim.Boards.Default;
import Piece.AnonymousToken;

/**
 * This move moves the token towards the position 0 of a given number of
 * positions.
 * 
 * @author Fabian Pijcke
 */
public final class Move implements IMove<AnonymousToken, Integer, Default, String, Game> {

	final int leapLength;

	/**
	 * Creates a move moving the token of leapLength positions towards the position 0.
	 * 
	 * @param leapLength
	 */
	public Move(final int leapLength) {
		this.leapLength = leapLength;
	}

	@Override
	public void apply(final Game game) {
		System.out.println("Token position : " + game.getBoard().getTokenPosition() + " - picked : " + leapLength);
		Integer curC = game.getBoard().getTokenPosition();
		Integer newC = curC - leapLength;
		game.getBoard().setPieceAt(newC, null);
		game.setNextPlayer();
	}

	@Override
	public void cancel(final Game game) {
		Integer curC = game.getBoard().getTokenPosition();
		Integer newC = curC + leapLength;
		game.getBoard().setPieceAt(newC, null);
		game.setPreviousPlayer();
	}

	@Override
	public boolean isLegal(Game game) {
		return leapLength > 0 && leapLength <= game.getMaxLeap();
	}

}
