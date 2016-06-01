package Games.Kalaha.Players;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import Games.Kalaha.Move;

public class EmptyAI extends Player {	
	private List<Integer> kalaha = new ArrayList<>();
	private final Random randomizer = new Random();
	
	@Override
	public Move pickMove(String avatar) {
		if(kalaha.isEmpty()) {
			foundKalaha(avatar);
		}
		
		if(canEmpty(avatar)) {
			return moveEmpty(avatar);
		} else {
			return randomMove(avatar);
		}
	}
	
	private void foundKalaha(String avatar) {
		for(int i = 0; i < board.getLength(); i++) {
			if(board.getPlayer(i).equals(avatar) && board.isKalaha(i)) {
				kalaha.add(i);
			}
		}
	}
	
	private boolean canEmpty(String avatar) { 
		if(board.getPlayer(0).equals(avatar) && (board.getPieceAt(0) > 0 && !kalaha.contains(0+board.getPieceAt(0)))) {
			return false;
		}
		
		boolean flag = true;
		int i = board.getLength() - 1;
		
		while(i > 0 && flag) {
			if(board.getPlayer(i).equals(avatar) && !board.isKalaha(i)) {
				if(board.getPieceAt(i) > 0 && !kalaha.contains(i+board.getPieceAt(i))) {
					flag = false;
				} else if(board.getPieceAt(i-1) > 0 && board.getPieceAt(i-1) - board.getPieceAt(i) != 2) {
					flag = false;
				}
			}
			i--;
		}
		
		return flag;
	}

	private Move moveEmpty(String avatar) {
		Move move = null;
		int i = board.getLength() - 1;
		
		while(i > -1 && move == null) {
			if(board.getPlayer(i).equals(avatar) && !board.isKalaha(i) && board.getPieceAt(i) > 0 && kalaha.contains(i+board.getPieceAt(i))) {
				move = new Move(i);
			}
			i--;
		}
		return move;
	}
	
	private Move randomMove(String avatar) {
		ArrayList<Integer> possibleMoves = new ArrayList<>();
		
		for (int i = 0; i < board.getLength(); i++) {
			if (board.getPlayer(i).equals(avatar) && !board.isKalaha(i) && board.getPieceAt(i) > 0) {
				possibleMoves.add(i);
			}			
		}
		
		int choice = randomizer.nextInt(possibleMoves.size());
		
		return new Move(possibleMoves.get(choice));
	}

}
