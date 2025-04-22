****Moved saves folder to assets directory added line to test save files for sprites and slightly changes layout for x and y position in test save files.

****Added debug sys.prnt lines to TurnBasedGame

****Created a CharacterManager class to access CharacterGenerator private methods

****Added to Character. class 2 imports, a spritePath and a texture, initialized spitePAth ->
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

String spritePath = "";

Character.java
Added to:public Character{
private String spritePath;
private Texture texture;
private Sprite sprite;

	      	...

	public Character() {
        	this.spritePath = spritePath;
        	this.texture = new Texture(Gdx.files.internal(spritePath));
        	this.sprite = new Sprite(this.texture);

		...

	public String getSpritePath() { return spritePath; }
    	public void setSpritePath(String spritePath) {
        this.spritePath = spritePath;
        this.texture = new Texture(Gdx.files.internal(spritePath));
        this.sprite = new Sprite(texture);
    }

    public Texture getTexture() {
        return texture;
    }

    public Sprite getSprite() {
        return sprite;
    }

    public Texture getTexture() { return texture; }




****Added a sys.print debug line to the class CharacterGenerator's loadCharacters() method, added a new case:
case "sprite":
spritePath = value;
break;

****changed a parameter in the class CharacterGenerator to be more clear by providing context to the directory tree ->
CharacterGenerator.java
change from:
public static void loadCharacters() {

        File savesDir = new File("saves");

to:
public static void loadCharacters() {
System.out.println("[DEBUG] Working directory: " + new java.io.File(".").getAbsolutePath());

        File savesDir = new File("assets/saves");

****Changed getFirst() and getLast() in GameLauncher to get(0) and get(characterList.size() - 1) for compatibility reasons->

GameLauncher.java
changed from:
Character Jane = Character.getCharacterArrayList().getFirst();
Character John = Character.getCharacterArrayList().getLast();

to:
List<Character> characterList = Character.getCharacterArrayList();

        Character Jane = Character.getCharacterArrayList().get(0);
        Character John = characterList.get(characterList.size() - 1);


****Changed setting Cell's position from inside MapGenerator to inside Cell ->

Cell.java
changed from:
public Cell(){
this.position = new Position();
this.isOccupied = false;
}

to:
public Cell(int x, int y) {
this.position = new Position();
this.position.setPosition(x, y);
this.isOccupied = false;
}


MapGenerator.java
changed from:
public static void generateCellArray(int width, int height) {
cellArray.clear(); //Clear cell array in case need to be called again
for (int y = 0; y <= height - 1; y++) {
for (int x = 0; x <= width - 1; x++) {
Cell cell = new Cell();
cell.setPosition(x, y);
cellArray.add(cell);
}
}
}


to:
public static void generateCellArray(int width, int height) {
for (int y = 0; y <= height - 1; y++) {
for (int x = 0; x <= width - 1; x++) {
cellArray.add(new Cell(x, y));
}
}
}

**** added spritePath as a parameter for Character, PCCharacter and NPCCharacter. 

**** added replaced addCommand() method to setQ TurnBasedGame.java:
// Only allows ONE command at a time per character
public void setQueuedCommand(Command command) {
// Remove any existing command from the same character
currentCommands.removeIf(c -> c.getCharacter() == command.getCharacter());

        currentCommands.add(command);
        System.out.println("Queued command for: " + command.getCharacter().getName());
    }

**** added in MoveCommand.java:

public Character getCharacter() {
return character;
}

**** added in AttackCommand.java:

java
Copy
Edit
public Character getAttacker() {
return attacker;
}

**** added in TurnBasedGame.java:
public List<Command> getCommandQueue() {
List<Command> allQueued = new java.util.ArrayList<>();
for (Character c : Character.getCharacterArrayList()) {
CommandManager cm = c.getCommandManager();
if (cm != null) {
allQueued.addAll(cm.getCommandQueue()); // ← call to per-character queue
}
}
return allQueued;
}

**** added in MoveCommand.java:
public Cell getTarget() {
return nextCell;
}

**** added in AttackCommand.java:
public Character getTarget() {
return defender;
}

**** added these methods to CommandManager.java:
public List<Command> getQueuedCommands() {
return currentCommands;
}

public void clearQueuedCommands() {
commandQueue.clear();
}
