
import org.junit.Test;
import static org.junit.Assert.*;
import java.lang.reflect.Field;
import java.util.*;
import java.lang.reflect.Method;

@SuppressWarnings("unchecked")
public class Test_Quilt {

    public Test_Quilt() {
    }

    @Test(timeout = 250)
    public void testConstructorPrimitives() throws Exception {
        for (int i = 0; i < 10; i++) {
            int rows = (int) (Math.random() * 10 + 2);
            int cols = (int) (Math.random() * 10 + 2);

            Quilt q = new Quilt(new char[1][1], rows, cols);

            assertEquals("Rows of blocks not set correctly", rows, (int) getField(q, "myRowsOfBlocks"));
            assertEquals("Cols of blocks not set correctly", cols, (int) getField(q, "myColsOfBlocks"));
        }
    }

    @Test(timeout = 250)
    public void testConstructorMyBlock() throws Exception {
        for (int i = 0; i < 10; i++) {
            char[][] block = new char[(int) (Math.random() * 10 + 2)][(int) (Math.random() * 10 + 2)];

            for (int r = 0; r < block.length; r++) {
                for (int c = 0; c < block[r].length; c++) {
                    block[r][c] = Math.random() < 0.5 ? '.' : 'X';
                }
            }

            Quilt q = new Quilt(block, 1, 1);

            assertArrayEquals("myBlock not set correctly", block, (char[][]) getField(q, "myBlock"));
        }
    }

    @Test(timeout = 1000)
    public void testPlaceFlipped() throws Throwable {
        char[][] block = new char[][] {
                { 'X', 'X', 'X' },
                { 'X', '.', 'X' },
                { 'X', '.', 'X' }
        };
        char[][] flippedBlock = new char[][] {
                { 'X', '.', 'X' },
                { 'X', '.', 'X' },
                { 'X', 'X', 'X' }
        };

        Quilt q = new Quilt(block, 2, 2);

        // call placeFlipped
        setField(q, "myQuilt", new char[6][6]);
        setField(q, "myBlock", block);

        Method m = Quilt.class.getDeclaredMethod("placeFlipped", int.class, int.class);
        m.setAccessible(true);

        try {
            m.invoke(q, 0, 0);
        } catch (Exception e) {
            throw e.getCause();
        }

        assertArrayEquals("placeFlipped not working correctly", flippedBlock,
                copySubrange((char[][]) getField(q, "myQuilt"), 0, 0, 3, 3));

        block = new char[][] {
                { 'X', 'X', 'X', '.' },
                { 'X', '.', '.', 'X' },
                { '.', '.', '.', 'X' },
                { '.', '.', 'X', 'X' }
        };
        flippedBlock = new char[][] {
                { '.', '.', 'X', 'X' },
                { '.', '.', '.', 'X' },
                { 'X', '.', '.', 'X' },
                { 'X', 'X', 'X', '.' }
        };

        q = new Quilt(block, 6, 6);

        // call placeFlipped
        setField(q, "myQuilt", new char[12][12]);
        setField(q, "myBlock", block);

        try {
            m.invoke(q, 4, 4);
        } catch (Exception e) {
            throw e.getCause();
        }

        assertArrayEquals("placeFlipped not working correctly", flippedBlock,
                copySubrange((char[][]) getField(q, "myQuilt"), 4, 4, 4, 4));

        // 3rd test
        block = new char[][] {
                "XX..X.XX..X..".toCharArray(),
                "...XX.XX..X..".toCharArray(),
                "X.X.X..XX.XXX".toCharArray(),
                "XXX..X.XX.XX.".toCharArray(),
                ".XXX...X..XXX".toCharArray(),
                "XX...XX.X.XX.".toCharArray(),
                ".X.XXX..X..X.".toCharArray()
        };
        flippedBlock = new char[][] {
                ".X.XXX..X..X.".toCharArray(),
                "XX...XX.X.XX.".toCharArray(),
                ".XXX...X..XXX".toCharArray(),
                "XXX..X.XX.XX.".toCharArray(),
                "X.X.X..XX.XXX".toCharArray(),
                "...XX.XX..X..".toCharArray(),
                "XX..X.XX..X..".toCharArray()
        };

        q = new Quilt(block, 20, 20);

        // call placeFlipped
        setField(q, "myQuilt", new char[140][140]);
        setField(q, "myBlock", block);

        try {
            m.invoke(q, 10, 10);
        } catch (Exception e) {
            throw e.getCause();
        }

        assertArrayEquals("placeFlipped not working correctly", flippedBlock,
                copySubrange((char[][]) getField(q, "myQuilt"), 10, 10, 13, 7));

    }

    @Test(timeout = 1000)
    public void testBuildQuilt() throws Throwable {
        char[][] block = new char[][] {
                { 'X', 'X', 'X' },
                { 'X', '.', 'X' },
                { 'X', '.', 'X' }
        };

        Quilt q = new Quilt(block, 2, 2);

        // call buildQuilt
        setField(q, "myQuilt", new char[6][6]);
        setField(q, "myBlock", block);

        Method m = Quilt.class.getDeclaredMethod("buildQuilt");
        m.setAccessible(true);

        try {
            m.invoke(q);
        } catch (Exception e) {
            throw e.getCause();
        }

        char[][] expected = new char[][] {
                { 'X', 'X', 'X', 'X', '.', 'X' },
                { 'X', '.', 'X', 'X', '.', 'X' },
                { 'X', '.', 'X', 'X', 'X', 'X' },
                { 'X', '.', 'X', 'X', 'X', 'X' },
                { 'X', '.', 'X', 'X', '.', 'X' },
                { 'X', 'X', 'X', 'X', '.', 'X' }
        };

        assertArrayEquals("buildQuilt not working correctly", expected, (char[][]) getField(q, "myQuilt"));

        // 2nd test
        block = new char[][] {
                { 'X', 'X', 'X', '.' },
                { 'X', '.', '.', 'X' },
                { '.', '.', '.', 'X' },
                { '.', '.', 'X', 'X' }
        };

        q = new Quilt(block, 6, 6);

        // call buildQuilt
        setField(q, "myQuilt", new char[36][36]);
        setField(q, "myBlock", block);

        try {
            m.invoke(q);
        } catch (Exception e) {
            throw e.getCause();
        }

        expected = new char[][] {
                "XXX...XXXXX...XXXXX...XX".toCharArray(),
                "X..X...XX..X...XX..X...X".toCharArray(),
                "...XX..X...XX..X...XX..X".toCharArray(),
                "..XXXXX...XXXXX...XXXXX.".toCharArray(),
                "..XXXXX...XXXXX...XXXXX.".toCharArray(),
                "...XX..X...XX..X...XX..X".toCharArray(),
                "X..X...XX..X...XX..X...X".toCharArray(),
                "XXX...XXXXX...XXXXX...XX".toCharArray(),
                "XXX...XXXXX...XXXXX...XX".toCharArray(),
                "X..X...XX..X...XX..X...X".toCharArray(),
                "...XX..X...XX..X...XX..X".toCharArray(),
                "..XXXXX...XXXXX...XXXXX.".toCharArray(),
                "..XXXXX...XXXXX...XXXXX.".toCharArray(),
                "...XX..X...XX..X...XX..X".toCharArray(),
                "X..X...XX..X...XX..X...X".toCharArray(),
                "XXX...XXXXX...XXXXX...XX".toCharArray(),
                "XXX...XXXXX...XXXXX...XX".toCharArray(),
                "X..X...XX..X...XX..X...X".toCharArray(),
                "...XX..X...XX..X...XX..X".toCharArray(),
                "..XXXXX...XXXXX...XXXXX.".toCharArray(),
                "..XXXXX...XXXXX...XXXXX.".toCharArray(),
                "...XX..X...XX..X...XX..X".toCharArray(),
                "X..X...XX..X...XX..X...X".toCharArray(),
                "XXX...XXXXX...XXXXX...XX".toCharArray()
        };

        assertArrayEquals("buildQuilt not working correctly", expected, (char[][]) getField(q, "myQuilt"));

        // 3rd test

        block = new char[][] {
                "XX..X.XX..X..".toCharArray(),
                "...XX.XX..X..".toCharArray(),
                "X.X.X..XX.XXX".toCharArray(),
                "XXX..X.XX.XX.".toCharArray(),
                ".XXX...X..XXX".toCharArray(),
                "XX...XX.X.XX.".toCharArray(),
                ".X.XXX..X..X.".toCharArray()
        };

        q = new Quilt(block, 5, 5);

        // call buildQuilt
        setField(q, "myQuilt", new char[140][140]);
        setField(q, "myBlock", block);

        try {
            m.invoke(q);
        } catch (Exception e) {
            throw e.getCause();
        }

        expected = new char[][] {
                "XX..X.XX..X...X.XXX..X..X.XX..X.XX..X...X.XXX..X..X.XX..X.XX..X..".toCharArray(),
                "...XX.XX..X..XX...XX.X.XX....XX.XX..X..XX...XX.X.XX....XX.XX..X..".toCharArray(),
                "X.X.X..XX.XXX.XXX...X..XXXX.X.X..XX.XXX.XXX...X..XXXX.X.X..XX.XXX".toCharArray(),
                "XXX..X.XX.XX.XXX..X.XX.XX.XXX..X.XX.XX.XXX..X.XX.XX.XXX..X.XX.XX.".toCharArray(),
                ".XXX...X..XXXX.X.X..XX.XXX.XXX...X..XXXX.X.X..XX.XXX.XXX...X..XXX".toCharArray(),
                "XX...XX.X.XX....XX.XX..X..XX...XX.X.XX....XX.XX..X..XX...XX.X.XX.".toCharArray(),
                ".X.XXX..X..X.XX..X.XX..X...X.XXX..X..X.XX..X.XX..X...X.XXX..X..X.".toCharArray(),
                ".X.XXX..X..X.XX..X.XX..X...X.XXX..X..X.XX..X.XX..X...X.XXX..X..X.".toCharArray(),
                "XX...XX.X.XX....XX.XX..X..XX...XX.X.XX....XX.XX..X..XX...XX.X.XX.".toCharArray(),
                ".XXX...X..XXXX.X.X..XX.XXX.XXX...X..XXXX.X.X..XX.XXX.XXX...X..XXX".toCharArray(),
                "XXX..X.XX.XX.XXX..X.XX.XX.XXX..X.XX.XX.XXX..X.XX.XX.XXX..X.XX.XX.".toCharArray(),
                "X.X.X..XX.XXX.XXX...X..XXXX.X.X..XX.XXX.XXX...X..XXXX.X.X..XX.XXX".toCharArray(),
                "...XX.XX..X..XX...XX.X.XX....XX.XX..X..XX...XX.X.XX....XX.XX..X..".toCharArray(),
                "XX..X.XX..X...X.XXX..X..X.XX..X.XX..X...X.XXX..X..X.XX..X.XX..X..".toCharArray(),
                "XX..X.XX..X...X.XXX..X..X.XX..X.XX..X...X.XXX..X..X.XX..X.XX..X..".toCharArray(),
                "...XX.XX..X..XX...XX.X.XX....XX.XX..X..XX...XX.X.XX....XX.XX..X..".toCharArray(),
                "X.X.X..XX.XXX.XXX...X..XXXX.X.X..XX.XXX.XXX...X..XXXX.X.X..XX.XXX".toCharArray(),
                "XXX..X.XX.XX.XXX..X.XX.XX.XXX..X.XX.XX.XXX..X.XX.XX.XXX..X.XX.XX.".toCharArray(),
                ".XXX...X..XXXX.X.X..XX.XXX.XXX...X..XXXX.X.X..XX.XXX.XXX...X..XXX".toCharArray(),
                "XX...XX.X.XX....XX.XX..X..XX...XX.X.XX....XX.XX..X..XX...XX.X.XX.".toCharArray(),
                ".X.XXX..X..X.XX..X.XX..X...X.XXX..X..X.XX..X.XX..X...X.XXX..X..X.".toCharArray(),
                ".X.XXX..X..X.XX..X.XX..X...X.XXX..X..X.XX..X.XX..X...X.XXX..X..X.".toCharArray(),
                "XX...XX.X.XX....XX.XX..X..XX...XX.X.XX....XX.XX..X..XX...XX.X.XX.".toCharArray(),
                ".XXX...X..XXXX.X.X..XX.XXX.XXX...X..XXXX.X.X..XX.XXX.XXX...X..XXX".toCharArray(),
                "XXX..X.XX.XX.XXX..X.XX.XX.XXX..X.XX.XX.XXX..X.XX.XX.XXX..X.XX.XX.".toCharArray(),
                "X.X.X..XX.XXX.XXX...X..XXXX.X.X..XX.XXX.XXX...X..XXXX.X.X..XX.XXX".toCharArray(),
                "...XX.XX..X..XX...XX.X.XX....XX.XX..X..XX...XX.X.XX....XX.XX..X..".toCharArray(),
                "XX..X.XX..X...X.XXX..X..X.XX..X.XX..X...X.XXX..X..X.XX..X.XX..X..".toCharArray(),
                "XX..X.XX..X...X.XXX..X..X.XX..X.XX..X...X.XXX..X..X.XX..X.XX..X..".toCharArray(),
                "...XX.XX..X..XX...XX.X.XX....XX.XX..X..XX...XX.X.XX....XX.XX..X..".toCharArray(),
                "X.X.X..XX.XXX.XXX...X..XXXX.X.X..XX.XXX.XXX...X..XXXX.X.X..XX.XXX".toCharArray(),
                "XXX..X.XX.XX.XXX..X.XX.XX.XXX..X.XX.XX.XXX..X.XX.XX.XXX..X.XX.XX.".toCharArray(),
                ".XXX...X..XXXX.X.X..XX.XXX.XXX...X..XXXX.X.X..XX.XXX.XXX...X..XXX".toCharArray(),
                "XX...XX.X.XX....XX.XX..X..XX...XX.X.XX....XX.XX..X..XX...XX.X.XX.".toCharArray(),
                ".X.XXX..X..X.XX..X.XX..X...X.XXX..X..X.XX..X.XX..X...X.XXX..X..X.".toCharArray(),
        };

        assertArrayEquals("buildQuilt not working correctly", expected, (char[][]) getField(q, "myQuilt"));

    }

    @Test(timeout = 1000)
    public void testConstructorQuilt() throws Exception {
        char[][] block = new char[][] {
                { 'X', 'X', 'X' },
                { 'X', '.', 'X' },
                { 'X', '.', 'X' }
        };

        Quilt q = new Quilt(block, 2, 2);

        char[][] expected = new char[][] {
                { 'X', 'X', 'X', 'X', '.', 'X' },
                { 'X', '.', 'X', 'X', '.', 'X' },
                { 'X', '.', 'X', 'X', 'X', 'X' },
                { 'X', '.', 'X', 'X', 'X', 'X' },
                { 'X', '.', 'X', 'X', '.', 'X' },
                { 'X', 'X', 'X', 'X', '.', 'X' }
        };

        assertArrayEquals("Quilt not constructed correctly", expected, (char[][]) getField(q, "myQuilt"));

        // 2nd test
        block = new char[][] {
                { 'X', 'X', 'X', '.' },
                { 'X', '.', '.', 'X' },
                { '.', '.', '.', 'X' },
                { '.', '.', 'X', 'X' }
        };

        q = new Quilt(block, 6, 6);

        expected = new char[][] {
                "XXX...XXXXX...XXXXX...XX".toCharArray(),
                "X..X...XX..X...XX..X...X".toCharArray(),
                "...XX..X...XX..X...XX..X".toCharArray(),
                "..XXXXX...XXXXX...XXXXX.".toCharArray(),
                "..XXXXX...XXXXX...XXXXX.".toCharArray(),
                "...XX..X...XX..X...XX..X".toCharArray(),
                "X..X...XX..X...XX..X...X".toCharArray(),
                "XXX...XXXXX...XXXXX...XX".toCharArray(),
                "XXX...XXXXX...XXXXX...XX".toCharArray(),
                "X..X...XX..X...XX..X...X".toCharArray(),
                "...XX..X...XX..X...XX..X".toCharArray(),
                "..XXXXX...XXXXX...XXXXX.".toCharArray(),
                "..XXXXX...XXXXX...XXXXX.".toCharArray(),
                "...XX..X...XX..X...XX..X".toCharArray(),
                "X..X...XX..X...XX..X...X".toCharArray(),
                "XXX...XXXXX...XXXXX...XX".toCharArray(),
                "XXX...XXXXX...XXXXX...XX".toCharArray(),
                "X..X...XX..X...XX..X...X".toCharArray(),
                "...XX..X...XX..X...XX..X".toCharArray(),
                "..XXXXX...XXXXX...XXXXX.".toCharArray(),
                "..XXXXX...XXXXX...XXXXX.".toCharArray(),
                "...XX..X...XX..X...XX..X".toCharArray(),
                "X..X...XX..X...XX..X...X".toCharArray(),
                "XXX...XXXXX...XXXXX...XX".toCharArray()
        };

        assertArrayEquals("Quilt not constructed correctly", expected, (char[][]) getField(q, "myQuilt"));

        // 3rd test

        block = new char[][] {
                "XX..X.XX..X..".toCharArray(),
                "...XX.XX..X..".toCharArray(),
                "X.X.X..XX.XXX".toCharArray(),
                "XXX..X.XX.XX.".toCharArray(),
                ".XXX...X..XXX".toCharArray(),
                "XX...XX.X.XX.".toCharArray(),
                ".X.XXX..X..X.".toCharArray()
        };

        q = new Quilt(block, 5, 5);

        expected = new char[][] {
                "XX..X.XX..X...X.XXX..X..X.XX..X.XX..X...X.XXX..X..X.XX..X.XX..X..".toCharArray(),
                "...XX.XX..X..XX...XX.X.XX....XX.XX..X..XX...XX.X.XX....XX.XX..X..".toCharArray(),
                "X.X.X..XX.XXX.XXX...X..XXXX.X.X..XX.XXX.XXX...X..XXXX.X.X..XX.XXX".toCharArray(),
                "XXX..X.XX.XX.XXX..X.XX.XX.XXX..X.XX.XX.XXX..X.XX.XX.XXX..X.XX.XX.".toCharArray(),
                ".XXX...X..XXXX.X.X..XX.XXX.XXX...X..XXXX.X.X..XX.XXX.XXX...X..XXX".toCharArray(),
                "XX...XX.X.XX....XX.XX..X..XX...XX.X.XX....XX.XX..X..XX...XX.X.XX.".toCharArray(),
                ".X.XXX..X..X.XX..X.XX..X...X.XXX..X..X.XX..X.XX..X...X.XXX..X..X.".toCharArray(),
                ".X.XXX..X..X.XX..X.XX..X...X.XXX..X..X.XX..X.XX..X...X.XXX..X..X.".toCharArray(),
                "XX...XX.X.XX....XX.XX..X..XX...XX.X.XX....XX.XX..X..XX...XX.X.XX.".toCharArray(),
                ".XXX...X..XXXX.X.X..XX.XXX.XXX...X..XXXX.X.X..XX.XXX.XXX...X..XXX".toCharArray(),
                "XXX..X.XX.XX.XXX..X.XX.XX.XXX..X.XX.XX.XXX..X.XX.XX.XXX..X.XX.XX.".toCharArray(),
                "X.X.X..XX.XXX.XXX...X..XXXX.X.X..XX.XXX.XXX...X..XXXX.X.X..XX.XXX".toCharArray(),
                "...XX.XX..X..XX...XX.X.XX....XX.XX..X..XX...XX.X.XX....XX.XX..X..".toCharArray(),
                "XX..X.XX..X...X.XXX..X..X.XX..X.XX..X...X.XXX..X..X.XX..X.XX..X..".toCharArray(),
                "XX..X.XX..X...X.XXX..X..X.XX..X.XX..X...X.XXX..X..X.XX..X.XX..X..".toCharArray(),
                "...XX.XX..X..XX...XX.X.XX....XX.XX..X..XX...XX.X.XX....XX.XX..X..".toCharArray(),
                "X.X.X..XX.XXX.XXX...X..XXXX.X.X..XX.XXX.XXX...X..XXXX.X.X..XX.XXX".toCharArray(),
                "XXX..X.XX.XX.XXX..X.XX.XX.XXX..X.XX.XX.XXX..X.XX.XX.XXX..X.XX.XX.".toCharArray(),
                ".XXX...X..XXXX.X.X..XX.XXX.XXX...X..XXXX.X.X..XX.XXX.XXX...X..XXX".toCharArray(),
                "XX...XX.X.XX....XX.XX..X..XX...XX.X.XX....XX.XX..X..XX...XX.X.XX.".toCharArray(),
                ".X.XXX..X..X.XX..X.XX..X...X.XXX..X..X.XX..X.XX..X...X.XXX..X..X.".toCharArray(),
                ".X.XXX..X..X.XX..X.XX..X...X.XXX..X..X.XX..X.XX..X...X.XXX..X..X.".toCharArray(),
                "XX...XX.X.XX....XX.XX..X..XX...XX.X.XX....XX.XX..X..XX...XX.X.XX.".toCharArray(),
                ".XXX...X..XXXX.X.X..XX.XXX.XXX...X..XXXX.X.X..XX.XXX.XXX...X..XXX".toCharArray(),
                "XXX..X.XX.XX.XXX..X.XX.XX.XXX..X.XX.XX.XXX..X.XX.XX.XXX..X.XX.XX.".toCharArray(),
                "X.X.X..XX.XXX.XXX...X..XXXX.X.X..XX.XXX.XXX...X..XXXX.X.X..XX.XXX".toCharArray(),
                "...XX.XX..X..XX...XX.X.XX....XX.XX..X..XX...XX.X.XX....XX.XX..X..".toCharArray(),
                "XX..X.XX..X...X.XXX..X..X.XX..X.XX..X...X.XXX..X..X.XX..X.XX..X..".toCharArray(),
                "XX..X.XX..X...X.XXX..X..X.XX..X.XX..X...X.XXX..X..X.XX..X.XX..X..".toCharArray(),
                "...XX.XX..X..XX...XX.X.XX....XX.XX..X..XX...XX.X.XX....XX.XX..X..".toCharArray(),
                "X.X.X..XX.XXX.XXX...X..XXXX.X.X..XX.XXX.XXX...X..XXXX.X.X..XX.XXX".toCharArray(),
                "XXX..X.XX.XX.XXX..X.XX.XX.XXX..X.XX.XX.XXX..X.XX.XX.XXX..X.XX.XX.".toCharArray(),
                ".XXX...X..XXXX.X.X..XX.XXX.XXX...X..XXXX.X.X..XX.XXX.XXX...X..XXX".toCharArray(),
                "XX...XX.X.XX....XX.XX..X..XX...XX.X.XX....XX.XX..X..XX...XX.X.XX.".toCharArray(),
                ".X.XXX..X..X.XX..X.XX..X...X.XXX..X..X.XX..X.XX..X...X.XXX..X..X.".toCharArray(),
        };

        assertArrayEquals("Quilt not constructed correctly", expected, (char[][]) getField(q, "myQuilt"));
    }

    // Helper methods

    private <T> T getField(Object instance, String fieldName) throws Exception {
        Field fld = instance.getClass().getDeclaredField(fieldName);
        fld.setAccessible(true);
        return (T) fld.get(instance);
    }

    private void setField(Object instance, String fieldName, Object value) throws Exception {
        Field fld = instance.getClass().getDeclaredField(fieldName);
        fld.setAccessible(true);
        if (value instanceof Integer) {
            fld.setInt(instance, (int) value);
        } else {
            fld.set(instance, value);
        }

    }

    private String dumpMatrix(char[][] matrix) {
        StringBuilder sb = new StringBuilder();
        for (int r = 0; r < matrix.length; r++) {
            for (int c = 0; c < matrix[r].length; c++) {
                sb.append(matrix[r][c]);
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    /**
     * @see https://stackoverflow.com/a/27343950
     */
    private static char[][] copySubrange(char[][] source, int x, int y, int width, int height) {
        if (source == null) {
            return null;
        }
        if (source.length == 0) {
            return new char[0][0];
        }
        if (height < 0) {
            throw new IllegalArgumentException("height must be positive");
        }
        if (width < 0) {
            throw new IllegalArgumentException("width must be positive");
        }
        if ((y + height) > source.length) {
            throw new IllegalArgumentException("subrange too high");
        }
        char[][] dest = new char[height][width];
        for (int destY = 0; destY < height; destY++) {
            char[] srcRow = source[(y + destY)];
            if ((x + width) > srcRow.length) {
                throw new IllegalArgumentException("subrange too wide");
            }
            System.arraycopy(srcRow, x, dest[destY], 0, width);
        }
        return dest;
    }

}
