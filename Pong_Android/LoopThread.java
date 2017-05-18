package com.example.up201503708.pong;

import android.graphics.Canvas;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Created by Leonardo on 04-05-2017.
 */

public class LoopThread extends Thread{

    public static final int MAX_FPS=60;
    private double averageFps;
    private boolean running;
    private SurfaceHolder surfaceHolder;
    private GameSurfaceView gameSurfaceView;
    public static Canvas canvas;
    public boolean notUpdating;


    public LoopThread(SurfaceHolder s, GameSurfaceView g){
        super();

        this.surfaceHolder=s;
        this.gameSurfaceView=g;
        running=false;
        notUpdating=true;
    }

    public void setRunning(boolean value){
        running=value;
    }

    @Override
    public void run() {
        long startTime;
        long frameDurationMillis;
        long waitTime;
        int frameCounter=0;
        long totalTime=0;
        long targetTime=1000/MAX_FPS;

        while(running){
            startTime=System.nanoTime();
            canvas=null;

            try{
                canvas=surfaceHolder.lockCanvas();

                synchronized(surfaceHolder){
                   // notUpdating=false;
                    gameSurfaceView.update();
                   // notUpdating=true;
                    gameSurfaceView.draw(canvas);
                }
            }catch(Exception e){
                e.printStackTrace();
            }
            finally {
                if (canvas != null){
                    try{
                        surfaceHolder.unlockCanvasAndPost(canvas);
                    }catch(Exception e){
                        e.printStackTrace();
                    }
                }
            }

            frameDurationMillis=(System.nanoTime()-startTime)/1000000;
            waitTime=targetTime-frameDurationMillis;

            try{

                if(waitTime>0){
                    this.sleep(waitTime);
                }
            }catch(Exception e){ e.printStackTrace();}

            totalTime=totalTime+(System.nanoTime()-startTime);
            frameCounter++;

            if(frameCounter==MAX_FPS){
                averageFps=frameCounter/(totalTime/1000000000);
                frameCounter=0;
                totalTime=0;
                System.out.println(averageFps);
            }

        }

    }
}
