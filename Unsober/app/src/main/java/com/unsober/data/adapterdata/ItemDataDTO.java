package com.unsober.data.adapterdata;

/**
 * Created by shrinivas on 07-07-2016.
 */
public class ItemDataDTO {
    private long mItemId;
    private String mItemTitle;
    private String mItemDescription;
    private String mItemImageLink;
    private String mItemYoutubeLink;
    private String mItemTag1;
    private String mItemTag2;
    private String mItemTag3;
    private int mItemCategoryId;
    private int mItemStatus;
    private String mItemDateTime;
    private long mItemView;

    public ItemDataDTO(long mItemId, String mItemTitle, String mItemDescription,
                       String mItemImageLink, String mItemYoutubeLink, String mItemTag1,
                       String mItemTag2, String mItemTag3, int mItemCategoryId, int mItemStatus,
                       String mItemDateTime, long mItemView) {
        this.mItemId = mItemId;
        this.mItemTitle = mItemTitle;
        this.mItemDescription = mItemDescription;
        this.mItemImageLink = mItemImageLink;
        this.mItemYoutubeLink = mItemYoutubeLink;
        this.mItemTag1 = mItemTag1;
        this.mItemTag2 = mItemTag2;
        this.mItemTag3 = mItemTag3;
        this.mItemCategoryId = mItemCategoryId;
        this.mItemStatus = mItemStatus;
        this.mItemDateTime = mItemDateTime;
        this.mItemView = mItemView;
    }

    public long getItemId() {
        return mItemId;
    }

    public void setItemId(long mItemId) {
        this.mItemId = mItemId;
    }

    public String getItemTitle() {
        return mItemTitle;
    }

    public void setItemTitle(String mItemTitle) {
        this.mItemTitle = mItemTitle;
    }

    public String getItemDescription() {
        return mItemDescription;
    }

    public void setItemDescription(String mItemDescription) {
        this.mItemDescription = mItemDescription;
    }

    public String getItemImageLink() {
        return mItemImageLink;
    }

    public void setItemImageLink(String mItemImageLink) {
        this.mItemImageLink = mItemImageLink;
    }

    public String getItemYoutubeLink() {
        return mItemYoutubeLink;
    }

    public void setItemYoutubeLink(String mItemYoutubeLink) {
        this.mItemYoutubeLink = mItemYoutubeLink;
    }

    public String getItemTag1() {
        return mItemTag1;
    }

    public void setItemTag1(String mItemTag1) {
        this.mItemTag1 = mItemTag1;
    }

    public String getItemTag2() {
        return mItemTag2;
    }

    public void setItemTag2(String mItemTag2) {
        this.mItemTag2 = mItemTag2;
    }

    public String getItemTag3() {
        return mItemTag3;
    }

    public void setItemTag3(String mItemTag3) {
        this.mItemTag3 = mItemTag3;
    }

    public int getItemCategoryId() {
        return mItemCategoryId;
    }

    public void setItemCategoryId(int mItemCategoryId) {
        this.mItemCategoryId = mItemCategoryId;
    }

    public int getItemStatus() {
        return mItemStatus;
    }

    public void setItemStatus(int mItemStatus) {
        this.mItemStatus = mItemStatus;
    }

    public String getItemDateTime() {
        return mItemDateTime;
    }

    public void setItemDateTime(String mItemDateTime) {
        this.mItemDateTime = mItemDateTime;
    }

    public long getItemView() {
        return mItemView;
    }

    public void setItemView(long mItemView) {
        this.mItemView = mItemView;
    }
}
