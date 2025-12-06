package Demo;

import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

public class ChampionDBDemo {

    public static void main(String[] args) {
        SimpleWriter out = new SimpleWriter1L();

        ChampionDB db = new ChampionDBKernel1L();

        out.println("=== Adding champions ===");
        db.addChampion("Ahri", "Mobile mage", "Mage", 4);
        db.addChampion("Garen", "Spin-to-win fighter", "Bruiser", 1);

        out.println("\nSize after additions: " + db.size());
        out.println("Database:");
        db.printAll(out);

        out.println("\n=== Retrieving a champion ===");
        out.println("Champion info: " + db.getChampion("Ahri"));

        out.println("\n=== Removing Garen ===");
        db.removeChampion("Garen");
        out.println("Size after removal: " + db.size());

        out.println("\nDatabase now:");
        db.printAll(out);

        out.close();
    }
}
