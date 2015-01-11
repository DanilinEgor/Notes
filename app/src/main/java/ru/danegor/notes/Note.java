package ru.danegor.notes;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

@Table(name = "Notes")
public class Note extends Model {
    @Column(name = "Name")
    public String name;

    @Column(name = "Text")
    public String text;

    public Note() {
        super();
    }

    public Note(String text) {
        super();
        this.text = text;
    }

    public Note(String name, String text) {
        super();
        this.name = name;
        this.text = text;
    }
}
