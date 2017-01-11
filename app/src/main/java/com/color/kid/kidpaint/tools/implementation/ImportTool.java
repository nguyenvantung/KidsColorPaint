package com.color.kid.kidpaint.tools.implementation;

import android.app.Activity;

import com.color.kid.kidpaint.R;
import com.color.kid.kidpaint.tools.ToolType;
import com.color.kid.kidpaint.ui.TopBar;


public class ImportTool extends StampTool {

	public ImportTool(Activity activity, ToolType toolType) {
		super(activity, toolType);
		mStampActive = true;
		mAttributeButton2.setEnabled(false);
	}

	@Override
	public int getAttributeButtonResource(TopBar.ToolButtonIDs buttonNumber) {
		switch (buttonNumber) {
		case BUTTON_ID_PARAMETER_BOTTOM_1:
			return R.drawable.icon_menu_stamp_paste;
		case BUTTON_ID_PARAMETER_BOTTOM_2:
			return NO_BUTTON_RESOURCE;
		default:
			return super.getAttributeButtonResource(buttonNumber);
		}
	}
}
