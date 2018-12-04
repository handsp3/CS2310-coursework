package io;

import astaire.DanceController;

/**
 * Main class to launch the program.
 */
public class Main {
	public static void main (String[] args) {
		TUI t = new TUI(new DanceController());
	}
}
