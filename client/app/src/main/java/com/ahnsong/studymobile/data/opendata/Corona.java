package com.ahnsong.studymobile.data.opendata;


import com.tickaroo.tikxml.annotation.Element;
import com.tickaroo.tikxml.annotation.Xml;

import lombok.Getter;
import lombok.Setter;

@Xml(name = "response")
@Getter
@Setter
public class Corona {
    @Element(name = "header")
    Header header;
    @Element(name = "body")
    Body body;
}
