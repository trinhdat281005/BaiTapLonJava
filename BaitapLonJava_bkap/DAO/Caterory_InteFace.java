package BaitapLonJava_bkap.DAO;

import BaitapLonJava_bkap.entities.Caterory;

import java.util.List;

public interface Caterory_InteFace {
    public List<Caterory> getall();
    public Boolean InSertCaterory (Caterory ca);
    public Boolean UpDateCaterory (Caterory ca);
    public Boolean DeleteCaterory (int id);
    public  List<Caterory> searchCaterory(String name);
}
