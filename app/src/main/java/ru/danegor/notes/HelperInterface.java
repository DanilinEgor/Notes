package ru.danegor.notes;

import java.util.List;

/**
 * Created by Egor on 04.01.2015.
 */
public interface HelperInterface {
    public void save(List<String> texts);
    public List<String> load();
}
