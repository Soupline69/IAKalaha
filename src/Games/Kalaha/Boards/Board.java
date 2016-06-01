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

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Board.Path;
import Games.Kalaha.Game;

/**
 * A Kalaha Board is composed of pits and of kalahas. Each pit and each Kalaha
 * is attributed to an avatar. Each pit "attacks" a list of pits.
 * 
 * @author Fabian Pijcke
 *
 */
public abstract class Board extends Path<Integer> {
	
	private final List<String> avatars;
	
	/**
	 * Constructs a Kalaha Board. As this class is abstract, it is only meant to
	 * be used by subclasses' constructors.
	 * 
	 * @param length
	 * @param avatars
	 */
	public Board(int length, List<String> avatars) {
		super(length);
		for (int i = 0; i < length; ++i) {
			super.setPieceAt(i, 0);
		}
		this.avatars = avatars;
	}
	
	/**
	 * Constructs a read-only board from a given board.
	 * 
	 * @param board
	 */
	protected Board(Board board) {
		super(board);
		this.avatars = board.avatars;
		
		/*setPieceAt(0, 5);
		setPieceAt(1, 3);
		setPieceAt(2, 0);
		setPieceAt(3, 0);
		setPieceAt(4, 0);
		setPieceAt(5, 0);
		setPieceAt(6, 0);
		setPieceAt(7, 5);
		setPieceAt(8, 6);
		setPieceAt(9, 2);*/

	}
	
	@Override
	public Integer getPieceAt(Integer c) {
		return super.getPieceAt(coordinate(c));
	}
	
	@Override
	public void setPieceAt(Integer c, Integer p) {
		super.setPieceAt(coordinate(c), p);
	}
	
	/**
	 * @param coordinate
	 * @return the player owning the pit/kalaha at the given coordinate.
	 */
	public abstract String getPlayer(Integer coordinate);
	
	/**
	 * @param coordinate
	 * @return true if and only if there is a kalaha at the given coordinate.
	 */
	public abstract boolean isKalaha(Integer coordinate);
	
	/**
	 * @param coordinate
	 * @return the list of pits attacked by the pit at the given coordinate.
	 */
	public abstract List<Integer> getCaptures(Integer coordinate);
	
	protected final Integer coordinate(Integer c) {
		return Math.floorMod(c, getLength());
	}
	
	/**
	 * @param kalahas
	 *            take kalahas into account ?
	 * @param pits
	 *            take pits into account ?
	 * @return the number of tokens belonging to each player.
	 */
	public HashMap<String, Integer> getSums(boolean kalahas, boolean pits) {
		HashMap<String, Integer> sums = new HashMap<>();
		avatars.forEach(avatar -> sums.put(avatar, 0));
		for (int i = 0; i < getLength(); ++i) {
			if (isKalaha(i) && kalahas || !isKalaha(i) && pits) {
				String avatar = getPlayer(i);
				sums.put(avatar, sums.get(avatar) + getPieceAt(i));
			}
		}
		return sums;
	}
	
	/**
	 * @param leftTokensGrantee
	 * @return the scores of each player according to the game rule.
	 */
	public Map<String, Integer> getScores(Game.LeftTokensGrantee leftTokensGrantee) {
		HashMap<String, Integer> scores;
		switch (leftTokensGrantee) {
		case OWNER:
			scores = getSums(true, true);
			break;
		case ENDER:
			scores = getSums(true, false);
			int bonus = getSums(false, true).values().stream().reduce(0, (a, b) -> a + b);
			String ender = avatars.get(avatars.size() - 1);
			scores.put(ender, scores.get(ender) + bonus);
			break;
		default:
		case NOBODY:
			scores = getSums(true, false);
		}
		
		return Collections.unmodifiableMap(scores);
	}
	
	@Override
	public abstract Board readOnlyBoard();
	
	@Override
	public abstract Board clone();

}
