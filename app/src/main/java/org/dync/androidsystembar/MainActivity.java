package org.dync.androidsystembar;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.chiralcode.colorpicker.ColorPicker;

public class MainActivity extends AppCompatActivity {

    private ColorPicker mColorPicker;
    private Button mButton;
    private TextView mTitle;
    private RadioGroup mRadioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mColorPicker = (ColorPicker) findViewById(R.id.color_picker);
        mTitle = (TextView) findViewById(R.id.tv_title);

        mRadioGroup = (RadioGroup) findViewById(R.id.rgroup);
        mRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                int checkedRadioButtonId = group.getCheckedRadioButtonId();
                if (checkedRadioButtonId == R.id.rb_statusbar_white){
                    StatusBarUtil.StatusBarLightMode(MainActivity.this,false);
                }else {
                    StatusBarUtil.StatusBarLightMode(MainActivity.this,true);
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
        StatusBarUtil.setStatusBarColor(MainActivity.this, getResources().getColor(R.color.colorAccent),false);
        mTitle.setBackgroundColor(getResources().getColor(R.color.colorAccent));
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
//        applySelectedColor();
    }

    private void applySelectedColor() {
        int selected = mColorPicker.getColor();
        int color = Color.argb(153, Color.red(selected), Color.green(selected), Color.blue(selected));

        StatusBarUtil.setStatusBarColor(MainActivity.this, color,false);
        mTitle.setBackgroundColor(color);
    }

}
