package com.cssl.pojo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Setter
@Getter
@ToString
public class Subject {

    private Integer sid;
    private String title;
    private Integer type;

    private Integer t_count;
    private Integer i_count;

    private List<Options> ops;
}
