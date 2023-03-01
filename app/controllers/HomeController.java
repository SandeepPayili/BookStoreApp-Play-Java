package controllers;

import play.mvc.*;

import views.html.*;
import views.html.Home.*;

/** 
 * This controller contains an action to handle HTTP requests
 * to the application's home page.
 */
public class HomeController extends Controller {

    /**
     * An action that renders an HTML page with a welcome message.
     * The configuration in the <code>routes</code> file means that
     * this method will be called when the application receives a
     * <code>GET</code> request with a path of <code>/</code>.
     */
    public Result index() {
        // return ok(index.render("Your new application is ready."));
        return ok(index.render());
        // return ok(index2.render("Default Index Page Provided by Play"));
    }
    public Result welcome(String firstName,String lastName){
        // return ok("Hello! "+ firstName +  " " +lastName + " Welcome to our website!!");
        return ok(welcome.render(firstName,lastName));
    }
}
