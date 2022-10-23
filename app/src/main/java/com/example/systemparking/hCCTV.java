package com.example.systemparking;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class hCCTV extends AppCompatActivity {

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference databaseReference = database.getReference();

    private WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hcctv);

        webView = (WebView) findViewById(R.id.wvCctv);
        String newUA= "Mozilla/5.0 (X11; U; Linux i686; en-US; rv:1.9.0.4) Gecko/20100101 Firefox/4.0";
        webView.getSettings().setUserAgentString(newUA);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setLoadWithOverviewMode(true);
        webView.getSettings().setUseWideViewPort(true);
        webView.getSettings().setBuiltInZoomControls(true);
        webView.setInitialScale(1);
        final SwipeRefreshLayout menyegarkan = (SwipeRefreshLayout) findViewById(R.id.sMenyegarkan);

        databaseReference.child("url").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String ip = snapshot.getValue(String.class);
                final String address = "http://"+ip+"/";
                Log.d("url", address);
                menyegarkan.setRefreshing(true);
                LoadUrl(address, webView, menyegarkan);
                menyegarkan.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                    @Override
                    public void onRefresh() {
                        LoadUrl(address, webView, menyegarkan);
                    }
                });}

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }});
    }

    private void LoadUrl(final String address, WebView wv, final SwipeRefreshLayout refreshLayout){
        wv.loadUrl(address);
        wv.setWebViewClient(new WebViewClient(){
            @Override
            public void onReceivedError(WebView view, int errorCode, String describ, String fillurl){
                super.onReceivedError(view, errorCode,describ,fillurl);}

            @Override
            public void onPageStarted(WebView view, String url, Bitmap fav){
                super.onPageStarted(view,url,fav);
                refreshLayout.setRefreshing(true);}

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url){
                view.loadUrl(url);
                return true;}

            @Override
            public void onPageFinished(WebView view, String url){
                super.onPageFinished(view,url);
                refreshLayout.setRefreshing(false);}
        });}

    public void destroyWV(){
        webView.clearHistory();
        webView.clearCache(true);
        webView.loadUrl("about:blank");
        webView.onPause();
        webView.removeAllViews();
        webView.destroyDrawingCache();
        webView.destroy();
        webView = null;}

    @Override
    public void onBackPressed(){
        super.onBackPressed();
        destroyWV();}
}