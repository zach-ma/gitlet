package capers;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.ObjectOutputStream;

import static capers.Utils.*;

/** A repository for Capers 
 * @author TODO
 * The structure of a Capers Repository is as follows:
 *
 * .capers/ -- top level folder for all persistent data in your lab12 folder
 *    - dogs/ -- folder containing all of the persistent data for dogs
 *    - story -- file containing the current story
 *
 *  change the above structure if you do something different.
 */
public class CapersRepository {
    /** Current Working Directory. */
    static final File CWD = new File(System.getProperty("user.dir"));

    /** Main metadata folder. */
    static final File CAPERS_FOLDER = Utils.join(".capers"); // TODO Hint: look at the `join` function in Utils

    /**
     * Does required filesystem operations to allow for persistence.
     * (creates any necessary folders or files)
     * Remember: recommended structure (you do not have to follow):
     *
     * .capers/ -- top level folder for all persistent data in your lab12 folder
     *    - dogs/ -- folder containing all of the persistent data for dogs
     *    - story -- file containing the current story
     */
    public static void setupPersistence() {
        // TODO
        try {
            CAPERS_FOLDER.mkdir();
            Utils.join(CAPERS_FOLDER, "dogs").mkdir();
            Utils.join(CAPERS_FOLDER, "story").createNewFile();
        } catch (IOException excp) {
            throw error("Internal error setting up persistence .");
        }
    }

    /**
     * Appends the first non-command argument in args
     * to a file called `story` in the .capers directory.
     * @param text String of the text to be appended to the story
     */
    public static void writeStory(String text) {
        // TODO
        File story = Utils.join(CAPERS_FOLDER, "story");
        String newText = Utils.readContentsAsString(story) + text + "\n";
        System.out.println(newText);
        Utils.writeContents(story, newText);
    }

    /**
     * Creates and persistently saves a dog using the first
     * three non-command arguments of args (name, breed, age).
     * Also prints out the dog's information using toString().
     */
    public static void makeDog(String name, String breed, int age) {
        // TODO
        File df = Utils.join(CAPERS_FOLDER, "dogs", name);
        Dog d = new Dog(name, breed, age);
        System.out.println(d);
        Utils.writeObject(df, d);
    }

    /**
     * Advances a dog's age persistently and prints out a celebratory message.
     * Also prints out the dog's information using toString().
     * Chooses dog to advance based on the first non-command argument of args.
     * @param name String name of the Dog whose birthday we're celebrating.
     */
    public static void celebrateBirthday(String name) {
        // TODO
        File df = Utils.join(CAPERS_FOLDER, "dogs", name);
        Dog d = Utils.readObject(df, Dog.class);
        d.haveBirthday();
        Utils.writeObject(df, d);
    }
}
