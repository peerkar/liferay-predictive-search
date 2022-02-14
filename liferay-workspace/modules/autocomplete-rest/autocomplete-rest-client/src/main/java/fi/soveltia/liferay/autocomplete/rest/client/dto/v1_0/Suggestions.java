package fi.soveltia.liferay.autocomplete.rest.client.dto.v1_0;

import fi.soveltia.liferay.autocomplete.rest.client.dto.v1_0.Suggestion;
import fi.soveltia.liferay.autocomplete.rest.client.function.UnsafeSupplier;
import fi.soveltia.liferay.autocomplete.rest.client.serdes.v1_0.SuggestionsSerDes;

import java.io.Serializable;

import java.util.Objects;

import javax.annotation.Generated;

/**
 * @author liferay
 * @generated
 */
@Generated("")
public class Suggestions implements Cloneable, Serializable {

	public static Suggestions toDTO(String json) {
		return SuggestionsSerDes.toDTO(json);
	}

	public Suggestion[] getSuggestions() {
		return suggestions;
	}

	public void setSuggestions(Suggestion[] suggestions) {
		this.suggestions = suggestions;
	}

	public void setSuggestions(
		UnsafeSupplier<Suggestion[], Exception> suggestionsUnsafeSupplier) {

		try {
			suggestions = suggestionsUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected Suggestion[] suggestions;

	@Override
	public Suggestions clone() throws CloneNotSupportedException {
		return (Suggestions)super.clone();
	}

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof Suggestions)) {
			return false;
		}

		Suggestions suggestions = (Suggestions)object;

		return Objects.equals(toString(), suggestions.toString());
	}

	@Override
	public int hashCode() {
		String string = toString();

		return string.hashCode();
	}

	public String toString() {
		return SuggestionsSerDes.toJSON(this);
	}

}