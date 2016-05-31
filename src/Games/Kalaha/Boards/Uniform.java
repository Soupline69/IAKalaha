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
package Games.Kalaha.Boards;

import java.util.ArrayList;
import java.util.List;

import FX.BoardMaker;
import javafx.scene.Node;
import javafx.scene.control.Spinner;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

/**
 * Creates an uniform board where each avatar has the same number of pits in
 * sequence, followed by their kalaha and each pit has the same number of
 * starting tokens.
 * 
 * @author Fabian Pijcke
 */
public class Uniform extends Board {

	/**
	 * An uniform Kalaha Board Maker.
	 * 
	 * @author Fabian Pijcke
	 */
	public static class Maker implements BoardMaker<Integer, Integer, Board, String> {

		private final Spinner<Integer> pitsPerPlayerSpinner = new Spinner<>(1, Integer.MAX_VALUE / 2 - 2, 6);
		private final Spinner<Integer> tokensPerPitSpinner = new Spinner<>(1, Integer.MAX_VALUE / 12, 4);
		
		@Override
		public Node getConfigPane() {
			GridPane grid = new GridPane();
			grid.add(new Text("Pits per player : "), 0, 0);
			grid.add(pitsPerPlayerSpinner, 1, 0);
			grid.add(new Text("Tokens per pit : "), 0, 1);
			grid.add(tokensPerPitSpinner, 1, 1);
			return grid;
		}

		@Override
		public Board getBoard(List<String> avatars) {
			return new Uniform(pitsPerPlayerSpinner.getValue(), tokensPerPitSpinner.getValue(), avatars);
		}
		
		@Override
		public String toString() {
			return "Uniform";
		}
		
	}
	
	private final List<String> avatars;
	private final int playerLength; // Not necessary, but boring to compute everytime.

	/**
	 * Constructs a new uniform Kalaha Board.
	 * 
	 * @param pitsPerPlayer
	 * @param tokensPerPit
	 * @param avatars
	 */
	public Uniform(int pitsPerPlayer, int tokensPerPit, List<String> avatars) {
		super(avatars.size() * (pitsPerPlayer + 1), avatars);
		
		this.playerLength = pitsPerPlayer + 1;
		
		this.avatars = new ArrayList<>();
		this.avatars.addAll(avatars);
		
		for (int i = 0; i < getLength(); ++i) {
			if (!isKalaha(i)) {
				setPieceAt(i, tokensPerPit);
			}
		}
	}

	@Override
	public String getPlayer(Integer c) {
		return avatars.get(coordinate(c) / playerLength);
	}

	@Override
	public boolean isKalaha(Integer c) {
		return coordinate(c + 1) % playerLength == 0;
	}
	
	@Override
	public List<Integer> getCaptures(Integer c) {
		if (isKalaha(c)) {
			return new ArrayList<>();
		}
		
		int baseC = c % playerLength;
		int baseOpp = (playerLength - 2) - baseC;
		int opp = c / playerLength * playerLength + baseOpp;
		
		ArrayList<Integer> ret = new ArrayList<>();
		for (int i = 1; i < avatars.size(); ++i) {
			ret.add(coordinate(opp + i * playerLength));
		}
		return ret;
	}
	
	protected Uniform(Uniform board) {
		super(board);
		this.avatars = board.avatars;
		this.playerLength = board.playerLength;
	}
	
	@Override
	public Uniform readOnlyBoard() {
		return new Uniform(this);
	}
	
	@Override
	public Uniform clone() {
		Uniform clone = new Uniform(playerLength - 1, 0, avatars);
		for (int i = 0; i < clone.getLength(); ++i) {
			clone.setPieceAt(i, getPieceAt(i));
		}
		return clone;
	}
}
