package com.example.nick.orgsmobile;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class StudentUpdateConfirmationDialog extends AppCompatDialogFragment {

    private TextView confirmationText;
    private EditText confirmationInput;
    private ConfirmationDialogListener listener;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState){
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_box_student_update_confirmation, null);

        builder.setView(view)
                .setTitle("Confirmation")
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dismiss();
                    }
                })
                .setPositiveButton(R.string.save_profile, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String requiredText = confirmationText.getText().toString();
                        String requiredInput = confirmationInput.getText().toString();
                        listener.checkRequired(requiredText, requiredInput);
                    }
                });

        confirmationText = view.findViewById(R.id.confirmation_text);
        confirmationInput = view.findViewById(R.id.confirmation_input);
        return builder.create();
    }

    @Override
    public void onAttach(Context context){
        super.onAttach(context);

        try {
            listener = (ConfirmationDialogListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() +
                    "Must implement ConfirmationDialogListener");
        }
    }

    public interface ConfirmationDialogListener{
        void checkRequired(String requiredText, String requiredInput);
    }
}
