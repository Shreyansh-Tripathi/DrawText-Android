package com.shreyansh.drawtext;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;

import android.util.SparseArray;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

import android.widget.Toast;

import com.google.android.gms.vision.Frame;
import com.google.android.gms.vision.text.TextBlock;
import com.google.android.gms.vision.text.TextRecognizer;


import me.panavtec.drawableview.DrawableView;
import me.panavtec.drawableview.DrawableViewConfig;

public class MainActivity extends AppCompatActivity{

    DrawableView drawableView;
    Button textButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        drawableView = findViewById(R.id.drawableView);
        textButton=findViewById(R.id.textButton);

        DrawableViewConfig config = new DrawableViewConfig();
        config.setCanvasHeight(2200);
        config.setCanvasWidth(1920);
        config.setShowCanvasBounds(true);
        config.setStrokeColor(Color.RED);
        config.setStrokeWidth(15f);
        config.setMaxZoom(5f);
        config.setMinZoom(1f);
        drawableView.setConfig(config);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction())
        {
            case MotionEvent.ACTION_DOWN:
                textButton.setVisibility(View.GONE);
                return true;

            case MotionEvent.ACTION_MOVE:
                textButton.setVisibility(View.GONE);
                break;

            case MotionEvent.ACTION_UP:
                textButton.setVisibility(View.VISIBLE);
                break;
            default:
                return false;
        }
        return true;
    }

    public void showText(View view) {
        getText(drawableView.obtainBitmap());
        drawableView.clear();
    }

    public void getText(Bitmap bitmap){
        TextRecognizer recognizer=new TextRecognizer.Builder(getApplicationContext()).build();

        if(!recognizer.isOperational()){
            Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
        }
        else{
            Frame frame=new Frame.Builder().setBitmap(bitmap).build();
            SparseArray<TextBlock> str=recognizer.detect(frame);

            StringBuilder text=new StringBuilder();

            for(int i=0;i<str.size();i++){
                TextBlock textBlock=str.valueAt(i);
                text.append(textBlock.getValue());
                text.append("\n");
            }

            Toast.makeText(this, text.toString(), Toast.LENGTH_SHORT).show();
        }
    }


   /* private void getText(Bitmap bitmap){
          TessBaseAPI tessBaseAPI;
        try{
            tessBaseAPI = new TessBaseAPI();
        }catch (Exception e){
            Toast.makeText(this, "Error-"+e.getMessage(), Toast.LENGTH_SHORT).show();
            return ;
        }

        tessBaseAPI.init("/data","eng");
        tessBaseAPI.setImage(bitmap);
        String retStr;
        try{
            retStr = tessBaseAPI.getUTF8Text();
        }catch (Exception e){
            Toast.makeText(this, "Error-"+e.getMessage(), Toast.LENGTH_SHORT).show();
            return;
        }
        tessBaseAPI.end();
        Toast.makeText(this, retStr, Toast.LENGTH_SHORT).show();
    }*/
}