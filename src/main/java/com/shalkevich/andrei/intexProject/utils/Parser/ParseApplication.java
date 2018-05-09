package com.shalkevich.andrei.intexProject.utils.Parser;

import java.io.File;
import java.util.Map;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.shalkevich.andrei.intexProject.utils.Parser.exceptions.NotDirectoryException;
import com.shalkevich.andrei.intexProject.utils.Parser.exceptions.PropertiesFileNotFoundException;

/**
 * Class for for performance parsing operation
 * @author Andrei Shalkevich
 */
public class ParseApplication {

	private static final Logger logger = LogManager.getLogger(ParseApplication.class);

	public static void main(String[] args) {
		
		logger.trace("Inside parse application.");
		try {
			ConfigProperties configPropertiesObject = new ConfigProperties();
			Set<Object> propertiesKeys = configPropertiesObject.getAllKeys();
			Map<String, String> keysPropertiesMap = configPropertiesObject.getPropertiesKeysValues(propertiesKeys);
			String inputFileExtentionToSearch = keysPropertiesMap.get("inputFileExtension");
			File pathToInputFiles = new File(keysPropertiesMap.get("path"));
			FileSearcher fileSearcher = new FileSearcher(inputFileExtentionToSearch, pathToInputFiles);
			fileSearcher.search(fileSearcher.getPathToInputFiles());
			Boolean isSeparateSaveMode = Boolean.valueOf(keysPropertiesMap.get("isSeparateSaveMode"));
			String keyDelimiter = keysPropertiesMap.get("delimiter");
			ParserImplementator parserImplementator = new ParserImplementator(keyDelimiter);
			String outputFileNameAndExtention = keysPropertiesMap.get("outputFileName")
					+ keysPropertiesMap.get("outputFileExtension");
			if (!isSeparateSaveMode) {
				parserImplementator.writeToSingleOutputFileWhileParsing(outputFileNameAndExtention,
						pathToInputFiles.getAbsolutePath(), fileSearcher.getFoundedFiles());
			} else {
				parserImplementator.writeToSeparateOutputFileWhileParsing(outputFileNameAndExtention,
						pathToInputFiles.getAbsolutePath(), fileSearcher.getFoundedFiles());
			}

		} catch (PropertiesFileNotFoundException ex) {
			logger.error(ex.getClass().getSimpleName() + ": " + ex.getMessage());
		} catch (NotDirectoryException ex) {
			logger.error(ex.getClass().getSimpleName() + ": " + ex.getMessage());
		}
	}
}