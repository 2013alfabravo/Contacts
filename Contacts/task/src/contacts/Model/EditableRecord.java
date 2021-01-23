package contacts.Model;

import java.io.Serializable;
import java.util.List;

@SuppressWarnings("unused")
public interface EditableRecord extends Serializable {
    List<String> getEditableFieldNames();

    <T> void setFieldValue(String fieldName, T value);

    Object getFieldValue(String fieldName);

    String getFullName();
}
