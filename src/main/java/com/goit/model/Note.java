package com.goit.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Entity
@Table(name = "notes")
public class Note {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "uuid", nullable = false, unique = true)
    private String uuid;

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Column(name = "content", nullable = false, length = 10000)
    private String content;

    @Enumerated(EnumType.STRING)
    @Column(name = "access_type", nullable = false)
    private AccessType accessType;

    public enum AccessType {
        PRIVATE,
        PUBLIC
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public AccessType getAccessType() {
        return accessType;
    }

    public void setAccessType(AccessType accessType) {
        this.accessType = accessType;
    }
}
