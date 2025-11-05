import components.standard.Standard;

/**
 * Kernel component for a {@code ChampionDB}.
 *
 * @mathsubtype <pre>
 * ChampionDB is finite set of (String, Champion)
 * </pre>
 *
 * @mathmodel <pre>
 * this.champions: Map<String, Champion>
 * </pre>
 *
 * @initially <pre>
 * this.champions = {}
 * </pre>
 */
public interface ChampionDBKernel extends Standard {

    /**
     * Adds a new champion to the database.
     *
     * @param name
     *            the champion's name
     * @param description
     *            the champion's description
     * @param role
     *            the champion's role
     * @param difficulty
     *            the champion's difficulty rating 1–10
     * @updates this
     * @requires name is not in this.champions
     * @ensures this.champions = #this.champions union(old list with new
     *          champion added)
     */
    void addChampion(String name, String description, String role,
            int difficulty);

    /**
     * Removes a champion from the database.
     *
     * @param name
     *            the champion's name
     * @return the {@code Champion} object that was removed
     * @updates this
     * @requires name is in this.champions
     * @ensures removeChampion = #this.champions[name] and this.champions =
     *          #this.champions
     */
    Champion removeChampion(String name);

    /**
     * Retrieves a champion from the database.
     *
     * @param name
     *            the champion's name
     * @return the corresponding {@code Champion}
     * @requires name is in this.champions
     * @ensures getChampion = this.champions[name] and this = #this
     */
    Champion getChampion(String name);

    /**
     * Removes all champions from this database.
     *
     * @updates this
     * @ensures this.champions = {}
     */
    void clear();

    /**
     * Reports the number of champions in this database.
     *
     * @return the size of this database
     * @ensures size = |this.champions|
     */
    int size();
}
