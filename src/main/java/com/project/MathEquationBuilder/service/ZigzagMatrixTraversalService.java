package com.project.MathEquationBuilder.service;

import com.project.MathEquationBuilder.model.EquationResult;
import org.springframework.stereotype.Service;

@Service
public class ZigzagMatrixTraversalService {

    public EquationResult printZigzag(int[][] matrix){
        int rows = matrix.length;
        int cols = matrix[0].length;

        boolean moveLeft = true;
        EquationResult equationResult = new EquationResult();
        int[][] result =new int[rows][cols];
        int i =0, j=0;
        for (int row=0; row<rows; row++){
            if (moveLeft){
                for(int col=0; col < cols; col++){
                    result[i][j++]=matrix[row][col];
                }
            } else {
                for(int col = cols-1; col >= 0; col--){
                    result[i][j++]=matrix[row][col];
                }
            }
            i++;
            if(j >= cols) {
                j = 0;
            }
            moveLeft = !moveLeft;
        }

        equationResult.setZigzagMatrix(result);
       for(int row=0; row < rows; row++){
           for(int col=0; col < cols; col++){
               System.out.print(equationResult.getZigzagMatrix()[row][col] + " ");
           }
           System.out.println();
       }
        return equationResult;
    }

}
