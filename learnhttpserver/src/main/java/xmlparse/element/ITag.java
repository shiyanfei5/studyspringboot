package xmlparse.element;

import java.util.List;

public interface ITag {
    String getValue();
    String getSons();
    String getSon(int index);
    String getAttribute(String attributeName);
    void setValue(String value);
    void setSon( ITag son);
    void setAttribute(String attributeName,String value );


}
