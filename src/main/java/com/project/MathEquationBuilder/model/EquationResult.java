// EquationResult.java
package com.project.MathEquationBuilder.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class EquationResult {

    private float[] variables;
    private double area;
    private float[][] inverse;
    private int[][] adjacent;
    private int determinant;
    private int[][] transpose;
    private  String descriptValue;
    private String topLeftValue;
    private int[][] zigzagMatrix;

}
