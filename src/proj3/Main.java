/**
 * This Main class will take in an argument which will have a 
 * a starting position of the TilePuzzle (3x3) and ending position.
 * 
 * @version 4/17/2012
 * @author Rob Avery <pw97976@umbc.edu>
 * CMSC 341 - Spring 2012 - Project 3
 * Section 02
 */

package proj3;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {

	/**
	 * 
	 * @param args - file directory
	 * @throws FileNotFoundException - if the file doesn't exist
	 * @throws TilesOutOfBounds - Puzzle error
	 */
	public static void main( String [] args ) throws FileNotFoundException, TilesOutOfBounds
    {
    	
        String file = args[0];
        
        String begin = "";
        String end = "";
        String tempTile;
        TilePuzzle beginPuzzle = new TilePuzzle();
        TilePuzzle endPuzzle = new TilePuzzle();
        TilePuzzleSolver tps;
        Scanner scan = null;
        
        try{
        	
        	scan = new Scanner( new File(file) );
        	
        }catch( FileNotFoundException e){
        	
        	System.out.println("File\"" + file + "\" doesn't exist");
        	System.exit(-1);
        	
        }
        
        //Create the beginning Puzzle image
        for(int i = 0; i < beginPuzzle.TOTAL_TILES; i++ ){
        	
        	tempTile = scan.next();        	
        	begin = begin.concat( tempTile + " ");
        	
        }
        beginPuzzle = new TilePuzzle( begin );
        
        //Create the ending Puzzle image
        for(int i = 0; i < endPuzzle.TOTAL_TILES; i++ ){
        	
        	tempTile = scan.next();        	
        	end = end.concat( tempTile + " ");
        	
        }
        endPuzzle = new TilePuzzle( end );

        tps = new TilePuzzleSolver( beginPuzzle, endPuzzle );
        tps.solve();
        tps.printResults();
   
    }

}
