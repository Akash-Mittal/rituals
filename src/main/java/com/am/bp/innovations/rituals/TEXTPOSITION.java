package com.am.bp.innovations.rituals;

public enum TEXTPOSITION {

    TOPLEFT("x=0:y=0"), TOPCENTER("x=(w-text_w)/2:y=0"), TOPRIGHT("x=w-tw:y=0"), TOP_CENTER_CENTER(
            "x=(w-text_w)/2:y=(h-text_h)/2 - 45"), CENTER_LEFT("x=0:y=(h-text_h)/2"), CENTER(
                    "x=(w-text_w)/2:y=(h-text_h)/2"), CENTERRIGHT("x=(w-text_w)/2:y=0"), CENTER_BOTTOM_CENTER(
                            "x=(w-text_w)/2:y=(h-text_h)/2 + 45"), BOTTOMLEFT(
                                    "x=0:y=h-th"), BOTTOMCENTER("x=(w-text_w)/2:y=h-th"), BOTTOMRIGHT("x=w-tw:y=h-th");

    private String getFFMPEGPosition;

    TEXTPOSITION(final String value) {
        this.getFFMPEGPosition = value;
    }

    public String getValue() {
        return getFFMPEGPosition;
    }

    @Override
    public String toString() {
        return this.name();
    }
}