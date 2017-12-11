import java.util.List;
import java.util.ArrayList;
import java.util.*;


public class Team {
  private String mName;
  private static List<Team> instances = new ArrayList<Team>();
  private int mId;
  private List<Hero> mHeroes;



public Team(String name) {
  mName = name;
  instances.add(this);
  mId = instances.size();
  mHeroes = new ArrayList<Hero>();
}

public String getName() {
  return mName;
}

public static List<Team> all() {
  return instances;
}

public static void clear() {
  instances.clear();
}


public int getId() {
  return mId;
}

public static Team find(int id) {
  return instances.get(id - 1);
}

//adding a member property mHeroes.
//It is an ArrayList that will eventually hold Hero objects belonging to each instance of Team
  public List<Hero> getHeroes() {
    return mHeroes;
  }
//This method will be called upon an individual Team object, and a Hero object will be passed in as an argument
  public void addHero(Hero hero) {
    mHeroes.add(hero);
  }
}
