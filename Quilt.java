
public class Quilt {

    /**
     * A single block
     */
    private char[][] myBlock;

    /**
     * The entire quilt
     */
    private char[][] myQuilt;

    private int myRowsOfBlocks;
    private int myColsOfBlocks;

    public Quilt(char[][] block, int rowsOfBlocks, int colsOfBlocks) {
        /*
         * To be implemented in part C
         */
        this.myColsOfBlocks = colsOfBlocks;
        this.myRowsOfBlocks = rowsOfBlocks;
        this.myBlock = new char[block.length][block[0].length];
        for(int i = 0; i < block.length;i++){
            for(int j = 0; j < block[0].length; j++){
                this.myBlock[i][j] = block[i][j];
            }
        }
        buildQuilt();
    }

    private void buildQuilt() {
        /*
         * To be implemented in part B
         */
        this.myQuilt = new char[myBlock.length*myRowsOfBlocks][myBlock[0].length*myColsOfBlocks];
        boolean flipped = false;
        boolean rowflip = false;
        for(int i = 0; i < myRowsOfBlocks; i++){
            flipped = rowflip;
            for(int j = 0; j < myColsOfBlocks; j++){
                int startRow = i*myBlock.length;
                int startCol = j*myBlock[0].length;
                if(flipped){
                    placeFlipped(startRow, startCol);
                    flipped = !flipped;
                }else{
                    placeBlock(startRow, startCol);
                    flipped = !flipped;
                }
            }
            rowflip = !rowflip;
        }
        
    }

    private void placeBlock(int startRow, int startCol) {
        /*
         * This method is implemented for you. Odds are pretty good
         * that you'll need to call this somewhere.
         */
        for (int r = 0; r < myBlock.length; r++) {
            for (int c = 0; c < myBlock[r].length; c++) {
                myQuilt[startRow + r][startCol + c] = myBlock[r][c];
            }
        }
    }

    private void placeFlipped(int startRow, int startCol) {
        /*
         * To be implemented in part A
         */
        for(int r = this.myBlock.length-1, qRow = startRow; r >= 0; r--, qRow++){
            for(int c = 0; c < this.myBlock[0].length; c++){
                this.myQuilt[qRow][startCol+c] = myBlock[r][c];
            }
        }
    }
}