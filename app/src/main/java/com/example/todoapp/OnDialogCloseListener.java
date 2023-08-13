package com.example.todoapp;

import android.content.DialogInterface;

// Interface to handle the close event of a dialog
public interface OnDialogCloseListener {

    // Method to be implemented by the hosting class to handle dialog close event
    void onDialogClose(DialogInterface dialogInterface);
}
