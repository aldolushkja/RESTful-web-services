package com.alushkja.rest.webservices.restfulwebservices.helloworld;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

//Controller
@RestController
public class HelloWorldController {

	@Autowired
	private MessageSource messageSource;

	// GET
	// URI - /hello-world
	// method - "Hello World"
//	@RequestMapping(method=RequestMethod.GET, path="/hello-world")
	@GetMapping(path = "/hello-world")
	public String helloWorld() {
		return "Hello world";
	}

	// GET
	// URI - /hello-world-bean
	@GetMapping(path = "/hello-world-bean")
	public HelloWorldBean helloWorldBean() {
		return new HelloWorldBean("Hello world");
	}

	// URI - /hello-world/path-variable/firetaurus
	@GetMapping(path = "/hello-world/path-variable/{name}")
	public HelloWorldBean helloWorldPathVariable(@PathVariable String name) {
		return new HelloWorldBean(String.format("Hello world, %s", name));
	}

	// INTERNATIONALIZATION PT.1
//	@GetMapping(path = "/hello-world-internationalized")
//	public String helloWorldInternationalized(
//			@RequestHeader(name = "Accept-Language", required = false) Locale locale) {
//		return messageSource.getMessage("good.morning.message", null, locale);
//	}
	// INTERNATIONALIZATION PT.2
	@GetMapping(path = "/hello-world-internationalized")
	public String helloWorldInternationalized() {
		return messageSource.getMessage("good.morning.message", null,  LocaleContextHolder.getLocale());
	}
}
