package project.config;

import org.apache.ignite.Ignite;
import org.apache.ignite.IgniteException;
import org.apache.ignite.Ignition;
import org.apache.ignite.cache.CacheAtomicityMode;
import org.apache.ignite.configuration.BinaryConfiguration;
import org.apache.ignite.configuration.CacheConfiguration;
import org.apache.ignite.configuration.ConnectorConfiguration;
import org.apache.ignite.configuration.IgniteConfiguration;
import org.apache.ignite.spi.discovery.tcp.TcpDiscoverySpi;
import org.apache.ignite.spi.discovery.tcp.ipfinder.multicast.TcpDiscoveryMulticastIpFinder;
import org.apache.ignite.springdata.repository.config.EnableIgniteRepositories;
import org.apache.ignite.springdata.repository.support.IgniteRepositoryFactoryBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import project.bean.MyBean;

import java.util.Arrays;

/**
 * Created by borde on 03/28/2018.
 */
@Configuration
@EnableJpaRepositories
public class Config {

    @Value("${enableFilePersistence}")
    private boolean enableFilePersistence;
    @Value("${igniteConnectorPort}")
    private int igniteConnectorPort;
    @Value("${igniteServerPortRange}")
    private String igniteServerPortRange;
    @Value("${ignitePersistenceFilePath}")
    private String ignitePersistenceFilePath;

    @Bean
    public IgniteConfiguration igniteCfg() {
        IgniteConfiguration igniteConfiguration = new IgniteConfiguration();

        CacheConfiguration cacheConfig = new CacheConfiguration();
        cacheConfig.setName("default");
        cacheConfig.setAtomicityMode(CacheAtomicityMode.ATOMIC);
        cacheConfig.setBackups(1);
        cacheConfig.setIndexedTypes(String.class, MyBean.class);

        TcpDiscoverySpi tcpDiscoverySpi=new TcpDiscoverySpi();
        TcpDiscoveryMulticastIpFinder tcpDiscoveryVmIpFinder=new TcpDiscoveryMulticastIpFinder();
        // need to be changed when it come to real cluster
        tcpDiscoveryVmIpFinder.setAddresses(Arrays.asList("127.0.0.1:47500..47509"));
        tcpDiscoverySpi.setIpFinder(tcpDiscoveryVmIpFinder);
        igniteConfiguration.setDiscoverySpi(new TcpDiscoverySpi());


        ConnectorConfiguration connectorConfiguration=new ConnectorConfiguration();
        connectorConfiguration.setPort(igniteConnectorPort);
        // common ignite configuration
//        igniteConfiguration.setMetricsLogFrequency(0);
//        igniteConfiguration.setQueryThreadPoolSize(2);
//        igniteConfiguration.setDataStreamerThreadPoolSize(1);
//        igniteConfiguration.setManagementThreadPoolSize(2);
//        igniteConfiguration.setPublicThreadPoolSize(2);
//        igniteConfiguration.setSystemThreadPoolSize(2);
//        igniteConfiguration.setRebalanceThreadPoolSize(1);
//        igniteConfiguration.setAsyncCallbackPoolSize(2);
//        igniteConfiguration.setPeerClassLoadingEnabled(false);
//        igniteConfiguration.setIgniteInstanceName("alertsGrid");
//        BinaryConfiguration binaryConfiguration = new BinaryConfiguration();
//        binaryConfiguration.setCompactFooter(false);
//        igniteConfiguration.setBinaryConfiguration(binaryConfiguration);

//        igniteConfiguration.setPeerClassLoadingEnabled(true);
        igniteConfiguration.setCacheConfiguration(cacheConfig);
        igniteConfiguration.setDiscoverySpi(tcpDiscoverySpi);
        igniteConfiguration.setConnectorConfiguration(connectorConfiguration);

        return igniteConfiguration;
    }

    @Bean(destroyMethod = "close")
    public Ignite igniteInstance(IgniteConfiguration igniteConfiguration) throws IgniteException {
        final Ignite start = Ignition.start(igniteConfiguration);
        start.active(true);
        return start;
    }
}
