package com.am.bp.innovations.rituals;

public enum DIMENSIONS {
    PIXELS160("160x160",
            "The standard logo size for a horizontal website layout typically ranges from 250 to 400 pixels wide."), PIXELS240(
                    "426x240", "240p"), // pixels (240p). This dimension and resolution is the lowest accepted quality
                                        // for
                                        // YouTube uploads and works best for slower internet connections.
    PIXELS360("640x360", "360p"), // pixels (360p). This is considered the basic resolution for video-viewing on
                                  // smartphones, and is generally used for embedded videos on websites.
    PIXELS480("854x480", "480p"), // pixels (480p). Standard definition, as it’s known, is the general playback
                                  // resolution for YouTube videos streamed over cellular data. The most common aspect
                                  // ratio for standard definition is 4:3.
    PIXELS720("1280x720", "720p"), // pixels (720p). This is the minimum resolution required for high definition (HD)
                                   // playback. A full 16:9 aspect ratio is recommended for videos at this resolution.
                                   // 720p is the recommended resolution for watching HD videos on a slower internet
                                   // connection.
    PIXELS1080("1920x1080", "1080p"), // pixels (1080p). Full HD resolution occurs at 1080p, and is a top-tier
                                      // resolution if you want to upload high quality videos for advanced playback
                                      // devices.
    PIXELS1440("2560x1440", "1440p"), // pixels (1440p). For an even more detailed viewing experience, you can upload
                                      // videos in 1440p, also known as 2K.
    PIXELS2160("3840x2160", "2160p"), // pixels (2160p). Ultra HD or 4K resolutions are the best resolution for large
                                      // screens if you want a sharp, defined image.
    PIXELS4320("7680x4320", "4320p");// pixels (4320p). Also known as Full Ultra HD (FUHD), YouTube now accepts videos
                                     // in 8K resolution, though there are very few benefits to uploading a video of
                                     // this quality, mainly because most viewers don’t have the means to support this
                                     // level of resolution on their devices.

    private String dimensions;
    private String resolution;

    DIMENSIONS(String dimensions, String resolution) {
        this.dimensions = dimensions;
        this.resolution = resolution;
    }

    public String getDimensions() {
        return dimensions;
    }

    public void setDimensions(String dimensions) {
        this.dimensions = dimensions;
    }

    public String getResolution() {
        return resolution;
    }

    public void setResolution(String resolution) {
        this.resolution = resolution;
    }

    public DIMENSIONS getYoutubeDefault() {
        return DIMENSIONS.PIXELS720;
    }

    @Override
    public String toString() {
        return this.name();
    }
}