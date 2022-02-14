package fi.soveltia.liferay.autocomplete.rest.client.serdes.v1_0;

import fi.soveltia.liferay.autocomplete.rest.client.dto.v1_0.Suggestion;
import fi.soveltia.liferay.autocomplete.rest.client.dto.v1_0.Suggestions;
import fi.soveltia.liferay.autocomplete.rest.client.json.BaseJSONParser;

import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.TreeMap;
import java.util.stream.Stream;

import javax.annotation.Generated;

/**
 * @author liferay
 * @generated
 */
@Generated("")
public class SuggestionsSerDes {

	public static Suggestions toDTO(String json) {
		SuggestionsJSONParser suggestionsJSONParser =
			new SuggestionsJSONParser();

		return suggestionsJSONParser.parseToDTO(json);
	}

	public static Suggestions[] toDTOs(String json) {
		SuggestionsJSONParser suggestionsJSONParser =
			new SuggestionsJSONParser();

		return suggestionsJSONParser.parseToDTOs(json);
	}

	public static String toJSON(Suggestions suggestions) {
		if (suggestions == null) {
			return "null";
		}

		StringBuilder sb = new StringBuilder();

		sb.append("{");

		if (suggestions.getSuggestions() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"suggestions\": ");

			sb.append("[");

			for (int i = 0; i < suggestions.getSuggestions().length; i++) {
				sb.append(String.valueOf(suggestions.getSuggestions()[i]));

				if ((i + 1) < suggestions.getSuggestions().length) {
					sb.append(", ");
				}
			}

			sb.append("]");
		}

		sb.append("}");

		return sb.toString();
	}

	public static Map<String, Object> toMap(String json) {
		SuggestionsJSONParser suggestionsJSONParser =
			new SuggestionsJSONParser();

		return suggestionsJSONParser.parseToMap(json);
	}

	public static Map<String, String> toMap(Suggestions suggestions) {
		if (suggestions == null) {
			return null;
		}

		Map<String, String> map = new TreeMap<>();

		if (suggestions.getSuggestions() == null) {
			map.put("suggestions", null);
		}
		else {
			map.put(
				"suggestions", String.valueOf(suggestions.getSuggestions()));
		}

		return map;
	}

	public static class SuggestionsJSONParser
		extends BaseJSONParser<Suggestions> {

		@Override
		protected Suggestions createDTO() {
			return new Suggestions();
		}

		@Override
		protected Suggestions[] createDTOArray(int size) {
			return new Suggestions[size];
		}

		@Override
		protected void setField(
			Suggestions suggestions, String jsonParserFieldName,
			Object jsonParserFieldValue) {

			if (Objects.equals(jsonParserFieldName, "suggestions")) {
				if (jsonParserFieldValue != null) {
					suggestions.setSuggestions(
						Stream.of(
							toStrings((Object[])jsonParserFieldValue)
						).map(
							object -> SuggestionSerDes.toDTO((String)object)
						).toArray(
							size -> new Suggestion[size]
						));
				}
			}
		}

	}

	private static String _escape(Object object) {
		String string = String.valueOf(object);

		for (String[] strings : BaseJSONParser.JSON_ESCAPE_STRINGS) {
			string = string.replace(strings[0], strings[1]);
		}

		return string;
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

			Class<?> valueClass = value.getClass();

			if (value instanceof Map) {
				sb.append(_toJSON((Map)value));
			}
			else if (valueClass.isArray()) {
				Object[] values = (Object[])value;

				sb.append("[");

				for (int i = 0; i < values.length; i++) {
					sb.append("\"");
					sb.append(_escape(values[i]));
					sb.append("\"");

					if ((i + 1) < values.length) {
						sb.append(", ");
					}
				}

				sb.append("]");
			}
			else if (value instanceof String) {
				sb.append("\"");
				sb.append(_escape(entry.getValue()));
				sb.append("\"");
			}
			else {
				sb.append(String.valueOf(entry.getValue()));
			}

			if (iterator.hasNext()) {
				sb.append(", ");
			}
		}

		sb.append("}");

		return sb.toString();
	}

}