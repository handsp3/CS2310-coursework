package io;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import astaire.DanceShow;
import astaire.Groups;

/**
 * Reads two types files and handles the relevant data.
 * 
 * @author Muhammed Avais Hussain
 */
public class AstaireFileReader {

	private static int timesCalled = 0;

	/**
	 * Reads a show file and returns the data as a DanceShow object.
	 * 
	 * @param fileName
	 * @return
	 * @throws IOException
	 */
	public static DanceShow readShowFile(String fileName) {
		DanceShow danceshow = new DanceShow();
		BufferedReader reader = null;
		fileName = "data/" + fileName;

		try {
			reader = new BufferedReader(new FileReader(fileName));
			String data = reader.readLine();
			data = reader.readLine();

			while (data != null) {
				String[] file = data.split("\t");
				String[] dancers = file[1].split(",");

				danceshow.addDance(file[0], dancers);
				data = reader.readLine();
			}
		} catch (FileNotFoundException e) {

			System.err.println("Error, file: " + fileName + " not found.");

			if (timesCalled == 0) {
				System.exit(0);
			}

		} catch (IOException e) {
			System.err.println("Error reading file : " + fileName);

		} finally {
			try {
				if (reader != null) {
					reader.close();
				}
			} catch (IOException e) {
				System.err.println("Error reading file : " + fileName);
			}
		}

		timesCalled++;

		return danceshow;
	}

	/**
	 * @throws FileNotFoundException Reads a group file and adds all the groups to
	 *                               the collection.
	 * 
	 * @param fileName @throws
	 */
	public static void readGroupsFile(String fileName) {
		fileName = "data/" + fileName;
		BufferedReader reader = null;

		try {
			reader = new BufferedReader(new FileReader(fileName));
			String data = reader.readLine();
			data = reader.readLine();

			while (data != null) {

				String[] file = data.split("\t");
				String[] members = file[1].split(",");

				Groups.addGroup(file[0], members);

				data = reader.readLine();
			}
		} catch (FileNotFoundException e) {

			System.err.println("Error " + fileName + " was not found. Enter correct file name.");
			System.exit(1);

		} catch (IOException e) {
			System.err.println("Error reading file : " + fileName);

		} finally {
			try {
				if (reader != null) {
					reader.close();
				}

			} catch (IOException e) {
				System.err.println("Error reading file : " + fileName);
			}
		}
	}
}