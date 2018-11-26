package com.alushkja.rest.webservices.restfulwebservices.filtering;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import static com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter.*;

import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

@RestController
public class FilteringController {

	private MappingJacksonValue filtering(SomeBean someBean, SimpleBeanPropertyFilter filter) {

		FilterProvider filters = new SimpleFilterProvider().addFilter("SomeBean Filter", filter);

		MappingJacksonValue mapping = new MappingJacksonValue(someBean);

		mapping.setFilters(filters);

		return mapping;
	}

	private MappingJacksonValue filteringList(List<SomeBean> someBean, SimpleBeanPropertyFilter filter) {

		FilterProvider filters = new SimpleFilterProvider().addFilter("SomeBean Filter", filter);

		MappingJacksonValue mapping = new MappingJacksonValue(someBean);

		mapping.setFilters(filters);

		return mapping;
	}

	// dynamic filtering
	// field1, field2
	@GetMapping("/filtering")
	public MappingJacksonValue retrieveSomeBean() {

		SomeBean someBean = new SomeBean("value1", "value2", "value3");

		// dynamic filtering
		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("field1", "field2");

		MappingJacksonValue mapping = filtering(someBean, filter);

		return mapping;
	}

	// dynamic filtering
	// field 2, field 3
	@GetMapping("/filtering-list")
	public MappingJacksonValue retrieveListOfSomeBean() {
		List<SomeBean> list = Arrays.asList(new SomeBean("value1", "value2", "value3"),
				new SomeBean("value4", "value5", "value6"));

		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("field2", "field3");

		MappingJacksonValue mapping = filteringList(list, filter);

		return mapping;
	}

}
