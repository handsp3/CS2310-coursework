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

	// Times the file is called.
	private static int timesCalled = 0;

	/**
	 * Reads a show file and returns the data as a DanceShow object.
	 * 
	 * @param fileName
	 * @return danceShow
	 */
	public static DanceShow readShowFile(String fileName) {
		/*
		 * New DanceShow object, Reader is null, The reader reads the fileName
		 * parameter.
		 */
		DanceShow danceshow = new DanceShow();
		BufferedReader reader = null;
		fileName = "data/" + fileName;

		/*
		 * Try function, Reading file line and storing it in data variable, While data
		 * is not null, the data stored will be split by tabs and stored in an array of
		 * string types, From array 1 in the string array this will be dancers and will
		 * split all the dancers by comma and store those separately in an array of
		 * strings. Adding the data from the reader inside the danceShow object, Array 0
		 * and dancers will add a dance.
		 */
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

			/*
			 * Error catching, prints an error if the fileName is not found, Exits the
			 * system if it not found.
			 */
		} catch (FileNotFoundException e) {

			System.err.println("Error, file: " + fileName + " not found.");

			if (timesCalled == 0) {
				System.exit(0);
			}

		} catch (IOException e) {
			System.err.println("Error reading file : " + fileName);

			/*
			 * If the reader is not null, close the reader, IO error message if the file
			 * cannot be read.
			 */
		} finally {
			try {
				if (reader != null) {
					reader.close();
				}
			} catch (IOException e) {
				System.err.println("Error reading file : " + fileName);
			}
		}

		// Increase the times called by 1.
		timesCalled++;

		// Returns danceShow object.
		return danceshow;
	}

	/**
	 * Static method belongs to the class doesn't return anything, Reads the groups
	 * file.
	 * 
	 * @param fileName fileName Path to the file to read.
	 */
	public static void readGroupsFile(String fileName) {
		fileName = "data/" + fileName;
		BufferedReader reader = null;

		try {
			reader = new BufferedReader(new FileReader(fileName));
			String data = reader.readLine();
			data = reader.readLine();

			/*
			 * While data is not null from the reader, store data inside String array split
			 * by tabs, Store the data inside the addGroup method in Groups class.
			 */
			while (data != null) {

				String[] file = data.split("\t");

				Groups.addGroup(file[0], file[1]);

				data = reader.readLine();
			}

			/*
			 * Error catching, prints an error if the fileName is not found, Exits the
			 * system if it not found.
			 */
		} catch (FileNotFoundException e) {

			System.err.println("Error " + fileName + " was not found. Enter correct file name.");
			System.exit(1);

		} catch (IOException e) {
			System.err.println("Error reading file : " + fileName);

			/*
			 * If the reader is not null, close the reader, IO error message if the file
			 * cannot be read.
			 */
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