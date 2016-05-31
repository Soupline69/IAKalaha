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
package Games.Kalaha;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import Game.RoundRobin;
import Games.Kalaha.Boards.Board;

/**
 * The Kalaha is played by two or more players. Each player has a set of pits
 * and a Kalaha. The pits and Kalaha contain zero or more tokens. Each in turn,
 * the players select a pit and take all the tokens in it, then drop the tokens
 * in the following pits and his own kalaha (one token per pit/kalaha)
 * clockwise. If the last token is dropped in the player's kalaha, he can play
 * again. If the last token is dropped in an owned empty pit, then the player
 * captures this token and all the stones in the pits "attacked" by this pit.
 * 
 * @author Fabian Pijcke
 */
public class Game implements RoundRobin<Integer, Integer, Board, String> {
	
	/**
	 * An enum stating which player will receive the tokens remaining on the
	 * board when the game ends.
	 * 
	 * @author Fabian Pijcke
	 */
	public static enum LeftTokensGrantee {
		/**
		 * The player playing the last move receives all of the remaining
		 * tokens.
		 */
		ENDER,
		/**
		 * The remaining tokens are given to the player owning the pit they lie
		 * on.
		 */
		OWNER,
		/**
		 * The remaining tokens are dropped.
		 */
		NOBODY
	}
	
	private final Board board;
	private ArrayList<String> avatars;
	
	private final LeftTokensGrantee leftTokensGrantee;
	private final boolean emptyCapture;
	
	/**
	 * Constructs a new Nim game given a board, the additional rules and an
	 * avatar/player map.
	 * 
	 * @param board
	 * @param leftTokensGrantee
	 * @param emptyCapture
	 * @param avatars
	 */
	public Game(Board board, LeftTokensGrantee leftTokensGrantee, boolean emptyCapture, List<String> avatars) {
		this.avatars = new ArrayList<>();
		this.avatars.addAll(avatars);
		
		this.board = board;
		
		this.leftTokensGrantee = leftTokensGrantee;
		this.emptyCapture = emptyCapture;
	}
	
	@Override
	public Board getBoardClone() {
		return getBoard().readOnlyBoard();
	}
	
	/**
	 * @return true if and only if a capture of zero opponent's tokens is still
	 *         a capture.
	 */
	public boolean getEmptyCapture() {
		return emptyCapture;
	}
	
	/**
	 * @return the player receiving tokens left on the board when the game ends.
	 */
	public LeftTokensGrantee getLeftTokensGrantee() {
		return leftTokensGrantee;
	}
	
	@Override
	public List<String> getPlayers() {
		return avatars;
	}

	@Override
	public Board getBoard() {
		return board;
	}
	
	@Override
	public boolean isGameEnded() {
		return getBoard().getSums(false, true).containsValue(0);
	}

	@Override
	public List<String> getWinners() {
		Map<String, Integer> scores = board.getScores(leftTokensGrantee);
		
		int m = scores.values().stream().reduce(0, Math::max);
		ArrayList<String> winners = new ArrayList<>();
		avatars.stream().filter(avatar -> scores.get(avatar) == m).forEach(avatar -> winners.add(avatar));
		
		return Collections.unmodifiableList(winners);
	}
}
