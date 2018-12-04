package io;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import astaire.DanceShow;
import astaire.Groups;

/**
 * Reads two types files and handles the relevant data.
 * @author 
 */
public class AstaireFileReader {
	/**
	 * Reads a show file and returns the data as a DanceShow object.
	 * 
	 * @param fileName
	 * @return
	 * @throws IOException
	 * @throws FileNotFoundException
	 */
	public static DanceShow readShowFile(String fileName) {
		DanceShow danceshow = new DanceShow();
		BufferedReader reader = null;

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
			// System.err.println("A FileNotFoundException was caught :" + e.getMessage());
			log(e, true);

		} catch (IOException e) {
			// System.err.println("An IOException was caught :" + e.getMessage());
			// log(e, false);

		} finally {
			try {
				if (reader != null) {
					reader.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return danceshow;
	}

	/**
	 * Reads a group file and adds all the groups to the collection.
	 * 
	 * @param fileName
	 */
	public static void readGroupsFile(String fileName) {

		BufferedReader reader = null;

		try {
			reader = new BufferedReader(new FileReader(fileName));
			String data = reader.readLine();
			data = reader.readLine();

			while (data != null) {

				String[] file = data.split("\t");
				
				/*
				 * DO not split the data in by ','. It is an unnecessary level of complexity.
				 * Pass the string directly to the Groups class.
				 */
				
				Groups.addGroup(file[0], file[1]);

				data = reader.readLine();
			}
		} catch (FileNotFoundException e) {
			// e.printStackTrace();
		    //throw new FileNotFoundException("File was not found!");

		//	System.err.println("A FileNotFoundException was caught :" + e.getMessage());
			// log(e, true);
		} catch (IOException e) {
			System.err.println("An IOException was caught :" + e.getMessage());
			// log(e, true);
		} finally {
			try {
				if (reader != null) {
					reader.close();
				}

			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static void log(Throwable throwable, boolean expected) {
		System.out.println(String.format("[%s] %s", expected ? "EXPECTED" : "UNEXPECTED", throwable.toString()));
		throwable.printStackTrace();
	}

	public static void main(String[] args) {
		AstaireFileReader.readShowFile("data/danceShowData_dances.csv");

		AstaireFileReader.readGroupsFile("data/danceShowData_danceGroups.csv");
	}

}