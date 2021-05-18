package database;

import java.util.List;

import model.Puupalikka;


public interface PuuntiheysDao {

    public List<Puupalikka> getAllItems();

    public Puupalikka getItem(long id);

    public boolean addItem(Puupalikka newItem);

    public boolean removeItem(Puupalikka item);
}