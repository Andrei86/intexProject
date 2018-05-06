package com.shalkevich.andrei.intexProject.utils.Parser;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import lombok.Data;

/**
 * Class for searching files with the specified extension in specified directory 
 * @author Andrei Shalkevich
 */
@Data
public class FileSearcher {
	
	private final static Logger logger = LogManager.getLogger(FileSearcher.class);
	
	private AppConfigProperties appConfigPropertiesObject;
	private ParseConfigProperties parseConfigPropertiesObject;
	private String inputFileExtentionToSearch;
	private File pathToInputFiles;
	private List<String> foundedFiles = new ArrayList<String>();
	
	public FileSearcher(AppConfigProperties appConfigPropertiesObject, ParseConfigProperties parseConfigPropertiesObject) {
		super();
		this.appConfigPropertiesObject = appConfigPropertiesObject;
		this.parseConfigPropertiesObject = parseConfigPropertiesObject;
		
		this.pathToInputFiles = new File(appConfigPropertiesObject.getPathToInputFile());
		this.inputFileExtentionToSearch = parseConfigPropertiesObject.getInputFileExtention();
	}
	
	public void searchDirectory() {

		if (pathToInputFiles.isDirectory()) {
			search(pathToInputFiles);
		} else {
			logger.info(pathToInputFiles.getAbsoluteFile() + " is not a directory!");
			/*
			 * may throw an exception hear
			 */
		}
	}

	private void search(File file) {

		if (file.isDirectory()) {
			logger.info("Searching directory ... " + file.getAbsoluteFile());
			Arrays.stream(file.listFiles()).forEach((f) -> {
				if (f.isDirectory()) {
					search(f);
				} else {
					if (f.getName().toLowerCase().endsWith(getInputFileExtentionToSearch())) {
						foundedFiles.add(f.getAbsoluteFile().toString());
					}
				}
			});
		}
	}
}
