package filechooserexample;

import java.awt.Component;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.ReadOnlyFileSystemException;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public class FileChooserExample {

	private static Component parent;

	public static void main(String[] args) {

		JFileChooser chooser = new JFileChooser();
	    FileNameExtensionFilter filter = new FileNameExtensionFilter(
	        "JPG & GIF Images", "txt","jpg", "gif","dted");
	    chooser.setFileFilter(filter);
	    int returnVal = chooser.showOpenDialog(parent);
	    if(returnVal == JFileChooser.APPROVE_OPTION) {
	       System.out.println("You chose to open this file: " +
	            chooser.getSelectedFile().getName());
	       		if (chooser.getSelectedFile().canRead())
	       		{
	       			System.out.println(""+chooser.getSelectedFile().getName());
	       			//chooser.getSelectedFile().
	       			File f = chooser.getSelectedFile();
	       			FileChooserExample.readFile(f);
	       		}
	       	
	    }
	}
	
	
	public static void readFile(File args) {

		try (BufferedReader br = new BufferedReader(new FileReader(args))) {

			String sCurrentLine;

			while ((sCurrentLine = br.readLine()) != null) {
				System.out.println(sCurrentLine);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
