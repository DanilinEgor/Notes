package ru.danegor.notes;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.activeandroid.query.Select;

/**
 * Created by Egor Danilin on 13.01.2015.
 */
public class EditorFragment extends Fragment {
    Note mNote;
    boolean mSaved = true;
    EditText mEditText;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.editor_fragment, container, false);
        ((MainActivity)getActivity()).showSaveButton();
        if (getArguments() != null) {
            long id = getArguments().getLong("id");
            mNote = (Note) new Select().from(Note.class).where("id=?", id).execute().get(0);
        } else {
            mNote = new Note();
        }

//        getActivity().findViewById(R.id.note_title).setVisibility(View.VISIBLE);
//        final TextView noteTitleTextView = (TextView) getActivity().findViewById(R.id.note_title_tv);
//        final EditText noteTitleEditText = (EditText) getActivity().findViewById(R.id.note_title_et);
//        noteTitleTextView.setVisibility(View.VISIBLE);
//        noteTitleTextView.setText(mNote.name);
//        noteTitleTextView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                noteTitleTextView.setVisibility(View.INVISIBLE);
//                noteTitleEditText.setVisibility(View.VISIBLE);
//                noteTitleEditText.requestFocus();
//            }
//        });

        mEditText = ((EditText) view.findViewById(R.id.edit_text));
        mEditText.setText(mNote.text);
        mEditText.requestFocus();
        mEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                mSaved = false;
            }
        });

        return view;
    }

    public void onBackPressed() {
        if (!mSaved) {
            new AlertDialog.Builder(getActivity())
                    .setMessage(R.string.save_changes)
                    .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            mNote.text = mEditText.getText().toString();
                            mNote.save();
                            dialog.dismiss();
                            ((MainActivity) getActivity()).pressBack();
                        }
                    })
                    .setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            ((MainActivity) getActivity()).pressBack();
                        }
                    })
                    .show();
        } else {
            ((MainActivity) getActivity()).pressBack();
        }
    }

    public void save() {
        mNote.text = mEditText.getText().toString();
        mNote.save();
        mSaved = true;
        Toast.makeText(getActivity(), "Note saved", Toast.LENGTH_SHORT).show();
    }

    public void offerSave() {
        if (!mSaved) {
            new AlertDialog.Builder(getActivity())
                    .setMessage(R.string.save_changes)
                    .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            mNote.text = mEditText.getText().toString();
                            mNote.save();
                            dialog.dismiss();
                            ((MainActivity) getActivity()).pressBack();
                        }
                    })
                    .setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            ((MainActivity) getActivity()).pressBack();
                        }
                    })
                    .show();
        } else {
            ((MainActivity) getActivity()).pressBack();
        }
    }
}