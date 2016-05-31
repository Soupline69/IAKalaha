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

import Board.IBoard;
import Core.IMove;
import Game.IGame;

/**
 * Most player classes do not require any parameter. This maker creates an
 * instance of a Player from a Player class.
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
 */
public class SimplePlayerMaker<Piece,
		Coordinate,
		Board extends IBoard<Piece, Coordinate>,
		Avatar,
		Game extends IGame<Piece, Coordinate, Board, Avatar>,
		Move extends IMove<Piece, Coordinate, Board, Avatar, Game>,
		Player extends Core.Player<Piece, Coordinate, Board, Avatar, Game, Move>>
	implements PlayerMaker<Piece, Coordinate, Board, Avatar, Game, Move, Player> {
	
	private final Class<? extends Player> playerClass;
	private final String name;
	
	/**
	 * Creates a player maker from a player class. An instance will be created
	 * using the default player class constructor.
	 * 
	 * @param name
	 *            will be shown in the graphical user interface when selecting
	 *            the player class.
	 * @param playerClass
	 */
	public SimplePlayerMaker(String name, Class<? extends Player> playerClass) {
		this.name = name;
		this.playerClass = playerClass;
	}

	@Override
	public Player getPlayer() {
		try {
			return playerClass.newInstance();
		}
		catch (IllegalAccessException | InstantiationException e) {
			e.printStackTrace();
			throw new RuntimeException("Unable to instanciate player of class " + playerClass);
		}
	}
	
	@Override
	public String toString() {
		return name;
	}

}
