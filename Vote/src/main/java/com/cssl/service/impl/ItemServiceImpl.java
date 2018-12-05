package com.cssl.service.impl;

import com.cssl.mapper.ItemMapper;
import com.cssl.pojo.Item;
import com.cssl.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ItemMapper itemMapper;

    @Override
    public int getByOidCount(int oid) {
        return itemMapper.getByOidCount(oid);
    }

    @Override
    public boolean savaItem(Item item) {
        if(itemMapper.sava(item)>0)
            return true;
        return false;
    }

    @Override
    public boolean del(int sid) {
        if(itemMapper.del(sid)>0)
            return true;
        return false;
    }

    @Override
    public int isVote(int uid, int sid) {

        return itemMapper.isVote(uid,sid);
    }
}
