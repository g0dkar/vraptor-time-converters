package br.com.caelum.vraptor.converter;

import static com.google.common.base.Strings.isNullOrEmpty;

import java.time.Instant;
import java.time.format.DateTimeParseException;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import org.slf4j.Logger;

import br.com.caelum.vraptor.Convert;

@RequestScoped
@Convert(Instant.class)
public class InstantConverter implements Converter<Instant> {
	private final Logger log;
	
	/** @deprecated CDI */ @Deprecated
	InstantConverter() { this(null); }
	
	@Inject
	public InstantConverter(final Logger log) {
		this.log = log;
	}
	
	public Instant convert(final String value, final Class<? extends Instant> type) {
		if (log.isDebugEnabled()) {
			log.debug("Converting \"{}\" to Instant", value);
		}
		
		if (isNullOrEmpty(value)) {
			return null;
		}
		
		try {
			return Instant.parse(value);
		} catch (final DateTimeParseException dtpe) {
			log.error("Error while converting Instant", dtpe);
			throw new ConversionException(new ConversionMessage("is_not_a_valid_instant", value));
		}
	}
}
