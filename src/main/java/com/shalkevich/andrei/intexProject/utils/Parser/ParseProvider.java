package com.shalkevich.andrei.intexProject.utils.Parser;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;

import lombok.Data;

@Data
public class ParseProvider {
	
	private FileSearcher filesForParseSearcher;
	private Boolean isSeparateSaveMode;
	private List<String> pathsList;
	private ParserImplementator parserImplementator;

	public ParseProvider(FileSearcher fileForParseSearcher, String isSeparateSaveMode, ParserImplementator parserImplementator) {
		super();
		this.filesForParseSearcher = fileForParseSearcher;
		this.isSeparateSaveMode = Boolean.valueOf(isSeparateSaveMode);
		this.parserImplementator = parserImplementator;
	}

	public void parseProcess() {
 
		filesForParseSearcher.searchDirectory();
		pathsList = filesForParseSearcher.getFoundedFiles();
		String outputFileExtention = filesForParseSearcher.getParseConfigPropertiesObject().getOutputFileExtention();
		String outputFileName = filesForParseSearcher.getParseConfigPropertiesObject().getOutputFileName();
		String outputFileNameWithExtention = outputFileName + outputFileExtention;
		String inputFilePath = filesForParseSearcher.getAppConfigPropertiesObject().getPathToInputFile();

		if (isSeparateSaveMode) {
			pathsList.stream().forEach((path) -> {
				try {
					Stream<String> parsedStringsStream = parserImplementator.parse(Files.lines(Paths.get(path)));
					File inputFile = new File(path);
					String parentPathNameString = inputFile.getParent();
					String outputFile = parentPathNameString + "/" + outputFileNameWithExtention;
					Files.write(Paths.get(outputFile), (Iterable<String>) parsedStringsStream::iterator);
					inputFile.delete();
					parserImplementator.getParsedStringslist().clear();
					
				} catch (IOException e) {
					e.printStackTrace();
				}
			});
		} else {
			String outputFile = inputFilePath + "/" + outputFileNameWithExtention;
			
			pathsList.stream().forEach((path) -> {
				try {
					Stream<String> parsedStringsStream = parserImplementator.parse(Files.lines(Paths.get(path)));

					Files.write(Paths.get(outputFile), (Iterable<String>) parsedStringsStream::iterator);
				} catch (IOException e) {
					e.printStackTrace();
				}
			});
		}
	}
}
