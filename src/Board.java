import java.util.Random;

public class Board {

    public final int ROWS = 4;
    public final int COLS = 4;

    private Piece[][] boardArray;

    //FOR DEBUGGING
    private Piece recentPiece;
    private int numPieces = 0;

    /**
     * Inits boardArray and adds the first piece
     */
    public Board(){

        // Inits boardArray with ROWS and COLS
        boardArray = new Piece[ROWS][COLS];

        // Adds a piece to the board to start the game
        addPiece();
    }

    /**
     * If the board is not full, adds a new piece at a random array location
     * If the board is full, end the game and exit the program
     */
    public void addPiece(){

        /**
         * NEED TO CHECK FOR COMBOS BEFORE ENDING THE GAME
         */

        // Random to generate ints for row and col position for new piece
        Random rand = new Random();

        // Ints to hold row and col
        int row = rand.nextInt(4);
        int col = rand.nextInt(4);

        // If the board is not full, add a piece
        if (!isFull()){

            // Until the boardArray location is vacant, generate a new boardArray location
            while (boardArray[row][col] != null){
                row = rand.nextInt(4);
                col = rand.nextInt(4);
            }

            // Add a new piece at the empty location
            boardArray[row][col] = new Piece();


            // For debugging, each new piece has a new value in ascending order
//            numPieces++;
//            recentPiece = new Piece();
//            recentPiece.setValue(numPieces);
//            boardArray[row][col] = recentPiece;

        } else {

            // If the board is full, print game over and exit the program
            System.out.println("Game over!");
            System.exit(0);
        }

        refreshDisplay();
    }

    /**
     * Moves all of the pieces in the board to the left as far as they can go without overlapping
     * or going out of bounds
     */
    public void moveLeft(){

        System.out.print("Move Left");

        // Starting at the second col, loop through the board
        for (int r = 0; r < ROWS; r++){
            for (int c = 1; c < COLS; c++){

                // If there is a piece in the array location, move it
                if (boardArray[r][c] != null){

                    // Int to hold the updated col position, starts at current col
                    int col = c;

                    // Move to the left if col is to the right of the first column and the board position to the left is empty
                    while (col > 0 && boardArray[r][col - 1] == null){
                        col--;
                    }

                    /* After finding the new location that the piece should move to, if there is a piece to the left, check to see
                     * if the two pieces have the same value. If they do, increase the piece to the left and delete the piece that
                     * would have moved
                     */
                    if ( col > 0 && boardArray[r][c].getValue() == boardArray[r][col - 1].getValue() && !boardArray[r][col - 1].hasChanged){
                        boardArray[r][col - 1].changeValue();
                        boardArray[r][c] = null;
                        boardArray[r][col - 1].hasChanged = true;
                        System.out.println("Combined!");
                    } else if (boardArray[r][col] == null){
                        // If the updated board position is empty, move the piece to the new position and set original location to null
                        boardArray[r][col] = boardArray[r][c];
                        boardArray[r][c] = null;
                    }
                }
            }
        }

        // Add a new piece after moving all pieces to the left
        addPiece();

        // Reset the entire board's hasChanged values to false
        resetHasChanged();
    }

    /**
     * Moves all of the pieces on the board to the right as far as they can go without overlapping
     * or going out of bounds
     */
    public void moveRight(){

        System.out.print("Move Right");

        // Loop through the board starting at the second to last column and moving to the left
        for (int r = 0; r < ROWS; r++){
            for (int c = COLS-2; c >= 0; c--){

                // If there is a piece in the array location, move it
                if (boardArray[r][c] != null){

                    // Int to hold the updated col position, starts at current col
                    int col = c;

                    // Move to the right if col is to the left of the last column and the board position to the right is empty
                    while (col < COLS-1 && boardArray[r][col + 1] == null){
                        col++;
                    }

                    /* After finding the new location that the piece should move to, if there is a piece to the right, check to see
                     * if the two pieces have the same value. If they do, increase the piece to the right and delete the piece that
                     * would have moved
                     */
                    if ( col < COLS-1 && boardArray[r][c].getValue() == boardArray[r][col + 1].getValue() && !boardArray[r][col + 1].hasChanged) {
                        boardArray[r][col + 1].changeValue();
                        boardArray[r][c] = null;
                        boardArray[r][col + 1].hasChanged = true;
                        System.out.println("Combined!");
                    } else if (boardArray[r][col] == null){
                        // If the updated board position is empty, move the piece to the new position and set original location to null
                        boardArray[r][col] = boardArray[r][c];
                        boardArray[r][c] = null;
                    }
                }
            }
        }

        // Add a new piece after moving all pieces to the right
        addPiece();

        // Reset the entire board's hasChanged values to false
        resetHasChanged();
    }

    /**
     * Moves all pieces in the board as far up as they can go without overlapping
     * or going out of bounds
     */
    public void moveUp(){

        System.out.print("Move Up");

        // Loops through the board starting at the second row since the top row is all the way up
        for (int r = 1; r < ROWS; r++){
            for (int c = 0; c < COLS; c++){

                // If there is a piece at the array location, move it
                if (boardArray[r][c] != null){

                    // Int to hold the updated row position, starts at current row
                    int row = r;

                    // Move up if row is below the top row and the location above is empty
                    while (row > 0 && boardArray[row - 1][c] == null){
                        row--;
                    }

                    /* After finding the new location that the piece should move to, if there is a piece to the above, check to see
                     * if the two pieces have the same value. If they do, increase the piece above and delete the piece that
                     * would have moved
                     */
                    if ( row > 0 && boardArray[r][c].getValue() == boardArray[row - 1][c].getValue() && !boardArray[row - 1][c].hasChanged){
                        boardArray[row - 1][c].changeValue();
                        boardArray[r][c] = null;
                        boardArray[row - 1][c].hasChanged = true;
                        System.out.println("Combined!");
                    } else if (boardArray[row][c] == null){
                        // If the updated array location is empty, move the piece to the new location and set original location to null
                        boardArray[row][c] = boardArray[r][c];
                        boardArray[r][c] = null;
                    }
                }
            }
        }

        // Add a new piece after moving all pieces up
        addPiece();

        // Reset the entire board's hasChanged values to false
        resetHasChanged();
    }

    /**
     * Moves all pieces in the board as far down as they can go without overlapping
     * or going out of bounds
     */
    public void moveDown(){

        System.out.print("Move Down");


        for (int r = ROWS-2; r >= 0; r--){
            for (int c = 0; c < COLS; c++){

                // If there is a piece at the array location, move it
                if (boardArray[r][c] != null){

                    // Int to hold the updated row position, starts at current row
                    int row = r;

                    // Move down if row is above the last row and the next location down is empty
                    while (row < ROWS-1 && boardArray[row + 1][c] == null){
                        row++;
                    }

                    /* After finding the new location that the piece should move to, if there is a piece to the above, check to see
                     * if the two pieces have the same value. If they do, increase the piece above and delete the piece that
                     * would have moved
                     */
                    if ( row < ROWS-1 && boardArray[r][c].getValue() == boardArray[row + 1][c].getValue() && !boardArray[row + 1][c].hasChanged){
                        boardArray[row + 1][c].changeValue();
                        boardArray[r][c] = null;
                        boardArray[row + 1][c].hasChanged = true;
                        System.out.println("Combined!");
                    } else if (boardArray[row][c] == null){
                        // If the updated array location is empty, move the piece to the new location and set original location to null
                        boardArray[row][c] = boardArray[r][c];
                        boardArray[r][c] = null;
                    }
                }
            }
        }

        // Add a new piece after moving all pieces up
        addPiece();

        // Reset the entire board's hasChanged values to false
        resetHasChanged();
    }

    /**
     * Loops through the board and returns false if any null spaces are encountered
     * @return
     */
    private boolean isFull(){

        // Loops through the board
        for (int r = 0; r < ROWS; r++){
            for (int c = 0; c < COLS; c++){

                // Returns false if the current array location is null (aka there is an empty place on the board)
                if (boardArray[r][c] == null){
                    return false;
                }
            }
        }

        // Returns true if the end of the array is reached (thus no empty spaces have been encountered)
        return true;
    }

    /**
     * Prints the boardString and a divider for each new iteration of the board
     */
    private void refreshDisplay(){
        System.out.print(" \n  ***\n");
        System.out.println(boardString());

    }

    /**
     * Loops through boardArray and adds each piece to a string and then returns the string
     * @return
     */
    private String boardString(){

        // String to hold the board
        String s = "";

        // Loops through the array
        for (int r = 0; r < ROWS; r++){
            for (int c = 0; c < COLS; c++){

                // If the boardArray location is not null, add the value of the piece to the string with a space
                if ( boardArray[r][c] != null){
                    s += boardArray[r][c].getValue() + " ";
                } else {

                    // If the boardArray location is empty, add an underscore with a space to hold the place
                    s += "_ ";
                }
            }

            // After every row, go to a new line
            s += "\n";
        }

        // Return the string
        return s;
    }

    /**
     * Loops through boardArray and resets each hasChanged value to false
     * Called after each move method
     */
    private void resetHasChanged(){

        for (int r = 0; r < ROWS; r++){
            for (int c = 0; c < COLS; c++){
                // If there is a piece, rest it's hasChanged value
               if (boardArray[r][c] != null && boardArray[r][c].hasChanged) {
                   boardArray[r][c].hasChanged = false;
                   //System.out.println("[" + r + " ," + c + "] hasChanged reset to false");
               }
            }
        }
    }
}
// "[" + r + " ," + c + "]"