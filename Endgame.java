package com.plantcola.plantgames;

public class Endgame {
	//case WIN
	public void win(){
		System.out.println("You won!");
		System.out.println("Thank you for playing!");
		System.exit(0);
	}

	//case LOSE
	public void lose() {
    	System.out.println("The bomb exploded. You lost.");
		System.out.println("Thank you for playing!");
		System.exit(0);
	}
	
	//case STARVE
	public void starve() {
    	System.out.println("You starved to death. You lost.");
		System.out.println("Thank you for playing!");
		System.exit(0);
	}
}
