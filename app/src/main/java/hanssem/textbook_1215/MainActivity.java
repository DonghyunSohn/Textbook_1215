package hanssem.textbook_1215;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends ActionBarActivity {

    public static final int PROGRESS_DIALOG = 1001;

    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 프로그레스바 객체 참조 후 최대값과 현재값 설정
        ProgressBar proBar = (ProgressBar) findViewById(R.id.progressBar01);
        proBar.setIndeterminate(false);
        proBar.setMax(100);
        proBar.setProgress(80);

        // 아이콘 이미지 설정
        ImageView icon = (ImageView) findViewById(R.id.iconItem);
        Resources res = getResources();
        Drawable drawable = (Drawable) res.getDrawable(R.drawable.apple);
        icon.setImageDrawable(drawable);

        // 텍스트 설정
        TextView nameText = (TextView) findViewById(R.id.dataItem01);
        nameText.setText("사과");

        // 텍스트 설정
        TextView progressText = (TextView) findViewById(R.id.dataItem02);
        progressText.setText("80%");

        // 보여주기 버튼 이벤트 설정
        Button btnShow = (Button) findViewById(R.id.btnShow);
        btnShow.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                showDialog(PROGRESS_DIALOG);    // 프로그레스 대화상자 없애기
            }
        });

        // 닫기 버튼 이벤트 설정
        Button btnClose = (Button) findViewById(R.id.btnClose);
        btnClose.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (progressDialog != null) {
                    progressDialog.dismiss();
                }
            }
        });

    }

    public Dialog onCreateDialog(int id) {
        switch (id) {
            case (PROGRESS_DIALOG):
                progressDialog = new ProgressDialog(this);
                progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                progressDialog.setMessage("데이터를 확인하는 중입니다.");

                return progressDialog;
        }

        return null;
    }
}