package com.fulai.myapplication;

import android.app.ActivityManager;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.CheckResult;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import static android.content.pm.ApplicationInfo.FLAG_LARGE_HEAP;

public class MainActivity extends AppCompatActivity {
    public static String TAG = "MainActivity";
    private View view1;
    private View view2;
    private TextView txt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txt = (TextView) findViewById(R.id.txt);

    }


    public void onClick(View view) {
        int memoryCacheSize = calculateMemoryCacheSize(this);
        System.out.println(memoryCacheSize);
        Toast.makeText(this, "修改之前", Toast.LENGTH_SHORT).show();
        txt.setText("首次运行");
    }

    @CheckResult
    static int calculateMemoryCacheSize(Context context) {
        ActivityManager am = getService(context, ACTIVITY_SERVICE);
        boolean largeHeap = (context.getApplicationInfo().flags & FLAG_LARGE_HEAP) != 0;
        int memoryClass = largeHeap ? am.getLargeMemoryClass() : am.getMemoryClass();
        // Target ~15% of the available heap.
        return (int) (1024L * 1024L * memoryClass / 7);
    }

    static <T> T getService(Context context, String service) {
        return (T) context.getSystemService(service);
    }


}
