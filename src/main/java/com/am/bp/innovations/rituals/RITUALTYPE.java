package com.am.bp.innovations.rituals;

public enum RITUALTYPE {
    NAMECHANTING("Name Chanting", HEALTHCATEGORY.SOCIAL),
    SUMMITBREATHING("Summit Breathig", HEALTHCATEGORY.MENTAL),
    PUSHUPS("Pushups", HEALTHCATEGORY.PHYSICAL),
    SQUATS("Squats", HEALTHCATEGORY.PHYSICAL),
    AFFIRMATIONS("Affirmations", HEALTHCATEGORY.MENTAL);

    RITUALTYPE(String name, HEALTHCATEGORY healthCategory) {
        this.name = name;
        this.healthCategory = healthCategory;
    }

    String name;
    HEALTHCATEGORY healthCategory;

}
