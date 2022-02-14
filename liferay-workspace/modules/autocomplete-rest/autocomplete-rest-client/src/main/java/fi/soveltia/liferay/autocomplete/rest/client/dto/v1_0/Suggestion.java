package fi.soveltia.liferay.autocomplete.rest.client.dto.v1_0;

import fi.soveltia.liferay.autocomplete.rest.client.dto.v1_0.Attribute;
import fi.soveltia.liferay.autocomplete.rest.client.function.UnsafeSupplier;
import fi.soveltia.liferay.autocomplete.rest.client.serdes.v1_0.SuggestionSerDes;

import java.io.Serializable;

import java.util.Objects;

import javax.annotation.Generated;

/**
 * @author liferay
 * @generated
 */
@Generated("")
public class Suggestion implements Cloneable, Serializable {

	public static Suggestion toDTO(String json) {
		return SuggestionSerDes.toDTO(json);
	}

	public Attribute[] getAttributes() {
		return attributes;
	}

	public void setAttributes(Attribute[] attributes) {
		this.attributes = attributes;
	}

	public void setAttributes(
		UnsafeSupplier<Attribute[], Exception> attributesUnsafeSupplier) {

		try {
			attributes = attributesUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected Attribute[] attributes;

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public void setText(UnsafeSupplier<String, Exception> textUnsafeSupplier) {
		try {
			text = textUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected String text;

	@Override
	public Suggestion clone() throws CloneNotSupportedException {
		return (Suggestion)super.clone();
	}

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof Suggestion)) {
			return false;
		}

		Suggestion suggestion = (Suggestion)object;

		return Objects.equals(toString(), suggestion.toString());
	}

	@Override
	public int hashCode() {
		String string = toString();

		return string.hashCode();
	}

	public String toString() {
		return SuggestionSerDes.toJSON(this);
	}

}