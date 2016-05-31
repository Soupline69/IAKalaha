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
package Games.Kalaha.Players;

import java.util.ArrayList;
import java.util.Random;

import Games.Kalaha.Move;

/**
 * A Random AI for playing Kalaha Game.
 * 
 * @author Fabian Pijcke
 */
public class RandomAI extends Player {
	
	private final Random randomizer = new Random();
	
	@Override
	public Move pickMove(String avatar) {
		ArrayList<Integer> possibleMoves = new ArrayList<>();
		for (int i = 0; i < board.getLength(); ++i) {
			if (board.getPlayer(i).equals(avatar) && !board.isKalaha(i) && board.getPieceAt(i) > 0) {
				possibleMoves.add(i);
			}
		}
		int choice = randomizer.nextInt(possibleMoves.size());
		return new Move(possibleMoves.get(choice));
	}
}
