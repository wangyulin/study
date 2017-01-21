package com.wyl.comment.service;

import com.wyl.comment.module.City;
import com.wyl.comment.module.Hotel;
import com.wyl.comment.module.Review;
import com.wyl.comment.module.ReviewDetails;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Created by wangyulin on 20/01/2017.
 */
public interface HotelService {

    Hotel getHotel(City city, String name);

    Page<Review> getReviews(Hotel hotel, Pageable pageable);

    Review getReview(Hotel hotel, int index);

    Review addReview(Hotel hotel, ReviewDetails details);

    ReviewsSummary getReviewSummary(Hotel hotel);

}
