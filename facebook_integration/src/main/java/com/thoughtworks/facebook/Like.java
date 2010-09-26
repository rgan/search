package com.thoughtworks.facebook;

public class Like {
    private String name;
    private String category;

    public Like(String name, String category) {
        this.name = name;      
        this.category = category;
    }

    public String getCategory() {
        return category;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Like like = (Like) o;

        if (category != null ? !category.equals(like.category) : like.category != null) return false;
        if (name != null ? !name.equals(like.name) : like.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (category != null ? category.hashCode() : 0);
        return result;
    }
}
