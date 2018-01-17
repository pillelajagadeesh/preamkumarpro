package appraamlabs.configs;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Value;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.WriteConcern;

@Configuration
@EnableMongoRepositories(basePackages = "appraamlabs.repositories")
public class MongoConfig extends AbstractMongoConfiguration {

 @Value("${mongodb.url}")
 private String url;

 @Value("${mongodb.database}")
 private String databaseName;

 @Value("${mongodb.port}")
 private int port;
 
 /*@Value("${mongodb.username}")
 private String username;
 
 @Value("${mongodb.password}")
 private String password;*/
 
 @Override
 protected String getDatabaseName() {
  return databaseName;
 }
 
 public @Bean Mongo mongo() throws Exception {
     //return new Mongo(this.url, this.port);
	 return new MongoClient(this.url, this.port);
/*	 MongoCredential mongoCredential = MongoCredential.createMongoCRCredential( this.username, this.databaseName, this.password.toCharArray());
	 mongoCredential.getAuthenticationMechanism();
	 return new MongoClient(new ServerAddress(
				url, port),
				Arrays.asList(mongoCredential));*/
 }

 @Override
 protected String getMappingBasePackage() {
  return "appraamlabs.models";
 }

 public @Bean MongoTemplate mongoTemplate() throws Exception {
	 //final UserCredentials userCredentials = new UserCredentials(this.username, this.password);
      MongoTemplate mongoTemplate = new MongoTemplate(mongo(),this.databaseName);
      mongoTemplate.setWriteConcern(WriteConcern.SAFE);
     return mongoTemplate;
 }
 
/* @Bean
 public MongoDbFactory mongoDbFactory() throws Exception {

     // Set credentials
     MongoCredential credential = MongoCredential.createCredential(this.username, this.databaseName, this.password.toCharArray());
    ServerAddress serverAddress = new ServerAddress(this.url, this.port);

     // Mongo Client
     MongoClient mongoClient = new MongoClient(serverAddress,Arrays.asList(credential)); 

     // Mongo DB Factory
     SimpleMongoDbFactory simpleMongoDbFactory = new SimpleMongoDbFactory(
             mongoClient, this.databaseName);

     return simpleMongoDbFactory;
 }

 @Bean
 public MongoTemplate mongoTemplate() throws Exception {
     return new MongoTemplate(mongoDbFactory());
 }*/
}
