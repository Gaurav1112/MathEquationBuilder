package com.project.MathEquationBuilder.model;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class EquationModel {

    private int[][] coefficients;
    private int[] constants;

    // getters and setters

    public EquationModel() {
        coefficients = new int[3][3];
        constants = new int[3];
    }

    public int[][] getCoefficients() {
        return coefficients;
    }

    public void setCoefficients(int[][] coefficients) {
        this.coefficients = coefficients;
    }

    public int[] getConstants() {
        return constants;
    }

    public void setConstants(int[] constants) {
        this.constants = constants;
    }
}
