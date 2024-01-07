package com.project.MathEquationBuilder.controller;

import com.project.MathEquationBuilder.model.EquationModel;
import com.project.MathEquationBuilder.service.EquationSolverService;
import com.project.MathEquationBuilder.model.EquationResult;
import com.project.MathEquationBuilder.service.ZigzagMatrixTraversalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class EquationController {
    static int[][] coefficients= new int[3][3];
    private final EquationSolverService equationSolverService;

    private final ZigzagMatrixTraversalService zigzagMatrixTraversalService;

    @Autowired
    public EquationController(EquationSolverService equationSolverService, ZigzagMatrixTraversalService zigzagMatrixTraversalService) {
        this.equationSolverService = equationSolverService;
        this.zigzagMatrixTraversalService = zigzagMatrixTraversalService;
    }

    @GetMapping("/equation-form")
    public String showEquationForm(Model model) {
        EquationModel equationModel = new EquationModel();
        model.addAttribute("equationModel", equationModel);
        return "equationForm";
    }
    @PostMapping("/matrix-traversal")
    public String matrixZigzagTraversal(EquationModel equationModel, Model model){
        try{
            EquationResult equationResult = zigzagMatrixTraversalService.printZigzag(coefficients);
            if (equationResult!=null){
                model.addAttribute("zigzagTraversal", equationResult.getZigzagMatrix());
            }
        } catch(Exception e){
            model.addAttribute("error", "An error occurred while solving the equations.");
        }
        model.addAttribute("equationModel1", equationModel);
        return "equationForm";
    }

    @PostMapping("/equation-solver/solve")
    public String solveEquations(EquationModel equationModel, Model model) {
        try {
            coefficients = equationModel.getCoefficients();
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
