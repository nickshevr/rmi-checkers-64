package checkers;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Board implements Remote, Serializable {
	private static final long serialVersionUID = 1L;

	private static final int NUM_PIECES = 36; // the maximum number of pieces in any game
	public final int gridSize;
	private char blackCorner;
	
	private Piece[] piecePlacement;

	/**
	 * NOTES
	 * 
	 * The top of the board is y=0, so to move pieces down, ADD to y
	 */


	// regular constructor
	public Board(int gridSize) throws Exception {
		this.gridSize = gridSize;
		int currentPiece;
		int i, j;

		// initialize the pieces
		piecePlacement = new Piece[NUM_PIECES];
		for(i=0; i<piecePlacement.length; i++)
			piecePlacement[i] = null;

		currentPiece = 0;

		// create all of the black pieces
		for (i = 0; i < 3; i++) {
			for (j = 0; j < this.gridSize; j++) {
				if ((i + j) % 2 == 1) {
					// NOTE: i == y  and  j == x
					Piece piece = new Piece(new Position(j, i), false, Piece.WHITE);
					piecePlacement[currentPiece++] = piece;
				}
			}
		}

		// create all of the white pieces
		for (i = gridSize - 1; i > this.gridSize - 4; i--) {
			for (j = 0; j <  gridSize; j++) {
				if ((i + j) % 2 == 1) {
					// NOTE: i == y  and  j == x
					Piece piece = new Piece(new Position(j, i), false, Piece.BLACK);
					piecePlacement[currentPiece++] = piece;
				}
			}
		}
	}

	// Copy constructor
	public Board(Board copy) throws RemoteException {
		this.gridSize = copy.gridSize;
		Piece[] copyPieces = copy.getPiecePlacement();

		piecePlacement = new Piece[NUM_PIECES];
		for (int i = 0; i < piecePlacement.length && copyPieces[i] != null; i++) {
			piecePlacement[i] = new Piece(copyPieces[i]);
		}

	}
	
	// getter for piece placement
		public Piece[] getPiecePlacement() throws RemoteException {
			return piecePlacement;
		}

		// setter for piecePlacement
		public void setPiecePlacement(Piece[] piecePlacement) throws Exception {
			if (piecePlacement == null) {
				throw new Exception("Wrong value");
			}
			this.piecePlacement = piecePlacement;
		}

		@Override
		public boolean equals(Object obj) {
			if (!(obj instanceof Board)) {
				return false;
			}
			Board newObj = (Board) obj;
			try {
				return Arrays.equals(newObj.getPiecePlacement(),
								this.getPiecePlacement());
			} catch (RemoteException e) {
				return false;
			}
		}
		
		public Piece getPieceAtPosition(int x, int y){
			Position pos;

			for(int i=0; i<piecePlacement.length; i++){
				if (piecePlacement[i] != null) {
					pos = piecePlacement[i].getPiecePosition();
					if(pos.getX() == x && pos.getY() == y)
						return piecePlacement[i];
				}
			}

			return null;
		}
		
		public int getPieceIndex(Position p, Piece[] pArray){
			Position pos;

			for(int i=0; i<pArray.length; i++){
				if (pArray[i] != null) {
					pos = pArray[i].getPiecePosition();
					if(pos.getX() == p.getX() && pos.getY() == p.getY())
						return i;
				}
			}

			return -1;
		}
}
	
