package com.marlin.cameragooglebioid;

import android.graphics.ImageFormat;
import android.media.Image;
import android.media.ImageReader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.android.cameraview.CameraView;

public class MainActivity extends AppCompatActivity {

    private CameraView mCameraView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mCameraView = findViewById(R.id.camera);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mCameraView.start();
    }

    @Override
    protected void onPause() {
        mCameraView.stop();
        super.onPause();
    }

    ImageReader imageReader;
    /**
     * lazily initialize ImageReader and select preview size
     */
    private void setupImageReader() {
        if (imageReader == null) {
            int maxImages = 2;  // should be at least 2 according to ImageReader.acquireLatestImage() documentation

            imageReader = ImageReader.newInstance(mCameraView.getWidth(), mCameraView.getHeight(), ImageFormat.YUV_420_888, maxImages);
            imageReader.setOnImageAvailableListener(reader -> {
                Image img = reader.acquireLatestImage();
                if (img != null) {

                    // Make a in memory copy of the image to close the image from the reader as soon as possible.
                    // This helps the thread running the preview staying up to date.
//                    IntensityPlane imgCopy = IntensityPlane.extract(img);
//                    img.close();
//
//                    try {
//                        int imageRotation = cameraHelper.getImageRotation(openCamera, getRelativeDisplayRotation());
//                        presenter.onImageCaptured(imgCopy, imageRotation);
//                    } catch (NullPointerException e) {
//                        // Fragment is no longer attached to Activity -> no need to process the image anymore
//                    }
                }
            }, null);
        }
    }
}
