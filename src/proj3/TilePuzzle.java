/**
 * This class keeps track of the dividual TilePuzzle(3x3).
 * It will also keep track of the merit cost, g value, and
 * h value if it is being used with the TilePuzzleSolver
 * class.
 * 
 * @version 4/17/2012
 * @author Rob Avery <pw97976@umbc.edu>
 * CMSC 341 - Spring 2012 - Project 3
 * Section 02
 */

package proj3;

import java.util.StringTokenizer;

public class TilePuzzle implements Comparable<TilePuzzle> {

	//Number of tiles
	final int TOTAL_TILES = 9;
	
	//Top Row
	private int tLeft;
	private int tMiddle;
	private int tRight;
	private final static int tLeft_KEY = 0;
	private final static int tMiddle_KEY = 1;
	private final static int tRight_KEY = 2;
	
	//Middle Row
	private int mLeft;
	private int mMiddle;
	private int mRight;
	private final static int mLeft_KEY = 3;
	private final static int mMiddle_KEY = 4;
	private final static int mRight_KEY = 5;
	
	//Bottom Row
	private int bLeft;
	private int bMiddle;
	private int bRight;
	private final static int bLeft_KEY = 6;
	private final static int bMiddle_KEY = 7;
	private final static int bRight_KEY = 8;
	
	//Merit Value = h + g
	private int meritVal;
	private int hVal;
	private int gVal;
	
	//parent - where it came from
	private TilePuzzle parent;
	
	/**
	 * Creates a blank TilePuzzle
	 */
	public TilePuzzle() {
		//Do nothing
	}
	
	/**
	 * Creates a new TilePuzzle that is identical as the one
	 * being passed in
	 * @param tp - the TilePuzzle that is being copied
	 */
	public TilePuzzle( TilePuzzle tp ) {
		
		if( tp == null )
			return;
		
		//Add top row elements
		tLeft = tp.tLeft;
		tMiddle = tp.tMiddle;
		tRight = tp.tRight;
		
		//Add middle row elements
		mLeft = tp.mLeft;
		mMiddle = tp.mMiddle;
		mRight = tp.mRight;
		
		//Add bottom row elements
		bLeft = tp.bLeft;
		bMiddle = tp.bMiddle;
		bRight = tp.bRight;
		
		//Merit values
		meritVal = tp.meritVal;
		hVal = tp.hVal;
		gVal = tp.gVal;
		
		//Create the parent
		parent = tp;
		
	}
	
	/**
	 * Creates a new TilePuzzle with all the different values in each Tile
	 * and their position in the Puzzle
	 * @param p - the String that includes each Tiles value
	 * @throws TilesOutOfBounds - If the string doesn't have the correct number of tiles
	 * 			ex. not 3x3 TilePuzzle
	 */
	public TilePuzzle ( String p ) throws TilesOutOfBounds {
		
		StringTokenizer token = new StringTokenizer( p );
		
		if( token.countTokens() > TOTAL_TILES || token.countTokens() < TOTAL_TILES )
			throw new TilesOutOfBounds();
		
		//Add top row elements
		tLeft = Integer.parseInt( token.nextToken() );
		tMiddle = Integer.parseInt( token.nextToken() );
		tRight = Integer.parseInt( token.nextToken() );
		
		//Add middle row elements
		mLeft = Integer.parseInt( token.nextToken() );
		mMiddle = Integer.parseInt( token.nextToken() );
		mRight = Integer.parseInt( token.nextToken() );
		
		//Add bottom row elements
		bLeft = Integer.parseInt( token.nextToken() );
		bMiddle = Integer.parseInt( token.nextToken() );
		bRight = Integer.parseInt( token.nextToken() );
		
		//Make the parent empty
		parent = null;
		
	}
	
	/**
	 * Display the TilePuzzle
	 */
	public String toString ( ) {
		
		String top, middle, bottom;
		String merit, g, h;
		
		//Actual Tile Puzzle
		top = String.valueOf(tLeft) + " " + String.valueOf(tMiddle) + " " + String.valueOf(tRight) +"\n";
		//System.out.printf("%d %d %d\n", tRight, tMiddle, tLeft);
		
		middle = String.valueOf(mLeft) + " " + String.valueOf(mMiddle) + " " + String.valueOf(mRight) +"\n";
		//System.out.printf("%d %d %d\n", mRight, mMiddle, mLeft);
		
		bottom = String.valueOf(bLeft) + " " + String.valueOf(bMiddle) + " " + String.valueOf(bRight) + "\n";
		//System.out.printf("%d %d %d\n\n", bRight, bMiddle, bLeft);
		
		//Cost Merit, g, and h values
		merit = "Cost = " + String.valueOf(meritVal) + "\n";
		g     = "g    = " + String.valueOf(gVal)     + "\n";
		h     = "h    = " + String.valueOf(hVal);
		return top + middle + bottom + merit + g + h;		
		
	}
	
	/**
	 * Returns the number of tiles that are similar
	 * ex. 0 - both puzzles are exactly the same
	 * 	   8 - completely different
	 * 
	 * @param itp the IntegerTilePuzzle being compared to
	 * @return the count of similar tiles
	 */
	public int h ( TilePuzzle itp ) {
		
		int count = 0;
		int emptyTile = this.findEmptyTile();
		
		if( itp == null )
			return count;
				
		//Top Row
		if( tRight != itp.tRight && !(emptyTile == tRight_KEY) )
			count++;
		
		if( tMiddle != itp.tMiddle && !(emptyTile == tMiddle_KEY) )
			count++;
		
		if( tLeft != itp.tLeft && !(emptyTile == tLeft_KEY) )
			count++;
			
		//Middle Row
		if( mRight != itp.mRight && !(emptyTile == mRight_KEY) )
			count++;
		
		if( mMiddle != itp.mMiddle && !(emptyTile == mMiddle_KEY) )
			count++;
		
		if( mLeft != itp.mLeft && !(emptyTile == mLeft_KEY) )
			count++;
		
		//Bottom Row
		if( bRight != itp.bRight && !(emptyTile == bRight_KEY) )
			count++;
		
		if( bMiddle != itp.bMiddle && !(emptyTile == bMiddle_KEY) )
			count++;
		
		if( bLeft != itp.bLeft && !(emptyTile == bLeft_KEY) )
			count++;
		
		
		return count;
	
	}

	/**
	 * Returns the saved gVal in the specific object
	 * @return gVal of the class
	 */
	public int getG(){
		return gVal;
	}
	
	/**
	 * Calculate the actual merit value of the Puzzle
	 * @param h cost to goal
	 * @param g height
	 * @return meritVal
	 */
	public int setMerit( int h , int g ){
		
		hVal = h;
		gVal = g;
		meritVal = hVal + gVal;
		return meritVal;
	}
	
	/**
	 * Returns the calculated Merit Value of the object
	 * @return meritVal of the class
	 */
	public int getMerit(){
		return meritVal;
	}
	
	/**
	 * A boolean that tells if the object is equal to
	 * any of it's parents, grandparents, great-grandparents, etc.
	 * @return True - it's equal to one of it's ancestors
	 * 		   False - it's NOT equal to one of it's ancestors
	 */
	public boolean isEqualToParent( ){
		
		TilePuzzle tempP = parent;
		
		while( tempP != null ){
			
			if( tempP.h(this) == 0 )			
				return true;
			
			tempP = tempP.parent;
		}
		
		return false;
	}
	
	/**
	 * The implemented compareTo method from the Comparable class.
	 * It compared the meritValues of each.
	 */
	@Override
	public int compareTo( TilePuzzle o ) {
		if( meritVal > o.meritVal )
			return 1;
		if( meritVal < o.meritVal )
			return -1;
		else
			return 0;
	}

	/**
	 * Finds the position of where the empty Tile is
	 * in the Puzzle
	 * @return the KEY value of the empty Tile
	 */
	private int findEmptyTile(){
		
		//Top Row
		if( tLeft == 0 )
			return tLeft_KEY;
		
		if( tMiddle == 0 )
			return tMiddle_KEY;
		
		if( tRight == 0 )
			return tRight_KEY;
			
		//Middle Row
		if( mLeft == 0 )
			return mLeft_KEY;
		
		if( mMiddle == 0 )
			return mMiddle_KEY;
		
		if( mRight == 0 )
			return mRight_KEY;
		
		//Bottom Row
		if( bLeft == 0 )
			return bLeft_KEY;
		
		if( bMiddle == 0 )
			return bMiddle_KEY;
		
		if( bRight == 0 )
			return bRight_KEY;
		
		return 0;
	}

	/**
	 * Tells whether the current TilePuzzle can move the empty
	 * Tile up
	 * @return True - it can move up
	 * 		   False -  it can't move up
	 */
	public boolean canMoveUp() {
		
		int emptyKey = findEmptyTile();
		
		switch( emptyKey ){
			case 0:
			case 1:
			case 2:
				return false;
			case 3:
			case 4:
			case 5:
			case 6:
			case 7:
			case 8:
				return true;
		}

		return false;
	}
	
	/**
	 * Moves the current position of the empty Tile
	 * up one Tile
	 */
	public void moveUp() {
		
		int emptyKey = findEmptyTile();
		int temp1, temp2;
		
		switch( emptyKey ){

			case 3:
				temp1 = tLeft;
				temp2 = mLeft;
				tLeft = temp2;
				mLeft = temp1;
				break;
			case 4:
				temp1 = tMiddle;
				temp2 = mMiddle;
				tMiddle = temp2;
				mMiddle = temp1;
				break;
			case 5:
				temp1 = tRight;
				temp2 = mRight;
				tRight = temp2;
				mRight = temp1;
				break;
			case 6:
				temp1 = mLeft;
				temp2 = bLeft;
				mLeft = temp2;
				bLeft = temp1;
				break;
			case 7:
				temp1 = mMiddle;
				temp2 = bMiddle;
				mMiddle = temp2;
				bMiddle = temp1;
				break;
			case 8:
				temp1 = mRight;
				temp2 = bRight;
				mRight = temp2;
				bRight = temp1;
				break;		
		}
	}

	/**
	 * Tells whether the current TilePuzzle can move the empty
	 * Tile down
	 * @return True - it can move down
	 * 		   False -  it can't move down
	 */
	public boolean canMoveDown() {
	
		int emptyKey = findEmptyTile();
		
		switch( emptyKey ){
			case 6:
			case 7:
			case 8:
				return false;
			case 0:
			case 1:
			case 2:
			case 3:
			case 4:
			case 5:
				return true;
		}

		return false;
	}

	/**
	 * Moves the current position of the empty Tile
	 * down one Tile
	 */
	public void moveDown() {
	
		int emptyKey = findEmptyTile();
		int temp1, temp2;
		
		switch( emptyKey ){
			case 0:
				temp1 = tLeft;
				temp2 = mLeft;
				tLeft = temp2;
				mLeft = temp1;
				break;
			case 1:
				temp1 = tMiddle;
				temp2 = mMiddle;
				tMiddle = temp2;
				mMiddle = temp1;
				break;
			case 2:
				temp1 = tRight;
				temp2 = mRight;
				tRight = temp2;
				mRight = temp1;
				break;
			case 3:
				temp1 = mLeft;
				temp2 = bLeft;
				mLeft = temp2;
				bLeft = temp1;
				break;
			case 4:
				temp1 = mMiddle;
				temp2 = bMiddle;
				mMiddle = temp2;
				bMiddle = temp1;
				break;
			case 5:
				temp1 = mRight;
				temp2 = bRight;
				mRight = temp2;
				bRight = temp1;
				break;
		}
	}

	/**
	 * Tells whether the current TilePuzzle can move the empty
	 * Tile left
	 * @return True - it can move left
	 * 		   False -  it can't move left
	 */
	public boolean canMoveLeft() {
		
		int emptyKey = findEmptyTile();
		
		switch( emptyKey ){
			case 0:
			case 3:
			case 6:
				return false;
			case 1:
			case 2:
			case 4:
			case 5:
			case 7:
			case 8:
				return true;
		}

		return false;
	}

	/**
	 * Moves the current position of the empty Tile
	 * left one Tile
	 */
	public void moveLeft() {
		
		int emptyKey = findEmptyTile();
		int temp1, temp2;
		
		switch( emptyKey ){
			case 1:
				temp1 = tLeft;
				temp2 = tMiddle;
				tLeft = temp2;
				tMiddle = temp1;
				break;
			case 2:
				temp1 = tMiddle;
				temp2 = tRight;
				tMiddle = temp2;
				tRight = temp1;
				break;
			case 4:
				temp1 = mLeft;
				temp2 = mMiddle;
				mLeft = temp2;
				mMiddle = temp1;
				break;
			case 5:
				temp1 = mMiddle;
				temp2 = mRight;
				mMiddle = temp2;
				mRight = temp1;
				break;
			case 7:
				temp1 = bLeft;
				temp2 = bMiddle;
				bLeft = temp2;
				bMiddle = temp1;
				break;
			case 8:
				temp1 = bMiddle;
				temp2 = bRight;
				bMiddle = temp2;
				bRight = temp1;
				break;
		}
	}

	/**
	 * Tells whether the current TilePuzzle can move the empty
	 * Tile right
	 * @return True - it can move right
	 * 		   False -  it can't move right
	 */
	public boolean canMoveRight() {
		
		int emptyKey = findEmptyTile();
		
		switch( emptyKey ){
			case 2:
			case 5:
			case 8:
				return false;
			case 0:
			case 1:
			case 3:
			case 4:
			case 6:
			case 7:
				return true;
		}

		return false;
	}

	/**
	 * Moves the current position of the empty Tile
	 * right one Tile
	 */
	public void moveRight() {
		
		int emptyKey = findEmptyTile();
		int temp1, temp2;
		
		switch( emptyKey ){
			case 0:
				temp1 = tLeft;
				temp2 = tMiddle;
				tLeft = temp2;
				tMiddle = temp1;
				break;
			case 1:
				temp1 = tMiddle;
				temp2 = tRight;
				tMiddle = temp2;
				tRight = temp1;
				break;
			case 3:
				temp1 = mLeft;
				temp2 = mMiddle;
				mLeft = temp2;
				mMiddle = temp1;
				break;
			case 4:
				temp1 = mMiddle;
				temp2 = mRight;
				mMiddle = temp2;
				mRight = temp1;
				break;
			case 6:
				temp1 = bLeft;
				temp2 = bMiddle;
				bLeft = temp2;
				bMiddle = temp1;
				break;
			case 7:
				temp1 = bMiddle;
				temp2 = bRight;
				bMiddle = temp2;
				bRight = temp1;
				break;
		}
	}
	
}
