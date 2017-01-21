package spittr.data;

import spittr.Spittle;

import java.util.List;

/**
 * Created by wangyulin on 17/01/2017.
 */


public interface SpittleRepository {

    List<Spittle> findSpittles(long max, int count);

}
