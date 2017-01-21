package com.wyl.comment.service.impl;

import com.wyl.comment.dao.HotelRepository;
import com.wyl.comment.dao.ReviewRepository;
import com.wyl.comment.module.*;
import com.wyl.comment.service.HotelService;
import com.wyl.comment.service.ReviewsSummary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wangyulin on 20/01/2017.
 */
@Component("hotelService")
@Transactional
class HotelServiceImpl implements HotelService {

    private final HotelRepository hotelRepository;

    private final ReviewRepository reviewRepository;

    public HotelServiceImpl(HotelRepository hotelRepository,
                            ReviewRepository reviewRepository) {
        this.hotelRepository = hotelRepository;
        this.reviewRepository = reviewRepository;
    }

    @Override
    public Hotel getHotel(City city, String name) {
        Assert.notNull(city, "City must not be null");
        Assert.hasLength(name, "Name must not be empty");
        return this.hotelRepository.findByCityAndName(city, name);
    }

    @Override
    public Page<Review> getReviews(Hotel hotel, Pageable pageable) {
        Assert.notNull(hotel, "Hotel must not be null");
        return this.reviewRepository.findByHotel(hotel, pageable);
    }

    @Override
    public Review getReview(Hotel hotel, int reviewNumber) {
        Assert.notNull(hotel, "Hotel must not be null");
        return this.reviewRepository.findByHotelAndIndex(hotel, reviewNumber);
    }

    @Override
    public Review addReview(Hotel hotel, ReviewDetails details) {
        Review review = new Review(hotel, 1, details);
        return this.reviewRepository.save(review);
    }

    @Override
    public ReviewsSummary getReviewSummary(Hotel hotel) {
        List<RatingCount> ratingCounts = this.hotelRepository.findRatingCounts(hotel);
        return new ReviewsSummaryImpl(ratingCounts);
    }

    private static class ReviewsSummaryImpl implements ReviewsSummary {

        private final Map<Rating, Long> ratingCount;

        public ReviewsSummaryImpl(List<RatingCount> ratingCounts) {
            this.ratingCount = new HashMap<Rating, Long>();
            for (RatingCount ratingCount : ratingCounts) {
                this.ratingCount.put(ratingCount.getRating(), ratingCount.getCount());
            }
        }

        @Override
        public long getNumberOfReviewsWithRating(Rating rating) {
            Long count = this.ratingCount.get(rating);
            return count == null ? 0 : count;
        }
    }
}
