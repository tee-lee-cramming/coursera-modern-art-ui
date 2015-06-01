package tee_lee_cramming.modernui;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

/**
 * Created by tlee on 01/06/15.
 */
public class InfoDialogFragment extends DialogFragment{
    static private final String URL = "http://www.moma.org";
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return new AlertDialog.Builder(getActivity())
                .setMessage(R.string.info_dialog_message)
                .setCancelable(true)
                .setPositiveButton(R.string.info_dialog_ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(URL));
                        startActivity(intent);
                    }
                })
                .setNegativeButton(R.string.info_dialog_cancel, null)
                .create();
    }
}
