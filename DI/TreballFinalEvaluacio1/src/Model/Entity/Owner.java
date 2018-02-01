package Model.Entity;

public class Owner {

    private String id;
    private String name;
    private String lastName;
    private String phone;

    public String getId(){
        return id;
    }

    public String getName(){
        return name;
    }

    public String getLastName(){
        return lastName;
    }

    public String getPhone(){
        return phone;
    }

    public Owner(String id, String name, String lastName, String phone) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.phone = phone;
    }
}
