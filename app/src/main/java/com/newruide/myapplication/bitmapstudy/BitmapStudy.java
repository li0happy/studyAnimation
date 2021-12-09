package com.newruide.myapplication.bitmapstudy;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.widget.ImageView;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Observable;

public class BitmapStudy {
    /**
     * Bitmap在绘图中相关的使用主要有两种：
     * （1）转换为BitmapDrawable对象使用；
     * （2）当做画布来使用
     *加载图像可以使用BitmapFactory 和Bitmap得静态方法，但是要剪裁或者缩放图片时，就只能使用Bitmap
     */
    //Bitmap -> BitmapDrawable
    private BitmapDrawable cvtBitmapToBitmapDrawable(Bitmap bitmap){
        BitmapDrawable bitmapDrawable = new BitmapDrawable(bitmap);
        return bitmapDrawable;
    }
    //自建画布
    private void createBitamp(){
        Bitmap bitmap = Bitmap.createBitmap(200,100, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        canvas.drawColor(Color.YELLOW);
    }
    /**
     * decodeByteArray(byte[] data,int offset,int length)使用
     *
     */
    private void setImageView(ImageView imageView,String path) throws IOException {
        new Runnable(){
            @Override
            public void run() {
                byte[] data = new byte[0];
                try {
                    data = getImage(path);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                int length = data.length;
                Bitmap bitmap = BitmapFactory.decodeByteArray(data,0,length);
                imageView.post(new Runnable() {
                    @Override
                    public void run() {
                        imageView.setImageBitmap(bitmap);
                    }
                });
            }
        };


    }
    private static byte[] getImage(String path) throws IOException {
        URL url = new URL(path);
        HttpURLConnection httpURLConnection =(HttpURLConnection) url.openConnection();
        httpURLConnection.setRequestMethod("GET");
        httpURLConnection.setReadTimeout(6000);
        InputStream in = null;
        if(httpURLConnection.getResponseCode() == 200){
            in = httpURLConnection.getInputStream();
            byte[] result = readStream(in);
            in.close();
            return result;
        }
        return null;
    }
    private static byte[] readStream(InputStream in) throws IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len = -1;
        while((len = in.read(buffer)) != -1){
            outputStream.write(buffer,0,len);
        }
        outputStream.close();
        in.close();
        return outputStream.toByteArray();
    }
}
