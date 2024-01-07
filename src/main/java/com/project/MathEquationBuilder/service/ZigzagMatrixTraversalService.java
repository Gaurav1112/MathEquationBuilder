package com.project.MathEquationBuilder.service;

import com.project.MathEquationBuilder.model.EquationResult;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ZigzagMatrixTraversalService {

    public EquationResult printZigzag(int[][] matrix){ int rows = matrix.length;
        int cols = matrix[0].length;
        int row = 0, col = 0;
        boolean row_inc = false;
        EquationResult eqResult = new EquationResult();
        int[] result = new int[10];
        int a=0, b=0;

        // Print matrix of lower half zig-zag pattern
        int mn = Math.min(rows, cols);
        for (int len = 1; len <= mn; ++len) {
            for (int i = 0; i < len; ++i) {
                System.out.print(matrix[row][col] + " ");
                result[a++] = matrix[row][col] ;

                if (i + 1 == len)
                    break;

                // Update row or col value according
                // to the last increment
                if (row_inc) {
                    ++row;
                    --col;
                } else {
                    --row;
                    ++col;
                }
            }

            if (len == mn)
                break;

            // Update row or col value according
            // to the last increment
            if (row_inc) {
                ++row;
                row_inc = false;
            } else {
                ++col;
                row_inc = true;
            }
        }

        // Update the indexes of row and col variable
        if (row == 0) {
            if (col == cols - 1)
                ++row;
            else
                ++col;
            row_inc = true;
        } else {
            if (row == rows - 1)
                ++col;
            else
                ++row;
            row_inc = false;
        }

        // Print the next half zig-zag pattern
        int MAX = Math.max(rows, cols) - 1;
        for (int len, diag = MAX; diag > 0; --diag) {
            if (diag > mn)
                len = mn;
            else
                len = diag;

            for (int i = 0; i < len; ++i) {

                System.out.print(matrix[row][col] + " ");
                result[a++] = matrix[row][col];

                if (i + 1 == len)
                    break;

                // Update row or col value according
                // to the last increment
                if (row_inc) {
                    ++row;
                    --col;
                } else {
                    ++col;
                    --row;
                }
            }
            a++;
            // Update the indexes of row and col variable
            if (row == 0 || col == cols - 1) {
                if (col == cols - 1)
                    ++row;
                else
                    ++col;

                row_inc = true;
            } else if (col == 0 || row == rows - 1) {
                if (row == rows - 1)
                    ++col;
                else
                    ++row;

                row_inc = false;
            }

        }
        eqResult.setZigzagMatrix(result);
        return eqResult;
    }

}
