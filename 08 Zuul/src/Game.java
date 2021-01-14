import java.util.ArrayList;

/**
 *  This class is the main class of the "World of Zuul" application. 
 *  "World of Zuul" is a very simple, text based adventure game.  Users 
 *  can walk around some scenery. That's all. It should really be extended 
 *  to make it more interesting!
 * 
 *  To play this game, create an instance of this class and call the "play"
 *  method.
 * 
 *  This main class creates and initialises all the others: it creates all
 *  rooms, creates the parser and starts the game.  It also evaluates and
 *  executes the commands that the parser returns.
 * 
 * @author  Michael Kolling and David J. Barnes
 * @version 1.0 (February 2002)
 */

class Game 
{
	//Initialize the different methods and the different Rooms + inventory
    private Parser parser;
    private Room currentRoom;
    Room outside, theatre, pub, lab, office, onetwenty, peculiar, cellar, gym, spanish, math, band, science,stairs1,stairs2,exit;
    ArrayList<Item> inventory = new ArrayList<Item>();
    /*
     * Create the game and initialise its internal map.
     */
    public Game() 
    {
        createRooms();
        parser = new Parser();
    }
    
    //calls game and starts play method
    public static void main (String[] args) {
    	Game mygame = new Game();
    	mygame.play();
    }
    
    /**
     * Create all the rooms and link their exits together.
     * Creates items in rooms or inventory
     */
    private void createRooms()
    {
       
        // create the rooms
        outside = new Room("outside in a courtyard. You have 4 exits.");
        theatre = new Room("in a lecture theatre");
        pub = new Room("in the campus pub");
        lab = new Room("in a computing lab");
        office = new Room("in the computing admin office");
        onetwenty = new Room("in the coolest place in the world.");       
        peculiar = new Room("a very strange room...");
        cellar = new Room("a very dark Room...");
        gym = new Room("a very large gym.");
        spanish = new Room("a spanish room.");
        math = new Room("a math room. There seems to be a lot of scribbles on the wall");
        band = new Room("a band room. Could there be any instruments that might be useful?");
        science = new Room("a science room. Chemicals and explosions might be helpful...");
        stairs1 = new Room("a hallway that leads down to a cellar...");
        stairs2 = new Room("another hallway that leads down to the cellar");
        exit = new Room("a room that leads to an exit!");
        
        // initialize room exits
        outside.setExit("east", theatre);
        outside.setExit("south", lab);
        outside.setExit("west", pub);
        outside.setExit("north", onetwenty);

        theatre.setExit("west", outside);
        theatre.setExit("south", band);
        theatre.setExit("east", stairs2);

        pub.setExit("east", outside);
        pub.setExit("north", math);
        pub.setExit("south",science);
        
        lab.setExit("north", outside);
        lab.setExit("south", peculiar);
        lab.setExit("east", band);

        office.setExit("south", onetwenty);
        
        onetwenty.setExit("north", office);
        onetwenty.setExit("south", outside);
        onetwenty.setExit("east", stairs1);
        onetwenty.setExit("west", math);
        
        peculiar.setExit("north", lab);
        peculiar.setExit("west", gym);
        peculiar.setExit("south",exit);
        
        cellar.setExit("west", stairs1);
        cellar.setExit("south", stairs2);
        
        gym.setExit("north", science);
        gym.setExit("east", peculiar);
        
        spanish.setExit("north", stairs2);
        spanish.setExit("west", band);
        
        math.setExit("east", onetwenty);
        math.setExit("south",pub);
        
        band.setExit("east",spanish);
        band.setExit("west",lab);
        band.setExit("north",theatre);
        
        science.setExit("north",pub);
        science.setExit("south",gym);
        
        stairs1.setExit("east",cellar);
        stairs1.setExit("west",cellar);
        
        stairs2.setExit("south", spanish);
        stairs2.setExit("north",cellar);
        stairs2.setExit("west", theatre);
        
        exit.setExit("north",peculiar);

        currentRoom = outside;  // start game outside
        //initialize inventory and what's in each room
        inventory.add(new Item("Computer"));
        
        onetwenty.setItem(new Item("Robot"));
        
        peculiar.setItem(new Item("Tusk"));
        peculiar.setItem(new Item("Cooler"));
        
        theatre.setItem(new Item("Flashlight"));
        
        science.setItem(new Item("Match"));
        science.setItem(new Item("CF3"));
        
        math.setItem(new Item("Ruler"));
        
        cellar.setItem(new Item ("Cotton"));
        
    }

    /**
     *  Main play routine.  Loops until end of play.
     */
    public void play() 
    {            
        printWelcome();

        // Enter the main command loop.  Here we repeatedly read commands and
        // execute them until the game is over.
                
        boolean finished = false;
        while (! finished) {
            Command command = parser.getCommand();
            finished = processCommand(command);
        }
        System.out.println("Thank you for playing.  Good bye.");
    }

    /**
     * Print out the opening message for the player.
     */
    private void printWelcome()
    {
        System.out.println();
        System.out.println("Welcome to Adventure!");
        System.out.println("Adventure is a new, incredibly boring adventure game.");
        System.out.println("Type 'help' if you need help.");
        System.out.println("Go through your rooms by using the commands \"go, help, inventory, get, drop and quit\". Good luck!");
        System.out.println();
        System.out.println(currentRoom.getLongDescription());
    }

    /**
     * Given a command, process (that is: execute) the command.
     * If this command ends the game, true is returned, otherwise false is
     * returned.
     */
    private boolean processCommand(Command command) 
    {
        boolean wantToQuit = false;

        if(command.isUnknown()) {
            System.out.println("I don't know what you mean...");
            return false;
        }

        String commandWord = command.getCommandWord();
        if (commandWord.equals("help"))
            printHelp();
        else if (commandWord.equals("go"))
            wantToQuit = goRoom(command);
        else if (commandWord.equals("quit")) {
            wantToQuit = quit(command);
        }
        else if (commandWord.equals("inventory")) {
        	printInventory();
        }
        else if (commandWord.equals("get")) {
        	getItem(command);
        }
        else if(commandWord.equals("drop")) {
        	dropItem(command);
        }
        return wantToQuit;
    }

    //drop item method which is very similar to the getItem command 
    //Drops item in current room
    private void dropItem(Command command) 
    {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know what to drop...
            System.out.println("Drop what?");
            return;
        }

        String item = command.getSecondWord();

        // Try to leave current room.
        Item newItem = null;
        int index = 0;
        for(int i = 0; i < inventory.size(); i++) {
        	if(inventory.get(i).getDescription().equals(item)) {
        		newItem = inventory.get(i);
        		index = i;
        	}
        }
        if (newItem == null)
            System.out.println("That item is not in your inventory");
        else {
            inventory.remove(index);
            currentRoom.setItem(new Item(item));
            System.out.println("Dropped: " + item);
        }
    }
    
    //take item from rooms items and adds into your inventory
    //makes item in room disappear
    private void getItem(Command command) 
    {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know what to get...
            System.out.println("Get what?");
            return;
        }

        String item = command.getSecondWord();

        Item newItem = currentRoom.getItem(item);
        
        if (newItem == null)
            System.out.println("That item is not in your inventory!");
        else {
        	inventory.add(newItem);
            currentRoom.removeItem(item);
            System.out.println("Picked up:" + item);
        }
    }
    
    //prints out what you are currently holding in your inventory
    private void printInventory() {
    	String output = "";
    	for (int i = 0; i < inventory.size(); i++) {
			output = output + inventory.get(i).getDescription() + " ";
		}
    	System.out.println("You are carrying: ");
    	System.out.println(output);
    }
    // implementations of user commands:

    /**
     * Print out some help information.
     * Here we print some stupid, cryptic message and a list of the 
     * command words.
     */
    private void printHelp() 
    {
        System.out.println("You are lost. You are alone. You wander");
        System.out.println("around at the university.");
        System.out.println();
        System.out.println("Your command words are:");
        parser.showCommands();
    }

    /** 
     * Try to go to one direction. If there is an exit, enter the new
     * room, otherwise print an error message.
     */
    private boolean goRoom(Command command) 
    {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("Go where?");
            return false;
        }

        String direction = command.getSecondWord();

        // Try to leave current room.
        Room nextRoom = currentRoom.getExit(direction);

        if (nextRoom == null)
            System.out.println("There is no door!");
        else {
            currentRoom = nextRoom;
            System.out.println(currentRoom.getLongDescription());
            if(currentRoom == exit) {
            	System.out.println("You win!");
            	return true;
            }
        }
        return false;
    }

    /** 
     * "Quit" was entered. Check the rest of the command to see
     * whether we really quit the game. Return true, if this command
     * quits the game, false otherwise.
     */
    private boolean quit(Command command) 
    {
        if(command.hasSecondWord()) {
            System.out.println("Quit what?");
            return false;
        }
        else {
            return true;  // signal that we want to quit
        }
    }
}