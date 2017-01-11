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

package com.color.kid.kidpaint.activity;

import android.animation.Animator;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Paint;
import android.graphics.Paint.Cap;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.SurfaceView;
import android.view.animation.BounceInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.color.kid.kidpaint.R;
import com.color.kid.kidpaint.adapter.Bucket;
import com.color.kid.kidpaint.adapter.BucketAdapter;
import com.color.kid.kidpaint.adapter.Pencil;
import com.color.kid.kidpaint.adapter.PencilAdapter;
import com.color.kid.kidpaint.constan.AppConstance;
import com.color.kid.kidpaint.dialog.BrushPickerDialog;
import com.color.kid.kidpaint.dialog.CustomAlertDialogBuilder;
import com.color.kid.kidpaint.dialog.FillToolDialog;
import com.color.kid.kidpaint.dialog.IndeterminateProgressDialog;
import com.color.kid.kidpaint.dialog.TextToolDialog;
import com.color.kid.kidpaint.dialog.ToolsDialog;
import com.color.kid.kidpaint.dialog.colorpicker.ColorPickerDialog;
import com.color.kid.kidpaint.listener.DrawingSurfaceListener;
import com.color.kid.kidpaint.listener.OnClickItemBush;
import com.color.kid.kidpaint.listener.OnClickItemDraw;
import com.color.kid.kidpaint.tools.Tool;
import com.color.kid.kidpaint.tools.ToolFactory;
import com.color.kid.kidpaint.tools.ToolType;
import com.color.kid.kidpaint.tools.implementation.ImportTool;
import com.color.kid.kidpaint.ui.DrawingSurface;
import com.color.kid.kidpaint.ui.Perspective;
import com.color.kid.kidpaint.util.ColoringUtility;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends OptionsActivity implements OnClickItemBush, OnClickItemDraw{

	private int[] listPencil = { R.drawable.pencil_aquamarine, R.drawable.pencil_beige, R.drawable.pencil_black, R.drawable.pencil_blue, R.drawable.pencil_bright_green,
			R.drawable.pencil_brown, R.drawable.pencil_copper, R.drawable.pencil_crimson, R.drawable.pencil_gold, R.drawable.pencil_green, R.drawable.pencil_grey,
			R.drawable.pencil_light_blue, R.drawable.pencil_magenta, R.drawable.pencil_orange, R.drawable.pencil_pink, R.drawable.pencil_purple, R.drawable.pencil_red,
			R.drawable.pencil_vailet, R.drawable.pencil_white, R.drawable.pencil_yellow};
	private int[] listBucket = {R.drawable.bucket_aquamarine, R.drawable.bucket_beige, R.drawable.bucket_black, R.drawable.bucket_blue, R.drawable.bucket_bright_green,
			R.drawable.bucket_brown, R.drawable.bucket_copper, R.drawable.bucket_crimson, R.drawable.bucket_gold, R.drawable.bucket_green, R.drawable.bucket_grey,
			R.drawable.bucket_light_blue, R.drawable.bucket_magenda, R.drawable.bucket_orange, R.drawable.bucket_pink, R.drawable.bucket_purple, R.drawable.bucket_red,
			R.drawable.bucket_violet, R.drawable.bucket_white, R.drawable.bucket_yellow};

	private List<Pencil> listPencilData = new ArrayList<>();
	private List<Bucket> listBucketData = new ArrayList<>();

	@Bind(R.id.listBucket)
	RecyclerView recyclerViewBucket;

	@Bind(R.id.listPencil)
	RecyclerView recyclerViewPencil;

	@Bind(R.id.toolBucket)
	ImageView imgBucket;

	@Bind(R.id.toolBush)
	ImageView imgBush;

	@Bind(R.id.toolEraser)
	ImageView imgEraser;

	@Bind(R.id.layoutPencilSize)
	RelativeLayout layoutPencilSize;

	@Bind(R.id.toolPencilClose)
	ImageView imgClosePencilSize;

	@Bind(R.id.pencilSize1)
	RelativeLayout pencilSize1;

	@Bind(R.id.pencilSize2)
	RelativeLayout pencilSize2;

	@Bind(R.id.pencilSize3)
	RelativeLayout pencilSize3;

	@Bind(R.id.pencilSize4)
	RelativeLayout pencilSize4;

	@Bind(R.id.pencilSize5)
	RelativeLayout pencilSize5;


	protected DrawingSurfaceListener mDrawingSurfaceListener;
	protected boolean mToolbarIsVisible = true;
	private int drawableData;
	private PencilAdapter pencilAdapter;
	private BucketAdapter bucketAdapter;
	private Tool toolOption;

	@Override
	public void onCreate(Bundle savedInstanceState) {

		ColorPickerDialog.init(this);
		BrushPickerDialog.init(this);
		ToolsDialog.init(this);
		IndeterminateProgressDialog.init(this);
		TextToolDialog.init(this);
		FillToolDialog.init(this);

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_color);
		ButterKnife.bind(this);
		PaintroidApplication.catroidPicturePath = null;
		String catroidPicturePath = null;
		Bundle extras = getIntent().getExtras();
		if (extras != null) {
			catroidPicturePath = extras.getString(getString(R.string.extra_picture_path_catroid));
			drawableData = extras.getInt(AppConstance.KEY_DRAWABLE);

		}
		if (catroidPicturePath != null) {
			PaintroidApplication.openedFromCatroid = true;
			if (!catroidPicturePath.equals("")) {
				PaintroidApplication.catroidPicturePath = catroidPicturePath;
				PaintroidApplication.scaleImage = false;
			}
		} else {
			PaintroidApplication.openedFromCatroid = false;
		}

		PaintroidApplication.drawingSurface = (DrawingSurface) findViewById(R.id.drawingSurfaceView);
		PaintroidApplication.perspective = new Perspective(((SurfaceView) PaintroidApplication.drawingSurface).getHolder());
		mDrawingSurfaceListener = new DrawingSurfaceListener();

		PaintroidApplication.drawingSurface.setOnTouchListener(mDrawingSurfaceListener);
		// draw rect
		initialiseNewBitmap(getDrawable(drawableData));
		setDataFooter();
		imgBush.setSelected(true);

	}

	@Override
	protected void onResume() {
		super.onResume();
	}


	@Override
	public void onDetachedFromWindow() {
		IndeterminateProgressDialog.getInstance().dismiss();
		super.onDetachedFromWindow();
	}

	@Override
	protected void onDestroy() {
		PaintroidApplication.commandManager.resetAndClear();
		PaintroidApplication.drawingSurface.recycleBitmap();
		ColorPickerDialog.getInstance().setInitialColor(
				getResources().getColor(R.color.color_chooser_black));
		PaintroidApplication.currentTool.changePaintStrokeCap(Cap.ROUND);
		PaintroidApplication.currentTool.changePaintStrokeWidth(25);
		PaintroidApplication.isPlainImage = true;
		PaintroidApplication.savedPictureUri = null;
		PaintroidApplication.saveCopy = false;

		ToolsDialog.getInstance().dismiss();
		IndeterminateProgressDialog.getInstance().dismiss();
		ColorPickerDialog.getInstance().dismiss();
		super.onDestroy();
	}


	@Override
	public void onBackPressed() {
		if (!mToolbarIsVisible) {
			//setFullScreen(false);

		} else if (PaintroidApplication.currentTool.getToolType() == ToolType.BRUSH) {
			showSecurityQuestionBeforeExit();
		} else {
			switchTool(ToolType.BRUSH);
		}
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (resultCode != Activity.RESULT_OK) {
			Log.d(PaintroidApplication.TAG,
					"onActivityResult: result not ok, most likely a dialog hast been canceled");
			return;
		}

		switch (requestCode) {
		case REQUEST_CODE_IMPORTPNG:
			Uri selectedGalleryImageUri = data.getData();
			Tool tool = ToolFactory.createTool(this, ToolType.IMPORTPNG);
			switchTool(tool);

			loadBitmapFromUriAndRun(selectedGalleryImageUri,new RunnableWithBitmap() {
						@Override
						public void run(Bitmap bitmap) {
							if (PaintroidApplication.currentTool instanceof ImportTool) {
								((ImportTool) PaintroidApplication.currentTool)	.setBitmapFromFile(bitmap);

							} else {
								Log.e(PaintroidApplication.TAG,
										"importPngToFloatingBox: Current tool is no ImportTool as required");
							}
						}
					});

			break;
		case REQUEST_CODE_FINISH:
			finish();
			break;
		default:
			super.onActivityResult(requestCode, resultCode, data);
		}
	}

	private void importPng() {
		Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
		intent.setType("image/*");
		intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
		startActivityForResult(intent, REQUEST_CODE_IMPORTPNG);
	}

	public synchronized void switchTool(ToolType changeToToolType) {

		switch (changeToToolType) {
		case REDO:
			PaintroidApplication.commandManager.redo();
			break;
		case UNDO:
			PaintroidApplication.commandManager.undo();
			break;
		case IMPORTPNG:
			importPng();
			break;
		default:
			Tool tool = ToolFactory.createTool(this, changeToToolType);
			switchTool(tool);
			break;
		}

	}

	public synchronized void switchTool(Tool tool) {
		Paint tempPaint = new Paint(
				PaintroidApplication.currentTool.getDrawPaint());
		if (tool != null) {
			PaintroidApplication.currentTool = tool;
			PaintroidApplication.currentTool.setDrawPaint(tempPaint);
		}
	}

	private void showSecurityQuestionBeforeExit() {
		if (PaintroidApplication.isSaved
				|| !PaintroidApplication.commandManager.hasCommands()
				&& PaintroidApplication.isPlainImage) {
			finish();
			return;
		} else {
			AlertDialog.Builder builder = new CustomAlertDialogBuilder(this);
			if (PaintroidApplication.openedFromCatroid) {
				builder.setTitle(R.string.closing_catroid_security_question_title);
				builder.setMessage(R.string.closing_security_question);
				builder.setPositiveButton(R.string.save_button_text,
						new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog, int id) {
								exitToCatroid();
							}
						});
				builder.setNegativeButton(R.string.discard_button_text,
						new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog, int id) {
								finish();
							}
						});
			} else {
				builder.setTitle(R.string.closing_security_question_title);
				builder.setMessage(R.string.closing_security_question);
				builder.setPositiveButton(R.string.save_button_text,
						new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog, int id) {
								saveFileBeforeExit();
								finish();
							}
						});
				builder.setNegativeButton(R.string.discard_button_text,
						new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog, int id) {
								finish();
							}
						});
			}
			builder.setCancelable(true);
			AlertDialog alert = builder.create();
			alert.show();
		}
	}

	private void saveFileBeforeExit() {
		saveFile();
	}

	private void exitToCatroid() {
		String pictureFileName = getString(R.string.temp_picture_name);

		if (PaintroidApplication.catroidPicturePath != null) {
			pictureFileName = PaintroidApplication.catroidPicturePath;
		} else {
			Bundle extras = getIntent().getExtras();
			if (extras != null) {
				String catroidPictureName = extras
						.getString(getString(R.string.extra_picture_name_catroid));
				if (catroidPictureName != null
						&& catroidPictureName.length() > 0) {
					pictureFileName = catroidPictureName;
				}
			}
			pictureFileName = FileIO.createNewEmptyPictureFile(this,
					pictureFileName).getAbsolutePath();
		}

		Intent resultIntent = new Intent();

		if (FileIO.saveBitmap(MainActivity.this,
				PaintroidApplication.drawingSurface.getBitmapCopy(),
				pictureFileName)) {
			Bundle bundle = new Bundle();
			bundle.putString(getString(R.string.extra_picture_path_catroid),
					pictureFileName);
			resultIntent.putExtras(bundle);
			setResult(RESULT_OK, resultIntent);
		} else {
			setResult(RESULT_CANCELED, resultIntent);
		}
		finish();
	}

	public void setDataFooter(){
		recyclerViewBucket.setLayoutManager( new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL, false));
		recyclerViewPencil.setLayoutManager( new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL, false));
		initTool();
		pencilAdapter = new PencilAdapter(listPencilData);
		pencilAdapter.setOnClickItemDraw(this);
		recyclerViewPencil.setAdapter(pencilAdapter);
		//
		bucketAdapter = new BucketAdapter(listBucketData);
		bucketAdapter.setOnClickItemDraw(this);
		recyclerViewBucket.setAdapter(bucketAdapter);
	}

	public void initTool() {
		for (int i = 0; i < listPencil.length; i++) {
			Pencil pencil = new Pencil();
			pencil.drawable = listPencil[i];
			pencil.color = ColoringUtility.COLORS_MAPS.get(i);
			pencil.select = false;
			pencil.position = i;
			listPencilData.add(pencil);
		}

		for (int i = 0; i < listBucket.length; i++) {
			Bucket bucket = new Bucket();
			bucket.color = ColoringUtility.COLORS_MAPS.get(i);
			bucket.drawable = listPencil[i];
			listBucketData.add(bucket);

		}
	}


	@Override
	public void selectItemBush(Pencil pencil) {
		pencilAdapter.notifyDataSetChanged();
		setDataSelect(pencil);
	}

	@Override
	public void onclickItemDraw(int color, boolean pencil) {

	}

	public void setDataSelect(Pencil pencil){
		for (int i = 0; i < listPencilData.size(); i++){
			if (pencil.position != listPencilData.get(i).position){
				listPencilData.get(i).select = false;
			}else {
				listPencilData.get(i).select = true;
			}
		}
	}

	@OnClick(R.id.toolBucket)
	void onClickBucket(){
		if (!imgBucket.isSelected()){
			imgBucket.setSelected(true);
			imgBush.setSelected(false);
		}

		toolOption = ToolFactory.createTool(this, ToolType.FILL);
		switchTool(toolOption);

	}

	@OnClick(R.id.toolBush)
	void onClickBush(){
		if (!imgBush.isSelected()){
			imgBush.setSelected(true);
			imgBucket.setSelected(false);
		}
		setVisiblePencilSize();
		toolOption = ToolFactory.createTool(this, ToolType.BRUSH);
		switchTool(toolOption);
	}

	@OnClick(R.id.toolEraser)
	void onClickEraser(){
		if (!imgEraser.isSelected()){
			imgEraser.setSelected(true);
			imgBucket.setSelected(false);
			imgBush.setSelected(false);
		}
		setVisiblePencilSize();
		toolOption = ToolFactory.createTool(this, ToolType.ERASER);
		switchTool(toolOption);
	}

	@OnClick(R.id.toolPencilClose)
	void onClickClosePencilSize(){
		setVisiblePencilSize();
	}

	@OnClick(R.id.toolDelete)
	void onClickDelete(){

	}

	@OnClick(R.id.toolZoom)
	void onClickShare(){
		toolOption = ToolFactory.createTool(this, ToolType.ZOOM);
		switchTool(toolOption);
	}

	@OnClick(R.id.toolDone)
	void onClickDone(){

	}

	public void setVisiblePencilSize(){
		if (((RelativeLayout.LayoutParams) layoutPencilSize.getLayoutParams()).topMargin <= 0) {
			layoutPencilSize.animate().translationY((float)getResources()
					.getDimensionPixelSize(R.dimen.main_pencil_margin_top))
					.setDuration(300).setInterpolator(new BounceInterpolator())
					.setListener(new AnimationTop()).start();
		} else {
			layoutPencilSize.animate().translationY((float) getResources()
					.getDimensionPixelSize(R.dimen.main_pencil_margin_top) * -1)
					.setDuration(300).setInterpolator(new BounceInterpolator())
					.setListener(new AnimationBotton()).start();
		}
	}

	class AnimationTop implements Animator.AnimatorListener {
		AnimationTop() {
		}

		public void onAnimationStart(Animator animation) {
		}

		public void onAnimationEnd(Animator animation) {
			RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) layoutPencilSize.getLayoutParams();
			params.topMargin = getResources().getDimensionPixelSize(R.dimen.main_pencil_margin_top);
			layoutPencilSize.setTranslationY(0.0f);
			layoutPencilSize.setLayoutParams(params);
		}

		public void onAnimationCancel(Animator animation) {
		}

		public void onAnimationRepeat(Animator animation) {
		}
	}

	/* renamed from: com.coloring.book.animals.fragment.MainFragment.8.2 */
	class AnimationBotton implements Animator.AnimatorListener {
		AnimationBotton() {
		}

		public void onAnimationStart(Animator animation) {
		}

		public void onAnimationEnd(Animator animation) {
			RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) layoutPencilSize.getLayoutParams();
			params.topMargin = 0;
			layoutPencilSize.setTranslationY(0.0f);
			layoutPencilSize.setLayoutParams(params);
		}

		public void onAnimationCancel(Animator animation) {
		}

		public void onAnimationRepeat(Animator animation) {
		}
	}

	@OnClick(R.id.pencilSize1)
	void selectSize1(){
		setSelcectPencilSize(1);
	}

	@OnClick(R.id.pencilSize2)
	void selectSize2(){
		setSelcectPencilSize(2);
	}

	@OnClick(R.id.pencilSize3)
	void selectSize3(){
		setSelcectPencilSize(3);
	}

	@OnClick(R.id.pencilSize4)
	void selectSize4(){
		setSelcectPencilSize(4);
	}

	@OnClick(R.id.pencilSize5)
	void selectSize5(){
		setSelcectPencilSize(5);
	}

	public void setSelcectPencilSize(int size){
		pencilSize1.setBackgroundResource(0);
		pencilSize2.setBackgroundResource(0);
		pencilSize3.setBackgroundResource(0);
		pencilSize4.setBackgroundResource(0);
		pencilSize5.setBackgroundResource(0);
		switch (size){
			case 1:
				pencilSize1.setBackgroundResource(R.drawable.background_pencil_size_select);
				break;
			case 2:
				pencilSize2.setBackgroundResource(R.drawable.background_pencil_size_select);
				break;
			case 3:
				pencilSize3.setBackgroundResource(R.drawable.background_pencil_size_select);
				break;
			case 4:
				pencilSize4.setBackgroundResource(R.drawable.background_pencil_size_select);
				break;
			case 5:
				pencilSize5.setBackgroundResource(R.drawable.background_pencil_size_select);
				break;
		}
	}
}
