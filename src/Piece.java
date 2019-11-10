public class Piece {

    /**
     * int to hold the value of the piece
     */
    private int value;

    /**
     * boolean to hold keep track of changes in value (used to prevent pieces
     * that just changed in the current move from being combined)
     */
    public boolean hasChanged;

    /**
     * Constructor that sets value to 2 (want to change)
     */
    public Piece(){

        // Value always starts at 2
        value = 2;

        /*
         * Want to change this later so that as the largest piece increases, the is a chance that the new piece will be
         * larger than 2
         */
    }

    /**
     * Multiplies the value by 2
     */
    public void changeValue(){

        value *= 2;
    }

    /**
     * Returns value
     * @return value
     */
    public int getValue(){
        return value;
    }

    public void setValue(int v){
        value = v;
    }
}
