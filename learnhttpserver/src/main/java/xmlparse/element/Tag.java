package xmlparse.element;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Tag implements ITag {
    private String name;



    private String value;   //节点的值
    private Map<String, String> attributeMap; //属性地图
    private List<ITag> sons; //儿子节点

    @Override
    public String getValue() {
        return null;
    }

    @Override
    public String getSons() {
        return null;
    }

    @Override
    public String getSon(int index) {
        return null;
    }

    @Override
    public String getAttribute(String attributeName){
        return attributeMap.get(attributeName);
    }

    @Override
    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public void setSon(ITag son) {
        if(sons == null ){
            sons = new ArrayList<ITag>();
        }
        sons.add(son);
    }

    @Override
    public void setAttribute(String attributeName, String value) {
        this.attributeMap.put(attributeName,value);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
