package com.cssl.service;

import com.cssl.pojo.Options;

import java.util.List;

public interface OptionsService {

    boolean addOptions(Options op);

    List<Options> getByOsid(int osid);

    boolean del(int osid);

}
