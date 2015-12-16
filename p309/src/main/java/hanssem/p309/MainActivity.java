package hanssem.p309;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;


public class MainActivity extends ActionBarActivity {

    private View panel;

    private SeekBar seekbar;    // 시크바 객체

    private TextView text01;

    private int brightness = 50;    // 기본 밝기값

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 레이아웃에 정의된 객체 참조
        panel = findViewById(R.id.panel01);
        text01 = (TextView) findViewById(R.id.text01);
        seekbar = (SeekBar) findViewById(R.id.seekbar01);

        // 버튼 이벤트 처리
        Button showBtn = (Button) findViewById(R.id.showBtn);
        showBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                showPanel();    // 시크바 보이기 메소드 호출
            }
        });

        // 시크바에 리스너 설정
        seekbar.setOnSeekBarChangeListener(new MyOnSeekBarChangeListener());

    }

    private void showPanel() {
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.translate_left);
        seekbar.setProgress(this.brightness);
        panel.setVisibility(View.VISIBLE);

        panel.startAnimation(animation);    // 애니메이션 설정
    }

    // 화면 밝기 설정 메소드
    private void setBrightness(int value) {
        if (value < 10) {
            value = 10;
        } else if (value > 100) {
            value = 100;
        }

        brightness = value;

        // 밝기 설정
        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.screenBrightness = (float) value / 100;
        getWindow().setAttributes(params);
    }

    // 패널 감추기
    private void hidePanel() {
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.translate_right);
        panel.startAnimation(animation);
        panel.setVisibility(View.GONE);

    }

    class MyOnSeekBarChangeListener implements SeekBar.OnSeekBarChangeListener {

        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            setBrightness(progress);
            text01.setText("밝기 수준 : " + progress);  // 텍스트에 밝기 수준 표시
        }

        public void onStartTrackingTouch(SeekBar seekBar) {
        }

        public void onStopTrackingTouch(SeekBar seekBar) {
            hidePanel();
        }
    }
}
