package org.openl.rules.webstudio.web.test;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

import org.openl.base.INamedThing;
import org.openl.types.IOpenClass;
import org.openl.types.IOpenField;
import org.openl.types.java.JavaOpenClass;
import org.richfaces.model.TreeNode;

public class MapParameterTreeNode extends CollectionParameterTreeNode {
    public MapParameterTreeNode(String fieldName,
            Object value,
            IOpenClass fieldType,
            ParameterDeclarationTreeNode parent, IOpenField previewField, boolean hasExplainLinks) {
        super(fieldName, value, fieldType, parent, previewField, hasExplainLinks);
    }

    @Override
    protected Object getKeyFromElementNum(int elementNum) {
        if (elementNum >= getChildren().size()) {
            return null;
        }
        return getChild(elementNum).getChild("key").getValue();
    }

    @Override
    protected LinkedHashMap<Object, ParameterDeclarationTreeNode> initChildrenMap() {
        if (isValueNull()) {
            return new LinkedHashMap<>();
        } else {
            IOpenClass collectionElementType = getType().getComponentClass();
            LinkedHashMap<Object, ParameterDeclarationTreeNode> elements = new LinkedHashMap<>();
            Map<Object, Object> map = getMap();
            int i = 0;
            for (Map.Entry<Object, Object> entry : map.entrySet()) {
                elements.put(i, createNode(entry.getKey(), entry.getValue()));
                i++;
            }
            return elements;
        }
    }

    @Override
    public void addChild(Object elementNum, TreeNode element) {
        Object value = element == null ? null : ((ParameterDeclarationTreeNode) element).getValue();
        LinkedHashMap<Object, ParameterDeclarationTreeNode> childrenMap = getChildernMap();
        childrenMap.put(childrenMap.size(), createNode(null, value));
    }

    @Override
    public void removeChild(ParameterDeclarationTreeNode toDelete) {
        for (Iterator<ParameterDeclarationTreeNode> iterator = getChildren().iterator(); iterator.hasNext(); ) {
            ParameterDeclarationTreeNode node = iterator.next();
            if (node == toDelete) {
                Entry value = (Entry) node.getValue();
                if (value != null) {
                    getMap().remove(value.getKey());
                }
                iterator.remove();
                break;
            }
        }
    }

    @Override
    protected ParameterDeclarationTreeNode createNode(Object key, Object value) {
        Entry element = new Entry(getMap(), key, value);
        return ParameterTreeBuilder.createNode(JavaOpenClass.getOpenClass(element.getClass()), element, previewField, null, this, hasExplainLinks);
    }

    @Override
    protected Object getNodeValue(ParameterDeclarationTreeNode node) {
        return ((Entry) node.getValue()).getValue();
    }

    @SuppressWarnings("unchecked")
    private Map<Object, Object> getMap() {
        return (Map<Object, Object>) getValue();
    }

    public static class Entry {
        private final Map<Object, Object> map;
        private Object key;
        private Object value;

        // Dummy constructor for UI
        public Entry() {
            map = new HashMap<>();
        }

        public Entry(Map<Object, Object> map, Object key, Object value) {
            this.map = map;
            this.key = key;
            this.value = value;
        }

        public Object getKey() {
            return key;
        }

        public void setKey(Object key) {
            // Remap value for old key to the new one
            Object value = this.key == null ? null : map.remove(this.key);
            if (value != null) {
                map.put(key, value);
            }

            this.key = key;
        }

        public Object getValue() {
            return value;
        }

        public void setValue(Object value) {
            this.value = value;

            if (key != null) {
                map.put(key, value);
            }
        }
    }
}