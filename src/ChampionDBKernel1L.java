import components.map.Map;
import components.map.Map1L;

/**
 * Kernel implementation of ChampionDB using a Map representation.
 *
 * @Convention: - champions != null - All keys in champions are non-null,
 *              unique, and correspond to the champion's name.
 *
 * @Correspondence: - Represents a ChampionDB where each key in the map is the
 *                  champion's name and the associated value is the Champion
 *                  object containing name, description, role, difficulty.
 */
public class ChampionDBKernel1L extends ChampionDBSecondary {

    /**
     * Map from champion names to Champion objects.
     */
    private final Map<String, Champion> champions;

    /**
     * creates a new empty ChampionDB.
     *
     * @ensures database is empty
     */
    public ChampionDBKernel1L() {
        this.champions = new Map1L<>();
    }

    @Override
    public void addChampion(String name, String description, String role,
            int difficulty) {
        assert name != null && !champions.hasKey(
                name) : "Precondition violated: name must be unique and non-null";
        champions.add(name, new Champion(name, description, role, difficulty));
    }

    @Override
    public Champion removeChampion(String name) {
        assert name != null && champions
                .hasKey(name) : "Precondition violated: name must exist";
        Champion removed = champions.value(name);
        champions.remove(name);
        return removed;
    }

    @Override
    public Champion getChampion(String name) {
        assert name != null && champions
                .hasKey(name) : "Precondition violated: name must exist";
        return champions.value(name);
    }

    @Override
    public void clear() {
        champions.clear();
    }

    @Override
    public int size() {
        return champions.size();
    }

    @Override
    protected ChampionDB newInstance() {
        return new ChampionDBKernel1L();
    }

    @Override
    public void transferFrom(ChampionDB source) {
        assert source != null : "Violation of: source is not null";
        assert source != this : "Violation of: source is not this";

        ChampionDBKernel1L localSource = (ChampionDBKernel1L) source;

        this.champions = localSource.champions;

        localSource.champions = localSource.createNewRep();
    }
    // Champion class representing champion data.

    private static class Champion {
        private final String name;
        private final String description;
        private final String role;
        private final int difficulty;

        private Champion(String name, String description, String role,
                int difficulty) {
            this.name = name;
            this.description = description;
            this.role = role;
            this.difficulty = difficulty;
        }

        @Override
        public String toString() {
            return name + " (" + role + ", difficulty: " + difficulty + "): "
                    + description;
        }
    }
}