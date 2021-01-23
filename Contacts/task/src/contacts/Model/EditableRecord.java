package contacts.Model;

import java.util.List;

@SuppressWarnings("unused")
public interface EditableRecord {
    List<String> getEditableFieldNames();

    <T> void setFieldValue(String fieldName, T value);

    Object getFieldValue(String fieldName);

    String getFullName();
}
