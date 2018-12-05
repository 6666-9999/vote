package com.cssl.mapper;

import com.cssl.pojo.Item;
import org.apache.ibatis.annotations.Param;

public interface ItemMapper {

    int getByOidCount(int oid);

    int sava(Item item);

    int del(int sid);

    int isVote(@Param("uid") int uid,@Param("sid") int sid);
}
