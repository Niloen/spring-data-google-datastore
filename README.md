Spring Data Google Datastore
=======================

The primary goal of the [Spring Data](http://projects.spring.io/spring-data/) project is to make it easier to build Spring-powered applications that use new data access technologies such as non-relational databases, map-reduce frameworks, and cloud based data services.
This modules provides integration with [Google Datastore](https://cloud.google.com/datastore).

**This is a work in progress**

# Examples

For examples on using the Spring Data Key Value, see the dedicated project, also available on [GitHub](https://github.com/spring-projects/spring-data-keyvalue-examples)

## Repository Support

	@Configuration
	@EnableGoogleDatastoreRepositories
	public class ApplicationConfig {
	}
