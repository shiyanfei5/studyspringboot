package xmlparse.element;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Tag implements ITag {

    private String name;
    private String value;   //节点的值
    private Map<String, String> attributeMap; //属性地图
    private List<Tag> sons; //儿子节点

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Map<String, String> getAttributeMap() {
        return attributeMap;
    }

    public void setAttributeMap(Map<String, String> attributeMap) {
        this.attributeMap = attributeMap;
    }

    public List<Tag> getSons() {
        return sons;
    }

    public void setSons(List<Tag> sons) {
        this.sons = sons;
    }

    public void addSon(Tag son){
        if(sons == null ){
            sons = new ArrayList<Tag>();
        }
        sons.add(son);
    }


}
