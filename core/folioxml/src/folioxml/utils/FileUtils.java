package folioxml.utils;

import java.io.*;
import java.nio.channels.FileChannel;

public class FileUtils {

	public static StringBuffer readFileToString(File f) throws IOException {
		BufferedReader input = new BufferedReader(new FileReader(f));
		StringBuffer sb = new StringBuffer();
		while (input.ready()) {
			sb.append(input.readLine() + "\n");
		}
		input.close();
		return sb;
	}

	public static File copyFile(File from, File to) throws IOException {
		FileChannel inChannel = new FileInputStream(from).getChannel();
		FileChannel outChannel = new FileOutputStream(to).getChannel();
		try {
			inChannel.transferTo(0, inChannel.size(), outChannel);
		} catch (IOException e) {
			throw e;
		} finally {
			if (inChannel != null)
				inChannel.close();
			if (outChannel != null)
				outChannel.close();

		}
		return to;
	}
	/**
	 * ouputs a new file
	 * @param outputFileName
	 * @param content
	 * @return
	 */
	public static boolean writeFileFromString(File outputFileName, String content) {
		try {

			
			//File fileName = new File(outputFileName);
			FileOutputStream fos2 = new FileOutputStream(outputFileName, false);

			fos2.write(content.getBytes());

			fos2.close();
		} catch (IOException ioe) {
			return false;
		}
		return true;

	}
}
