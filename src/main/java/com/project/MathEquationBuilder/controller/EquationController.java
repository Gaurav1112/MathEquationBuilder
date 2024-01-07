package com.project.MathEquationBuilder.controller;

import com.project.MathEquationBuilder.model.EquationModel;
import com.project.MathEquationBuilder.service.EquationSolverService;
import com.project.MathEquationBuilder.model.EquationResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class EquationController {

    private final EquationSolverService equationSolverService;

    @Autowired
    public EquationController(EquationSolverService equationSolverService) {
        this.equationSolverService = equationSolverService;
    }

    @GetMapping("/equation-form")
    public String showEquationForm(Model model) {
        EquationModel equationModel = new EquationModel();
        model.addAttribute("equationModel", equationModel);
        return "equationForm";
    }

    @PostMapping("/equation-solver/solve")
    public String solveEquations(EquationModel equationModel, Model model) {
        try {
            int[][] coefficients = equationModel.getCoefficients();
            int[] constants = equationModel.getConstants();

            EquationResult result = equationSolverService.solveEquations(coefficients, constants);

            if (result != null) {
                model.addAttribute("solution", result.getVariables());
                model.addAttribute("inverse", result.getInverse());
                model.addAttribute("adjugate", result.getAdjacent());
                model.addAttribute("area", result.getArea());
                model.addAttribute("determinant", result.getDeterminant());
                model.addAttribute("transpose", result.getTranspose());
                model.addAttribute("descriptValue", result.getDescriptValue());
                model.addAttribute("topLeftValue", result.getTopLeftValue());
            } else {
                model.addAttribute("error", "The equations could not be solved.");
            }
        } catch (Exception e) {
            model.addAttribute("error", "An error occurred while solving the equations.");
        }

        model.addAttribute("equationModel", equationModel);
        return "equationForm";
    }
}
