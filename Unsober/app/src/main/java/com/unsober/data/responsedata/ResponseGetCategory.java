package com.unsober.data.responsedata;

import com.unsober.data.BaseDTO;

import java.util.ArrayList;

/**
 * Created by akshay on 07-07-2016.
 */
public class ResponseGetCategory extends BaseDTO {

    ArrayList<ResponseCategoryDTO> categories;

    public ResponseGetCategory() {
    }

    public ArrayList<ResponseCategoryDTO> getCategories() {
        return categories;
    }

    public void setCategories(ArrayList<ResponseCategoryDTO> categories) {
        this.categories = categories;
    }
}
