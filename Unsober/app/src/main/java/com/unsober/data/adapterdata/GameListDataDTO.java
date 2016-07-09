package com.unsober.data.adapterdata;

/**
 * Created by shrinivas on 08-07-2016.
 */
public class GameListDataDTO {
    private long mItemId;
    private String mGameTitle;
    private String mImageLink;
    private String mNumberOfPlayers;
    private String mGameDescription;

    public GameListDataDTO(long mItemId, String mGameTitle, String mImageLink, String mNumberOfPlayers,String mGameDescription) {
        this.mItemId = mItemId;
        this.mGameTitle = mGameTitle;
        this.mImageLink = mImageLink;
        this.mNumberOfPlayers = mNumberOfPlayers;
        this.mGameDescription = mGameDescription;
    }

    public long getItemId() {
        return mItemId;
    }

    public void setItemId(long mItemId) {
        this.mItemId = mItemId;
    }

    public String getGameTitle() {
        return mGameTitle;
    }

    public void setGameTitle(String mGameTitle) {
        this.mGameTitle = mGameTitle;
    }

    public String getImageLink() {
        return mImageLink;
    }

    public void setImageLink(String mImageLink) {
        this.mImageLink = mImageLink;
    }

    public String getNumberOfPlayers() {
        return mNumberOfPlayers;
    }

    public void setNumberOfPlayers(String mNumberOfPlayers) {
        this.mNumberOfPlayers = mNumberOfPlayers;
    }

    public String getGameDescription() {
        return mGameDescription;
    }

    public void setGameDescription(String mGameDescription) {
        this.mGameDescription = mGameDescription;
    }
}
