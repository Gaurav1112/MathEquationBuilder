package com.project.MathEquationBuilder.service;

import org.springframework.stereotype.Service;

@Service
public class InverseMajorMinorMatrixService {

    private void swap(int[][] arr, int i, int j, int k){
        int temp = arr[i][j];
        arr[i][j] = arr[i][k];
        arr[i][k] = temp;
    }
    public  int[][] inverseMajorMinorMatrix(int[][] arr){
        int rows = arr.length;
        int cols = arr[0].length;

        System.out.println("The Original Matrix is :" );
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                System.out.print(arr[row][col] + " ");
            }
            System.out.println();
        }
        for (int row = 0; row < rows; row++){
            for (int col =0; col < cols; col++){
                if(row == col){
                    int k = cols -1 - row;
                    swap(arr, row, col, k);
                }
            }
        }

        System.out.println("The Inverse Matrix is :" );
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                System.out.print(arr[row][col] + " ");
            }
            System.out.println();
        }
        return arr;
    }

}
