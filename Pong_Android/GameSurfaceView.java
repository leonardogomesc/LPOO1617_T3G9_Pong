package com.example.up201503708.pong;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.view.Display;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.WindowManager;

/**
 * Created by Leonardo on 04-05-2017.
 */

public class GameSurfaceView extends SurfaceView implements SurfaceHolder.Callback{

    private LoopThread loopThread;
    private RectPlayer player;
    private Point playerPoint;
    private Game game;

    public GameSurfaceView(Context context){
        super(context);

        getHolder().addCallback(this);
        setFocusable(true);

       /* player=new RectPlayer(new Rect(100,100,200,200), Color.rgb(0,0,255));
        playerPoint=new Point(150,150);
        */
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.x;
        int height = size.y;

       game=new Game(0,0,width,height);
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height){


    }

    @Override
    public void surfaceCreated(SurfaceHolder holder){
        loopThread= new LoopThread(getHolder(),this);

        loopThread.setRunning(true);
        loopThread.start();
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder){
       boolean retry=true;
        System.out.println("destroyed");

        while(retry){
            retry=false;
            try {
                loopThread.setRunning(false);
                loopThread.join();
            }catch (Exception e){
               e.getStackTrace();
                retry=true;
            }
        }
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(game.getPlaying()==false){
            game.reset();
            game.setPlaying(true);
        }
        if(loopThread.notUpdating) {
        //  game.getP1().setPos_y(event.getY());
            game.targetPos=(int)event.getY();
        }
        return true;
        //  return super.onTouchEvent(event);
    }

    public void update(){
        game.update();
    }

    @Override
    public void draw(Canvas canvas){
        super.draw(canvas);
        canvas.drawColor(Color.rgb(255,255,255));

        Paint paint=new Paint();
        paint.setColor(Color.BLACK);

        canvas.drawCircle((float)game.getBall().getPosition()[0],(float)game.getBall().getPosition()[1],game.ballSize/2,paint);

        canvas.drawRect(new Rect((int)(game.getP1().getPos()[0]-game.paddleSize[0]/2),
                (int)(game.getP1().getPos()[1]-game.paddleSize[1]/2),
                (int)(game.getP1().getPos()[0]+game.paddleSize[0]/2),
                (int)(game.getP1().getPos()[1]+game.paddleSize[1]/2)),paint);

        canvas.drawRect(new Rect((int)(game.getP2().getPos()[0]-game.paddleSize[0]/2),
                (int)(game.getP2().getPos()[1]-game.paddleSize[1]/2),
                (int)(game.getP2().getPos()[0]+game.paddleSize[0]/2),
                (int)(game.getP2().getPos()[1]+game.paddleSize[1]/2)),paint);

        if(game.getObstacles()!=null) {
            for (int i = 0; i < game.getObstacles().length; i++) {

                canvas.drawRect(new Rect((int) (game.getObstacles()[i].getPos()[0] - game.getObstacles()[i].getSize()[0] / 2),
                        (int) (game.getObstacles()[i].getPos()[1] - game.getObstacles()[i].getSize()[1] / 2),
                        (int) (game.getObstacles()[i].getPos()[0] + game.getObstacles()[i].getSize()[0] / 2),
                        (int) (game.getObstacles()[i].getPos()[1] + game.getObstacles()[i].getSize()[1] / 2)), paint);
            }
        }
    }
}
