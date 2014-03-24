package br.com.caelum.vraptor.converter.jodatime;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;

import java.util.Locale;

import org.joda.time.LocalDateTime;
import org.junit.Before;
import org.junit.Test;

import br.com.caelum.vraptor.converter.ConversionException;

/**
 * Tests to {@link LocalDateTimeConverter}.
 */
public class LocalDateTimeConverterTest {

	private LocalDateTimeConverter converter;

	@Before
	public void setup() {
		converter = new LocalDateTimeConverter(new Locale("pt", "BR"));
	}

	@Test
	public void shouldBeAbleToConvert() {
		assertThat(converter.convert("05/06/2010 3:38", LocalDateTime.class),
				is(equalTo(new LocalDateTime(2010, 6, 5, 3, 38))));
	}

	@Test
	public void shouldBeAbleToConvertEmpty() {
		assertThat(converter.convert("", LocalDateTime.class), is(nullValue()));
	}

	@Test
	public void shouldBeAbleToConvertNull() {
		assertThat(converter.convert(null, LocalDateTime.class), is(nullValue()));
	}

	@Test
	public void shouldThrowExceptionWhenUnableToParse() {
		try {
			converter.convert("a,10/06/2008/a/b/c", LocalDateTime.class);
		} catch (ConversionException e) {
			e.getValidationMessage().setBundle(new MockResourceBundle());
			assertThat(e.getValidationMessage().getMessage(), is("is_not_a_valid_datetime"));
		}
	}
}