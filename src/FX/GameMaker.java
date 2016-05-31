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
package FX;

import java.util.List;
import java.util.Map;

import Board.IBoard;
import Core.IMove;
import Game.IGame;

/**
 * Game Maker.
 * @see Maker
 * 
 * @author Fabian Pijcke
 *
 * @param <Piece>
 * @param <Coordinate>
 * @param <Board>
 * @param <Avatar>
 * @param <Game>
 * @param <Move>
 * @param <Player>
 * @param <GameRunner>
 */
public interface GameMaker<Piece,
		Coordinate,
		Board extends IBoard<Piece, Coordinate>,
		Avatar,
		Game extends IGame<Piece, Coordinate, Board, Avatar>,
		Move extends IMove<Piece, Coordinate, Board, Avatar, Game>,
		Player extends Core.Player<Piece, Coordinate, Board, Avatar, Game, Move>,
		GameRunner extends Core.IGameRunner<Piece, Coordinate, Board, Avatar, Game, Move, Player>> 
	extends Maker {

	/**
	 * @return a list of board makers associated to the game.
	 */
	List<BoardMaker<Piece, Coordinate, Board, Avatar>> getBoardMakers();
	
	/**
	 * @return a list of player makers associated to the game.
	 */
	List<PlayerMaker<Piece, Coordinate, Board, Avatar, Game, Move, Player>> getPlayerMakers();
	
	/**
	 * @return an avatar maker for the game.
	 */
	AvatarMaker<Avatar> getNewAvatarMaker();
	
	/**
	 * @param board
	 * @param avatars
	 * @return a Game instance.
	 */
	Game getGame(Board board, List<Avatar> avatars);
	
	/**
	 * @param game
	 * @param players
	 * @return a GameRunner for the game.
	 */
	GameRunner getGameRunner(Game game, Map<Avatar, Player> players);
	
}