package com.github.PedroHenrique_LS.controllers;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.PedroHenrique_LS.exception.UnsupportedMathOperationException;

@RestController
@RequestMapping("/math")
public class MathController {
	
	@RequestMapping("/sum/{numberOne}/{numberTwo}")
	public Double sum(@PathVariable("numberOne") String numberOne, @PathVariable("numberTwo") String numberTwo) throws Exception {
		if(!isNumeric(numberOne) || !isNumeric(numberTwo)) 
			throw new UnsupportedMathOperationException("Please set a numeric value!");
		return convertToDouble(numberOne) + convertToDouble(numberTwo);
	}
	
	@RequestMapping("/sub/{numberOne}/{numberTwo}")
	public Double sub(@PathVariable("numberOne") String numberOne, @PathVariable("numberTwo") String numberTwo) throws Exception {
		if(!isNumeric(numberOne) || !isNumeric(numberTwo)) 
			throw new UnsupportedMathOperationException("Please set a numeric value!");
		return convertToDouble(numberOne) - convertToDouble(numberTwo);
	}
	
	@RequestMapping("/mul/{numberOne}/{numberTwo}")
	public Double mul(@PathVariable("numberOne") String numberOne, @PathVariable("numberTwo") String numberTwo) throws Exception {
		if(!isNumeric(numberOne) || !isNumeric(numberTwo)) 
			throw new UnsupportedMathOperationException("Please set a numeric value!");
		return convertToDouble(numberOne) * convertToDouble(numberTwo);
	}
	
	@RequestMapping("/div/{numberOne}/{numberTwo}")
	public Double div(@PathVariable("numberOne") String numberOne, @PathVariable("numberTwo") String numberTwo) throws Exception {
		if(!isNumeric(numberOne) || !isNumeric(numberTwo)) 
			throw new UnsupportedMathOperationException("Please set a numeric value!");
		return convertToDouble(numberOne) / convertToDouble(numberTwo);
	}
	@RequestMapping("/med/{numberOne}/{numberTwo}")
	public Double med(@PathVariable("numberOne") String numberOne, @PathVariable("numberTwo") String numberTwo) throws Exception {
		if(!isNumeric(numberOne) || !isNumeric(numberTwo)) 
			throw new UnsupportedMathOperationException("Please set a numeric value!");
		return (convertToDouble(numberOne) + convertToDouble(numberTwo)) / 2;
	}
	@RequestMapping("/sqrt/{number}")
	public Double sum(@PathVariable("number") String number) throws Exception {
		if(!isNumeric(number)) 
			throw new UnsupportedMathOperationException("Please set a numeric value!");
		return Math.sqrt(convertToDouble(number));
	}

	private Double convertToDouble(String strNumber) throws IllegalArgumentException {
		if( strNumber == null || strNumber.isEmpty()) 
			throw new UnsupportedMathOperationException("Please set a numeric value!");
		String number = strNumber.replace(",", ".");
		return Double.parseDouble(number);
	}

	private boolean isNumeric(String strNumber) {
		if( strNumber == null || strNumber.isEmpty()) 
			throw new UnsupportedMathOperationException("Please set a numeric value!");
		String number = strNumber.replace(",", ".");
		return (number.matches("[-+]?[0-9]*\\.?[0-9]+"));
		
	}

}
