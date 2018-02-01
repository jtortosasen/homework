package Model.Entity;

public class Parrot {

    private String id;
    private String name;
    private String race;
    private String color;
    private String owner;

    public String getId(){
        return id;
    }

    public String getName(){
        return name;
    }

    public String getRace(){
        return race;
    }

    public String getColor(){
        return color;
    }

    public String getOwner(){
        return owner;
    }

    public Parrot(String id, String name, String race, String color, String owner) {
        this.id = id;
        this.name = name;
        this.race = race;
        this.color = color;
        this.owner = owner;
    }
}
