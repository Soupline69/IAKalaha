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

import java.util.Map;

import Core.IGameRunner;
import Games.Kalaha.Boards.Board;
import Games.Kalaha.Players.Player;

/**
 * The kalaha game runs in a very typical way. The only difference is that
 * players need to be informed of the additional rules.
 * 
 * @author Fabian Pijcke
 */
public class GameRunner implements IGameRunner<Integer, Integer, Board, String, Game, Move, Player> {
	
	private final Game game;
	private final Map<String, Player> players;

	/**
	 * Constructs a Kalaha GameRunner.
	 * @param game
	 * @param players
	 */
	public GameRunner(Game game, Map<String, Player> players) {
		this.game = game;
		this.players = players;
	}
	
	@Override
	public Game getGame() {
		return game;
	}
	
	@Override
	public Map<String, Player> getPlayers() {
		return players;
	}
	
	@Override
	public void gameInit() {
		IGameRunner.super.gameInit();
		players.values().forEach(player -> {
			player.informEmptyCapture(game.getEmptyCapture());
			player.informLeftTokensGrantee(game.getLeftTokensGrantee());
		});
	}

}
