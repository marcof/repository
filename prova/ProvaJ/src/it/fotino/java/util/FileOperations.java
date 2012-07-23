/**
 * 
 */
package it.fotino.java.util;

import java.io.BufferedInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author acer
 *
 */
public class FileOperations {
	
	public static String readFileToString(File f) {
		BufferedInputStream bin = null;

		try {
			// create FileInputStream object
			FileInputStream fin = new FileInputStream(f);

			// create object of BufferedInputStream
			bin = new BufferedInputStream(fin);

			// create a byte array
			byte[] contents = new byte[1024];

			int bytesRead = 0;
			StringBuilder strFileContents = new StringBuilder();

			while ((bytesRead = bin.read(contents)) != -1) {

				strFileContents.append(new String(contents, 0, bytesRead));
			}

			return strFileContents.toString();

		} catch (FileNotFoundException e) {
			return "File not found" + e;
		} catch (IOException ioe) {
			return "Exception while reading the file " + ioe;
		} finally {
			// close the BufferedInputStream using close method
			try {
				if (bin != null)
					bin.close();
			} catch (IOException ioe) {
				return "Error while closing the stream :" + ioe;
			}

		}
	}

	public static void writeStringToFile(String text, File f) {
		FileOutputStream fos; 
	    DataOutputStream dos;

	    try {

	      fos = new FileOutputStream(f);
	      dos=new DataOutputStream(fos);
	      dos.writeChars(text);

	    } catch (IOException e) {
	      e.printStackTrace();
	    }
	}
	
	

}
