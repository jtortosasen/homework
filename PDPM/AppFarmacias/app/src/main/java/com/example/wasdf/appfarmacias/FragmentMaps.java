package com.example.wasdf.appfarmacias;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.Objects;

public class FragmentMaps extends Fragment implements OnMapReadyCallback {

    private boolean globalZone = true;

    private CallbackListener callbackListener;
    public interface CallbackListener{
        void locationIdInformation(int idInformation);
    }


    @Override
    public void onAttach(Context context){
        super.onAttach(context);
        if(context instanceof CallbackListener){
            callbackListener = (CallbackListener) context;
            Log.d("info","callbackListener");
        }else{
            throw new ClassCastException(context.toString() + "error implement");
        }
    }


    AccessData dataMaps = new AccessData();
    private final String tagTown = "town";
    private final String tagPharmacy = "site";


    public FragmentMaps(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_maps, parent, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SupportMapFragment mapFragment = (SupportMapFragment) this.getChildFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    @Override
    public void onMapReady(final GoogleMap googleMap) {
        if(globalZone){
            addTownMarkers(googleMap);
            zoomToZone(googleMap);
        }

        googleMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            public boolean onMarkerClick(Marker marker) {
                String tag = (String) marker.getTag();
                Log.d("Markerto", (String) marker.getTag());
                int idSite = -1;
                if(tag != null){
                    if(tag.contains(tagPharmacy)){
                        String id = tag.replace(tagPharmacy,"");
                        idSite = Integer.parseInt(id);
                    }
                }

                if(Objects.equals(tagTown, tag)){
                    globalZone = false;
                    Log.d("Markertown", "aaa");
                    zoomToTown(googleMap, marker);
                    addSites(googleMap, marker.getTitle());

                }else Log.d("idSite", "Not value");

                if(Objects.equals(marker.getTag(),tagPharmacy+idSite) && idSite != -1)
                    openFragmentInformation(idSite);
                return false;
            }
        });
    }

    private void zoomToZone(GoogleMap googleMap) {
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(dataMaps.getPosTown(0), 10));
        //googleMap.animateCamera(CameraUpdateFactory.zoomTo(11),2000,null);
    }
    private void zoomToTown(GoogleMap googleMap, Marker marker){
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(marker.getPosition(), 16));
        //googleMap.animateCamera(CameraUpdateFactory.zoomTo(15),2000,null);
    }

    private void addTownMarkers(GoogleMap googleMap) {
        Marker marker;
        for (int i = 0; i < dataMaps.getTowns(); i++) {
            marker = googleMap.addMarker(new MarkerOptions()
                    .position(dataMaps.getPosTown(i))
                    .title(dataMaps.getNameTown(i)));
            marker.setTag(dataMaps.getTagTown(i));
        }
    }

    private  void addSites(GoogleMap googleMap, String name){
        int id = dataMaps.getIdFromNameTown(name);
        int[] idSites = dataMaps.getAllIdSites(id);
        Marker marker;
        for (int idSite : idSites) {
            marker = googleMap.addMarker(new MarkerOptions()
                    .position(dataMaps.getPosSite(idSite))
                    .title(dataMaps.getNameSite(idSite)));
            marker.setTag(dataMaps.getTagSite(idSite) + idSite);
        }
    }



    public void openFragmentInformation(int id){
        callbackListener.locationIdInformation(id);
    }

}
