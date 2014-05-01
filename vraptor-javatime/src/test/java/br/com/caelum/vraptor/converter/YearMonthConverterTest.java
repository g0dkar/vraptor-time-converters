package br.com.caelum.vraptor.converter;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;

import java.time.YearMonth;
import java.util.Locale;

import org.junit.Before;
import org.junit.Test;

/**
 * Tests to {@link YearMonthConverter}.
 */
public class YearMonthConverterTest {

	private YearMonthConverter converter;

	@Before
	public void setup() {
		converter = new YearMonthConverter(new Locale("pt", "BR"));
	}

	@Test
	public void shouldBeAbleToConvert() {
		assertThat(converter.convert("01/06/2007", YearMonth.class), is(equalTo(YearMonth.of(2007, 6))));
	}

	@Test
	public void shouldBeAbleToConvertEmpty() {
		assertThat(converter.convert("", YearMonth.class), is(nullValue()));
	}

	@Test
	public void shouldBeAbleToConvertNull() {
		assertThat(converter.convert(null, YearMonth.class), is(nullValue()));
	}

	@Test
	public void shouldThrowExceptionWhenUnableToParse() {
		try {
			converter.convert("aa/xx/pppp", YearMonth.class);
		} catch (ConversionException e) {
			e.getValidationMessage().setBundle(new MockResourceBundle());
			assertThat(e.getValidationMessage().getMessage(), is("is_not_a_valid_yearmonth"));
		}
	}
}