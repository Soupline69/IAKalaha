package Games.Kalaha.Players;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Map.Entry;
import Games.Kalaha.Move;

public class StrongAI extends Player {
	protected List<Integer> kalaha = new ArrayList<>();
	protected final Random randomizer = new Random();

	@Override
	public Move pickMove(String avatar) {
		if(kalaha.isEmpty()) {
			foundKalaha(avatar);
		}

		Move move = inKalaha(avatar);
		
		if(move == null) {
			move = fillEmpty(avatar);
		}
		
		if(move == null && !kalaha.isEmpty()) {
			move = avoidKalahaEnnemy(avatar);
		}
		
		if(move == null && !kalaha.isEmpty()) {
			move = nextTurnKalaha(avatar);
		}
		
		if(move == null) {
			move = randomMove(avatar);
		}
		
		return move;
	}
	
	// OK
	private Move fillEmpty(String avatar) {
		Map<Integer, Integer> empty = getEmpty();
	
		int pos = -1;
	
		if(!empty.isEmpty()) {
			Iterator<Integer> it = empty.keySet().iterator();
			while(it.hasNext() && pos == -1) {
				int toReach = it.next();
				int i = 0;
			
				while(i < board.getLength() && pos == -1) {
					if(board.getPlayer(i).equals(avatar) && !board.isKalaha(i) && board.getPieceAt(i) > 0) {
						int result = i+board.getPieceAt(i);
						while(result > board.getLength()) {
							result -= board.getLength();
						}
						
						if(result == toReach) {
							pos = i;
						}
					}
					i++;
				}
			}
		}
		
		return pos > -1 ? new Move(pos) : null;
	}
	
	// OK 
	private Map<Integer, Integer> getEmpty() {
		Map<Integer, Integer> empty = new HashMap<>();
		
		for(int i = 0; i < board.getLength(); i++) {
			if(!board.isKalaha(i) && board.getPieceAt(i) == 0) {
				int tokenWin = 0;
				List<Integer> attacked = board.getCaptures(i);
				
				for(int attack : attacked) {
					tokenWin += board.getPieceAt(attack);
				}
				
				empty.put(i, tokenWin);
			}
		}
				
		return sortByComparator(empty);
	}
	
	// OK
	private Map<Integer, Integer> sortByComparator(Map<Integer, Integer> unsortMap) {
	    List<Entry<Integer, Integer>> list = new LinkedList<>(unsortMap.entrySet());
	
	    // Sorting the list based on values
	    Collections.sort(list, new Comparator<Entry<Integer, Integer>>() {
	    	@Override
	        public int compare(Entry<Integer, Integer> o1, Entry<Integer, Integer> o2) {
	        	return o2.getValue().compareTo(o1.getValue());
	        }
	    });
	
	    // Maintaining insertion order with the help of LinkedList
	    Map<Integer, Integer> sortedMap = new LinkedHashMap<>();
	    for (Entry<Integer, Integer> entry : list)
	    {
	        sortedMap.put(entry.getKey(), entry.getValue());
	    }
	
	    return sortedMap;
	}
	
	// OK
	private Move avoidKalahaEnnemy(String avatar) {
		List<Integer> moveToAvoid = checkKalahaEnnemy(avatar);
		int best = 0;
		int pos = -1;
		
		for(int i = 0; i < board.getLength(); i++) {
			if(board.getPlayer(i).equals(avatar) && !board.isKalaha(i)) {
				int cpt = 0;
				for(int toReach : moveToAvoid) {
					int result;
					
					if(i > toReach) {
						result = (i+board.getPieceAt(i)) - (toReach + board.getLength());
					} else {
						result = (i+board.getPieceAt(i)) - toReach;
					}
					
					if(result >= 0) {
						cpt++;
					}
				}
				
				if(cpt > 0 && cpt > best) {
					best = cpt;
					pos = i;
				}
			}
		}

		return pos > -1 ? new Move(pos) : null;
	}
	
	// OK 
	private List<Integer> checkKalahaEnnemy(String avatar) {
		List<Integer> inKalaha = new ArrayList<>();
		for(int i = 0; i < board.getLength(); i++) {
			if(!board.getPlayer(i).equals(avatar) && !board.isKalaha(i) && board.isKalaha(i+board.getPieceAt(i)) && !kalaha.contains(i+board.getPieceAt(i))) {
				inKalaha.add(i);
			}
		}
		
		return inKalaha;
	}
	
	// OK
	private Move nextTurnKalaha(String avatar) {
		int best = 0;
		int pos = -1;
		
		for(int i = 0; i < board.getLength() ; i++) {
			if(board.getPlayer(i).equals(avatar) && !board.isKalaha(i)) {
				int cpt = 0;
				for(int j = i+1 ; j < i+board.getPieceAt(i)+1; j++) {
					if(kalaha.contains(j+board.getPieceAt(j)+1) ) {
						cpt++;
					}
				}
				
				if(cpt > 0 && cpt > best) {
					best = cpt;
					pos = i;
				}	
			}
		}

		return pos > -1 ? new Move(pos) : null;
	}

	// OK
	private void foundKalaha(String avatar) {
		for(int i = 0; i < board.getLength(); i++) {
			if(board.getPlayer(i).equals(avatar) && board.isKalaha(i)) {
				kalaha.add(i);
			}
		}
	}
		
	// OK
	private Move inKalaha(String avatar) {
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
