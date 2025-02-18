package uk.co.cocosquid.tokiponakeyboard;


import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.net.Uri;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.FileProvider;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Array;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TextToImage extends FileProvider {
    void draw(String msg, String name, Context c) throws IOException {
        int charsperline = 18;
        int msgLength = msg.codePointCount(0, msg.length());
        int linenum = (int) Math.ceil((float) msgLength / (float) charsperline);
        int width = charsperline*50 + 4;
        int height = 51*linenum + 8;
        String now = String.valueOf(System.currentTimeMillis());
        Log.i("num",now);
        List<String> lines = new ArrayList<>();
        int lineStart = 0;
        int x;
        String substr;
        //split the string into lines, Making sure to split on a character break and not in the middle of a character
        for(int i=0;i<linenum;i++){
            for(x=lineStart;x<msg.length();x++){
                substr = msg.substring(lineStart,x);
                if(substr.codePointCount(0, substr.length())>charsperline){
                    x-=1;
                    break;
                }
            }
            Log.i("loop",msg.substring(lineStart,x));
            lines.add(msg.substring(lineStart,x));
            lineStart = x;
        }
        Log.i("num",String.valueOf(linenum));
        Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        canvas.drawColor(Color.TRANSPARENT);
        Paint paint = new Paint();
        Typeface sitelenpona = Typeface.createFromAsset(c.getAssets(),"fairfaxhd.ttf");
        paint.setTypeface(sitelenpona);
        paint.setTextAlign(Paint.Align.LEFT);
        paint.setAntiAlias(true);
        paint.setTextSize(50);
        //canvas.drawText(msg,0,20,paint);
        for(int i = 0; i < linenum; i++) {
            paint.setColor(Color.BLACK);
            canvas.drawText(lines.get(i), 2, 50+51*i-10, paint);
            canvas.drawText(lines.get(i), 6, 50+51*i-10, paint);
            canvas.drawText(lines.get(i), 4, 52+51*i-10, paint);
            canvas.drawText(lines.get(i), 4, 48+51*i-10, paint);
            canvas.drawText(lines.get(i), 2, 52+51*i-10, paint);
            canvas.drawText(lines.get(i), 6, 48+51*i-10, paint);
            canvas.drawText(lines.get(i), 6, 52+51*i-10, paint);
            canvas.drawText(lines.get(i), 2, 48+51*i-10, paint);
            paint.setColor(Color.WHITE);
            canvas.drawText(lines.get(i), 4, 50+51*i-10, paint);
        }
        canvas.save();
        canvas.restore();
        String path = c.getFilesDir().getAbsolutePath() + "/"+ now +".png";
        FileOutputStream fos = new FileOutputStream(path);
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, fos);
        fos.flush();
        fos.close();
        Uri uri = FileProvider.getUriForFile(c, c.getPackageName() + ".provider", new File(path));
        Object systemService = c.getSystemService(Context.CLIPBOARD_SERVICE);
        ((ClipboardManager) systemService).setPrimaryClip(ClipData.newUri(c.getContentResolver(), null, uri));

        //delete old files, files cannot be deleted immidietly because then they would not have time to be uploaded to discord
        File[] files =  c.getFilesDir().listFiles();
        assert files != null;
        for (File file : files) {
            try {
                String fileName = file.getName();
                fileName = fileName.substring(0, fileName.length() - 4); //chop .png off the end
                long fileAge = Long.parseLong(fileName);
                if (fileAge < Long.parseLong(now) - 3000) { //if the file is more than 30s old
                    file.delete();
                }
                Log.d("Files", "FileName:" + file.getName());
            } catch (Exception ignored){

            }
        }
    }

    @Override
    public boolean onCreate() {
        return false;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] strings, @Nullable String s, @Nullable String[] strings1, @Nullable String s1) {
        return null;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return "";
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues contentValues) {
        return null;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String s, @Nullable String[] strings) {
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues contentValues, @Nullable String s, @Nullable String[] strings) {
        return 0;
    }
}
