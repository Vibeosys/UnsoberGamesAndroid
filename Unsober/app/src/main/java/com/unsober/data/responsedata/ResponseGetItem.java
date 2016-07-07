package com.unsober.data.responsedata;

import com.unsober.data.BaseDTO;

import java.util.ArrayList;

/**
 * Created by shrinivas on 07-07-2016.
 */
public class ResponseGetItem  extends BaseDTO{
    ArrayList<ResponseItemDTO>responseItem ;


    public ResponseGetItem() {
    }

    public ArrayList<ResponseItemDTO> getResponseItemDTO() {
        return responseItem;
    }

    public void setResponseItemDTO(ArrayList<ResponseItemDTO> responseItemDTO) {
        this.responseItem = responseItemDTO;
    }
}
