import components.map.Map;
import components.map.Map1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

public class MVP {
    private static class Champion {
        String name;
    String description
        String role;
        int difficulty;

    champion(String name, String description, String role, int difficulty){
        this.name=name;
        this.description=description;
        this.role=role;
        this.difficulty=difficulty;
    }

        public String toString() {
            return this.name + " (" + this.role + ",difficulty"
                    + this.difficulty + "):" + this.description;
        }
    }

    private final Map<String, Champion> champmap;

    // * @ensures this.champMap={}
    public MVP() {
        this.champMap = new Map1L<>();
    }

    /**
     * Adds champion to db
     *
     * @param name
     *            champion name
     * @param description
     *            champion description
     * @param role
     *            champion role (e.g., "Assassin", "Mage")
     * @param difficulty
     *            difficulty rating (1–10)
     * @updates this
     * @requires name is not in this.champMap
     * @ensures this.champMap = #this.champMap combined with {(name, new
     *          Champion(...))}
     */
    public void addChampion(String name, String description, String role,
            int difficulty) {
        Champion c = new Champion(name, description, role, difficulty);
        this.champMap.add(name, c);
    }

    /**
     * Retrieves a champion by name.
     *
     * @param name
     *            champion name
     * @return champion object
     * @requires name is in (this.champMap)
     * @ensures getChampion = this.champMap[name] and this = #this
     */
    public Champion getChampion(String name) {
        return this.champMap.value(name);
    }

    /**
     * Removes a champion from the database.
     *
     * @param name
     *            champion name
     * @updates this
     * @requires name is in (this.champMap)
     * @ensures this.champMap = #this.champMap \ {(name, #this.champMap[name])}
     */
    public void removeChampion(String name) {
        this.champMap.remove(name);
    }

    /**
     * Prints all champions in the database.
     *
     * @param out
     *            output stream
     * @requires out.isOpen
     * @ensures this = #this and out contains printed representation of all
     *          champions
     */
    public void printAll(SimpleWriter out) {
        Map<String, Champion> temp = this.champMap.newInstance();
        temp.transferFrom(this.champMap);
        while (temp.size() > 0) {
            Map.Pair<String, Champion> entry = temp.removeAny();
            out.println(entry.value());
            this.champMap.add(entry.key(), entry.value());

        }
    }

    /**
     * Demonstration main method
     */
    public static void main(String[] args) {
        SimpleWriter out = new SimpleWriter1L();

        MVP db = new MVP();

        db.addChampion("Ahri", "A vastayan mage with charm-based magic.",
                "Mage", 4);
        db.addChampion("Garen",
                "A noble warrior of Demacia with strong defenses.", "Bruiser",
                1);
        db.addChampion("Zed",
                "A master of shadows who excels in assassination.", "Assassin",
                8);

        out.println("=== All Champions ===");
        db.printAll(out);

        out.println();
        out.println("=== Retrieve Example ===");
        out.println(db.getChampion("Zed"));

        out.close();
    }

}
