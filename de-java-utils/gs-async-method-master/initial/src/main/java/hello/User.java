package hello;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
/**
 * 
 * Create a representation of a GitHub User

	Before you can create a GitHub lookup service, you need to define a representation for the data you’ll 
	retrieve through GitHub’s API.
	
	To model the user representation, you create a resource representation class. Provide a plain old Java 
	object with fields, constructors, and accessors:
 *
 *  Spring uses the Jackson JSON library to convert GitHub’s JSON response into a User object. The 
 *  @JsonIgnoreProperties annotation signals Spring to ignore any attributes not listed in the class. 
 *  This makes it easy to make REST calls and produce domain objects.
 */
@JsonIgnoreProperties(ignoreUnknown=true)
public class User {

    private String name;
    private String blog;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBlog() {
        return blog;
    }

    public void setBlog(String blog) {
        this.blog = blog;
    }

    @Override
    public String toString() {
        return "User [name=" + name + ", blog=" + blog + "]";
    }

}