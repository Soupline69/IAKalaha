package Games.Kalaha.Players;

import Games.Kalaha.Move;

public class Heuristic1AI extends Player {

	// maximiser le nombre de pions dans les réserves appartenant au joueur;
	@Override
	public Move pickMove(String avatar) {
		for(int i=0; i<board.getLength(); i++) {
			if(board.isKalaha(i) && board.getPlayer(i).equals(avatar)) {
				System.out.println(avatar+" a le kalaha "+i);
			}
		}
		return null;
	}
	
}
