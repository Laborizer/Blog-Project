package fi.tuni.blogproject;

/**
 * Class used for converting the curl commands into objects.
 *
 * <p>
 *     Objects are made based on the data in curl.commands.json -file
 *     in application-properties.
 * </p>
 */
public class CurlCommand {
    private String name;
    private String command;

    /**
     * Constructor for CurlCommand.
     *
     * @param name Name/description of the command.
     * @param command Curl command string.
     */
    public CurlCommand(String name, String command) {
        this.name = name;
        this.command = command;
    }

    /**
     * Default constructor for CurlCommand.
     */
    public CurlCommand() {}

    /**
     * Gets the name of the curl command.
     *
     * @return Name of the curl command.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name for curl command.
     *
     * @param name Name of the curl command.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the curl command.
     *
     * @return Curl command.
     */
    public String getCommand() {
        return command;
    }

    /**
     * Sets the curl command.
     *
     * @param command Curl command.
     */
    public void setCommand(String command) {
        this.command = command;
    }
}
