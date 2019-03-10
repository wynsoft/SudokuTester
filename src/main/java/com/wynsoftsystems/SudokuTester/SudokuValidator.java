package com.wynsoftsystems.SudokuTester;

import java.util.*;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.regex.*;

public class SudokuValidator implements Closeable
{
	private static final String VALID_NUMBERS = "123456789";
	Pattern pattern = Pattern.compile("^[1-9]+$");
	private ArrayList<Row> blocks = null;

	private String InputFile;
	public final String getInputFile()
	{
		return InputFile;
	}
	public final void setInputFile(String value)
	{
		InputFile = value;
	}

	public final ArrayList<Row> BuildBlocks() throws IOException
	{
		Path path = Paths.get(InputFile);
		List<String> allLines = Files.readAllLines(path, StandardCharsets.UTF_8);
		
		// Check if there is any data in the file
		if (allLines.size() > 0)
		{
			blocks = new ArrayList<Row>();
			for (String line : allLines) {
				if (line.trim().length() > 0)
				{
					Row block = new Row();
					char[] orignalNumbers = line.toCharArray();
					block.setCell(orignalNumbers);
					blocks.add(block);
				}
			}
			if (blocks.isEmpty())
			{
				throw new RuntimeException("The file provided contains all blank rows");
			}
			return blocks;
		}
		else
		{
			throw new RuntimeException("The file provided has no data.");
		}
	}

	public final boolean IsValidSudoku() throws Exception
	{
		try
		{
			blocks = BuildBlocks();
			for (Row block : blocks)
			{
				// Check if we have 9 numbers
				if (block.getCell().length == 9)
				{
					char[] numbers = new char[block.getCell().length];
					System.arraycopy(block.getCell(), 0, numbers, 0, block.getCell().length);
					Arrays.sort(numbers);
					String temp = new String(numbers);
					// Check if we have only numbers 1 to 9 (no need to check for duplicates here as the next condition will find it)
					Matcher matcher = pattern.matcher(temp);
					if (!matcher.matches())
					{
						System.out.println("File contains either 0 or non numeric values.");
						return false;
					}
					// This check will detect any duplicate numbers
					if (!VALID_NUMBERS.equals(temp))
					{
						return false;
					}
				}
				else
				{
					System.out.println("Invalid File - Contains Less Numbers than expected.");
					return false;
				}
			}
			if (blocks.size() == 9)
			{
				// We now have 9 valid rows in the blocks
				// We now need to check the column validity
				if (HasValidColumns(blocks))
				{
					// We now have all valid columns
					// Next we need to validate 3x3 blocks i.e the first 3 numbers of 3 consecutive rows
					if (HasValidBlock(blocks))
					{
						return true;
					}
				}
			}
			else
			{
				System.out.println("Invalid file - Contains More or Less rows of numbers.");
			}
		}
		catch (RuntimeException ex)
		{
			System.out.println(ex.getMessage());
		}
		return false;
	}

	public final boolean HasValidColumns(ArrayList<Row> blocks)
	{
		char[] row1 = blocks.get(0).getCell();
		char[] row2 = blocks.get(1).getCell();
		char[] row3 = blocks.get(2).getCell();
		char[] row4 = blocks.get(3).getCell();
		char[] row5 = blocks.get(4).getCell();
		char[] row6 = blocks.get(5).getCell();
		char[] row7 = blocks.get(6).getCell();
		char[] row8 = blocks.get(7).getCell();
		char[] row9 = blocks.get(8).getCell();

		for (int c = 0; c < 9; c++)
		{
			char[] col = (String.valueOf(row1[c]) + String.valueOf(row2[c]) + String.valueOf(row3[c]) + String.valueOf(row4[c]) + String.valueOf(row5[c]) + String.valueOf(row6[c]) + String.valueOf(row7[c]) + String.valueOf(row8[c]) + String.valueOf(row9[c])).toCharArray();
			Arrays.sort(col);
			String temp = new String(col);
			if (!VALID_NUMBERS.equals(temp))
			{
				return false;
			}
		}
		return true;
	}

	public final boolean HasValidBlock(ArrayList<Row> blocks)
	{
		for (int r = 0; r < 9; r += 3)
		{
			for (int c = 0; c < 9; c += 3)
			{
				String b1_1 = String.valueOf(blocks.get(r).getCell()[c]) + String.valueOf(blocks.get(r).getCell()[c + 1]) + String.valueOf(blocks.get(r).getCell()[c + 2]);
				String b1_2 = String.valueOf(blocks.get(r + 1).getCell()[c]) + String.valueOf(blocks.get(r + 1).getCell()[c + 1]) + String.valueOf(blocks.get(r + 1).getCell()[c + 2]);
				String b1_3 = String.valueOf(blocks.get(r + 2).getCell()[c]) + String.valueOf(blocks.get(r + 2).getCell()[c + 1]) + String.valueOf(blocks.get(r + 2).getCell()[c + 2]);

				char[] block = (b1_1 + b1_2 + b1_3).toCharArray();
				Arrays.sort(block);

				String temp = new String(block);
				if (!VALID_NUMBERS.equals(temp))
				{
					return false;
				}
			}
		}
		return true;
	}
	public void close() throws IOException {
		// TODO Auto-generated method stub
	
	}
}