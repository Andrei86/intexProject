package com.shalkevich.andrei.intexProject.utils.Parser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.shalkevich.andrei.intexProject.utils.Parser.exceptions.PropertyNotFoundException;
import com.shalkevich.andrei.intexProject.utils.Parser.exceptions.PropertiesFileNotFoundException;

/**
 * Class app entry point
 * @author Andrei Shalkevich
 */
public class App {

	private static final Logger logger = LogManager.getLogger(App.class);

	public static void main(String[] args) throws IOException {

		AppConfigProperties appConfigProperties = new AppConfigProperties("app.properties");
		ParseConfigProperties parseConfigProperties = new ParseConfigProperties("parse.properties");

		try {			
			appConfigProperties.setPathForInputFilesForParsing();
			parseConfigProperties.setParseOptions();
			FileSearcher fileForParseSearcher = new FileSearcher(appConfigProperties, parseConfigProperties);
			String delimiter = parseConfigProperties.getDelimiter();
			ParserImplementator parserImplementator = new ParserImplementator(delimiter);
			String isSeparateSaveMode = parseConfigProperties.getIsSeparateSaveMode();
			ParseProvider parseProvider = new ParseProvider(fileForParseSearcher, isSeparateSaveMode, parserImplementator);
			parseProvider.parseProcess();
			
		} catch (PropertiesFileNotFoundException ex) {
			logger.error("Exception", ex);
		} catch (PropertyNotFoundException ex) {
			logger.error("Exception", ex);
		}
	}
}
