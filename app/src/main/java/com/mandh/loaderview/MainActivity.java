package com.mandh.loaderview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

import com.mandh.loader.LoaderDialog;
import com.mandh.loader.LoaderView;
import com.mandh.loader.RotationDirection;

public class MainActivity extends AppCompatActivity {
    private LoaderView loaderView, xmlLoaderView;
    private LoaderDialog loaderDialog;
    private Button startSpinning, startSpinningDialog;
    private EditText opacity, backgroundOpacity, loaderHeight, loaderWidth, backgroundColor;
    private RadioButton secondImage, clockwise, fromXmlLoaderView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        defineElements();
        setListeners();
    }

    private void defineElements() {
        loaderView = new LoaderView(this);
        loaderDialog = new LoaderDialog(this);

        startSpinning = findViewById(R.id.start_spinning);
        opacity = findViewById(R.id.loader_opacity);
        backgroundOpacity = findViewById(R.id.background_opacity);
        backgroundColor = findViewById(R.id.background_color);
        loaderHeight = findViewById(R.id.loader_height);
        loaderWidth = findViewById(R.id.loader_width);
        secondImage = findViewById(R.id.second_image);
        clockwise = findViewById(R.id.clockwise);
        xmlLoaderView = findViewById(R.id.xml_loaderview);
        fromXmlLoaderView = findViewById(R.id.from_xml);
        startSpinningDialog = findViewById(R.id.start_spinning_dialog);
    }

    private void setListeners() {
        startSpinning.setOnClickListener(v -> {
            if (fromXmlLoaderView.isChecked()) {
                xmlLoaderView.setloaderStatus(true);
                new Handler().postDelayed(
                        () -> xmlLoaderView.setloaderStatus(false), 5000);
            } else {
                loaderView.setLoaderImageHeight(Integer.parseInt(loaderHeight.getText().toString()));
                loaderView.setLoaderImageWidth(Integer.parseInt(loaderWidth.getText().toString()));
                loaderView.setLoaderOpacity(Float.parseFloat(opacity.getText().toString()));
                loaderView.setLoaderBackgroundColor(backgroundColor.getText().toString());
                loaderView.setLoaderBackgroundOpacity(Float.parseFloat(backgroundOpacity.getText().toString()));

                loaderView.setLoaderDirection(clockwise.isChecked() ? RotationDirection.CLOCKWISE : RotationDirection.ANTI_CLOCKWISE);

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    if (secondImage.isChecked()) {
                        loaderView.setLoaderImage(getDrawable(R.drawable.circular_arrow));

                    } else {
                        loaderView.setDefaultLoaderImage();
                    }
                }

                loaderView.setloaderStatus(true);
                new Handler().postDelayed(
                        () -> loaderView.setloaderStatus(false), 5000);
            }
        });

        startSpinningDialog.setOnClickListener(v -> {
            loaderDialog.showLoader();
            new Handler().postDelayed(
                    () -> loaderDialog.hideLoader(), 5000);
        });
    }
}