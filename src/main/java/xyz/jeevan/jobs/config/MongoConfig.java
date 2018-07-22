package xyz.jeevan.jobs.config;

import com.mongodb.MongoClient;
import cz.jirutka.spring.embedmongo.EmbeddedMongoFactoryBean;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;

@Configuration
public class MongoConfig {

  @Value("${application.mongo.host}")
  private String host;

  @Value("${application.mongo.database}")
  private String database;

  @Value("${application.mongo.port}")
  private int port;

  @Bean
  public MongoTemplate mongoTemplate() throws IOException {
    EmbeddedMongoFactoryBean mongo = new EmbeddedMongoFactoryBean();
    mongo.setBindIp(host);
    mongo.setPort(port);
    MongoClient mongoClient = mongo.getObject();
    MongoTemplate mongoTemplate = new MongoTemplate(mongoClient, database);
    return mongoTemplate;
  }

}
