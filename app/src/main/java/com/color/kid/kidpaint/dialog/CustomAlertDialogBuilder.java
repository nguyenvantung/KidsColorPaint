package com.color.kid.kidpaint.dialog;

import android.app.AlertDialog;
import android.content.Context;
import android.view.ContextThemeWrapper;

import com.color.kid.kidpaint.R;


public class CustomAlertDialogBuilder extends AlertDialog.Builder {
	public CustomAlertDialogBuilder(Context context) {
		super(new ContextThemeWrapper(context, R.style.CustomPaintroidDialog));
	}
}
