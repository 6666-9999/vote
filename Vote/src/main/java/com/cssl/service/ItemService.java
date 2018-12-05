package com.cssl.service;

import com.cssl.pojo.Item;

public interface ItemService {

    int getByOidCount(int oid);

    boolean savaItem(Item item);

    boolean del(int sid);

    int isVote(int uid,int sid);
}
