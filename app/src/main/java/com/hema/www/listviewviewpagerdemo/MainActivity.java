package com.hema.www.listviewviewpagerdemo;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ListView lv;
    ArrayList<String> arrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lv = (ListView) findViewById(R.id.lv);

        View header = LayoutInflater.from(MainActivity.this)
                .inflate(R.layout.viewpage_layout, null);

        DecoratorViewPager vp = (DecoratorViewPager) header.findViewById(R.id.vp);
        vp.setNestedpParent((ViewGroup) vp.getParent());

        MyPageAdapter adapter = new MyPageAdapter(MainActivity.this);
        vp.setAdapter(adapter);

        lv.addHeaderView(header);

        for (int i = 0; i < 90; i++) {
            arrayList.add(i + " ----- " + System.currentTimeMillis());
        }

        ArrayAdapter arrayAdapter =
                new ArrayAdapter<>(this, R.layout.item, R.id.textView1, arrayList);

        lv.setAdapter(arrayAdapter);
    }

    public class MyPageAdapter extends PagerAdapter {
        private Context mContext;

        public MyPageAdapter(Context context) {
            mContext = context;
        }

        @Override
        public int getCount() {
            return 15;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            ImageView imageView = new ImageView(mContext);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setBackgroundResource(R.mipmap.ic_launcher);

            container.addView(imageView);
            return imageView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }

}
