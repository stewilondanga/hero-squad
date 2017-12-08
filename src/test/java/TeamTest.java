import org.junit.*;
import static org.junit.Assert.*;
import java.util.*;
import java.util.List;
import java.util.ArrayList;

public class TeamTest {

  @Test
    public void all_returnsAllInstancesOfSquad_true() {
      Team firstTeam = new Team("Moringaladies");
      Team secondTeam = new Team("Moringagents");
      assertEquals(true, Team.all().contains(firstTeam));
      assertEquals(true, Team.all().contains(secondTeam));
    }

  @Test
    public void clear_emptiesAllCategoriesFromList_0() {
      Team testTeam = new Team("Moringaladies");
      Team.clear();
      assertEquals(Team.all().size(), 0);
    }

  @Test
    public void find_returnsTeamWithSameId_secondTeam() {
      Team.clear();
      Team firstTeam = new Team("Moringaladies");
      Team secondTeam = new Team("Moringagents");
      assertEquals(Team.find(secondTeam.getId()), secondTeam);
    }

//adding a hero to a squad
  @Test
    public void addHero_addsHeroToList_true() {
      Team testTeam = new Team("Moringaladies");
      Hero testHero = new Hero("Codewarninja, peergradetroll, canvasreaper, gmailgangsta");
      testTeam.addHero(testHero);
      assertTrue(testTeam.getHeroes().contains(testHero));
    }
}
