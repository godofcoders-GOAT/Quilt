[![Review Assignment Due Date](https://classroom.github.com/assets/deadline-readme-button-22041afd0340ce965d47ae6ef1cefeee28c7c493a6346c4f15d667ab976d596c.svg)](https://classroom.github.com/a/FGf4Gsoe)
[![Open in Codespaces](https://classroom.github.com/assets/launch-codespace-2972f46106e565e64193e422d61a12cf1da4916b45550586e14ef0a7c637dd04.svg)](https://classroom.github.com/open-in-codespaces?assignment_repo_id=18552129)
# Lab: Quilt

A patchwork quilt can be made by sewing together many blocks, all of the same size. Each individual block is made up of a number of small squares cut from fabric. A block can be represented as a two-dimensional array of nonblank characters, each of which stands for one small square of fabric. The entire quilt can also be represented as a two-dimensional array of completed blocks. The example below shows an array that represents a quilt made of 9 blocks (in 3 rows and 3 columns). Each block contains 20 small squares (of 4 rows by 5 columns). The quilt uses 2 different fabric squares, represented by the characters 'x' and '.'. We consider only quilts where the main block alternates with the same block flipped upside down (i.e., reflected about a horizontal line through the block's center), as in the example below. 

![alt text](.github/image.png)

> [!NOTE]
> The blocks are flipping along a horizontal access, not rotating 180 degrees even though with this particular block it ends up with the same result. 

Consider the problem of storing and displaying information about a quilt.

The class `Quilt`, whose declaration is shown below, is used to keep track of the blocks for an entire quilt. Since the pattern is based on one block, we can define an entire quilt with only one block and the number of rows and columns of that block. 

```java
public class Quilt {
    private char[][] myBlock; // A single block
    private char[][] myQuilt; // The full quilt
    private int myRowsOfBlocks;
    private int myColsOfBlocks;
    public Quilt(char[][] block, int rowsOfBlocks, int colsOfBlocks {
        /* to be implemented in part (c) */
    }
    private void buildQuilt() {
        /* to be implemented in part (b) */
    }
    private void placeBlock(int startRow, int startCol) {
        /* implementation not shown */
    }
    private void placeFlipped(int startRow, int startCol) {
        /* to be implemented in part (a) */
    }
} 
```

## Part A

Write a private function `placeFlipped`, as started below. `placeFlipped` is intended to place a flipped (upside-down) version of the block into the matrix `myQuilt` with the flipped block's upper left corner located
the `startRow`, `startCol` position in `myQuilt`.

For example, if quilt `q` contains the block shown in part A then the call

```java
q.placeFlipped(4,10);
```

would place the flipped version of `q`'s quilt `block` into matrix `myQuilt` in as the third block in the second row of quilt blocks. This is the block whose upper-left position is at position `myQuilt[4][10]`. In the diagram below, the upper-left corner of the flipped block being placed into `myQuilt` is dark gray. 

![alt text](.github/image2.png)

Complete the method `placeFlipped`. Assume that `placeFlipped` is called only with parameters that satisfy its preconditions. Assume that the instance variable `myBlock` is a character matrix that contains the pattern for a single block.

```java
/**
 * Precondition: startRow >= 0; startCol >= 0;
 * startRow + myBlock.length <= myQuilt.length
 * startCol + myBlock[0].length <= myQuilt[0].length
 * Postcondition: a flipped version of myBlock has been copied into
 * the matrix myQuilt with its upper-left corner at
 * the position startRow, startCol
 */
private void placeFlipped(int startRow, int startCol) { 
```

## Part B

Write the method `buildQuilt`, as started below. `buildQuilt` fills `myQuilt` with a character matrix in such a way that the main block alternates with the flipped version of the main block, as shown in the original example. A call to `buildQuilt()` will fill `myQuilt` with a character matrix with `myBlock` placed starting with the upper-left corner at position 0,0; the flipped block placed with its upper-left corner at position 0,5; the given block place with its upper-left corner at position 0,10; the flipped block placed with its upper-left corner at position 4,0; and so on.

In writing `buildQuilt` you must call methods `placeBlock` and `placeFlipped` specified in part A. Assume that `placeBlock` and `placeFlipped` work as specified, regardless of what you wrote in part A. Assume that the instance variable `myBlock` is a character matrix that has been loaded with the information for a single block. Assume that `myRowsOfBlocks` and `myColsOfBlocks` have been correctly initialized to contain the number of rows and columns of blocks respectively.

> [!NOTE]
> The blocks alternate both vertically and horizontally through the quilt. Notice the block that starts in position `[4][0]` in the example image above. It is flipped because the block directly above it is not flipped. Not because the block starting at `[0][10]` is not flipped. They're not just alternating as they fill. They're alternating in both directions. 

Complete the method buildQuilt below.

```java
private void buildQuilt() { 
```

## Part C

Write the code for the constructor, as started below, that initializes the quilt.

The constructor must

1. Store the basic pattern in the instance variable `myBlock`
2. Store the number of rows and columns of the block in `myRowsOfBlocks` and `myRowsOfColumns`
3. Store the complete quilt in `myQuilt`

You must use the `buildQuilt` method created in part B. Assume that `buildQuilt` functions correctly, regardless of what you wrote.

Complete the constructor below. Assume that the constructor is called only with parameters that satisfy its preconditions.

```java
/**
 * Precondition: rowsOfBlocks > 0, colsOfBlocks > 0
 * Postcondition: myRowsOfBlocks and myColsOfBlocks are initialized to
 * the number of rows and columns of blocks that make up
 * the quilt; myBlock has been resized and initialized
 * to the block pattern from the char matrix block; myQuilt
 * contains the pattern for the full quilt
 */
public Quilt(char[][] block, int rowsOfBlocks, int colsOfBlocks) { 
```

## Supporting Files

You may notice that there are files and folders in this assignment other than the ones that you are working on. Those are part of the autograding system and need to be kept as-is. Modifying these files may cause your code to be incorrectly graded, and in worst case may be considered academic dishonesty. 

Generally these will be folders that start with a period like `.github` or `.devcontainer` or Java files that start with `Test_`. 
