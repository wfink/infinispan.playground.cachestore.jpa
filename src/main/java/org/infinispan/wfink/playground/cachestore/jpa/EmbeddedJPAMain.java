package org.infinispan.wfink.playground.cachestore.jpa;

import org.infinispan.Cache;
import org.infinispan.configuration.cache.Configuration;
import org.infinispan.configuration.cache.ConfigurationBuilder;
import org.infinispan.manager.DefaultCacheManager;
import org.infinispan.manager.EmbeddedCacheManager;
import org.infinispan.persistence.jpa.configuration.JpaStoreConfigurationBuilder;

public class EmbeddedJPAMain {
  private final EmbeddedCacheManager cacheManager;
  private final Cache<String, PersonEntity> personCache;

  public EmbeddedJPAMain() {
    cacheManager = new DefaultCacheManager(true);

    Configuration cacheConfig = new ConfigurationBuilder()
        // .availabilityInterval(1000)
        // .connectionAttempts(1).connectionInterval(5000)
        // .passivation(false)
        // .addStore(JpaStoreConfigurationBuilder.class)
        // .persistenceUnitName("rt").maxBatchSize(-1)
        // .storeMetadata(false)
        // .entityClass(Temp.class)
        // .async().enable().failSilently(true).modificationQueueSize(10000).threadPoolSize(5)
        // .shared(true)
        // .transactional(false)
        // .preload(cacheConf.preload())

        .persistence().addStore(JpaStoreConfigurationBuilder.class)
        .storeMetadata(false).maxBatchSize(-1)
        .persistenceUnitName("org.infinispan.wfink.playground.embedded.JPA")
        .entityClass(PersonEntity.class)
        .async().enable() // .failSilently(false).modificationQueueSize(10000).threadPoolSize(5)
        .build();
    cacheManager.createCache("personCache", cacheConfig);

    personCache = cacheManager.getCache("personCache");
  }

  private void stop() {
    this.personCache.shutdown();
    this.personCache.stop();
    this.cacheManager.stop();
  }

  private void createPerson() {
    System.out.println("put person wfink");
    personCache.put("wfink", new PersonEntity("wfink", "Wolf", "Fink"));
  }

  private void deletePerson() {
    System.out.println("delete person wfink");
    personCache.remove("wfink");
  }

  private void readPerson() {
    System.out.println("read person wfink : " + personCache.get("wfink"));
  }

  public static void main(String[] args) {
    final EmbeddedJPAMain main = new EmbeddedJPAMain();

    // main.createPerson();
    main.readPerson();
    main.deletePerson();
    main.readPerson();
    main.stop();
  }

}
