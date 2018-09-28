package com.pradeepsinha.myapplication;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Toast;

import java.util.Random;

public class GameView extends SurfaceView{
    private Bitmap bmp,bmp1,bmp2,bmp3,bmp4,bmp5,bmp6,bmp7,bmp8,bmp9,bmps,oldbmps,bmpfront;

    private SurfaceHolder holder;

    private GameLoopThread gameLoopThread;

    private int x ;
    private GestureDetector mTapDetector;
    private int xSpeed = 70 ;
 Canvas canvas;
    int answer;
    Random random;
    Paint paint;
    public GameView(Context context) {
        super(context);
        Log.d(" x####",""+ x);
        gameLoopThread = new GameLoopThread(this);

        holder = getHolder();
        mTapDetector = new GestureDetector(context,new GestureTap());
        holder.addCallback(new SurfaceHolder.Callback() {



            @Override

            public void surfaceDestroyed(SurfaceHolder holder) {

                boolean retry = true;

                gameLoopThread.setRunning(false);

                while (retry) {

                    try {

                        gameLoopThread.join();

                        retry = false;

                    } catch (InterruptedException e) {

                    }

                }

            }



            @Override

            public void surfaceCreated(SurfaceHolder holder) {

                gameLoopThread.setRunning(true);

                gameLoopThread.start();

            }



            @Override

            public void surfaceChanged(SurfaceHolder holder, int format,

                                       int width, int height) {

            }

        });

        bmp = BitmapFactory.decodeResource(getResources(), R.drawable.cardbacks);
        bmp1 = BitmapFactory.decodeResource(getResources(), R.drawable.card_1);
        bmp2 = BitmapFactory.decodeResource(getResources(), R.drawable.card_2);
        bmp3 = BitmapFactory.decodeResource(getResources(), R.drawable.card_3);
        bmp4 = BitmapFactory.decodeResource(getResources(), R.drawable.card_4);
        bmp5 = BitmapFactory.decodeResource(getResources(), R.drawable.card_5);
        bmp6 = BitmapFactory.decodeResource(getResources(), R.drawable.card_6);
        bmp7 = BitmapFactory.decodeResource(getResources(), R.drawable.card_7);
        bmp8 = BitmapFactory.decodeResource(getResources(), R.drawable.card_8);
        bmp9 = BitmapFactory.decodeResource(getResources(), R.drawable.card_9);
     //  bmps=bmp1;
       if(oldbmps==null)
           oldbmps=bmp;

       // oldbmps=bmps;
        bmpfront=bmp1;
        x=getWidth()/4;
        Log.d(" x******",""+ x);
         random=new Random();

    }


boolean animStop=false,showOldbmps;
    boolean isFront=false;
    boolean isNewImage;

int left,top,base,rotation=0;
int viewWidth,viewHeight;
/*
    @Override public void onDraw(Canvas canvas) {
        Bitmap bitmap = bmp;//addWhiteBorder(bitmapsrc, 2);
        Matrix matrix2 = new   Matrix();
        int oldw = bitmap.getWidth()/2;
        int oldh =  bitmap.getHeight()/2;
        float d_up = oldh / 20;
        float d_down = d_up + 10;
        float[] src2 = new float[] { 30, 30, oldw, 30, oldw,
                oldh, 0, oldh };
        float[] dst2 = new float[] { 100, d_up, oldw, 100,
                oldw, oldh, 100, oldh - d_down };
        matrix2.setPolyToPoly(src2, 0, dst2, 0,
                src2.length >> 1);
        Bitmap bMatrix2 = Bitmap.createBitmap(bitmap, 0, 0,
                bitmap.getWidth(), bitmap.getHeight(),
                matrix2, true);
        canvas.setBitmap(bMatrix2);

    }*/
    @Override
    protected void onDraw(Canvas canvas) {
        this.canvas=canvas;
        paint = new Paint();
//
//        if (x > bmp1.getWidth())
//      //  if (x > getWidth()/4)
//        {
//
//            xSpeed = -4;
//            animStop=true;
//
//        }
        //else
       // canvas.drawBitmap(bmp, getWidth()/4 , 48, null);
        if (x >= canvas.getWidth()) {

            xSpeed = -70;
            isFront=true;


        }

        if(x>=canvas.getWidth()/10 && x<=canvas.getWidth()/9)
            oldbmps=bmps;

        if (x <= canvas.getWidth()/10 ) {

            isFront=false;
            xSpeed = 70;
            answer = random.nextInt(8) + 1;
           // oldbmps=bmps;


        }



        if(x==0&&isNewImage)
            isNewImage=true;
        else
            isNewImage=false;

        x = x + xSpeed;

    if(!animStop)
    {
        canvas.drawColor(Color.BLACK);
        //canvas.drawRect(0,0,canvas.getWidth(), canvas.getHeight(), null);

//        if (x== 0) {
//
//            xSpeed = 8;
//
//        }
//        x = x - xSpeed;

        Log.d(" x====",""+ x);

        left=x+getWidth()/8;
        top=50;
        base=getWidth()/8;

        switch (answer)
        {
            case 1:

                bmps=bmp1;
                if(isFront)
                {
                    canvas.drawBitmap(bmp, base , top, null);
                    canvas.drawBitmap(oldbmps, base , top, null);
                    canvas.drawBitmap(bmp1, left , top, null);

                 //   canvas.drawBitmap(bmps, getWidth()/4 , 48, null);
                }
                else
                {
                    canvas.drawBitmap(bmp1, left , top, null);
                    canvas.drawBitmap(bmp, base , top, null);
                    canvas.drawBitmap(oldbmps, base , top, null);
                }
               // if(isNewImage)
               //     canvas.drawBitmap(bmps, base , top, null);

                 break;
            case 2:
             //   oldbmps=bmps;
                bmps=bmp2;
                if(isFront)
                {
                    canvas.drawBitmap(bmp, base , top, null);
                    canvas.drawBitmap(oldbmps, base , top, null);
                 //   canvas.drawBitmap(bmps, base , 48, null);
                    canvas.drawBitmap(bmp2, left , top, null);
                }
                else
                {
                    canvas.drawBitmap(bmp2, left , top, null);
                    canvas.drawBitmap(bmp, base , top, null);
                    canvas.drawBitmap(oldbmps, base , top, null);
                }
           //     if(isNewImage)
                 //   canvas.drawBitmap(bmps, base , top, null);
             //   bmps=bmp2;
                break;
            case 3:
             //   oldbmps=bmps;
                bmps=bmp3;
                if(isFront)
                {
                    canvas.drawBitmap(bmp, base , top, null);
                    canvas.drawBitmap(oldbmps, base , top, null);
                  //  canvas.drawBitmap(bmps, getWidth()/4 , 48, null);
                    canvas.drawBitmap(bmp3, left , top, null);
                }
                else
                {
                    canvas.drawBitmap(bmp3, left , top, null);
                    canvas.drawBitmap(bmp, base , top, null);
                    canvas.drawBitmap(oldbmps, base , top, null);
                }
            //    if(isNewImage)
                   // canvas.drawBitmap(bmps, base , top, null);
             //   bmps=bmp3;
                break;
            case 4:
            //    oldbmps=bmps;
                bmps=bmp4;
                if(isFront)
                {
                    canvas.drawBitmap(bmp, base , top, null);
                    canvas.drawBitmap(oldbmps, base , top, null);
                //   canvas.drawBitmap(bmps, base , 48, null);
                    canvas.drawBitmap(bmp4, left, top, null);
                }
                else
                {
                    canvas.drawBitmap(bmp4, left, top, null);
                    canvas.drawBitmap(bmp, base , top, null);
                    canvas.drawBitmap(oldbmps, base , top, null);
                }
          //      if(isNewImage)
                //    canvas.drawBitmap(bmps, base , top, null);
            //    bmps=bmp4;
                break;
            case 5:
            //    oldbmps=bmps;
                bmps=bmp5;
                if(isFront)
                {
                    canvas.drawBitmap(bmp, base , top, null);
                    canvas.drawBitmap(oldbmps, base , top, null);
                //    canvas.drawBitmap(bmps, base , 48, null);
                    canvas.drawBitmap(bmp5, left , top, null);
                }
                else
                {
                    canvas.drawBitmap(bmp5, left , top, null);
                    canvas.drawBitmap(bmp, base , top, null);
                    canvas.drawBitmap(oldbmps, base , top, null);
                }
         //       if(isNewImage)
               //     canvas.drawBitmap(bmps, base , top, null);
            //    bmps=bmp5;
                break;
            case 6:
             //   oldbmps=bmps;
                bmps=bmp6;
                if(isFront)
                {
                    canvas.drawBitmap(bmp, base , top, null);
                    canvas.drawBitmap(oldbmps, base , top, null);
                //    canvas.drawBitmap(bmps, base , 48, null);
                    canvas.drawBitmap(bmp6, left , top, null);
                }
                else
                {
                    canvas.drawBitmap(bmp6, left , top, null);
                    canvas.drawBitmap(bmp, base , top, null);
                    canvas.drawBitmap(oldbmps, base , top, null);
                }
           //     if(isNewImage)
                  //  canvas.drawBitmap(bmps, base , top, null);
            //    bmps=bmp6;
                break;
            case 7:
             //   oldbmps=bmps;
                bmps=bmp7;
                if(isFront)
                {
                    canvas.drawBitmap(bmp, base , top, null);
                    canvas.drawBitmap(oldbmps, base , top, null);
               //     canvas.drawBitmap(bmps, base , 48, null);
                    canvas.drawBitmap(bmp7, left , top, null);
                }
                else
                {
                    canvas.drawBitmap(bmp7, left , top, null);
                    canvas.drawBitmap(bmp, base , top, null);
                    canvas.drawBitmap(oldbmps, base , top, null);
                }
          //      if(isNewImage)
                  //  canvas.drawBitmap(bmps, base , top, null);
           //     bmps=bmp7;
                break;
            case 8:
          //      oldbmps=bmps;
                bmps=bmp8;
                if(isFront)
                {
                    canvas.drawBitmap(bmp, base , top, null);
                    canvas.drawBitmap(oldbmps, base , top, null);
                  //  canvas.drawBitmap(bmps, base , 48, null);
                    canvas.drawBitmap(bmp8, left , top, null);
                }
                else
                {
                    canvas.drawBitmap(bmp8, left , top, null);
                    canvas.drawBitmap(bmp, base , top, null);
                    canvas.drawBitmap(bmps, base , top, null);
                }
        //        if(isNewImage)
                 //   canvas.drawBitmap(bmps, base , top, null);
           //     bmps=bmp8;
                break;
            case 9:
            //    oldbmps=bmps;
                bmps=bmp9;
                if(isFront)
                {
                    canvas.drawBitmap(bmp, base , top, null);
                    canvas.drawBitmap(oldbmps, base , top, null);
                //    canvas.drawBitmap(bmps, base , 48, null);
                    canvas.drawBitmap(bmp9, left, top, null);
                }
                else
                {
                    canvas.drawBitmap(bmp9, left , top, null);
                    canvas.drawBitmap(bmp, base , top, null);
                    canvas.drawBitmap(oldbmps, base , top, null);
                }
        //        if(isNewImage)

          //      bmps=bmp9;
                break;

        }






    }
    else
    {
        canvas.drawColor(Color.BLACK);
       // canvas.drawRect(0,0,canvas.getWidth(), canvas.getHeight(), null);
      //  canvas.drawBitmap(bmp9, base , top, null);
        canvas.drawBitmap(bmp, base , top, null);
       // canvas.drawBitmap(bmps, base , top, null);
    }
    if(doubleTap)
    {
        canvas.drawColor(Color.BLACK);
        canvas.save();
       // canvas.rotate(20.0f);

        paint.setColor(Color.BLUE);
        paint.setStyle(Paint.Style.STROKE);
        canvas.drawBitmap(bmp, canvas.getWidth()/4, 10, paint);
        canvas.drawBitmap(oldbmps, x, 10, paint);
        canvas.restore();

    }
//    if(isFliped)
//    {
//
//        Paint paint = new Paint();
//        paint.setAntiAlias(true);
//        Matrix needleTransMatrix = new Matrix();
//        needleTransMatrix.postRotate(rotationAngle,needleImage.getWidth()/2,needleImage.getHeight());
//        needleTransMatrix.postTranslate( centerX+METER_OFFSET-needleImage.getWidth()/2, centerY-needleImage.getHeight());
//
//        Matrix dialTransMatrix = new Matrix();
//        dialTransMatrix.postRotate(rotationAngle,dialImage.getWidth()/2,dialImage.getHeight()/2);
//        dialTransMatrix.postTranslate( METER_OFFSET,0);
//
//        canvas.drawBitmap(bgImage, METER_OFFSET, 0, paint);
//        canvas.drawBitmap(dialImage, dialTransMatrix, paint);
//        canvas.drawBitmap(needleImage, needleTransMatrix, paint);
//    }
    }

    private long lastClick;
 boolean doubleTap=false;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mTapDetector.onTouchEvent(event);
        return true;
    }
    class GestureTap extends GestureDetector.SimpleOnGestureListener {
        @Override
        public boolean onDoubleTap(MotionEvent e) {
            Toast.makeText(getContext(),"onDoubleTap",Toast.LENGTH_LONG).show();
            animStop=false;
            doubleTap=false;
            return true;
        }

        @Override
        public boolean onSingleTapConfirmed(MotionEvent e) {
            Toast.makeText(getContext()," onSingleTapConfirmed",Toast.LENGTH_LONG).show();
            animStop=true;
            doubleTap=true;
            // TODO: handle tap here
            return true;
        }
    }
}