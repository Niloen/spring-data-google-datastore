/*
 * Copyright 2015-2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.niloen.spring.data.google.datastore.repository;

import com.niloen.spring.data.google.datastore.repository.configuration.EnableGoogleDatastoreRepositories;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Reference;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Point;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class GoogleDatastoreRepositoryIntegrationTests {

	@Autowired
	PersonRepository repo;

	@Configuration
	@EnableGoogleDatastoreRepositories(considerNestedRepositories = true, includeFilters = {
					@ComponentScan.Filter(type = FilterType.REGEX, pattern = ".*PersonRepository|.*CityRepository") })
	static class Config {
	}

	public interface PersonRepository extends PagingAndSortingRepository<Person, String> {
		List<Person> findByFirstname(String firstname);
	}

	@lombok.Data
	public static class Person implements Serializable {

		@Id
		String id;

		String firstname;
		String lastname;

	}
	@Test
	public void simpleFindShouldReturnEntitiesCorrectly() {

		Person rand = new Person();
		rand.firstname = "rand";
		rand.lastname = "al'thor";

		Person egwene = new Person();
		egwene.firstname = "egwene";

		repo.save(Arrays.asList(rand, egwene));

		assertThat(repo.count(), is(2L));

		assertThat(repo.findOne(rand.id), is(rand));
		assertThat(repo.findOne(egwene.id), is(egwene));

		assertThat(repo.findByFirstname("rand").size(), is(1));
		assertThat(repo.findByFirstname("rand"), hasItem(rand));

		assertThat(repo.findByFirstname("egwene").size(), is(1));
		assertThat(repo.findByFirstname("egwene"), hasItem(egwene));
	}
}
