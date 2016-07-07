package com.unsober.data.adapterdata;

/**
 * Created by shrinivas on 07-07-2016.
 */
public class CategoryDataDTO {
    private long mCategoryId;
    private String mCategoryName;
    private String mCategoryIcon;
    private long mCategoryParentId;
    private int mCategoryStatus;

    public CategoryDataDTO(long mCategoryId, String mCategoryName, String mCategoryIcon,
                           long mCategoryParentId, int mCategoryStatus) {
        this.mCategoryId = mCategoryId;
        this.mCategoryName = mCategoryName;
        this.mCategoryIcon = mCategoryIcon;
        this.mCategoryParentId = mCategoryParentId;
        this.mCategoryStatus = mCategoryStatus;
    }

    public long getCategoryId() {
        return mCategoryId;
    }

    public void setCategoryId(long mCategoryId) {
        this.mCategoryId = mCategoryId;
    }

    public String getCategoryName() {
        return mCategoryName;
    }

    public void setCategoryName(String mCategoryName) {
        this.mCategoryName = mCategoryName;
    }

    public String getCategoryIcon() {
        return mCategoryIcon;
    }

    public void setCategoryIcon(String mCategoryIcon) {
        this.mCategoryIcon = mCategoryIcon;
    }

    public long getCategoryParentId() {
        return mCategoryParentId;
    }

    public void setCategoryParentId(long mCategoryParentId) {
        this.mCategoryParentId = mCategoryParentId;
    }

    public int getCategoryStatus() {
        return mCategoryStatus;
    }

    public void setCategoryStatus(int mCategoryStatus) {
        this.mCategoryStatus = mCategoryStatus;
    }
}
