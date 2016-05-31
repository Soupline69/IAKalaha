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
 * A grid is a 2D board of fixed width and height. The elements are stored in an
 * array for efficiency purposes.
 * 
 * @author Fabian Pijcke
 * @param <Piece>
 */
public class Grid<Piece> implements IBoard<Piece, GridCoordinate> {
    
	private final boolean readOnly;
    private final ArrayList<Piece> elements;
    private final int width, height;
    
    /**
     * Constructs an empty Grid map.
     * 
     * @param width
     * @param height
     */
    public Grid(int width, int height) {
    	elements = new ArrayList<>(width * height);
    	for (int i = width * height; i > 0; --i) {
    		elements.add(null);
    	}
        this.width = width;
        this.height = height;
        this.readOnly = false;
    }
    
    @Override
    public Piece getPieceAt(GridCoordinate c) {
        if (has(c)) {
        	return elements.get(c.getY() * width + c.getX());
        }
        return null;
    }
    
    /**
     * @return the width of the grid.
     */
    public int getWidth() {
        return width;
    }
    
    /**
     * @return the height of the grid.
     */
    public int getHeight() {
        return height;
    }
    
    @Override
    public void setPieceAt(GridCoordinate c, Piece e) {
    	IBoard.super.setPieceAt(c, e);
    	
    	elements.set(c.getY() * width + c.getX(), e);
    }

    @Override
    public void forEach(IConsumer<Piece> c) {
    	elements.forEach(c.filter((v) -> v != null));
    }

    @Override
    public boolean has(GridCoordinate c) {
        return c.getX() >= 0 && c.getX() < getWidth() && c.getY() >= 0 && c.getY() < getHeight();
    }

	@Override
	public boolean isReadOnly() {
		return readOnly;
	}

	private Grid(Grid<Piece> grid) {
		this.elements = grid.elements;
		this.width = grid.width;
		this.height = grid.height;
		this.readOnly = true;
	}

	@Override
	public Grid<Piece> readOnlyBoard() {
		return new Grid<>(this);
	}
	
	protected void copy(Grid<Piece> orig) {
		for (int i = 0; i < width * height; ++i) {
			if (orig.elements.get(i) != null) {
				elements.set(i, orig.elements.get(i));
			}
		}
	}

	@Override
	public Grid<Piece> clone() {
		Grid<Piece> clone = new Grid<>(width, height);
		clone.copy(this);
		return clone;
	}
    
}
