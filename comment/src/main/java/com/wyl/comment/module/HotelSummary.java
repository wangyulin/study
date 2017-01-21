package com.wyl.comment.module;

/**
 * Created by wangyulin on 20/01/2017.
 */
public interface HotelSummary {

    City getCity();

    String getName();

    Double getAverageRating();

    default Integer getAverageRatingRounded() {
        return getAverageRating() == null ? null : (int) Math.round(getAverageRating());
    }

}
