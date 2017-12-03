package com.example.eslam.paletteapi;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.graphics.Palette;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    ImageView img, im1, im2, im3, im4, im5, im6;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        img = findViewById(R.id.img1);
        im1 = findViewById(R.id.im1);
        im2 = findViewById(R.id.im2);
        im3 = findViewById(R.id.im3);
        im4 = findViewById(R.id.im4);
        im5 = findViewById(R.id.im5);
        im6 = findViewById(R.id.im6);
        btn = findViewById(R.id.palette);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int id = R.drawable.ess;
                Bitmap bitmap = BitmapFactory.decodeResource(getResources(),id);
                createPaletteAsync(bitmap,id);
            }
        });

    }


    // Generate palette synchronously and return it
    public Palette createPaletteSync(Bitmap bitmap) {
        Palette p = Palette.from(bitmap).generate();
        return p;
    }


    // Generate palette asynchronously and use it on a different
    // thread using onGenerated()
    public void createPaletteAsync(final Bitmap bitmap, final int id) {
        Palette.from(bitmap).generate(new Palette.PaletteAsyncListener() {
            public void onGenerated(Palette p) {
                generateColors(id,bitmap);
            }
        });
    }
    void generateColors(int id,Bitmap bitmap){
        bitmap = BitmapFactory.decodeResource(getResources(),id);
        Palette p = createPaletteSync(bitmap);

        int defaultHex = 0x000000;
        int vibrant = p.getVibrantColor(defaultHex);
        int vibrantLight = p.getLightVibrantColor(defaultHex);
        int vibrantDark = p.getDarkVibrantColor(defaultHex);
        int muted = p.getMutedColor(defaultHex);
        int mutedLight = p.getLightMutedColor(defaultHex);
        int mutedDark = p.getDarkMutedColor(defaultHex);

        im1.setBackgroundColor(vibrant);
        im2.setBackgroundColor(vibrantLight);
        im3.setBackgroundColor(vibrantDark);
        im4.setBackgroundColor(muted);
        im5.setBackgroundColor(mutedLight);
        im6.setBackgroundColor(mutedDark);

    }
}
