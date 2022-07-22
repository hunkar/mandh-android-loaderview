package com.mandh.loader;

import android.content.Context;

public class LoaderDialog {
    Dialog dialog;
    Context context;

    /**
     * Constructor
     *
     * @param context Context of game.
     */
    public LoaderDialog(Context context) {
        this.context = context;
        this.dialog = new Dialog(context);
    }

    public void showLoader(){
        dialog.show();
    }

    public void hideLoader(){
        dialog.dismiss();
    }
}
