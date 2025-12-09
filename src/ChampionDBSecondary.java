import components.simplewriter.SimpleWriter;

/**
 * Layered implementation of secondary methods for {@code ChampionDB}.
 * 
 */
public abstract class ChampionDBSecondary implements ChampionDB {

    /**
     * Default constructor.
     */
    protected ChampionDBSecondary() {
        // Default constructor
    }

    /**
     * Checks whether the given champion name exists in this database.
     *
     * @param name
     *            the champion's name
     * @return true if a champion with that name exists, false otherwise
     * @ensures contains = (name in DOMAIN(this.champions)) and this = #this
     */
    @Override
    public boolean contains(String name) {
        boolean found = false;
        if (name != null && this.size() > 0) {
            try {
                Champion temp = this.getChampion(name);
                if (temp != null) {
                    found = true;
                }
            } catch (AssertionError e) {
                found = false;
            }
        }
        return found;
    }

    /**
     * Prints all champions in this database in an unspecified order.
     *
     * @param out
     *            the output stream
     * @requires out.isOpen
     * @ensures this = #this and out contains printed representation of all
     *          champions in this
     */
    @Override
    public void printAll(SimpleWriter out) {
        assert out.isOpen() : "Violation of: out.isOpen";
        out.println(this.toString());
    }

    /**
     * Returns a string representation of this database.
     *
     * @return string form of this database
     * @ensures toString = [string showing champions in unspecified order] and
     *          this = #this
     */
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append("ChampionDB with ").append(this.size())
                .append(" champions");
        return result.toString();
    }

    /**
     * Reports whether this and the given object are equal.
     *
     * @param obj
     *            the object to compare with this
     * @return true if the two databases contain the same champions, false
     *         otherwise
     * @ensures equals = (this.champions = ((ChampionDB) obj).champions)
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || this.getClass() != obj.getClass()) {
            return false;
        }

        ChampionDB other = (ChampionDB) obj;
        return this.size() == other.size();
    }

    /**
     * Returns a hash code for this database.
     *
     * @return integer hash code for this
     * @ensures hashCode = [consistent with equals]
     */
    @Override
    public int hashCode() {
        return this.size();
    }

    /**
     * Returns a new, empty instance of this {@code ChampionDB}.
     *
     * @return a new instance of the same dynamic type as {@code this}
     * @ensures newInstance.is_empty
     */
    protected abstract ChampionDB newInstance();

}
