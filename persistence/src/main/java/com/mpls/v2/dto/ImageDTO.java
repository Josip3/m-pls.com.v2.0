package com.mpls.v2.dto;

public class ImageDTO {

    private Long id;

    private String path;

    private String originalName;

    private Boolean available;

    public ImageDTO() {
    }

    public ImageDTO(String path, String originalName) {
        this.path = path;
        this.originalName = originalName;
    }

    public Boolean getAvailable() {
        return available;
    }

    public ImageDTO setAvailable(Boolean available) {
        this.available = available;
        return this;
    }

    public Long getId() {
        return id;
    }

    public ImageDTO setId(Long id) {
        this.id = id;
        return this;
    }

    public String getPath() {
        return path;
    }

    public ImageDTO setPath(String path) {
        this.path = path;
        return this;
    }

    public String getOriginalName() {
        return originalName;
    }

    public ImageDTO setOriginalName(String originalName) {
        this.originalName = originalName;
        return this;
    }

    @Override
    public String toString() {
        return "ImageDTO{" +
                "id=" + id +
                ", path='" + path + '\'' +
                ", originalName='" + originalName + '\'' +
                '}';
    }
}
