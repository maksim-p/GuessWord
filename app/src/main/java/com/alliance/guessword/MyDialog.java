package com.alliance.guessword;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;


public class MyDialog extends DialogFragment {
    View dialogView;
    EditText editTeam;
    TextView teamName;
    ImageView changeName;

    int viewId;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Bundle bundle = getArguments();
        viewId = getArguments().getInt("ID");

        teamName = getActivity().findViewById(viewId);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        dialogView = inflater.inflate(R.layout.dialog, null);

        changeName = dialogView.findViewById(R.id.changeName);

        changeName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editTeam.clearComposingText();
                teamName.setText(editTeam.getText());
                onDestroyView();
            }
        });

        editTeam = dialogView.findViewById(R.id.editTeam);
        editTeam.setText(teamName.getText());
        editTeam.setSelection(editTeam.getText().length());

        builder.setView(dialogView);
        Dialog dialog = builder.create();
        dialog.setCanceledOnTouchOutside(false);
        return dialog;
    }
}
