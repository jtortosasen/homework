package com.example.wasdf.examenandroid;

import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

public class Option2Activity extends AppCompatActivity {

    private ViewPager mViewPager;
    private SectionsPagerAdapter mSectionsPagerAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_option2);

        mViewPager = (ViewPager) findViewById(R.id.container);

        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(mSectionsPagerAdapter);

    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public static class MyFragments extends Fragment{

        static MyFragments newInstance(int resource, String name, String price, int imageResource){
            Bundle bundle = new Bundle();
            bundle.putInt("layout", resource);
            bundle.putString("name", name);
            bundle.putString("price", price);
            bundle.putInt("image", imageResource);
            MyFragments fragment = new MyFragments();
            fragment.setArguments(bundle);
            return fragment;
        }

        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            Bundle bundle = getArguments();
            View view = inflater.inflate(R.layout.fragment_layout1, container, false);
            TextView textViewName = (TextView) view.findViewById(R.id.textViewName);
            TextView textViewPrice = (TextView) view.findViewById(R.id.textViewPrice);
            ImageView imageView = (ImageView) view.findViewById(R.id.imageView);


            textViewName.setText(bundle.getString("name"));
            textViewPrice.setText(bundle.getString("price"));
            imageView.setImageResource(bundle.getInt("image"));

            return view;
        }

    }

    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        String name, price;
        int image;

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            Motos moto = new Motos();

            switch (position){
                case 0:
                    name = moto.getNomMoto(position);
                    price = String.valueOf(moto.getPreuMoto(position));
                    image = R.mipmap.moto0;

                    return MyFragments.newInstance(R.layout.fragment_layout1, name, price, image);
                case 1:
                    name = moto.getNomMoto(position);
                    price = String.valueOf(moto.getPreuMoto(position));
                    image = R.mipmap.moto1;
                    return MyFragments.newInstance(R.layout.fragment_layout1, name, price, image);
                case 2:
                    name = moto.getNomMoto(position);
                    price = String.valueOf(moto.getPreuMoto(position));
                    image = R.mipmap.moto2;
                    return MyFragments.newInstance(R.layout.fragment_layout1, name, price, image);
                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 3;
        }
    }
}
