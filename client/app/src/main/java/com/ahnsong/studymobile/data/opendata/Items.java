package com.ahnsong.studymobile.data.opendata;

import com.tickaroo.tikxml.annotation.Element;
import com.tickaroo.tikxml.annotation.Xml;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Xml(name = "items")
@Getter
@Setter
public class Items {
    @Element(name="item")
    List<Item> itemList;
}
