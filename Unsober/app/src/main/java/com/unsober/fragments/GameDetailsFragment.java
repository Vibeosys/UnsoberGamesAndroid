package com.unsober.fragments;

import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.Nullable;


import android.text.Html;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerFragment;
import com.unsober.R;
import com.unsober.data.adapterdata.ItemDataDTO;
import com.unsober.utils.AppConstants;
import com.unsober.utils.CustomVolleyRequestQueue;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by shrinivas on 06-07-2016.
 */
public class GameDetailsFragment extends BaseFragment implements YouTubePlayer.OnInitializedListener {
    private static final String TAG = GameDetailsFragment.class.getSimpleName();
    private TextView mTxtTitle, mTxtDescription, mTxtPlayers, mTxtViews;
    private AdView mAdView;
    private NetworkImageView mImgGame;
    private ItemDataDTO mItemDataDTO;
    private long mItemId = 0;
    private ImageLoader mImageLoader;
    private FrameLayout mFrameLayout;
    private String mYoutubeLink;
    private String mTitle;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_details_layout, container, false);
        //getActivity().setTitle("Game Title#1");

        mTxtTitle = (TextView) view.findViewById(R.id.txtTitle);
        mTxtDescription = (TextView) view.findViewById(R.id.txtDescription);
        mTxtPlayers = (TextView) view.findViewById(R.id.txtPlayers);
        mTxtViews = (TextView) view.findViewById(R.id.txtViews);
        mImgGame = (NetworkImageView) view.findViewById(R.id.imgGame);
        mAdView = (AdView) view.findViewById(R.id.adView);
        try {
            Bundle bundle = this.getArguments();
            if (bundle != null) {
                mItemId = getArguments().getLong("itemId");

            } else {
                Log.e(TAG, "## Error to get the item no");
            }
        } catch (Exception e) {
            Log.e(TAG, "## error tp get bundle data");
        }

        if (mItemId != 0)
            setUpUI();
        return view;
    }

    private void setUpUI() {
        mItemDataDTO = mDbRepository.getItemDetail(mItemId);
        mTitle = mItemDataDTO.getItemTitle();
        getActivity().setTitle(mTitle.toUpperCase());
        YouTubePlayerFragment youTubePlayerFragment = YouTubePlayerFragment.newInstance();
        youTubePlayerFragment.initialize(AppConstants.YOUTUBE_AUTH_KEY, this);

        if (mSessionManager.getIsPurchased() == AppConstants.ITEM_PURCHASED) {
            mAdView.setVisibility(View.GONE);
        } else {
            mAdView.setVisibility(View.VISIBLE);
            AdRequest adRequest = new AdRequest.Builder()
               /* .addTestDevice("0C1256C41F20A2DA8E3751E2E9B38809")
                .addTestDevice("DC7854A3ADFE5403F956AFB5B83C7391")
                .addTestDevice("61626A327E33DC376127B6762DAFAE0C")*/
                    .build();
            mAdView.loadAd(adRequest);
        }

        mTxtTitle.setText(mItemDataDTO.getItemTitle());
        mTxtDescription.setText(Html.fromHtml(mItemDataDTO.getItemDescription()));
        mTxtPlayers.setText("Minimum of " + mItemDataDTO.getNoOfPlayers() + " Players");
        mTxtViews.setText(mItemDataDTO.getItemView() + " Views");

        mImageLoader = CustomVolleyRequestQueue.getInstance(getActivity().getApplicationContext())
                .getImageLoader();
        final String url = mItemDataDTO.getItemImageLink();
        if (url != null && !url.isEmpty()) {
            try {
                mImageLoader.get(url, ImageLoader.getImageListener(mImgGame,
                        R.drawable.cooler, R.drawable.cooler));
                mImgGame.setImageUrl(url, mImageLoader);
            } catch (Exception e) {
                mImgGame.setImageResource(R.drawable.cooler);
            }
        } else {
            mImgGame.setImageResource(R.drawable.cooler);
        }
        mYoutubeLink = mItemDataDTO.getItemYoutubeLink();
        if (TextUtils.isEmpty(mYoutubeLink) || mYoutubeLink == null || mYoutubeLink.equals("")) {

        } else {
            FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
            transaction.add(R.id.youtube_fragment, youTubePlayerFragment).commit();
        }

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
       /* super.onCreateOptionsMenu(menu, inflater);*/
        inflater.inflate(R.menu.game_details_search, menu);
        MenuItem menuItem = menu.findItem(R.id.gameDetails_search);


    }

    @Override
    public void onPause() {
        if (mAdView != null) {
            mAdView.pause();
        }
        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mAdView != null) {
            mAdView.resume();
        }
    }

    @Override
    public void onDestroy() {
        if (mAdView != null) {
            mAdView.destroy();
        }
        super.onDestroy();
    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean wasRestored) {
        youTubePlayer.setFullscreen(false);
        youTubePlayer.setShowFullscreenButton(false);
        if (!wasRestored) {
            youTubePlayer.cueVideo(getCode(mYoutubeLink));
        }
    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

    }

    private String getCode(String url) {
        String pattern = "(?<=watch\\?v=|/videos/|embed\\/|youtu.be\\/|\\/v\\/|\\/e\\/|watch\\?v%3D|watch\\?feature=player_embedded&v=|%2Fvideos%2F|embed%\u200C\u200B2F|youtu.be%2F|%2Fv%2F)[^#\\&\\?\\n]*";

        Pattern compiledPattern = Pattern.compile(pattern);
        Matcher matcher = compiledPattern.matcher(url); //url is youtube url for which you want to extract the id.
        if (matcher.find()) {
            return matcher.group();
        }
        return null;
    }
}
