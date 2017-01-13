package com.plantcola.plantgames;

import java.util.ArrayList;

class Rooms {

    public static void build(Room[][] room, final int WIDTH, final int HEIGHT) {

    	// Initialize rooms
        for (int i = 0; i < WIDTH; i++) {
            for (int j = 0; j < HEIGHT; j++) {
                room[i][j] = new Room(i, "", "", null);
            }
        }
        
        //[x axis][y axis]
        room[0][0].setNumber(1);
        room[0][0].setName("Field");
        room[0][0].setDescription("You are in the field of the campus.");
        room[0][0].setItems("apple");

        room[0][1].setNumber(2);
        room[0][1].setName("Lab");
        room[0][1].setDescription("You are in the lab of the campus.");
        room[0][1].setItems("bread");

        room[1][0].setNumber(3);
        room[1][0].setName("Library");
        room[1][0].setDescription("You are in the library.");
        room[1][0].setItems("book");
        room[1][0].setItems("banana");

        room[1][1].setNumber(4);
        room[1][1].setName("Toilet");
        room[1][1].setDescription("You are in the public toilet. The bomb is here! Use the bomb defuser to defuse the bomb.");
        room[1][1].setItems("bomb");
        
        room[0][2].setNumber(5);
        room[0][2].setName("Music Room");
        room[0][2].setDescription("You are in the music room.");
        room[0][2].setItems("bomb defuser");
        room[0][2].setItems("biscuit");
        
        room[1][2].setNumber(6);
        room[1][2].setName("Basketball Court");
        room[1][2].setDescription("You are in the basketball court.");
        room[1][2].setItems("chocolate");
        
        room[2][2].setNumber(7);
        room[2][2].setName("Chapel");
        room[2][2].setDescription("You are in the chapel.");
        room[2][2].setItems("time traveller");
        
        room[2][1].setNumber(8);
        room[2][1].setName("Hall");
        room[2][1].setDescription("You are in the hall. Hush!");
        room[2][1].setItems("beef");
        
        room[2][0].setNumber(9);
        room[2][0].setName("Room 1");
        room[2][0].setDescription("You are in room 1.");
        room[2][0].setItems("beamer");
    }

    public static void print(Room[][] room, int x, int y) {
    	System.out.println();
    	System.out.println(room[x][y].getDescription());
    	System.out.println("Items in this room: " + room[x][y].getItems());
    }

    // Remove item from room when added to inventory
    public static void removeItem(Room[][] room, int x, int y, String item) {
    	room[x][y].deleteItem(item);
    }
}

class Room {

    private int number;
    private String name;
    private String description;
    public ArrayList<String> items = new ArrayList<>();

    public Room(int number, String name, String description,
            ArrayList<String> items) {
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getNumber() {
        return this.number;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return this.description;
    }

    public void setItems(String item) {
        this.items.add(item);
    }

    public void deleteItem(String item) {
        this.items.remove(item);
    }

    public ArrayList<String> getItems() {
        return this.items;
    }
}
