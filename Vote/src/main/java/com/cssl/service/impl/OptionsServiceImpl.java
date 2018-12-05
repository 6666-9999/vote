package com.cssl.service.impl;

import com.cssl.mapper.OptionsMapper;
import com.cssl.pojo.Options;
import com.cssl.service.OptionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class OptionsServiceImpl implements OptionsService {

    @Autowired
    private OptionsMapper om;

    @Override
    public boolean addOptions(Options op) {
        int row=om.add(op);
        if(row>0)
            return true;
        return false;
    }

    @Override
    public List<Options> getByOsid(int osid) {
        return om.getBySid(osid);
    }


    @Override
    public boolean del(int osid) {
        if(om.del(osid)>0)
            return true;
        return false;
    }

}
