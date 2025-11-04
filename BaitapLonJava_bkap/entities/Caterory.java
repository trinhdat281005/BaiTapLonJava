package BaitapLonJava_bkap.entities;

public class Caterory {
    private  String name;
    private  String id;
    private boolean status;
    private String parentId ;


    public Caterory( String id ,String name, boolean status, String parentId) {

        this.id = id;
        this.name = name;

        this.status = status;
        this.parentId = parentId;
    }

    public Caterory( String id,String name) {

        this.id = id;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId= parentId;
    }


    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Caterory{" +

                ", id=" + id +
                "name='" + name + '\'' +
                ", parentId='" + parentId + '\'' +
                ", status=" + status +
                '}';
    }


}
