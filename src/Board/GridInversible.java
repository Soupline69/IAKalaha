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

package Board;

import java.util.HashMap;
import java.util.Map;

import Utils.IConsumer;

/**
 * An inversible grid keeps track of the positions of the pieces on it. This is
 * particularly useful for games with several piece classes, such as Chess.
 * 
 * @author shepard
 * @param <Piece>
 */
public class GridInversible<Piece> extends Grid<Piece> implements IInversible<Piece, GridCoordinate> {
	
	private final Map<Piece, GridCoordinate> inverse;
	
	/**
	 * Creates a new empty grid of given width and height.
	 * 
	 * @param width
	 * @param height
	 */
	public GridInversible(int width, int height) {
		super(width, height);
		this.inverse = new HashMap<>();
	}
	
	@Override
	public void setPieceAt(GridCoordinate c, Piece e) {
		super.setPieceAt(c, e);
		inverse.put(e, c);
	}

	@Override
	public GridCoordinate getPositionOf(Piece piece) {
		return inverse.get(piece);
	}
	
	@Override
	public void forEach(IConsumer<Piece> c) {
		inverse.keySet().forEach(c);
	}
	
	protected void copy(GridInversible<Piece> orig) {
		super.copy(orig);
		for (Piece piece : orig.inverse.keySet()) {
			inverse.put(piece, orig.inverse.get(piece));
		}
	}
	
	@Override
	public GridInversible<Piece> clone() {
		GridInversible<Piece> clone = new GridInversible<>(getWidth(), getHeight());
		clone.copy(this);
		return clone;
	}

}
