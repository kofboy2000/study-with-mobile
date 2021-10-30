package com.ahnsong.studymobile.data.opendata;

import com.tickaroo.tikxml.annotation.Element;
import com.tickaroo.tikxml.annotation.PropertyElement;
import com.tickaroo.tikxml.annotation.Xml;

import lombok.Getter;
import lombok.Setter;

@Xml(name = "body")
@Getter
@Setter
public class Body {
    @Element
    Items items;
    @PropertyElement
    int numOfRows;
    @PropertyElement
    int pageNo;
    @PropertyElement
    int totalCount;
}
