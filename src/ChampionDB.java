import components.simplewriter.SimpleWriter;

/**
 * Extending {@code ChampionDBKernel} with additional operations.
 *
 * @extends ChampionDBKernel
 */
public interface ChampionDB extends ChampionDBKernel {

    /**
     * Checks whether the given champion name exists in this database.
     *
     * @param name
     *            the champion's name
     * @return true if a champion with that name exists, false otherwise
     * @ensures contains = (name in DOMAIN(this.champions)) and this = #this
     */
    boolean contains(String name);

    /**
     * Prints all champions in this database in an unspecified order.
     *
     * @param out
     *            the output stream
     * @requires out.isOpen
     * @ensures this = #this and out contains printed representation of all
     *          champions in this
     */
    void printAll(SimpleWriter out);

    /**
     * Returns a string representation of this database.
     *
     * @return string form of this database
     * @ensures toString = [string showing champions in unspecified order] and
     *          this = #this
     */

}
