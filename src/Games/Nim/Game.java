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

import java.util.List;

import Game.RoundRobin;
import Games.Nim.Boards.Default;
import Piece.AnonymousToken;

/**
 * The game of Nim is played on a path with a token. The players move in turn
 * the stone towards position 0. The maximum displacement is a parameter of the
 * game. The initial position of the stone is another parameter. The last player
 * is automatically declared winner. There is always a single winner. There must
 * be at least one player and there is no upper bound.
 * 
 * The players list is handled in a round-robin cushion : The first player in
 * the list is the one to play next move. When he has picked a move, he is
 * thrown back at the end of the players list.
 * 
 * @author Fabian Pijcke
 */
public class Game implements RoundRobin<AnonymousToken, Integer, Default, String> {

	private Default board;
	private List<String> players;
	private int maxLeap;

	/**
	 * Constructs a game of Nim with the given list of players (the first player
	 * plays first, and so on), the given maximum leap and the given token
	 * initial position.
	 * 
	 * @param players
	 * @param board
	 * @param maxLeap
	 */
	public Game(List<String> players, Default board, int maxLeap) {
		this.players = players;
		this.maxLeap = maxLeap;
		this.board = board;
	}

	/**
	 * @return the maximum leap allowed in this game.
	 */
	public int getMaxLeap() {
		return maxLeap;
	}

	@Override
	public Default getBoard() {
		return board;
	}
	
	@Override
	public Default getBoardClone() {
		return getBoard().clone();
	}

	@Override
	public List<String> getPlayers() {
		return players;
	}

	@Override
	public boolean isGameEnded() {
		return board.getTokenPosition() == 0 || getPlayers().size() == 1;
	}

	@Override
	public List<String> getWinners() {
		return players.subList(players.size() - 1, players.size());
	}

}
