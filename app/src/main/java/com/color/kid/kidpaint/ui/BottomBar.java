package com.color.kid.kidpaint.ui;

import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;

import com.color.kid.kidpaint.R;
import com.color.kid.kidpaint.activity.MainActivity;
import com.color.kid.kidpaint.activity.PaintroidApplication;
import com.color.kid.kidpaint.dialog.ToolsDialog;
import com.color.kid.kidpaint.tools.Tool;


public class BottomBar implements View.OnTouchListener {
	private ImageButton mAttributeButton1;
	private ImageButton mAttributeButton2;
	private ImageButton mToolMenuButton;
	private MainActivity mMainActivity;

	public BottomBar(MainActivity mainActivity) {
		mMainActivity = mainActivity;

		mAttributeButton1 = (ImageButton) mainActivity
				.findViewById(R.id.btn_bottom_attribute1);
		mAttributeButton1.setOnTouchListener(this);

		mAttributeButton2 = (ImageButton) mainActivity
				.findViewById(R.id.btn_bottom_attribute2);
		mAttributeButton2.setOnTouchListener(this);

		mToolMenuButton = (ImageButton) mainActivity
				.findViewById(R.id.btn_bottom_tools);
		mToolMenuButton.setOnTouchListener(this);
	}

	@Override
	public boolean onTouch(View view, MotionEvent motionEvent) {
		if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
			view.setBackgroundResource(R.color.transparent);
			switch (view.getId()) {
			case R.id.btn_bottom_attribute1:
				if (PaintroidApplication.currentTool != null) {
					PaintroidApplication.currentTool
							.attributeButtonClick(TopBar.ToolButtonIDs.BUTTON_ID_PARAMETER_BOTTOM_1);
				}
				return true;
			case R.id.btn_bottom_attribute2:
				if (PaintroidApplication.currentTool != null) {
					PaintroidApplication.currentTool
							.attributeButtonClick(TopBar.ToolButtonIDs.BUTTON_ID_PARAMETER_BOTTOM_2);
				}
				return true;
			case R.id.btn_bottom_tools:
				ToolsDialog.getInstance().show();
				return true;
			default:
				return false;
			}
		} else if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
			view.setBackgroundResource(R.color.holo_blue_bright);
		}
		return false;
	}

	public void setTool(Tool tool) {
		mAttributeButton1.setEnabled(true);
		mAttributeButton1
				.setImageResource(tool
						.getAttributeButtonResource(TopBar.ToolButtonIDs.BUTTON_ID_PARAMETER_BOTTOM_1));

		mAttributeButton2.setEnabled(true);
		mAttributeButton2
				.setImageResource(tool
						.getAttributeButtonResource(TopBar.ToolButtonIDs.BUTTON_ID_PARAMETER_BOTTOM_2));

	}

	// public void setAttributeButton1Drawable(int resId) {
	// mAttributeButton1.setImageResource(resId);
	// }
	//
	// public void setAttributeButton2Drawable(int resId) {
	// mAttributeButton2.setImageResource(resId);
	// }
	//
	// public void setAttributeButton1Enabled(boolean enabled) {
	// mAttributeButton1.setEnabled(enabled);
	// }
	//
	// public void setAttributeButton2Enabled(boolean enabled) {
	// mAttributeButton2.setEnabled(enabled);
	// }
}
