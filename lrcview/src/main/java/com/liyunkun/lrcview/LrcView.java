package com.liyunkun.lrcview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.media.MediaPlayer;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

import com.liyunkun.lrcview.Bean.LrcBean;

import java.util.List;

/**
 * Created by liyunkun on 2016/10/21 0021.
 */
public class LrcView extends View {
    private int width;
    private int height;
    private List<LrcBean> list;
    private Paint gPaint;
    private Paint pPaint;
    private MediaPlayer player;
    private int currentPosition = 0;
    private int lastPosition = 0;

    public void setList(List<LrcBean> list) {
        this.list = list;
    }

    public void setPlayer(MediaPlayer player) {
        this.player = player;
    }

    public LrcView(Context context) {
        this(context, null);
    }

    public LrcView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LrcView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        pPaint = new Paint();
        pPaint.setAntiAlias(true);
        pPaint.setColor(getResources().getColor(android.R.color.darker_gray));
        pPaint.setTextSize(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 18, getResources().getDisplayMetrics()));
        pPaint.setTextAlign(Paint.Align.CENTER);

        gPaint = new Paint();
        gPaint.setAntiAlias(true);
        gPaint.setColor(getResources().getColor(R.color.textColor));
        gPaint.setTextSize(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 18, getResources().getDisplayMetrics()));
        gPaint.setTextAlign(Paint.Align.CENTER);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (width == 0 || height == 0) {
            width = getMeasuredWidth();
            height = getMeasuredHeight();
        }
        if (list == null || list.size() == 0) {
            canvas.drawText("暂无歌词", width / 2, height / 2, pPaint);
            return;
        }
        setCurrentPosition();

//        drawText1(canvas);
        int position = player.getCurrentPosition();
        long start = list.get(currentPosition).getStart();
        float y = (position - start) > 500 ? currentPosition * 80 : lastPosition * 80 + (currentPosition - lastPosition) * 80 * (position - start) / 500F;
        setScrollY((int) y);
        if (getScrollY() == currentPosition * 80) {
            lastPosition = currentPosition;
        }
        drawText3(canvas, position);
//        drawText2(canvas);
        postInvalidateDelayed(100);
    }

    private void drawText3(Canvas canvas, int position) {
        for (int i = 0; i < list.size(); i++) {
            canvas.drawText(list.get(i).getText(),width/2,height/2+80*i,pPaint);
        }
        LrcBean lrcBean = list.get(currentPosition);
        float measureText = pPaint.measureText(lrcBean.getText());
        int bitmapWidth = (int) ((position - lrcBean.getStart()) *1.0F/ (lrcBean.getEnd() - lrcBean.getStart()) * measureText);
        if (bitmapWidth > 0) {
            Bitmap bitmap=Bitmap.createBitmap(bitmapWidth,80, Bitmap.Config.ARGB_8888);
            Canvas bitmapCanvas=new Canvas(bitmap);
            bitmapCanvas.drawText(lrcBean.getText(),measureText/2,80,gPaint);
            canvas.drawBitmap(bitmap,(width-measureText)/2,height/2+80*(currentPosition-1),null);
        }
    }

    private void drawText2(Canvas canvas) {
        for (int i = 0; i < list.size(); i++) {
            if (i == currentPosition) {
                canvas.drawText(list.get(i).getText(), width / 2, height / 2 + 80 * i, gPaint);
            } else {
                canvas.drawText(list.get(i).getText(), width / 2, height / 2 + 80 * i, pPaint);
            }
        }
    }

    private void drawText1(Canvas canvas) {
        String text = list.get(currentPosition).getText();
        canvas.drawText(text, width / 2, height / 2, gPaint);
        int dimension = (int) (TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 20, getResources().getDisplayMetrics()));
        for (int i = 0; i < 10; i++) {
            int lastPosition = currentPosition - i;
            if (lastPosition < -1) {
                return;
            }
            canvas.drawText(list.get(lastPosition).getText(), width / 2, height / 2 - (dimension * i), pPaint);
        }
        for (int i = 0; i < 10; i++) {
            int lastPosition = currentPosition + i;
            if (lastPosition > list.size()) {
                return;
            }
            canvas.drawText(list.get(lastPosition).getText(), width / 2, height / 2 + (dimension * i), pPaint);
        }
    }

    private void setCurrentPosition() {

        try {
            int position = player.getCurrentPosition();

            if (position < list.get(0).getStart()) {
                currentPosition = 0;
                return;
            }
            if (position > list.get(list.size() - 1).getStart()) {
                currentPosition = list.size() - 1;
                return;
            }

            for (int i = 0; i < list.size(); i++) {
                if (position >= list.get(i).getStart() && position < list.get(i).getEnd()) {
                    currentPosition = i;
                    return;
                }
            }

        } catch (Exception e) {
//            e.printStackTrace();
            postInvalidateDelayed(100);
        }
    }

    public void update() {
        currentPosition = 0;
        lastPosition = 0;
        setScrollY(0);
        invalidate();
    }
}
