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

package Core;

import java.util.Map;

import Board.IBoard;
import Game.IGame;
import Move.Movement.IllegalMovementException;

/**
 * A game runner is an utility that takes a game instance and a list of player
 * instances and that makes these players play this game. It is possible to
 * refine one aspect of the process by overriding one of its default methods.
 * 
 * @author Fabian Pijcke
 *
 * @param <Piece>
 * @param <Coordinate>
 * @param <Board>
 * @param <Avatar>
 * @param <Game>
 * @param <Move>
 * @param <Pl>
 */
public interface IGameRunner<Piece,
		Coordinate,
		Board extends IBoard<Piece, Coordinate>,
		Avatar,
		Game extends IGame<Piece, Coordinate, Board, Avatar>,
		Move extends IMove<Piece, Coordinate, Board, Avatar, Game>,
		Pl extends Core.Player<Piece, Coordinate, Board, Avatar, Game, Move>> {
	
	/**
	 * @return the game being played.
	 */
	Game getGame();
	
	/**
	 * @return the players taking part to the game.
	 */
	Map<Avatar, Pl> getPlayers();
	
	/**
	 * Informs players of parameters of the game being played. Meant to be
	 * overriden if necessary.
	 */
	default public void gameInit() {
		getPlayers().values().forEach(player -> {
			player.informBoard(getGame().getBoardClone());
			player.informAvatars(getGame().getPlayers());
		});
		getGame().getPlayers().forEach(avatar -> {
			getPlayers().get(avatar).informAvatar(avatar);
		});
	}
	
	/**
	 * Asks the next player for the move to make, and tries to apply this move.
	 * 
	 * @throws IllegalMovementException
	 */
	default public void gameStep() throws IllegalMovementException {
		Avatar currentPlayer = getGame().getCurrentPlayer();
		Move move = getPlayers().get(currentPlayer).pickMove(currentPlayer);
		if (move.isLegal(getGame())) {
			move.apply(getGame());
		}
		else {
			throw new IllegalMovementException();
		}
	}
	
	/**
	 * The main loop of the game. As long as the game is not finished, executes
	 * the next game step. By default, after two illegal moves, the current
	 * player is disqualified.
	 */
	default public void gameLoop() {
		while (!getGame().isGameEnded()) {
			try {
				gameStep();
			}
			catch (IllegalMovementException e) {
				try {
					gameStep();
				}
				catch (IllegalMovementException f) {
					getGame().disqualify(getGame().getCurrentPlayer());
				}
			}
		}
	}
	
	/**
	 * Informs players that the game is ended and the avatars of the winners.
	 */
	default public void gameFinish() {
		getPlayers().values().forEach(player -> player.informEnd(getGame().getWinners()));
	}
	
	/**
	 * Default behaviour of a game.
	 */
	default public void gameStart() {
		gameInit();
		gameLoop();
		gameFinish();
	}
	
}
