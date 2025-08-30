package Model;

public class PGListings {
    private int id;
    private int ownerId;
    private String title;
    private String city;
    private double rent;
    private String description;

    public PGListings(int id, int ownerId, String title, String city, double rent, String description) {
        this.id = id;
        this.ownerId = ownerId;
        this.title = title;
        this.city = city;
        this.rent = rent;
        this.description = description;
    }

    public PGListings(int ownerId, String title, String city, double rent, String description) {
        this.ownerId = ownerId;
        this.title = title;
        this.city = city;
        this.rent = rent;
        this.description = description;
    }

    public int getId() { return id; }
    public int getOwnerId() { return ownerId; }
    public String getTitle() { return title; }
    public String getCity() { return city; }
    public double getRent() { return rent; }
    public String getDescription() { return description; }

    public void setId(int id) { this.id = id; }
    public void setOwnerId(int ownerId) { this.ownerId = ownerId; }
    public void setTitle(String title) { this.title = title; }
    public void setCity(String city) { this.city = city; }
    public void setRent(double rent) { this.rent = rent; }
    public void setDescription(String description) { this.description = description; }

    
    public String toString() {
        return "PGListing{" +
                "id=" + id +
                ", ownerId=" + ownerId +
                ", title='" + title + '\'' +
                ", city='" + city + '\'' +
                ", rent=" + rent +
                ", description='" + description + '\'' +
                '}';
    }
}
