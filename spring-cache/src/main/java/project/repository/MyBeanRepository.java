package project.repository;

import org.apache.ignite.springdata.repository.IgniteRepository;
import org.apache.ignite.springdata.repository.config.Query;
import org.apache.ignite.springdata.repository.config.RepositoryConfig;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import project.bean.MyBean;

import javax.cache.Cache;
import java.util.List;

/**
 * Created by borde on 03/29/2018.
 */
@RepositoryConfig(cacheName = "customcache")
public interface MyBeanRepository extends IgniteRepository<MyBean, String> {
    /**
     * Returns top MyBean with the specified surname.
     * @param name MyBean surname.
     * @return MyBean that satisfy the query.
     */
    public Cache.Entry<Long, MyBean> findTopByField0Like(String name);

    /**
     * Getting ids of all the MyBean satisfying the custom query from {@link Query} annotation.
     *
     * @param orgId Query parameter.
     * @param pageable Pageable interface.
     * @return A list of MyBeans' ids.
     */
    @Query("SELECT id FROM MyBean WHERE id <> :ordId")
    public List<String> selectId(@Param("ordId") String orgId, Pageable pageable);
}
