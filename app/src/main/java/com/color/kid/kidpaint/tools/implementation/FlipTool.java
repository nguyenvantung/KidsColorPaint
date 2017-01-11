/**
 *  Paintroid: An image manipulation application for Android.
 *  Copyright (C) 2010-2015 The Catrobat Team
 *  (<http://developer.catrobat.org/credits>)
 *
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU Affero General Public License as
 *  published by the Free Software Foundation, either version 3 of the
 *  License, or (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 *  GNU Affero General Public License for more details.
 *
 *  You should have received a copy of the GNU Affero General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.color.kid.kidpaint.tools.implementation;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.PointF;

import com.color.kid.kidpaint.R;
import com.color.kid.kidpaint.activity.PaintroidApplication;
import com.color.kid.kidpaint.command.Command;
import com.color.kid.kidpaint.command.implementation.FlipCommand;
import com.color.kid.kidpaint.dialog.IndeterminateProgressDialog;
import com.color.kid.kidpaint.tools.ToolType;
import com.color.kid.kidpaint.ui.TopBar;


public class FlipTool extends BaseTool {

	public FlipTool(Context context, ToolType toolType) {
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
	public int getAttributeButtonResource(TopBar.ToolButtonIDs toolButtonID) {
		switch (toolButtonID) {
		case BUTTON_ID_PARAMETER_BOTTOM_1:
			return R.drawable.icon_menu_flip_horizontal;
		case BUTTON_ID_PARAMETER_BOTTOM_2:
			return R.drawable.icon_menu_flip_vertical;
		default:
			return super.getAttributeButtonResource(toolButtonID);
		}
	}

	@Override
	public void attributeButtonClick(TopBar.ToolButtonIDs toolButtonID) {
		FlipCommand.FlipDirection flipDirection = null;
		switch (toolButtonID) {
		case BUTTON_ID_PARAMETER_BOTTOM_1:
			flipDirection = FlipCommand.FlipDirection.FLIP_HORIZONTAL;
			break;
		case BUTTON_ID_PARAMETER_BOTTOM_2:
			flipDirection = FlipCommand.FlipDirection.FLIP_VERTICAL;
			break;
		default:
			return;
		}

		Command command = new FlipCommand(flipDirection);
		IndeterminateProgressDialog.getInstance().show();
		((FlipCommand) command).addObserver(this);
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

	@Override
	public void draw(Canvas canvas) {
	}

}
