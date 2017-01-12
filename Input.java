package com.plantcola.plantgames;

import java.util.Scanner;

public class Input {
	
    private static Scanner in;

	public static String getInput() {
        System.out.print("> ");
        in = new Scanner(System.in);
        String input = in.nextLine();
        input.toLowerCase();
        return input;
    }
}
