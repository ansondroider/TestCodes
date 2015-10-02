package testcodes.assets;

import android.app.Activity;
import android.graphics.SurfaceTexture;
import android.media.MediaPlayer;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.TextureView;
import android.view.Window;

import com.ansondroid.testcodes.R;

import java.io.IOException;

/**
 * Created by anson on 15-9-26.
 * for assets test
 */
public class AssetsTest {
    public static void playAssetsVideo(Activity a){
        a.requestWindowFeature(Window.FEATURE_NO_TITLE);
        a.setContentView(R.layout.activity_assets);
        final TextureView sv = (TextureView)a.findViewById(R.id.sv);

        final MediaPlayer mediaplayer = MediaPlayer.create(a, R.layout.activity_assets);//raw.foxplay_splash);
        mediaplayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                //complete
                mp.stop();
                //mp.release();
            }
        });

        sv.setSurfaceTextureListener(new TextureView.SurfaceTextureListener() {
            @Override
            public void onSurfaceTextureAvailable(SurfaceTexture surface, int width, int height) {

                mediaplayer.setSurface(new Surface(surface));
                mediaplayer.start();
            }

            @Override
            public void onSurfaceTextureSizeChanged(SurfaceTexture surface, int width, int height) {

            }

            @Override
            public boolean onSurfaceTextureDestroyed(SurfaceTexture surface) {
                if(mediaplayer.isPlaying()) {
                    mediaplayer.stop();
                    mediaplayer.release();
                }
                return false;
            }

            @Override
            public void onSurfaceTextureUpdated(SurfaceTexture surface) {

            }
        });

        /*sv.getHolder().addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                mediaplayer.setDisplay(sv.getHolder());
                try {
                    ///mediaplayer.prepare();
                    mediaplayer.start();

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

            }

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {
                if(mediaplayer.isPlaying()) {
                    mediaplayer.stop();
                    mediaplayer.release();
                }
            }
        });*/

    }
}
