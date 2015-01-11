package ru.danegor.notes;

import android.content.Context;
import android.preference.PreferenceManager;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

/**
 * Created by Egor on 04.01.2015.
 */
public class Helper implements HelperInterface {
    private final String TEXTS = "ru.danegor.notes.texts";
    private Context mContext;

    public Helper(Context context) {
        mContext = context;
    }

    @Override
    public void save(List<Note> texts) {
        PreferenceManager.getDefaultSharedPreferences(mContext).edit().putString(TEXTS, new Gson().toJson(texts)).commit();
    }

    @Override
    public List<Note> load() {
        return new Gson().fromJson(PreferenceManager.getDefaultSharedPreferences(mContext).getString(TEXTS, "[]"), new TypeToken<List<Note>>() {
        }.getType());
    }
}
