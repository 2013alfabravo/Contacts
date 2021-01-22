package contacts;

import java.util.List;

public interface EditableRecord {
    List<String> getEditableFieldNames();
    <T> void setFieldValue(String fieldName, T value);
    Object getFieldValue(String fieldName);
}
