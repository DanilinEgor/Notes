package ru.danegor.notes;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.activeandroid.query.Select;
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
        final List<Note> numbers = new Select().from(Note.class).execute();
        gridView.setAdapter(new NotesAdapter(getActivity(), numbers));
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Note note = numbers.get(position);
                Fragment fragment = new EditorFragment();
                Bundle extras = new Bundle();
                extras.putLong("id", note.getId());
                fragment.setArguments(extras);
                ((MainActivity) getActivity()).setFragment(fragment);
            }
        });
        return rootView;
    }

    class NotesAdapter extends BaseAdapter {
        private Context context;
        private List<String> texts = new ArrayList<>();

        public NotesAdapter(Context context, List<Note> texts) {
            this.context = context;
            for (Note note : texts)
                this.texts.add(note.text);
        }

        public View getView(final int position, View convertView, ViewGroup parent) {
            ViewHolder viewHolder;
            if (convertView == null) {
                LayoutInflater inflater = ((Activity) context).getLayoutInflater();
                convertView = inflater.inflate(R.layout.grid_item, parent, false);

                viewHolder = new ViewHolder();
                viewHolder.text = (TextView) convertView.findViewById(R.id.card_textView);
                viewHolder.card = (CardView) convertView.findViewById(R.id.cardView);

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
