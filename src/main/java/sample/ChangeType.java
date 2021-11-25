package sample;
import java.io.*;
import java.util.Scanner;
public class ChangeType {
    public static String inputPathToFile() {
        boolean isInCorrect;
        String path = "in.txt";
        System.out.println("Введите ссылку на файл.");
        return path;
    }

    public static int[] takeProduct(int sizeOfVector, int linesOfMatrix, int columnsOfMatrix, int[] vector, int[][] matrix) {
        int[] prod = new int[sizeOfVector];
        for (int i = 0; i < linesOfMatrix; i++) {
            prod[i] = 0;
            for (int j = 0; j < columnsOfMatrix; j++) {
                prod[i] = prod[i] + matrix[i][j] * vector[i];
            }
        }
        return prod;
    }
}
