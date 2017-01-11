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
import android.graphics.PointF;

import com.color.kid.kidpaint.activity.PaintroidApplication;
import com.color.kid.kidpaint.dialog.colorpicker.ColorPickerDialog;
import com.color.kid.kidpaint.tools.ToolType;
import com.color.kid.kidpaint.ui.TopBar;


public class PipetteTool extends BaseTool {

	public PipetteTool(Context context, ToolType toolType) {
		super(context, toolType);
	}

	@Override
	public void draw(Canvas canvas) {
	}

	@Override
	public boolean handleDown(PointF coordinate) {
		return setColor(coordinate);
	}

	@Override
	public boolean handleMove(PointF coordinate) {
		return setColor(coordinate);
	}

	@Override
	public boolean handleUp(PointF coordinate) {
		return setColor(coordinate);
	}

	protected boolean setColor(PointF coordinate) {
		if (coordinate == null) {
			return false;
		}
		int color = PaintroidApplication.drawingSurface.getPixel(coordinate);
		ColorPickerDialog.getInstance().setInitialColor(color);
		changePaintColor(color);
		return true;
	}

	@Override
	public int getAttributeButtonResource(TopBar.ToolButtonIDs buttonNumber) {

		switch (buttonNumber) {
		case BUTTON_ID_PARAMETER_TOP:
			return getStrokeColorResource();
		default:
			return super.getAttributeButtonResource(buttonNumber);
		}
	}

	@Override
	public int getAttributeButtonColor(TopBar.ToolButtonIDs buttonNumber) {

		return super.getAttributeButtonColor(buttonNumber);

	}

	@Override
	public void resetInternalState() {
	}

	@Override
	public void attributeButtonClick(TopBar.ToolButtonIDs buttonNumber) {
		// no clicks allowed
	}

}
