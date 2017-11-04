package com.example.user.colorizer;



import android.graphics.Color;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;



public class MainActivity extends AppCompatActivity {
    boolean color = true;
    boolean red = true;
    boolean blue = true;
    boolean green = true;
    private ImageView imageview;
//int imageindex=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageview = (ImageView) findViewById(R.id.imageView);

       // loadImage();
    }

   // private void loadImage() {
        //Glide.with(this).load(imageResIds[imageIndex]).into(imageView);
  //  }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

    getMenuInflater().inflate(R.menu.options_menu,menu);
        Drawable nextImageDrawable=menu.findItem(R.id.nextImage).getIcon();
        nextImageDrawable.setColorFilter(Color.WHITE,PorterDuff.Mode.SRC_ATOP);
         menu.findItem(R.id.red).setChecked(red);
        menu.findItem(R.id.green).setChecked(green);
        menu.findItem(R.id.blue).setChecked(blue);
        menu.setGroupVisible(R.id.colorGroup,color);

        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            //  case R.id.nextImage:
            // imageIndex++;
            //If(imageIndex>=imageResIds.lenght)
            //imageIndex=0;
            //loadimage();
            //break;
            case R.id.color:
                color = !color;
                updateSaturation();
                invalidateOptionsMenu();
                break;
            case R.id.red:
                red = !red;
                updateColors();
                item.setChecked(red);
                break;

            case R.id.green:
                green = !green;
                updateColors();
                item.setChecked(green);
                break;

            case R.id.blue:
                blue = !blue;
                updateColors();
                item.setChecked(blue);
                break;
            case R.id.reset:
                imageview.clearColorFilter();
                red=green=blue=true;
                invalidateOptionsMenu();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void updateSaturation ()
                {
                    ColorMatrix colorMatrix = new ColorMatrix();
                    if (color) {
                        red = green = blue = true;
                        colorMatrix.setSaturation(1);
                    } else {
                        colorMatrix.setSaturation(0);
                    }
                    ColorMatrixColorFilter colorFilter = new ColorMatrixColorFilter(colorMatrix);
                    imageview.setColorFilter(colorFilter);
                }

            private void updateColors() {
                ColorMatrix colorMatrix = new ColorMatrix();
                float[] matrix =
                        {

                                1, 0, 0, 0, 0,
                                0, 1, 0, 0, 0,
                                0, 0, 1, 0, 0,
                                0, 0, 0, 1, 0
                         };
                if (!red) matrix[0] = 0;
                if (!green) matrix[6] = 0;
                if (!blue) matrix[12] = 0;
                colorMatrix.set(matrix);
                ColorMatrixColorFilter colorFilter = new ColorMatrixColorFilter(colorMatrix);

                imageview.setColorFilter(colorFilter);

            }
        }












