package com.shalkevich.andrei.intexProject.utils.Parser;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.shalkevich.andrei.intexProject.utils.Parser.exceptions.NotDirectoryException;

import lombok.Data;

/**
 * Class for searching files with the specified extension in specified directory 
 * @author Andrei Shalkevich
 */
@Data
public class FileSearcher {
	
private final static Logger logger = LogManager.getLogger(FileSearcher.class);
	
	/**
	* File's to parse extention field
	*/
	private String inputFileExtentionToSearch;
	
	/**
	* Path to input files searching for parsing
	*/
	private File pathToInputFiles;
	
	/**
	* Field for save paths to searched files for parsing
	*/
	private List<String> foundedFiles = new ArrayList<String>();

	public FileSearcher(String inputFileExtentionToSearch, File pathToInputFiles) {
		super();
		this.inputFileExtentionToSearch = inputFileExtentionToSearch;
		this.pathToInputFiles = pathToInputFiles;
	}
	
	/**
	* Search method for adding files to foundedFiles list for parsing
	* @param File file - directory for searching
	*/
	public void search(File file) throws NotDirectoryException{

		if (file.isDirectory()) {

			logger.info("Searching directory ... " + file.getAbsoluteFile());
			
			
			Arrays.stream(file.listFiles()).forEach((f) -> {
				if (f.isDirectory()) {
					try {
						search(f);
					} catch (NotDirectoryException ex) {
						logger.error(ex.getClass().getSimpleName() + ": " + ex.getMessage());
					}
				} else {
					if (f.getName().toLowerCase().endsWith(getInputFileExtentionToSearch())) {
						foundedFiles.add(f.getAbsoluteFile().toString());
					}
				}
			});
		} else
			throw new NotDirectoryException(String.format("%s is not a directory", file.getAbsolutePath()));
	}
}