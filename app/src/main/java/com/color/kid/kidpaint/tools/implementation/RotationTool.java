package com.color.kid.kidpaint.tools.implementation;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.PointF;

import com.color.kid.kidpaint.R;
import com.color.kid.kidpaint.activity.PaintroidApplication;
import com.color.kid.kidpaint.command.Command;
import com.color.kid.kidpaint.command.implementation.RotateCommand;
import com.color.kid.kidpaint.dialog.IndeterminateProgressDialog;
import com.color.kid.kidpaint.tools.ToolType;
import com.color.kid.kidpaint.ui.TopBar;


public class RotationTool extends BaseTool {

	public RotationTool(Context context, ToolType toolType) {
		super(context, toolType);
	}

	@Override
	public boolean handleDown(PointF coordinate) {
		return false;
	}

	@Override
	public boolean handleMove(PointF coordinate) {
		return false;
	}

	@Override
	public boolean handleUp(PointF coordinate) {
		return false;
	}

	@Override
	public void resetInternalState() {

	}

	@Override
	public void draw(Canvas canvas) {

	}

	@Override
	public int getAttributeButtonResource(TopBar.ToolButtonIDs toolButtonID) {
		switch (toolButtonID) {
		case BUTTON_ID_PARAMETER_BOTTOM_1:
			return R.drawable.icon_menu_rotate_left;
		case BUTTON_ID_PARAMETER_BOTTOM_2:
			return R.drawable.icon_menu_rotate_right;
		default:
			return super.getAttributeButtonResource(toolButtonID);
		}
	}

	@Override
	public void attributeButtonClick(TopBar.ToolButtonIDs toolButtonID) {
		RotateCommand.RotateDirection rotateDirection = null;
		switch (toolButtonID) {
		case BUTTON_ID_PARAMETER_BOTTOM_1:
			rotateDirection = RotateCommand.RotateDirection.ROTATE_LEFT;
			break;
		case BUTTON_ID_PARAMETER_BOTTOM_2:
			rotateDirection = RotateCommand.RotateDirection.ROTATE_RIGHT;
			break;
		default:
			return;
		}

		Command command = new RotateCommand(rotateDirection);
		IndeterminateProgressDialog.getInstance().show();
		((RotateCommand) command).addObserver(this);
		PaintroidApplication.commandManager.commitCommand(command);
	}

	@Override
	public int getAttributeButtonColor(TopBar.ToolButtonIDs buttonNumber) {
		switch (buttonNumber) {
		case BUTTON_ID_PARAMETER_TOP:
			return Color.TRANSPARENT;
		default:
			return super.getAttributeButtonColor(buttonNumber);
		}
	}

}
