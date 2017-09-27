package org.dync.androidsystembar;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.chiralcode.colorpicker.ColorPicker;

public class MainActivity extends AppCompatActivity {

    private Context mContext;
    private ColorPicker mColorPicker;
    private Button mButton;
    private TextView mTitle;
    private RadioGroup mRadioGroup;
    private RadioGroup mRadioGroupConfiguration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = this;

        mColorPicker = (ColorPicker) findViewById(R.id.color_picker);
        mTitle = (TextView) findViewById(R.id.tv_title);

        mRadioGroup = (RadioGroup) findViewById(R.id.rgroup);
        mRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                int checkedRadioButtonId = group.getCheckedRadioButtonId();
                String message = "";
                RadioButton radioButton = null;
                if (checkedRadioButtonId == R.id.rb_statusbar_white) {
                    StatusBarUtil.StatusBarLightMode(MainActivity.this, false);
                } else {
                    StatusBarUtil.StatusBarLightMode(MainActivity.this, true);
                }
                radioButton = (RadioButton) MainActivity.this.findViewById(checkedRadioButtonId);
                message = radioButton.getText().toString();
                Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
            }
        });
        mRadioGroupConfiguration = (RadioGroup) findViewById(R.id.rgroup_configuration);
        mRadioGroupConfiguration.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                if (checkedId == R.id.rb_landscape){//横屏
                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
//                    getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);//全屏
                    StatusBarUtil.setFitsSystemWindows(MainActivity.this, true);
                }else if(checkedId == R.id.rb_portrait){//竖屏
                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);// 竖屏
//                    getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);//清除全屏
                    StatusBarUtil.setFitsSystemWindows(MainActivity.this, false);
                }
            }
        });
        mButton = (Button) findViewById(R.id.button);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                applySelectedColor();
            }
        });
        mTitle.setBackgroundColor(getResources().getColor(R.color.colorAccent));
    }

    @Override
    protected void onStart() {
        super.onStart();
        StatusBarUtil.setStatusBarColor(MainActivity.this, getResources().getColor(R.color.colorAccent));
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
//        applySelectedColor();
    }

    private void applySelectedColor() {
        int selected = mColorPicker.getColor();
//        int color = Color.argb(153, Color.red(selected), Color.green(selected), Color.blue(selected));
        int color = Color.rgb(Color.red(selected), Color.green(selected), Color.blue(selected));

        StatusBarUtil.setStatusBarColor(MainActivity.this, color);
        mTitle.setBackgroundColor(color);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {// 横屏
//            Log.e(TAG, "onConfigurationChanged: " + "横屏");
//            onConfigurationLandScape();

        } else if (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
//            Log.e(TAG, "onConfigurationChanged: " + "竖屏");
//            onConfigurationPortrait();
        }
    }
}
