package com.wynsoftsystems.SudokuTester;

import java.io.File;

import org.apache.commons.lang3.StringUtils;

public class App 
{
    public static void main( String[] args ) throws Exception
    {
    	if (args.length > 0 && !StringUtils.isEmpty(args[0])) {
			if ((new File(args[0].toString())).isFile())
			{
				@SuppressWarnings("resource")
				SudokuValidator validator = new SudokuValidator();
				validator.setInputFile(args[0].toString());
				System.out.println(validator.IsValidSudoku());
			} else {
    			System.out.println("Input file not found.");
    		}
    	} else {
    		System.out.println( "An input file was expected.\n\nUsage:\n\tSudokuTester.exe <path_to_your_input_file>");
    	}
    }
}
