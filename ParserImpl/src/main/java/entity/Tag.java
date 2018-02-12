//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package entity;

import entity.EnumClass.Type;

public class Tag implements ITag {
    public Type type;
    public String content;

    public Tag() {
    }

    public Type getType() {
        return this.type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (o != null && this.getClass() == o.getClass()) {
            Tag tag = (Tag)o;
            return this.type != tag.type ? false : this.content.equals(tag.content);
        } else {
            return false;
        }
    }

    public int hashCode() {
        int result = this.type.hashCode();
        result = 31 * result + this.content.hashCode();
        return result;
    }

    public String toString() {
        return "{Type: " + this.type + ", content= " + this.content + "}";
    }
}
