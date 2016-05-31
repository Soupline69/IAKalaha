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

package Games.Nim.Boards;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import Board.Path;
import FX.BoardMaker;
import Piece.AnonymousToken;
import javafx.scene.Node;
import javafx.scene.control.Spinner;
import javafx.scene.layout.VBox;

/**
 * The Nim board is a simple path on which a token is moved towards position 0.
 * As there is only one token, we provide a facility to get the token position
 * in constant time.
 * 
 * @author Fabian Pijcke
 */
public class Default extends Path<AnonymousToken> {
	
	/**
	 * A Nim Classic Board Maker.
	 * 
	 * @author Fabian Pijcke
	 */
	public static class Maker implements BoardMaker<AnonymousToken, Integer, Default, String> {
	
		private final Spinner<Integer> initialPositionSpinner = new Spinner<>(1, Integer.MAX_VALUE, 20);

		@Override
		public Node getConfigPane() {
			return new VBox(initialPositionSpinner);
		}
		
		@Override
		public String toString() {
			return "Default";
		}
		
		@Override
		public Default getBoard(List<String> avatars) {
			return new Default(initialPositionSpinner.getValue());
		}

	}

	private AnonymousToken token;
	private AtomicInteger tokenPosition;
	
	/**
	 * Constructs a path of a given length and places the token on the last
	 * cell.
	 * 
	 * @param length
	 */
	public Default(int length) {
		super(length);
		token = new AnonymousToken();
		tokenPosition = new AtomicInteger();
		setPieceAt(length - 1, token);
	}
	
	protected Default(Default board) {
		super(board);
		token = board.token;
		tokenPosition = board.tokenPosition;
	}
	
	@Override
	public Default readOnlyBoard() {
		return new Default(this);
	}
	
	protected void copy(Default orig) {
		super.copy(orig);
		token = orig.token;
		tokenPosition = orig.tokenPosition;
	}

	@Override
	public Default clone() {
		Default clone = new Default(getLength());
		clone.copy(this);
		return clone;
	}

	/**
	 * @return the token position.
	 */
	public Integer getTokenPosition() {
		return tokenPosition.get();
	}

	@Override
	public void setPieceAt(Integer c, AnonymousToken dummy) {
		super.setPieceAt(c, token);
		tokenPosition.set(c);
	}

}
