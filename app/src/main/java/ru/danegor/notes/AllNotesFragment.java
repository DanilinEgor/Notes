package ru.danegor.notes;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.etsy.android.grid.StaggeredGridView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Egor on 31.12.2014.
 */
public class AllNotesFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        StaggeredGridView gridView = (StaggeredGridView) rootView.findViewById(R.id.gridView);
        HelperInterface helper = new Helper(getActivity());
        List<Note> numbers = new ArrayList<>();
        Note note;
        note = new Note("1");
        note.save();
        numbers.add(note);
        note = new Note("2222222222222222222222222222222222222222222222222222222222222222222222");
        note.save();
        numbers.add(note);
        note = new Note("3");
        note.save();
        numbers.add(note);
        note = new Note("4");
        note.save();
        numbers.add(note);
        note = new Note("5");
        note.save();
        numbers.add(note);

        gridView.setAdapter(new ImageAdapter(getActivity(), numbers));
        return rootView;
    }

    class ImageAdapter extends BaseAdapter {
        private Context context;
        private List<String> texts = new ArrayList<>();

        public ImageAdapter(Context context, List<Note> texts) {
            this.context = context;
            for (Note note : texts)
                this.texts.add(note.text);
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder viewHolder;
            if (convertView == null) {
                LayoutInflater inflater = ((Activity) context).getLayoutInflater();
                convertView = inflater.inflate(R.layout.grid_item, parent, false);

                viewHolder = new ViewHolder();
                viewHolder.text = (TextView) convertView.findViewById(R.id.card_textView);
                viewHolder.card = (CardView) convertView.findViewById(R.id.cardView);

                convertView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                    }
                });
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }
            viewHolder.text.setText(texts.get(position));
            return convertView;
        }

        @Override
        public int getCount() {
            return texts.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

    }

    class ViewHolder {
        CardView card;
        TextView text;
    }
}
