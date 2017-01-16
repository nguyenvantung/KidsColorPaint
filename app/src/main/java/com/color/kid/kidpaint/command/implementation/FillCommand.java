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

package com.color.kid.kidpaint.command.implementation;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;

import com.color.kid.kidpaint.constan.AppConstance;
import com.color.kid.kidpaint.tools.helper.QueueLinearFloodFiller;


public class FillCommand extends BaseCommand {
	private static final int EMPTY_COMMAND_LIST_LENGTH = 1;
	private float mColorTolerance;
	private Point mClickedPixel;

	public FillCommand(Point clickedPixel, Paint currentPaint, float colorTolerance) {
		super(currentPaint);
		mClickedPixel = clickedPixel;
		mColorTolerance = colorTolerance;
	}

	@Override
	public void run(Canvas canvas, Bitmap bitmap) {

		notifyStatus(NOTIFY_STATES.COMMAND_STARTED);
		if (mClickedPixel == null) {
			setChanged();
			notifyStatus(NOTIFY_STATES.COMMAND_FAILED);
			return;
		}
		int replacementColor = AppConstance.selectColor;
		int targetColor = bitmap.getPixel(mClickedPixel.x, mClickedPixel.y);
		QueueLinearFloodFiller queueLinearFloodFiller = new QueueLinearFloodFiller(bitmap, targetColor, replacementColor);
		queueLinearFloodFiller.setTolerance(50);
		queueLinearFloodFiller.floodFill(mClickedPixel.x, mClickedPixel.y);
		notifyStatus(NOTIFY_STATES.COMMAND_DONE);
	}
}
