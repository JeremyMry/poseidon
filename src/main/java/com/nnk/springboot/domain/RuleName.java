package com.nnk.springboot.domain;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.sql.Timestamp;

@Entity
public class RuleName {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;

    @Size(min= 1, max = 125, message = "{Size.Field.String}")
    private String name;

    @Size(min= 1, max = 125, message = "{Size.Field.String}")
    private String description;

    @Size(min= 1, max = 125, message = "{Size.Field.String}")
    private String json;

    @Size(min= 1, max = 125, message = "{Size.Field.String}")
    private String template;

    @Size(min= 1, max = 125, message = "{Size.Field.String}")
    private String sqlStr;

    @Size(min= 1, max = 125, message = "{Size.Field.String}")
    private String sqlPart;

    public RuleName() {
    }

    public RuleName(@Size(min = 1, max = 125, message = "{Size.Field.String}") String name, @Size(min = 1, max = 125, message = "{Size.Field.String}") String description, @Size(min = 1, max = 125, message = "{Size.Field.String}") String json, @Size(min = 1, max = 125, message = "{Size.Field.String}") String template, @Size(min = 1, max = 125, message = "{Size.Field.String}") String sqlStr, @Size(min = 1, max = 125, message = "{Size.Field.String}") String sqlPart) {
        this.name = name;
        this.description = description;
        this.json = json;
        this.template = template;
        this.sqlStr = sqlStr;
        this.sqlPart = sqlPart;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getJson() {
        return json;
    }

    public void setJson(String json) {
        this.json = json;
    }

    public String getTemplate() {
        return template;
    }

    public void setTemplate(String template) {
        this.template = template;
    }

    public String getSqlStr() {
        return sqlStr;
    }

    public void setSqlStr(String sqlStr) {
        this.sqlStr = sqlStr;
    }

    public String getSqlPart() {
        return sqlPart;
    }

    public void setSqlPart(String sqlPart) {
        this.sqlPart = sqlPart;
    }

    @Override
    public String toString() {
        return "RuleName{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", json='" + json + '\'' +
                ", template='" + template + '\'' +
                ", sqlStr='" + sqlStr + '\'' +
                ", sqlPart='" + sqlPart + '\'' +
                '}';
    }
}
