package com.demo.DSS5MSREVIEW.service;

import com.demo.DSS5MSREVIEW.model.Review;
import com.demo.DSS5MSREVIEW.model.ReviewRequestModel;

import java.util.ArrayList;

public interface ReviewService {
    void save(ReviewRequestModel requestModel);

    ArrayList<Review> findAll();


}
