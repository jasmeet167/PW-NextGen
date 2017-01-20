package com.csc.fsg.life.tools.copybook.parser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

/* Modifications: WMA-1170 */

public class CopybookBufferedReader 
	extends BufferedReader 
{
	// list of paths to look in to find included copybooks
	private List<File> sourcePathList = new ArrayList<File>();
	
	CopybookBufferedReader includedCopybookReader = null;
	
	public CopybookBufferedReader(Reader in, List<File> sourcePathList) {
		super(in);
		this.sourcePathList = sourcePathList;
	}
	
	@Override
	public String readLine() 
		throws IOException
	{
		String line = null;
		
		// if we have an included copybook reader, use it
		if (includedCopybookReader != null) {
			line = includedCopybookReader.readLine();
		
			if (line == null) {
				includedCopybookReader = null;
				line = super.readLine();
			}
		} else
			line = super.readLine();
		
		// check for end of file and clean up
		if (line == null) {
			includedCopybookReader = null;
			return null;
		}
		
		if (line.trim().length() == 0)
			return line;
		
		// if the line represents a comment, return an empty string
		if (line.length() > 6
		 &&	line.charAt(6) == '*')
			return "";
		
		// check for existence of COPY statement
		int copyIndex = line.indexOf("COPY ");
		if (copyIndex >= 0) {
			// load included copybook
			String copybookName = line.substring(copyIndex+4).trim();
			copybookName = copybookName.substring(0, 8);
			
			File copyFile = CopybookInterpreter.getCopybookFile(sourcePathList, copybookName + ".CPY");
			if (copyFile == null) {
	            throw new IOException("Unable to find copybook: " + copybookName + ".CPY");
			}
			
			// create BufferedReader for included copybook
			includedCopybookReader = new CopybookBufferedReader(new FileReader(copyFile), sourcePathList);

			// replace COPY statement with empty string
			if (line.length() > 72)
				line = line.substring(0, 72);
			line = line.replace(" SUPPRESS", "");
			line = line.replace(" .", ".");
			line = line.replace("COPY ", "");
			line = line.replace(copybookName + ".", "");
		}
		
		return line;
	}
	
}
