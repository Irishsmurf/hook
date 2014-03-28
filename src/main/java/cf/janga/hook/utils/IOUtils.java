package cf.janga.hook.utils;

import java.io.File;
import java.io.IOException;
import java.io.Reader;

import org.apache.commons.lang3.StringUtils;

public class IOUtils {

	private IOUtils() {
	}

	public static boolean hasExtension(File file, String extension) {
		String fileName = file.getName();
		return StringUtils.isNotBlank(fileName) && fileName.endsWith("." + extension) && file.isFile();
	}

	public static void closeReader(Reader reader) {
		try {
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
