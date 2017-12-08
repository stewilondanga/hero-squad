import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateENgine;
import static spark.Spark.*;

public class App {
  public static void main(String[] args) {
    staticFileLocation("/public");
    String layout = "templates/layout.vtl";

    ProcessBuilder process = new ProcessBuilder();
  Integer port;
  if (process.environment().get("PORT") != null) {
      port = Integer.parseInt(process.environment().get("PORT"));
  } else {
      port = 4567;
  }

  setPort(port);

  //creating a root route in App.java file that will render our home page
  //displaying custom methods
    get("/", (request, response) -> {
          Map<String, Object> model = new HashMap<String, Object>();
          model.put("heroes", request.session().attribute("heroes"));
          model.put("template", "templates/index.vtl");
          return new ModelAndView(model, layout);
        }, new VelocityTemplateEngine());

        get("heroes/new", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("template", "templates/heroform.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/heroes", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("heroes", Hero.all());
      model.put("template", "templates/heroes.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

//This route will be executed when someone clicks a link to see a particular Hero's detail page
    get("heroes/:id", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      Hero hero = Hero.find(Integer.parseInt(request.params(":id")));
      model.put("hero", hero);
      model.put("template", "templates/hero.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());


//route responsible for rendering the template with the new-team form
    get("/teams/new", (request, response) -> {
  Map<String, Object> model = new HashMap<String, Object>();
  model.put("template", "templates/teamform.vtl");
  return new ModelAndView(model, layout);
}, new VelocityTemplateEngine());

//route to display all teams
get("/teams", (request, response) -> {
  Map<String, Object> model = new HashMap<String, Object>();
  model.put("teams", Team.all());
  model.put("template", "templates/teams.vtl");
  return new ModelAndView(model, layout);
}, new VelocityTemplateEngine());

//routing and a basic template setup
get("/teams/:id", (request, response) -> {
  Map<String, Object> model = new HashMap<String, Object>();
  Team team = Team.find(Integer.parseInt(request.params(":id")));
  model.put("team", team);
  model.put("template", "templates/team.vtl");
  return new ModelAndView(model,layout);
}, new VelocityTemplateEngine());

//Because we are now exclusively creating new Hero objects after selecting their corresponding Team this route will replace our Hero objects
get("teams/:id/heroes/new", (request, response) -> {
  Map<String, Object> model = new HashMap<String, Object>();
  Team team = Team.find(Integer.parseInt(request.params(":id")));
  model.put("team", team);
  model.put("template", "templates/teamheroesform.vtl");
  return new ModelAndView(model, layout);
}, new VelocityTemplateEngine());

// a route to process new-team form submission
post("/teams", (request, response) -> {
  Map<String, Object> model = new HashMap<String, Object>();
  String name = request.queryParams("name");
  Team newTeam = new Team(name);
  model.put("template", "templates/teamsuccess.vtl");
  return new ModelAndView(model, layout);
}, new VelocityTemplateEngine());

//a route to process new-hero form submission
      post("/heroes", (request, response) -> {
        Map<String, Object> model = new HashMap<String, Object>();

      ArrayList<Hero> heroes = request.session().attribute("heroes");
      if (heroes == null) {
      heroes = new ArrayList<Hero>();
      request.session().attribute("heroes", heroes);
      }

      String name = request.queryParams("name");
  int age = Integer.parseInt(request.queryParams("age"));
  String strength = request.queryParams("strength");
  String weakness = request.queryParams("weakness");
  Hero newHero = new Hero(name, age, strength, weakness);
  heroes.add(newHero);

          model.put("template", "templates/success.vtl");
          return new ModelAndView(model, layout);
        }, new VelocityTemplateEngine());


  }
}
