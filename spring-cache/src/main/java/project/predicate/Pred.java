package project.predicate;

import org.apache.ignite.lang.IgniteBiPredicate;
import project.bean.MyBean;

/**
 * Created by borde on 03/29/2018.
 */
public class Pred implements IgniteBiPredicate<String, MyBean> {

    @Override
    public boolean apply(String s, MyBean myBean) {
        return !myBean.getId().equals("") ;
    }
}