package com.plantcola.plantgames;

import java.util.ArrayList;

public class Startup {
	
    // Load inventory
    ArrayList<String> inventory = new ArrayList<>();

    public void start() {

        // Build rooms
        final int WIDTH = 3;
        final int HEIGHT = 3;
        Room[][] room = new Room[WIDTH][HEIGHT];
        Rooms.build(room, WIDTH, HEIGHT);
        int x = 0;
        int y = 0;
        int score = 0;
        int currenty = -1;
		int currentx = -1;

        
        // Title Screen
    	System.out.println("+----------------------------------+");
    	System.out.println("| Welcome to Plant Text Adventure! |");
    	System.out.println("+----------------------------------+");
    	
    	//intro
    	System.out.println("You are a member of the bomb disposal squad!");
    	System.out.println("Find the bomb defuser and defuse the bomb!");
    	System.out.println("Valid commands are: inventory, restart, eat, look, go, get, beam, fire and defuse.");
    	System.out.println("For example, \"go s\" will be used to go towards the south, \"go n\" to the north, etc.");
    	
    	// Print starting room description
    	Rooms.print(room, x, y);

        // Start game loop
    	int hunger = 30;
    	boolean playing = true;
    	while(playing){
    		for(int i = 0; i < 30; i++) {
    			// Get user input
    			String input = Input.getInput();
    			System.out.println(input);

    			// Movement commands
    			if (input.equals("go n")) {
    				if (y > 0) {
    					y--;
    					Rooms.print(room, x, y);
    					hunger -= 3;
    					System.out.println("Your hunger: "+ hunger);
    				} else {
    					System.out.println("You can't go that way.");
    				}
    			} else if (input.equals("go s")) {
    				if (y < HEIGHT - 1) {
    					y++;
    					Rooms.print(room, x, y);
    					hunger -= 3;
    					System.out.println("Your hunger: "+ hunger);
    				} else {
    					System.out.println("You can't go that way.");
    				}
    			} else if (input.equals("go e")) {
    				if (x > 0) {
    					x--;
    					Rooms.print(room, x, y);
    					hunger -= 3;
    					System.out.println("Your hunger: "+ hunger);
    				} else {
    					System.out.println("You can't go that way.");
    				}
    			} else if (input.equals("go w")) {
    				if (x < WIDTH - 1) {
    					x++;
    					Rooms.print(room, x, y);
    					hunger -= 3;
    					System.out.println("Your hunger: "+ hunger);
    				} else {
    					System.out.println("You can't go that way.");
    				}
    			} else if (input.equals("go")) {
    				System.out.println("go where?");
    			}

    			// Look commands
    			else if (input.equals("look")) {
    				Rooms.print(room, x, y);
    			}

    			// Get commands
    			else if (input.length() > 4  && input.substring(0, 4).equals("get ")) {
    				if (input.substring(0, input.indexOf(' ')).equals("get")) {
    					if (input.substring(input.indexOf(' ')).length() > 1) {
    						String item = input.substring(input.indexOf(' ') + 1);
    						score = Inventory.checkItem(x, y, item, inventory, room, score);
    						hunger -= 1;
        					System.out.println("Your hunger: "+ hunger);
        					switch(item){
        					case "beamer":
        						System.out.println("When you have a beamer, you can teleport.");
        						System.out.println("When you issue the command 'beam', the game memorizes your location.");
        						System.out.println("When you issue 'fire', the game will teleport you to the location you memorized.");
        						break;
        					case "book":
        						System.out.println("Books can be used!");
        						break;
        					case "time traveller":
        						System.out.println("Time travellers can be used!");
        					}
    					}	
    				}
    			}
    			
    			//get only command
    			else if (input.equals("get")){
    				System.out.println("What do you want to get?");
    				String getInput = Input.getInput();
        			System.out.println(getInput);
        			score = Inventory.checkItem(x, y, getInput, inventory, room, score);
        			hunger -= 1;
        			System.out.println(GameReturnAI.getAI(getInput));
					System.out.println("Your hunger: "+ hunger);
    			}
            
    			//eat commands
    			else if (input.length() > 4  && input.substring(0, 4).equals("eat ")) {
    				if (input.substring(0, input.indexOf(' ')).equals("eat")) {
    					if (input.substring(input.indexOf(' ')).length() > 1) {
    						String item = input.substring(input.indexOf(' ') + 1);
    						if (existsTest(item) == true){
    							switch(item){
    							case "apple":
    								hunger += 3;
    								break;
    							case "bread":
    								hunger += 6;
    								break;
    							case "banana":
    								hunger += 4;
    								break;
    							case "biscuit":
    								hunger += 5;
    								break;
    							case "beef":
    								hunger += 8;
    								break;
    							default:
    								System.out.println(item + " is not a thing that can be eaten!");
    								break;
    							}
    							System.out.println("Your hunger is " + hunger);
    							removeItem(item);
    						} else {
    							if(Inventory.isInRoom(x, y, item, room)){
    								System.out.println("You need to get the " + item + " before you eat it!");
    								System.out.println("Use 'get " + item + " ' to get it!");
    							} else {
    								System.out.println(item + " is not a thing that can be eaten!");
    							}
    						}	
    					}	
    				}
    			}
    			//eat only command
    			else if (input.equals("eat")){
    				System.out.println("What do you want to eat? e.g. Type 'eat bread' to eat bread");
    			}

    			//use commands
    			else if (input.length() > 4  && input.substring(0, 4).equals("use ")) {
    				if (input.substring(0, input.indexOf(' ')).equals("use")) {
    					if (input.substring(input.indexOf(' ')).length() > 1) {
    						String item = input.substring(input.indexOf(' ') + 1);
    						if (existsTest(item) == true){
    							switch(item){
    							case "book":
    								hunger -= 2;
    								Inventory.inventorySize = 30;
    								break;
    							case "time traveller":
    								i -= 9;
    								break;
    							default:
    								System.out.println(item + " is not a thing that can be used!");
    								break;
    							}
    							System.out.println("Your hunger is " + hunger);
    							removeItem(item);
    						} else {
    							if (Inventory.isInRoom(x, y, item, room)){
    								System.out.println("You need to get the " + item + " before you use it!");
    								System.out.println("Use 'get " + item + " ' to get it!");
    							} else {
    								System.out.println(item + " is not a thing that can be used!");
    							}
    						}	
    					}	
    				}
    			}
    			
    			// Inventory commands
    			else if (input.equals("i") || input.equals("inv") || input.equals("inventory")) {
    				Inventory.print(inventory);
    			}
    			
    			//system commands
    			else if (input.equals("restart")) {
    				System.out.println();
    				start();
    			}
            

    			else if (input.equals("quit")) {
    				System.out.println("Goodbye!");
    				System.exit(0);
    			}
    			
    			//help command
    			else if (input.equals("help")) {
    				System.out.println("Type go n/e/s/w to move around");
    				System.out.println("Type 'look' for a description of the room you're in");
    				System.out.println("Type 'get' + the item to get something");
    				System.out.println("Type 'i' to view your inventory");
    				System.out.println("Type 'restart' to restart the game");
    				System.out.println("Type 'quit' to quit the game");
    				System.out.println("Type 'defuse' to defuse the bomb");
    				System.out.println("Type 'eat (the name of the food)' to eat something");
    				System.out.print("Typing 'beam' will memorize the room you are now in \nand then typing 'fire' will teleport you to that room\n");
    			}
    			
    			//beamer command
    			else if (input.equals("beam") || input.equals("fire")){
    				if (inventory.contains("beamer")) {
    					if (input.equals("beam")){
    						currenty = y;
    						currentx = x;
    						System.out.println("Current room memorized!");
    						System.out.println("Current room is: X: " + currentx + " Y: " + currenty);
    					} else {
    						if (currenty != -1 && currentx != -1){
    							System.out.println("Teleporting to " + currentx + " " + currenty + "...");
    							x = currentx;
    							y = currenty;
    							Rooms.print(room, x, y);
    						} else{
    							System.out.println("You need to use the fire command before using the beam command");
    						}
    					}
    				} else {
    					System.out.println("You dont have a beamer! Find it to use this function!");
    				}
    			}
    			
    			//item commands
    			else if (input.equals("defuse")){
    				if (inventory.contains("bomb defuser")){
    					System.out.println("Trying to defuse bomb...");
    					hunger -= 4;
    					// Check if item is a valid room item
    					boolean validRoomItem = false;
    					for (String roomItems : room[x][y].items ) {
    						if (roomItems.equals("bomb")) {
    							validRoomItem = true;
    							break;
    						}
    					}
    					if (validRoomItem == true){
        	        		if(hunger < 0){
        	        			System.out.println("You were one step away from winning!");
        	        			System.out.println("Unfortunately, you used all your hunger to defuse the bomb!");
        	        			Endgame end = new Endgame();
        	        			end.starve();
        	        		}
    						System.out.println("Bomb defused.");
    						playing = false;
    							Endgame end = new Endgame();
    							end.win();
    					} else {
    		        		if(hunger < 0){
    		        			Endgame end = new Endgame();
    		        			end.starve();
    		        		}
    						System.out.println("There is no bomb to defuse!");
    					}
            		
    				} else {
    					System.out.println("You don't have a bomb defuser!");
    				}
        				
    				// Catch-all for invalid input
    			} else {
    				System.out.println("You can't do that.");
    			}
    			
    			//test for starvation
        		if(hunger < 0){
        			Endgame end = new Endgame();
        			end.starve();
        		}
    		}
    		
    		//end the game
    		Endgame end = new Endgame();
    		end.lose();
    	    System.exit(0);
    	}
    }
    
    //test if exists in inventory
    private boolean existsTest(String item){
		for (String itemInInv: inventory) {
			if (itemInInv.equals(item)) {
				return true;
			}
		}
		return false;
    }
    
    private void removeItem (String item){
    	inventory.remove(item);
    }
}
