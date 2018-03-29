package project.work;

import org.apache.ignite.cache.query.ScanQuery;
import org.apache.ignite.lang.IgniteBiPredicate;
import org.apache.ignite.lang.IgniteClosure;
import project.bean.MyBean;
import lombok.extern.slf4j.Slf4j;
import org.apache.ignite.Ignite;
import org.apache.ignite.IgniteCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import project.repository.MyBeanRepository;

import javax.cache.Cache;
import java.util.List;

/**
 * Created by borde on 03/28/2018.
 */
@Component
@Slf4j
public class Executor {

//    @Autowired
//    private Ignite ignite;

    @Autowired
    private MyBeanRepository myBeanRepository;

    @Scheduled(fixedDelay = 10000)
    public void executeIgniteAction() {

        log.info("running the insert into cache");


        MyBean mbList = myBeanRepository.findOne("288fb222-a953-4748-bc15-1f430df9a2c4");

        System.out.println(mbList);




        //ignite.destroyCache("customcache");

//        IgniteCache<String, MyBean> cache = ignite.getOrCreateCache("customcache");

//        Iterator<Cache.Entry<String, MyBean>> iter = cache.iterator();
//
//        while (iter.hasNext()) {
//            System.out.println(iter.next().getKey());
//        }


//        List<String> keys = cache.query(new ScanQuery<>(
//                        // Remote filter.
//                        new IgniteBiPredicate<String, MyBean>() {
//                            @Override public boolean apply(String k, MyBean p) {
//                                return p.getId().equals("288fb222-a953-4748-bc15-1f430df9a2c4");
//                            }
//                        }),
//                // Transformer.
//                new IgniteClosure<Cache.Entry<String, MyBean>, String>() {
//                    @Override public String apply(Cache.Entry<String, MyBean> e) {
//                        return e.getKey();
//                    }
//                }
//        ).getAll();
//
//
//        System.out.println(keys);


//        MyBean mb = cache.get("288fb222-a953-4748-bc15-1f430df9a2c4");
//        System.out.println(mb);

//        288fb222-a953-4748-bc15-1f430df9a2c4
//        af16219f-6f2b-46bb-bb38-76579b5c93e5
//        9f2ed589-fb34-41bd-960c-072b22648067
//        9850f0da-bb69-4495-aeed-1cfa9f22bd46
//        1fb370d8-7afc-47c1-ac0c-b327f380341c


//        for (int i = 0; i < 2000000; i++) {
//
//            if (i % 1000 == 0) {
//                log.info("inserted until now " + i);
//            }
//
//            MyBean bean = new MyBean();
//            String key = UUID.randomUUID().toString();
//            bean.setId(key);
//
//            cache.put(key, bean);
//        }
    }




}
