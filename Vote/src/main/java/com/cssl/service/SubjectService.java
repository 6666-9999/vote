package com.cssl.service;

import com.cssl.pojo.Subject;
import com.github.pagehelper.Page;

import java.util.List;
import java.util.Map;

public interface SubjectService {

    Page<Subject> getPage(Map<String,Object> map);

    boolean addSubejct(Subject sub);

    int getNewId();

    Subject getSubBySid(int sid);

    Subject getByOptions(int sid);

    boolean del(int sid);


    boolean updateVote(Subject sub);
}
