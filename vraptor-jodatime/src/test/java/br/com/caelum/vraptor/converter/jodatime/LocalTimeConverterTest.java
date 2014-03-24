package br.com.caelum.vraptor.converter.jodatime;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;

import java.util.Locale;

import org.joda.time.LocalTime;
import org.junit.Before;
import org.junit.Test;

import br.com.caelum.vraptor.converter.ConversionException;

/**
 * Tests to {@link LocalTimeConverter}.
 */
public class LocalTimeConverterTest {

	private LocalTimeConverter converter;

	@Before
	public void setup() {
		converter = new LocalTimeConverter(new Locale("pt", "BR"));
	}

	@Test
	public void shouldBeAbleToConvert() {
		assertThat(converter.convert("15:38", LocalTime.class),
				is(equalTo(new LocalTime(15, 38))));
	}

	@Test
	public void shouldBeAbleToConvertEmpty() {
		assertThat(converter.convert("", LocalTime.class), is(nullValue()));
	}

	@Test
	public void shouldBeAbleToConvertNull() {
		assertThat(converter.convert(null, LocalTime.class), is(nullValue()));
	}

	@Test
	public void shouldThrowExceptionWhenUnableToParse() {
		try {
			converter.convert("xx:yy:ff", LocalTime.class);
		} catch (ConversionException e) {
			e.getValidationMessage().setBundle(new MockResourceBundle());
			assertThat(e.getValidationMessage().getMessage(), is("is_not_a_valid_time"));
		}
	}
}