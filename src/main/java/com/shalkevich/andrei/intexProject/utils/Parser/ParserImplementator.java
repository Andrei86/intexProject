package com.shalkevich.andrei.intexProject.utils.Parser;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import lombok.Data;

/**
 * Class for file parsing
 * @author Andrei Shalkevich
 */
@Data
public class ParserImplementator implements IParser<String> {
	
private final static Logger logger = LogManager.getLogger(ParserImplementator.class);
	
	/**
	 * Field for building property's key-value string
	 */
	private StringBuilder propertyBuilder;
	
	/**
	 * List field for strings after file(s) parsing
	 */
	private List<String> parsedStringsList = new ArrayList<>(); 
	
	/**
	 * Delimiter field for parse() method
	 */
	private String delimiter;
	
	/**
	 * Constructor
	 * @param String delimiter - property key delimiter
	 */
	public ParserImplementator(String delimiter) {
		super();
		this.delimiter = delimiter;
	}
	
	 /**
     * Method for input file parsing string by string: deletes
     * spaces, replaces property key delimiters with dots, replaces
     * property key-value delimiter with colon
     * @param Stream<String> inputStream - string stream from input file
     * @return return - Stream<String> stream for writing to a file
     */
	@Override
	public List<String> parse(Stream<String> inputStream) {
		
		logger.info("Inside parse() method");
			
		inputStream.map(s->s.trim()).forEach(x->{
			if (x.contains(delimiter)) {

				if (!x.contains(" "))
					
					propertyBuilder.append(x.replace(delimiter, "."));
				else {
					String[] propertyKeyValuePair = x.split(delimiter + " ");
					String propertyKeyValue = propertyKeyValuePair[0] + ":" + (propertyKeyValuePair[1].trim());
					parsedStringsList.add(propertyBuilder.toString() + propertyKeyValue);
				}
			}
		});
		return parsedStringsList;
	}
	
	/**
	 * Method for converting pathname to Stream<String> object
	 * @param String pathname
	 * @param Stream<String>
	 * @throws IOException 
	 */
	public Stream<String> pathNameToStreamConverting(String pathname) throws IOException{
		Stream<String> stream = Files.lines(Paths.get(pathname));
		return stream;
	}
	
	/**
	 * Method for writing list of parsed strings to a certain file
	 * @param List<String> parsedStringsList
	 * @param String pathName
	 * @throws IOException 
	 */
	public File listToFileWriting(List<String> parsedStringsList, String pathName) throws IOException { 
		
		Stream<String> streamForParsedStrings = parsedStringsList.stream();
		Path path = Files.write(Paths.get(pathName), (Iterable<String>) streamForParsedStrings::iterator);
		return path.toFile();
	}
	
	/**
	 * Method for writing list of parsed strings to single output file
	 * @param String outputFileNameAndExtension - name with extension for 
	 * output file for writing parsed strings
	 * @param String inputFilePathName - path to a directory for searching files for parsing
	 * @param inputFilesList - pathnames list of input files for parsing
	 */
	public void writeToSingleOutputFileWhileParsing(String outputFileNameAndExtension, String inputFilePathName,
			List<String> inputFilesList) {

		String singleOutPathName = inputFilePathName + File.separator + outputFileNameAndExtension;
		inputFilesList.stream().forEach((p) -> {
			
			propertyBuilder = new StringBuilder();
			Stream<String> streamBeforeFileParsing = null;
			try {
				streamBeforeFileParsing = pathNameToStreamConverting(p);
			}catch (IOException e) {
				logger.error(e.getClass().getSimpleName() + " " + p + " while parsing!");
			}
			parse(streamBeforeFileParsing);
			new File(p).delete();
		});
		try {
			listToFileWriting(parsedStringsList, singleOutPathName);
		} catch (IOException e) {
			logger.error(e.getClass().getSimpleName() + " " + singleOutPathName + " while parsing!");
		}
	}
	
	/**
	 * Method for writing list of parsed strings to single output file
	 * @param String outputFileNameAndExtension - name with extension for 
	 * output file for writing parsed strings
	 * @param String inputFilePathName - path to a directory for searching files for parsing
	 * @param inputFilesList - pathnames list of input files for parsing
	 */
	public void writeToSeparateOutputFileWhileParsing(String outputFileNameAndExtention, String inputFilePathName,
			List<String> inputFilesList) {

		inputFilesList.stream().forEach((p) -> {
			propertyBuilder = new StringBuilder();
			String separateOutPathName = new File(p).getParent() + "/" + outputFileNameAndExtention;
			Stream<String> streamBeforeFileParsing = null;
			try {
				streamBeforeFileParsing = pathNameToStreamConverting(p);
			} catch (IOException ex) {
				logger.error(ex.getClass().getSimpleName() + " " + p +  " while parsing!");
			}
			parse(streamBeforeFileParsing);
			try {
				listToFileWriting(parsedStringsList, separateOutPathName);
			} catch (IOException e) {
				logger.error(e.getClass().getSimpleName() + separateOutPathName + " while parsing!");
			}
			parsedStringsList = new ArrayList<>();
			new File(p).delete();
		});
	}
}