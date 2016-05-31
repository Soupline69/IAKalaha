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

import java.util.ArrayList;

import Utils.IConsumer;

/**
 * The complete implementation of a Path board. Instances of this class are
 * meant to be passed to the game implementation, not to the users or AIs.
 * 
 * @author Fabian Pijcke
 * @param
 * 			<Piece>
 */
public class Path<Piece> implements IBoard<Piece, Integer> {

	private ArrayList<Piece> elements;
	private final int length;
	private boolean readOnly;

	/**
	 * Constructs a path that is a copy of the original one. A copy is meant to
	 * be passed to players, so that they can not alter the content of the
	 * board. Note that if there are informations on the board the player need
	 * not have access to, this constructor should not be used directly.
	 * 
	 * @param path
	 */
	protected Path(Path<Piece> path) {
		elements = path.elements;
		length = path.length;
		readOnly = true;
	}

	/**
	 * Constructs an empty path.
	 * 
	 * @param length
	 */
	public Path(int length) {
		elements = new ArrayList<>(length);
		for (int i = 0; i < length; ++i) {
			elements.add(null);
		}

		this.length = length;
		this.readOnly = false;
	}

	@Override
	public Piece getPieceAt(Integer c) {
		if (has(c)) {
			return elements.get(c);
		}
		return null;
	}

	/**
	 * @return the length of the path.
	 */
	public int getLength() {
		return length;
	}

	@Override
	public void setPieceAt(Integer c, Piece e) {
		IBoard.super.setPieceAt(c, e);

		elements.set(c, e);
	}
	@Override
	public void forEach(IConsumer<Piece> c) {
		elements.forEach(c.filter((v) -> v != null));
	}

	@Override
	public boolean has(Integer c) {
		return c >= 0 && c < length;
	}
	
	@Override
	public boolean isReadOnly() {
		return readOnly;
	}
	
	@Override
	public Path<Piece> readOnlyBoard() {
		return new Path<>(this);
	}
	
	protected void copy(Path<Piece> orig) {
		for (int i = 0; i < length; ++i) {
			if (orig.elements.get(i) != null) {
				elements.set(i, orig.elements.get(i));
			}
		}
	}

	@Override
	public Path<Piece> clone() {
		Path<Piece> clone = new Path<>(length);
		clone.copy(this);
		return clone;
	}
    

}
