package Demo;

import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

public class ChampionDBCompare {
    public static void main(String[] args) {
        SimpleWriter out = new SimpleWriter1L();
        ChampionDB a = new ChampionDBKernel1L();
        ChampionDB b = new ChampionDBKernel1L();

        a.addChampion("Zed", "Shadow assassin", "Assassin", 8);
        b.addChampion("Zed", "Shadow assassin", "Assassin", 8);

        out.println("a.equals(b): " + a.equals(b));
        out.println(
                "a.hashCode == b.hashCode: " + (a.hashCode() == b.hashCode()));

        ChampionDB target = new ChampionDBKernel1L();
        target.transferFrom((ChampionDB) a);
        out.println("After transfer, target.size(): " + target.size());
        out.println("Source a size: " + a.size());

        out.close();
    }
}