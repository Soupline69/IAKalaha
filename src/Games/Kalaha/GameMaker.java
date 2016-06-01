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
import java.util.List;
import java.util.Map;

import FX.AvatarMaker;
import FX.BoardMaker;
import FX.PlayerMaker;
import FX.SimplePlayerMaker;
import FX.StringAvatarMaker;
import Games.Kalaha.Boards.Board;
import Games.Kalaha.Boards.FromFile;
import Games.Kalaha.Boards.Uniform;
import Games.Kalaha.Players.EmptyAI;
import Games.Kalaha.Players.HumanGUI;
import Games.Kalaha.Players.Heuristic1AI;
import Games.Kalaha.Players.Heuristic2AI;
import Games.Kalaha.Players.Heuristic3AI;
import Games.Kalaha.Players.Heuristic4AI;
import Games.Kalaha.Players.Player;
import Games.Kalaha.Players.RandomAI;
import Games.Kalaha.Players.StrongAI;
import javafx.scene.Node;
import javafx.scene.control.CheckBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

/**
 * A Kalaha Game Maker.
 * 
 * @author Fabian Pijcke
 */
public class GameMaker implements FX.GameMaker<Integer, Integer, Board, String, Game, Move, Player, GameRunner> {
	
	private final RadioButton rbOwner, rbFinisher, rbNoone;
	private final CheckBox cbEmptyCaptures;
	
	/**
	 * Constructs a Kalaha Game Maker.
	 */
	public GameMaker() {
		ToggleGroup ltg = new ToggleGroup();
		rbOwner = new RadioButton("Owner");
		rbOwner.setToggleGroup(ltg);
		rbFinisher = new RadioButton("Finisher");
		rbFinisher.setToggleGroup(ltg);
		rbNoone = new RadioButton("No one");
		rbNoone.setToggleGroup(ltg);
		rbOwner.setSelected(true);
		
		cbEmptyCaptures = new CheckBox("Empty captures");
		cbEmptyCaptures.setSelected(true);
	}

	@Override
	public Node getConfigPane() {
		HBox ltg = new HBox();
		ltg.getChildren().add(new Text("Remaining tokens are for: "));
		ltg.getChildren().add(rbOwner);
		ltg.getChildren().add(rbFinisher);
		ltg.getChildren().add(rbNoone);
		
		VBox box = new VBox();
		box.getChildren().add(ltg);
		box.getChildren().add(cbEmptyCaptures);
		
		return box;
	}

	@Override
	public List<BoardMaker<Integer, Integer, Board, String>> getBoardMakers() {
		List<BoardMaker<Integer, Integer, Board, String>> l = new ArrayList<>();
		l.add(new Uniform.Maker());
		l.add(new FromFile.Maker());
		return l;
	}

	@Override
	public List<PlayerMaker<Integer, Integer, Board, String, Game, Move, Player>> getPlayerMakers() {
		List<PlayerMaker<Integer, Integer, Board, String, Game, Move, Player>> l = new ArrayList<>();
		l.add(new SimplePlayerMaker<Integer, Integer, Board, String, Game, Move, Player>("Human GUI", HumanGUI.class));
		l.add(new SimplePlayerMaker<Integer, Integer, Board, String, Game, Move, Player>("Random AI", RandomAI.class));
		l.add(new SimplePlayerMaker<Integer, Integer, Board, String, Game, Move, Player>("Heuristic 1", Heuristic1AI.class));
		l.add(new SimplePlayerMaker<Integer, Integer, Board, String, Game, Move, Player>("Heuristic 2", Heuristic2AI.class));
		l.add(new SimplePlayerMaker<Integer, Integer, Board, String, Game, Move, Player>("Heuristic 3", Heuristic3AI.class));
		l.add(new SimplePlayerMaker<Integer, Integer, Board, String, Game, Move, Player>("Heuristic 4", Heuristic4AI.class));
		l.add(new SimplePlayerMaker<Integer, Integer, Board, String, Game, Move, Player>("Empty case", EmptyAI.class));
		l.add(new SimplePlayerMaker<Integer, Integer, Board, String, Game, Move, Player>("Strong AI", StrongAI.class));

		return l;
	}

	@Override
	public AvatarMaker<String> getNewAvatarMaker() {
		return new StringAvatarMaker();
	}

	@Override
	public Game getGame(Board board, List<String> avatars) {
		Game.LeftTokensGrantee ltg;
		if (rbOwner.isSelected()) {
			ltg = Game.LeftTokensGrantee.OWNER;
		}
		else if (rbFinisher.isSelected()) {
			ltg = Game.LeftTokensGrantee.ENDER;
		}
		else if (rbNoone.isSelected()) {
			ltg = Game.LeftTokensGrantee.NOBODY;
		}
		else {
			ltg = Game.LeftTokensGrantee.OWNER;
		}
		
		return new Game(board, ltg, cbEmptyCaptures.isSelected(), avatars);
	}

	@Override
	public GameRunner getGameRunner(Game game, Map<String, Player> players) {
		return new GameRunner(game, players);
	}
	
	@Override
	public String toString() {
		return "Kalaha";
	}

}
