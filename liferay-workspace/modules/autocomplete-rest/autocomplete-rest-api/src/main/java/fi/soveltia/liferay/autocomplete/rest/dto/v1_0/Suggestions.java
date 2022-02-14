package fi.soveltia.liferay.autocomplete.rest.dto.v1_0;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import com.liferay.petra.function.UnsafeSupplier;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.vulcan.graphql.annotation.GraphQLField;
import com.liferay.portal.vulcan.graphql.annotation.GraphQLName;
import com.liferay.portal.vulcan.util.ObjectMapperUtil;

import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serializable;

import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import javax.annotation.Generated;

import javax.validation.Valid;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author liferay
 * @generated
 */
@Generated("")
@GraphQLName(description = "Typeahead", value = "Suggestions")
@JsonFilter("Liferay.Vulcan")
@XmlRootElement(name = "Suggestions")
public class Suggestions implements Serializable {

	public static Suggestions toDTO(String json) {
		return ObjectMapperUtil.readValue(Suggestions.class, json);
	}

	public static Suggestions unsafeToDTO(String json) {
		return ObjectMapperUtil.unsafeReadValue(Suggestions.class, json);
	}

	@Schema
	@Valid
	public Suggestion[] getSuggestions() {
		return suggestions;
	}

	public void setSuggestions(Suggestion[] suggestions) {
		this.suggestions = suggestions;
	}

	@JsonIgnore
	public void setSuggestions(
		UnsafeSupplier<Suggestion[], Exception> suggestionsUnsafeSupplier) {

		try {
			suggestions = suggestionsUnsafeSupplier.get();
		}
		catch (RuntimeException re) {
			throw re;
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@GraphQLField
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	protected Suggestion[] suggestions;

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
		StringBundler sb = new StringBundler();

		sb.append("{");

		if (suggestions != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"suggestions\": ");

			sb.append("[");

			for (int i = 0; i < suggestions.length; i++) {
				sb.append(String.valueOf(suggestions[i]));

				if ((i + 1) < suggestions.length) {
					sb.append(", ");
				}
			}

			sb.append("]");
		}

		sb.append("}");

		return sb.toString();
	}

	@Schema(
		accessMode = Schema.AccessMode.READ_ONLY,
		defaultValue = "fi.soveltia.liferay.autocomplete.rest.dto.v1_0.Suggestions",
		name = "x-class-name"
	)
	public String xClassName;

	private static String _escape(Object object) {
		String string = String.valueOf(object);

		return string.replaceAll("\"", "\\\\\"");
	}

	private static boolean _isArray(Object value) {
		if (value == null) {
			return false;
		}

		Class<?> clazz = value.getClass();

		return clazz.isArray();
	}

	private static String _toJSON(Map<String, ?> map) {
		StringBuilder sb = new StringBuilder("{");

		@SuppressWarnings("unchecked")
		Set set = map.entrySet();

		@SuppressWarnings("unchecked")
		Iterator<Map.Entry<String, ?>> iterator = set.iterator();

		while (iterator.hasNext()) {
			Map.Entry<String, ?> entry = iterator.next();

			sb.append("\"");
			sb.append(entry.getKey());
			sb.append("\": ");

			Object value = entry.getValue();

			if (_isArray(value)) {
				sb.append("[");

				Object[] valueArray = (Object[])value;

				for (int i = 0; i < valueArray.length; i++) {
					if (valueArray[i] instanceof String) {
						sb.append("\"");
						sb.append(valueArray[i]);
						sb.append("\"");
					}
					else {
						sb.append(valueArray[i]);
					}

					if ((i + 1) < valueArray.length) {
						sb.append(", ");
					}
				}

				sb.append("]");
			}
			else if (value instanceof Map) {
				sb.append(_toJSON((Map<String, ?>)value));
			}
			else if (value instanceof String) {
				sb.append("\"");
				sb.append(value);
				sb.append("\"");
			}
			else {
				sb.append(value);
			}

			if (iterator.hasNext()) {
				sb.append(", ");
			}
		}

		sb.append("}");

		return sb.toString();
	}

}