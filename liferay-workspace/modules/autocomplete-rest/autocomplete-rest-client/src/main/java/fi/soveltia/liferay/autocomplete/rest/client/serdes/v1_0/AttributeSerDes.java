package fi.soveltia.liferay.autocomplete.rest.client.serdes.v1_0;

import fi.soveltia.liferay.autocomplete.rest.client.dto.v1_0.Attribute;
import fi.soveltia.liferay.autocomplete.rest.client.json.BaseJSONParser;

import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.TreeMap;

import javax.annotation.Generated;

/**
 * @author liferay
 * @generated
 */
@Generated("")
public class AttributeSerDes {

	public static Attribute toDTO(String json) {
		AttributeJSONParser attributeJSONParser = new AttributeJSONParser();

		return attributeJSONParser.parseToDTO(json);
	}

	public static Attribute[] toDTOs(String json) {
		AttributeJSONParser attributeJSONParser = new AttributeJSONParser();

		return attributeJSONParser.parseToDTOs(json);
	}

	public static String toJSON(Attribute attribute) {
		if (attribute == null) {
			return "null";
		}

		StringBuilder sb = new StringBuilder();

		sb.append("{");

		if (attribute.getKey() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"key\": ");

			sb.append("\"");

			sb.append(_escape(attribute.getKey()));

			sb.append("\"");
		}

		if (attribute.getValue() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"value\": ");

			sb.append("\"");

			sb.append(_escape(attribute.getValue()));

			sb.append("\"");
		}

		sb.append("}");

		return sb.toString();
	}

	public static Map<String, Object> toMap(String json) {
		AttributeJSONParser attributeJSONParser = new AttributeJSONParser();

		return attributeJSONParser.parseToMap(json);
	}

	public static Map<String, String> toMap(Attribute attribute) {
		if (attribute == null) {
			return null;
		}

		Map<String, String> map = new TreeMap<>();

		if (attribute.getKey() == null) {
			map.put("key", null);
		}
		else {
			map.put("key", String.valueOf(attribute.getKey()));
		}

		if (attribute.getValue() == null) {
			map.put("value", null);
		}
		else {
			map.put("value", String.valueOf(attribute.getValue()));
		}

		return map;
	}

	public static class AttributeJSONParser extends BaseJSONParser<Attribute> {

		@Override
		protected Attribute createDTO() {
			return new Attribute();
		}

		@Override
		protected Attribute[] createDTOArray(int size) {
			return new Attribute[size];
		}

		@Override
		protected void setField(
			Attribute attribute, String jsonParserFieldName,
			Object jsonParserFieldValue) {

			if (Objects.equals(jsonParserFieldName, "key")) {
				if (jsonParserFieldValue != null) {
					attribute.setKey((String)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "value")) {
				if (jsonParserFieldValue != null) {
					attribute.setValue((String)jsonParserFieldValue);
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