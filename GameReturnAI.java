package com.plantcola.plantgames;

public class GameReturnAI {
	
	//get command
	public static String getAI(String item){
		String getAIreturn;
		getAIreturn = "So you wanted to get " + item + "? You should type 'get " + item + "' next time!";
		return getAIreturn;
	}
}
