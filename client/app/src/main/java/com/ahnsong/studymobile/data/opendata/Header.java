package com.ahnsong.studymobile.data.opendata;

import com.tickaroo.tikxml.annotation.PropertyElement;
import com.tickaroo.tikxml.annotation.Xml;

import lombok.Getter;
import lombok.Setter;

@Xml(name = "header")
@Getter
@Setter
public class Header {
    @PropertyElement
    int resultCode;
    @PropertyElement
    String resultMsg;
}
