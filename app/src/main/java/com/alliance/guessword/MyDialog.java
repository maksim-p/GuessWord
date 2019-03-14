package com.alliance.guessword;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;


public class MyDialog extends DialogFragment {
    View dialogView;
    EditText editTeam;
    TextView teamName;

    int viewId;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Bundle bundle = getArguments();
        viewId = getArguments().getInt("ID");

        teamName = getActivity().findViewById(viewId);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        dialogView = inflater.inflate(R.layout.dialog, null);

        editTeam = dialogView.findViewById(R.id.editTeam);
        editTeam.setText(teamName.getText());

        builder.setView(dialogView);
        Dialog dialog = builder.create();

        dialog.setCanceledOnTouchOutside(true);
        return dialog;
    }

    @Override
    public void onCancel(DialogInterface dialog) {
        super.onCancel(dialog);
        teamName.setText(editTeam.getText());
    }
}
