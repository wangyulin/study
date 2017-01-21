package com.wyl.comment.service;

import com.wyl.comment.module.Rating;

/**
 * Created by wangyulin on 20/01/2017.
 */
public interface ReviewsSummary {

    long getNumberOfReviewsWithRating(Rating rating);

}