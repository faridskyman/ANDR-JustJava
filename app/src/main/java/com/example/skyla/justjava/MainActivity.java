package com.example.skyla.justjava;

import android.app.ActionBar;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.target.GlideDrawableImageViewTarget;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;
import org.w3c.dom.Text;

import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




    }


    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        //display(1);
        displayPrice(returnTxtViewAsInt(getTxtView(R.id.quantity_text_view)) * 4);

        //load image to imageview via library 'glide'
        ImageView imageView = (ImageView)findViewById(R.id.glideImage);
        //imageView.setLayoutParams(new ActionBar.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT));
        String URL = "http://www.wizlearn.com/style/img/logo/asknlearn.jpg";
        URL = "http://i1344.photobucket.com/albums/p652/ecnal79/atat_zps0d833e6c.jpg";
        //Glide.with(this).load(URL).into(imageView);


        Glide.with(getApplicationContext())
                .load(URL)
                        // The placeholder image is shown immediately and
                        // replaced by the remote image when Picasso has
                        // finished fetching it.
                //.placeholder(R.drawable.android_logo)
                        //A request will be retried three times before the error placeholder is shown.
                //.error(R.drawable.error)
                        // Transform images to better fit into layouts and to
                        // reduce memory size.
                //.diskCacheStrategy(DiskCacheStrategy.ALL)
                .transform(new CircleTransform(getApplicationContext()))
                //.override(250, 300)
                //.centerCrop()
                .into(imageView);
        imageView.setVisibility(View.VISIBLE);
        //downloadBtn.setVisibility(View.INVISIBLE);
    }

    public void rollbackImage(View view)
    {
        ImageView imageView = (ImageView)findViewById(R.id.glideImage);
        Glide.with(getApplicationContext())
                .load(R.drawable.android_logo)
                .into(imageView);
        imageView.setVisibility(View.VISIBLE);
    }





    /**
     * This method displays the given quantity value on the screen.
     */
    private void display(int number) {

        TextView quantityTextView = getTxtView(R.id.quantity_text_view);
        int val = returnTxtViewAsInt(quantityTextView) + number;

        quantityTextView.setText("" + val);
    }

    public void incrementQuantity(View view)
    {
        updateQuantity(true);
    }

    public void decrementQuantity(View view)
    {
        updateQuantity(false);
    }


    public void updateQuantity(boolean isIncrement)
    {
        TextView quantityTextView = getTxtView(R.id.quantity_text_view);
        int val = returnTxtViewAsInt(quantityTextView);
        if (isIncrement) {
            if (val <3)
            {
                val = 3;
            }
            else {
                val++;
            }
        }
        else {
            if (val >=2) {
                val--;
            }
            else
            {
                val = 1;
            }
        }
        quantityTextView.setText("" + val);
        displayPrice(returnTxtViewAsInt(getTxtView(R.id.quantity_text_view)) * 4);
    }




    private int returnTxtViewAsInt(TextView tv)
    {
        return Integer.parseInt(tv.getText().toString());
    }

    private TextView getTxtView(int id)
    {
        return (TextView)findViewById(id);
    }

    private void  displayPrice(int number)
    {
        TextView priceTextView = (TextView) findViewById(R.id.price_text_view);

        priceTextView.setText(NumberFormat.getCurrencyInstance().format(number));
    }


    public static class CircleTransform extends BitmapTransformation {
        public CircleTransform(Context context) {
            super(context);
        }

        @Override
        protected Bitmap transform(BitmapPool pool, Bitmap toTransform,
                                   int outWidth, int outHeight) {
            return circleCrop(pool, toTransform);
        }

        private static Bitmap circleCrop(BitmapPool pool, Bitmap source) {
            if (source == null)
                return null;

            int size = Math.min(source.getWidth(), source.getHeight());
            int x = (source.getWidth() - size) / 2;
            int y = (source.getHeight() - size) / 2;

            // TODO this could be acquired from the pool too
            Bitmap squared = Bitmap.createBitmap(source, x, y, size, size);

            Bitmap result = pool.get(size, size, Bitmap.Config.ARGB_8888);
            if (result == null) {
                result = Bitmap.createBitmap(size, size,
                        Bitmap.Config.ARGB_8888);
            }

            Canvas canvas = new Canvas(result);
            Paint paint = new Paint();
            paint.setShader(new BitmapShader(squared,
                    BitmapShader.TileMode.CLAMP, BitmapShader.TileMode.CLAMP));
            paint.setAntiAlias(true);
            float r = size / 2f;
            canvas.drawCircle(r, r, r, paint);
            return result;
        }

        @Override
        public String getId() {
            return getClass().getName();
        }
    }


}

