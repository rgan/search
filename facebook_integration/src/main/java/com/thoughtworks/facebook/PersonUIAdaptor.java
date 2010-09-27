package com.thoughtworks.facebook;

public class PersonUIAdaptor {
    private Person person;

    public PersonUIAdaptor(Person person) {
        this.person = person;
    }

    public String describe() {
        return describe(0);
    }

    public String describe(int depth) {
        StringBuilder description = new StringBuilder();
        description.append("<ul><li>").append("Name:").append(person.getNameOrId());
        description.append("(").append(person.getFriends().size()).append(")");
        if (person.getLikes().size() > 0) {
            description.append("<br>Likes:<br>");
            for (Like like : person.getLikes()) {
                description.append(like.getCategory() + " : " + like.getName() + "&nbsp;");
            }
        }
        if (person.getFriends().size() > 0) {
            description.append("<br>Friends:");
            for (Person friend : person.getFriends()) {
                description.append(new PersonUIAdaptor(friend).describe(depth + 1));
            }
        }
        description.append("</li></ul>");
        return description.toString();
    }
}
