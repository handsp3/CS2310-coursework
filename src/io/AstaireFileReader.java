package io;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

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

		// DanceShow object, Reader is null, The reader reads the fileName parameter.

		DanceShow danceshow = new DanceShow();
		BufferedReader reader = null;
		fileName = "data/" + fileName;

		/*
		 * Buffered reader reading fileName storing it inside reader. Reading line 1 and
		 * 2 from reader variable storing it in data.
		 */
		try {
			reader = new BufferedReader(new FileReader(fileName));
			String data = reader.readLine();
			data = reader.readLine();

			// While data is not null create array for files and dancers.
			// Split the data by Tabs and commas.
			while (data != null) {
				String[] file = data.split("\t");
				String[] dancers = file[1].split(",");

				// String ArrayList of performers.
				ArrayList<String> performers = new ArrayList<String>();

				// Adds Dancers Array to the
				for (String dancer : dancers) {
					performers.add(dancer.trim());
				}

				danceshow.addDance(file[0].trim(), performers);
				data = reader.readLine();
			}
			reader.close();

			/*
			 * Error catching, prints an error if the fileName is not found, Exits the
			 * system if it not found.
			 */
		} catch (FileNotFoundException e) {

			System.err.println("Error, file: " + fileName + " not found.");

			// Exit the system if this is the first time this method has been called.
			if (timesCalled == 0) {
				System.exit(0);
			}

		} catch (IOException e) {
			System.err.println("Error reading file : " + fileName);

			/*
			 * If the reader is not null, close the reader, IO error message if the file
			 * cannot be read.
			 */
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
	 * @param fileName Path to the file to read.
	 */
	public static void readGroupsFile(String fileName) {

		// Gives the filename the prefix.
		fileName = "data/" + fileName;
		// Reader is null at this point.
		BufferedReader reader = null;

		/*
		 * Buffered reader reading fileName storing it inside reader. Reading line 1 and
		 * 2 from reader variable storing it in data.
		 */
		try {
			reader = new BufferedReader(new FileReader(fileName));
			String data = reader.readLine();
			data = reader.readLine();

			/*
			 * While data is not null from the reader, store data inside String array split
			 * by tabs, Store the data inside the addGroup method in Groups class.
			 */
			while (data != null) {

				// String Array file storing data split in tabs.
				String[] file = data.split("\t");
				String[] memberData = file[1].split(", ");

				// ArrayList of type string members.
				ArrayList<String> members = new ArrayList<String>();

				// Enhanced for adding elements to the ArrayList.
				for (String member : memberData) {
					members.add(member);
				}

				// Sending file Array 0 and members to static add group method in Groups class.
				Groups group = Groups.getGroups();
				group.addGroup(file[0], members);

				// Reader reads next line.
				data = reader.readLine();
			}
			// Reader closes.
			reader.close();

			/*
			 * Error catching, prints an error if the fileName is not found, Exits the
			 * system if it not found.
			 */
		} catch (FileNotFoundException e) {

			/*
			 * Print a error message with the filename that was entered if the file name is
			 * incorrect or not found.
			 */
			System.err.println("Error " + fileName + " was not found. Enter correct file name.");
			// Exits the system.
			System.exit(1);

		} catch (IOException e) {

			// Print error if there is error reading the given filename.
			System.err.println("Error reading file : " + fileName);
		}
	}
}