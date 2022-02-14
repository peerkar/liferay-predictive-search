package fi.soveltia.liferay.autocomplete.rest.client.dto.v1_0;

import fi.soveltia.liferay.autocomplete.rest.client.function.UnsafeSupplier;
import fi.soveltia.liferay.autocomplete.rest.client.serdes.v1_0.AttributeSerDes;

import java.io.Serializable;

import java.util.Objects;

import javax.annotation.Generated;

/**
 * @author liferay
 * @generated
 */
@Generated("")
public class Attribute implements Cloneable, Serializable {

	public static Attribute toDTO(String json) {
		return AttributeSerDes.toDTO(json);
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public void setKey(UnsafeSupplier<String, Exception> keyUnsafeSupplier) {
		try {
			key = keyUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected String key;

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public void setValue(
		UnsafeSupplier<String, Exception> valueUnsafeSupplier) {

		try {
			value = valueUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected String value;

	@Override
	public Attribute clone() throws CloneNotSupportedException {
		return (Attribute)super.clone();
	}

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof Attribute)) {
			return false;
		}

		Attribute attribute = (Attribute)object;

		return Objects.equals(toString(), attribute.toString());
	}

	@Override
	public int hashCode() {
		String string = toString();

		return string.hashCode();
	}

	public String toString() {
		return AttributeSerDes.toJSON(this);
	}

}