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

/**
 * This package contains the base classes for the default graphical interface
 * launching games in MetaBoard. The Launcher class is not meant to be
 * subclassed. It launches an application allowing someone to launch a game
 * between players on a chosen game and on a chosen board.
 * 
 * The Maker interfaces are factories for objects useful for the game. There is
 * always a getConfigPane() allowing the factory to ask user for some
 * parameters, and a get*() method supposed to construct an instance from the
 * parameters given by the user.
 */
package FX;
