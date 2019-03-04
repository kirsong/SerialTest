package twodpay.mobile.isapp.serialtest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {
    StaticDatas staticDatas = StaticDatas.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @OnClick(R.id.connect)
    public void connect() {
        thread.start();
    }

    Thread thread=new Thread(new Runnable() {
        @Override
        public void run() {
            staticDatas.SerialPortUtil();
            staticDatas.readData();
        }
    });
}
