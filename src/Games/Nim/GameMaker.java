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

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import FX.AvatarMaker;
import FX.BoardMaker;
import FX.PlayerMaker;
import FX.SimplePlayerMaker;
import FX.StringAvatarMaker;
import Games.Nim.Boards.Default;
import Games.Nim.Players.HumanDialogBox;
import Games.Nim.Players.Player;
import Games.Nim.Players.RandomAI;
import Piece.AnonymousToken;
import javafx.scene.Node;
import javafx.scene.control.Spinner;
import javafx.scene.layout.VBox;

/**
 * A Nim Game Maker.
 * 
 * @author Fabian Pijcke
 */
public class GameMaker implements FX.GameMaker<AnonymousToken, Integer, Default, String, Game, Move, Player, GameRunner> {
	
	private final Spinner<Integer> maxLeapSpinner = new Spinner<>(1, Integer.MAX_VALUE, 3);
	
	@Override
	public String toString() {
		return "Nim";
	}

	@Override
	public Node getConfigPane() {
		return new VBox(maxLeapSpinner);
	}

	@Override
	public Game getGame(Default board, List<String> avatars) {
		return new Game(avatars, board, maxLeapSpinner.getValue());
	}
	
	@Override
	public GameRunner getGameRunner(Game game, Map<String, Player> players) {
		return new GameRunner(game, players);
	}

	@Override
	public List<BoardMaker<AnonymousToken, Integer, Default, String>> getBoardMakers() {
		ArrayList<BoardMaker<AnonymousToken, Integer, Default, String>> ret = new ArrayList<>();
		ret.add(new Default.Maker());
		return ret;
	}

	@Override
	public List<PlayerMaker<AnonymousToken, Integer, Default, String, Game, Move, Player>> getPlayerMakers() {
		ArrayList<PlayerMaker<AnonymousToken, Integer, Default, String, Game, Move, Player>> ret = new ArrayList<>();
		ret.add(new SimplePlayerMaker<AnonymousToken, Integer, Default, String, Game, Move, Player>("Human with dialog boxes", HumanDialogBox.class));
		ret.add(new SimplePlayerMaker<AnonymousToken, Integer, Default, String, Game, Move, Player>("Random AI", RandomAI.class));
		return ret;
	}
	
	@Override
	public AvatarMaker<String> getNewAvatarMaker() {
		return new StringAvatarMaker();
	}

}
