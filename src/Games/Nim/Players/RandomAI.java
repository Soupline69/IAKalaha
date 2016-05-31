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

package Games.Nim.Players;

import java.util.List;
import java.util.Random;

import Games.Nim.Move;

/**
 * This AI plays randomly, but ensures to play a valid move in the endgame.
 * 
 * @author Fabian Pijcke
 */
public class RandomAI extends Player {
	
	private Random randomizer = new Random();
	
	@Override
	public Move pickMove(String avatar) {
		return new Move(randomizer.nextInt(Math.min(board.getTokenPosition(), maxLeap)) + 1);
	}

	@Override
	public void informEnd(List<String> winners) {
		// "I don't care, let's just start another game!"
	}

	@Override
	public void informAvatar(String avatar) {
		// I don't care about that trivial information.
	}

}
