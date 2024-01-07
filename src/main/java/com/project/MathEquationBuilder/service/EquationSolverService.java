package com.project.MathEquationBuilder.service;

import com.project.MathEquationBuilder.model.EquationResult;
import org.springframework.stereotype.Service;

@Service
public class EquationSolverService {

    public EquationResult solveEquations(int[][] coefficients, int[] constants) {
        try {
            float[][] invA = new float[3][3];
            invA = inverse(coefficients, invA, 3, 3);

            if (invA == null) {
                System.out.println("Cannot find inverse for singular matrix");
                return null;
            }

            float[] result = solveLinearEquations(invA, constants);
            for (int constant: constants) {
                System.out.print(constant+ " ");
            }
            System.out.print("{");
            for(float val: result){
                System.out.printf("%.6f ", val );
            }
            System.out.print("}");

            EquationResult equationResult = new EquationResult();
            equationResult.setVariables(result);
            if(determinant(coefficients, 3 ) !=0) {
                equationResult.setArea((determinant(coefficients, 3)) / 2);
                equationResult.setDeterminant(determinant(coefficients, 3));
            }
            int[][] adj = new int[3][3];
            adjoint(coefficients, adj, 3);
            equationResult.setAdjacent(adj);
            equationResult.setInverse(invA);
            equationResult.setTranspose(transpose(coefficients));
            String descriptValue = getDesciptiveValue();
            equationResult.setDescriptValue(descriptValue);
            String topLeftValue= topLeftValue();
            equationResult.setTopLeftValue(topLeftValue);


            return equationResult;
        } catch (Exception e) {
            return null;
        }
    }

    private String topLeftValue() {
        String result="We have lot of properties associated with adjoint of matrix :";
      result += "\n-----------------------------\n";
        result += "adj(AB) = adj(B) * adj(A)";
      result += "\n-----------------------------\n";
        result += "adj(kA) =  k^(n-1) * adj(A)";
      result += "\n-----------------------------\n";
        result += "inverse(A) = adj(A) / determinant(A)";
      result += "\n-----------------------------\n";
        result += "inverse(inverse(A)) = A";
      result += "\n-----------------------------\n";
        result += "inverse(AB) = inverse(B) * inverse(A)";
      result += "\n-----------------------------\n";
        result += "A*adj(A) = determinant(A) * I --> Here I is identity matrix of same order";
      result += "\n-----------------------------\n";
        result += "Only matrix with determinant more than 0, can have inversal. Matrix with determinant 0 is singular matrix";
        return result;
    }

    private String getDesciptiveValue() {
        String result = "";
        result = " The values of the variables are calculated with the formula.";
        result += "\n-----------------------------\n";
        result += " Formula: X = inverseOf(A)*B";
        result += "\n-----------------------------\n";
        result += " To find the inverse we have a formula: ";
        result += "\n-----------------------------\n";
        result += " Formula: inverseOf(A) = adjoint(A)/ det(A)";
        result += "\n-----------------------------\n";
        result += " Adjoint is formed by taking the transpose of the cofactor matirx.To find the adjoint(A) we have a formula: ";
        result += "\n-----------------------------\n";
        result += " Formula: adjoint(A) = inverseOf(A)* determinant(A)";
        result += "\n-----------------------------\n";
        result += " Determinant of A is the is defined as a special number that is defined only for square matrices";
        result += "\n-----------------------------\n";
        result += " Determimant of 2D matrix is : (ad-bc)";
        result += "\n-----------------------------\n";
        result += " Dtereminant of 3D matrix is : a(ei-fh) - b(di-fg) + c(dh-eg)";
        result += "\n-----------------------------\n";
        result += "  How to calculate ?";
        result += "\n-----------------------------\n";
        result += " For each element of the first row or first column get the cofactor of those elements";
        result += "\n-----------------------------\n";
        result += " Then multiply the element with the determinant of the corresponding cofactor.";
        result += "\n-----------------------------\n";
        result += " Finally, add them with alternate signs. As a base case, the value of determinant of 1*1 matrix is the single value itself.";
        result += "\n-----------------------------\n";
        result += "  Determinant of a matrix using Determinant properties";
        result += "\n-----------------------------\n";
        result += " Case1: if there is no non zero element. In this case, the determinant of a mtrix is 0";
        result += "\n-----------------------------\n";
        result += " Case2: if there exists a non zero element there exists 2 case:";
        result += "\n-----------------------------\n";
        result += " Case A: if the index is with respective diagonal row element, Using the determinant properties make all the column elements down to zero.";
        result += "\n-----------------------------\n";
        result += "  Case B: Swap the row with respect to the diagonal element column and continue the Case A operation.";
        return result;

    }

    private int[][] transpose(int[][] coefficients) {
        int[][] transposeMatrix = new int[coefficients.length][coefficients.length];
        for(int row=0; row<coefficients.length; row++){
            for(int col=0; col<coefficients.length; col++){
                transposeMatrix[col][row] = coefficients[row][col];
            }
        }

        return transposeMatrix;
    }

    private float[][] inverse(int[][] arr, float[][] inv, int X, int N) {
        int det = determinant(arr, X);
        System.out.println(det);
        if (det == 0) {
            System.out.println("Cannot find inverse for singular matrix");
            return new float[][]{};
        }
        int adj[][] = new int[X][X];
        adjoint(arr, adj, N);
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                System.out.print(adj[row][col] + " ");
            }
            System.out.println();
        }
        for (int row = 0; row < X; row++)
            for (int col = 0; col < X; col++)
                inv[row][col] = adj[row][col] / (float) det;

        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                System.out.print(inv[row][col]);
            }
            System.out.println();
        }
        return inv;
    }

    private int determinant(int[][] arr, int n) {
        int D = 0;
        if (n == 1) {
            return arr[0][0];
        }
        int[][] temp = new int[n][n];
        int sign = -1;
        for (int f = 0; f < n; f++) {
            getCofactor(arr, temp, 0, f, n);
            D += sign * arr[0][f] * determinant(temp, n - 1);
            sign = -sign;
        }
        return D;
    }

    private void getCofactor(int[][] arr, int[][] temp, int p, int q, int n) {
        int i = 0, j = 0;
        for (int row = 0; row < n; row++) {
            for (int col = 0; col < n; col++) {
                if (row != p && col != q) {
                    temp[i][j++] = arr[row][col];
                    if (j == n - 1) {
                        j = 0;
                        i++;
                    }
                }
            }
        }

    }

    private void adjoint(int[][] arr, int[][] adj, int N) {

        if (N == 1) {
            adj[0][0] = 1;
            return;
        }
        int sign = 1;
        int[][] temp = new int[N][N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                getCofactor(arr, temp, i, j, N);
                sign = ((i + j) % 2 == 0) ? 1 : -1;
                adj[j][i] = sign * (determinant(temp, N - 1));
            }
        }
        System.out.println();
        System.out.println("Adjoint are: ");
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                System.out.print(adj[row][col] + " ");
            }
            System.out.println();
        }

    }

    private float[] solveLinearEquations(float[][] invA, int[] constants) {
        int n = constants.length;

        // Multiply the inverse of the coefficient matrix by the constants
        float[] solution = new float[n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                solution[i] += invA[i][j] * constants[j];
            }
        }

        return solution;
    }
}
