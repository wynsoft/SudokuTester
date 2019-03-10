package com.wynsoftsystems.SudokuTester;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import junit.framework.TestCase;

public class SudokuValidatorTest extends TestCase {

	SudokuValidator app= new SudokuValidator();
	List<Row> blocks = null;
	
	@Test
	public void testBuildBlocks() {
		app.setInputFile("E:\\_WINDOWS_APPS\\SudokuTester\\TestFiles\\input_sudoku_ValidWithBlankRows.txt");
		try {
			blocks = app.BuildBlocks();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertEquals(blocks.size(), 9);
	}

	@Test
	public void testBuildBlocksBlankRows() {
        String exMsg = "";
        app.setInputFile("E:\\_WINDOWS_APPS\\SudokuTester\\TestFiles\\input_sudoku_InvalidEmptyRows.txt");
        try
        {
            blocks = app.BuildBlocks();
        } catch (Exception ex) {
            exMsg = ex.getMessage();
        }
        assertEquals(exMsg, "The file provided contains all blank rows");
        blocks = null;
	}

	@Test
	public void testBuildBlocksBlankFile() {
        String exMsg = "";
        app.setInputFile("E:\\_WINDOWS_APPS\\SudokuTester\\TestFiles\\input_sudoku_InvalidEmptyFile.txt");
        try
        {
            blocks = app.BuildBlocks();
        }
        catch (Exception ex)
        {
            exMsg = ex.getMessage();
        }
        assertEquals(exMsg, "The file provided has no data.");
        blocks = null;
	}

	@Test
	public void testHasValidColumns() {
        app.setInputFile("E:\\_WINDOWS_APPS\\SudokuTester\\TestFiles\\input_sudoku_ValidWithBlankRows.txt");
        try {
			blocks = app.BuildBlocks();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        assertTrue(app.HasValidColumns((ArrayList<Row>) blocks));
        blocks = null;
	}

	@Test
	public void testHasValidBlock() {
        app.setInputFile("E:\\_WINDOWS_APPS\\SudokuTester\\TestFiles\\input_sudoku_ValidWithBlankRows.txt");
        try {
			blocks = app.BuildBlocks();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        assertTrue(app.HasValidBlock((ArrayList<Row>) blocks));
        blocks = null;
	}

	@Test
	public void testValidFileWithBlankRows() {
        app.setInputFile("E:\\_WINDOWS_APPS\\SudokuTester\\TestFiles\\input_sudoku_ValidWithBlankRows.txt");
        try {
			assertTrue(app.IsValidSudoku());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        blocks = null;
	}

	@Test
	public void testInvalidFileWithLessRows() {
        app.setInputFile("E:\\_WINDOWS_APPS\\SudokuTester\\TestFiles\\input_sudoku_InvalidLessRows.txt");
        try {
			assertFalse(app.IsValidSudoku());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        blocks = null;
	}

	@Test
	public void testInvalidFileWithDuplicateNumbers() {
        app.setInputFile("E:\\_WINDOWS_APPS\\SudokuTester\\TestFiles\\input_sudoku_InvalidDuplicateNumbers.txt");
        try {
			assertFalse(app.IsValidSudoku());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        blocks = null;
	}

	@Test
	public void testValidFileWithNoBlankRows() {
        app.setInputFile("E:\\_WINDOWS_APPS\\SudokuTester\\TestFiles\\input_sudoku_ValidWithNoBlankRows.txt");
        try {
			assertTrue(app.IsValidSudoku());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        blocks = null;
	}

	@Test
	public void testInvalidFileWithLessColumns() {
        app.setInputFile("E:\\_WINDOWS_APPS\\SudokuTester\\TestFiles\\input_sudoku_InvalidLessColumns.txt");
        try {
			assertFalse(app.IsValidSudoku());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        blocks = null;
	}

	@Test
	public void testInvalidFileWithInvalidCharacters() {
        app.setInputFile("E:\\_WINDOWS_APPS\\SudokuTester\\TestFiles\\input_sudoku_InvalidCharacters.txt");
        try {
			assertFalse(app.IsValidSudoku());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        blocks = null;
	}

	@Test
	public void testInvalidFileWithInvalidEmptyRows() {
        app.setInputFile("E:\\_WINDOWS_APPS\\SudokuTester\\TestFiles\\input_sudoku_InvalidEmptyRows.txt");
        try {
			assertFalse(app.IsValidSudoku());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        blocks = null;
	}

	@Test
	public void testInvalidFileWithInvalidEmptyFile() {
        app.setInputFile("E:\\_WINDOWS_APPS\\SudokuTester\\TestFiles\\input_sudoku_InvalidEmptyFile.txt");
        try {
			assertFalse(app.IsValidSudoku());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        blocks = null;
	}
}
