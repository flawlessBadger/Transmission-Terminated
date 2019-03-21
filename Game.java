public class Game {
    private Character character;
    private Room currentRoom;
    private Terminal terminal;
    private int talkingSpeed = 30;
    private boolean isPatched = false;
    private boolean isFullpower = false;
    private boolean isSuitFixed = false;
    
    public static void main(String[] args) {
        new Game();
    }
    
    public Game() {
        character = new Character();
        terminal = new Terminal();

        //rooms
        Room bridge = new Room("control room", "");
        Room central = new Room("central room", "");
        Room eng = new Room("engineering wing", "");
        Room tool = new Room("tool room", "");
        Room generator_room = new Room("generator room", "");
        Room space_gear_room = new Room("space gear room", "");
        Room crew_wing = new Room("crew wing", "");
        Room bathroom = new Room("bathroom", "");
        Room medbay = new Room("medbay", "");
        Room living_quarters = new Room("living quarters", "");
        Room corridor = new Room("corridor", "");
        Room storage = new Room("storage", "");
        Room loading_bay = new Room("loading bay", "");
        Room engine_room = new Room("engine room", "");

        currentRoom = bridge;
        
        bridge.addExit("north", central);
        central.addExit("north", corridor);
        central.addExit("east", crew_wing);
        central.addExit("south", bridge);
        central.addExit("west", eng);
        eng.addExit("north", tool);
        eng.addExit("east", central);
        eng.addExit("west", space_gear_room);
        eng.addExit("south", generator_room);
        tool.addExit("south", eng);
        generator_room.addExit("north", eng);
        space_gear_room.addExit("east", eng);
        crew_wing.addExit("north", bathroom);
        crew_wing.addExit("east", living_quarters);
        crew_wing.addExit("south", medbay);
        crew_wing.addExit("west", central);
        living_quarters.addExit("west", crew_wing);
        medbay.addExit("north", crew_wing);
        bathroom.addExit("south", crew_wing);
        corridor.addExit("south", central);
        corridor.addExit("north", storage);
        storage.addExit("north", loading_bay);
        storage.addExit("south", corridor);
        loading_bay.addExit("south", storage);
        loading_bay.addExit("north", engine_room);
        engine_room.addExit("south", loading_bay);

        storage.makeInaccessible("the door is broken, I will need to break through");
        tool.makeInaccessible("I need Ripley's security card to get in");
        loading_bay.makeInaccessible("The loading door is open, I will need to use a space suit to get through");
        central.makeInaccessible("The door is locked");

        tool.addItem("plasma cutter", "really powerful cutter, This should serve well to get through most barricades", true);
        space_gear_room.addItem("space suit", "It is a damaged, needs repairs before use", true);
        bridge.addItem("terminal", "Used to control the ship", false);
        storage.addItem("broken oil tank", "it burst open, strange, it looks like something broke through from the inside", false);
        medbay.addItem("atomic joiner", "This thing can fix anything, wounds, plates, tools, you name it", false);
        storage.addItem("power cell", "We should try putting it inside the generator", true);
        corridor.addItem("body", "poor sarah, looks like she has just shut the door,     \n when something happened to her, but what?", false);
        loading_bay.addItem("body", "If that's Craig then what is inside the engine room", false);
        corridor.addItem("security card", "i can use this to get into the tool room", true);
        generator_room.addItem("generator", "It Provides power to the ship", false);
        bathroom.addItem("toilet", "its a toilet what else do you want to know", false);
        intro();
    }
    
    private void intro() {
        terminal.print("Welcome to ", talkingSpeed, false);
        terminal.print("TransmissionTerminated", talkingSpeed, true);
        terminal.print("\n.\n.\n", 500, false);
        terminal.print("In the year 2025 NASA discovered oil beneath the surface of the moon.\n", talkingSpeed, false);
        terminal.print("Within two years the US has invaded the moon and declared it as the 51st US state. \n", talkingSpeed, false);
        terminal.print("By the year 2030 MoonOil Ltd. was one of the largest oil companies in the world,\n", talkingSpeed, false);
        terminal.print("Scientist however greatly overestimated the amount of oil the moon has \n", talkingSpeed, false);
        terminal.print("and MoonOil Ltd. went bankrupt in the year 2035.\n", talkingSpeed, false);
        terminal.print("Now it is the year 2037 and only a few small companies are still operating on the moon.\n", talkingSpeed, false);
        terminal.print("You are a radio operator at one of them, this looks to be another uneventful night shift.\n", talkingSpeed, false);
        terminal.print("When suddenly...", talkingSpeed, false);
        terminal.print("\n.\n.\n", 200, false);
        terminal.print("INCOMING TRANSMISSION FROM XD-1! ACCEPT? (yes/no)", talkingSpeed, false);
        String[] response = terminal.prompt();
        if (response[0].equals("yes")) {
            terminal.print("Bill: This is Bill from the ship XD-1, we have an emergency\n", talkingSpeed, true);
            terminal.print("You:  What happened?\n", talkingSpeed, false);
            terminal.print("Bill: I was in the control room when I heard a scream followed by an explosion!\n      Before I could go see what happened the door shut itself\n", talkingSpeed, true);
            terminal.print("      What do I do now?\n", talkingSpeed, true);
            terminal.print("(Type 'help' to get a list of available commands)", talkingSpeed / 2, false);
            step();
        } else
            terminal.print("You decided to ignore the transmission and the ship XD-1 was never heard from again. \nYou were fired the next day\n\n", talkingSpeed, false);
    }
    
    private void step() {


        String[] response = terminal.prompt();

        switch (response[0]) {
            case "go":
                go(response[1]);
                break;
            case "look":
                look();
                break;
            case "inventory":
                inventory();
                break;
            case "take":
                takeItem(response[1]);
                break;
            case "drop":
                dropItem(response[1]);
                break;
            case "examine":
                examine(response[1]);
                break;
            case "ship":
                ship(response[1]);
                break;
            case "help":
                help();
                break;
            case "use":
                use(response[1]);
                break;
            case "quit":
                terminal.print("Bill: Wait no! don't leave me here\n", talkingSpeed, true);
                terminal.print(" Transmission Terminated", talkingSpeed, false);
                System.exit(0);
                break;
            default:
                terminal.print("Bill: Sorry I didnt get that", talkingSpeed, true);
        }

        step();

    }

    //commands
    private void help() {
        terminal.print(" go <direction>\n", 0, false);
        terminal.print("    The available directions are north, east, south and west\n", 0, false);
        terminal.print("    Move to a room in selected direction\n", 0, false);
        terminal.print(" look\n", 0, false);
        terminal.print("    See adjacent rooms and the items in this room\n", 0, false);
        terminal.print(" inventory\n", 0, false);
        terminal.print("    See the list of items inside the characters inventory\n", 0, false);
        terminal.print(" take <item>\n", 0, false);
        terminal.print("    Pick up an item and put it inside characters inventory\n", 0, false);
        terminal.print("    Not all items can be picked up\n", 0, false);
        terminal.print(" drop <item>\n", 0, false);
        terminal.print("    drop an item from characters inventory\n", 0, false);
        terminal.print(" use <item>\n", 0, false);
        terminal.print("    Attempt to use an item\n", 0, false);
        terminal.print(" examine <item>\n", 0, false);
        terminal.print("    Examine an item in the characters inventory or inside the current room\n", 0, false);
        terminal.print(" quit\n", 0, false);
        terminal.print("    Quits the game", 0, false);
    }

    private void takeItem(String name) {
        terminal.print(currentRoom.moveItem(character, name) ? "Bill: I picked it up" : "Bill: I cant do that", talkingSpeed, true);
    }

    private void dropItem(String name) {
        if (character.moveItem(currentRoom, name))
            terminal.print("Bill: I droped it", talkingSpeed, true);
        else terminal.print("Bill: I don't have that", talkingSpeed, true);
    }

    private void examine(String name) {
        if (currentRoom.contains(name))
            terminal.print("Bill: " + currentRoom.getItemDesc(name), talkingSpeed, true);
        else if (character.contains(name))
            terminal.print("Bill: " + character.getItemDesc(name), talkingSpeed, true);
        else
            terminal.print("Bill: I don't see that in here", talkingSpeed, true);
    }

    private void use(String name) {
        if (character.contains(name) || currentRoom.contains(name)) {
            InventoryInterface ii = character.contains(name) ? character:currentRoom;
              // ii.useItem();
            switch (name) {
                case "terminal":
                    if (ii.getItemUses(name) == 0) {
                        isPatched = true;
                        terminal.print("Bill: " + "I am patching you in\n", talkingSpeed, true);
                        terminal.print(" 'ship' commands are available now ", talkingSpeed, false);
                        ii.useItem(name);
                    } else terminal.print("Bill: " + "There is nothing else I can do with this", talkingSpeed, true);
                    break;
                case "security card":
                    if (currentRoom.getName().equals("engineering wing") && ii.getItemUses(name) == 0) {
                        currentRoom.getExit("north").setAccessibility(true);
                        terminal.print("Bill: " + "door to tool room is unlocked now", talkingSpeed, true);
                        ii.useItem(name);
                    } else
                        terminal.print("Bill: " + "I don't know what to do with that here", talkingSpeed, true);
                    break;
                case "power cell":
                    if (currentRoom.getName().equals("generator room") && ii.getItemUses(name) == 0) {
                        isFullpower = true;
                        terminal.print("Bill: " + "The power cell jump started the generator, it is operating at full power now", talkingSpeed, true);
                        ii.useItem(name);
                    } else
                        terminal.print("Bill: " + "I don't know what to do with that here", talkingSpeed, true);
                    break;
                case "atomic joiner":
                    if (currentRoom.getName().equals("medbay") && ii.getItemUses(name) == 0 && character.contains("space suit")) {
                        if (isFullpower) {
                            isSuitFixed = true;
                            terminal.print("Bill: " + "The space suit is fixed now", talkingSpeed, true);
                            ii.useItem(name);
                        } else
                            terminal.print("Bill: " + "The ship needs to be running at full power to use this machine", talkingSpeed, true);
                    } else
                        terminal.print("Bill: " + "I don't know what to do with that", talkingSpeed, true);
                    break;
                case "space suit":
                    if (character.contains("space suit") && ii.getItemUses(name) == 0) {
                        if (isSuitFixed) {
                           //s currentRoom.getExit("north").setAccessibility(true);
                            terminal.print("Bill: " + "I've put it on, now I can go to the loading bay", talkingSpeed, true);
                            ii.useItem(name);
                        } else terminal.print("Bill: " + "It needs to be fixed first", talkingSpeed, true);
                    } else
                        terminal.print("Bill: " + "I don't know what to do with that here", talkingSpeed, true);
                    break;
                case "plasma cutter":
                    if (currentRoom.getName().equals("corridor") && ii.getItemUses(name) == 0) {
                        terminal.print("Bill: " + "I've cut a hole inside the door, The way to the storage room is open now", talkingSpeed, true);
                        currentRoom.getExit("north").setAccessibility(true);
                        ii.useItem(name);
                    } else
                        terminal.print("Bill: " + "I don't know what to do with that here", talkingSpeed, true);
                    break;
                case "toilet":
                    terminal.print("Bill: " + "Is something wrong with you?", talkingSpeed, true);
                    ii.useItem(name);
                    break;
                default:
                    terminal.print("Bill: " + "I don't know what to do with that", talkingSpeed, true);
                    break;
            }
        } else
            terminal.print("Bill: " + "I don't know what that is", talkingSpeed, true);
    }

    private void ship(String command) {

        if (isPatched) {
            if (command.equals("") || command.equals("help")) {
                terminal.print(" ship commands: ship crew, ship scan, ship unlock", talkingSpeed, false);
            } else if (command.equals("unlock")) {
                if (currentRoom.getName().equals("control room"))
                    currentRoom.getExit("north").setAccessibility(true);
                terminal.print(" doors unlocked", talkingSpeed, false);
            } else if (command.equals("crew")) {
                terminal.print(" CRAIG\n    Age: 27\n    Position: Captain\n", talkingSpeed, false);
                terminal.print(" RIPLEY\n    Age: 30\n    Position: Technician\n", talkingSpeed, false);
                terminal.print(" BILL\n    Age: 24\n    Position: Radio operator", talkingSpeed, false);
            } else if (command.equals("scan")) {
                terminal.print(" Warning: Engines are shut down\n", talkingSpeed, false);
                terminal.print(" Lifeforms detected: 2 - engine room and " + currentRoom.getName(), talkingSpeed, false);
                terminal.print("\nBill: The engine room is at the very north of the ship, we should get there ", talkingSpeed, true);
            }else
            terminal.print("Unknown command", talkingSpeed, false);
          
        } else
            terminal.print("Command unavailable", talkingSpeed, false);

    }

    private void look() {
        terminal.print("Bill: " + "I am in the " + currentRoom.getName(), talkingSpeed, true);
        for (String key : currentRoom.getExits().keySet()) {
            terminal.print("\n      there is " + currentRoom.getExit(key).getName() + " to the " + key, talkingSpeed, true);
        }
        if (!currentRoom.getItems().isEmpty()) {
            terminal.print("\n      I see ", talkingSpeed, true);
            for (String key : currentRoom.getItems().keySet()) {
                terminal.print(key + ", ", talkingSpeed, true);
            }
        }
    }

    private void inventory() {
        terminal.print("Bill: " + "I have ", talkingSpeed, true);
        if (character.getItems().isEmpty())
            terminal.print("nothing.", talkingSpeed, true);
        else
            for (String key : character.getItems().keySet()) {
                terminal.print(key + ", ", talkingSpeed, true);
            }
    }

    private void go(String direction) {
        if (!direction.equals("north") && !direction.equals("south") && !direction.equals("east") && !direction.equals("west"))
            terminal.print("Bill: " + "Thats not a direction.", talkingSpeed, true);
        else {
            Room r = currentRoom.getExit(direction);
            if (r == null) {
                terminal.print("Bill: " + "Not possible\n", talkingSpeed, true);
                terminal.print("You:  Why?\n", talkingSpeed, false);
                terminal.print("Bill: " + "Because there is nothing in that direction!", talkingSpeed, true);
            } else{
            if(currentRoom.getName().equals("storage")&&character.getItemUses("space suit")>0)
                 r.setAccessibility(true);
                if (!r.getAccessibility()) {
                terminal.print("Bill: " + "Not possible\n", talkingSpeed, true);
                terminal.print("You:  Why?\n", talkingSpeed, false);
                terminal.print("Bill: " + "Because " + r.getBlockedResponse(), talkingSpeed, true);
            } else {
                currentRoom = r;
                if (currentRoom.getName().equals("engine room")) end();
                terminal.print("Bill: " + "I am now in the " + currentRoom.getName(), talkingSpeed, true);
            }
        }
        }

    }


    private void end() {
        terminal.print("Bill: Thanks for helping me get the last one, see you soon earthling\n", talkingSpeed * 3, true);
        terminal.print("\nTransmission Terminated\n", talkingSpeed * 3, false);
        System.exit(0);
    }



}
