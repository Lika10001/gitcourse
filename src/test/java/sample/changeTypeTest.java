package sample;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class changeTypeTest {
    @Test
    void testInputPathToFile(){
        String expected = "in.txt" ;
        String actual;
        actual = ChangeType.inputPathToFile();
        assertEquals(expected, actual);
    }
    @Test
    void testTakeProduct(int sizeOfVector, int linesOfMatrix, int columnsOfMatrix, int[] vector, int[][] matrix) {
        int[] expected = new int[6];
        int[] actual = new int[sizeOfVector];
        for (int i = 0; i < linesOfMatrix; i++) {
            actual[i] = 0;
            for (int j = 0; j < columnsOfMatrix; j++) {
                actual[i] = actual[i] + matrix[i][j] * vector[i];
            }
        }
        actual = ChangeType.takeProduct(sizeOfVector, linesOfMatrix, columnsOfMatrix, vector, matrix);
        assertEquals(expected, actual);
    }

}
