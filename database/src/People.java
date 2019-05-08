

public class People {
    protected int id;
    protected String name;
    protected String address;
    protected String status;
 
    public People() {
    }
 
    public People(int id) {
        this.id = id;
    }
 
    public People(int id, String name, String address, String status) {
        this(name, address, status);
        this.id = id;
    }
     
    public People(String name, String address, String status) {
        this.name = name;
        this.address = address;
        this.status = status;
    }
 
    public int getId() {
        return id;
    }
 
    public void setId(int id) {
        this.id = id;
    }
 
    public String getName() {
        return name;
    }
 
    public void setName(String name) {
        this.name = name;
    }
 
    public String getAddress() {
        return address;
    }
 
    public void setAddress(String address) {
        this.address = address;
    }
 
    public String getStatus() {
        return status;
    }
 
    public void setStatus(String status) {
        this.status = status;
    }
}