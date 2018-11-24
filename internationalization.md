## Internationalization

#### Configuration 
- LocaleResolver
	- Default Locale - Locale.US
- ResourceBundleMessageResource

#### Usage
- Autowire MessageSource
- @RequestHeader(value = "Accept-Language", required = false) Locale locale
- messageSource.getMessage("helloWorld.message", null, locale)
