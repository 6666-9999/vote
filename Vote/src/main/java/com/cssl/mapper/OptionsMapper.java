package com.cssl.mapper;

import com.cssl.pojo.Options;

import java.util.List;

public interface OptionsMapper {

    int add(Options op);

    List<Options> getBySid(int osid);

    int del(int osid);

    int update(Options op);
}
