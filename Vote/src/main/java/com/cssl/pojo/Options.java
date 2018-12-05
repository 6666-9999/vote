package com.cssl.pojo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class Options {

    private Integer oid;
    private String content;
    private Integer osid;

    private Integer voteCount;

}
