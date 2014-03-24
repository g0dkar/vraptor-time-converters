package br.com.caelum.vraptor.converter.jodatime;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;

import java.util.Locale;

import org.joda.time.LocalDate;
import org.junit.Before;
import org.junit.Test;

import br.com.caelum.vraptor.converter.ConversionException;

/**
 * Tests to {@link LocalDateConverter}.
 */
public class LocalDateConverterTest {

	private LocalDateConverter converter;

	@Before
	public void setup() {
		converter = new LocalDateConverter(new Locale("pt", "BR"));
	}

	@Test
	public void shouldBeAbleToConvert() {
		assertThat(converter.convert("05/06/2010", LocalDate.class),
				is(equalTo(new LocalDate(2010, 6, 5))));
	}

	@Test
	public void shouldBeAbleToConvertEmpty() {
		assertThat(converter.convert("", LocalDate.class), is(nullValue()));
	}

	@Test
	public void shouldBeAbleToConvertNull() {
		assertThat(converter.convert(null, LocalDate.class), is(nullValue()));
	}

	@Test
	public void shouldThrowExceptionWhenUnableToParse() {
		try {
			converter.convert("a,10/06/2008/a/b/c", LocalDate.class);
		} catch (ConversionException e) {
			e.getValidationMessage().setBundle(new MockResourceBundle());
			assertThat(e.getValidationMessage().getMessage(), is("is_not_a_valid_date"));
		}
	}
}